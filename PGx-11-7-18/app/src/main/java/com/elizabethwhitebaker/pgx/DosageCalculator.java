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

                if(DosageSpin1.toString().equals("Mg/Kg"))
                {
                    DosageFinal = Dosage1 / 1000;
                }
                if(DosageSpin1.toString().equals("Mcg/Kg"))
                {
                    DosageFinal = Dosage1 / 1000000;
                }
                if(DosageSpin1.toString().equals("Gm/Kg"))
                {
                    DosageFinal = Dosage1;
                }
                if(WeightSpin1.equals("Kg"))
                {
                    WeightFinal = Weight1;
                }
                if(WeightSpin1.equals("Lb"))
                {
                    WeightFinal = Weight1 * 0.453592  ;
                }
                if(MedAmountSpin1.equals("Gm"))
                {
                    MedAmountFinal = MedAmount1;
                }
                if(MedAmountSpin1.equals("Mcg"))
                {
                    MedAmountFinal = MedAmount1 / 1000000;
                }
                if(MedAmountSpin1.equals("Mg"))
                {
                    MedAmountFinal = MedAmount1 / 1000;
                }
                if(PerVolumeSpin1.equals("Ml"))
                {
                    PerVolumeFinal = PerVolume1;
                }
                if(PerVolumeSpin1.equals("L"))
                {
                    PerVolumeFinal = PerVolume1 * 1000;
                }

                Dose1 = DosageFinal * WeightFinal;

                if(DoseSpin1.equals("Gm BID"))
                {
                    DoseFinal = Dose1/2;
                }
                if(DoseSpin1.equals("Gm Daily"))
                {
                    DoseFinal = Dose1;
                }
                if(DoseSpin1.equals("Gm QID"))
                {
                    DoseFinal = Dose1/4;
                }
                if(DoseSpin1.equals("Gm TID"))
                {
                    DoseFinal = Dose1/3;
                }
                if(DoseSpin1.equals("Gm q1 hr"))
                {
                    DoseFinal = Dose1/24;
                }
                if(DoseSpin1.equals("Gm q2 hr"))
                {
                    DoseFinal = Dose1/12;
                }
                if(DoseSpin1.equals("Gm q4 hr"))
                {
                    DoseFinal = Dose1/6;
                }
// gm done

                if(DoseSpin1.equals("Mcg BID"))
                {
                    DoseFinal = Dose1 * 1000000/2;
                }
                if(DoseSpin1.equals("Mcg Daily"))
                {
                    DoseFinal = Dose1 * 1000000;
                }
                if(DoseSpin1.equals("Mcg QID"))
                {
                    DoseFinal = Dose1 * 1000000/4;
                }
                if(DoseSpin1.equals("Mcg TID"))
                {
                    DoseFinal = Dose1 * 1000000/3;
                }
                if(DoseSpin1.equals("Mcg q1 hr"))
                {
                    DoseFinal = Dose1 * 1000000/24;
                }
                if(DoseSpin1.equals("Mcg q2 hr"))
                {
                    DoseFinal = Dose1 * 1000000/12;
                }
                if(DoseSpin1.equals("Mcg q4 hr"))
                {
                    DoseFinal = Dose1 * 1000000/6;
                }
// mcg done

                if(DoseSpin1.equals("Mg BID"))
                {
                    DoseFinal = Dose1 * 1000/2;
                }
                if(DoseSpin1.equals("Mg Daily"))
                {
                    DoseFinal = Dose1 * 1000;
                }
                if(DoseSpin1.equals("Mg QID"))
                {
                    DoseFinal = Dose1 * 1000/4;
                }
                if(DoseSpin1.equals("Mg TID"))
                {
                    DoseFinal = Dose1 * 1000/3;
                }
                if(DoseSpin1.equals("Mg q1 hr"))
                {
                    DoseFinal = Dose1 * 1000/24;
                }
                if(DoseSpin1.equals("Mg q2 hr"))
                {
                    DoseFinal = Dose1 * 1000/12;
                }
                if(DoseSpin1.equals("Mg q4 hr"))
                {
                    DoseFinal = Dose1 * 1000/6;
                }


                int a=3;


                //Change precision


                Dose.setText(String.format("%."+String.valueOf(a)+"f", DoseFinal));
//                Dose.setText(String.valueOf(DoseFinal));

                LiquidDose1 = (DosageFinal * WeightFinal * PerVolumeFinal)/MedAmountFinal;
                if(LiquidDoseSpin1.equals("Ml BID"))
                {
                    LiquidDoseFinal = LiquidDose1/2;
                }
                if(LiquidDoseSpin1.equals("Ml Daily"))
                {
                    LiquidDoseFinal = LiquidDose1;
                }
                if(LiquidDoseSpin1.equals("Ml QID"))
                {
                    LiquidDoseFinal = LiquidDose1/4;
                }
                if(LiquidDoseSpin1.equals("Ml TID"))
                {
                    LiquidDoseFinal = LiquidDose1/3;
                }
                if(LiquidDoseSpin1.equals("Ml q1 hr"))
                {
                    LiquidDoseFinal = LiquidDose1/24;
                }
                if(LiquidDoseSpin1.equals("Ml q2 hr"))
                {
                    LiquidDoseFinal = LiquidDose1/12;
                }
                if(LiquidDoseSpin1.equals("Ml q4 hr"))
                {
                    LiquidDoseFinal = LiquidDose1 / 6;
                }


// ml done

                if(LiquidDoseSpin1.equals("L BID"))
                {
                    LiquidDoseFinal = LiquidDose1/(2  * 1000);
                }
                if(LiquidDoseSpin1.equals("L Daily"))
                {
                    LiquidDoseFinal = LiquidDose1 / 1000;
                }
                if(LiquidDoseSpin1.equals("L QID"))
                {
                    LiquidDoseFinal = LiquidDose1/(4 * 1000);
                }
                if(LiquidDoseSpin1.equals("L TID"))
                {
                    LiquidDoseFinal = LiquidDose1/(3 * 1000);
                }
                if(LiquidDoseSpin1.equals("L q1 hr"))
                {
                    LiquidDoseFinal = LiquidDose1/(24 * 1000);
                }
                if(LiquidDoseSpin1.equals("L q2 hr"))
                {
                    LiquidDoseFinal = LiquidDose1 /(12 * 1000);
                }
                if(LiquidDoseSpin1.equals("L q4 hr"))
                {
                    LiquidDoseFinal = LiquidDose1 /(6 * 1000);
                }
// L done


                LiquidDose.setText(String.format("%."+String.valueOf(a)+"f", LiquidDoseFinal));
//                LiquidDose.setText(String.valueOf(LiquidDoseFinal));

            }
        });



    }


}