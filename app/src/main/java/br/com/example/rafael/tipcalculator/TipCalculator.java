package br.com.example.rafael.tipcalculator;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class TipCalculator extends AppCompatActivity {

    private static final String BILL_TOTAL = "BILL_TOTAL";
    private static final String CUSTOM_PERCENT = "CUSTOM_PERCENT";

    private double currentBillTotal;
    private int currentCustomPercent;
    private EditText tip10EditText;
    private EditText total10EditText;
    private EditText tip15EditText;
    private EditText total15EditText;
    private EditText billEditText;
    private EditText tip20EditText;
    private EditText total20EditText;
    private TextView customTipTextView;
    private EditText tipCustomEditText;
    private EditText totalCustomEditText;
    //SeekBar.OnSeekBarChangeListener customSeekBarListener;
    //private TextWatcher billEditTextWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        if (savedInstanceState == null) {
            currentBillTotal = 0.0;
            currentCustomPercent = 18;
        } else {

            currentBillTotal = savedInstanceState.getDouble(BILL_TOTAL);

            currentCustomPercent = savedInstanceState.getInt(CUSTOM_PERCENT);
        }

        tip10EditText = (EditText) findViewById(R.id.tip10EditText);
        total10EditText = (EditText) findViewById(R.id.total10EditText);
        tip15EditText = (EditText) findViewById(R.id.tip15EditText);
        total15EditText = (EditText) findViewById(R.id.total15EditText);
        tip20EditText = (EditText) findViewById(R.id.tip20EditText);
        total20EditText = (EditText) findViewById(R.id.total20EditText);

        customTipTextView = (TextView) findViewById(R.id.customTipTextView);

        tipCustomEditText = (EditText) findViewById(R.id.tipCustomEditText);
        totalCustomEditText = (EditText) findViewById(R.id.totalCustomEditText);

        billEditText = (EditText) findViewById(R.id.billEditText);

        billEditText.addTextChangedListener(billEditTextWatcher);

        SeekBar customSeekBar = (SeekBar) findViewById(R.id.customSeekBar);
        customSeekBar.setOnSeekBarChangeListener(customSeekBarListener);
    }
        private void updateStandard(){

        double tenPercentTip = currentBillTotal * .1;
        double tenPercentTotal = currentBillTotal + tenPercentTip;

        tip10EditText.setText(String.format("%.02f", tenPercentTip));

        total10EditText.setText(String.format("%.02f", tenPercentTotal));

        double fiftenPercentTip = currentBillTotal * .15;
        double fiftenPercentTotal = currentBillTotal + fiftenPercentTip;

        tip15EditText.setText(String.format("%.02f", fiftenPercentTip));

        total15EditText.setText(String.format("%.02f", fiftenPercentTotal));

        double twentyPercentTip = currentBillTotal * .20;
        double twentyPercentTotal = currentBillTotal + twentyPercentTip;

        tip20EditText.setText(String.format("%.02f", twentyPercentTip));

        total20EditText.setText(String.format("%.02f", twentyPercentTotal));



    }

    private void updateCustom(){

        customTipTextView.setText(currentCustomPercent + "%");

        double customTipAmount = currentBillTotal * currentCustomPercent * .01;

        double customTotalAmount = currentBillTotal + customTipAmount;

        tipCustomEditText.setText(String.format("%.02f", customTipAmount));
        totalCustomEditText.setText(String.format("%.02f", customTotalAmount));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putDouble(BILL_TOTAL,currentBillTotal);
        outState.putInt(CUSTOM_PERCENT,currentCustomPercent);
    }

    private SeekBar.OnSeekBarChangeListener customSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            currentCustomPercent = seekBar.getProgress();
            updateCustom();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private TextWatcher billEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try {
                currentBillTotal = Double.parseDouble(s.toString());
            }catch (NumberFormatException e)
            {
                currentBillTotal = 0.0;
            }
            updateStandard();
            updateCustom();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
