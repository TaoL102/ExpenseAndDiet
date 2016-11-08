package com.expenseanddiet.app.expensediet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.expenseanddiet.app.expensediet.Fragment.ExpenseChartFragment;
import com.expenseanddiet.app.expensediet.Models.ExpenseChart;
import com.github.mikephil.charting.charts.PieChart;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExpenseChartActivity extends DrawerActivity {

    private static final String TAG = "ExpenseChartActivity";
    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_expense_chart);


        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_expense_chart,null, false);
        mDrawer.addView(contentView, 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        // [START initialize_database_ref]

        mDatabase = FirebaseDatabase.getInstance().getReference();

        // [END initialize_database_ref]



        // Test
        addExpense( "201611",33,55,34,37,12,76,57,101 );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_expense_chart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final ExpenseChartFragment fragment1=new ExpenseChartFragment();
        private final ExpenseChartFragment fragment2=new ExpenseChartFragment();

        private  Fragment[] mFragments = new Fragment[] {
                fragment1,fragment2
        };
        private  String[] mFragmentNames = new String[] {
                getString(R.string.heading_last_month),
                getString(R.string.heading_this_month),
        };

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);

            // Put receipt id to fragment
            Bundle args1=new Bundle();
            args1.putString("yearMonth","201610");
            fragment1.setArguments(args1);


            // Put receipt id to fragment
            Bundle args2=new Bundle();
            args2.putString("yearMonth","201611");
            fragment2.setArguments(args2);

        }

        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }

        @Override
        public int getCount() {
            return mFragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentNames[position];
        }

    }


    // [START get expense data from database]
    private void addExpense(String yearMonth,
                            double dairyTotal,
                            double snacksAndSweetsTotal,
                            double meatAndPoultryTotal,
                            double grainsTotal,
                            double fruitsAndFruitJuiceTotal,
                            double vegetablesTotal,
                            double beveragesTotal,
                            double alcoholTotal) {

        ExpenseChart expenseChart = new ExpenseChart(yearMonth, dairyTotal, snacksAndSweetsTotal,
                meatAndPoultryTotal,grainsTotal,fruitsAndFruitJuiceTotal,vegetablesTotal,beveragesTotal,alcoholTotal);

        Map<String, Object> expenseChartValues = expenseChart.toMap();
        Map<String, Object> childUpdates = new HashMap<>();

        childUpdates.put("/monthlyexpenses/" + yearMonth, expenseChartValues);
        childUpdates.put("/user-monthlyexpenses/" + getUid() + "/" + yearMonth, expenseChartValues);

        mDatabase.updateChildren(childUpdates);
    }
    // [END write_fan_out]

    public void shareBtn_OnClick(View view) {

        // Find
        PieChart chartView = (PieChart) findViewById(R.id.chart);
        Bitmap bitmap = chartView.getChartBitmap();

        // Create image
        File imagePath = new File(getFilesDir(), "images");
        File newFile = new File(imagePath, "sharePic.jpg");
        createImage(bitmap,imagePath,newFile);

        // Create intent
        Intent shareIntent = new Intent(Intent.ACTION_SEND);

        // Clarify file path. To solve the Gmail "Permission denied for the attachment" issue,
        // I use FileProvider.getUriForFile() method to grant permissions to the receiving app temporaryly,
        // which would expire automatically when the receiving app's task stack is finished.
        // Reference： https://developer.android.com/training/secure-file-sharing/setup-sharing.html

        Uri contentUri = FileProvider.getUriForFile(this, "com.expenseanddiet.app.expensediet.fileprovider", newFile);

        // Set intent
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
        shareIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

        // Call startActivity() method to invoke Chooser panel.
        startActivity(Intent.createChooser(shareIntent, "Share via"));

    }

    /**
     * This method is used to create an image in the storage
     * Reference： https://developer.android.com/training/secure-file-sharing/setup-sharing.html
     * @return if image is created in the assigned directory
     */
    public boolean createImage(Bitmap bitmap,File imagePath,File file) {

        // Make sure the directory exists.
        imagePath.mkdirs();

        // Save stream to file
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            // Create new bitmap based on the size and config of the old
            Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
// Instantiate a canvas and prepare it to paint to the new bitmap
            Canvas canvas = new Canvas(newBitmap);
// Paint it white (or whatever color you want)
            canvas.drawColor(Color.WHITE);
// Draw the old bitmap ontop of the new white one
            canvas.drawBitmap(bitmap, 0, 0, null);
            newBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            // PNG is a lossless format, the compression factor (100) is ignored
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
