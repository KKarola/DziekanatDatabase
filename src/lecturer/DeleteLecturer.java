package lecturer;

import connectDB.Connect;
import create.AddUpdateDelete;
import java.sql.SQLException;

public class DeleteLecturer extends AddUpdateDelete {
    private static final String[] options = {"Numer wykładowcy"};
    Connect connect = new Connect();

    public DeleteLecturer() {
        super();
        setTitle("Usuń wykładowce");
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
            String sqlRecord =  "DELETE FROM WYKLADOWCA WHERE id_wykladowcy = '" + textFields[0].getText() + "'";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
    }
}
