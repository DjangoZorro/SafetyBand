package the.challenge.safetyband.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Armband {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String naam;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @OneToMany(mappedBy = "armband")
    private Set<Statistieken> statistiekens;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Statistieken> getStatistiekens() {
        return statistiekens;
    }

    public void setStatistiekens(Set<Statistieken> statistiekens) {
        this.statistiekens = statistiekens;
    }
}
