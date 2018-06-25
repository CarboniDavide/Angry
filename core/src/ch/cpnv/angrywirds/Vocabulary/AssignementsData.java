package ch.cpnv.angrywirds.Vocabulary;

public class AssignementsData {
    private int id;
    private int vocId;
    private String title;
    private String result;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVocId() {
        return vocId;
    }

    public void setVocId(int vocId) {
        this.vocId = vocId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public AssignementsData(int id, int vocId, String title, String result){
        this.id = id;
        this.vocId = vocId;
        this.title = title;
        this.result = result;

    }
}
