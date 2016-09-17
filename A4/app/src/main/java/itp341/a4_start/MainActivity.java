package itp341.a4_start;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private final double COST_MULTIPLIER = 1.2;
    private final double numShoeMultiplier = 0.5;
    private final double numTreatMultiplier = 1.5;
    private final double numHumanMultiplier = 10.0;
    private final double numHydrantMultiplier = 100.0;

    private TextView textViewDoge;
    private TextView textViewHydrant;
    private TextView textViewHuman;
    private TextView textViewShoe;
    private TextView textViewTreat;

    private ImageButton imageButtonMocha;

    private Button buttonHydrant;
    private Button buttonHuman;
    private Button buttonShoe;
    private Button buttonTreat;

    private long numDoge = 0;
    private long numHydrant = 0;
    private long numHuman = 0;
    private long numShoe = 0;
    private long numTreat = 0;

    private long numHydrantCost = 10000;
    private long numHumanCost = 1000;
    private long numShoeCost = 10;
    private long numTreatCost = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d(TAG, "onCreate Start");

        textViewDoge = (TextView) findViewById(R.id.textViewDogeCount);
        textViewHydrant = (TextView) findViewById(R.id.textViewHydrantCount);
        textViewHuman = (TextView) findViewById(R.id.textViewHumanCount);
        textViewShoe = (TextView) findViewById(R.id.textViewShoesCount);
        textViewTreat = (TextView) findViewById(R.id.textViewTreatsCount);


        Log.v(TAG, "Finished linking textviews");

        imageButtonMocha = (ImageButton) findViewById(R.id.imageButtonMocha);
        imageButtonMocha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double addAmount = 1 + numShoe * numShoeMultiplier + numTreat * numTreatMultiplier
                        + numHuman * numHumanMultiplier + numHydrant * numHydrantMultiplier;

                Log.v(TAG, "Adding " + numShoe * numShoeMultiplier + " from shoes");
                Log.v(TAG, "Adding " + numTreat * numTreatMultiplier + " from treats");
                Log.v(TAG, "Adding " + numHuman * numHumanMultiplier + " from humans" );
                Log.v(TAG, "Adding " + numHydrant * numHydrantMultiplier + " from hydrants");

                Log.d(TAG, "Adding: " + addAmount + " to existing: " + numDoge);

                numDoge += addAmount;
                updateDogeCount();
                updateBuyButtons();
            }
        });

        DogeClickerButtonListener listener = new DogeClickerButtonListener();

        buttonHydrant = (Button) findViewById(R.id.buttonBuyHydrant);
        buttonHuman = (Button) findViewById(R.id.buttonBuyHuman);
        buttonShoe = (Button) findViewById(R.id.buttonBuyShoe);
        buttonTreat = (Button) findViewById(R.id.buttonBuyTreat);

        Log.v(TAG, "Finished linking buttons");

        buttonHydrant.setOnClickListener(listener);
        buttonHuman.setOnClickListener(listener);
        buttonShoe.setOnClickListener(listener);
        buttonTreat.setOnClickListener(listener);

        Log.v(TAG, "Finished button listeners");

        String output = getResources().getString(R.string.textCost) + " ";

        buttonHydrant.setText(output + numHydrantCost);
        buttonHuman.setText(output + numHumanCost);
        buttonShoe.setText(output + numShoeCost);
        buttonTreat.setText(output + numTreatCost);

        updateBuyButtons();
        updateDogeCount();

