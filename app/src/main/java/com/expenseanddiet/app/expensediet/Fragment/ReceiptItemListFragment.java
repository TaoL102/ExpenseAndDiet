package com.expenseanddiet.app.expensediet.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.expenseanddiet.app.expensediet.Models.ReceiptItem;
import com.expenseanddiet.app.expensediet.R;
import com.expenseanddiet.app.expensediet.ViewHolder.ReceiptItemViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * Created by Tao on 7/11/2016.
 */

public abstract class ReceiptItemListFragment extends Fragment{

    private static final String TAG = "ReceiptItemListFragment";

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    private FirebaseRecyclerAdapter<ReceiptItem, ReceiptItemViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    public ReceiptItemListFragment() {}

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_receipt_item, container, false);

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        mRecycler = (RecyclerView) rootView.findViewById(R.id.receipt_item_list);
        mRecycler.setHasFixedSize(true);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up Layout Manager, reverse layout
        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query postsQuery = getQuery(mDatabase);
        mAdapter = new FirebaseRecyclerAdapter<ReceiptItem, ReceiptItemViewHolder>(ReceiptItem.class, R.layout.item_receipt_item,
                ReceiptItemViewHolder.class, postsQuery) {
            @Override
            protected void populateViewHolder(final ReceiptItemViewHolder viewHolder, final ReceiptItem model, final int position) {
                final DatabaseReference postRef = getRef(position);

                // Set click listener for the whole post view
                final String postKey = postRef.getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Launch PostDetailActivity
                        /*Intent intent = new Intent(getActivity(), PostDetailActivity.class);
                        intent.putExtra(PostDetailActivity.EXTRA_POST_KEY, postKey);
                        startActivity(intent);*/

                        //  startActivity(new Intent(getActivity(), ReceiptDetailActivity.class));
                    }
                });

                // Bind Post to ViewHolder, setting OnClickListener for the star button
                viewHolder.bindToPost(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View starView) {
                        // Need to write to both places the post is stored
                        DatabaseReference globalPostRef = mDatabase.child("posts").child(postRef.getKey());
                        DatabaseReference userPostRef = mDatabase.child("user-posts").child(model.itemID).child(postRef.getKey());


                    }
                });

            }
        };
        mRecycler.setAdapter(mAdapter);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public abstract Query getQuery(DatabaseReference databaseReference);

}
