package com.example.robertoferro.diary.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.robertoferro.diary.ListDiaryEntries.ListDiaryEntriesActivity;
import com.example.robertoferro.diary.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;

import java.util.Arrays;
import java.util.List;

import static com.example.robertoferro.diary.Login.ErrorScreenActivity.ERROR_MESSAGE_KEY;


public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeGetStartedButton();
        handleIfUserIsLoggedInAlready();
    }

    private void initializeGetStartedButton() {
        Button mButton = findViewById(R.id.get_started);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startUserLogin();
            }
        });
    }

    private void handleIfUserIsLoggedInAlready() {
        if(UserRepository.isUserLoggedIn()){
            navigateToDiaryEntriesList();
        }
    }

    private void startUserLogin() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());


        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                navigateToDiaryEntriesList();
            } else {
                IdpResponse response = IdpResponse.fromResultIntent(data);
                if(response != null){
                    String errorMessage = response.getError().getLocalizedMessage();
                    if(errorMessage != null){
                        navigateToSpecializeErrorScreen(errorMessage);
                    } else {
                        navigateToGenericErrorScreen();
                    }
                } else {
                    navigateToGenericErrorScreen();
                }

            }
        }
    }

    private void navigateToDiaryEntriesList() {
        Intent intent = new Intent(this,ListDiaryEntriesActivity.class);
        startActivity(intent);
    }

    private void navigateToSpecializeErrorScreen(String errorMessage) {
        Intent intent = new Intent(this,ListDiaryEntriesActivity.class);
        intent.putExtra(ERROR_MESSAGE_KEY,errorMessage);
        startActivity(intent);
    }

    private void navigateToGenericErrorScreen() {
        Intent intent = new Intent(this,ListDiaryEntriesActivity.class);
        intent.putExtra(ERROR_MESSAGE_KEY,"Something went wrong, try again");
        startActivity(intent);
    }

}
