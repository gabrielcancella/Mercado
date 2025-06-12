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

            int width = Tela.WIDTH;
            int height = Tela.HEIGHT;

            try {
                Class<?> telaClass = tela.getClass();
                width = telaClass.getDeclaredField("WIDTH").getInt(null);
                height = telaClass.getDeclaredField("HEIGHT").getInt(null);
            } catch (Exception e) {
                System.out.println("Usando dimensões padrão da interface Tela");
                System.out.println("WIDTH padrão: " + width);
                System.out.println("HEIGHT padrão: " + height);
            }

            setWindowSize(width, height);
        });
    }

    public static void setWindowSize(int width, int height) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = getInstance().getFrame();
            Dimension size = new Dimension(width, height);
            frame.setResizable(false);
            frame.setSize(size);
            frame.setLocationRelativeTo(null);
        });
    }

    public static void backToMainScreen() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = getInstance().getFrame();
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            frame.setContentPane(telaPrincipal.getBackground());
            frame.revalidate();
            frame.repaint();

            setWindowSize(TelaPrincipal.WIDTH, TelaPrincipal.HEIGHT);
        });
    }

    public JFrame getFrame() {
        return frame;
    }
}
