package com.example.robertoferro.diary.DiaryEntry;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.example.robertoferro.diary.Database.DiaryEntries;
import com.example.robertoferro.diary.Login.UserRepository;
import com.example.robertoferro.diary.Utils.DateConverter;
import com.example.robertoferro.diary.Utils.DiaryMode;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DiaryEntryViewModel extends AndroidViewModel implements DiaryEntryViewModelCallback {

    private DiaryMode mCurrentDiaryMode;
    private DiaryEntryViewCallback mView;
    private DiaryEntries mUpdateDiaryEntry;
    private DiaryEntryRepository mDiaryEntryRepository;

    public DiaryEntryViewModel(@NonNull Application application) {
        super(application);
        mDiaryEntryRepository = new DiaryEntryRepository(application,this);
    }

    public void configure(DiaryEntryActivity view, DiaryMode mode, DiaryEntries diaryEntry) {
        this.mView = view;
        this.mCurrentDiaryMode = mode;
        this.mUpdateDiaryEntry = diaryEntry;
    }

    public void saveDairyEntry(String title, String body) {
        String id = UserRepository.getUserEmail();
        DiaryEntries diaryEntry;
        switch (mCurrentDiaryMode){
            case CREATE:
                diaryEntry = new DiaryEntries(id,title,body,new Date());
                mDiaryEntryRepository.insert(diaryEntry);
                break;
            case UPDATE:
                diaryEntry = new DiaryEntries(id,title,body,mUpdateDiaryEntry.getDate());
                diaryEntry.setIdentifier(mUpdateDiaryEntry.getIdentifier());
                mDiaryEntryRepository.update(diaryEntry);
                break;
        }

    }

    @Override
    public void diaryEntrySaved() {
        mView.diaryEntrySaved();
    }

    public void initializeUpdateViews() {
        handleViewInsertOrUpdateState();
    }

    private void handleViewInsertOrUpdateState() {
        switch (mCurrentDiaryMode){
            case UPDATE:
                refreshViewInUpdateState();
                break;
            case CREATE:
                refreshViewInCreateState();
                break;
        }
    }

    private void refreshViewInUpdateState(){
        Date diaryEntryDate = mUpdateDiaryEntry.getDate();
        String dateFormatted = createDateFormat(diaryEntryDate);
        mView.initializeDateView(dateFormatted);
        mView.initializeBodyView(mUpdateDiaryEntry.getBody());
        mView.initializeTitleView(mUpdateDiaryEntry.getTitle());
    }

    private void refreshViewInCreateState(){
        Date date = new Date();
        String dateFormatted = createDateFormat(date);
        mView.initializeDateView(dateFormatted);
    }

    private String createDateFormat(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy");
        return simpleDateFormat.format(date);

    }
}
