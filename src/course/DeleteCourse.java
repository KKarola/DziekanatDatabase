package course;

import connectDB.Connect;
import create.AddUpdateDelete;
import java.sql.SQLException;

public class DeleteCourse extends AddUpdateDelete {
    private static final String[] options = {"Numer przedmiotu"};
    Connect connect = new Connect();

    public DeleteCourse() {
        super();
        setTitle("Usuń przedmiot");
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
            String sqlRecord = "DELETE FROM PRZEDMIOT WHERE id_przedmiotu = '" + textFields[0].getText() + "'";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
    }
}

