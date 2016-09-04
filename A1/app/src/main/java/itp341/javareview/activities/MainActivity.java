/*  THIS FILE IS COMPLETE
    WHEN YOU HAVE FINISHED THE HEROBATTLE CLASS,
        UNCOMMENT LINE #40
*/
package itp341.javareview.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import itp341.javareview.R;
import itp341.javareview.superhero.HeroBattle;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    TextView textDisplay;
    Button buttonStart;

    HeroBattle game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textDisplay = (TextView) findViewById(R.id.textDisplay);
        buttonStart = (Button) findViewById(R.id.buttonFight);

        game = new HeroBattle();

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = new String();

                /* UNCOMMENT THE LINE BELOW */
                result = game.play();

                textDisplay.setText(result);
            }
        });

    }
}
