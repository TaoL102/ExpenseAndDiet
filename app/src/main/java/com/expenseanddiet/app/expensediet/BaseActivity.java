package com.expenseanddiet.app.expensediet;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Tao on 9/10/2016.
 */

public class BaseActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;
    static boolean isDatabaseInitialized = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            if(!isDatabaseInitialized){
                FirebaseDatabase.getInstance().setPersistenceEnabled(true);
                isDatabaseInitialized = true;
            }else {

            }
        }catch (Exception e){
            e.printStackTrace();
        }
}

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }



}
