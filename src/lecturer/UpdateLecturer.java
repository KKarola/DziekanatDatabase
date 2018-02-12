package lecturer;

import connectDB.Connect;
import create.AddUpdateDelete;

import java.sql.SQLException;

public class UpdateLecturer extends AddUpdateDelete {
    private static final String[] options = {"Numer wykładowcy", "Imie", "Nazwisko", "Tytul", "Przedmiot_Id_przedmiotu"};
    Connect connect = new Connect();

    public UpdateLecturer() {
        super();
        setTitle("Zmień dane wykładowcy");
    }

    @Override
    public String[] getOptions() {
        return options;
    }

    @Override
    public void action1() {
        LecturerWindow lecturerWindow = new LecturerWindow();
        lecturerWindow.setVisible(true);
        dispose();
    }

    @Override
    public void action2() {
        connect.connect();
        try {
            String sqlRecord = "UPDATE WYKLADOWCA SET imie = '" + textFields[1].getText() +
                    "', nazwisko = '" + textFields[2].getText() + "', tytul = '" + textFields[3].getText() +
                    "', przedmiot_id_przedmiotu = '" + textFields[4].getText() +
                    "' WHERE id_wykladowcy = '" + textFields[0].getText() + "'";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
    }
}
