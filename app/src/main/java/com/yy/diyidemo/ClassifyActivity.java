package com.yy.diyidemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/*显示分类列表*/
public class ClassifyActivity extends Activity {

    private ListView listView;
    private List<String> classifyList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_classify);

        getClassifyList();
        listView = (ListView)findViewById(R.id.classify_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ClassifyActivity.this,
                android.R.layout.simple_list_item_1,classifyList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String classifyID = classifyList.get(position);
                Toast.makeText(ClassifyActivity.this,"select " + classifyID, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("classifyID",classifyID);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void getClassifyList() {
        //TODO 从数据库获取分类列表
        classifyList.add("全部");
        classifyList.add("草稿箱");
        classifyList.add("分类1");
    }
}
