package itp341.wang.cherrie.a7;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class SizeActivity extends AppCompatActivity {
    private Spinner spinner;
    private String size = "";
    private Button setChangesButton;
    private String sizeAnswer = "";
    private int numCompletedPuzzles = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size);
        Intent i = getIntent();
        numCompletedPuzzles = i.getIntExtra(MainActivity.EXTRA_NUM_COMPLETE, -1);
        sizeAnswer = i.getStringExtra(MainActivity.EXTRA_SIZE_ANSWER);

        spinner = (Spinner) findViewById(R.id.spinnerSize);
        setChangesButton = (Button) findViewById(R.id.buttonChange);

        // BUTTON LISTENER
        setChangesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                Intent output = new Intent();
                // save size and send back to MainActivity
                output.putExtra(MainActivity.EXTRA_SIZE, size);
                // number of completed puzzles
                output.putExtra(MainActivity.EXTRA_NUM_COMPLETE, numCompletedPuzzles);
                // pass the answer back again ?? ?
                output.putExtra(MainActivity.EXTRA_SIZE_ANSWER, sizeAnswer);
                setResult(Activity.RESULT_OK, output);
                finish();
            }
        });

        // Change listener for the Spinner deciding how things get split
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        size = "small";
                        break;
                    case 1:
                        size = "medium";
                        break;
                    case 2:
                        size = "big";
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // sometimes you need nothing here
            }
        });
    }
}
