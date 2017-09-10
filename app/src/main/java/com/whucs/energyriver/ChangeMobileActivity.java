package com.whucs.energyriver;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.Public.ValidStandard;

public class ChangeMobileActivity extends AppCompatActivity implements View.OnClickListener{
    EditText mobile, verify;
    TextView submit,getVerify;
    ImageView back;
    Resources res;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_mobile);
        initWidget();
    }

    private void initWidget() {
        mobile = (EditText) findViewById(R.id.mobile);
        mobile.setText(Common.getMobile(this));
        /*
        verify = (EditText) findViewById(R.id.verify);
        getVerify = (TextView) findViewById(R.id.get_verify);
        */
        submit = (TextView) findViewById(R.id.submit);
        back = (ImageView) findViewById(R.id.back);

        back.setOnClickListener(this);
        submit.setOnClickListener(this);
      //  getVerify.setOnClickListener(this);

        res = getResources();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:
                String mobile_value = mobile.getText().toString();
                if(ValidStandard.isMobile(mobile_value)) {
                    Intent intent = new Intent();
                    intent.putExtra("mobile", mobile_value);
                    setResult(RESULT_OK, intent);
                    ChangeMobileActivity.this.finish();
                }else
                    Toast.makeText(this,res.getText(R.string.illegal_mobile), Toast.LENGTH_SHORT).show();
                break;
            case R.id.back:
                setResult(RESULT_CANCELED);
                ChangeMobileActivity.this.finish();
                break;
        }
    }


}
