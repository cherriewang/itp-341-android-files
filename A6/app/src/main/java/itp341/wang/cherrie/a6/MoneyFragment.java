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
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;


public class MoneyFragment extends Fragment {
    public static final String TAG = MainActivity.class.getSimpleName();

    private EditText editMoneyText;
    private TextView myResult;
    private Spinner fromSpinner;
    private Spinner toSpinner;
    private Button convertButton;
    private double numMoney = 0;
    private double converted = 0;
    private double rate = 1.0;
    private String sourceCurrency = "";
    private String targetCurrency = "";


    public MoneyFragment() {
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
        View v = inflater.inflate(R.layout.fragment_money, container, false);
        editMoneyText = (EditText) v.findViewById(R.id.moneyText);
        myResult = (TextView) v.findViewById(R.id.textViewMoneyResult);
        fromSpinner = (Spinner) v.findViewById(R.id.spinnerFrom);
        toSpinner = (Spinner) v.findViewById(R.id.spinnerTo);
        convertButton = (Button) v.findViewById(R.id.buttonMoneyConvert);

        // Action listener for EditText where user inputs bill
        editMoneyText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // saves the money value
                Log.e(TAG, v.getText().toString());
                numMoney = Double.parseDouble(v.getText().toString());

                //updateResultCount();

                Log.e(TAG, "Did we make it");
                // update tip
                return true;
            }
        });

        // CONVERT BUTTON LISTENER that performs the calculations using what the values are
        // at time of click
        convertButton.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                if (sourceCurrency.equals("USD")) {
                    if (targetCurrency.equals("YUAN")) {
                        rate = 6.51;
                    }
                    if (targetCurrency.equals("EURO")) {
                        rate = 0.90;
                    }
                }
                if (sourceCurrency.equals("YUAN")) {
                    if (targetCurrency.equals("USD")) {
                        rate = 0.15;
                    }
                    if (targetCurrency.equals("EURO")) {
                        rate = 0.14;
                    }
                }
                if (sourceCurrency.equals("EURO")) {
                    if (targetCurrency.equals("USD")) {
                        rate = 1.12;
                    }
                    if (targetCurrency.equals("YUAN")) {
                        rate = 7.27;
                    }
                }
                // calculate from the current selection
                converted = numMoney * rate;
                // update all views
                updateResultCount();
            }
        });

        // Change listener for the Spinner deciding how things get split
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        // USD
                        sourceCurrency = "USD";
                        break;
                    case 1:
                        // YUAN
                        sourceCurrency = "YUAN";
                        break;
                    case 2:
                        // EURO
                        sourceCurrency = "EURO";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // sometimes you need nothing here
            }
        });

        // Change listener for the Spinner deciding how things get split
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        // USD
                        targetCurrency = "USD";
                        break;
                    case 1:
                        // YUAN
                        targetCurrency = "YUAN";
                        break;
                    case 2:
                        // EURO
                        targetCurrency = "EURO";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // sometimes you need nothing here
            }
        });

        Log.d(TAG, "WE MADE MONEY FRAGMENT");
        return v;
    }

    // Changes the text UI displaying the result of money conversion
    private void updateResultCount(){
        // currencies are the same
        if(sourceCurrency.equals(targetCurrency)){
            DecimalFormat twoPlaces = new DecimalFormat("0.00");
            myResult.setText("Result: $"+twoPlaces.format(numMoney));
            return;
        }
        DecimalFormat twoPlaces = new DecimalFormat("0.00");
        myResult.setText("Result: $"+twoPlaces.format(converted));
    }

}
