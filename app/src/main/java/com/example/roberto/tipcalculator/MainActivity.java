/*Credits: Android for Programmers: An App-Driven Approach, 2/e, Volume 1*/

package com.example.roberto.tipcalculator;


import android.app.Activity;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;



public class MainActivity extends Activity {

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.ITALY);
    private static final NumberFormat percentageFormat = NumberFormat.getPercentInstance(Locale.ITALY);


    private double billAmount=0.0;
    private TextView amountDisplayTextView;

    private double customPercentage = 0.18;



    private TextView tip15TextView;
    private TextView total15TextView;

    private TextView percentageCustomTextView;
    private TextView tipCustomTextView;
    private TextView totalCustomTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        go();
    }

        private void go () {
        amountDisplayTextView = (TextView) findViewById(R.id.amountDisplayTextView);
        tip15TextView = (TextView) findViewById(R.id.tip15TextView);
        total15TextView = (TextView) findViewById(R.id.total15TextView);

        percentageCustomTextView = (TextView) findViewById(R.id.percentageCustomTextView);
        percentageCustomTextView = (TextView) findViewById(R.id.percentageCustomTextView);
        tipCustomTextView = (TextView) findViewById(R.id.tipCustomTextView);
        totalCustomTextView = (TextView) findViewById(R.id.totalCustomTextView);


        //Update GUI
        amountDisplayTextView.setText(currencyFormat.format(billAmount));
        updateStandard();
        updateCustom();

        EditText amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);


        SeekBar customTipSeekBar = (SeekBar) findViewById(R.id.customSeekBar);
        customTipSeekBar.setOnSeekBarChangeListener(customSeekBarListener);


    } //end go



    private void updateStandard(){
        double fifteenPercentTip = billAmount*0.15;
        double fifteenPercentTotal = billAmount + fifteenPercentTip;
        tip15TextView.setText(currencyFormat.format(fifteenPercentTip));
        total15TextView.setText(currencyFormat.format(fifteenPercentTotal));
    }

    private void updateCustom(){

        percentageCustomTextView.setText(percentageFormat.format(customPercentage));

        double customTip = billAmount*customPercentage;
        double customTotal = billAmount + customTip;
        tipCustomTextView.setText(currencyFormat.format(customTip));
        totalCustomTextView.setText(currencyFormat.format(customTotal));
    }



    private TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

                if ((s!=null) && (s.length()>0)){
                billAmount = Double.parseDouble(s.toString())/100;
                amountDisplayTextView.setText(currencyFormat.format(billAmount));
                updateCustom();
                updateStandard();
                }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private SeekBar.OnSeekBarChangeListener customSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            customPercentage = progress/100.0;
            updateCustom();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
