package group;

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

public class GroupWindow extends Windows {
    static Connect connect = new Connect();
    JTable table;

    public GroupWindow() {
        super();
        setTitle("Grupy studenckie");
    }

    public void addTable() {
        table = new JTable();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return false;
            }
        };
        Object[] columnsName = new Object[2];
        columnsName[0] = "Id_grupy";
        columnsName[1] = "Nazwa_grupy";
        model.setColumnIdentifiers(columnsName);

        Object[] rowData = new Object[2];
        for (int i = 0; i < getGroup().size(); i++) {
            rowData[0] = getGroup().get(i).getId();
            rowData[1] = getGroup().get(i).getNameGroup();
            model.addRow(rowData);
        }
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().add(table);
        getContentPane().add(scrollPane);
    }

    static ArrayList<Group> getGroup() {
        ArrayList<Group> list = new ArrayList<>();
        connect.connect();
        Group group;
        try {
            String sqlRecord = "SELECT * FROM GRUPA";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.resultSet = connect.preparedStatement.executeQuery();
            while (connect.resultSet.next()) {
                group = new Group(
                        connect.resultSet.getInt("id_grupy"),
                        connect.resultSet.getString("nazwa_grupy")
                );
                list.add(group);
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
                AddGroup addGroup = new AddGroup();
                addGroup.setVisible(true);
                dispose();
            }
        });
        button[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateGroup updateGroup = new UpdateGroup();
                updateGroup.setVisible(true);
                dispose();
            }
        });
        button[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteGroup deleteGroup = new DeleteGroup();
                deleteGroup.setVisible(true);
                dispose();
            }
        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectRow = table.rowAtPoint(e.getPoint());
                    TableModel model = table.getModel();
                    String idGroup = model.getValueAt(selectRow, 0).toString();
                    String nameGroup = model.getValueAt(selectRow, 1).toString();
                    UpdateGroup updateGroup = new UpdateGroup();
                    updateGroup.setVisible(true);
                    updateGroup.textFields[0].setText(idGroup);
                    updateGroup.textFields[1].setText(nameGroup);
                    dispose();
                }
            }
        });
    }

}
