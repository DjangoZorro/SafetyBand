package the.challenge.safetyband.domain;

import javax.persistence.*;

@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    private String breedtegraad;

    private String lengtegraad;

    private String land;

    private String provincie;

    private String stad;

    private String postcode;

    private String huisnummer;

    private String straatnaam;

    @OneToOne(mappedBy = "location")
    private Armband armband;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBreedtegraad() {
        return breedtegraad;
    }

    public void setBreedtegraad(String breedtegraad) {
        this.breedtegraad = breedtegraad;
    }

    public String getLengtegraad() {
        return lengtegraad;
    }

    public void setLengtegraad(String lengtegraad) {
        this.lengtegraad = lengtegraad;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getProvincie() {
        return provincie;
    }

    public void setProvincie(String provincie) {
        this.provincie = provincie;
    }

    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public Armband getArmband() {
        return armband;
    }

    public void setArmband(Armband armband) {
        this.armband = armband;
    }
}
