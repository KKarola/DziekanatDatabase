package lecturer;

public class Lecturer {
    private int id_wykladowcy;
    private String imie;
    private String nazwisko;
    private String tytul;
    private int przedmiot_id_przedmiotu;

    public Lecturer(int id_wykladowcy, String imie, String nazwisko, String tytul, int przedmiot_id_przedmiotu) {
        this.id_wykladowcy = id_wykladowcy;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.tytul = tytul;
        this.przedmiot_id_przedmiotu = przedmiot_id_przedmiotu;
    }

    public int getIdWykladowcy() { return id_wykladowcy; }

    public String getImie() { return imie; }

    public String getNazwisko() { return nazwisko; }

    public String getTytul() { return tytul; }

    public int getPrzedmiot_id_przedmioturzedmiotu() { return przedmiot_id_przedmiotu; }
}