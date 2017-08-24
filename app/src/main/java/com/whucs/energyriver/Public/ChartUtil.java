package com.whucs.energyriver.Public;


import com.java4less.rchart.Chart;
import com.java4less.rchart.FillStyle;
import com.java4less.rchart.LineStyle;
import com.java4less.rchart.PieDataSerie;
import com.java4less.rchart.PiePlotter;
import com.java4less.rchart.Title;
import com.java4less.rchart.gc.ChartColor;
import com.java4less.rchart.gc.ChartFont;
import com.java4less.rchart.gc.GraphicsProvider;

public class ChartUtil {
    public Title getTitle(String title){
        return new Title(title);
    }

    public PieDataSerie getPieDataSerie(double[] scale,String[] label){
        PieDataSerie pieDataSerie =  new PieDataSerie(scale,new FillStyle[]{},new boolean[]{true},label);
        pieDataSerie.valueFont = GraphicsProvider.getFont("Arial", ChartFont.PLAIN,16);
        pieDataSerie.textDistanceToCenter = 16;
        return pieDataSerie;
    }

    //是否3D,饼图半径,饼图间隔
    public PiePlotter getPiePlotter(boolean is3D,float radius,int space){
        PiePlotter piePlotter = new PiePlotter();
        piePlotter.effect3D = true;
        piePlotter.border = new LineStyle(1,GraphicsProvider.getColor(ChartColor.BLACK),LineStyle.LINE_NORMAL);
        piePlotter.radiusModifier = radius;
        piePlotter.space = space;
        piePlotter.labelFormat = "#PERCENTAGE#";
        piePlotter.labelLine = new LineStyle(1,GraphicsProvider.getColor(ChartColor.BLACK),LineStyle.LINE_NORMAL);
        return piePlotter;
    }

    //饼图标题,各部分比例,各部分说明,是否3D,饼图半径,饼图间隔
    public Chart getChart(String title,double[] scale,String[] label,boolean is3D,float radiusModifier,int space ){
        Chart chart = new Chart(getTitle(title),getPiePlotter(is3D, radiusModifier, space),null,null);
        chart.addSerie(getPieDataSerie(scale,label));
        chart.back = new FillStyle(GraphicsProvider.getColor(3,25,45));
        chart.layout = Chart.LAYOUT_LEGEND_TOP;
        //外部设置width & height
        return chart;
    }

}
