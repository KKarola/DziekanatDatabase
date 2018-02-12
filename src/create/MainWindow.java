package create;

import group.GroupWindow;
import lecturer.LecturerWindow;
import result.ResultWindow;
import student.StudentWindow;
import course.CourseWindow;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    public static final String[] options = {"Grupy", "Oceny", "Przedmioty", "Studenci", "Wykładowcy"};
    public static final JButton[] button = new JButton[options.length];

    MainWindow() {
        setTitle("Dziekanat");
        setPreferredSize(new Dimension(500,160));
        setLayout(new GridLayout(2,1));
        addButtons();
        addLabel();
        new CreateDB();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addButtons() {
        JPanel panel = new JPanel();
        for(int i = 0; i < options.length; i ++) {
            button[i] = new JButton(options[i]);
            panel.add(button[i]);
        }
        add(panel);
        click();
    }

    public void click() {
        button[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GroupWindow groupWindow = new GroupWindow();
                groupWindow.setVisible(true);
            }
        });
        button[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultWindow resultWindow = new ResultWindow();
                resultWindow.setVisible(true);
            }
        });
        button[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CourseWindow courseWindow = new CourseWindow();
                courseWindow.setVisible(true);
            }
        });
        button[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentWindow studentWindow = new StudentWindow();
                studentWindow.setVisible(true);
            }
        });
        button[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LecturerWindow lecturerWindow = new LecturerWindow();
                lecturerWindow.setVisible(true);
            }
        });
    }

    public void addLabel() {
        JLabel label = new JLabel("Witaj w dziekanacie", JLabel.CENTER);
        label.setFont(new Font("Serif", Font.PLAIN, 30));
        add(label);
    }
}
