package connection;

import java.sql.Connection;

public class MySQLConnection {
    private static String url = "jdbc:mysql://localhost:3306/mercado_tres_irmoes";
    public static String usuario = "root";
    public static String senha = "root";
    private Connection conn;

    public Connection getConnection() {
        if (this.conn == null) {
            setConnection();
        }
        return this.conn;
    }

    private void setConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = java.sql.DriverManager.getConnection(url, usuario, senha);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (this.conn != null) {
                this.conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
