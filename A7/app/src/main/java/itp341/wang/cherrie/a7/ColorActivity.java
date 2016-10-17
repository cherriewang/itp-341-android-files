package itp341.wang.cherrie.a7;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ColorActivity extends AppCompatActivity {
    public static final String EXTRA_RED_ANSWER = "com.itp341.wang.cherrie.red_answer";
    public static final String EXTRA_GREEN_ANSWER = "com.itp341.wang.cherrie.green_answer";
    public static final String EXTRA_BLUE_ANSWER = "com.itp341.wang.cherrie.blue_answer";

    private Button setChangesButton;
    private SeekBar redBar;
    private SeekBar greenBar;
    private SeekBar blueBar;
    private TextView redDisplay;
    private TextView greenDisplay;
    private TextView blueDisplay;
    private View colorPreview;
    private int numRed = 0;
    private int numGreen = 0;
    private int numBlue = 0;
    // answer to puzzle passed in
    private int R_answer = 0;
    private int G_answer = 0;
    private int B_answer = 0;
    private int numCompletedPuzzles = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        numCompletedPuzzles = i.getIntExtra(MainActivity.EXTRA_NUM_COMPLETE, -1);
        R_answer = i.getIntExtra(EXTRA_RED_ANSWER, -1);
        G_answer = i.getIntExtra(EXTRA_GREEN_ANSWER, -1);
        B_answer = i.getIntExtra(EXTRA_BLUE_ANSWER, -1);
        setContentView(R.layout.activity_color);

        setChangesButton = (Button) findViewById(R.id.buttonSetColor);
        redBar = (SeekBar) findViewById(R.id.seekBar);
        greenBar = (SeekBar) findViewById(R.id.seekBar2);
        blueBar = (SeekBar) findViewById(R.id.seekBar3);
        redDisplay = (TextView) findViewById(R.id.textView6);
        greenDisplay = (TextView) findViewById(R.id.textView8);
        blueDisplay = (TextView) findViewById(R.id.textView10);
        colorPreview = (View) findViewById(R.id.my_square);

        // BUTTON LISTENER
        setChangesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                // number of completed puzzles
                Intent output = new Intent();
                output.putExtra(MainActivity.EXTRA_NUM_COMPLETE, numCompletedPuzzles);
                // three values selected by the user, numRed numGreen numBlue
                output.putExtra(MainActivity.EXTRA_RED, numRed);
                output.putExtra(MainActivity.EXTRA_GREEN, numGreen);
                output.putExtra(MainActivity.EXTRA_BLUE, numBlue);
                // returns answer
                output.putExtra(EXTRA_RED_ANSWER, R_answer);
                output.putExtra(EXTRA_GREEN_ANSWER, G_answer);
                output.putExtra(EXTRA_BLUE_ANSWER, B_answer);
                setResult(Activity.RESULT_OK, output);
                finish();
            }
        });
        // RED SEEKBAR LISTENER
        // Change listener for SeekBar where user changes percentage
        redBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // update the percent
                numRed = progress;
                updateViewCount();
            }
        });
        // RED SEEKBAR LISTENER
        // Change listener for SeekBar where user changes percentage
        greenBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
                numGreen = progress;
                updateViewCount();
            }
        });
        // RED SEEKBAR LISTENER
        // Change listener for SeekBar where user changes percentage
        blueBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
                numBlue = progress;
                updateViewCount();
            }
        });
    }
    private void updateViewCount(){
        redDisplay.setText(""+numRed);
        greenDisplay.setText(""+numGreen);
        blueDisplay.setText(""+numBlue);
        colorPreview.setBackgroundColor(Color.rgb(numRed, numGreen, numBlue));
    }
}
