package course;

import create.AddUpdateDelete;
import connectDB.Connect;
import java.sql.SQLException;

public class AddCourse extends AddUpdateDelete {
    private static final String[] options = {"Numer przedmiotu", "Nazwa przedmiotu", "Skrót przedmiotu", "ECTS", "Grupa_Id_grupy"};
    Connect connect = new Connect();

    public AddCourse() {
        super();
        setTitle("Dodaj przedmiot");
    }

    @Override
    public String[] getOptions() {
        return options;
    }

    @Override
    public void action1() {
        CourseWindow courseWindow = new CourseWindow();
        courseWindow.setVisible(true);
        dispose();
    }

    @Override
    public void action2() {
        connect.connect();
        try {
            String sqlRecord = "INSERT INTO PRZEDMIOT(id_przedmiotu, nazwa_przedmiotu, skrot, ects, grupa_id_grupy) VALUES" +
                    "('" + textFields[0].getText() + "', '" + textFields[1].getText() + "', '" + textFields[2].getText() +
                    "', '" + textFields[3].getText() + "', '" + textFields[4].getText() + "')";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
    }
}