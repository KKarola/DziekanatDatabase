package student;

public class Student {
    private int id_studenta;
    private String imie;
    private String nazwisko;
    private int grupa_id_grupy;

    public Student(int id_studenta, String imie, String nazwisko, int grupa_id_grupy) {
        this.id_studenta = id_studenta;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.grupa_id_grupy = grupa_id_grupy;
    }

    public int getIdStudenta() {
        return id_studenta;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public int getGrupaIdGrupy() {
        return grupa_id_grupy;
    }
}