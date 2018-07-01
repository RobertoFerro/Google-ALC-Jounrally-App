package com.example.robertoferro.diary.DiaryEntry;

public interface DiaryEntryViewCallback {

    public void diaryEntrySaved();
    public void initializeDateView(String date);
    public void initializeBodyView(String body);
    public void initializeTitleView(String title);

}
