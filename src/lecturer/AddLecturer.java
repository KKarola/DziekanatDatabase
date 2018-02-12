package lecturer;

import connectDB.Connect;
import create.AddUpdateDelete;
import java.sql.SQLException;

public class AddLecturer extends AddUpdateDelete {
    private static final String[] options = {"Numer wykładowcy", "Imie", "Nazwisko", "Tytul", "Przedmiot_Id_przedmiotu"};
    Connect connect = new Connect();

    public AddLecturer() {
        super();
        setTitle("Dodaj wykładowce");
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
            String sqlRecord = "INSERT INTO WYKLADOWCA(id_wykladowcy, imie, nazwisko, tytul, przedmiot_id_przedmiotu) VALUES" +
                    "('" + textFields[0].getText() + "', '" + textFields[1].getText() +
                    "', '" + textFields[2].getText() + "', '" + textFields[3].getText() +
                    "', '" + textFields[4].getText() + "')";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
    }
}