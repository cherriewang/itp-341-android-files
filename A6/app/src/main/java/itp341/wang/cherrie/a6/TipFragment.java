package itp341.wang.cherrie.a6;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;


public class TipFragment extends Fragment {
    private static final String TAG = "TipFragment";
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

    public TipFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tip, container, false);

        textViewPercent = (TextView) v.findViewById(R.id.textViewPercentCount);
        textViewTip = (TextView) v.findViewById(R.id.textViewTipCount);
        textViewTotal = (TextView) v.findViewById(R.id.textViewTotalCount);
        textViewPerPerson = (TextView) v.findViewById(R.id.textViewPerPersonCount);
        editTextBill = (EditText) v.findViewById(R.id.billText);
        seekBar = (SeekBar) v.findViewById(R.id.seekBarPercent);
        perPersonLayout = (LinearLayout) v.findViewById(R.id.linearLayout6);
        spinner = (Spinner) v.findViewById(R.id.spinnerFish);

        // Setting listeners

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

        Log.d(TAG, "WE MADE TIP FRAGMENT");
        return v;

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
