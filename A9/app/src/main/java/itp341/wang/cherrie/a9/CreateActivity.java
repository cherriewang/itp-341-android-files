package itp341.wang.cherrie.a9;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.ImageVideoBitmapDecoder;

import itp341.wang.cherrie.a9.model.Movie;

public class CreateActivity extends AppCompatActivity {
    private Spinner spinner;
    private EditText titleEditText;
    private EditText descripEditText;
    private EditText urlEditText;
    private ImageView movieImage;
    private Button saveButton;
    private Movie myMovie;
    private int spinnerTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        titleEditText = (EditText) findViewById(R.id.editTextTitle);
        descripEditText = (EditText) findViewById(R.id.editTextDescription);
        urlEditText = (EditText) findViewById(R.id.editTextURL);
        spinner = (Spinner) findViewById(R.id.spinnerGenre);
        saveButton = (Button) findViewById(R.id.save_button);
        movieImage = (ImageView) findViewById(R.id.imageMovie);
        myMovie = new Movie();

        // Change listener for the Spinner deciding how things get split
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        spinnerTracker = 0;
                        setMovieImage(spinnerTracker);
                        break;
                    case 1:
                        spinnerTracker = 1;
                        setMovieImage(spinnerTracker);
                        break;
                    case 2:
                        spinnerTracker = 2;
                        setMovieImage(spinnerTracker);
                        break;
                    case 3:
                        spinnerTracker = 3;
                        setMovieImage(spinnerTracker);
                        break;
                    case 4:
                        spinnerTracker = 4;
                        setMovieImage(spinnerTracker);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // sometimes you need nothing here
            }
        });

        // Save Button Listener
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            //On click function
            public void onClick(View view) {
                myMovie.setTitle(titleEditText.getText().toString());
                myMovie.setDescription(descripEditText.getText().toString());
                myMovie.setUrl(urlEditText.getText().toString());
                myMovie.setGenre(spinnerTracker);
                // Add to Singleton here
                Singleton.Instance().addMovie(myMovie);

                Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                setResult(Activity.RESULT_OK, homeIntent);
                finish();
            }
        });
    }

    private void setMovieImage(int val){
        Log.e("CreateActivity", "Current value is: "+val);
        if(val == 0){
            // HORROR: Batman
            movieImage.setImageDrawable(getResources().getDrawable(R.drawable.batman));
//            Glide.with(getApplicationContext())
//                    .load("http://i.imgur.com/g7covAM.jpg")
//                    .placeholder(R.drawable.placeholder)
//                    .into(movieImage);
            Log.e("CreateActivity", "Loaded 0");
        } else if (val == 1){
            // ACTION: Deadpool
            movieImage.setImageDrawable(getResources().getDrawable(R.drawable.deadpool));
//            Glide.with(getApplicationContext())
//                    .load("http://i.imgur.com/uN1SCcs.jpg")
//                    .placeholder(R.drawable.placeholder)
//                    .into(movieImage);
            Log.e("CreateActivity", "Loaded 1");
        } else if (val == 2){
            // DRAMA: Finding Nemo
            movieImage.setImageDrawable(getResources().getDrawable(R.drawable.nemo));
//            Glide.with(getApplicationContext())
//                    .load("http://i.imgur.com/Rphg9Ts.jpg")
//                    .placeholder(R.drawable.placeholder)
//                    .into(movieImage);
            Log.e("CreateActivity", "Loaded 2");

        } else if (val == 3){
            // COMEDY: Shrek
            movieImage.setImageDrawable(getResources().getDrawable(R.drawable.shrek));
//            Glide.with(getApplicationContext())
//                    .load("http://screenrant.com/wp-content/uploads/Shrek2.jpg")
//                    .placeholder(R.drawable.placeholder)
//                    .into(movieImage);
            Log.e("CreateActivity", "Loaded 3");
        } else if(val == 4){
            // SCI-FI: Big Hero Six
            movieImage.setImageDrawable(getResources().getDrawable(R.drawable.bighero));
//            Glide.with(getApplicationContext())
//                    .load("http://i.imgur.com/qOOu2T9.jpg")
//                    .placeholder(R.drawable.placeholder)
//                    .into(movieImage);
            Log.e("CreateActivity", "Loaded 4");
        }

    }
}
