package student;

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

public class StudentWindow extends Windows {
    static Connect connect = new Connect();
    JTable table;

    public StudentWindow() {
        super();
        setTitle("Studenci");
    }

    public void addTable() {
        table = new JTable();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return false;
            }
        };
        Object[] columnsName = new Object[4];
        columnsName[0] = "Id_studenta";
        columnsName[1] = "Imie";
        columnsName[2] = "Nazwisko";
        columnsName[3] = "Grupa_id_grupy";
        model.setColumnIdentifiers(columnsName);

        Object[] rowData = new Object[4];
        for (int i = 0; i < getStudent().size(); i++) {
            rowData[0] = getStudent().get(i).getIdStudenta();
            rowData[1] = getStudent().get(i).getImie();
            rowData[2] = getStudent().get(i).getNazwisko();
            rowData[3] = getStudent().get(i).getGrupaIdGrupy();
            model.addRow(rowData);
        }
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().add(table);
        getContentPane().add(scrollPane);
    }

    static ArrayList<Student> getStudent() {
        ArrayList<Student> list = new ArrayList<>();
        connect.connect();
        Student student;
        try {
            String sqlRecord = "SELECT * FROM STUDENT";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.resultSet = connect.preparedStatement.executeQuery();
            while (connect.resultSet.next()) {
                student = new Student(
                        connect.resultSet.getInt("id_studenta"),
                        connect.resultSet.getString("imie"),
                        connect.resultSet.getString("nazwisko"),
                        connect.resultSet.getInt("grupa_id_grupy")
                );
                list.add(student);
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
                AddStudent addStudent = new AddStudent();
                addStudent.setVisible(true);
                dispose();
            }
        });
        button[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateStudent updateStudent = new UpdateStudent();
                updateStudent.setVisible(true);
                dispose();
            }
        });
        button[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteStudent deleteStudent = new DeleteStudent();
                deleteStudent.setVisible(true);
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
                    String imie = model.getValueAt(selectRow, 1).toString();
                    String nazwisko = model.getValueAt(selectRow, 2).toString();
                    String grupaIdGrupa = model.getValueAt(selectRow, 3).toString();
                    UpdateStudent updateStudent = new UpdateStudent();
                    updateStudent.setVisible(true);
                    updateStudent.textFields[0].setText(idStudent);
                    updateStudent.textFields[1].setText(imie);
                    updateStudent.textFields[2].setText(nazwisko);
                    updateStudent.textFields[3].setText(grupaIdGrupa);
                    dispose();
                }
            }
        });
    }
}
