package itp341.wang.cherrie.a9;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import itp341.wang.cherrie.a9.model.Movie;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "com.itp341.wang.cherrie.movie";
    ListView movieList;
    Button addButton;
    MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList =(ListView)findViewById(R.id.movie_list);
        addButton = (Button) findViewById(R.id.buttonAdd);
        adapter = new MovieAdapter(this, (ArrayList<Movie>) Singleton.Instance().getMovieList());
        movieList.setAdapter(adapter);

        // Listener to view more
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start intent
                Intent createIntent = new Intent(getApplicationContext(), CreateActivity.class);
                startActivityForResult(createIntent, 1);
            }
        });

    }

    private class MovieAdapter extends ArrayAdapter<Movie> {
        public MovieAdapter(Context context, ArrayList<Movie> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Movie movie = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_row, parent, false);
            }
            // Lookup view for data population
            TextView movieTitle = (TextView) convertView.findViewById(R.id.textView2);
            TextView movieDescription = (TextView) convertView.findViewById(R.id.textView);
            ImageView imageMovie = (ImageView) convertView.findViewById(R.id.movie_image);
            Button moreButton = (Button) convertView.findViewById(R.id.buttonMore);

            // Populate the data into the template view using the data object
            movieTitle.setText(movie.getTitle());
            movieDescription.setText(movie.getDescription());

//            if(movie.getUrl() != ""){
//                Glide.with(getApplication())
//                    .load(movie.getUrl())
//                    .placeholder(R.drawable.placeholder)
//                    .into(imageMovie);
//            } else{
                setMovieImage(movie.getGenre(), imageMovie);
//            }

            moreButton.setTag(position);

            // Listener to view more
            moreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = (Integer) view.getTag();
                    // Access the row position here to get the correct data item
                    Movie selectedMovie = getItem(position);

                    Log.e("MainActivity", "do we crash here");
                    // Start intent
                    Intent detailIntent = new Intent(getApplicationContext(), DetailActivity.class);
                    detailIntent.putExtra(EXTRA_MOVIE, position);
                    startActivityForResult(detailIntent,2);
                }
            });

            // Return the completed view to render on screen
            return convertView;
        }
    }

    private void setMovieImage(int val, ImageView movieImage){
        Log.e("CreateActivity", "Current value is: "+val);
        if(val == 0){
            // HORROR: Batman
            movieImage.setImageDrawable(getResources().getDrawable(R.drawable.batman));
            Log.e("CreateActivity", "Loaded 0");
        } else if (val == 1){
            // ACTION: Deadpool
            movieImage.setImageDrawable(getResources().getDrawable(R.drawable.deadpool));

            Log.e("CreateActivity", "Loaded 1");
        } else if (val == 2){
            // DRAMA: Finding Nemo
            movieImage.setImageDrawable(getResources().getDrawable(R.drawable.nemo));
            Log.e("CreateActivity", "Loaded 2");

        } else if (val == 3){
            // COMEDY: Shrek
            movieImage.setImageDrawable(getResources().getDrawable(R.drawable.shrek));
            Log.e("CreateActivity", "Loaded 3");
        } else if(val == 4){
            // SCI-FI: Big Hero Six
            movieImage.setImageDrawable(getResources().getDrawable(R.drawable.bighero));
            Log.e("CreateActivity", "Loaded 4");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case(1): {
                refresh();
            }
            break;
            case(2):{

            }
            break;
        }
    }

    private void refresh(){
        adapter.notifyDataSetChanged();
    }
}
