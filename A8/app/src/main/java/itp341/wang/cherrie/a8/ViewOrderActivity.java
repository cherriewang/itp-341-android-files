package itp341.wang.cherrie.a8;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import itp341.wang.cherrie.a8.model.Card;
import itp341.wang.cherrie.a8.model.Ticket;

public class ViewOrderActivity extends AppCompatActivity {
    public static final String TAG = ViewOrderActivity.class.getSimpleName();
    public static final String EXTRA_VERIFIED = "com.itp341.wang.cherrie.verified";
    public static final String EXTRA_RETURN_TO_ORDER = "com.itp341.wang.cherrie.return_to_order";
    private Card currentCard;
    private Ticket currentTicket;

    private TextView from;
    private TextView to;
    private TextView type;
    private TextView priority;
    private TextView card_num;
    private TextView card_name;

    private Button editTicketButton;
    private Button editCardButton;
    private Button purchaseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        Intent i = getIntent();
        currentCard = (Card) i.getExtras().getSerializable(CardActivity.EXTRA_CARD);
        currentTicket = (Ticket) i.getExtras().getSerializable(MainActivity.EXTRA_TICKET);
        Log.e(TAG, "Should have received extras");

        from = (TextView) findViewById(R.id.textViewFrom);
        to = (TextView) findViewById(R.id.textViewTo);
        type = (TextView) findViewById(R.id.textViewType);
        priority = (TextView) findViewById(R.id.textViewPriority);
        card_num = (TextView) findViewById(R.id.textViewCardNumber);
        card_name = (TextView) findViewById(R.id.textViewNameOnCard);

        editTicketButton = (Button) findViewById(R.id.button3);
        editCardButton = (Button) findViewById(R.id.button2);
        purchaseButton = (Button) findViewById(R.id.button);

        // set all of the UI Components
        from.setText(currentTicket.getStartLoc());
        Log.e(TAG, "Start: "+currentTicket.getStartLoc());
        to.setText(currentTicket.getEndLoc());
        Log.e(TAG, "End: "+currentTicket.getEndLoc());
        type.setText(currentTicket.getTripType());
        Log.e(TAG, "Type: "+currentTicket.getTripType());
        priority.setText(currentTicket.getPriority());
        Log.e(TAG, "Priority: "+currentTicket.getPriority());
        card_num.setText(String.valueOf(currentCard.getNum()));
        Log.e(TAG, "CARD num: "+currentCard.getNum());
        card_name.setText(currentCard.getName());
        Log.e(TAG, "CARD name: "+currentCard.getName());

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("Name", "");
        int num = preferences.getInt("Num", -1);
        if(!name.equals("")){
            card_name.setText(name);
            currentCard.setName(name);
        }
        if(num != -1){
            card_num.setText(String.valueOf(num));
            currentCard.setNum(num);
        }
        Log.e(TAG, "we made it through the preferences!");


        // USE CARD BUTTON LISTENER
        editTicketButton.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                // start intent to ColorActivity
                Intent editTicketIntent = new Intent(getApplicationContext(), MainActivity.class);
                editTicketIntent.putExtra(MainActivity.EXTRA_TICKET, currentTicket);
                editTicketIntent.putExtra(CardActivity.EXTRA_CARD, currentCard);

                setResult(Activity.RESULT_OK, editTicketIntent);
                finish();
            }
        });

        // EDIT CARD BUTTON LISTENER
        editCardButton.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                // need to update the TextEdit with number of tickets sold, if valid
                Intent cardIntent = new Intent(getApplicationContext(), CardActivity.class);
                cardIntent.putExtra(MainActivity.EXTRA_TICKET, currentTicket);
                cardIntent.putExtra(CardActivity.EXTRA_CARD, currentCard);
                cardIntent.putExtra(EXTRA_RETURN_TO_ORDER, true);
                startActivityForResult(cardIntent,5);
            }
        });

        purchaseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                // need to update the TextEdit with number of tickets sold, if valid
                Intent verifyIntent = new Intent(getApplicationContext(), MainActivity.class);
                verifyIntent.putExtra(EXTRA_VERIFIED, true);
                setResult(Activity.RESULT_OK, verifyIntent);
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "Receive activity result");
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case(5): {
                Log.e(TAG, "Back from editing card");
                // Should repopulate all things with the appropriate
                currentCard = (Card) data.getExtras().getSerializable(CardActivity.EXTRA_CARD);
                Log.e(TAG, "got card");
                currentTicket = (Ticket) data.getExtras().getSerializable(MainActivity.EXTRA_TICKET);
                Log.e(TAG, "got ticket");
                changeViews();
            }
            break;
        }
    }


    private void changeViews(){
        Log.e(TAG, "in change views");
        from.setText(currentTicket.getStartLoc());
        to.setText(currentTicket.getEndLoc());
        type.setText(currentTicket.getTripType());
        priority.setText(currentTicket.getTripType());
        Log.e(TAG, "finished changing ticket");
        // card
        card_name.setText(currentCard.getName());
        card_num.setText(String.valueOf(currentCard.getNum()));
        Log.e(TAG, "finished changing card");
    }
}
