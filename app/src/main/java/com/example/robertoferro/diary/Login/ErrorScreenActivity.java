package com.example.robertoferro.diary.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.robertoferro.diary.R;

public class ErrorScreenActivity extends AppCompatActivity {

    public static final String ERROR_MESSAGE_KEY = "errorMessageKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_screen);

        Intent intent = getIntent();
        String errorMessage = intent.getStringExtra(ERROR_MESSAGE_KEY);
        TextView errorMessageTextView = findViewById(R.id.errorMessage);
        errorMessageTextView.setText(errorMessage);
    }
}
