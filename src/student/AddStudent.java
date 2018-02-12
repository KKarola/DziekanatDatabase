package student;

import connectDB.Connect;
import create.AddUpdateDelete;
import java.sql.SQLException;

public class AddStudent extends AddUpdateDelete {
    private static final String[] options = {"Numer studenta", "Imie", "Nazwisko", "Grupa_Id_grupy"};
    Connect connect = new Connect();

    public AddStudent() {
        super();
        setTitle("Dodaj studenta");
    }

    @Override
    public String[] getOptions() {
        return options;
    }

    @Override
    public void action1() {
        StudentWindow studentWindow = new StudentWindow();
        studentWindow.setVisible(true);
        dispose();
    }

    @Override
    public void action2() {
        connect.connect();
        try {
            String sqlRecord = "INSERT INTO STUDENT(id_studenta, imie, nazwisko, grupa_id_grupy) VALUES" +
                    "('" + textFields[0].getText() + "', '" + textFields[1].getText() +
                    "', '" + textFields[2].getText() + "', '" + textFields[3].getText() + "')";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
    }
}