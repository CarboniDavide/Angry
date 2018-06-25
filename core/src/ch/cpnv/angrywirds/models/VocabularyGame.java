package ch.cpnv.angrywirds.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import ch.cpnv.angrywirds.Activities.Play;
import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.Providers.VoxData;
import ch.cpnv.angrywirds.Vocabulary.Vocabulary;
import ch.cpnv.angrywirds.Vocabulary.Word;

public class VocabularyGame {
    Vocabulary vocabulary;
    String titleGame = "";
    String wordToFind = "";
    int idWordToFind;

    private BitmapFont themeBmp;
    private BitmapFont wordBmp;

    public static  final  float THEME_X = Play.PANEL_POSITION.x + 20;
    public static  final  float THEME_Y = Play.PANEL_POSITION.y + 130;
    public static  final  float WORD_X = Play.PANEL_POSITION.x + 20;
    public static  final  float WORD_Y = Play.PANEL_POSITION.y + 70;

    public VocabularyGame(ArrayList<Pig> pigs){
        themeBmp = new BitmapFont();
        themeBmp.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        themeBmp.getData().setScale(3,3);
        wordBmp = new BitmapFont();
        wordBmp.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        wordBmp.getData().setScale(3,3);
        prepareVocabulary(pigs);
    }

    public void draw(SpriteBatch batch){
        themeBmp.setColor(Color.BLACK);
        themeBmp.draw(batch, titleGame, THEME_X, THEME_Y);
        wordBmp.setColor(Color.BLACK);
        wordBmp.draw(batch, wordToFind, WORD_X, WORD_Y);
    }

    private void prepareVocabulary(ArrayList<Pig> pigs){

        Boolean langhOk = false;
        int voc_number = AngryWirds.vocId -1;
        int words_lenght = 0;
        int id = 0;
        int lang1 = 0;
        int lang2 = 0;
        String title = "";
        ArrayList<Word> localWords = new ArrayList<Word>();;

        // get the size of words for the select vocabulary
        words_lenght = VoxData.vocabularies.get(voc_number).getWords().size();

        // Set local value for the selected vocabularz
        title = VoxData.vocabularies.get(voc_number).getTitle();
        id = VoxData.vocabularies.get(voc_number).getId();
        lang1 = VoxData.vocabularies.get(voc_number).getLang1();
        lang2 = VoxData.vocabularies.get(voc_number).getLang2();
        if (lang1 == AngryWirds.LangueId)
                langhOk = true;

        int i=0;
        while (i < pigs.size()){
            //  get a random word
            int word_number =(int)((Math.random() * (((words_lenght-1) - 0) + 1) ) + 0);
            //get the random word value
            int idWord = VoxData.vocabularies.get(voc_number).getWords().get(word_number).getId();
            String word1 =  VoxData.vocabularies.get(voc_number).getWords().get(word_number).getValue1();
            String word2 =  VoxData.vocabularies.get(voc_number).getWords().get(word_number).getValue2();
            Word newWord= new Word(idWord,word1,word2);

            // Insert if dont already exists
            if (!wordExists(localWords,word2)) {
                localWords.add(newWord);
                pigs.get(i).setWord(word2);
                pigs.get(i).setIdWord(idWord);
                i++;
            }
        }

        vocabulary = new Vocabulary(id,title,lang1,lang2,localWords);

        // set the words to find
        titleGame = vocabulary.getTitle();
        wordToFind = vocabulary.getWords().get(0).getValue1();
        idWordToFind = vocabulary.getWords().get(0).getId();
    }

    private Boolean wordExists(ArrayList<Word> words, String word)
    {
        for(Word w: words)
            if (w.getValue2().equals(word)) return true;
        return false;
    }
}
