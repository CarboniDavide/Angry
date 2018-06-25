package ch.cpnv.angrywirds.Vocabulary;

import java.util.ArrayList;

public class Vocabulary {

    private int id;
    private String title;
    private int lang1;
    private int lang2;
    private ArrayList<Word> words;

    // Getter

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getLang1() {
        return lang1;
    }

    public int getLang2() {
        return lang2;
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    // Construct
    public Vocabulary(int id, String title, int lang1, int lang2, ArrayList<Word> words)
    {
        this.id = id;
        this.title = title;
        this.lang1 = lang1;
        this.lang2 = lang2;
        this.words = words;
    }
}
