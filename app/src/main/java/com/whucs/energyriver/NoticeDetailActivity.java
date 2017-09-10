package com.whucs.energyriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import com.whucs.energyriver.Presenter.UpdateNoticePresenter;
import com.whucs.energyriver.View.UpdateNoticeView;


public class NoticeDetailActivity extends AppCompatActivity implements View.OnClickListener,UpdateNoticeView{
    ImageView back;
    WebView webView;
    Long noticeID;
    UpdateNoticePresenter presenter;
    boolean execState;

    //点开页面即更新阅读
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_detail);
        Intent intent = getIntent();
        if(intent!=null)
            noticeID = intent.getLongExtra("id",-1);
        initWidget();
    }

    private void initWidget(){
        //初始化控件
        back = (ImageView) findViewById(R.id.back);
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
       // webView.loadUrl(Common.ROOT+Common.NOTICE+noticeID);
        back.setOnClickListener(this);

        execState = false;
        presenter = new UpdateNoticePresenter(this);
        presenter.updateNoticeState(this);

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
