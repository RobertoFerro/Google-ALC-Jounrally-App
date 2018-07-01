package com.example.robertoferro.diary.Database;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DiaryEntriesDao {

    @Insert
    void insert(DiaryEntries entry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(DiaryEntries entry);

    @Query("SELECT * from diary_entry_table WHERE id= :id ORDER BY date ASC")
    LiveData<List<DiaryEntries>> getAllDiaryEntries(String id);

}
