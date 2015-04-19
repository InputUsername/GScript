package com.inputusername.android.gscript;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class OutputActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        Intent intent = getIntent();
        String output = intent.getStringExtra(MainActivity.EXTRA_OUTPUT);

        EditText editTextOutput = (EditText)findViewById(R.id.editText_Output);
        editTextOutput.setText(output);
    }

}
