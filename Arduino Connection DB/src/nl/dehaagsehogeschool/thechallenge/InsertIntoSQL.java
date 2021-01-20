package nl.dehaagsehogeschool.thechallenge;

import java.sql.*;

public class InsertIntoSQL {

    /**
     * Connect to the vb1.db database
     *
     * @return the Connection object
     */
    private Connection connect() {
        Connection conn = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        // MySQL connection string, pas zonodig het pad aan:
        String connection = "jdbc:mysql://localhost:3306/safetyband";
        String user = "root";
        String password = "";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(connection, user, password);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Update a row into the location table
     *
     * @param id
     * @param breedtegraad
     * @param lengtegraad
     */
    public void updateLocation(long id, float breedtegraad, float lengtegraad) {
        String sql = "UPDATE location SET breedtegraad = ?,  huisnummer = ?,  land = ?,  lengtegraad = ?,  postcode = ?,  provincie = ?,  stad = ?,  straatnaam = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connect().prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.setFloat(2, breedtegraad);
            preparedStatement.setNull(3, Types.INTEGER);
            preparedStatement.setString(4, null);
            preparedStatement.setFloat(5, lengtegraad);
            preparedStatement.setString(6, null);
            preparedStatement.setString(7, null);
            preparedStatement.setString(8, null);
            preparedStatement.setString(9, null);
            preparedStatement.executeUpdate();
            System.out.println("Location has been updated");
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Insert a row into the location table or update if inserting fails
     *
     * @param id
     * @param breedtegraad
     * @param lengtegraad
     */
    public void insertLocation(long id, float breedtegraad, float lengtegraad) {
        String sql = "INSERT INTO location(id, breedtegraad, huisnummer, land, lengtegraad, postcode, provincie, stad, straatnaam) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connect().prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.setFloat(2, breedtegraad);
            preparedStatement.setNull(3, Types.INTEGER);
            preparedStatement.setString(4, null);
            preparedStatement.setFloat(5, lengtegraad);
            preparedStatement.setString(6, null);
            preparedStatement.setString(7, null);
            preparedStatement.setString(8, null);
            preparedStatement.setString(9, null);
            preparedStatement.executeUpdate();
            System.out.println("Location has been inserted into Database");
        }
        catch (Exception e) {
            System.err.println(e.getMessage() + "\nTrying to update Location instead...");
            updateLocation(id, breedtegraad, lengtegraad);
        }
    }

    /**
     * Update a row into the location table
     *
     * @param id
     * @param naam
     * @param locationID
     * @param actief
     */
    public void updateArmband(long id, String naam, long locationID, int actief) {
        String sql = "UPDATE armband SET naam = ?,  location_id = ?,  actief = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connect().prepareStatement(sql);
            preparedStatement.setString(1, naam);
            preparedStatement.setLong(2, locationID);
            preparedStatement.setInt(3, actief);
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();
            System.out.println("Armband has been updated");
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Insert a row into the location table or update if inserting fails
     *
     * @param id
     * @param naam
     * @param locationID
     * @param actief
     */
    public void insertArmband(long id, String naam, long locationID, int actief) {
        String sql = "INSERT INTO armband(id, naam, location_id, actief) VALUES(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connect().prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, naam);
            preparedStatement.setLong(3, locationID);
            preparedStatement.setInt(4, actief);
            preparedStatement.executeUpdate();
            System.out.println("Armband has been inserted into Database");
        }
        catch (Exception e) {
            System.err.println(e.getMessage() + "\nTrying to update database instead...");
            updateArmband(id, naam, locationID, actief);
        }
    }

}
