package com.inputusername.android.gscript;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inputusername.android.gscript.lang.Functions;
import com.inputusername.android.gscript.lang.PartiallyImplemented;
import com.inputusername.android.gscript.lang.Unimplemented;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;


public class HelpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        TextView textView = (TextView)findViewById(R.id.textView_methods);
        textView.append("\n-------");

        List<String> excludes = Arrays.asList("wait", "execute", "notify", "notifyAll");

        for (Method method : Functions.class.getMethods()) {
            if (!method.isAnnotationPresent(Unimplemented.class)
                    && method.getReturnType().equals(Void.TYPE)
                    && !excludes.contains(method.getName())) {
                textView.append("\n\n" + method.getName());

                if (method.isAnnotationPresent(PartiallyImplemented.class)) {
                    textView.append(" (partially)");
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.help_actions, menu);
        return true;
    }
}
