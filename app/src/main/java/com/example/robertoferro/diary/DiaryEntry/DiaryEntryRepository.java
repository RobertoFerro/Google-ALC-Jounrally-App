package com.example.robertoferro.diary.DiaryEntry;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.robertoferro.diary.Database.DiaryEntries;
import com.example.robertoferro.diary.Database.DiaryEntriesDao;
import com.example.robertoferro.diary.Database.DiaryEntriesRoomDatabase;
import com.example.robertoferro.diary.DiaryEntry.DiaryEntryViewModel;
import com.example.robertoferro.diary.DiaryEntry.DiaryEntryViewModelCallback;

import java.util.List;

public class DiaryEntryRepository {

    private DiaryEntriesDao mDiaryEntriesDao;
    private DiaryEntryViewModelCallback mViewModelCallback;

    public DiaryEntryRepository(Application application, DiaryEntryViewModelCallback callback) {
        DiaryEntriesRoomDatabase db = DiaryEntriesRoomDatabase.getDatabase(application);
        mViewModelCallback = callback;
        mDiaryEntriesDao = db.diaryEntriesDao();
    }


    public void insert(DiaryEntries diaryEntry) {
        new insertAsyncTask(mDiaryEntriesDao,mViewModelCallback).execute(diaryEntry);
    }

    public void update(DiaryEntries diaryEntry) {
        new updateAsyncTask(mDiaryEntriesDao,mViewModelCallback).execute(diaryEntry);
    }


    private static class updateAsyncTask extends AsyncTask<DiaryEntries, Void, Void> {
        private DiaryEntriesDao mAsyncTaskDao;
        private DiaryEntryViewModelCallback mOperationCallback;

        updateAsyncTask(DiaryEntriesDao dao, DiaryEntryViewModelCallback operationCallback) {
            mAsyncTaskDao = dao;
            mOperationCallback = operationCallback;
        }

        @Override
        protected Void doInBackground(final DiaryEntries... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mOperationCallback.diaryEntrySaved();
        }

    }

    private static class insertAsyncTask extends AsyncTask<DiaryEntries, Void, Void> {

        private DiaryEntriesDao mAsyncTaskDao;
        private DiaryEntryViewModelCallback mOperationCallback;

        insertAsyncTask(DiaryEntriesDao dao, DiaryEntryViewModelCallback operationCallback) {
            mAsyncTaskDao = dao;
            mOperationCallback = operationCallback;
        }

        @Override
        protected Void doInBackground(final DiaryEntries... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mOperationCallback.diaryEntrySaved();
        }
    }
}
