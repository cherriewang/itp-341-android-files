package itp341.wang.cherrie.a6;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class UnitFragment extends Fragment {
    public static final String TAG = MainActivity.class.getSimpleName();

    private RadioGroup fromGroup;
    private RadioGroup toGroup;
    private TextView myResult;
    private String sourceUnit;
    private String targetUnit;
    private double startValue = 0.0;
    private double converted = 0.0;
    private Button convertButton;
    private double rate = 0.0;
    private EditText editUnitText;

    public UnitFragment() {
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
        Log.d(TAG, "WE MADE UNIT FRAGMENT");
        View v = inflater.inflate(R.layout.fragment_unit, container, false);
        fromGroup = (RadioGroup) v.findViewById(R.id.radioFrom);
        toGroup = (RadioGroup) v.findViewById(R.id.radioTo);
        myResult = (TextView) v.findViewById(R.id.textViewMoneyResult);
        convertButton = (Button) v.findViewById(R.id.buttonMoneyConvert);
        editUnitText = (EditText) v.findViewById(R.id.unitText);

        // Action listener for EditText where user inputs bill
        editUnitText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // saves the money value
                Log.e(TAG, v.getText().toString());
                startValue = Double.parseDouble(v.getText().toString());

                Log.e(TAG, "Did we make it");
                return true;
            }
        });


        // gets input from the FROM RADIO BUTTON
        fromGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                RadioButton r = (RadioButton) group.findViewById(checkedId);
                sourceUnit = r.getText().toString();
                Log.d(TAG, sourceUnit);
            }
        });

        // gets input from the TO RADIO BUTTON
        toGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                RadioButton r = (RadioButton) group.findViewById(checkedId);
                targetUnit = r.getText().toString();
                Log.d(TAG, targetUnit);
            }
        });

        // CONVERT BUTTON LISTENER that performs the calculations using what the values are
        // at time of click
        convertButton.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                // CM TO ______
                if (sourceUnit.equals("centimeters")) {
                    if (targetUnit.equals("meters")) {
                        rate = 0.01;
                    }
                    if (targetUnit.equals("feet")) {
                        rate = 0.0328;
                    }
                    if (targetUnit.equals("miles")) {
                        rate = 0.00000621;
                    }
                    if (targetUnit.equals("kilometers")) {
                        rate = 0.00001;
                    }
                }
                // METERS TO ______
                if (sourceUnit.equals("meters")) {
                    if (targetUnit.equals("centimeters")) {
                        rate = 100;
                    }
                    if (targetUnit.equals("feet")) {
                        rate = 3.2808;
                    }
                    if (targetUnit.equals("miles")) {
                        rate = 0.000621;
                    }
                    if (targetUnit.equals("kilometers")) {
                        rate = 0.01;
                    }
                }
                // FEET TO _______
                if (sourceUnit.equals("feet")) {
                    if (targetUnit.equals("centimeters")) {
                        rate = 30.48;
                    }
                    if (targetUnit.equals("meters")) {
                        rate = 0.3048;
                    }
                    if (targetUnit.equals("miles")) {
                        rate = 0.000189;
                    }
                    if (targetUnit.equals("kilometers")) {
                        rate = 0.000304;
                    }
                }
                // MILES TO _______
                if (sourceUnit.equals("miles")) {
                    if (targetUnit.equals("centimeters")) {
                        rate = 160934;
                    }
                    if (targetUnit.equals("meters")) {
                        rate = 1609.34;
                    }
                    if (targetUnit.equals("feet")) {
                        rate = 5280;
                    }
                    if (targetUnit.equals("kilometers")) {
                        rate = 1.60934;
                    }
                }
                // KM TO _______
                if (sourceUnit.equals("kilometers")) {
                    if (targetUnit.equals("centimeters")) {
                        rate = 100000;
                    }
                    if (targetUnit.equals("meters")) {
                        rate = 1000;
                    }
                    if (targetUnit.equals("feet")) {
                        rate = 3280.84;
                    }
                    if (targetUnit.equals("miles")) {
                        rate = 0.62137;
                    }
                }
                // calculate from the current selection
                converted = startValue * rate;
                // update all views
                updateResultCount();
            }
        });

        return v;
    }
    // Changes the text UI displaying the result of money conversion
    private void updateResultCount(){
        // currencies are the same
        if(sourceUnit.equals(targetUnit)){
            myResult.setText("Result: "+startValue);
            return;
        }
        myResult.setText("Result: "+converted);
    }


}
