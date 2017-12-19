package io.eodc.infinitybotjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler {
    public static void createDB(String fileName) {
        String url = "jdbc:sqlite:./" + fileName;
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("DB has been created at " + url);
            }
        } catch (SQLException e) {
            System.out.print("[ERROR] ");
            e.printStackTrace();
        }
    }
}
