package com.whucs.energyriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.whucs.energyriver.Presenter.UpdateNoticePresenter;
import com.whucs.energyriver.View.UpdateNoticeView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class NoticeDetailActivity extends AppCompatActivity implements View.OnClickListener,UpdateNoticeView{
    ImageView back;
    TextView time,title,content;
    Long noticeID;
    String time_value,content_value,title_value;
    UpdateNoticePresenter presenter;
    boolean execState;

    //点开页面即更新阅读
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_detail);
        Intent intent = getIntent();
        if(intent!=null) {
            noticeID = intent.getLongExtra("id", -1);
            time_value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.parseLong(intent.getStringExtra("time"))));
            title_value = intent.getStringExtra("title");
            content_value = intent.getStringExtra("content");
        }
        initWidget();
    }

    private void initWidget(){
        //初始化控件
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        time = (TextView) findViewById(R.id.time);
        title = (TextView) findViewById(R.id.title);
        content = (TextView) findViewById(R.id.content);

        execState = false;
        presenter = new UpdateNoticePresenter(this);
        presenter.updateNoticeState(this);
        time.setText(time_value);
        title.setText(title_value);
        content.setText(content_value);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.back:
                if(execState)
                    setResult(RESULT_OK);
                else
                    setResult(RESULT_CANCELED);
                NoticeDetailActivity.this.finish();
                break;
        }
    }

    @Override
    public Long getNoticeID() {
        return noticeID;
    }

    @Override
    public void updateSuccess() {
        execState = true;
    }

}
