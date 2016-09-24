package itp341.wang.cherrie.a5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    private TextView textViewPercent;
    private TextView textViewTip;
    private TextView textViewTotal;
    private TextView textViewPerPerson;
    private EditText editTextBill;
    private SeekBar seekBar;
    private LinearLayout perPersonLayout;
    private Spinner spinner;

    private double numBill = 0;
    private int numPercent = 15;
    private double numWays = 0;
    private double numTip = 0;
    private double numTotal = 0;
    private double numPerPerson = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPercent = (TextView) findViewById(R.id.textViewPercentCount);
        textViewTip = (TextView) findViewById(R.id.textViewTipCount);
        textViewTotal = (TextView) findViewById(R.id.textViewTotalCount);
        textViewPerPerson = (TextView) findViewById(R.id.textViewPerPersonCount);
        editTextBill = (EditText) findViewById(R.id.billText);
        seekBar = (SeekBar) findViewById(R.id.seekBarPercent);
        perPersonLayout = (LinearLayout) findViewById(R.id.linearLayout6);
        spinner = (Spinner) findViewById(R.id.spinnerFish);

        // Action listener for EditText where user inputs bill
        editTextBill.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    // saves the bill value
                    Log.e(TAG, v.getText().toString());
                    numBill = Double.parseDouble(v.getText().toString());
                    numTip = numBill*((double)numPercent/100);
                    numTotal = numBill+numTip;
                    updateTipCount();
                    updateTotalCount();
                    Log.e(TAG, "Did we make it");
                    // update tip
                    return true;
            }
        });

        // Change listener for SeekBar where user changes percentage
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // update the percent
                numPercent = progress;
                numTip = numBill*((double) numPercent/100);
                numTotal = numBill+numTip;
                updatePercentCount();
                updateTipCount();
                updateTotalCount();
                updatePerPersonCount();
            }
        });

        // Change listener for the Spinner deciding how things get split
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        perPersonLayout.setVisibility(View.GONE);
                        numWays = 0;
                        break;
                    case 1:
                        perPersonLayout.setVisibility(View.VISIBLE);
                        numWays = 2;
                        numPerPerson = numTotal/numWays;
                        updatePerPersonCount();
                        break;
                    case 2:
                        perPersonLayout.setVisibility(View.VISIBLE);
                        numWays = 3;
                        numPerPerson = numTotal/numWays;
                        updatePerPersonCount();
                        break;
                    case 3:
                        perPersonLayout.setVisibility(View.VISIBLE);
                        numWays = 4;
                        numPerPerson = numTotal/numWays;
                        updatePerPersonCount();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // sometimes you need nothing here
            }
        });

    }

    // Changes the text UI displaying percent selected
    private void updatePercentCount(){
        textViewPercent.setText(numPercent + "%");
    }

    // Changes the text UI displaying calculated tip based on selected percentage and bill input
    private void updateTipCount(){
        DecimalFormat twoPlaces = new DecimalFormat("0.00");
        textViewTip.setText("$"+twoPlaces.format(numTip));
    }

    // Changes the text UI displaying total bill based on tip percentage and bill input
    private void updateTotalCount(){
        DecimalFormat twoPlaces = new DecimalFormat("0.00");
        textViewTotal.setText("$"+twoPlaces.format(numTotal));
    }

    // Changes the text UI displaying cost per person
    private void updatePerPersonCount(){
        numPerPerson = numTotal/numWays;
        DecimalFormat twoPlaces = new DecimalFormat("0.00");
        Log.e(TAG, "Changing per person UI");
        if (perPersonLayout.getVisibility() == View.VISIBLE) {
            // Its visible
            Log.e(TAG, "Visible");
            textViewPerPerson.setText("$"+twoPlaces.format(numPerPerson));
        } else {
            Log.e(TAG, "Not visible");
        }
    }
}
