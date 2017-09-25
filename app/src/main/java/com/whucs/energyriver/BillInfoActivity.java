package com.whucs.energyriver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.whucs.energyriver.Public.Common;

/*账单结算详情 webview*/
public class BillInfoActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView back;
    WebView bill;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill);
        initWidget();
    }

    private void initWidget(){
        back = (ImageView) findViewById(R.id.back);
        bill = (WebView) findViewById(R.id.bill);
        back.setOnClickListener(this);
        Intent intent = getIntent();
        if(intent!=null){
            String url = Common.ROOT+Common.BILL;
            String param = "?userID="+Common.getID(this)+"&pwd="+Common.getPwd(this)+"&billID="+intent.getIntExtra("id",-1);
            url += param;
            bill.getSettings().setJavaScriptEnabled(true);
            bill.loadUrl(url);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                BillInfoActivity.this.finish();
                break;
            default:
                break;
        }
    }

}
