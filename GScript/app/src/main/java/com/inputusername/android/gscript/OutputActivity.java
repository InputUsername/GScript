package com.inputusername.android.gscript;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;
import android.widget.ShareActionProvider;

public class OutputActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        Intent intent = getIntent();
        String output = intent.getStringExtra(MainActivity.EXTRA_OUTPUT);

        EditText editTextOutput = (EditText)findViewById(R.id.editText_Output);
        editTextOutput.setText(output);
        editTextOutput.setSelection(output.length());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.output_actions, menu);

        /* SHARING DISABLED

        EditText editTextOutput = (EditText)findViewById(R.id.editText_Output);
        String output = editTextOutput.getText().toString();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "GScript output: " + output);
        intent.setType("text/plain");

        MenuItem item = menu.findItem(R.id.action_share);
        ShareActionProvider shareActionProvider = (ShareActionProvider)item.getActionProvider();
        shareActionProvider.setShareIntent(intent);
        */

        return true;
    }

}
