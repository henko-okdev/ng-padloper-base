package ngdemo.db;


import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static ngdemo.db.DataBaseUtils.closeSilently;


public class DataBaseManager {

    private final HikariDataSource source;

    public DataBaseManager(HikariDataSource source) {
        this.source = source;
    }

    public void createTables() {
        createOperators();
        createContacts();
        createDonations();
        createTerritories();
    }

    private void createTerritories() {
        Connection conn = null;
        String query = "CREATE TABLE IF NOT EXISTS PUBLIC.TERRITORIES(" +
                        "ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL," +
                        "NAME VARCHAR(80) NOT NULL," +
                        "PARENT_ID INT," +
                        "TYPE VARCHAR(80) NOT NULL," +
                        "ZIP VARCHAR(80));";
        try {
            conn = source.getConnection();
            conn.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeSilently(conn);
        }
    }

    private void createDonations() {
        Connection conn = null;
        String query = "CREATE TABLE IF NOT EXISTS PUBLIC.DONATIONS(" +
                        "ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL," +
                        "OPERATOR_ID INT NOT NULL," +
                        "CONTACT_ID INT NOT NULL," +
                        "AMOUNT DECIMAL NOT NULL," +
                        "DATE DECIMAL NOT NULL);";

        try {
            conn = source.getConnection();
            conn.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeSilently(conn);
        }
    }

    private void createContacts() {
        Connection conn = null;
        String query = "CREATE TABLE IF NOT EXISTS PUBLIC.CONTACTS(" +
                        "ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL," +
                        "FIRST_NAME VARCHAR(80) NOT NULL," +
                        "LAST_NAME VARCHAR(80) NOT NULL," +
                        "PHONE VARCHAR(80)," +
                        "EMAIL VARCHAR(80)," +
                        "CITY_ID INT);";

        try {
            conn = source.getConnection();
            conn.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeSilently(conn);
        }
    }

    private void createOperators() {
        Connection conn = null;
        String query = "CREATE TABLE IF NOT EXISTS PUBLIC.OPERATORS(" +
                        "ID INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL," +
                        "FIRST_NAME VARCHAR(80) NOT NULL," +
                        "LAST_NAME VARCHAR(80) NOT NULL," +
                        "PHONE VARCHAR(80) NOT NULL," +
                        "EMAIL VARCHAR(80) NOT NULL);";

        try {
            conn = source.getConnection();
            conn.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeSilently(conn);
        }
    }
}
