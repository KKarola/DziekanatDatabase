package course;

import connectDB.Connect;
import create.AddUpdate;
import java.sql.SQLException;

public class UpdateCourse extends AddUpdate {
    private static final String[] options = {"Numer przedmiotu", "Nazwa przedmiotu",
            "Skrót przedmiotu", "ECTS", "Grupa_Id_grupy"};
    Connect connect = new Connect();

    public UpdateCourse() {
        super();
        setTitle("Zmień przedmiot");
    }

    @Override
    public String[] getOptions() {
        return options;
    }

    @Override
    public void action1() {
        StudentsCourse studentsCourse = new StudentsCourse();
        studentsCourse.setVisible(true);
        dispose();
    }

    @Override
    public void action2() {
        connect.connect();
        try {
            String sqlRecord = "UPDATE PRZEDMIOT SET nazwa_przedmiotu = '" + textFields[1].getText() +
                    "', skrot = '" + textFields[2].getText() + "', ects = '" + textFields[3].getText() +
                    "', grupa_id_grupy = '" + textFields[4].getText() +
                    "' WHERE id_przedmiotu = '" + textFields[0].getText() + "'";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
    }
}