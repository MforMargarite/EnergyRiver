package com.whucs.energyriver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.whucs.energyriver.Public.Params;


public class InquiryFragment extends Fragment implements View.OnClickListener{
    private WebView webView;
    private ImageView rank;
    private Activity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inquiry,null);
        activity = getActivity();
        initWidget(view);
        return view;
    }

    private void initWidget(View view){
        webView = (WebView) view.findViewById(R.id.result);
        webView.getSettings().setJavaScriptEnabled(true);//允许js交互
        webView.loadUrl(Params.Inquiry);
        rank = (ImageView) view.findViewById(R.id.rank);
        rank.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rank:
                Intent intent = new Intent(activity,RankActivity.class);
                startActivity(intent);
                break;
        }
    }
}
