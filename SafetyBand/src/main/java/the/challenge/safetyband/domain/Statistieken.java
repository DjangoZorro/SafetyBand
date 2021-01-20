package the.challenge.safetyband.domain;

import javax.persistence.*;
import java.sql.Time;
import java.util.Set;

@Entity
@Table(name = "statistieken")
public class Statistieken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    private Integer aantal_keer_noodknop_ingedrukt;

    private Integer aantal_keer_annuleringsknop_ingedrukt;

    private Integer wachttijd_voor_eenheid_arrivatie;

    private Integer aantal_keer_geholpen;

    private Integer aantal_wachttijd_voor_annulering;

    private Time tijdstip_noodknop_ingedrukt;

    private Time tijdstip_eenheid_arrivatie;

    @ManyToOne
    @JoinColumn(name = "armband_id")
    private Armband armband;

    @ManyToMany(mappedBy = "statistieken")
    private Set<WebAdmin> webadmin;

    public Statistieken() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAantal_keer_noodknop_ingedrukt() {
        return aantal_keer_noodknop_ingedrukt;
    }

    public void setAantal_keer_noodknop_ingedrukt(Integer aantal_keer_noodknop_ingedrukt) {
        this.aantal_keer_noodknop_ingedrukt = aantal_keer_noodknop_ingedrukt;
    }

    public Integer getAantal_keer_annuleringsknop_ingedrukt() {
        return aantal_keer_annuleringsknop_ingedrukt;
    }

    public void setAantal_keer_annuleringsknop_ingedrukt(Integer aantal_keer_annuleringsknop_ingedrukt) {
        this.aantal_keer_annuleringsknop_ingedrukt = aantal_keer_annuleringsknop_ingedrukt;
    }

    public Integer getWachttijd_voor_eenheid_arrivatie() {
        return wachttijd_voor_eenheid_arrivatie;
    }

    public void setWachttijd_voor_eenheid_arrivatie(Integer wachttijd_voor_eenheid_arrivatie) {
        this.wachttijd_voor_eenheid_arrivatie = wachttijd_voor_eenheid_arrivatie;
    }

    public Integer getAantal_keer_geholpen() {
        return aantal_keer_geholpen;
    }

    public void setAantal_keer_geholpen(Integer aantal_keer_geholpen) {
        this.aantal_keer_geholpen = aantal_keer_geholpen;
    }

    public Integer getAantal_wachttijd_voor_annulering() {
        return aantal_wachttijd_voor_annulering;
    }

    public void setAantal_wachttijd_voor_annulering(Integer aantal_wachttijd_voor_annulering) {
        this.aantal_wachttijd_voor_annulering = aantal_wachttijd_voor_annulering;
    }

    public Time getTijdstip_noodknop_ingedrukt() {
        return tijdstip_noodknop_ingedrukt;
    }

    public void setTijdstip_noodknop_ingedrukt(Time tijdstip_noodknop_ingedrukt) {
        this.tijdstip_noodknop_ingedrukt = tijdstip_noodknop_ingedrukt;
    }

    public Time getTijdstip_eenheid_arrivatie() {
        return tijdstip_eenheid_arrivatie;
    }

    public void setTijdstip_eenheid_arrivatie(Time tijdstip_eenheid_arrivatie) {
        this.tijdstip_eenheid_arrivatie = tijdstip_eenheid_arrivatie;
    }

    public Armband getArmband() {
        return armband;
    }

    public void setArmband(Armband armband) {
        this.armband = armband;
    }

    public Set<WebAdmin> getWebadmin() {
        return webadmin;
    }

    public void setWebadmin(Set<WebAdmin> webadmin) {
        this.webadmin = webadmin;
    }
}
