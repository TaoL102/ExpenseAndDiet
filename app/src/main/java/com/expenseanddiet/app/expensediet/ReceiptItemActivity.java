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

import com.expenseanddiet.app.expensediet.Fragment.ReceiptItemFragment;
import com.expenseanddiet.app.expensediet.Models.ReceiptItem;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ReceiptItemActivity extends DrawerActivity {

    private static final String TAG = "ReceiptItemActivity";
    public static final String RECEIPT_ID = "receiptId";

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

    private String receiptId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_receipt_item);

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
     //   setSupportActionBar(toolbar);

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

        // Get receiptId from intent
        receiptId = getIntent().getStringExtra(RECEIPT_ID);

        if (receiptId == null) {
            throw new IllegalArgumentException("Must pass RECEIPT_ID");
        }


        // Test
       /*addReceiptItem(Integer.toString(new Random().nextInt(100000)),
               receiptId,
                "TEST PRODUCT NAME",
                new Random().nextDouble()
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
        private final ReceiptItemFragment receiptItemFragment=new ReceiptItemFragment();
private  String receipt_Id;

        private final Fragment[] mFragments = new Fragment[] {
                receiptItemFragment
        };
        private String[] mFragmentNames = new String[] {

        };

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
// Get receiptId from intent
            receipt_Id=getIntent().getStringExtra(RECEIPT_ID);
            Log.d(TAG,"ReceiptID:"+receipt_Id);

            // Put receipt id to fragment
            Bundle args=new Bundle();
            args.putString("receiptId",receipt_Id);
            receiptItemFragment.setArguments(args);

            // Initialize fragment name
            mFragmentNames= new String[] {
                    receipt_Id
            };
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }

        @Override
        public int getCount()  {
            return mFragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentNames[position];
        }
    }


    // [START write_fan_out]
    private void addReceiptItem( String itemID,String receiptId,String name,double price) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("receipts").push().getKey();
        ReceiptItem post = new ReceiptItem(  itemID, receiptId, name, price);
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/items/" + itemID, postValues);
        childUpdates.put("/receipt-items/" + receiptId+ "/" + itemID , postValues);

        mDatabase.updateChildren(childUpdates);
    }
    // [END write_fan_out]
}
