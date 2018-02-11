package group;

import connectDB.Connect;
import create.Delete;
import java.sql.SQLException;


public class DeleteGroup extends Delete {
    private static final String options = "Numer grupy";
    Connect connect = new Connect();

    public DeleteGroup() {
        super();
        setTitle("Usuń grupe");
    }

    @Override
    public String getOption() {
        return options;
    }

    @Override
    public void action1() {
        StudentsGroup studentsGroupe = new StudentsGroup();
        studentsGroupe.setVisible(true);
        dispose();
    }

    public void action2() {
        connect.connect();
        try {
            String sqlRecord = "DELETE FROM GRUPA WHERE id_grupy = '" + textField1.getText() + "'";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
    }
}


