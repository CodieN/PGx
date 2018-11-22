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

import static android.widget.AdapterView.*;

public class GetStartedActivity extends AppCompatActivity implements SpinnerAdapter{
    private static final String TAG = "GetStartedActivity";

    private Spinner geneSpinner, drugSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate==========================================");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        geneSpinner = (Spinner) findViewById(R.id.gene_spinner);
        drugSpinner = (Spinner) findViewById(R.id.drug_spinner);
        final Button btnDone = (Button) findViewById(R.id.btn_done1);

        btnDone.setEnabled(false);

        ArrayAdapter<CharSequence> geneAdapter = ArrayAdapter.createFromResource(this,
                R.array.gene_array, android.R.layout.simple_spinner_item);
        geneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        geneSpinner.setAdapter(geneAdapter);

        final ArrayAdapter<CharSequence> drugAdapterTPMT = ArrayAdapter.createFromResource(this,
                R.array.drugs_arrayTPMT, android.R.layout.simple_spinner_item);
        drugAdapterTPMT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<CharSequence> drugAdapterDPYD = ArrayAdapter.createFromResource(this,
                R.array.drugs_arrayDPYD, android.R.layout.simple_spinner_item);
        drugAdapterDPYD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        geneSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(geneSpinner.getSelectedItem().toString().equals("Select gene")) {
                    drugSpinner.setEnabled(false);
                } else {
                    drugSpinner.setEnabled(true);
                    switch(geneSpinner.getSelectedItem().toString())
                    {
                        case "TPMT (Thiopurine methyltransferase)":
                            drugSpinner.setEnabled(true);
                            drugSpinner.setAdapter(drugAdapterTPMT);
                            break;
                        case "DPYD (Dihydropyrimidine dehydrogenase)":
                            drugSpinner.setEnabled(true);
                            drugSpinner.setAdapter(drugAdapterDPYD);
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        drugSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!drugSpinner.getSelectedItem().toString().equals("Select drug")) {
                    btnDone.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnDone.setOnClickListener(mOnClickListener);

    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                Intent intent = new Intent(GetStartedActivity.this, DosingGuidelines.class);
                intent.putExtra("drug", drugSpinner.getSelectedItem().toString());
                intent.putExtra("gene", geneSpinner.getSelectedItem().toString());
                startActivity(intent);
            } catch(NullPointerException npe) {

            }
        }
    };

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
