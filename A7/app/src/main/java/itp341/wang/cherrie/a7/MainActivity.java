package itp341.wang.cherrie.a7;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    // Things to be juggled around probably
    public static final String EXTRA_NUM_COMPLETE = "com.itp341.wang.cherrie.num_completed";
    public static final String EXTRA_RED = "com.itp341.wang.cherrie.red";
    public static final String EXTRA_GREEN = "com.itp341.wang.cherrie.green";
    public static final String EXTRA_BLUE = "com.itp341.wang.cherrie.blue";
    public static final String EXTRA_SIZE = "com.itp341.wang.cherrie.size";
    public static final String EXTRA_SIZE_ANSWER = "com.itp341.wang.cherrie.size_answer";
    public static final String EXTRA_NAME = "com.itp341.wang.cherrie.name";
    public static final String EXTRA_NAME_ANSWER = "com.itp341.wang.cherrie.name_answer";

    // UI Components
    private Button colorButton;
    private Button sizeButton;
    private Button nameButton;
    private Button solveButton;
    private TextView clueOne;
    private TextView clueTwo;
    private TextView clueThree;

    // Numbers
    private int color1;
    private int color2;
    private int color3;

    // Private variables that keep track of stuff
    private int numCompletedPuzzles = 0;
    private String sizeAnswer = "big";
    private String userInputSize = "";
    private String userInputName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // getting buttons...
        colorButton = (Button) findViewById(R.id.buttonDoorOne);
        sizeButton = (Button) findViewById(R.id.buttonDoorTwo);
        nameButton = (Button) findViewById(R.id.buttonDoorThree);
        solveButton = (Button) findViewById(R.id.buttonSolve);
        // for color changing purposes
        clueOne = (TextView) findViewById(R.id.textClue1);
        clueTwo = (TextView) findViewById(R.id.textClue2);
        clueThree = (TextView) findViewById(R.id.textClue3);

        // COLOR BUTTON LISTENER
        colorButton.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                // start intent to ColorActivity
                Intent colorIntent = new Intent(getApplicationContext(), ColorActivity.class);
                // add data to colorIntent
                colorIntent.putExtra(EXTRA_NUM_COMPLETE, numCompletedPuzzles);
                colorIntent.putExtra(ColorActivity.EXTRA_RED_ANSWER, 0);
                colorIntent.putExtra(ColorActivity.EXTRA_GREEN_ANSWER, 0);
                colorIntent.putExtra(ColorActivity.EXTRA_BLUE_ANSWER, 255);
                startActivityForResult(colorIntent,1);
            }
        });
        // SIZE BUTTON LISTENER
        sizeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                // start intent to SizeActivity
                Intent sizeIntent = new Intent(getApplicationContext(), SizeActivity.class);
                // add data to colorIntent
                sizeIntent.putExtra(EXTRA_NUM_COMPLETE, numCompletedPuzzles);
                sizeIntent.putExtra(EXTRA_SIZE_ANSWER, "big");
                startActivityForResult(sizeIntent,2);
            }
        });
        // NAME BUTTON LISTENER
        nameButton.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                // start intent to NameActivity
                Intent nameIntent = new Intent(getApplicationContext(), NameActivity.class);
                // add data to colorIntent
                nameIntent.putExtra(EXTRA_NUM_COMPLETE, numCompletedPuzzles);
                nameIntent.putExtra(EXTRA_NAME_ANSWER, "AI");
                startActivityForResult(nameIntent,3);
            }
        });

        // SOLVE BUTTON LISTENER
        solveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                // start intent to WinLoseActivity
                Intent winLoseIntent = new Intent(getApplicationContext(), WinLoseActivity.class);
                winLoseIntent.putExtra(EXTRA_NUM_COMPLETE, numCompletedPuzzles);
                startActivityForResult(winLoseIntent,4);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (1): {
                // ColorActivity
                Log.e(TAG, "We've returned from Color Activity, case 1");
                // get data
                color1 = data.getIntExtra(EXTRA_RED,-1);
                color2 = data.getIntExtra(EXTRA_GREEN,-1);
                color3 = data.getIntExtra(EXTRA_BLUE,-1);
                colorHandler();
            }
            break;

            case (2): {
                // SizeActivity
                Log.e(TAG, "We've returned from Size Activity, case 2");
                // get data
                userInputSize = data.getStringExtra(EXTRA_SIZE);
                sizeHandler();
            }
            break;
            case (3): {
                // NameActivity
                Log.e(TAG, "We've returned from Name Activity, case 3");
                userInputName = data.getStringExtra(EXTRA_NAME);
                nameHandler();
            }
            break;
            case (4): {
                // WinLoseActivity
                Log.e(TAG, "We've returned from WinLose Activity, case 4");
                resetAllClues();
            }
            break;
        }
    }

    private void colorHandler(){
        // door num = 1
        // change clue color when the two match and the number of
        // completed puzzles is one less than the door number
        Log.e(TAG, "We've returned from Color Activity, IN HANDLER");
        if (numCompletedPuzzles+1 == 1 && Color.rgb(color1, color2, color3) == Color.rgb(0,0,255)) {
            Log.d(TAG, "Color is correct");
            clueOne.setTextColor(Color.rgb(0, 255, 0));
            numCompletedPuzzles++;
        }
    }

    private void sizeHandler(){
        // door num = 2
        // change clue color when the two match and the number of
        // completed puzzles is one less than the door number
        if (numCompletedPuzzles+1 == 2 && sizeAnswer.equals(userInputSize)) {
            clueTwo.setTextColor(Color.rgb(0, 255, 0));
            numCompletedPuzzles++;
        }
    }

    private void nameHandler(){
        // door num = 3
        // change clue color when the two match and the number of
        // completed puzzles is one less than the door number
        Log.e(TAG, "User returns this name: "  + userInputName);
        if (numCompletedPuzzles+1 == 3 && userInputName.equals("AI")) {
            clueThree.setTextColor(Color.rgb(0, 255, 0));
            numCompletedPuzzles++;
        }
    }

    // Function called if we return from the WinLoseActivity – resets
    private void resetAllClues() {
        // changes the colors of the the text to red again
        clueOne.setTextColor(Color.rgb(255,0,0));
        clueTwo.setTextColor(Color.rgb(255,0,0));
        clueThree.setTextColor(Color.rgb(255,0,0));
        // reset holder values probably
        numCompletedPuzzles = 0;
        userInputSize = "";
        userInputName = "";
    }
}
