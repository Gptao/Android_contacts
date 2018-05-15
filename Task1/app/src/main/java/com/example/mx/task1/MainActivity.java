package com.example.mx.task1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText edtuser;
    private EditText edtpwd;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtuser=(EditText)findViewById(R.id.username);
        edtpwd=(EditText)findViewById(R.id.password);

        Button login = (Button) findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=edtuser.getText().toString();
                password=edtpwd.getText().toString();
                final ProgressDialog mydialog=new ProgressDialog(MainActivity.this);
                mydialog.setTitle("登录中");
                mydialog.setMessage("正在验证，请稍候......");
                mydialog.setProgressStyle(
                        ProgressDialog.STYLE_SPINNER);
                mydialog.show();

                new Thread() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        super.run();
                        try {
                            Thread.sleep(1000);
                            if(username.equals("1510742")&&password.equals("1"))
                            {
                                mydialog.dismiss();
                                Intent intent=new Intent(MainActivity.this,IndexActivity.class);
                                intent.putExtra("username",username);
                                intent.putExtra("password",password);
                                startActivity(intent);
                            }
                            else
                            {
                                mydialog.dismiss();
                                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                                dialog.setTitle("User not exists!");
                                dialog.setMessage("Please try again");
                                dialog.setCancelable(false);
                                dialog.setPositiveButton("I Know", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                dialog.show();
                                //不能使用TOAST
                               // Toast.makeText(MainActivity.this,"用户名密码错误！",Toast.LENGTH_SHORT).show();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }}
