package com.expenseanddiet.app.expensediet;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.expenseanddiet.app.expensediet.Fragment.NutrientChartFragment;
import com.expenseanddiet.app.expensediet.Models.NutrientChart;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class NutrientChartActivity extends DrawerActivity{

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
        //setContentView(R.layout.activity_nutrient_chart);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_nutrient_chart,null, false);
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // [START initialize_database_ref]

        mDatabase = FirebaseDatabase.getInstance().getReference();

        // [END initialize_database_ref]

        // Test
         addNutrient( "201611",33,55,34 );

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nutrient_chart, menu);
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

        private final NutrientChartFragment fragment1=new NutrientChartFragment();
        private final NutrientChartFragment fragment2=new NutrientChartFragment();

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
    private void addNutrient(String yearMonth,
                             double proteinTotal,
                             double fatTotal,
                             double carbsTotal) {

        NutrientChart nutrientChart = new NutrientChart(yearMonth,proteinTotal,fatTotal,carbsTotal);

        Map<String, Object> expenseChartValues = nutrientChart.toMap();
        Map<String, Object> childUpdates = new HashMap<>();

        childUpdates.put("/monthlynutrients/" + yearMonth, expenseChartValues);
        childUpdates.put("/user-monthlynutrients/" + getUid() + "/" + yearMonth, expenseChartValues);

        mDatabase.updateChildren(childUpdates);
    }
    // [END write_fan_out]

}
