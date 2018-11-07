package com.elizabethwhitebaker.pgx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AlleleActivity extends DosageCalculator {
    public String poorMet;
    public String normMet;
    public String intMet;

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
        setContentView(R.layout.activity_allele);
        poorMet = (String) getString(R.string.poor_met);
        normMet = (String) getString(R.string.normal_met);
        intMet = (String) getString(R.string.int_met);

        String gene = getIntent().getStringExtra("gene");
        String drug = getIntent().getStringExtra("drug");
        String allele1 = getIntent().getStringExtra("allele1");
        String allele2 = getIntent().getStringExtra("allele2");
        String alleles = allele1 + " " + allele2;

        //TextView header = (TextView) findViewById(R.id.heading_tv);
        TextView metabolizer = (TextView) findViewById(R.id.metabolizer_tv);
        TextView recommendations = (TextView) findViewById(R.id.recs_txt_tv);
        recommendations.setMovementMethod(new ScrollingMovementMethod());
        //header.setText(gene + " and " + drug + " with the alleles " + allele1 + " and " + allele2 + ":");
//        Button button = (Button) findViewById(R.id.button);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(AlleleActivity.this, DosageCalculator.class));
//            }
//        });

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

        switch(gene) {  //for each gene
            case "TPMT (Thiopurine methyltransferase)":
                switch(drug) {  //there are many drugs
                    case "Mercaptopurine":
                    case "6MP":
                    case "Purinethol":
                    case "Purixan":
                    case "6Mercaptopurine":
                        switch (alleles) {  //for which there are many alleles
                            case "*1 *1":
                            case "*1 *1S":
                            case "*1S *1S":
                                metabolizer.setText(normMet);  //normal metabolism
                                recommendations.setText("Start with normal starting dose:\n" +
                                        "* Adult: 2.5 mg/kg daily   (include drug calculator option with each dose)\n" +
                                        "* Pediatric: 1.25-2.5 mg/kg (50-70 mg/m2) daily\n" +
                                        "Allow 2 weeks to reach steady state after each dose adjustment.\n");
                                break;
                            case "*1 *2":
                            case "*1 *3A":
                            case "*1 *3B":
                            case "*1 *3C":
                            case "*1 *4":
                            case "*1S *2":
                            case "*1S *3A":
                            case "*1S *3B":
                            case "*1S *3C":
                            case "*1S *4":
                                metabolizer.setText(intMet);  //intermediate metabolism
                                recommendations.setText("Start with 30-70% reduction from normal starting dose:\n" +
                                        "* Adult: 0.75-1.75 mg/kg daily  (include drug calculator option with each dose)\n" +
                                        "Pediatric: 0.375-1.75 mg/kg (15-49 mg/m2) daily\n" +
                                        "Allow 2-4 weeks to reach steady state after each dose adjustment. \n" +
                                        "In those who require a dosage reduction based on myelosuppression, the median dose may be ~40% lower \n");
                                break;
                            case "*2 *2":
                            case "*2 *3A":
                            case "*2 *3B":
                            case "*2 *3C":
                            case "*2 *4":
                                metabolizer.setText(poorMet);  //poor metabolism
                                recommendations.setText("For malignancy, reduce daily dose by 10-fold from normal starting dose and reduce frequency to thrice weekly instead of daily:\n" +
                                        "* Adult: 0.25 mg/kg Three Times Weekly (include drug calculator option with each dose)\n" +
                                        "* Pediatric: 0.125-0.25 mg/kg (5-7 mg/m2) Three Times Weekly\n" +
                                        "Allow 4-6 weeks to reach steady state after each dose adjustment. \n" +
                                        "For nonmalignant conditions, consider alternative nonthiopurine immunosuppressant therapy.\n");
                                break;
                        }
                    case "Thioguanine":
                    case "6TG":
                    case "6Thioguanine":
                    case "Tabloid":
                        switch (alleles) {
                            case "*1 *1":
                            case "*1 *1S":
                            case "*1S *1S":
                                metabolizer.setText(normMet);  //normal metabolism
                                recommendations.setText("Start with normal starting dose:\n" +
                                        "* Adult and Pediatric: 2 mg/kg/day  (include dosage calculator)\n" +
                                        "Allow 2-4 weeks to reach steady state after each dose adjustment.\n" +
                                        "May cautiously increase to 3 mg/kg/day if no response after 4 weeks\n");
                                break;
                            case "*1 *2":
                            case "*1 *3A":
                            case "*1 *3B":
                            case "*1 *3C":
                            case "*1 *4":
                            case "*1S *2":
                            case "*1S *3A":
                            case "*1S *3B":
                            case "*1S *3C":
                            case "*1S *4":
                                metabolizer.setText(intMet);  //intermediate metabolism
                                recommendations.setText("Start with 30-50% reduction from normal starting dose:\n" +
                                        "1 -1.4 mg/kg daily  (include drug calculator option with each dose)\n" +
                                        "Allow 2-4 weeks to reach steady state after each dose adjustment. \n");
                                break;
                            case "*2 *2":
                            case "*2 *3A":
                            case "*2 *3B":
                            case "*2 *3C":
                            case "*2 *4":
                                metabolizer.setText(poorMet);  //poor metabolism
                                recommendations.setText("Reduce daily dose by 10-fold from normal starting dose and reduce frequency to thrice weekly instead of daily:\n" +
                                        " 0.2 mg/kg Three Times Weekly (include drug calculator option with each dose) \n" +
                                        "Allow 4-6 weeks to reach steady state after each dose adjustment. \n" +
                                        "For nonmalignant conditions, consider alternative nonthiopurine immunosuppressant therapy.\n");
                                break;
                        }
                    case "Azathioprine":
                    case "Azasan":
                    case "Imuran":
                        switch (alleles) {
                            case "*1 *1":
                            case "*1 *1S":
                            case "*1S *1S":
                                metabolizer.setText(normMet);  //normal metabolism
                                recommendations.setText("Start with normal starting dose:\n" +
                                        "Immunosuppressant/Kidney Transplantation  (include dosage calculator) \n" +
                                        "* Adult: 3-5 mg/kg/day\n" +
                                        "Rheumatoid Arthritis\n" +
                                        "* Adult: 1 mg/kg/day\n" +
                                        "Juvenile Idiopathic Arthritis \n" +
                                        "* Pediatric: 1 mg/kg/day\n");
                                break;
                            case "*1 *2":
                            case "*1 *3A":
                            case "*1 *3B":
                            case "*1 *3C":
                            case "*1 *4":
                            case "*1S *2":
                            case "*1S *3A":
                            case "*1S *3B":
                            case "*1S *3C":
                            case "*1S *4":
                                metabolizer.setText(intMet);  //intermediate metabolism
                                recommendations.setText("Start with 30-70% reduction from normal starting dose:\n" +
                                        "Immunosuppressant/Kidney Transplantation \n" +
                                        "Adult: 0.9-3.5 mg/kg daily  (include drug calculator option with each dose)\n" +
                                        "Rheumatoid Arthritis\n" +
                                        "Adult: 0.3-0.7 mg/kg/day\n" +
                                        "Juvenile Idiopathic Arthritis \n" +
                                        "Pediatric: 0.3-0.7 mg/kg/day\n" +
                                        "Allow 2-4 weeks to reach steady state after each dose adjustment. \n");
                                break;
                            case "*2 *2":
                            case "*2 *3A":
                            case "*2 *3B":
                            case "*2 *3C":
                            case "*2 *4":
                                metabolizer.setText(poorMet);  //poor metabolism
                                recommendations.setText("Consider alternate therapy.\n" +
                                        "If you do use, reduce daily dose by 10-fold from normal starting dose and reduce frequency to thrice weekly instead of daily:\n" +
                                        " Immunosuppressant/Kidney Transplantation \n" +
                                        "Adult: 0.3-0.5 mg/kg Three Times Weekly   (include drug calculator option with each dose)\n" +
                                        "Rheumatoid Arthritis\n" +
                                        "Adult: 0.1 mg/kg Three Times Weekly\n" +
                                        "Juvenile Idiopathic Arthritis \n" +
                                        "Pediatric: 0.1 mg/kg Three Times Weekly\n" +
                                        "Allow 4-6 weeks to reach steady state after each dose adjustment. \n");
                                break;
                            //default:
                              //  recommendations.setText("Please select again.");
                        }
                    //default:
                      //  recommendations.setText("Please select again.");
                }

            case "DPYD (Dihydropyrimidine dehydrogenase)":
                switch(drug) {
                    case "XELODA":
                    case "Capecitabine":
                        switch(alleles) {
                            case "*Normal/No variant *Normal/No variant":
                                //metabolizer.setText(DPYD1);
                                break;
                            case "*Normal/No variant *c.295_298delTCAT":
                            case "*Normal/No variant *c.703C>T":
                            case "*Normal/No variant *c.1156G>T":
                            case "*Normal/No variant *c.1679T>G":
                            case "*Normal/No variant *c.1898delC":
                            case "*Normal/No variant *c.1905+1G>T":
                            case "*Normal/No variant *c.2983G>T":
                            case "*c.557A>G *c.1129-5923C>G":
                            case "*c.557A>G *c.2846A>T":
                            case "*c.557A>G *c.557A>G":
                            case "*c.1129-5923C>G *c.1129-5923C>G":
                            case "*c.1129-5923C>G *c.2846A>T":
                            case "*c.2846A>T *c.2846A>T":
                                //metabolizer.setText(DPYD2);
                                break;
                            case "*Normal/No variant *c.557A>G":
                            case "*Normal/No variant *c.2846A>T":
                            case "*Normal/No variant *c.1129-5923C>G":
                                //metabolizer.setText(DPYD3);
                                break;
                            case "*c.295_298delTCAT *c.295.298delTCAT":
                            case "*c.295_298delTCAT *c.703C>T":
                            case "*c.295_298delTCAT *c.1156G>T":
                            case "*c.295_298delTCAT *c.1679T>G":
                            case "*c.295_298delTCAT *c.1898delC":
                            case "*c.295_298delTCAT *c.1905+1G>A":
                            case "*c.295_298delTCAT *c.2983G>T":
                            case "*c.703C>T *c.703C>T":
                            case "*c.703C>T *c.1156G>T":
                            case "*c.703C>T *c.1679T>G":
                            case "*c.703C>T *c.1898delC":
                            case "*c.703C>T *c.1905+1G>A":
                            case "*c.703C>T *c.2983G>T":
                            case "*c.1156G>T *c.1156G>T":
                            case "*c.1156G>T *c.1679T>G":
                            case "*c.1156G>T *c.1898delC":
                            case "*c.1156G>T *c.1905+1G>A":
                            case "*c.1156G>T *c.2983G>T":
                            case "*c.1679T>G *c.1679T>G":
                            case "*c.1679T>G *c.1898delC":
                            case "*c.1679T>G *c.1905+1G>A":
                            case "*c.1679T>G *c.2983G>T":
                            case "*c.1898delC *c.1898delC":
                            case "*c.1898delC *c.1905+1G>A":
                            case "*c.1898delC *c.2983G>T":
                            case "*c.1905+1G>A *c.1905+1G>A":
                            case "*c.1905+1G>A *c.2983G>T":
                                //metabolizer.setText(avoidUse);
                                break;
                            case "*c.295_298delTCAT *c.557A>G":
                            case "*c.295_298delTCAT *c.1129-5923C>G":
                            case "*c.295_298delTCAT *c.2846A>T":
                            case "*c.557A>G *c.703C>T":
                            case "*c.557A>G *c.1156G>T":
                            case "*c.557A>G *c.1679T>G":
                            case "*c.557A>G *c.1898delC":
                            case "*c.557A>G *c.1905+1G>A":
                            case "*c.557A>G *c.2983G>T":
                            case "*c.703C>T *c.1129-5923C>G":
                            case "*c.703C>T *c.2846A>T":
                            case "*c.1129-5923C>G *c.1156G>T":
                            case "*c.1129-5923C>G *c.1679T>G":
                            case "*c.1129-5923C>G *c.1898delC":
                            case "*c.1129-5923C>G *c.1905+1G>A":
                            case "*c.1129-5923C>G *c.2983G>T":
                            case "*c.1156G>T *c.2846A>T":
                            case "*c.1679T>G *c.2846A>T":
                            case "*c.1898delC *c.2846A>T":
                            case "*c.1905+1G>A *c.2846A>T":
                            case "*c.2846A>T *c.2983G>T":
                            case "*c.2983G>T *c.2983G>T":
                                //metabolizer.setText(avoidUse2);
                                break;
                        }
                    case "Fluorouracil":
                    case "Efudex":
                    case "Fluoroplex":
                    case "Adrucil":
                    case "5-fluorouracil":
                    case "5-FU":
                        switch(alleles) {
                            case "*Normal/No variant *Normal/No variant":
                                //metabolizer.setText(DPYD5);
                                break;
                            case "*Normal/No variant *c.295_298delTCAT":
                            case "*Normal/No variant *c.703C>T":
                            case "*Normal/No variant *c.1156G>T":
                            case "*Normal/No variant *c.1679T>G":
                            case "*Normal/No variant *c.1898delC":
                            case "*Normal/No variant *c.1905+1G>T":
                            case "*Normal/No variant *c.2983G>T":
                            case "*c.557A>G *c.1129-5923C>G":
                            case "*c.557A>G *c.2846A>T":
                            case "*c.557A>G *c.557A>G":
                            case "*c.1129-5923C>G *c.1129-5923C>G":
                            case "*c.1129-5923C>G *c.2846A>T":
                            case "*c.2846A>T *c.2846A>T":
                                //metabolizer.setText(DPYD6);
                                break;
                            case "*Normal/No variant *c.557A>G":
                            case "*Normal/No variant *c.2846A>T":
                            case "*Normal/No variant *c.1129-5923C>G":
                                //metabolizer.setText(DPYD7);
                                break;
                            case "*c.295_298delTCAT *c.295.298delTCAT":
                            case "*c.295_298delTCAT *c.703C>T":
                            case "*c.295_298delTCAT *c.1156G>T":
                            case "*c.295_298delTCAT *c.1679T>G":
                            case "*c.295_298delTCAT *c.1898delC":
                            case "*c.295_298delTCAT *c.1905+1G>A":
                            case "*c.295_298delTCAT *c.2983G>T":
                            case "*c.703C>T *c.703C>T":
                            case "*c.703C>T *c.1156G>T":
                            case "*c.703C>T *c.1679T>G":
                            case "*c.703C>T *c.1898delC":
                            case "*c.703C>T *c.1905+1G>A":
                            case "*c.703C>T *c.2983G>T":
                            case "*c.1156G>T *c.1156G>T":
                            case "*c.1156G>T *c.1679T>G":
                            case "*c.1156G>T *c.1898delC":
                            case "*c.1156G>T *c.1905+1G>A":
                            case "*c.1156G>T *c.2983G>T":
                            case "*c.1679T>G *c.1679T>G":
                            case "*c.1679T>G *c.1898delC":
                            case "*c.1679T>G *c.1905+1G>A":
                            case "*c.1679T>G *c.2983G>T":
                            case "*c.1898delC *c.1898delC":
                            case "*c.1898delC *c.1905+1G>A":
                            case "*c.1898delC *c.2983G>T":
                            case "*c.1905+1G>A *c.1905+1G>A":
                            case "*c.1905+1G>A *c.2983G>T":
                                //metabolizer.setText(avoidUse);
                                break;
                            case "*c.295_298delTCAT *c.557A>G":
                            case "*c.295_298delTCAT *c.1129-5923C>G":
                            case "*c.295_298delTCAT *c.2846A>T":
                            case "*c.557A>G *c.703C>T":
                            case "*c.557A>G *c.1156G>T":
                            case "*c.557A>G *c.1679T>G":
                            case "*c.557A>G *c.1898delC":
                            case "*c.557A>G *c.1905+1G>A":
                            case "*c.557A>G *c.2983G>T":
                            case "*c.703C>T *c.1129-5923C>G":
                            case "*c.703C>T *c.2846A>T":
                            case "*c.1129-5923C>G *c.1156G>T":
                            case "*c.1129-5923C>G *c.1679T>G":
                            case "*c.1129-5923C>G *c.1898delC":
                            case "*c.1129-5923C>G *c.1905+1G>A":
                            case "*c.1129-5923C>G *c.2983G>T":
                            case "*c.1156G>T *c.2846A>T":
                            case "*c.1679T>G *c.2846A>T":
                            case "*c.1898delC *c.2846A>T":
                            case "*c.1905+1G>A *c.2846A>T":
                            case "*c.2846A>T *c.2983G>T":
                            case "*c.2983G>T *c.2983G>T":
                                //metabolizer.setText(avoidUse2);
                                break;
                        }
                }
        }
    }
}


