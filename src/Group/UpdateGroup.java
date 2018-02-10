package Group;

import connectDB.Connect;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UpdateGroup extends JDialog {
    Connect connect = new Connect();
    JTextField textField1 = new JTextField(10);
    JTextField textField2 = new JTextField(10);

    public UpdateGroup() {
        initComponents();
        panel1();
        panel2();
        panel3();
        panel4();
    }

    public void initComponents() {
        setTitle("Zmodyfikuj grupę studencką");
        setBounds(0, 0, 300, 175);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new GridLayout(4, 1));
    }

    public void panel1() {
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Numer grupy"));
        panel1.add(textField1);
        getContentPane().add(panel1);
    }

    public void panel2() {
        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("Nowa nazwa grupy"));
        panel2.add(textField2);
        getContentPane().add(panel2);
    }

    public void panel3() {
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

    public void panel4() {
        JButton button2 = new JButton("Powrót do menu");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action1();
            }
        });
        getContentPane().add(button2);
    }

    public void action1() {
        StudentsGroup studentsGroup = new StudentsGroup();
        studentsGroup.setVisible(true);
        dispose();
    }

    public void action2() {
        connect.connect();
        try {
            String sqlRecord = "UPDATE GRUPA SET nazwa_grupy = '" +
                    textField2.getText() + "' WHERE id_grupy = '" + textField1.getText() + "'";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
    }
}


