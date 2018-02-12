package result;

public class Result {
    private int student_id_studenta;
    private int przedmiot_id_przedmiotu;
    private int ocena;

    public Result(int student_id_studenta, int przedmiot_id_przedmiotu, int ocena) {
        this.student_id_studenta = student_id_studenta;
        this.przedmiot_id_przedmiotu = przedmiot_id_przedmiotu;
        this.ocena = ocena;
    }

    public int getStudentIdStudenta() { return student_id_studenta; }

    public int getPrzedmiotIdPrzedmiotu() { return przedmiot_id_przedmiotu; }

    public int getOcena() { return ocena;}
}

