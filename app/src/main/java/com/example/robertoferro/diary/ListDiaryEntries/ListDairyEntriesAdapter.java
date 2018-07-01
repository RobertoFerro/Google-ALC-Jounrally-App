package com.example.robertoferro.diary.ListDiaryEntries;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.robertoferro.diary.Database.DiaryEntries;
import com.example.robertoferro.diary.DiaryEntry.DiaryEntryActivity;
import com.example.robertoferro.diary.R;
import com.example.robertoferro.diary.Utils.DiaryMode;
import com.example.robertoferro.diary.Utils.ItemClickListener;

import java.util.Date;
import java.util.List;

import static com.example.robertoferro.diary.Database.DiaryEntries.INTENT_IDENTIFIER_DAIRY_ENTRY_OBJECT;
import static com.example.robertoferro.diary.DiaryEntry.DiaryEntryActivity.INTENT_IDENTIFIER_DAIRY_MODE;

public class ListDairyEntriesAdapter extends RecyclerView.Adapter<DiaryEntriesViewHolder> implements ItemClickListener  {

    private final LayoutInflater mInflater;
    private List<DiaryEntries> mDiaryEntries;
    ListDairyEntriesAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public DiaryEntriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.diary_entry_item, parent, false);
        return new DiaryEntriesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DiaryEntriesViewHolder holder, int position) {
        if (mDiaryEntries != null) {
            DiaryEntries current = mDiaryEntries.get(position);

            Date diaryDate = current.getDate();

            String dayOfWeek = (String ) DateFormat.format("EEE", diaryDate);
            String day = (String) DateFormat.format("dd", diaryDate);
            String monthString = (String) DateFormat.format("MMM",  diaryDate);

            holder.mDayTextView.setText(dayOfWeek);
            holder.mDateTextView.setText(day);
            holder.mMonthTextView.setText(monthString);
            holder.mTitleTextView.setText(current.getTitle());
            holder.mBodyTextView.setText(current.getBody());

            holder.setClickListener(this);
        } else {
            holder.mTitleTextView.setText(R.string.No_Diary_Entries);
        }
    }

    void setDiaryEntries(List<DiaryEntries> diaryEntries){
        mDiaryEntries = diaryEntries;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mDiaryEntries != null)
            return mDiaryEntries.size();
        else return 0;
    }

    private void navigateToDiaryEntryScreen(DiaryEntries diaryEntries, Context context){
        Intent intent = new Intent(context, DiaryEntryActivity.class);
        intent.putExtra(INTENT_IDENTIFIER_DAIRY_MODE, DiaryMode.UPDATE);
        intent.putExtra(INTENT_IDENTIFIER_DAIRY_ENTRY_OBJECT,diaryEntries);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View view, int position, boolean isLongClick) {
        Context context = view.getContext();
        DiaryEntries diaryEntry = mDiaryEntries.get(position);
        navigateToDiaryEntryScreen(diaryEntry, context);
    }
}
