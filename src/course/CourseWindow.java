package course;

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

public class CourseWindow extends Windows {
    static Connect connect = new Connect();
    JTable table;

    public CourseWindow() {
        super();
        setTitle("Przedmioty");
    }

    public void addTable() {
        table = new JTable();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        Object[] columnsName = new Object[5];
        columnsName[0] = "Id_przedmiotu";
        columnsName[1] = "Nazwa_przedmiotu";
        columnsName[2] = "Skrót";
        columnsName[3] = "ECTS";
        columnsName[4] = "Grupa_Id_grupy";
        model.setColumnIdentifiers(columnsName);

        Object[] rowData = new Object[5];
        for (int i = 0; i < getCourse().size(); i++) {
            rowData[0] = getCourse().get(i).getId();
            rowData[1] = getCourse().get(i).getNameCourse();
            rowData[2] = getCourse().get(i).getSkrot();
            rowData[3] = getCourse().get(i).getEcts();
            rowData[4] = getCourse().get(i).getGrupaIdGrupy();
            model.addRow(rowData);
        }
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().add(table);
        getContentPane().add(scrollPane);
    }

    static ArrayList<Course> getCourse() {
        ArrayList<Course> list = new ArrayList<>();
        connect.connect();
        Course course;
        try {
            String sqlRecord = "SELECT * FROM PRZEDMIOT";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.resultSet = connect.preparedStatement.executeQuery();
            while (connect.resultSet.next()) {
                course = new Course(
                        connect.resultSet.getInt("id_przedmiotu"),
                        connect.resultSet.getString("nazwa_przedmiotu"),
                        connect.resultSet.getString("skrot"),
                        connect.resultSet.getInt("ects"),
                        connect.resultSet.getInt("grupa_id_grupy")
                );
                list.add(course);
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
                AddCourse addCourse = new AddCourse();
                addCourse.setVisible(true);
                dispose();
            }
        });
        button[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateCourse updateCourse = new UpdateCourse();
                updateCourse.setVisible(true);
                dispose();
            }
        });
        button[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteCourse deleteCourse = new DeleteCourse();
                deleteCourse.setVisible(true);
                dispose();
            }
        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectRow = table.rowAtPoint(e.getPoint());
                    TableModel model = table.getModel();
                    String idCourse = model.getValueAt(selectRow, 0).toString();
                    String nameCourse = model.getValueAt(selectRow, 1).toString();
                    String sktotCourse = model.getValueAt(selectRow, 2).toString();
                    String ects = model.getValueAt(selectRow, 3).toString();
                    String grupaIdGrupy = model.getValueAt(selectRow, 4).toString();
                    UpdateCourse updateCourse = new UpdateCourse();
                    updateCourse.setVisible(true);
                    updateCourse.textFields[0].setText(idCourse);
                    updateCourse.textFields[1].setText(nameCourse);
                    updateCourse.textFields[2].setText(sktotCourse);
                    updateCourse.textFields[3].setText(ects);
                    updateCourse.textFields[4].setText(grupaIdGrupy);
                    dispose();
                }
            }
        });
    }

}
