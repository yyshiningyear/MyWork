package com.yy.diyidemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2015/5/15.
 */
public class DiaryAdapter extends ArrayAdapter<Diary> {

    private int resourceID;

    public DiaryAdapter(Context context, int resource, List<Diary> objects) {
        super(context, resource, objects);
        resourceID = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Diary diary = getItem(position);
        View view ;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceID, null);
            viewHolder = new ViewHolder();
            viewHolder.diaryTitle = (TextView) view.findViewById(R.id.diary_title);
            viewHolder.diaryTime = (TextView) view.findViewById(R.id.diary_time);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.diaryTitle.setText(diary.getTitle());
        viewHolder.diaryTime.setText(diary.getTime());
        return view;
    }
    class ViewHolder {
        TextView diaryTitle;
        TextView diaryTime;
    }
}
