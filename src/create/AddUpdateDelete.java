package create;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AddUpdateDelete extends JDialog {
    public abstract String[] getOptions();
    public JPanel[] panels = new JPanel[getOptions().length];
    public JTextField[] textFields = new JTextField[getOptions().length];
    public abstract void action1();
    public abstract void action2();

    public AddUpdateDelete() {
        initComponents();
        getPanels();
        panel1();
        panel2();
    }

    public void initComponents() {
        setTitle("");
        setBounds(0, 0, 340, (getOptions().length + 2 ) * 32 + 20);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new GridLayout(getOptions().length + 2, 1));
    }

    public void getPanels() {
        for (int i = 0; i < getOptions().length; i++) {
            panels[i] = new JPanel();
            panels[i].setLayout(new GridLayout(1,2));
            panels[i].add(new JLabel(getOptions()[i]));
            textFields[i] = new JTextField(10);
            panels[i].add(textFields[i]);
            getContentPane().add(panels[i]);
        }
    }

    public void panel1() {
        JButton button1 = new JButton("Zapisz zmiany");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action2();
                action1();
            }
        });
        getContentPane().add(button1);
    }

    public void panel2() {
        JButton button2 = new JButton("Powrót do menu");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action1();
            }
        });
        getContentPane().add(button2);
    }

}