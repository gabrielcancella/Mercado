package view;

import models.interfaces.Tela;

import javax.swing.*;
import java.awt.*;

public class ViewManager {
    private static ViewManager instance = null;
    private JFrame frame;

    public ViewManager() {
        SwingUtilities.invokeLater(() -> {
            this.frame = new JFrame("Mercado Três Irmãos");
            TelaPrincipal telaPrincipal = new TelaPrincipal();

            frame.setContentPane(telaPrincipal.getBackground());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public static void init() {
        if (instance == null) {
            instance = new ViewManager();
        }
    }

    public static ViewManager getInstance() {
        if (instance == null) {
            instance = new ViewManager();
        }
        return instance;
    }

    public static void setScreen(Tela tela) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = getInstance().frame;
            frame.setContentPane(tela.getBackground());
            frame.revalidate();
            frame.repaint();
        });
    }

    public static void setWindowSize(int width, int height) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = getInstance().getFrame();
            frame.setSize(width, height);
            frame.setMinimumSize(new Dimension(width, height));
            frame.setMaximumSize(new Dimension(width, height));
        });
    }

    public static void backToMainScreen() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = getInstance().getFrame();
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            frame.setContentPane(telaPrincipal.getBackground());
            frame.revalidate();
            frame.repaint();
        });
    }

    public JFrame getFrame() {
        return frame;
    }
}
