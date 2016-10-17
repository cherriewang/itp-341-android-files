 package itp341.wang.cherrie.a7;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class NameActivity extends AppCompatActivity {
    private static final String TAG = "NameActivity";
    private Button setChangesButton;
    private EditText nameEditText;
    private String nameInput = "";
    private int numCompletedPuzzles = 0;
    private String nameAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        Intent i = getIntent();
        numCompletedPuzzles = i.getIntExtra(MainActivity.EXTRA_NUM_COMPLETE, -1);
        nameAnswer = i.getStringExtra(MainActivity.EXTRA_NAME_ANSWER);

        setChangesButton = (Button) findViewById(R.id.button2);
        nameEditText = (EditText) findViewById(R.id.editText);

        // BUTTON LISTENER
        setChangesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                Intent output = new Intent();
                // returns answer from first activity
                output.putExtra(MainActivity.EXTRA_NAME_ANSWER, nameAnswer);
                // number of completed puzzles
                output.putExtra(MainActivity.EXTRA_NUM_COMPLETE, numCompletedPuzzles);
                // name selected by the user
                output.putExtra(MainActivity.EXTRA_NAME, nameInput);
                setResult(Activity.RESULT_OK, output);
                finish();
            }
        });

        // Action listener for EditText where user inputs bill
        nameEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // saves the bill value
                Log.e(TAG, v.getText().toString());
                nameInput = v.getText().toString();
                return true;
            }
        });
    }
}
