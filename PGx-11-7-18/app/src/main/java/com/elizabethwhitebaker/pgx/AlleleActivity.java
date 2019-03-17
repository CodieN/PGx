//AlleleActivity is what drives the dosage calculator and all of its calculations.
package com.elizabethwhitebaker.pgx;

// These are all of the import statements
// Imports the intent made on the DosageGuidelines page
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
// Imports the ability to bundle content
import android.graphics.Color;
import android.os.Bundle;
// Imports the ability to use HTML elements
import android.text.Html;
// Imports the ability to scroll textviews
import android.text.method.ScrollingMovementMethod;
// Imports the ability to do onClick methods and view them
// real time
import android.view.View;
// Imports the ability to control buttons
import android.widget.Button;
// Imports the ability to control editTexts
import android.widget.EditText;
// Imports the ability to control spinner elements
import android.widget.Spinner;
// Imports the ability to control TextViews
import android.widget.TextView;
import android.widget.Toast;

// The Allele activity class inherits from the DosingGuidelines
// so it can get the spinner items for the textview recommendation
// text choice
public class AlleleActivity extends DosingGuidelines {
    //variables for this entire page

    // the are the text for the metabolizer textview so the user
    // can know if the patient is a poor, intermediate, or normal
    // metabolizer of the drug selected on the DosageGuidelines
    // page
    public String poorMet;
    public String normMet;
    public String intMet;
    // text fields the user can edit
    // the dosage is how much the user is prescribing to the patient
    EditText Dosage;
    // the weight field is for the weight of the patient,... not the doctor
    EditText Weight;
    // the med amount edittext is for the liquid dosage amount
    EditText MedAmount;
    // the per volume field is for entering the amount of meds per volume
    // for the patient
    EditText PerVolume;

    // the spinning menus that the user can pick units from

    // this spinner is for choosing the units for the dosage
    Spinner DosageSpin;
    // this spinner is for the weight units
    Spinner WeightSpin;
    // this spinner is for the liquid med amount units
    Spinner MedAmountSpin;
    // this spinner is for the liquid med per volume units
    Spinner PerVolumeSpin;
    // this spinner is for selecting the units the resulting med dosage
    // should be in
    Spinner DoseSpin;
    // this spinner is for selecting the units the resulting liquid med
    // dosage should be in
    Spinner LiquidDoseSpin;
    // gets the selection of the user to see what decimal precision they want the results to be in
    Spinner DecimalPrecSpin;

    // this is what holds the results after the calculations are done

    // this textview is used to display the results of the dosage calculator
    TextView Dose;
    // this textview is used to display the result of the liquid calculations
    // of the dosage calculator
    TextView LiquidDose;

    //intent is what brings the drug, alleles, and gene chosen from the other Activity pages
    //into this one, so the calculations can be done on the drug, gene, and alleles that the
    //user selected.
    Intent intent;

    // This overrides the DosageGuidelines' page's onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // this sets the page that we are programming which is the activity_allele.xml page
        setContentView(R.layout.activity_allele);

        DosageSpin = (Spinner)findViewById(R.id.spinner);
        WeightSpin = (Spinner)findViewById(R.id.spinner2);
        MedAmountSpin = (Spinner)findViewById(R.id.spinner3);
        PerVolumeSpin = (Spinner)findViewById(R.id.spinner4);
        DoseSpin = (Spinner)findViewById(R.id.spinner6);
        LiquidDoseSpin = (Spinner)findViewById(R.id.spinner7);
        DecimalPrecSpin = (Spinner)findViewById(R.id.decimal_prec);

        DoseSpin.setSelection(15);
        LiquidDoseSpin.setSelection(1);
        DecimalPrecSpin.setSelection(2);
        MedAmountSpin.setSelection(2);

        // these set the string values for the metabolizer

        // poorMet is defined in the xml so this captures that string literal
        poorMet = (String) getString(R.string.poor_met);
        // normalMet is defined in the xml so this captures that string literal
        normMet = (String) getString(R.string.normal_met);
        // intMet is defined in the xml so this captures that string literal
        intMet = (String) getString(R.string.int_met);

        // this statement is what gets the values of the gene, drug, and alleles from the other
        // activity pages
        intent = getIntent();

