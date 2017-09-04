package com.whucs.energyriver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import com.whucs.energyriver.Public.Common;


public class InquiryFragment extends Fragment implements View.OnClickListener{
    private WebView webView;
    private ImageView rank;
    private Activity activity;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.inquiry,null);
        activity = getActivity();
        if(getUserVisibleHint()) {
            initWidget(view);
        }return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(view == null) {
            activity = getActivity();
            view = LayoutInflater.from(activity).inflate(R.layout.inquiry, null);
        }
        initWidget(view);
    }

    private void initWidget(View view){
        webView = (WebView) view.findViewById(R.id.result);
        webView.getSettings().setJavaScriptEnabled(true);//允许js交互
        webView.loadUrl(Common.ROOT + Common.Inquiry);
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
