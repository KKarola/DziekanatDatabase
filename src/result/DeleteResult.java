package result;

import connectDB.Connect;
import create.AddUpdateDelete;
import java.sql.SQLException;

public class DeleteResult extends AddUpdateDelete {
    private static final String[] options = {"Numer studenta", "Numer przedmiotu"};
    Connect connect = new Connect();

    public DeleteResult() {
        super();
        setTitle("Usuń ocene");
    }

    @Override
    public String[] getOptions() {
        return options;
    }

    @Override
    public void action1() {
        ResultWindow resultWindow = new ResultWindow();
        resultWindow.setVisible(true);
        dispose();
    }

    @Override
    public void action2() {
        connect.connect();
        try {
            String sqlRecord = "DELETE FROM OCENA_KONCOWA WHERE student_id_studenta = '" + textFields[0].getText() +
                    "' AND przedmiot_id_przedmiotu = '" + textFields[1].getText() + "'";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
    }
}

