package student;

import connectDB.Connect;
import create.AddUpdateDelete;
import java.sql.SQLException;

public class DeleteStudent extends AddUpdateDelete {
    private static final String[] options = {"Numer studenta"};
    Connect connect = new Connect();

    public DeleteStudent() {
        super();
        setTitle("Usuń studenta");
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
            String sqlRecord = "DELETE FROM STUDENT WHERE id_studenta = '" + textFields[0].getText() + "'";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
    }
}