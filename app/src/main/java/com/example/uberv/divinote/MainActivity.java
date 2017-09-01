package com.example.uberv.divinote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.uberv.divinote.storage.database.DiviNoteDatabaseHelper;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DiviNoteDatabaseHelper helper = new DiviNoteDatabaseHelper(this);
        Timber.d(helper.toString());
    }
}
