package result;

import connectDB.Connect;
import create.Windows;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResultWindow extends Windows {
    static Connect connect = new Connect();
    JTable table;

    public ResultWindow() {
        super();
        setTitle("Oceny");
    }

    public void addTable() {
        table = new JTable();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        Object[] columnsName = new Object[3];
        columnsName[0] = "Id_studenta";
        columnsName[1] = "Id_przedmiotu";
        columnsName[2] = "Ocena";
        model.setColumnIdentifiers(columnsName);

        Object[] rowData = new Object[3];
        for (int i = 0; i < getResult().size(); i++) {
            rowData[0] = getResult().get(i).getStudentIdStudenta();
            rowData[1] = getResult().get(i).getPrzedmiotIdPrzedmiotu();
            rowData[2] = getResult().get(i).getOcena();
            model.addRow(rowData);
        }
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().add(table);
        getContentPane().add(scrollPane);
    }

    static ArrayList<Result> getResult() {
        ArrayList<Result> list = new ArrayList<>();
        connect.connect();
        Result result;
        try {
            String sqlRecord = "SELECT * FROM OCENA_KONCOWA";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.resultSet = connect.preparedStatement.executeQuery();
            while (connect.resultSet.next()) {
                result = new Result(
                        connect.resultSet.getInt("student_id_studenta"),
                        connect.resultSet.getInt("przedmiot_id_przedmiotu"),
                        connect.resultSet.getInt("ocena")
                );
                list.add(result);
            }
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
        return list;
    }

    public void click() {
        button[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddResult addResult = new AddResult();
                addResult.setVisible(true);
                dispose();
            }
        });
        button[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateResult updateResult = new UpdateResult();
                updateResult.setVisible(true);
                dispose();
            }
        });
        button[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteResult deleteResult = new DeleteResult();
                deleteResult.setVisible(true);
                dispose();
            }
        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectRow = table.rowAtPoint(e.getPoint());
                    TableModel model = table.getModel();
                    String idStudent = model.getValueAt(selectRow, 0).toString();
                    String idPrzedmiot = model.getValueAt(selectRow, 1).toString();
                    String ocena = model.getValueAt(selectRow, 2).toString();
                    UpdateResult updateResult = new UpdateResult();
                    updateResult.setVisible(true);
                    updateResult.textFields[0].setText(idStudent);
                    updateResult.textFields[1].setText(idPrzedmiot);
                    updateResult.textFields[2].setText(ocena);
                    dispose();
                }
            }
        });
    }
}