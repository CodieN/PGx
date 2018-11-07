package com.elizabethwhitebaker.pgx;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    public ConstraintLayout intro, intro2, intro3, intro4;
    public View currentLayout;
    public TextView mTextView;
    public Toolbar mToolbar;
//    public final Button btnKnow = (Button) findViewById(R.id.btnKnow);
//    public final Button btnStart = (Button) findViewById(R.id.btnStart);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentLayout = findViewById(R.id.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        Button btnKnow = (Button) findViewById(R.id.btnKnow);
        Button btnStart = (Button) findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GetStartedActivity.class));

            }
        });
        btnKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, KnowMoreActivity.class));
            }
        });

//        intro = (ConstraintLayout) findViewById(R.id.intro);
//        intro2 = (ConstraintLayout) findViewById(R.id.intro2);
//        intro3 = (ConstraintLayout) findViewById(R.id.intro3);
//        intro4 = (ConstraintLayout) findViewById(R.id.intro4);
//
//        currentLayout = findViewById(R.id.intro);
//
//        Log.d(TAG, "root view is " + currentLayout.getId() + " and " + R.id.intro);
//        currentLayout.setOnClickListener(mOnClickListener);

//        Old Code
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        Button btnKnow = (Button) findViewById(R.id.btnKnow);
//        Button btnStart = (Button) findViewById(R.id.btnStart);
//
//        btnStart.setOnClickListener(this);
//        btnKnow.setOnClickListener(this);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch(currentLayout.getId()) {

                case R.id.intro4:


                    break;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onClick(View v) {
        Intent intent = null;

//        switch(v.getId()) {
//            case btnKnow:
//                startActivity(new Intent(this, KnowMoreActivity.class));
//                Log.d(TAG, "onClick: =============================jjjj");
//                break;
//            case btnStart:
//                startActivity(new Intent(this, GetStartedActivity.class));
//                break;
//
//            default:
//        }

    }

}
