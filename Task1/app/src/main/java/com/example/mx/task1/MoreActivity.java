package com.example.mx.task1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MoreActivity extends Activity {
    private TextView name;
    private TextView mobilenum;
    private TextView phonenum;
    private TextView email;
    private TextView address;
    private Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        name = (TextView) findViewById(R.id.vname);
        mobilenum = (TextView) findViewById(R.id.vmobilenum);
        phonenum = (TextView) findViewById(R.id.vphonenum);
        email = (TextView) findViewById(R.id.vemail);
        address = (TextView) findViewById(R.id.vaddress);
        confirm = (Button) findViewById(R.id.confirm);
        Intent intent = getIntent();
        name.setText(intent.getCharSequenceExtra("name"));
        mobilenum.setText(intent.getCharSequenceExtra("mobilenum"));
        phonenum.setText(intent.getCharSequenceExtra("phonenum"));
        email.setText(intent.getCharSequenceExtra("email"));
        address.setText(intent.getCharSequenceExtra("address"));
        intent.removeExtra("name");
        intent.removeExtra("mobilenum");
        intent.removeExtra("phonenum");
        intent.removeExtra("email");
        intent.removeExtra("address");
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent1=new Intent(MoreActivity.this,IndexActivity.class);
                startActivity(intent1);
                finish();
            }

        });

    }
}
