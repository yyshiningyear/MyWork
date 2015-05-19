package com.yy.diyidemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.commonsware.cwac.richedit.RichEditText;
import com.commonsware.cwac.richtextutils.SpannedXhtmlGenerator;

import java.text.SimpleDateFormat;


public class EditDiaryActivity extends Activity {

    private static final String TAG = "diary";
    private Button editSave;
    private Button editBack;
    private EditText editTitle;
    private RichEditText editData;
    private String classifyID = "全部";
    private Diary diary;
    private DiaryHandler diaryHandler;
    private int DB_VERSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit_diary);
        Intent intent = getIntent();
//        data = intent.getStringExtra("data");
        classifyID = intent.getStringExtra("classifyID");

        diaryHandler = new DiaryHandler(EditDiaryActivity.this, DB_VERSION);
//        diary = diaryHandler.findDiaryByData(data);
//        classifyID = diary.getClassify();

        editTitle = (EditText)findViewById(R.id.edit_diary_title);
        editData = (RichEditText)findViewById(R.id.edit_diary_data);
        editData.enableActionModes(true);       //!!!does not work on android5.1 https://github.com/commonsguy/cwac-richedit

        editSave = (Button)findViewById(R.id.btn_edit_save);
        editBack = (Button)findViewById(R.id.btn_edit_back);

//        initView();
        editBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classifyID = "草稿箱";

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String time = dateFormat.format(new java.util.Date());
                SpannedXhtmlGenerator xhtmlGenerator = new SpannedXhtmlGenerator();
                String dataXhtml = xhtmlGenerator.toXhtml(editData.getText());  //将输入转为html格式
                dataXhtml = "</html></body>"+dataXhtml+"</body></html>";

                //保存到数据库
                Diary diary = new Diary(editTitle.getText().toString(), time, dataXhtml, classifyID);
                diaryHandler.addDiary(diary);
                finish();
            }
        });
        //TODO 区分编辑和新建，编辑应该用update
        editSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String time = dateFormat.format(new java.util.Date());
                SpannedXhtmlGenerator xhtmlGenerator = new SpannedXhtmlGenerator();
                String dataXhtml = xhtmlGenerator.toXhtml(editData.getText());  //将输入转为html格式
                dataXhtml = "</html></body>"+dataXhtml+"</body></html>";

                //保存到数据库
                Diary diary = new Diary(editTitle.getText().toString(), time, dataXhtml, classifyID);
                diaryHandler.addDiary(diary);


                Intent intent = new Intent();
                intent.putExtra("title", editTitle.getText().toString());
                intent.putExtra("time", time);
                intent.putExtra("data", dataXhtml);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void initView() {

        editTitle.setText(diary.getTitle());
        editData.setText(diary.getData());
    }
}
