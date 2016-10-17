package itp341.wang.cherrie.a7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class WinLoseActivity extends AppCompatActivity {
    private static final String TAG = "WinLoseActivity";
    private TextView textResult;
    private int numCompletedPuzzles = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        numCompletedPuzzles = i.getIntExtra(MainActivity.EXTRA_NUM_COMPLETE, -1);
        Log.d(TAG,"Did we retrieve data from Extra: " + numCompletedPuzzles);
        setContentView(R.layout.activity_win_lose);
        textResult = (TextView) findViewById(R.id.result);
        // validate data
        // change textResult to either win or lose
        if (numCompletedPuzzles == 3){
            // display win
            textResult.setText(getResources().getString(R.string.win));
        } else {
            // display try again
            textResult.setText(getResources().getString(R.string.lose));
            Log.d(TAG,"Lost: " + numCompletedPuzzles);
        }
    }
}
