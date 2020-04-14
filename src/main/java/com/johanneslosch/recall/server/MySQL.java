package com.johanneslosch.recall.server;

import com.johanneslosch.recall.util.ConfigReader;

import java.sql.*;

/*

 */

public class MySQL {
    private static final String PORT = ConfigReader.read("data", "config", "dbPort");
    private static final String DBNAME = ConfigReader.read("data", "config", "dbName");
    private static final String USER = ConfigReader.read("data", "config","dbUser");
    private static final String PASSWORD = ConfigReader.read("data", "config","dbPassword");

    private static final String DATABASE_URL = "jdbc:mysql://" + ConfigReader.read("data", "config","dbHost")+":" + PORT + "/" + DBNAME;
    private static Connection connection;
    private static PreparedStatement prepareStatement;

    /**
     * @return                  the connection
     */
    // connect database
    private static Connection connect() {
        if (connection == null) {
            System.out.println("Connecting database...");

            try {
                connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
                System.out.println("Database connected!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * Set Data in Database for Storage
     * @param delay                 delay between send and remember
     * @param unit                  unit from delay
     * @param recipient             user who should get the message after all
     * @param sender                message sender
     * @return                      a Statement to set stuff
     * @throws SQLException         trows this if error
     */
    private static PreparedStatement preparedStatementStorage(String delay, String unit, String recipient, String sender) throws SQLException {
        connect();
        prepareStatement = connection.prepareStatement("INSERT INTO `log`(`delay`, `unit`, `recipient`) VALUES ( \"" + delay + "\",\"" + unit + "\",\"" + recipient + "\", \"" + sender + "\")");
        prepareStatement.execute();//
        return prepareStatement;
    }


//    static PreparedStatement deleteData(int id, String table) throws SQLException {
//        connect();
//        prepareStatement = connection.prepareStatement("DELETE FROM " + table + " WHERE `id` = " + id + ")");
//        prepareStatement.execute();//
//        return prepareStatement;
//    }

    /**
     * Helps to get data from DataBase
     * @param category              category in DataBase
     * @param table                 a mySQL table
     * @return                      a resultSet for usage
     * @throws SQLException         throws in Error
     */
    private static ResultSet getData(String category, String table) throws SQLException {
        connect();
        Statement statement = null;
        statement = connection.createStatement();
        String sql = ("SELECT " +  category + " FROM `" + table + "`;");
        assert statement != null;


        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet;
    }

    /**
     * Class to get Data from Database
     */
    public static class MySQLUseDataManager extends MySQL {
        public static String getInt(String category, String table) throws SQLException {
            return String.valueOf(getData(category, table).getInt(1));

        }

        public static String getString(String category, String table) throws SQLException {
            return getData(category, table).getString(1);
        }

        public static void setData(String delay, String unit, String recipient, String sender) throws SQLException {
            MySQL.preparedStatementStorage(delay, unit, recipient, sender);
        }
    }
}