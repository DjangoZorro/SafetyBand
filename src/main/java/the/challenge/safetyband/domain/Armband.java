package the.challenge.safetyband.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "armband")
public class Armband {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    private String naam;
    private boolean actief;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @OneToMany(mappedBy = "armband")
    private Set<Statistieken> statistieken;

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

    public boolean isActief() {
        return actief;
    }

    public void setActief(boolean actief) {
        this.actief = actief;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Statistieken> getStatistieken() {
        return statistieken;
    }

    public void setStatistieken(Set<Statistieken> statistieken) {
        this.statistieken = statistieken;
    }
}
