/**
 * Dit programma leest data vanaf de USB seriële poort uit (vanaf bijv. Arduino of Microbit)
 * en insert dit in een MySQL database.
 * Gebruik van de jSerialCom library: https://github.com/Fazecast/jSerialComm/wiki/Usage-Examples
 */


package nl.dehaagsehogeschool.thechallenge;

import java.text.SimpleDateFormat;
import java.util.*; // Scanner om invoer te lezen
import java.util.concurrent.ThreadLocalRandom;

import com.fazecast.jSerialComm.*;
import static com.fazecast.jSerialComm.SerialPort.*;


public class ComPortSendReceive {

    public static SerialPort serialPort;

    public static void main(String[] args) {
        String portName;
        SerialPort portNames[] = SerialPort.getCommPorts();

        if (portNames.length == 0) {
            System.out.println("Er zijn geen seriële poorten. Sluit je Micro:bit aan!");
            return;
        }

        if (portNames.length == 1) {
            portName = portNames[0].getSystemPortName();
            System.out.println(portName + " wordt nu gebruikt.");
        } else {
            System.out.println("Meerdere seriële poorten gedetecteerd: ");
            for (int i = 0; i < portNames.length; i++) {
                System.out.println(portNames[i].getSystemPortName());
            }

            System.out.println("Type poortnaam die je wilt gebruiken en druk Enter...");
            Scanner in = new Scanner(System.in);
            portName = in.next();
        }

        serialPort = SerialPort.getCommPort(portName);

        try {
            // seriële poort openen en instellen
            serialPort.openPort();
            serialPort.setComPortParameters(9600, 8, ONE_STOP_BIT, NO_PARITY);
            serialPort.setFlowControl(FLOW_CONTROL_DISABLED);

            System.out.println("Seriële port is gereed...");

        } catch (Exception ex) {
            System.out.println("Fout bij schrijven naar seriële poort: " + ex);
        }

        try {
            Thread.sleep(5000); // 5 seconden pauzeren
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder bericht = new StringBuilder();
        InsertIntoSQL database = new InsertIntoSQL();
        serialPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }

            public void serialEvent(SerialPortEvent event) {
                //Als het event niet van een SerialPort komt, stopt het programma
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) { return; }

                //Een array van alle bytes
                byte buffer[] = new byte[serialPort.bytesAvailable()];

                //Zorgt ervoor dat de bytes van de arduino worden gelezen
                serialPort.readBytes(buffer, buffer.length);

                //Gaat langs elke byte in het bericht
                for (byte b : buffer) {
                    if ((b == '\r' || b == '\n') && bericht.length() > 0) { // regeleinde gedetecteerd

                        //Bericht veranderen naar String voor makkelijkere verwerking
                        String berichtString = bericht.toString();

                        //Als het bericht begint met een # dan is het de data en moet deze worden verwerkt
                        if(berichtString.startsWith("\n#")) {

                            //Eerst halen we de enter en de # weg
                            berichtString = berichtString.substring(2);

                            //Dan splitten we de data in verschillende data onderdelen
                            String[] berichtData = berichtString.split(",");

                            //De data wordt opgeslagen in individuele variabelen en er wordt een random ID aangemaakt
                            long locationID = ThreadLocalRandom.current().nextLong(1, 999999999);
                            float breedtegraad = Float.parseFloat(berichtData[0]);
                            float lengtegraad = Float.parseFloat(berichtData[1]);
                            long armbandID = Long.parseLong(berichtData[2]);
                            String naam = berichtData[3];
                            int actief = Integer.parseInt(berichtData[4]);



                            //Insert als het kan, anders wordt de data in de database geupdate
                            database.insertLocation(locationID, breedtegraad, lengtegraad);
                            database.insertArmband(armbandID, naam, locationID, actief);
                        }

                        //Ook als het bericht geen data is moet het worden geprint
                        else{
                            System.out.println(berichtString);
                        }

                        //Zorgt ervoor dat het oude bericht niet aan het nieuwe bericht wordt geplakt
                        bericht.setLength(0);
                    }
                    else {
                        bericht.append((char) b);
                    }
                }
            }
        });
    }
}
