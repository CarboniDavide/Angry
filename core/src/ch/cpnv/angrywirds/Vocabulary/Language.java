package ch.cpnv.angrywirds.Vocabulary;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;

public class Language {
    private int id;
    private String langue;

    public static final String LANGUE_FR = "Français";
    public static final String LANGUE_DE = "Allemand";
    public static final String LANGUE_EN = "Anglais";
    public static final String LANGUE_ES = "Espagnol";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public Language(int id, String langue){
        this.id = id;
        this.langue = langue;

    }
}
