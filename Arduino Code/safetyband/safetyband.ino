#include <SoftwareSerial.h>
#include <TinyGPS++.h>

int n = 0;
int buttonHULP = 7;
int buttonCANCEL = 6;

int buttonCANCELState = 0;
int buttonHULPState = 0;

int led = 5;

//De gegevens die doorgestuurd moeten worden (moeten nog veranderd worden met GPS ding)
String latitude = "52.023774065657";
String longitude = "4.3539098118103";
String armbandID = "1";
String armbandNaam = "TestArmband";
String armbandActief = "1";



//Alle gegevens in een mooie message
String message = "#" + String(latitude) + "," + String(longitude) + "," + String(armbandID)+ "," + String(armbandNaam)+ "," + String(armbandActief);


//GPS installeren
SoftwareSerial gpsSerial(3,4);
TinyGPSPlus gps; 

void setup()
{
  Serial.begin(9600);
  gpsSerial.begin(9600);
  pinMode(buttonHULP, INPUT);
  pinMode(buttonCANCEL, INPUT);
  pinMode(led, OUTPUT);
}

void loop() {

//Een message zodat de eventReader niet automatisch stopt
  if(n == 0) {
    Serial.println("Connection established...");
    n++;
  }

//Als de hulp button wordt ingedrukt gaat een lampje aan en kom je in een volgende loop
  if(digitalRead(buttonHULP) == LOW) {
    digitalWrite(led, HIGH);
    delayCheckPress(10000);  
  }
    
  digitalWrite(led, LOW);
}

//Methode om het controleren van de cancel button te delayen terwijl je blijft controleren
void delayCheckPress(unsigned long duration) {
  unsigned long start = millis();

    //Een loop die voor een bepaalde tijd duurt
    while (millis()-start <= duration) {
         //Elke loop check je de cancel button
         if((digitalRead(buttonCANCEL)) == LOW) {
            Serial.println("Message has been cancelled.");
            return;
         }         
    } 
    while (gpsSerial.available() > 0){
      gps.encode(gpsSerial.read());
      if (gps.location.isUpdated()){
        latitude = gps.location.lat();
        longitude = gps.location.lng();
        Serial.println(message);
        delay(500);
     }
   }
    Serial.println(message);
    delay(500);
}
