package create;

import connectDB.Connect;
import java.sql.*;

public class CreateDB {
    public static Connect connect = new Connect();

    public CreateDB() {
        createDziekanat();
        createTableGrupa();
        createTablePrzedmiot();
        createTableStudent();
        createTableWykladowca();
        createTableOcenaKoncowa();
    }

    public static void createDziekanat() {
        connect.connect1();
        try {
            String sqlRecord = "CREATE DATABASE IF NOT EXISTS DZIEKANAT";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
    }

    public static void createTableGrupa() {
        connect.connect();
        try {
            String sqlRecord = "CREATE TABLE IF NOT EXISTS GRUPA " +
                    "(id_grupy INTEGER not NULL, " +
                    " nazwa_grupy VARCHAR(255), " +
                    " PRIMARY KEY ( id_grupy ))";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
    }

    public static void createTableOcenaKoncowa() {
        connect.connect();
        try {
            String sqlRecord = "CREATE TABLE IF NOT EXISTS OCENA_KONCOWA " +
                    "(ocena INTEGER not NULL," +
                    " student_id_studenta INTEGER not NULL," +
                    " przedmiot_id_przedmiotu INTEGER not NULL," +
                    " PRIMARY KEY ( student_id_studenta, przedmiot_id_przedmiotu), " +
                    " FOREIGN KEY (student_id_studenta) " +
                    " REFERENCES student(id_studenta), " +
                    " FOREIGN KEY (przedmiot_id_przedmiotu) " +
                    " REFERENCES przedmiot(id_przedmiotu))";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
    }

    public static void createTablePrzedmiot() {
        connect.connect();
        try {
            String sqlRecord = "CREATE TABLE IF NOT EXISTS PRZEDMIOT " +
                    "(id_przedmiotu INTEGER not NULL, " +
                    " nazwa_przedmiotu VARCHAR(255) not NULL, " +
                    " skrot VARCHAR(255) not NULL, " +
                    " ects INTEGER not NULL, " +
                    " grupa_id_grupy INTEGER not NULL, " +
                    " PRIMARY KEY ( id_przedmiotu), " +
                    " FOREIGN KEY (grupa_id_grupy) " +
                    " REFERENCES grupa(id_grupy))";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
    }

    public static void createTableStudent() {
        connect.connect();
        try {
            String sqlRecord = "CREATE TABLE IF NOT EXISTS STUDENT " +
                    "(id_studenta INTEGER not NULL, " +
                    " imie VARCHAR(255) not NULL, " +
                    " nazwisko VARCHAR(255) not NULL, " +
                    " grupa_id_grupy INTEGER not NULL, " +
                    " PRIMARY KEY ( id_studenta), " +
                    " FOREIGN KEY (grupa_id_grupy) " +
                    " REFERENCES grupa(id_grupy))";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
    }

    public static void createTableWykladowca() {
        connect.connect();
        try {
            String sqlRecord = "CREATE TABLE IF NOT EXISTS WYKLADOWCA " +
                    "(id_wykladowcy INTEGER not NULL, " +
                    " imie VARCHAR(255) not NULL, " +
                    " nazwisko VARCHAR(255) not NULL, " +
                    " tytul VARCHAR(255) not NULL, " +
                    " przedmiot_id_przedmiotu INTEGER not NULL, " +
                    " PRIMARY KEY ( id_wykladowcy), "  +
                    " FOREIGN KEY (przedmiot_id_przedmiotu) " +
                    " REFERENCES przedmiot(id_przedmiotu))";
            connect.preparedStatement = connect.conn.prepareStatement(sqlRecord);
            connect.preparedStatement.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Error: " + se);
        }
        connect.disconnet();
    }
}
