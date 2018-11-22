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
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DosingGuidelines extends AppCompatActivity implements SpinnerAdapter {
    private static final String TAG = "DosingGuidelines";

    private Spinner alleleSpinner1, alleleSpinner2;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosing_guidelines);

        intent = new Intent(DosingGuidelines.this, AlleleActivity.class);
        String gene = getIntent().getStringExtra("gene");
        String drug = getIntent().getStringExtra("drug");
        intent.putExtra("gene", gene);
        intent.putExtra("drug", drug);

        alleleSpinner1 = (Spinner) findViewById(R.id.allele_spinner1);
        alleleSpinner2 = (Spinner) findViewById(R.id.allele_spinner2);
        final Button btnDone = (Button) findViewById(R.id.btn_done);

        btnDone.setEnabled(false);

        switch(gene) {
            case "TPMT (Thiopurine methyltransferase)":

                ArrayAdapter<CharSequence> allele1AdapterTPMT = ArrayAdapter.createFromResource(this,
                        R.array.allele1_arrayTPMT, android.R.layout.simple_spinner_item);
                allele1AdapterTPMT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                alleleSpinner1.setAdapter(allele1AdapterTPMT);

                ArrayAdapter<CharSequence> allele2AdapterTPMT = ArrayAdapter.createFromResource(this,
                        R.array.allele2_arrayTPMT, android.R.layout.simple_spinner_item);
                allele2AdapterTPMT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                alleleSpinner2.setAdapter(allele2AdapterTPMT);
                break;
            case "DPYD (Dihydropyrimidine dehydrogenase)":

                ArrayAdapter<CharSequence> allele1AdapterDPYD = ArrayAdapter.createFromResource(this,
                        R.array.allele1_arrayDPYD, android.R.layout.simple_spinner_item);
                allele1AdapterDPYD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                alleleSpinner1.setAdapter(allele1AdapterDPYD);

                ArrayAdapter<CharSequence> allele2AdapterDPYD = ArrayAdapter.createFromResource(this,
                        R.array.allele2_arrayDPYD, android.R.layout.simple_spinner_item);
                allele2AdapterDPYD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                alleleSpinner2.setAdapter(allele2AdapterDPYD);
                break;
        }
        alleleSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!alleleSpinner1.getSelectedItem().toString().equals("Select first allele") &&
                        !alleleSpinner2.getSelectedItem().toString().equals("Select second allele")) {
                    btnDone.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        alleleSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!alleleSpinner1.getSelectedItem().toString().equals("Select first allele") &&
                        !alleleSpinner2.getSelectedItem().toString().equals("Select second allele")) {
                    btnDone.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        TextView textView = (TextView) findViewById(R.id.dosing_guideline_tv);

        textView.setText("Dosing Guidelines for " + gene + " and " + drug);

        btnDone.setOnClickListener(mOnClickListener);

    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent.putExtra("allele1", alleleSpinner1.getSelectedItem().toString());
            intent.putExtra("allele2", alleleSpinner2.getSelectedItem().toString());
            startActivity(intent);
        }
    };

    /*@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(!alleleSpinner1.getSelectedItem().toString().equals("Select first allele") && !alleleSpinner2.getSelectedItem().toString().equals("Select second allele") ) {
            intent.putExtra("allele1", alleleSpinner1.getSelectedItem().toString());
            intent.putExtra("allele2", alleleSpinner2.getSelectedItem().toString());

            startActivity(intent);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }*/

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