        // these statements set string variables equal to the individual selections the user made
        // on previous pages

        // this gets the gene the user selected on the GetStartedActivity page that was
        // passed to the DosageGuidelines page with intent and passed to this page with intent
        String gene = intent.getStringExtra("gene"); //gene
        // this gets the drug the user selected on the GetStartedActivity page
        String drug = intent.getStringExtra("drug"); //drug
        // the alleles are passed from the DosageGuidelines page with intent
        String allele1 = intent.getStringExtra("allele1"); //alleles
        String allele2 = intent.getStringExtra("allele2");
        //this value is used in the long nested switch statement at the bottom
        String alleles = allele1 + " " + allele2;

        //this textview uses the values from poorMet, intMet, and normMet (above) to populate
        //itself with text depending on the gene and drug and alleles chosen (figured out in
        //the nested switch statement below)
        TextView metabolizer = (TextView) findViewById(R.id.metabolizer_tv);
        //this textview uses the nested switch statement below to figure out which recommendation
        //is correct for the gene, alleles, and drug the user selected
        TextView recommendations = (TextView) findViewById(R.id.recs_txt_tv);
        //this allows for the textview to be scrollable, so the user can see all of the
        //recommendation text
        recommendations.setMovementMethod(new ScrollingMovementMethod());
        //defines the calculate button
        final Button Calculate = (Button)findViewById(R.id.button2);

        //sets a method that listens for the calculate button to be tapped
        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finds all the controls from the XML layout and sets them equal to variables so
                //it's easier to manipulate and calculate with the contents of the controls
                Dosage = (EditText) findViewById(R.id.editText);
                Weight = (EditText) findViewById(R.id.editText2);
                MedAmount = (EditText) findViewById(R.id.editText3);
                PerVolume = (EditText) findViewById(R.id.editText4);
                Dose = (TextView) findViewById(R.id.textView6);
                LiquidDose = (TextView) findViewById(R.id.textView8);

                //sets all of the calculated results to 0
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
                //gets all of the units the user wants the calculation to be converted to
                String DosageSpin1 = DosageSpin.getSelectedItem().toString();
                String WeightSpin1 = String.valueOf(WeightSpin.getSelectedItem());
                String MedAmountSpin1 = String.valueOf(MedAmountSpin.getSelectedItem());
                String PerVolumeSpin1 = String.valueOf(PerVolumeSpin.getSelectedItem());
                String DoseSpin1 = String.valueOf(DoseSpin.getSelectedItem());
                String LiquidDoseSpin1 = String.valueOf(LiquidDoseSpin.getSelectedItem());

