import connection.MySQLConnection;

public class Main {
    public static void main(String[] args) {
        // Test the MySQLConnection class
        MySQLConnection connection = new MySQLConnection();
        connection.getConnection();
        System.out.println("Connection established: " + (connection.getConnection() != null));

        // Close the connection
        connection.closeConnection();
        System.out.println("Connection closed.");
    }
}