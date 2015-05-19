package com.yy.diyidemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;


public class ShowDiaryActivity extends ActionBarActivity {

    private final static String TAG = "diary";
    private String data;
    private Diary diary;
    private WebView view;
    private DiaryHandler diaryHandler;
    private int DB_VERSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_diary);

        diaryHandler = new DiaryHandler(ShowDiaryActivity.this, DB_VERSION);

        Intent intent = getIntent();
        data = intent.getStringExtra("data");
        diary = diaryHandler.findDiaryByData(data);     //以data为表示获得日记

        view = (WebView)findViewById(R.id.detail_content);
        view.loadDataWithBaseURL(null, data, "text/html", "utf-8", null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_diary_detail, menu);
        return  true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.btn_delete_diary:
                diaryHandler.deleteDiary(diary);
                finish();
                break;
            case R.id.btn_edit_diary:
//                Intent intent = new Intent(ShowDiaryActivity.this, EditDiaryActivity.class);
//                intent.putExtra("data",data);
//                startActivity(intent);
                Toast.makeText(ShowDiaryActivity.this, "edit ",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;

    }

}
