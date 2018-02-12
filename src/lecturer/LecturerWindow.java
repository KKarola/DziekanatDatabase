package lecturer;

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

public class LecturerWindow extends Windows {
    static Connect connect = new Connect();
    JTable table;

    public LecturerWindow() {
        super();
        setTitle("Wykładowcy");
    }

    public void addTable() {
        table = new JTable();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return false;
            }
        };
        Object[] columnsName = new Object[5];
        columnsName[0] = "Id_wykladowcy";
        columnsName[1] = "Imie";
        columnsName[2] = "Nazwisko";
        columnsName[3] = "Tytuł";
        columnsName[4] = "Przedmiot_id_przedmiotu";
        model.setColumnIdentifiers(columnsName);

        Object[] rowData = new Object[5];
        for (int i = 0; i < getLecturer().size(); i++) {
            rowData[0] = getLecturer().get(i).getIdWykladowcy();
            rowData[1] = getLecturer().get(i).getImie();
            rowData[2] = getLecturer().get(i).getNazwisko();
            rowData[3] = getLecturer().get(i).getTytul();
            rowData[4] = getLecturer().get(i).getPrzedmiot_id_przedmioturzedmiotu();
            model.addRow(rowData);
        }
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().add(table);
        getContentPane().add(scrollPane);
    }

    static ArrayList<Lecturer> getLecturer() {
        ArrayList<Lecturer> list = new ArrayList<>();
        connect.connect();
        Lecturer lecturer;
        try {
            String sqlRecord = "SELECT * FROM WYKLADOWCA";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.resultSet = connect.preparedStatement.executeQuery();
            while (connect.resultSet.next()) {
                lecturer = new Lecturer(
                        connect.resultSet.getInt("id_wykladowcy"),
                        connect.resultSet.getString("imie"),
                        connect.resultSet.getString("nazwisko"),
                        connect.resultSet.getString("tytul"),
                        connect.resultSet.getInt("przedmiot_id_przedmiotu")
                );
                list.add(lecturer);
            }
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
        return list;
    }

    @Override
    public void click() {
        button[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddLecturer addLecturer = new AddLecturer();
                addLecturer.setVisible(true);
                dispose();
            }
        });
        button[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateLecturer updateLecturer = new UpdateLecturer();
                updateLecturer.setVisible(true);
                dispose();
            }
        });
        button[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteLecturer deleteLecturer = new DeleteLecturer();
                deleteLecturer.setVisible(true);
                dispose();
            }
        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectRow = table.rowAtPoint(e.getPoint());
                    TableModel model = table.getModel();
                    String idLecturer = model.getValueAt(selectRow, 0).toString();
                    String imie = model.getValueAt(selectRow, 1).toString();
                    String nazwisko = model.getValueAt(selectRow, 2).toString();
                    String tytul = model.getValueAt(selectRow, 3).toString();
                    String PrzedmiotIdPrzedmiot = model.getValueAt(selectRow, 4).toString();
                    UpdateLecturer updateLecturer = new UpdateLecturer();
                    updateLecturer.setVisible(true);
                    updateLecturer.textFields[0].setText(idLecturer);
                    updateLecturer.textFields[1].setText(imie);
                    updateLecturer.textFields[2].setText(nazwisko);
                    updateLecturer.textFields[3].setText(tytul);
                    updateLecturer.textFields[4].setText(PrzedmiotIdPrzedmiot);
                    dispose();
                }
            }
        });
    }
}