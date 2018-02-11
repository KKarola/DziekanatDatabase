package group;

public class Group {
    private int id_grupy;
    private String nazwa_grupy;

    public Group(int id_grupy, String nazwa_grupy) {
        this.id_grupy = id_grupy;
        this.nazwa_grupy = nazwa_grupy;
    }

    public int getId() {return id_grupy; }

    public String getNameGroup() {return nazwa_grupy;}
}
