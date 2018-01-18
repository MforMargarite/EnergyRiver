package com.whucs.energyriver;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.java4less.rchart.Chart;
import com.java4less.rchart.ChartListener;
import com.java4less.rchart.FillStyle;
import com.java4less.rchart.Legend;
import com.java4less.rchart.LineStyle;
import com.java4less.rchart.PieDataSerie;
import com.java4less.rchart.PiePlotter;
import com.java4less.rchart.Title;
import com.java4less.rchart.android.ChartPanel;
import com.java4less.rchart.gc.ChartColor;
import com.java4less.rchart.gc.ChartFont;
import com.java4less.rchart.gc.ChartGraphics;
import com.java4less.rchart.gc.GraphicsProvider;
import com.java4less.rchart.gc.android.ChartAndroidGraphics;
import com.whucs.energyriver.Bean.Percent;
import com.whucs.energyriver.Presenter.RankPresenter;
import com.whucs.energyriver.Public.Common;
import com.whucs.energyriver.View.PercentView;
import com.whucs.energyriver.Widget.StateSwitchFragment;

import java.util.List;


public class TypeRankFragment extends StateSwitchFragment implements PercentView{
    Activity activity;
    Resources res;
    LinearLayout container;
    RankPresenter presenter;
    Chart chart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);
        View content = inflater.inflate(R.layout.container,null);
        initWidget(content);
        return view;
    }

    private void initWidget(View view){
        activity = getActivity();
        res = activity.getResources();
        iniAdapter(view);
        initChart();

        View empty = LayoutInflater.from(activity).inflate(R.layout.empty,null);
        Toolbar toolbar = (Toolbar) empty.findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
        TextView content = (TextView)empty.findViewById(R.id.content);
        content.setText(res.getText(R.string.no_type));
        TextView addition = (TextView)empty.findViewById(R.id.addition);
        addition.setText(res.getText(R.string.no_type_addition));
        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setMargins(0,8,0,0);
        addition.setLayoutParams(layout);
        addState("empty",empty);

        container = (LinearLayout) view.findViewById(R.id.container);
        TextView typename1 = (TextView) view.findViewById(R.id.typename1);
        TextView typename2 = (TextView) view.findViewById(R.id.typename2);
        TextView typename3 = (TextView) view.findViewById(R.id.typename3);
        typename1.setText(Common.types[0]);
        typename2.setText(Common.types[1]);
        typename3.setText(Common.types[2]);
        presenter = new RankPresenter(this);
        presenter.getMobileEnergyByType(activity);
    }

    private void initChart(){
        Title title = new Title("");
        //开始绘图
        PiePlotter pp = new PiePlotter();
        //设置3D效果为true
        pp.effect3D = true;
        //设置边框
        pp.border = new LineStyle(1,GraphicsProvider.getColor(ChartColor.WHITE), LineStyle.LINE_NORMAL);
        //设置label的格式（#PERCENTAGE#，#VALUE#,#LABEL#）什么也不想显示的话直接“ ”里面有一个空格就可以了
        pp.labelFormat = "#PERCENTAGE#";
        //设置半径
        pp.radiusModifier = 3.0;
        //设置块与块之间的间隔
        pp.space = 10;
        //设置label到块之间的线的样式
        pp.labelLine = new LineStyle(2,GraphicsProvider.getColor(ChartColor.WHITE),LineStyle.LINE_NORMAL);

        Legend legend = new Legend();
        legend.legendLabel = " ";
        chart = new com.java4less.rchart.Chart(title, pp, null, null);
        chart.layout = Chart.LAYOUT_LEGEND_TOP;
        chart.topMargin = 0.2;
        chart.bottomMargin = 0.4;
        chart.leftMargin=0.01;
        chart.rightMargin=0.1;
        chart.legend = legend;
    }

    @Override
    public void reload() {
        showViewByTag("loading");
        if(activity == null)
            activity = getActivity();
        if(presenter == null)
            presenter = new RankPresenter(this);
        presenter.getMobileEnergyByType(activity);
    }

    @Override
    public void setPercent(List<Percent> data) {
        if(data!=null && data.size()>0) {

            int len = data.size();
            double[] chart_scale = new double[len];
            FillStyle[] chart_color = new FillStyle[len];
            boolean[] chart_group = new boolean[len];
            String[] chart_label = new String[len];
            Double total = 0.0;
            for (int i = 0; i < len; i++) {
                chart_scale[i] = Double.parseDouble(data.get(i).getTotal().toString());
                chart_group[i] = true;
                chart_color[i] = new FillStyle(GraphicsProvider.getColor(Common.type_colors[i]));
                chart_label[i] = data.get(i).getLoopTypeName();
                total+=chart_scale[i];
            }
            for (int i = 0; i < len; i++) {
                chart_scale[i] /= total;
            }
            PieDataSerie pds = new PieDataSerie(chart_scale, chart_color, chart_group, chart_label);
            //设置label的样式
            pds.valueFont = GraphicsProvider.getFont("Arial", ChartFont.PLAIN,40);
            pds.valueColor = GraphicsProvider.getColor(ChartColor.WHITE);
            //设置label到中心的距离
            pds.textDistanceToCenter = 1.4;
            chart.addSerie(pds);
            chart.setHeight((int)(0.54*Common.getParentHeight(activity)));
            chart.setWidth(Common.getParentWidth(activity));

            ChartPanel chartPanel = new ChartPanel(activity);
            chartPanel.setBackgroundColor(res.getColor(R.color.baseColor));
            chartPanel.setChart(chart);
            container.addView(chartPanel);
            showViewByTag("content");
        }else
            showViewByTag("empty");
    }

    @Override
    public void getPercentError() {
        showViewByTag("error");
    }
}