//        textViewDoge.setText(numDoge+"");
//        textViewHydrant.setText(numHydrant+"");
//        textViewHuman.setText(numHuman+"");
//        textViewShoe.setText(numShoe+"");
//        textViewTreat.setText(numTreat+"");

        Log.d(TAG, "onCreate end");
    }

    private void updateDogeCount(){
        Log.v(TAG, "New numDoge: " + numDoge);
        textViewDoge.setText(numDoge + "");
    }

    private void updateBuyButtons(){
        Log.v(TAG, "buttonHydrant enabled: " + (numDoge >= numHydrantCost));
        buttonHydrant.setEnabled(numDoge >= numHydrantCost);
        Log.v(TAG, "buttonHuman enabled: " + (numDoge >= numHumanCost));
        buttonHuman.setEnabled(numDoge >= numHumanCost);
        Log.v(TAG, "buttonShoe enabled: " + (numDoge >= numShoeCost));
        buttonShoe.setEnabled(numDoge >= numShoeCost);
        Log.v(TAG, "buttonTreat enabled: " + (numDoge >= numTreatCost));
        buttonTreat.setEnabled(numDoge >= numTreatCost);
    }

    private class DogeClickerButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Log.d(TAG, "button clicked with id: " + v.getId());
            switch(v.getId()){
                case R.id.buttonBuyHuman:
                    Log.v(TAG, "Buying human for " + numHumanCost + " with " + numDoge + " in bank");
                    numDoge -= numHumanCost;
                    ++numHuman;
                    numHumanCost *= COST_MULTIPLIER;
                    Log.v(TAG, "Humans now cost " + numHumanCost);
                    ((Button) v).setText(getResources().getString(R.string.textCost) + " " + numHumanCost);
                    textViewHuman.setText(numHuman + "");
                    break;

                case R.id.buttonBuyHydrant:
                    Log.v(TAG, "Buying hydrant for " + numHydrantCost + " with " + numDoge + " in bank");
                    numDoge -= numHydrantCost;
                    ++numHydrant;
                    numHydrantCost *= COST_MULTIPLIER;
                    Log.v(TAG, "Hydrants now cost " + numHydrantCost);
                    ((Button) v).setText(getResources().getString(R.string.textCost) + " " + numHydrantCost);
                    textViewHydrant.setText(numHydrant + "");
                    break;

                case R.id.buttonBuyShoe:
                    Log.v(TAG, "Buying shoe for " + numShoeCost + " with " + numDoge + " in bank");
                    numDoge -= numShoeCost;
                    ++numShoe;
                    numShoeCost *= COST_MULTIPLIER;
                    Log.v(TAG, "Shoes now cost " + numShoeCost);
                    ((Button) v).setText(getResources().getString(R.string.textCost) + " " + numShoeCost);
                    textViewShoe.setText(numShoe + "");
                    break;

                case R.id.buttonBuyTreat:
                    Log.v(TAG, "Buying treat for " + numTreatCost + " with " + numDoge + " in bank");
                    numDoge -= numTreatCost;
                    ++numTreat;
                    numTreatCost *= COST_MULTIPLIER;
                    Log.v(TAG, "Treats now cost " + numTreatCost);
                    ((Button) v).setText(getResources().getString(R.string.textCost) + " " + numTreatCost);
                    textViewTreat.setText(numTreat + "");
                    break;
                case R.id.buttonInfo:
                    Log.v(TAG, "Toasting here");
                    String toastNum = getString(R.string.your)+" "+String.valueOf(numShoe)+" "+
                            getString(R.string.shoes)+" "+String.valueOf(numShoeMultiplier*numShoe)
                            +getString(R.string.dogesPerPress)

                            +getString(R.string.your)+" "+String.valueOf(numTreat)+" "+
                            getString(R.string.treats)+" "+String.valueOf(numTreatMultiplier*numTreat)
                            +getString(R.string.dogesPerPress)

                            +getString(R.string.your)+" "+String.valueOf(numHuman)+" "+
                            getString(R.string.humans)+" "+String.valueOf(numHumanMultiplier*numHuman)
                            +getString(R.string.dogesPerPress)

                            +getString(R.string.your)+" "+String.valueOf(numHydrant)+" "+
                            getString(R.string.hydrants)+" "+String.valueOf(numHydrantMultiplier*numHydrant)
                            +getString(R.string.dogesPerPress);
                    Toast.makeText(MainActivity.this, toastNum, Toast.LENGTH_SHORT).show();
                    break;
            }

            updateBuyButtons();
            updateDogeCount();
        }
    }

    public void displayContributionMessage(View v){
        // display toast
        Log.v(TAG, "Toasting here");
        String toastNum = getString(R.string.your)+" "+String.valueOf(numShoe)+" "+
                getString(R.string.shoes)+" "+String.valueOf(numShoeMultiplier*numShoe)
                +" "+getString(R.string.dogesPerPress)

                +getString(R.string.your)+" "+String.valueOf(numTreat)+" "+
                getString(R.string.treats)+" "+String.valueOf(numTreatMultiplier*numTreat)
                +" "+getString(R.string.dogesPerPress)

                +getString(R.string.your)+" "+String.valueOf(numHuman)+" "+
                getString(R.string.humans)+" "+String.valueOf(numHumanMultiplier*numHuman)
                +" "+getString(R.string.dogesPerPress)

                +getString(R.string.your)+" "+String.valueOf(numHydrant)+" "+
                getString(R.string.hydrants)+" "+String.valueOf(numHydrantMultiplier*numHydrant)
                +" "+getString(R.string.dogesPerPress);
        Toast.makeText(MainActivity.this, toastNum, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putLong("dogeCount", numDoge);
        savedInstanceState.putLong("hydrantCount", numHydrant);
        savedInstanceState.putLong("humanCount", numHuman);
        savedInstanceState.putLong("shoeCount", numShoe);
        savedInstanceState.putLong("treatCount", numTreat);

        savedInstanceState.putLong("hydrantCost", numHydrantCost);
        savedInstanceState.putLong("humanCost", numHumanCost);
        savedInstanceState.putLong("shoeCost", numShoeCost);
        savedInstanceState.putLong("treatCost", numTreatCost);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        numDoge = savedInstanceState.getLong("dogeCount");
        numHydrant = savedInstanceState.getLong("hydrantCount");
        numHuman = savedInstanceState.getLong("humanCount");
        numShoe = savedInstanceState.getLong("shoeCount");
        numTreat = savedInstanceState.getLong("treatCount");

        numHydrantCost =savedInstanceState.getLong("hydrantCost");
        numHumanCost = savedInstanceState.getLong("humanCost");
        numShoeCost = savedInstanceState.getLong("shoeCost");
        numTreatCost = savedInstanceState.getLong("treatCost");

        // dog count UI update
        textViewDoge.setText(numDoge + "");
        // buying buttons UI update
        buttonHuman.setText(getResources().getString(R.string.textCost) + " " + numHumanCost);
        textViewHuman.setText(numHuman + "");
        buttonHydrant.setText(getResources().getString(R.string.textCost) + " " + numHydrantCost);
        textViewHydrant.setText(numHydrant + "");
        buttonShoe.setText(getResources().getString(R.string.textCost) + " " + numShoeCost);
        textViewShoe.setText(numShoe + "");
        buttonTreat.setText(getResources().getString(R.string.textCost) + " " + numTreatCost);
        textViewTreat.setText(numTreat + "");
    }
}
