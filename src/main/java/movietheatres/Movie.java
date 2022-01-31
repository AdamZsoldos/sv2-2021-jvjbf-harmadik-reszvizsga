package movietheatres;

import java.time.LocalTime;
import java.util.Objects;

public class Movie {

    private final String title;
    private final LocalTime startTime;

    public Movie(String title, LocalTime startTime) {
        this.title = title;
        this.startTime = startTime;
    }

    public String getTitle() {
        return title;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
