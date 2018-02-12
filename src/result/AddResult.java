package result;

import connectDB.Connect;
import create.AddUpdateDelete;
import java.sql.SQLException;

public class AddResult extends AddUpdateDelete {
    private static final String[] options = {"Numer studenta", "Numer przedmiotu", "Ocena"};
    Connect connect = new Connect();

    public AddResult() {
        super();
        setTitle("Dodaj ocene");
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
            String sqlRecord = "INSERT INTO OCENA_KONCOWA(student_id_studenta, przedmiot_id_przedmiotu, ocena) VALUES" +
                    "('" + textFields[0].getText() + "', '" + textFields[1].getText() + "', '" + textFields[2].getText() + "')";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
    }
}