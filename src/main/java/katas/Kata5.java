package katas;

import model.Movie;
import util.DataUtil;

import java.util.List;

import static com.google.common.primitives.Doubles.max;

/*
    Goal: Retrieve the largest rating using reduce()
    DataSource: DataUtil.getMovies()
    Output: Double
*/
public class Kata5 {
    public static Double execute() {
        List<Movie> movies = DataUtil.getMovies();
        return movies.stream()
                .map(movie -> movie.getRating())
                .reduce((((aDouble, bDouble) -> max(aDouble, bDouble)))).get();
    }
}
