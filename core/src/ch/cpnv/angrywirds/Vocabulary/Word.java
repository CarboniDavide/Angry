package ch.cpnv.angrywirds.Vocabulary;

public class Word {
    public int id;
    public String value1;
    public String value2;

    // Getter

    public int getId() {
        return id;
    }

    public String getValue1() {
        return value1;
    }

    public String getValue2() {
        return value2;
    }

    // Constructor
    public Word(int id, String value1, String value2) {
    this.id = id;
    this.value1 = value1;
    this.value2 = value2;
    }

}


