package itp341.wang.cherrie.a6;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    Button buttonTipCalc;
    Button buttonUnitConv;
    Button buttonMoneyExch;

    boolean loadMoney = false;
    boolean loadTip = true;
    boolean loadUnit = false;
    int fragmentSwitch = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonTipCalc = (Button) findViewById(R.id.buttonTip);
        buttonUnitConv = (Button) findViewById(R.id.buttonUnit);
        buttonMoneyExch = (Button) findViewById(R.id.buttonMoney);

        FragmentManager fm = getSupportFragmentManager();
        Fragment sampleFragment = fm.findFragmentById(R.id.fragment_container);


        // LISTENER FOR TIP CALCULATOR BUTTON
        buttonTipCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "buttonTipCalc onClick");
                FragmentManager fm = getSupportFragmentManager();
                Fragment sampleFragment = fm.findFragmentById(R.id.fragment_container);


                    Log.d(TAG, "buttonTipCalc onClick: sampleFragment is not null");
                    if (fragmentSwitch != 1)
                        sampleFragment = new TipFragment();
                    fragmentSwitch = 1;
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, sampleFragment);
                    fragmentTransaction.commit();

            }
        });

        // LISTENER FOR UNIT CONVERSION BUTTON
        buttonUnitConv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "buttonUnitConv onClick");
                FragmentManager fm = getSupportFragmentManager();
                Fragment sampleFragment = fm.findFragmentById(R.id.fragment_container);


                    Log.d(TAG, "buttonUnitConv onClick: sampleFragment is not null");
                    if (fragmentSwitch != 2)
                        sampleFragment = new UnitFragment();
                    fragmentSwitch = 2;
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, sampleFragment);
                    fragmentTransaction.commit();

            }
        });

        // LISTENER FOR MONEY EXCHANGE BUTTON
        buttonMoneyExch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "buttonMoneyExch onClick");
                FragmentManager fm = getSupportFragmentManager();
                Fragment sampleFragment = fm.findFragmentById(R.id.fragment_container);


                    Log.d(TAG, "buttonMoneyExch onClick: sampleFragment is not null");
                    if (fragmentSwitch != 3)
                        sampleFragment = new MoneyFragment();
                    fragmentSwitch = 3;
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, sampleFragment);
                    fragmentTransaction.commit();

            }
        });




    }


}
