package group;

import connectDB.Connect;
import create.AddUpdateDelete;
import java.sql.SQLException;


public class DeleteGroup extends AddUpdateDelete {
    private static final String[] options = {"Numer grupy"};
    Connect connect = new Connect();

    public DeleteGroup() {
        super();
        setTitle("Usuń grupę studencką");
    }

    @Override
    public String[] getOptions() {
        return options;
    }

    @Override
    public void action1() {
        GroupWindow groupWindow = new GroupWindow();
        groupWindow.setVisible(true);
        dispose();
    }

    @Override
    public void action2() {
        connect.connect();
        try {
            String sqlRecord = "DELETE FROM GRUPA WHERE id_grupy = '" + textFields[0].getText() + "'";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
    }
}
