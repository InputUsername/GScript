package com.inputusername.android.gscript;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toolbar;

import com.inputusername.android.gscript.lang.Interpreter;

public class MainActivity extends Activity {
    public final static String EXTRA_OUTPUT = "GS_OUTPUT";

    public final static String PREFS_NAME = "GS_PREFS";
    public final static String PREF_SAVED_CODE = "GS_SAVED_CODE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String code = settings.getString(PREF_SAVED_CODE, "");
        getCodeEditText().setText(code);
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREF_SAVED_CODE, getCodeEditText().getText().toString());
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_run:
                runCode();
                return true;
            case R.id.action_clear:
                clearCode();
                return true;
            case R.id.action_help:
                showHelp();
                return true;
            default:
                return true;
        }
    }

    EditText getCodeEditText() {
        return (EditText)findViewById(R.id.editText_Code);
    }

    void runCode() {
        String code = getCodeEditText().getText().toString();
        if (code.isEmpty()) {
            return;
        }
        String output = Interpreter.interpret(code);

        Intent intent = new Intent(this, OutputActivity.class);
        intent.putExtra(EXTRA_OUTPUT, output);
        startActivity(intent);
    }

    void clearCode() {
        EditText editTextCode = getCodeEditText();
        editTextCode.setText("");
    }

    void showHelp() {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

}
