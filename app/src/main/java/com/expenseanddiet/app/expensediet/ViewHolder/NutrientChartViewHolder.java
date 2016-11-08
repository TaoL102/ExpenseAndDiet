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


        PieDataSet set = new PieDataSet(entries,item.yearMonth);
        set.setColors(Color.BLACK, Color.BLUE,Color.GREEN);
        PieData data = new PieData(set);
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

        //  chartView.setCenterTextTypeface(mTfLight);
        // chartView.setCenterText(generateCenterSpannableText());

        chartView.setDrawHoleEnabled(true);
        chartView.setHoleColor(Color.WHITE);

        chartView.setTransparentCircleColor(Color.WHITE);
        chartView.setTransparentCircleAlpha(110);

        chartView.setHoleRadius(58f);
        chartView.setTransparentCircleRadius(61f);

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
        // mChart.spin(2000, 0, 360);


        Legend l = chartView.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        chartView.setEntryLabelColor(Color.WHITE);
        //  chartView.setEntryLabelTypeface(mTfRegular);
        chartView.setEntryLabelTextSize(12f);

    }}
