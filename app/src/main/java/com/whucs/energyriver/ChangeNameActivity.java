package com.whucs.energyriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.whucs.energyriver.Public.Common;


public class ChangeNameActivity extends AppCompatActivity implements View.OnClickListener{
    EditText username_input;
    TextView submit;
    ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_name);
        initWidget();
    }

    private void initWidget() {
        username_input = (EditText) findViewById(R.id.username_input);
        username_input.setText(Common.getUserName(this));
        submit = (TextView) findViewById(R.id.submit);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:
                String username_value = username_input.getText().toString().trim();
                if(username_value.equals(""))
                    Toast.makeText(ChangeNameActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                else {
                    Intent data = new Intent();
                    data.putExtra("username",username_value);
                    setResult(1,data);
                    ChangeNameActivity.this.finish();
                }
                break;
            case R.id.back:
                setResult(0);
                ChangeNameActivity.this.finish();
                break;
        }
    }
}
