package com.example.mx.task1;

/**
 * Created by MX on 2018/5/12.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class IndexActivity extends Activity {
    private List<String> nameList=new ArrayList<>();
    private List<Info> InfoList=new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_item:
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);break;
            case R.id.info_item:
                Toast.makeText(this,"点击了信息按钮",Toast.LENGTH_SHORT).show();
                break;
        }return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Intent intent = getIntent();
        // password.setText(intent.getCharSequenceExtra("password"));
        initName();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        ImageButton add = (ImageButton) findViewById(R.id.add);
        ImageButton search = (ImageButton) findViewById(R.id.search);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        InfoAdapter adapter=new InfoAdapter(nameList);

        adapter.setOnItemClickLisitenter(new RecycleViewLisitenter.onItemClickLisitenter() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent2=new Intent(IndexActivity.this,MoreActivity.class);
                intent2.putExtra("name",InfoList.get(position).getName());
                intent2.putExtra("mobilenum",InfoList.get(position).getMobilenum());
                intent2.putExtra("phonenum",InfoList.get(position).getPhoneNum());
                intent2.putExtra("email",InfoList.get(position).getEmail());
                intent2.putExtra("address",InfoList.get(position).getAddress());
                startActivity(intent2);
                    for(int i = 0 ; i < InfoList.size() ; i++) {
                    Log.i("测试输出",String.valueOf(InfoList.get(i).getId()));
                        finish();
                }
//Toast.makeText(IndexActivity.this,"您点击了："+String.valueOf(position),Toast.LENGTH_SHORT).show();

            }
        });

        adapter.setOnItemLongClickLisitenter(new RecycleViewLisitenter.onItemLongClickLisitenter() {
            @Override
            public void onItemLongClick(View v, final int position) {
                //Toast.makeText(IndexActivity.this,"您长按了："+String.valueOf(position),Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder=new AlertDialog.Builder(IndexActivity.this);
                builder.setTitle("温馨提示");
                builder.setMessage("确认删除这条记录？");
                builder.setPositiveButton("是",
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DBHelper myhelper=new DBHelper(getApplicationContext());
                                myhelper.delete(InfoList.get(position).getId());
                                myhelper.close();
                                finish();
                                Intent intent = new Intent(IndexActivity.this, IndexActivity.class);
                                startActivity(intent);
                            }
                        }
                );
                builder.setNegativeButton("否",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                AlertDialog dialog=builder.create();
                dialog.show();

            }
        });
        recyclerView.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IndexActivity.this,AddActivity.class);
                startActivity(intent);
                //finish();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.half_dialog_view, null);
                final EditText editText = (EditText) view.findViewById(R.id.dialog_edit);
                AlertDialog dialog = new AlertDialog.Builder(IndexActivity.this)
                        .setIcon(R.mipmap.search)//设置标题的图片
                        .setTitle("查找联系人：")//设置对话框的标题
                        .setView(view)
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String content = editText.getText().toString();
                                int i=0;
                                for(i=0;i<InfoList.size();i++)
                                {
                                    if(InfoList.get(i).getName().equals(content))
                                    {
                                        Intent intent2=new Intent(IndexActivity.this,MoreActivity.class);
                                        intent2.putExtra("name",InfoList.get(i).getName());
                                        intent2.putExtra("mobilenum",InfoList.get(i).getMobilenum());
                                        intent2.putExtra("phonenum",InfoList.get(i).getPhoneNum());
                                        intent2.putExtra("email",InfoList.get(i).getEmail());
                                        intent2.putExtra("address",InfoList.get(i).getAddress());
                                        startActivity(intent2);
                                        break;
                                    }

                                }
                                if(i==InfoList.size())
                                Toast.makeText(IndexActivity.this, "联系人不存在！", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();
            }
        });
    }

    private void initName() {
        DBHelper helper=new DBHelper(getApplicationContext());
        Cursor c=helper.query();
        if(c!=null&&c.moveToFirst())
        {
            do {
                nameList.add(c.getString(1));
                InfoList.add(new Info(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5)));
            }
            while(c.moveToNext());

        }
        helper.close();
    }
}