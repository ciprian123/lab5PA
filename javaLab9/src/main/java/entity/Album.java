package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "albums")
@NamedQueries({
        @NamedQuery(name = "Albums.findByName",   query = "SELECT a FROM Album a WHERE a.name = :name"),
        @NamedQuery(name = "Albums.findByArtist", query = "SELECT a FROM Album a WHERE a.artistId = :id")
})
public class Album {
    @Id
    @GeneratedValue(generator = "incrementator")
    @GenericGenerator(name = "incrementator", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "artist_id")
    private int artistId;

    @Column(name = "release_year")
    private int releaseYear;

    public Album() {

    }

    public Album(int id, String name, int artistId, int releaseYear) {
        this.id = id;
        this.name = name;
        this.artistId = artistId;
        this.releaseYear = releaseYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "Entity.Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artistId=" + artistId +
                ", releaseYear='" + releaseYear + '\'' +
                '}';
    }
}
