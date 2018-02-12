package result;

import connectDB.Connect;
import create.AddUpdateDelete;

import java.sql.SQLException;

public class UpdateResult extends AddUpdateDelete {
    private static final String[] options = {"Numer studenta", "Numer przedmiotu", "Ocena"};
    Connect connect = new Connect();

    public UpdateResult() {
        super();
        setTitle("Zmień ocene");
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
            String sqlRecord = "UPDATE OCENA_KONCOWA SET ocena = '" + textFields[2].getText() +
                    "' WHERE student_id_studenta = '" + textFields[0].getText() +
                    "' AND przedmiot_id_przedmiotu = '" + textFields[1].getText() + "'";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
    }
}