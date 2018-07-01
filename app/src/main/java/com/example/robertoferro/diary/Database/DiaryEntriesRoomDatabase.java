package com.example.robertoferro.diary.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.robertoferro.diary.Utils.DateConverter;

@Database(entities = {DiaryEntries.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class DiaryEntriesRoomDatabase extends RoomDatabase {

    private static DiaryEntriesRoomDatabase INSTANCE;

    public abstract DiaryEntriesDao diaryEntriesDao();

    public static DiaryEntriesRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DiaryEntriesRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DiaryEntriesRoomDatabase.class, "dairy_entries_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
