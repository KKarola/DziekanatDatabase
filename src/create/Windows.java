package create;

import javax.swing.*;
import java.awt.*;

public abstract class Windows extends JFrame {
    private static final String[] options = {"Dodaj", "Zmień", "Usuń"};
    public static final JButton[] button = new JButton[options.length];
    public abstract void addTable();
    public abstract void click();

    public Windows() {
        initComponent();
        addButtons();
        addTable();
        click();
    }

    public void initComponent() {
        setTitle("");
        setBounds(0, 0, 400, 400);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
    }

    public void addButtons() {
        JPanel panel = new JPanel();
        for (int i = 0; i < button.length; i++) {
            button[i] = new JButton(options[i]);
            panel.add(button[i]);
        }
        add(panel, BorderLayout.PAGE_START);
    }

}