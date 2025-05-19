package connection;

import java.sql.Connection;

public class MySQLConnection {
    private static String url = "jdbc:mysql://localhost:3306/mercado_tres_irmoes";
    private static String usuario = "root";
    private static String senha = "root";
    public static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            setConnection();
        }
        return conn;
    }

    private static void setConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = java.sql.DriverManager.getConnection(url, usuario, senha);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
