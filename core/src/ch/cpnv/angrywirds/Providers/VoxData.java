package ch.cpnv.angrywirds.Providers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.Vocabulary.AssignementsData;
import ch.cpnv.angrywirds.Vocabulary.Vocabulary;
import ch.cpnv.angrywirds.Vocabulary.Language;
import ch.cpnv.angrywirds.Vocabulary.Word;

public abstract class VoxData {
    public static ArrayList<Language> languages = new ArrayList<Language>();
    public static ArrayList<Vocabulary> vocabularies = new ArrayList<Vocabulary>();
    public static ArrayList<AssignementsData> assignements = new ArrayList<AssignementsData>();

    private  static final String API = "http://voxerver.mycpnv.ch/api/v1/";

    public enum Status { unknown, ready, cancelled, nocnx, error }

    //Status for Get request
    public static Status status = Status.unknown;

    // Status for Post request
    public static Status statusPost = Status.unknown;

    public static void getVoacobulary(){
        // Clear the old values
        vocabularies.clear();
        //Request to API
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.GET);
        request.setUrl("http://voxerver.mycpnv.ch/api/v1/fullvocs");
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Accept", "application/json");

        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {

            public void handleHttpResponse(Net.HttpResponse httpResponse) {

                int statusCode = httpResponse.getStatus().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    Gdx.app.log("asd", "Request Failed");
                    return;
                }

                try {
                    JsonValue values = new JsonReader().parse(httpResponse.getResultAsString());
                    for (JsonValue value: values.iterator()) {
                        ArrayList<Word> w = new ArrayList<Word>();
                        for(JsonValue word: value.get("Words").iterator())
                            w.add(new Word(word.getInt("mId"),word.getString("mValue1"),word.getString("mValue2")));

                        vocabularies.add(new Vocabulary(value.getInt("mId"), value.getString("mTitle"), value.getInt("mLang1"),value.getInt("mLang2"), w));
                    }
                }
                catch(Exception exception) {
                    exception.printStackTrace();
                    status = Status.error;
                }
            }

            public void failed(Throwable t) {
                status = Status.nocnx;
                Gdx.app.log("asd","Request Failed Completely");
            }

            @Override
            public void cancelled() {
                Gdx.app.log("asd","Request Cancelled");
                status = Status.cancelled;
            }

        });
    }

    public static void getLanguages() {
        //Clear the old values
        languages.clear();
        //Request to API
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.GET);
        request.setUrl("http://voxerver.mycpnv.ch/api/v1/languages");
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Accept", "application/json");

        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {

            public void handleHttpResponse(Net.HttpResponse httpResponse) {


                int statusCode = httpResponse.getStatus().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    Gdx.app.log("asd", "Request Failed");
                    return;
                }

                //String responseJson = httpResponse.getResultAsString();
                try {
                    JsonValue values = new JsonReader().parse(httpResponse.getResultAsString());
                    for (JsonValue value: values.iterator()){
                        languages.add(new Language(value.getInt("lId"),value.getString("lName")));
                    }
                } catch (Exception exception) {

                    exception.printStackTrace();
                    status = Status.error;
                }
            }

            public void failed(Throwable t) {
                Gdx.app.log("asd", "Request Failed Completely");
                status = Status.nocnx;
            }

            @Override
            public void cancelled() {
                Gdx.app.log("asd", "Request Cancelled");
                status = Status.cancelled;
            }

        });
    }

    public static void getAssignemts() {
        //Clear the old values
        assignements.clear();
        //Request to API
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.GET);
        request.setUrl("http://voxerver.mycpnv.ch/api/v1/assignments/"+ AngryWirds.token);
        request.setHeader("Content-Type", "application/json");
        request.setHeader("Accept", "application/json");

        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {

            public void handleHttpResponse(Net.HttpResponse httpResponse) {


                int statusCode = httpResponse.getStatus().getStatusCode();
                if (statusCode != HttpStatus.SC_OK) {
                    Gdx.app.log("asd", "Request Failed");
                    return;
                }

                //String responseJson = httpResponse.getResultAsString();
                try {
                    JsonValue values = new JsonReader().parse(httpResponse.getResultAsString());
                    for (JsonValue value: values.iterator()){
                        assignements.add(new AssignementsData(value.getInt("assignment_id"),value.getInt("vocabulary_id"), value.getString("title"), value.getString("result")));
                        status = Status.ready;
                    }
                } catch (Exception exception) {

                    exception.printStackTrace();
                    status = Status.error;
                }
            }

            public void failed(Throwable t) {
                Gdx.app.log("asd", "Request Failed Completely");
                status = Status.nocnx;
            }

            @Override
            public void cancelled() {
                Gdx.app.log("asd", "Request Cancelled");
                status = Status.cancelled;
            }

        });
    }

    private static Boolean VocExists(String voc){
        for(Vocabulary v: vocabularies)
            if (v.getTitle().equals(voc)) return true;

        return false;
    }

    public static void load(){
        getVoacobulary();
        getLanguages();
        getAssignemts();
    }

    public static int getLangueId(String langue){
        for (Language l:languages)
            if (l.getLangue().equals(langue))
                return l.getId();
        return 1; // default lang
    }

    public static Boolean checkIF_vocExsist_for_langue(int langueId) {
       for(Vocabulary v: vocabularies)
           if (v.getLang1() == langueId)
               return true;
       return false;
    }

    // post data to the server
    static public void submitResults (int assignement, String token, int score) {
        Gdx.app.log("AJAXPOST", "Appel ajax demandé");
        HttpRequestBuilder requestSubmitResults = new HttpRequestBuilder();
        PostAssignmentsDatas datas = new PostAssignmentsDatas(assignement, token, score);

        //Header reqquest for laravel
        requestSubmitResults.header("Content-Type", "application/json");
        //requestSubmitResults.header("X-Requested-With", "XMLHttpRequest");

        Net.HttpRequest httpRequestSubmitResults = requestSubmitResults.newRequest()
                .method(Net.HttpMethods.POST)
                .jsonContent(datas)
                .url(API+"result")
                .build();

        Gdx.app.log("AJAXPOST", httpRequestSubmitResults.getContent());
        Gdx.net.sendHttpRequest(httpRequestSubmitResults, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                Gdx.app.log("AJAXPOST", "Soumission des résultats");
                Gdx.app.log("AJAXPOST", httpResponse.toString());
                statusPost = Status.ready;
            }

            @Override
            public void failed(Throwable t) {
                statusPost = Status.nocnx;
                Gdx.app.log("AJAXPOST", "No connection", t);
            }

            @Override
            public void cancelled() {
                statusPost = Status.cancelled;
                Gdx.app.log("AJAXPOST", "cancelled");
            }
        });
    }
}
