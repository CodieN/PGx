package com.elizabethwhitebaker.pgx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class DosageCalculator extends AppCompatActivity {
    EditText Dosage;
    EditText Weight;
    EditText MedAmount;
    EditText PerVolume;

    Spinner DosageSpin;
    Spinner WeightSpin;
    Spinner MedAmountSpin;
    Spinner PerVolumeSpin;
    Spinner DoseSpin;
    Spinner LiquidDoseSpin;

    TextView Dose;
    TextView LiquidDose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosage_calc);

        Button Calculate = (Button)findViewById(R.id.button2);
        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dosage = (EditText)findViewById(R.id.editText);
                Weight = (EditText)findViewById(R.id.editText2);
                MedAmount = (EditText)findViewById(R.id.editText3);
                PerVolume = (EditText)findViewById(R.id.editText4);
                Dose = (TextView)findViewById(R.id.textView6);
                LiquidDose = (TextView)findViewById(R.id.textView8);

                DosageSpin = (Spinner)findViewById(R.id.spinner);
                WeightSpin = (Spinner)findViewById(R.id.spinner2);
                MedAmountSpin = (Spinner)findViewById(R.id.spinner3);
                PerVolumeSpin = (Spinner)findViewById(R.id.spinner4);
                DoseSpin = (Spinner)findViewById(R.id.spinner6);
                LiquidDoseSpin = (Spinner)findViewById(R.id.spinner7);

                double Dosage1 = 0;
                double Weight1 = 0;
                double MedAmount1 = 0;
                double PerVolume1 = 0;

                double DosageFinal = 0;
                double WeightFinal = 0;
                double MedAmountFinal = 0;
                double PerVolumeFinal = 0;

                double Dose1 = 0;
                double LiquidDose1 = 0;
                double DoseFinal = 0;
                double LiquidDoseFinal = 0;

                String DosageSpin1= DosageSpin.getSelectedItem().toString();
                String WeightSpin1=String.valueOf(WeightSpin.getSelectedItem());
                String MedAmountSpin1=String.valueOf(MedAmountSpin.getSelectedItem());
                String PerVolumeSpin1=String.valueOf(PerVolumeSpin.getSelectedItem());
                String DoseSpin1=String.valueOf(DoseSpin.getSelectedItem());
                String LiquidDoseSpin1=String.valueOf(LiquidDoseSpin.getSelectedItem());


                try {
                    Dosage1 = Double.parseDouble(String.valueOf(Dosage.getText()));
                }
                catch(Exception e)
                {
                }
                try {
                    Weight1 = Double.parseDouble(String.valueOf(Weight.getText()));
                }
                catch(Exception e)
                {
                }
                try {
                    MedAmount1 = Double.parseDouble(String.valueOf(MedAmount.getText()));
                }
                catch (Exception e)
                {
                }
                try {
                    PerVolume1 = Double.parseDouble(String.valueOf(PerVolume.getText()));
                }
                catch(Exception e)
                {
                }

                switch(DosageSpin1.toString()) {
                    case "mg/kg":
                        DosageFinal = Dosage1;
                        break;
                    case "mcg/kg":
                        DosageFinal = Dosage1 / 1000;
                        break;
                    case "gm/kg":
                        DosageFinal = Dosage1 * 1000;
                        break;
                }

                switch(WeightSpin1.toString()) {
                    case "kg":
                        WeightFinal = Weight1;
                        break;
                    case "lbs":
                        WeightFinal = Weight1 * 0.45359237;
                        break;
                }

                switch(MedAmountSpin1.toString()) {
                    case "gm":
                        MedAmountFinal = MedAmount1 * 1000;
                        break;
                    case "mcg":
                        MedAmountFinal = MedAmount1 / 1000;
                        break;
                    case "mg":
                        MedAmountFinal = MedAmount1;
                        break;
                }

                switch(PerVolumeSpin1.toString()) {
                    case "mL":
                        PerVolumeFinal = PerVolume1;
                        break;
                    case "L":
                        PerVolumeFinal = PerVolume1 * 1000;
                        break;
                }

                Dose1 = DosageFinal * WeightFinal;

                switch(DoseSpin1.toString()) {
                    case "gm BID":
                        DoseFinal = Dose1/2000;
                        break;
                    case "gm Daily":
                        DoseFinal = Dose1/1000;
                        break;
                    case "gm QID":
                        DoseFinal = Dose1/4000;
                        break;
                    case "gm TID":
                        DoseFinal = Dose1/3000;
                        break;
                    case "gm q1 hr":
                        DoseFinal = Dose1/24000;
                        break;
                    case "gm q2 hr":
                        DoseFinal = Dose1/12000;
                        break;
                    case "gm q4 hr":
                        DoseFinal = Dose1/6000;
                        break;
                    case "mcg BID":
                        DoseFinal = Dose1/0.002;
                        break;
                    case "mcg Daily":
                        DoseFinal = Dose1/0.001;
                        break;
                    case "mcg QID":
                        DoseFinal = Dose1/0.004;
                        break;
                    case "mcg TID":
                        DoseFinal = Dose1/0.003;
                        break;
                    case "mcg q1 hr":
                        DoseFinal = Dose1/0.024;
                        break;
                    case "mcg q2 hr":
                        DoseFinal = Dose1/0.012;
                        break;
                    case "mcg q4 hr":
                        DoseFinal = Dose1/0.006;
                        break;
                    case "mg BID":
                        DoseFinal = Dose1/2;
                        break;
                    case "mg Daily":
                        DoseFinal = Dose1;
                        break;
                    case "mg QID":
                        DoseFinal = Dose1/4;
                        break;
                    case "mg TID":
                        DoseFinal = Dose1/3;
                        break;
                    case "mg q1 hr":
                        DoseFinal = Dose1/24;
                        break;
                    case "mg q2 hr":
                        DoseFinal = Dose1/12;
                        break;
                    case "mg q4 hr":
                        DoseFinal = Dose1/6;
                        break;
                }

                int a=3;

                //Change precision

                Dose.setText(String.format("%."+String.valueOf(a)+"f", DoseFinal));

                LiquidDose1 = (Dose1 * PerVolumeFinal)/MedAmountFinal;

                switch(LiquidDoseSpin1.toString()) {
                    case "mL BID":
                        LiquidDoseFinal = LiquidDose1/2;
                        break;
                    case "mL Daily":
                        LiquidDoseFinal = LiquidDose1;
                        break;
                    case "mL QID":
                        LiquidDoseFinal = LiquidDose1/4;
                        break;
                    case "mL TID":
                        LiquidDoseFinal = LiquidDose1/3;
                        break;
                    case "mL q1 hr":
                        LiquidDoseFinal = LiquidDose1/24;
                        break;
                    case "mL q2 hr":
                        LiquidDoseFinal = LiquidDose1/12;
                        break;
                    case "mL q4 hr":
                        LiquidDoseFinal = LiquidDose1/6;
                        break;
                    case "L BID":
                        LiquidDoseFinal = LiquidDose1/(2  * 1000);
                        break;
                    case "L Daily":
                        LiquidDoseFinal = LiquidDose1 / 1000;
                        break;
                    case "L QID":
                        LiquidDoseFinal = LiquidDose1/(4 * 1000);
                        break;
                    case "L TID":
                        LiquidDoseFinal = LiquidDose1/(3 * 1000);
                        break;
                    case "L q1 hr":
                        LiquidDoseFinal = LiquidDose1/(24 * 1000);
                        break;
                    case "L q2 hr":
                        LiquidDoseFinal = LiquidDose1 /(12 * 1000);
                        break;
                    case "L q4 hr":
                        LiquidDoseFinal = LiquidDose1 /(6 * 1000);
                        break;
                }

                LiquidDose.setText(String.format("%."+String.valueOf(a)+"f", LiquidDoseFinal));
            }
        });
    }
}