package com.example.paul.hangman;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    public final String TAG = "GameActivity";

    private TextView lettersAsUnderScore;
    private TextView lettersShown;
    private ImageView hangMan;
    private LinearLayout letterBoxLayout;

    private ArrayList<String> words;

    private Random random = new Random();

    private String currentGuessWord;
    private String lettersAvailable;
    private String lettersChosen = "";

    private int currentTries;
    private final int TRIESGIVEN= 9;
    private int correctGuesses;

    private TypedArray images;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        lettersAsUnderScore = (TextView) findViewById(R.id.letters_as_underscore);
        lettersShown = (TextView) findViewById(R.id.letters_shown);
        letterBoxLayout = (LinearLayout) findViewById(R.id.letter_blocks_layout);
        hangMan = (ImageView) findViewById(R.id.hangMan);

        images = getResources().obtainTypedArray(R.array.images);

        words = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.car_brands)));




        init();
    }


    public void init() {

        currentTries = 0;
        correctGuesses = 0;

        hangMan.setImageDrawable(getDrawable(R.drawable.img0));

        int randomInt = random.nextInt(words.size());
        currentGuessWord = words.remove(randomInt);
        Log.d(TAG, "currentGuessWord: " + currentGuessWord);
        int amountOfUnderscores = currentGuessWord.length();

        String underscores = "";
        for (int i = 0; i < amountOfUnderscores; i++) {
            underscores += "_ ";
        }

//        65 to 90

        String letterShow = getScrambledGuessLetters(currentGuessWord);



        lettersAsUnderScore.setText(underscores);
//        lettersShown.setText(letterShow);


        for (int i = 0; i < letterShow.length(); i++) {
            final TextView textView = new TextView(GameActivity.this);
            textView.setText(letterShow.substring(i, i +1));


            textView.setTextSize(23);
            textView.setPadding(2,2,2,2);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    letterPushed(textView.getText().toString());
                    letterBoxLayout.removeView(textView);
                }
            });

            letterBoxLayout.addView(textView);
        }


    }

    public String getScrambledGuessLetters(String guessWord){

        String returnString = "";

        for (int i = 0; i < guessWord.length(); i++) {
            if (!(returnString.contains(Character.toString(guessWord.charAt(i))))) {
                returnString += guessWord.charAt(i);
            }
        }

        Log.d(TAG, "guessWord:\t" + guessWord);

        for (int i = 0; i < 15; i++) {
            String temp = "";
            char tempChar = (char) (random.nextInt(25) + 65);
            temp += tempChar;
            if (!returnString.contains(temp)) {
                returnString += temp;
            }
        }
        Log.d(TAG, "guessWordwithextra:\t" + guessWord);


        for (int i = 0; i < 100; i++) {
            int random1 = random.nextInt(returnString.length());
            int random2 = random.nextInt(returnString.length());

            if (random1 < random2) {
                returnString = returnString.substring(0, random1) + returnString.substring(random2) + returnString.substring(random1, random2);
            }
        }
        Log.d(TAG, "guessWord scrambled:\t" + guessWord);

        return returnString;




    }


    public void letterPushed(String letter) {
        if(currentGuessWord.contains(letter)) {
            for (int i = 0; i < currentGuessWord.length(); i++) {
                Log.d(TAG, "aHit");
                if (currentGuessWord.charAt(i) == letter.charAt(0)) {
                    String tempLetters = lettersAsUnderScore.getText().toString();
                    tempLetters = tempLetters.substring(0, (2 *i) ) + letter + tempLetters.substring((2 * i) + 1);
                    lettersAsUnderScore.setText(tempLetters);
                    ++correctGuesses;
                } if (correctGuesses == currentGuessWord.length()) {
                    Toast.makeText(this, "Winner", Toast.LENGTH_LONG).show();
                }
            }
        }else {
            ++currentTries;
            hangMan.setImageDrawable(images.getDrawable(currentTries));
            if ((currentTries == TRIESGIVEN -1)){
                lettersAsUnderScore.setText(currentGuessWord);
                try {
//                    Thread.sleep(3000);
                } catch (Exception e) {Log.e(TAG, e.getMessage()); e.printStackTrace();}
//                init();
            }

        }
    }
}
