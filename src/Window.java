
import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public static final String[] options = {"Grupy", "Oceny", "Przedmioty", "Studenci", "Wykładowcy"};
    public static final JButton[] button = new JButton[options.length];

    Window() {
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

    }

    public void addLabel() {
        JLabel label = new JLabel("Witaj w dziekanacie", JLabel.CENTER);
        label.setFont(new Font("Serif", Font.PLAIN, 30));
        add(label);
    }
}
