package com.whucs.energyriver;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.whucs.energyriver.Presenter.MessagePresenter;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.Public.ValidStandard;
import com.whucs.energyriver.View.ChangeMobileView;
import java.util.Timer;
import java.util.TimerTask;


public class ChangeMobileActivity extends AppCompatActivity implements View.OnClickListener,ChangeMobileView{
    EditText mobile, verify;
    TextView submit,getVerify;
    ImageView back;
    Resources res;
    MessagePresenter presenter;
    Timer timer;
    Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_mobile);
        initWidget();
    }

    private void initWidget() {
        mobile = (EditText) findViewById(R.id.mobile);
        mobile.setText(Common.getMobile(this));

        verify = (EditText) findViewById(R.id.verify);
        getVerify = (TextView) findViewById(R.id.get_verify);

        submit = (TextView) findViewById(R.id.submit);
        back = (ImageView) findViewById(R.id.back);

        back.setOnClickListener(this);
        submit.setOnClickListener(this);
        getVerify.setOnClickListener(this);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch(msg.what){
                    case 0:
                        if (Common.times == 60) {
                            if(timer!=null)
                                timer.cancel();
                            enableVerify();
                        } else {
                            disableVerify();
                        }break;
                    case 1:
                        Common.times++;
                        break;
                    case 2:
                        Toast.makeText(ChangeMobileActivity.this,res.getText(R.string.get_verify),Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        };
        res = getResources();
        presenter = new MessagePresenter(this);
    }

    public void enableVerify(){
        getVerify.setText(getResources().getString(R.string.get_verify));
        getVerify.setClickable(true);
        getVerify.setTextColor(getResources().getColor(R.color.white));
        getVerify.setBackgroundColor(res.getColor(R.color.colorPrimary));
        Common.times = 0;
    }



    public void disableVerify(){
        getVerify.setMinWidth(getVerify.getWidth());
        getVerify.setText((59 - Common.times) + " s后重试");
        getVerify.setBackgroundColor(res.getColor(R.color.unclickable_btn));
        getVerify.setTextColor(res.getColor(R.color.unclickable_text));
        getVerify.setClickable(false);
        Common.times++;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Common.times % 60 != 0) {
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
        Common.phone_input = mobile.getText().toString();
    }

    @Override
    public void onDestroy() {
        Common.times = 0;
        if(timer!=null)
            timer.cancel();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:
                String mobile_value = mobile.getText().toString();
                if(ValidStandard.isMobile(mobile_value)) {
                    String verify_code = verify.getText().toString().trim();
                    if(verify_code.equals("")){
                        Toast.makeText(this,res.getText(R.string.empty_verify),Toast.LENGTH_SHORT).show();
                    }else if(!Common.verifySMS(verify_code)){
                        Toast.makeText(this,res.getText(R.string.wrong_verify),Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent = new Intent();
                        intent.putExtra("mobile", mobile_value);
                        setResult(RESULT_OK, intent);
                        ChangeMobileActivity.this.finish();
                    }
                }else
                    Toast.makeText(this,res.getText(R.string.illegal_mobile), Toast.LENGTH_SHORT).show();
                break;
            case R.id.back:
                setResult(RESULT_CANCELED);
                ChangeMobileActivity.this.finish();
                break;
            case R.id.get_verify:
                getVerify();
                break;
            default:
                break;
        }
    }

    //计时并改变按钮text
    private void getVerify(){
        if(ValidStandard.isMobile(getPhone())) {
            TimerTask task = new TimerTask() {
                public void run() {
                    Message msg = handler.obtainMessage();
                    msg.what = 0;
                    handler.sendMessage(msg);
                }
            };
            timer = new Timer();
            timer.schedule(task, 0, 1000);
            presenter.getVerifyCode(this);
        }else
            Toast.makeText(this,res.getText(R.string.illegal_mobile),Toast.LENGTH_SHORT).show();
    }


    @Override
    public String getPhone() {
        return mobile.getText().toString();
    }

    @Override
    public void postSuccess() {
        Toast.makeText(this,res.getText(R.string.get_verify_success),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void postFail() {
        Toast.makeText(this,res.getText(R.string.get_verify_fail),Toast.LENGTH_SHORT).show();
    }
}
