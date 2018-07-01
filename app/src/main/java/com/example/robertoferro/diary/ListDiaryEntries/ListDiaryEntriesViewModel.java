package com.example.robertoferro.diary.ListDiaryEntries;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.robertoferro.diary.Database.DiaryEntries;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class ListDiaryEntriesViewModel extends AndroidViewModel {

    private LiveData<List<DiaryEntries>> mAllDiaryEntries;

    public ListDiaryEntriesViewModel(@NonNull Application application) {
        super(application);
        ListDiaryEntriesRepository repository = new ListDiaryEntriesRepository(application);
        mAllDiaryEntries = repository.getAllDiaryEntries();
    }

    public LiveData<List<DiaryEntries>> getAllDiaryEntries() {
        return mAllDiaryEntries;
    }

}
