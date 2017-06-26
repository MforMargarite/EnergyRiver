package com.whucs.energyriver;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

/*账单结算 暂定webview*/
public class BillActivity extends AppCompatActivity implements View.OnClickListener{
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                BillActivity.this.finish();
                break;
            default:
                break;
        }
    }

}
