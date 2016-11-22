package com.dream.will.company_funny.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Author：Will on 2016/11/22 10:56
 * Mail：heheheqin.will@gmail.com
 */

public class BaseNoActionBarActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        getSupportActionBar().hide();
    }
}
