package ch.cpnv.angrywirds.Vocabulary;

import java.util.ArrayList;

public class ListVocabulary {

    private int id;
    private String title;

    // Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Construct
    public ListVocabulary(int id, String title)
    {
        this.id = id;
        this.title = title;
    }
}
