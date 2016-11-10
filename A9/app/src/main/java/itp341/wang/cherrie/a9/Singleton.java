package itp341.wang.cherrie.a9;

import java.util.ArrayList;
import java.util.List;

import itp341.wang.cherrie.a9.model.Movie;

/**
 * Created by Cherrie on 11/8/16.
 */

public class Singleton {

    private static Singleton instance;
    private List<Movie> movieList = new ArrayList<Movie>();;

    //no outer class can initialize this class's object
    private Singleton() {}

    public static Singleton Instance()
    {
        //if no instance is initialized yet then create new instance
        //else return stored instance
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void addMovie(Movie movieToAdd){
        movieList.add(movieToAdd);
    }

    public List<Movie> getMovieList(){
        return movieList;
    }
    public Movie getMovie(int index){
        return movieList.get(index);
    }


}
