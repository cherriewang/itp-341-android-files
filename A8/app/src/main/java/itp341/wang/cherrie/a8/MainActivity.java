package itp341.wang.cherrie.a8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import itp341.wang.cherrie.a8.model.Card;
import itp341.wang.cherrie.a8.model.Ticket;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_TICKET = "com.itp341.wang.cherrie.ticket";
    public static final String EXTRA_SOLD = "com.itp341.wang.cherrie.tickets_sold";
    public static final String EXTRA_FIRST = "com.itp341.wang.cherrie.first";
    private int ticketsSold = 0;

    // UI Components
    private Button useCardButton;
    private Button verifyButton;
    private RadioGroup fromGroup;
    private RadioGroup toGroup;
    private Spinner startSpin;
    private Spinner endSpin;
    private TextView ticketCount;

    // temp holder variables
    private String ticketType;
    private String priority;
    private Ticket myTicket;
    private Card myCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getting buttons...
        useCardButton = (Button) findViewById(R.id.buttonUseCard);
        verifyButton = (Button) findViewById(R.id.buttonVerify);
        // radio buttons
        fromGroup = (RadioGroup) findViewById(R.id.radioFrom);
        toGroup = (RadioGroup) findViewById(R.id.radioTo);
        // ticket
        ticketCount = (TextView) findViewById(R.id.textViewTickets);
        startSpin = (Spinner) findViewById(R.id.spinnerFrom);
        endSpin = (Spinner) findViewById(R.id.spinnerTo);

        myTicket = new Ticket();
        myCard = new Card();

        // USE CARD BUTTON LISTENER
        useCardButton.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                Log.e(TAG, "use Card Intent / Listener");
                // start intent to ColorActivity
                Intent useCardIntent = new Intent(getApplicationContext(), CardActivity.class);
                useCardIntent.putExtra(EXTRA_TICKET, myTicket);
                startActivityForResult(useCardIntent,1);
            }
        });

        // USE CARD BUTTON LISTENER
        verifyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                Log.e(TAG, "verify Intent / Listener");
               // need to update the TextEdit with number of tickets sold, if valid
                Intent verifyIntent = new Intent(getApplicationContext(), ViewOrderActivity.class);
                verifyIntent.putExtra(EXTRA_TICKET, myTicket);
                verifyIntent.putExtra(CardActivity.EXTRA_CARD, myCard);
                Log.e(TAG, "Extras have been put in");
                startActivityForResult(verifyIntent,2);
            }
        });

        // gets input from the FROM RADIO BUTTON
        fromGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                RadioButton r = (RadioButton) group.findViewById(checkedId);
                Log.e(TAG, "About to update Ticket Type");
                myTicket.setTripType(r.getText().toString());
                myTicket.setRadioBtnId_type(checkedId);
                //Log.d(TAG, ticketType);
            }
        });

        // gets input from the TO RADIO BUTTON
        toGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                RadioButton r = (RadioButton) group.findViewById(checkedId);
                Log.e(TAG, "About to update Ticket priority");
                myTicket.setPriority(r.getText().toString());
                myTicket.setRadioBtnId_priority(checkedId);
                //Log.d(TAG, priority);
            }
        });

        // SPINNER LISTENERS
        // Change listener for the Spinner deciding destinations
        startSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Log.e(TAG, "Changed to Los Angeles");
                        myTicket.setStartLoc("Los Angeles");
                        break;
                    case 1:
                        Log.e(TAG, "Changed to Maryland");
                        myTicket.setStartLoc("Maryland");
                        break;
                    case 2:
                        Log.e(TAG, "Changed to Chicago");
                        myTicket.setStartLoc("Chicago");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // sometimes you need nothing here
            }
        });
        endSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        Log.e(TAG, "Changed to Los Angeles");
                        myTicket.setEndLoc("Los Angeles");
                        break;
                    case 1:
                        Log.e(TAG, "Changed to Maryland");
                        myTicket.setEndLoc("Maryland");
                        break;
                    case 2:
                        Log.e(TAG, "Changed to Chicago");
                        myTicket.setEndLoc("Chicago");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // sometimes you need nothing here
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "Receive activity result");
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case(0): {
                Log.e(TAG, "Want to Edit Order again, case 0");
                // Should repopulate all things with the appropriate
                myTicket = (Ticket) data.getExtras().getSerializable(EXTRA_TICKET);
                myCard = (Card) data.getExtras().getSerializable(CardActivity.EXTRA_CARD);
                repopulateOrder();
            }
            break;
            case (1): {
                // UseCard
                Log.e(TAG, "We've returned from Use Card Activity, case 1");
                // get data
                myCard = (Card) data.getExtras().getSerializable(CardActivity.EXTRA_CARD);
                myTicket = (Ticket) data.getExtras().getSerializable(EXTRA_TICKET);
                Log.e(TAG, "Got extras");
                repopulateOrder();
            }
            break;
            case (2): {
                // VerifyActivity
                Log.e(TAG, "We've returned from View Order Activity, case 2");
                // get data
                boolean increment = data.getBooleanExtra(ViewOrderActivity.EXTRA_VERIFIED, false);
                ticketCountHandler(increment);
            }
            break;
        }
    }

    private void repopulateOrder(){
        Log.e(TAG, "Getting ready to update views!");
        // radio buttons
        fromGroup.check(myTicket.getRadioBtnId_type());
        toGroup.check(myTicket.getRadioBtnId_priority());
        Log.e(TAG, "radioButtons done: "+ myTicket.getRadioBtnId_type()+ " "+myTicket.getRadioBtnId_priority());
        // spinners (by value)
        String startLoc = myTicket.getStartLoc(); //the value you want the position for
        String endLoc = myTicket.getEndLoc(); //the value you want the position for
        ArrayAdapter startAdap = (ArrayAdapter) startSpin.getAdapter(); //cast to an ArrayAdapter
        int spinnerPosition1 = startAdap.getPosition(startLoc);
        startSpin.setSelection(spinnerPosition1);
        ArrayAdapter endAdap = (ArrayAdapter) endSpin.getAdapter(); //cast to an ArrayAdapter
        int spinnerPosition2 = endAdap.getPosition(endLoc);
        endSpin.setSelection(spinnerPosition2);
        ticketCount.setText(String.valueOf(ticketsSold));
    }

    private void ticketCountHandler(boolean inc){
        Log.e(TAG, "Ticket Handler");
        if(inc){
            ticketsSold++;
            // update UI
            ticketCount.setText(String.valueOf(ticketsSold));
        } else {
            // populate the rest to edit
            repopulateOrder();
        }
    }

}
