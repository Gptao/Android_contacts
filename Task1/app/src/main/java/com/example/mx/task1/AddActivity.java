package com.example.mx.task1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends Activity {
private Button add;
    private EditText name;
    private EditText mobilenum;
    private EditText phonenum;
    private EditText email;
    private EditText address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        add=(Button)findViewById(R.id.addinfo);
        name=(EditText)findViewById(R.id.edtname);
        mobilenum=(EditText)findViewById(R.id.edtmobilenum);
        phonenum=(EditText)findViewById(R.id.edtphonenum);
        email=(EditText)findViewById(R.id.edtemail);
        address=(EditText)findViewById(R.id.edtaddress);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values=new ContentValues();
                //(_id INTEGER DEFAULT '1' NOT NULL PRIMARY KEY AUTOINCREMENT,Name TEXT,MobileNum TEXT,PhoneNum TEXT,Email TEXT,Address TEXT)";
                if(name.getText().toString().equals(""))
                {
                    AlertDialog.Builder builder=new AlertDialog.Builder(AddActivity.this);
                    builder.setTitle("提示");
                    builder.setMessage("请输入联系人名称！");
                    builder.setPositiveButton("是",
                            new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            }
                    );
                    AlertDialog dialog=builder.create();
                    dialog.show();
                }
                else{
                values.put("Name",name.getText().toString());
                values.put("MobileNum",mobilenum.getText().toString());
                values.put("PhoneNum",phonenum.getText().toString());
                values.put("Email",email.getText().toString());
                values.put("Address",address.getText().toString());
                DBHelper helper=new DBHelper(getApplicationContext());
                helper.Insert(values);
                helper.close();
                Intent newintent=new Intent(AddActivity.this,IndexActivity.class);
                startActivity(newintent);
                finish();}
            }
        });
    }
}
