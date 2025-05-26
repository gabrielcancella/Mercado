import connection.MySQLConnection;
import controllers.ProdutoController;
import view.CadastroProduto;

import javax.swing.*;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = MySQLConnection.getConnection();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Mercado Três Irmãos");
            CadastroProduto cadastroProduto = new CadastroProduto();

            frame.setContentPane(cadastroProduto.getBackground());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}