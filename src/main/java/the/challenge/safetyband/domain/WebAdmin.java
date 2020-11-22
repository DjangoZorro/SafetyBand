package the.challenge.safetyband.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "webadmin")
public class WebAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    private String gebruikersnaam;

    private String wachtwoord;

    private String email;

    private String naam;

    private String achternaam;

    @ManyToMany
    @JoinTable(name = "webadmin_statistieken", joinColumns = @JoinColumn(name = "webadmin_id"), inverseJoinColumns = @JoinColumn(name = "statistieken_id"))
    private Set<Statistieken> statistieken;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Set<Statistieken> getStatistieken() {
        return statistieken;
    }

    public void setStatistieken(Set<Statistieken> statistieken) {
        this.statistieken = statistieken;
    }
}
