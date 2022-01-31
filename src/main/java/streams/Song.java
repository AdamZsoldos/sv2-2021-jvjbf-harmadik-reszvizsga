package streams;

import java.time.LocalDate;
import java.util.List;

public class Song {

    private final String title;
    private final int length;
    private final List<String> performers;
    private final LocalDate release;

    public Song(String title, int length, List<String> performers, LocalDate release) {
        this.title = title;
        this.length = length;
        this.performers = performers;
        this.release = release;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public List<String> getPerformers() {
        return performers;
    }

    public LocalDate getRelease() {
        return release;
    }
}
