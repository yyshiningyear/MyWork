package com.yy.diyidemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DiaryActivity extends Activity {

    private final static String TAG = "diary";
    private final static int WRITE_DIARY = 1;   //写日记activity的调用码
    private final static int CLASSIFY = 2;      //分类列表activity的调用码
    private List<Diary> diaryList = new ArrayList<Diary>();     //日记列表
    private Button writeDiary;
    private Button categoryList;
    private TextView classifyTitle;
    private String classifyID = "全部";
    private DiaryAdapter adapter;
    private DiaryHandler diaryHandler;  //数据库操作
    private int DB_VERSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_diary);

        diaryHandler = new DiaryHandler(DiaryActivity.this, DB_VERSION);
        classifyTitle = (TextView)findViewById(R.id.title_classifyID);
        classifyTitle.setText(classifyID);

        initList(classifyID);     //默认打开全部日记
        adapter = new DiaryAdapter(DiaryActivity.this, R.layout.diary_item, diaryList);
        ListView listView = (ListView)findViewById(R.id.diary_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Diary diary = diaryList.get(position);
                Intent intent = new Intent(DiaryActivity.this, ShowDiaryActivity.class);

                intent.putExtra("data",diary.getData());
                startActivity(intent);
            }
        });


        categoryList = (Button)findViewById(R.id.btn_diary_category);
        writeDiary = (Button)findViewById(R.id.write_diary);
        writeDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiaryActivity.this, EditDiaryActivity.class);
                intent.putExtra("classifyID", classifyID);    //传入当前的分类作为新建日记的分类
                startActivityForResult(intent, WRITE_DIARY);
            }
        });
        categoryList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DiaryActivity.this,"分类",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DiaryActivity.this, ClassifyActivity.class);
                startActivityForResult(intent, CLASSIFY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case WRITE_DIARY:
                if (resultCode == RESULT_OK) {
                    String title = data.getStringExtra("title");
                    String time = data.getStringExtra("time");
                    String rData = data.getStringExtra("data");
                    Diary editDiary = new Diary(title, time, rData);
                    diaryList.add(editDiary);
                    adapter.notifyDataSetChanged();
                }
                break;
            case CLASSIFY:
                //根据选择的分类重新显示日记列表
                if (resultCode == RESULT_OK) {
                    classifyID = data.getStringExtra("classifyID");
                    classifyTitle.setText(classifyID);
                    initList(classifyID);
                    adapter.notifyDataSetChanged();

                }
        }
    }


    private void initList(String classifyID) {
        diaryList.clear();
        diaryList.addAll(diaryHandler.findDiaryByClassifyID(classifyID));
    }

}
