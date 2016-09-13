package itp341.wang.cherrie.a3;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {


    public int americanCount = 0;
    public int chineseCount = 0;
    public int indianCount = 0;
    public int italianCount = 0;
    public int middleEastCount = 0;
    public int portCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView americanView = (ImageView)findViewById(R.id.americanView);
        ImageView chineseView = (ImageView)findViewById(R.id.chineseView);
        ImageView indianView = (ImageView)findViewById(R.id.indianView);
        ImageView italianView = (ImageView)findViewById(R.id.italianView);
        ImageView middleEastView = (ImageView)findViewById(R.id.middleEastView);
        ImageView portView = (ImageView)findViewById(R.id.portView);

        Glide.with(getApplicationContext())
                .load("http://i.imgur.com/7NbznWw.jpg")
                .placeholder(R.drawable.placeholder)
                .into(americanView);
        Glide.with(getApplicationContext())
                .load("http://i.imgur.com/eTuCPxM.jpg")
                .placeholder(R.drawable.placeholder)
                .into(chineseView);
        Glide.with(getApplicationContext())
                .load("http://i.imgur.com/s4P2QXS.jpg")
                .placeholder(R.drawable.placeholder)
                .into(indianView);
        Glide.with(getApplicationContext())
                .load("http://i.imgur.com/FGCy7ib.jpg")
                .placeholder(R.drawable.placeholder)
                .into(italianView);
        Glide.with(getApplicationContext())
                .load("http://i.imgur.com/QbYgAVR.jpg")
                .placeholder(R.drawable.placeholder)
                .into(middleEastView);
        Glide.with(getApplicationContext())
                .load("http://i.imgur.com/0HjQWOK.jpg")
                .placeholder(R.drawable.placeholder)
                .into(portView);
    }

    public void displayAmericanMessage(View view){
        // update count
        americanCount++;
        // display toast
        String toastNum = getString(R.string.order)+" "+String.valueOf(americanCount)+" "+getString(R.string.american);
        Toast.makeText(MainActivity.this, toastNum, Toast.LENGTH_SHORT).show();

    }

    public void displayChineseMessage(View view){
        //update count
        chineseCount++;
        // display toast
        String toastNum = getString(R.string.order)+" "+String.valueOf(chineseCount)+" "+getString(R.string.chinese);
        Toast.makeText(MainActivity.this, toastNum, Toast.LENGTH_SHORT).show();
    }

    public void displayIndianMessage(View view){
        //update count
        indianCount++;
        // display toast
        String toastNum = getString(R.string.order)+" "+String.valueOf(indianCount)+" "+getString(R.string.indian);
        Toast.makeText(MainActivity.this, toastNum, Toast.LENGTH_SHORT).show();
    }

    public void displayItalianMessage(View view){
        //update count
        italianCount++;
        // display toast
        String toastNum = getString(R.string.order)+" "+String.valueOf(italianCount)+" "+getString(R.string.italian);
        Toast.makeText(MainActivity.this, toastNum, Toast.LENGTH_SHORT).show();
    }

    public void displayMiddleEastMessage(View view){
        //update count
        middleEastCount++;
        // display toast
        String toastNum = getString(R.string.order)+" "+String.valueOf(middleEastCount)+" "+getString(R.string.middle_east);
        Toast.makeText(MainActivity.this, toastNum, Toast.LENGTH_SHORT).show();
    }

    public void displayPortMessage(View view){
        //update count
        portCount++;
        // display toast
        String toastNum = getString(R.string.order)+" "+String.valueOf(portCount)+" "+getString(R.string.port);
        Toast.makeText(MainActivity.this, toastNum, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putInt("americanCount", americanCount);
        savedInstanceState.putInt("chineseCount", chineseCount);
        savedInstanceState.putInt("indianCount", indianCount);
        savedInstanceState.putInt("italianCount", italianCount);
        savedInstanceState.putInt("middleEastCount", middleEastCount);
        savedInstanceState.putInt("portCount", portCount);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        americanCount = savedInstanceState.getInt("americanCount");
        chineseCount = savedInstanceState.getInt("chineseCount");
        indianCount = savedInstanceState.getInt("indianCount");
        italianCount = savedInstanceState.getInt("italianCount");
        middleEastCount = savedInstanceState.getInt("middleEastCount");
        portCount = savedInstanceState.getInt("portCount");
    }
}
