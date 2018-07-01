package com.example.robertoferro.diary.ListDiaryEntries;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.robertoferro.diary.Database.DiaryEntries;
import com.example.robertoferro.diary.DiaryEntry.DiaryEntryActivity;
import com.example.robertoferro.diary.Login.LoginActivity;
import com.example.robertoferro.diary.R;
import com.example.robertoferro.diary.Utils.DiaryMode;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

import static com.example.robertoferro.diary.DiaryEntry.DiaryEntryActivity.INTENT_IDENTIFIER_DAIRY_MODE;

public class ListDiaryEntriesActivity extends AppCompatActivity {


    private ListDairyEntriesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_diary_entries);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.list_diary_tool_bar_title);
        setSupportActionBar(toolbar);

        initializeFabButton();
        initializeRecyclerView();
        initializeViewModel();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list_dairy_entries, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                logUserOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logUserOut() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        navigateToLoginScreen();
                    }
                });
    }

    private void initializeViewModel() {
        ListDiaryEntriesViewModel viewModel = ViewModelProviders.of(this).get(ListDiaryEntriesViewModel.class);
        viewModel.getAllDiaryEntries().observe(this, new Observer<List<DiaryEntries>>() {
            @Override
            public void onChanged(@Nullable final List<DiaryEntries> diaryEntries) {
                adapter.setDiaryEntries(diaryEntries);
            }
        });
    }

    private void initializeFabButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToDiaryEntryScreenInCreateMode();
            }
        });
        fab.setImageResource(R.drawable.ic_add_black_24dp);
    }

    private void initializeRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter = new ListDairyEntriesAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void navigateToDiaryEntryScreenInCreateMode() {
        Intent intent = new Intent(this,DiaryEntryActivity.class);
        intent.putExtra(INTENT_IDENTIFIER_DAIRY_MODE, DiaryMode.CREATE);
        startActivity(intent);
    }

    private void navigateToLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


}