                if (Dosage.getText().toString().equals("")) {
                    AlertDialog alertDialog = new AlertDialog.Builder(
                            Calculate.getContext()).create();
                    alertDialog.setTitle("Type Dosage Value");
                    alertDialog.setMessage("Type a value for the dosage amount the patient is being prescribed please.");
                    alertDialog.show();
                } else if (Weight.getText().toString().equals("")) {
                    AlertDialog alertDialog = new AlertDialog.Builder(
                            Calculate.getContext()).create();
                    alertDialog.setTitle("Type Weight Value");
                    alertDialog.setMessage("Type a value for the weight amount of the patient please.");
                    alertDialog.show();
                } else {
                    //trys to see if the user entered numbers instead of letters
                    try {
                        Dosage1 = Double.parseDouble(String.valueOf(Dosage.getText()));
                    } catch (Exception e) {
                    }
                    try {
                        Weight1 = Double.parseDouble(String.valueOf(Weight.getText()));
                    } catch (Exception e) {
                    }

                    //conversions based on the units the user chooses from the spinners
                    switch (DosageSpin1.toString()) {
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

                    switch (WeightSpin1.toString()) {
                        case "kg":
                            WeightFinal = Weight1;
                            break;
                        case "lbs":
                            WeightFinal = Weight1 * 0.45359237;
                            break;
                    }

                    //this calculation needs to be made before we move on with other calculations
                    Dose1 = DosageFinal * WeightFinal;
                    //more unit conversions
                    switch (DoseSpin1.toString()) {
                        case "gm BID":
                            DoseFinal = Dose1 / 2000;
                            break;
                        case "gm Daily":
                            DoseFinal = Dose1 / 1000;
                            break;
                        case "gm QID":
                            DoseFinal = Dose1 / 4000;
                            break;
                        case "gm TID":
                            DoseFinal = Dose1 / 3000;
                            break;
                        case "gm q1 hr":
                            DoseFinal = Dose1 / 24000;
                            break;
                        case "gm q2 hr":
                            DoseFinal = Dose1 / 12000;
                            break;
                        case "gm q4 hr":
                            DoseFinal = Dose1 / 6000;
                            break;
                        case "mcg BID":
                            DoseFinal = Dose1 / 0.002;
                            break;
                        case "mcg Daily":
                            DoseFinal = Dose1 / 0.001;
                            break;
                        case "mcg QID":
                            DoseFinal = Dose1 / 0.004;
                            break;
                        case "mcg TID":
                            DoseFinal = Dose1 / 0.003;
                            break;
                        case "mcg q1 hr":
                            DoseFinal = Dose1 / 0.024;
                            break;
                        case "mcg q2 hr":
                            DoseFinal = Dose1 / 0.012;
                            break;
                        case "mcg q4 hr":
                            DoseFinal = Dose1 / 0.006;
                            break;
                        case "mg BID":
                            DoseFinal = Dose1 / 2;
                            break;
                        case "mg Daily":
                            DoseFinal = Dose1;
                            break;
                        case "mg QID":
                            DoseFinal = Dose1 / 4;
                            break;
                        case "mg TID":
                            DoseFinal = Dose1 / 3;
                            break;
                        case "mg q1 hr":
                            DoseFinal = Dose1 / 24;
                            break;
                        case "mg q2 hr":
                            DoseFinal = Dose1 / 12;
                            break;
                        case "mg q4 hr":
                            DoseFinal = Dose1 / 6;
                            break;
                    }

                    //this sets a textview's text equal to the result of the calculation
                    Dose.setText(String.format("%." + String.valueOf(DecimalPrecSpin.getSelectedItem().toString()) + "f", DoseFinal));
                }

                if (MedAmount.getText().toString().equals("") && !PerVolume.getText().toString().equals("")) {
                    AlertDialog alertDialog = new AlertDialog.Builder(
                            Calculate.getContext()).create();
                    alertDialog.setTitle("Type Med Amount Value");
                    alertDialog.setMessage("Type a value for the liquid medication amount given to the patient please.");
                    alertDialog.show();
                } else if (!MedAmount.getText().toString().equals("") && PerVolume.getText().toString().equals("")) {
                    AlertDialog alertDialog = new AlertDialog.Builder(
                            Calculate.getContext()).create();
                    alertDialog.setTitle("Type Per Volume Value");
                    alertDialog.setMessage("Type a value for the per volume amount of the drug please.");
                    alertDialog.show();
                } else if (!MedAmount.getText().toString().equals("") && !PerVolume.getText().toString().equals("")) {
                    try {
                        MedAmount1 = Double.parseDouble(String.valueOf(MedAmount.getText()));
                    } catch (Exception e) {
                    }
                    try {
                        PerVolume1 = Double.parseDouble(String.valueOf(PerVolume.getText()));
                    } catch (Exception e) {
                    }

                    switch (MedAmountSpin1.toString()) {
                        case "gm":
                            MedAmountFinal = MedAmount1 * 1000000000;
                            break;
                        case "mcg":
                            MedAmountFinal = MedAmount1 * 1000;
                            break;
                        case "mg":
                            MedAmountFinal = MedAmount1 * 1000000;
                            break;
                    }

                    switch (PerVolumeSpin1.toString()) {
                        case "mL":
                            PerVolumeFinal = PerVolume1 * 1000;
                            break;
                        case "L":
                            PerVolumeFinal = PerVolume1 * 1000000;
                            break;
                    }

                    //calculations to get a liquid dose
                    LiquidDose1 = (Dose1 * PerVolumeFinal) / MedAmountFinal;
                    //more unit conversions
                    switch (LiquidDoseSpin1.toString()) {
                        case "mL BID":
                            LiquidDoseFinal = LiquidDose1 * 2000;
                            break;
                        case "mL Daily":
                            LiquidDoseFinal = LiquidDose1 * 1000;
                            break;
                        case "mL QID":
                            LiquidDoseFinal = LiquidDose1 * 4000;
                            break;
                        case "mL TID":
                            LiquidDoseFinal = LiquidDose1 * 3000;
                            break;
                        case "mL q1 hr":
                            LiquidDoseFinal = LiquidDose1 * 24000;
                            break;
                        case "mL q2 hr":
                            LiquidDoseFinal = LiquidDose1 * 12000;
                            break;
                        case "mL q4 hr":
                            LiquidDoseFinal = LiquidDose1 * 6000;
                            break;
                        case "L BID":
                            LiquidDoseFinal = LiquidDose1 * 2;
                            break;
                        case "L Daily":
                            LiquidDoseFinal = LiquidDose1;
                            break;
                        case "L QID":
                            LiquidDoseFinal = LiquidDose1 * 4;
                            break;
                        case "L TID":
                            LiquidDoseFinal = LiquidDose1 * 3;
                            break;
                        case "L q1 hr":
                            LiquidDoseFinal = LiquidDose1 * 24;
                            break;
                        case "L q2 hr":
                            LiquidDoseFinal = LiquidDose1 * 12;
                            break;
                        case "L q4 hr":
                            LiquidDoseFinal = LiquidDose1 * 6;
                            break;
                    }
                    //sets the textview's text equal to the calculation result for liquid dose
                    LiquidDose.setText(String.format("%." + String.valueOf(DecimalPrecSpin.getSelectedItem().toString()) + "f", LiquidDoseFinal));
                }
            }
        });

        //this is the long nested switch statement that uses previously selected items to produce
        //the reccomendation text to display in the reccomendations textview
        switch(gene) {  //pick the gene
            case "TPMT (Thiopurine methyltransferase)":
                switch(drug) {  //pick the drug
                    case "Mercaptopurine":
                    case "6MP":
                    case "Purinethol":
                    case "Purixan":
                    case "6Mercaptopurine":
                        switch (alleles) {  //pick the alleles
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
                            default:  //this is if a drug isn't selected
                                recommendations.setText("Please select again.");
                        }
                        break;
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
                                        "0.2 mg/kg Three Times Weekly (include drug calculator option with each dose) \n" +
                                        "Allow 4-6 weeks to reach steady state after each dose adjustment. \n" +
                                        "For nonmalignant conditions, consider alternative nonthiopurine immunosuppressant therapy.\n");
                                break;
                            default:  //this is if a drug isn't selected
                                recommendations.setText("Please select again.");
                        }
                        break;
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
                            default:  //this is used when a drug hasn't been selected
                                recommendations.setText("Please select again.");
                        }
                        break;
                    default:  //this is used when allele(s) haven't been selected
                        recommendations.setText("Please select again.");
                }
                break;

            case "DPYD (Dihydropyrimidine dehydrogenase)":
                switch(drug) {
                    case "XELODA":
                    case "Capecitabine":
                        switch(alleles) {
                            case "*Normal/No variant *Normal/No variant":
                                metabolizer.setText(normMet);
                                recommendations.setText("- Monotherapy (Metastatic Colorectal Cancer, Adjuvant Colorectal Cancer, " +
                                        "Metastatic Breast Cancer)\n" +
                                        "- The recommended dose of XELODA is 1250 " + Html.fromHtml("mg/m<sup>2</sup>") +" administered " +
                                        "orally twice daily (morning and evening; " +
                                        "equivalent to 2500 " + Html.fromHtml("mg/m<sup>2</sup>") +" total daily dose) " +
                                        "for 2 weeks followed by a 1-week rest period given as 3-week cycles.");
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
                                metabolizer.setText(intMet);
                                recommendations.setText("Reduce starting dose by 50% followed by titration " +
                                        "of dose based on toxicity (increase the dose in patients " +
                                        "experiencing no or clinically tolerable toxicity in the first " +
                                        "two cycles to maintain efficacy; decrease the dose in patients " +
                                        "who do not tolerate the starting dose to minimize toxicities) " +
                                        "or therapeutic drug monitoring (if available).\n" +
                                        "Start with 625 " + Html.fromHtml("mg/m<sup>2</sup>") +
                                        " administered orally twice daily " +
                                        "(morning and evening; equivalent to 1250 " +
                                        Html.fromHtml("mg/m<sup>2</sup>") +" total daily dose) " +
                                        "for 2 weeks followed by a 1-week rest period given as 3-week cycles.");
                                break;
                            case "*Normal/No variant *c.557A>G":
                            case "*Normal/No variant *c.2846A>T":
                            case "*Normal/No variant *c.1129-5923C>G":
                                metabolizer.setText(intMet);
                                recommendations.setText("Reduce starting dose by 25% to 50% followed by titration " +
                                        "of dose based on toxicity (increase the dose in patients " +
                                        "experiencing no or clinically tolerable toxicity in the first " +
                                        "two cycles to maintain efficacy; decrease the dose in patients who " +
                                        "do not tolerate the starting dose to minimize toxicities) or " +
                                        "therapeutic drug monitoring (if available).\n" +
                                        "Start with 625-937.5 " + Html.fromHtml("mg/m<sup>2</sup>") +" administered " +
                                        "orally twice daily (morning and evening; equivalent to 1250-1875 " +
                                        Html.fromHtml("mg/m<sup>2</sup>") +" total daily dose) " +
                                        "for 2 weeks followed by a 1-week rest period given as 3-week cycles.");
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
                                metabolizer.setText(poorMet);
                                recommendations.setText("Avoid use of this drug.");
                                recommendations.setTextColor(Color.RED);
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
                                metabolizer.setText(poorMet);
                                recommendations.setText("- Avoid use of this drug. In the event, based on clinical " +
                                        "advice, alternative agents are not considered a suitable " +
                                        "therapeutic option, 5-fluorouracil should be administered " +
                                        "at a strongly reduced dose* with early therapeutic drug monitoring.\n" +
                                        "* A dose of <25% of the normal starting dose is estimated " +
                                        "assuming additive effects of alleles on 5-FU clearance.");
                                break;
                            default:
                                recommendations.setText("Please select again.");
                        }
                        break;
                    case "Fluorouracil":
                    case "Efudex":
                    case "Fluoroplex":
                    case "Adrucil":
                    case "5-fluorouracil":
                    case "5-FU":
                        switch(alleles) {
                            case "*Normal/No variant *Normal/No variant":
                                metabolizer.setText(normMet);
                                recommendations.setText("Keep normal recommended dosage.");
                                recommendations.setTextColor(Color.RED);
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
                                metabolizer.setText(intMet);
                                recommendations.setText("Reduce starting dose by 50% followed by " +
                                        "titration of dose based on toxicity (increase the dose " +
                                        "in patients experiencing no or clinically tolerable " +
                                        "toxicity in the first two cycles to maintain efficacy; " +
                                        "decrease the dose in patients who do not tolerate the " +
                                        "starting dose to minimize toxicities) or therapeutic " +
                                        "drug monitoring (if available).");
                                break;
                            case "*Normal/No variant *c.557A>G":
                            case "*Normal/No variant *c.2846A>T":
                            case "*Normal/No variant *c.1129-5923C>G":
                                metabolizer.setText(intMet);
                                recommendations.setText("Reduce starting dose by 25% to 50% " +
                                        "followed by titration of dose based on toxicity " +
                                        "(increase the dose in patients experiencing no or " +
                                        "clinically tolerable toxicity in the first two cycles to " +
                                        "maintain efficacy; decrease the dose in patients who do " +
                                        "not tolerate the starting dose to minimize toxicities) " +
                                        "or therapeutic drug monitoring (if available).");
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
                                metabolizer.setText(poorMet);
                                recommendations.setText("Avoid use of this drug.");
                                recommendations.setTextColor(Color.RED);
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
                                metabolizer.setText(poorMet);
                                recommendations.setText("- Avoid use of this drug. In the event, based on clinical " +
                                        "advice, alternative agents are not considered a suitable " +
                                        "therapeutic option, 5-fluorouracil should be administered " +
                                        "at a strongly reduced dose* with early therapeutic drug monitoring.\n" +
                                        "* A dose of <25% of the normal starting dose is estimated " +
                                        "assuming additive effects of alleles on 5-FU clearance.");
                                break;
                            default:
                                recommendations.setText("Please select again.");
                        }
                        break;
                    default:
                        recommendations.setText("Please select again.");
                }
                break;

            default:
                recommendations.setText("Please select again.");
        }
    }
}


