package com.elizabethwhitebaker.pgx;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class GetStartedActivity extends AppCompatActivity implements SpinnerAdapter, AdapterView.OnItemSelectedListener{
    private static final String TAG = "GetStartedActivity";

    private Spinner geneSpinner, drugSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate==========================================");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        geneSpinner = (Spinner) findViewById(R.id.gene_spinner);
        drugSpinner = (Spinner) findViewById(R.id.drug_spinner);

        ArrayAdapter<CharSequence> geneAdapter = ArrayAdapter.createFromResource(this,
                R.array.gene_array, android.R.layout.simple_spinner_item);
        geneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        geneSpinner.setAdapter(geneAdapter);
//        geneSpinner.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> drugAdapter = ArrayAdapter.createFromResource(this,
                R.array.drugs_array, android.R.layout.simple_spinner_item);
        drugAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drugSpinner.setAdapter(drugAdapter);
//        drugSpinner.setOnItemSelectedListener(this);

        Button btnDone = (Button) findViewById(R.id.btn_done1);
        btnDone.setOnClickListener(mOnClickListener);

    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(GetStartedActivity.this, DosingGuidelines.class);
            intent.putExtra("drug", drugSpinner.getSelectedItem().toString());
            intent.putExtra("gene", geneSpinner.getSelectedItem().toString());
            startActivity(intent);
        }
    };

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(!geneSpinner.getSelectedItem().toString().equals("Select gene") && !drugSpinner.getSelectedItem().toString().equals("Select drug") ) {


        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
