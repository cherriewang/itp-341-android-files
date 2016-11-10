package itp341.wang.cherrie.a9;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import itp341.wang.cherrie.a9.model.Comment;
import itp341.wang.cherrie.a9.model.Movie;

public class DetailActivity extends AppCompatActivity {

    TextView movieTitle;
    TextView movieDescription;
    ImageView movieImage;
    EditText newComment;
    EditText nameComment;
    ListView listComments;
    Button addComment;
    int movieIndex;
    Movie selectedMovie;
    ArrayList<Comment> myComments;
    ArrayAdapter<String> arrayAdapter;
    CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        movieIndex = i.getIntExtra(MainActivity.EXTRA_MOVIE, -1);
        selectedMovie = new Movie();
        // Get the movie from the Singleton
        selectedMovie = Singleton.Instance().getMovie(movieIndex);

        movieTitle = (TextView) findViewById(R.id.textViewTitle);
        movieDescription = (TextView) findViewById(R.id.textViewDescript);
        movieImage = (ImageView) findViewById(R.id.detail_image_movie);
        newComment = (EditText) findViewById(R.id.commentEditText);
        nameComment = (EditText)findViewById(R.id.nameEditText);
        listComments = (ListView) findViewById(R.id.commentsList);
        addComment = (Button) findViewById(R.id.buttonComment);

        movieTitle.setText(Singleton.Instance().getMovie(movieIndex).getTitle());
        movieDescription.setText(Singleton.Instance().getMovie(movieIndex).getDescription());
        setMovieImage(Singleton.Instance().getMovie(movieIndex).getGenre(), movieImage);

        if(selectedMovie.getComments() == null) {
            ArrayList<Comment> noComments = new ArrayList<Comment>();
            noComments.add(new Comment());
            selectedMovie.setComments(noComments);
        }
        myComments = selectedMovie.getComments();


//        arrayAdapter =
//                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, myComments);
//        listComments.setAdapter(arrayAdapter);

        adapter = new DetailActivity.CommentAdapter(this,
                (ArrayList<Comment>) Singleton.Instance().getMovie(movieIndex).getComments());
        listComments.setAdapter(adapter);

        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add String to Movie's List of Comment Strings
                String date = (DateFormat.format("dd-MM-yyyy hh:mm:ss", new java.util.Date()).toString());
                Comment myComment = new Comment();
                myComment.setComment(newComment.getText().toString());
                myComment.setTimestamp(date);
                myComment.setName(nameComment.getText().toString());
                Singleton.Instance().getMovie(movieIndex).addComment(myComment);
                newComment.getText().clear();
                nameComment.getText().clear();
                adapter.notifyDataSetChanged();
            }
        });
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

    private class CommentAdapter extends ArrayAdapter<Comment> {
        public CommentAdapter(Context context, ArrayList<Comment> comments) {
            super(context, 0, comments);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
           Comment c = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_comment_row, parent, false);
            }
            // Lookup view for data population
            TextView cName = (TextView) convertView.findViewById(R.id.textViewName);
            TextView cTimeStamp = (TextView) convertView.findViewById(R.id.textViewTime);
            TextView cComment = (TextView) convertView.findViewById(R.id.textViewComment);

            // Populate the data into the template view using the data object
            cName.setText(c.getName());
            cComment.setText(c.getComment());
            cTimeStamp.setText(c.getTimestamp());


            // Return the completed view to render on screen
            return convertView;
        }
    }

}
