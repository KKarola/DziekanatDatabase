package create;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Delete extends JDialog {
    public abstract String getOption();
    public JTextField textField1;

    public Delete() {
        initComponents();
        panel1();
        panel2();
        panel3();
    }

    public void initComponents() {
        setTitle("");
        setBounds(0, 0, 300, 175);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new GridLayout(3, 1));
    }

    public void panel1() {
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel(getOption()));
        textField1 = new JTextField(10);
        panel1.add(textField1);
        getContentPane().add(panel1);
    }

    public void panel2() {
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

    public void panel3() {
        JButton button2 = new JButton("Powrót do menu");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action1();
            }
        });
        getContentPane().add(button2);
    }

    public void action1() { }

    public void action2() { }
}