package student;

import connectDB.Connect;
import create.AddUpdateDelete;
import java.sql.SQLException;

public class UpdateStudent extends AddUpdateDelete {
    private static final String[] options = {"Numer studenta", "Imie", "Nazwisko", "Grupa_Id_grupy"};
    Connect connect = new Connect();

    public UpdateStudent() {
        super();
        setTitle("Zmień dane studenta");
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
            String sqlRecord = "UPDATE STUDENT SET imie = '" + textFields[1].getText() +
                    "', nazwisko = '" + textFields[2].getText() + "', grupa_id_grupy = '" + textFields[3].getText() +
                    "' WHERE id_studenta = '" + textFields[0].getText() + "'";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
    }
}
