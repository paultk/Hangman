package com.example.paul.hangman;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * Created by paul on 20.11.16.
 */

public class CustomEdit extends EditText{
    public CustomEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }
    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        Log.e("Log", "onKeyPreIme");
        return true;
        //return super.onKeyPreIme(keyCode, event);
    }
}
