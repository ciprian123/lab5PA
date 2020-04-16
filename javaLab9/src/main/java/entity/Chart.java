package entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "charts")
@NamedQueries({
        @NamedQuery(name = "Charts.printTop",
                query = "SELECT c FROM Chart c ORDER BY c.rank DESC")
})
public class Chart {
    @Id
    @GeneratedValue(generator = "incrementator")
    @GenericGenerator(name = "incrementator", strategy = "increment")
    @Column(name = "artist_id")
    private int artistId;

    @Column(name = "rank")
    private int rank;

    public Chart() {

    }

    public Chart(int artistId, int rank) {
        this.artistId = artistId;
        this.rank = rank;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Chart{" +
                "artistId=" + artistId +
                ", rank=" + rank +
                '}';
    }
}
