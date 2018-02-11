package course;

import connectDB.Connect;
import create.Delete;
import java.sql.SQLException;

public class DeleteCourse extends Delete{
    private static final String options = "Numer przedmiotu";
    Connect connect = new Connect();

    public DeleteCourse() {
        super();
        setTitle("Usuń przedmiot");
    }

    @Override
    public String getOption() {
        return options;
    }

    @Override
    public void action1() {
        StudentsCourse studentsCourse = new StudentsCourse();
        studentsCourse.setVisible(true);
        dispose();
    }

    public void action2() {
        connect.connect();
        try {
            String sqlRecord = "DELETE FROM PRZEDMIOT WHERE id_przedmiotu = '" + textField1.getText() + "'";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
    }
}
