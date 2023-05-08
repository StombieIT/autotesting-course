package org.ficko.models;

import java.util.Objects;

public class Track {
    private final String link;
    private final String title;

    public Track(String link, String title) {
        this.link = link;
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Track{" +
                "link='" + link + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Objects.equals(link, track.link) && Objects.equals(title, track.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, title);
    }
}
