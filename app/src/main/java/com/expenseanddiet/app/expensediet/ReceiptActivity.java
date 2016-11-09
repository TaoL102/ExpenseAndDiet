package com.expenseanddiet.app.expensediet;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.expenseanddiet.app.expensediet.Fragment.ReceiptFragment;
import com.expenseanddiet.app.expensediet.Models.Receipt;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ReceiptActivity extends DrawerActivity {

    private static final String TAG = "ReceiptActivity";
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
       // setContentView(R.layout.activity_receipt);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_receipt,null, false);
        mDrawer.addView(contentView, 0);


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
      /*  addReceipt(getUid(),
                "NZCOUNTDOWN0001"+ new Random().nextInt(100000),
                new Random().nextDouble(),
                new Random().nextInt(50),
                new Random().nextDouble(),
                new Random().nextDouble(),new Random().nextDouble(),new Random().nextDouble(),new Random().nextDouble(),new Random().nextDouble(),new Random().nextDouble(),new Random().nextDouble(),new Random().nextDouble(),new Random().nextDouble(),new Random().nextDouble()
        );*/
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

        private final ReceiptFragment lastMonthFragment=new ReceiptFragment();
        private final ReceiptFragment thisMonthFragment=new ReceiptFragment();

        private final Fragment[] mFragments = new Fragment[] {
                lastMonthFragment,
                thisMonthFragment,
                 };
        private final String[] mFragmentNames = new String[] {
                getString(R.string.heading_last_month),
                getString(R.string.heading_this_month),
        };

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            // get today and clear time of day
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
            cal.clear(Calendar.MINUTE);
            cal.clear(Calendar.SECOND);
            cal.clear(Calendar.MILLISECOND);

// get start of the month
            cal.set(Calendar.DAY_OF_MONTH, 1);
            long startDate=cal.getTimeInMillis();

            cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
            long endDate=cal.getTimeInMillis();

            Log.d(TAG,startDate+","+endDate);

            Bundle thisMonthBundle=new Bundle();
            thisMonthBundle.putLong("startDate",startDate);
            thisMonthBundle.putLong("endDate",endDate);

            thisMonthFragment.setArguments(thisMonthBundle);

            // get today and clear time of day
             cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
            cal.clear(Calendar.MINUTE);
            cal.clear(Calendar.SECOND);
            cal.clear(Calendar.MILLISECOND);

// get start of last month
            cal.set(Calendar.DAY_OF_MONTH,1);
            cal.add(Calendar.DATE, -1);
            endDate=startDate;
            startDate=cal.getTimeInMillis();



            Bundle lastMonthBundle=new Bundle();
            lastMonthBundle.putLong("startDate",startDate);
            lastMonthBundle.putLong("endDate",endDate);
            Log.d(TAG,startDate+","+endDate);

            lastMonthFragment.setArguments(lastMonthBundle);


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

    // [START write_fan_out]
    private void addReceipt(String uid,
                            String receiptId,
                            double totalPrice,
                            int totalQuantity,
                            double dairyTotal,
                            double snacksAndSweetsTotal,
                            double meatAndPoultryTotal,
                            double grainsTotal,
                            double fruitsAndFruitJuiceTotal,
                            double vegetablesTotal,
                            double beveragesTotal,
                            double alcoholTotal,
                            double proteinTotal,
                            double fatTotal,
                            double carbsTotal) {
        // Create new receipt at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("receipts").push().getKey();
        Receipt receipt = new Receipt(uid, receiptId,totalPrice,totalQuantity,dairyTotal, snacksAndSweetsTotal,
         meatAndPoultryTotal,grainsTotal,fruitsAndFruitJuiceTotal,vegetablesTotal,beveragesTotal,alcoholTotal, proteinTotal,
        fatTotal, carbsTotal);

        Map<String, Object> receiptValues = receipt.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/receipts/" + receiptId, receiptValues);
        childUpdates.put("/user-receipts/" + uid + "/" + receiptId, receiptValues);

        mDatabase.updateChildren(childUpdates);
    }
    // [END write_fan_out]
}
