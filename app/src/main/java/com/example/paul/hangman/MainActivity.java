package com.example.paul.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private final int norwegianLang = 0;
    private final int britishLang = 1;

    private ImageView norwegianFlag;
    private ImageView britishFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);


        norwegianFlag = (ImageView) findViewById(R.id.norwegian_flag);
        britishFlag = (ImageView) findViewById(R.id.british_flag);


        norwegianFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toGame(norwegianLang);
            }
        });
        britishFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toGame(britishLang);
            }
        });
    }

    public void toGame(int lang) {
        Intent intent = new Intent("com.example.paul.hangman.GameActivity");
        intent.putExtra("language", lang);
        startActivity(intent);

    }

}
