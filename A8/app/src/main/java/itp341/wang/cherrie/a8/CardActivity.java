package itp341.wang.cherrie.a8;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import itp341.wang.cherrie.a8.model.Card;
import itp341.wang.cherrie.a8.model.Ticket;

public class CardActivity extends AppCompatActivity {
    public static final String TAG = CardActivity.class.getSimpleName();
    public static final String EXTRA_CARD = "com.itp341.wang.cherrie.card";
    // UI Components
    private EditText cardNumEditText;
    private EditText cardNameEditText;
    private Button saveButton;

    private Card myCard;
    private Ticket myTicket;

    private boolean toViewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        Intent i = getIntent();

        cardNameEditText = (EditText) findViewById(R.id.editTextName);
        cardNumEditText = (EditText) findViewById(R.id.editTextCardNum);
        saveButton = (Button) findViewById(R.id.buttonSave);
        myCard = new Card();
        myTicket = (Ticket) i.getExtras().getSerializable(MainActivity.EXTRA_TICKET);
        toViewOrder = i.getBooleanExtra(ViewOrderActivity.EXTRA_RETURN_TO_ORDER, false);
        Log.e(TAG, "toViewOrder: "+toViewOrder);

        // Action listener for EditText where user inputs card name
        cardNameEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // saves the bill value
                Log.e(TAG, v.getText().toString());
                String nameInput = v.getText().toString();
                myCard.setName(nameInput);
                return true;
            }
        });
        // Action listener for EditText where user inputs card number
        cardNumEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // saves the bill value
                Log.e(TAG, v.getText().toString());
                //int numInput = Integer.parseInt(v.getText().toString());
                myCard.setNum(Integer.parseInt(v.getText().toString()));
                return true;
            }
        });

        // Should return a card
        // SAVE BUTTON LISTENER
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                // Make the card
                myCard.setName(cardNameEditText.getText().toString());
                myCard.setNum(Integer.parseInt(cardNumEditText.getText().toString()));
                savePref();

//                if(toViewOrder){
//                    Log.e(TAG, "ViewOrderIntent starting");
//                    // start intent to ViewOrderActivity
//                    Intent viewOrderIntent = new Intent(getApplicationContext(), ViewOrderActivity.class);
//                    // Create a new card
//                    viewOrderIntent.putExtra(EXTRA_CARD, myCard);
//                    viewOrderIntent.putExtra(MainActivity.EXTRA_TICKET, myTicket);
//                    setResult(Activity.RESULT_OK,viewOrderIntent);
//                    finish();
//                } else {
                    Log.e(TAG, "MainActivityIntent starting");
                    // start intent to MainActivity
                    Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                    // Create a new card
                    mainIntent.putExtra(EXTRA_CARD, myCard);
                    mainIntent.putExtra(MainActivity.EXTRA_TICKET, myTicket);
                    mainIntent.putExtra(ViewOrderActivity.EXTRA_RETURN_TO_ORDER, toViewOrder);
                    setResult(Activity.RESULT_OK, mainIntent);
                    finish();
                //}
            }
        });

    }
    private void savePref(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Name",cardNameEditText.getText().toString());
        editor.putInt("Num",Integer.parseInt(cardNumEditText.getText().toString()));
        editor.apply();
    }
}
