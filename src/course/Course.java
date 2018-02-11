package course;

public class Course {
    private int id_przedmiotu;
    private String nazwa_przedmiotu;
    private String skrot;
    private int ects;
    private int grupa_id_grupy;

    public Course(int id_przedmiotu, String nazwa_przedmiotu, String skrot, int ects, int grupa_id_grupy) {
        this.id_przedmiotu = id_przedmiotu;
        this.nazwa_przedmiotu = nazwa_przedmiotu;
        this.skrot = skrot;
        this.ects = ects;
        this.grupa_id_grupy = grupa_id_grupy;
    }

    public int getId() {return id_przedmiotu; }

    public String getNameCourse() {return nazwa_przedmiotu;}

    public String getSkrot() { return skrot; }

    public int getEcts() { return ects; }

    public int getGrupaIdGrupy() { return grupa_id_grupy; }
}