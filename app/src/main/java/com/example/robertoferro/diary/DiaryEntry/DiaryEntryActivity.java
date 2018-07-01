package com.example.robertoferro.diary.DiaryEntry;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.robertoferro.diary.Database.DiaryEntries;
import com.example.robertoferro.diary.R;
import com.example.robertoferro.diary.Utils.DiaryMode;

import static com.example.robertoferro.diary.Database.DiaryEntries.INTENT_IDENTIFIER_DAIRY_ENTRY_OBJECT;

public class DiaryEntryActivity extends AppCompatActivity implements DiaryEntryViewCallback {

    public static final String INTENT_IDENTIFIER_DAIRY_MODE = "DairyMode";

    private TextView mDiaryDateTextView;
    private EditText mDiaryBodyEditText;
    private EditText mDiaryTitleEditText;
    private CoordinatorLayout mCoordinatorLayout;

    private DiaryEntryViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_entry);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.your_diary_post);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeViews();
        initializeViewModel();
        initializeFabButton();

    }

    private void initializeViewModel() {
        mViewModel = ViewModelProviders.of(this).get(DiaryEntryViewModel.class);
        Intent intent = getIntent();
        DiaryMode mode = (DiaryMode) intent.getSerializableExtra(INTENT_IDENTIFIER_DAIRY_MODE);
        DiaryEntries diaryEntry = (DiaryEntries) intent.getSerializableExtra(INTENT_IDENTIFIER_DAIRY_ENTRY_OBJECT);
        mViewModel.configure(this,mode,diaryEntry);
        mViewModel.initializeUpdateViews();
    }

    private void initializeViews() {
        mCoordinatorLayout = findViewById(R.id.coordinatorLayout);
        mDiaryDateTextView = findViewById(R.id.diary_entry_post_date);
        mDiaryTitleEditText = findViewById(R.id.diary_entry_post_title);
        mDiaryBodyEditText = findViewById(R.id.diary_entry_post_body);
    }

    private void initializeFabButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSaveDairyPostButtonPressed();
            }
        });
        fab.setImageResource(R.drawable.ic_save_black_24dp);

    }

    private void handleSaveDairyPostButtonPressed() {
        String title = mDiaryTitleEditText.getText().toString();
        String body = mDiaryBodyEditText.getText().toString();
        mViewModel.saveDairyEntry(title,body);
    }

    @Override
    public void diaryEntrySaved() {
        Snackbar.make(mCoordinatorLayout, "Dairy Entry Successfully Saved", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void initializeDateView(String date) {
        mDiaryDateTextView.setText(date);
    }

    @Override
    public void initializeBodyView(String body) {
        mDiaryBodyEditText.setText(body);
    }

    @Override
    public void initializeTitleView(String title) {
        mDiaryTitleEditText.setText(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        else
        {
            return super.onOptionsItemSelected(item);
        }
    }
}
