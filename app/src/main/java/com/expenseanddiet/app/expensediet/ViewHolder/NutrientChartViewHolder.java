package com.expenseanddiet.app.expensediet.ViewHolder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.expenseanddiet.app.expensediet.Models.NutrientChart;
import com.expenseanddiet.app.expensediet.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tao on 9/11/2016.
 */

public class NutrientChartViewHolder extends RecyclerView.ViewHolder {

    public TextView itemNameView;
    public TextView itemPriceView;
    public ImageView vendorView;
    public PieChart chartView;
    public View view;

    // TODO: 20/10/2016  ALL have to be modified.

    public NutrientChartViewHolder(View itemView) {
        super(itemView);

        itemNameView = (TextView) itemView.findViewById(R.id.item_name);
        itemPriceView = (TextView) itemView.findViewById(R.id.item_price);
        vendorView = (ImageView) itemView.findViewById(R.id.receipt_vendor);

        // Bar chart
        chartView = (PieChart) itemView.findViewById(R.id.chart);
        this.view=view;

    }

    public void bindToPost(NutrientChart item, View.OnClickListener starClickListener) {
        //  itemNameView.setText(item.alcoholTotal);
        //itemPriceView.setText(Double.toString(item.alcoholTotal));
        /*vendorView.setOnClickListener(starClickListener);*/



        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry((float)item.proteinTotal, "Protein"));
        entries.add(new PieEntry((float)item.fatTotal, "Fat"));
        entries.add(new PieEntry((float)item.carbsTotal, "Carbs"));


        PieDataSet set = new PieDataSet(entries,"");
        set.setColors(
                Color.parseColor("#ACFF59"),
                Color.parseColor("#0F63AD"),
                Color.parseColor("#EF8700"),
                Color.parseColor("#019FE9"),
                Color.parseColor("#F7C80E"),
                Color.parseColor("#50B948"),
                Color.parseColor("#5C2772"),
                Color.parseColor("#DE0000")
        );
        PieData data = new PieData(set);
        data.setValueTextSize(12f);
        chartView.setData(data);
        chartView.invalidate(); // refresh

        // Styling piechart
        stylePieChart();


    }

    private void stylePieChart(){
        chartView.setUsePercentValues(true);
        chartView.getDescription().setEnabled(false);
        chartView.setExtraOffsets(5, 10, 5, 5);

        chartView.setDragDecelerationFrictionCoef(0.95f);


        chartView.setDrawHoleEnabled(true);
        chartView.setHoleColor(Color.WHITE);

        chartView.setTransparentCircleColor(Color.WHITE);
        chartView.setTransparentCircleAlpha(110);

        chartView.setHoleRadius(30f);
        chartView.setTransparentCircleRadius(40f);

        chartView.setDrawCenterText(true);

        chartView.setRotationAngle(0);
        // enable rotation of the chart by touch
        chartView.setRotationEnabled(true);
        chartView.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        // chartView.setOnChartValueSelectedListener(this);

        //  setData(4, 100);

        chartView.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // chartView.spin(2000, 0, 360);


        Legend l = chartView.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);
        l.setXEntrySpace(0f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
        l.setTextSize(12f);
        l.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        // entry label styling
        chartView.setEntryLabelColor(Color.BLACK);
        //  chartView.setEntryLabelTypeface(mTfRegular);
        chartView.setEntryLabelTextSize(16f);
        chartView.setBackgroundColor(Color.WHITE);


    }





}
