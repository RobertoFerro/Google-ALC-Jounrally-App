package com.example.robertoferro.diary.ListDiaryEntries;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.robertoferro.diary.R;
import com.example.robertoferro.diary.Utils.ItemClickListener;

class DiaryEntriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public final TextView mDayTextView;
    public final TextView mBodyTextView;
    public final TextView mDateTextView;
    public final TextView mMonthTextView;
    public final TextView mTitleTextView;

    private ItemClickListener clickListener;

    public DiaryEntriesViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        mDayTextView = itemView.findViewById(R.id.day);
        mDateTextView = itemView.findViewById(R.id.date);
        mBodyTextView = itemView.findViewById(R.id.body);
        mMonthTextView = itemView.findViewById(R.id.month);
        mTitleTextView = itemView.findViewById(R.id.title);
    }

    @Override
    public void onClick(View view) {
        clickListener.onClick(view, getLayoutPosition(), false);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}
