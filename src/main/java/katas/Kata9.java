package katas;

import com.google.common.collect.ImmutableMap;
import model.BoxArt;
import model.InterestingMoment;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        return movieLists.stream()
                .flatMap(movieList -> movieList.getVideos().stream())
                .map(movie -> ImmutableMap.of(
                        "id", movie.getId(),
                        "title", movie.getTitle(),
                        "time", movie.getInterestingMoments().stream()
                                .filter(interestingMoment -> interestingMoment.getType().equals("Middle"))
                                .map(InterestingMoment::getTime)
                                .findFirst().get(),
                        "boxart", movie.getBoxarts().stream()
                                .reduce((boxArt, boxArt2) -> boxArt.getUrl().length() < boxArt2.getUrl().length() ? boxArt : boxArt2)
                                .map(BoxArt::getUrl).get())
                )
                .collect(Collectors.toList());
    }
}
