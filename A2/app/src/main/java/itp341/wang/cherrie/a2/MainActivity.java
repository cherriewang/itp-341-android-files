package itp341.wang.cherrie.a2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public int numTea = 0;
    public int numCoffee = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayTeaMessage(View view){
        // update numTea
        numTea++;
        // display toast
        String toastNum = getString(R.string.toast_tea)+" ("+String.valueOf(numTea)+" times)";
        Toast.makeText(MainActivity.this, toastNum, Toast.LENGTH_SHORT).show();

    }

    public void displayCoffeeMessage(View view){
        //update numCoffee
        numCoffee++;
        // display toast
        String toastNum = getString(R.string.toast_coffee)+" ("+String.valueOf(numCoffee)+" times)";
        Toast.makeText(MainActivity.this, toastNum, Toast.LENGTH_SHORT).show();
    }
}
