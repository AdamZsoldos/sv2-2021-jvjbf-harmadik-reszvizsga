package movietheatres;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.*;

public class MovieTheatreService {

    private final Map<String, List<Movie>> shows = new LinkedHashMap<>();

    public Map<String, List<Movie>> getShows() {
        return shows;
    }

    public void readFromFile(Path path) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while (reader.ready()) {
                parseLine(reader.readLine());
            }
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read file", e);
        }
    }

    private void parseLine(String line) {
        String[] fields = line.split("-");
        String theater = fields[0];
        fields = fields[1].split(";");
        String title = fields[0];
        LocalTime startTime = LocalTime.parse(fields[1]);
        addShow(theater, title, startTime);
    }

    private void addShow(String theater, String title, LocalTime startTime) {
        shows.putIfAbsent(theater, new ArrayList<>());
        List<Movie> movies = shows.get(theater);
        movies.add(new Movie(title, startTime));
        movies.sort(Comparator.comparing(Movie::getStartTime));
    }

    public List<String> findMovie(String title) {
        return shows.entrySet().stream()
                .filter(entry -> entry.getValue().stream().anyMatch(movie -> movie.getTitle().equals(title)))
                .map(Map.Entry::getKey)
                .toList();
    }

    public LocalTime findLatestShow(String title) {
        LocalTime latestShow = null;
        for (List<Movie> movies : shows.values()) {
            for (Movie movie : movies) {
                if (movie.getTitle().equals(title) && (latestShow == null || movie.getStartTime().isAfter(latestShow))) {
                    latestShow = movie.getStartTime();
                }
            }
        }
        if (latestShow == null) {
            throw new IllegalArgumentException("Movie not found");
        }
        return latestShow;
    }
}
