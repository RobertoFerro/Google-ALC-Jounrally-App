package com.example.robertoferro.diary.ListDiaryEntries;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.example.robertoferro.diary.Database.DiaryEntries;
import com.example.robertoferro.diary.Database.DiaryEntriesDao;
import com.example.robertoferro.diary.Database.DiaryEntriesRoomDatabase;
import com.example.robertoferro.diary.Login.UserRepository;

import java.util.List;

public class ListDiaryEntriesRepository {

    private LiveData<List<DiaryEntries>> mAllDiaryEntries;

    public ListDiaryEntriesRepository(Application application) {
        DiaryEntriesRoomDatabase db = DiaryEntriesRoomDatabase.getDatabase(application);
        DiaryEntriesDao  diaryEntriesDao = db.diaryEntriesDao();
        String id = UserRepository.getUserEmail();
        mAllDiaryEntries = diaryEntriesDao.getAllDiaryEntries(id);
    }

    public LiveData<List<DiaryEntries>> getAllDiaryEntries() {
        return mAllDiaryEntries;
    }
}
