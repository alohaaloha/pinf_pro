package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Kliring.
 */
@Entity
@Table(name = "kliring")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Kliring implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "id_poruke", length = 50, nullable = false)
    private String idPoruke;

    @NotNull
    @Size(max = 8)
    @Column(name = "swwift_duznika", length = 8, nullable = false)
    private String swwift_duznika;

    @NotNull
    @Size(max = 18)
    @Column(name = "obracunski_racun_duznika", length = 18, nullable = false)
    private String obracunskiRacunDuznika;

    @NotNull
    @Size(max = 8)
    @Column(name = "swift_poverioca", length = 8, nullable = false)
    private String swift_poverioca;

    @NotNull
    @Size(max = 18)
    @Column(name = "obracunski_racun_poverioca", length = 18, nullable = false)
    private String obracunskiRacunPoverioca;

    @NotNull
    @Column(name = "ukupan_iznos", nullable = false)
    private Double ukupanIznos;

    @NotNull
    @Column(name = "datum_valute", nullable = false)
    private LocalDate datumValute;

    @NotNull
    @Column(name = "datum", nullable = false)
    private ZonedDateTime datum;

    @OneToMany(mappedBy = "kliring")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<StavkaKliringa> stavkaKliringas = new HashSet<>();

    @ManyToOne
    private Valuta valuta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdPoruke() {
        return idPoruke;
    }

    public void setIdPoruke(String idPoruke) {
        this.idPoruke = idPoruke;
    }

    public String getSwwift_duznika() {
        return swwift_duznika;
    }

    public void setSwwift_duznika(String swwift_duznika) {
        this.swwift_duznika = swwift_duznika;
    }

    public String getObracunskiRacunDuznika() {
        return obracunskiRacunDuznika;
    }

    public void setObracunskiRacunDuznika(String obracunskiRacunDuznika) {
        this.obracunskiRacunDuznika = obracunskiRacunDuznika;
    }

    public String getSwift_poverioca() {
        return swift_poverioca;
    }

    public void setSwift_poverioca(String swift_poverioca) {
        this.swift_poverioca = swift_poverioca;
    }

    public String getObracunskiRacunPoverioca() {
        return obracunskiRacunPoverioca;
    }

    public void setObracunskiRacunPoverioca(String obracunskiRacunPoverioca) {
        this.obracunskiRacunPoverioca = obracunskiRacunPoverioca;
    }

    public Double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(Double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public LocalDate getDatumValute() {
        return datumValute;
    }

    public void setDatumValute(LocalDate datumValute) {
        this.datumValute = datumValute;
    }

    public ZonedDateTime getDatum() {
        return datum;
    }

    public void setDatum(ZonedDateTime datum) {
        this.datum = datum;
    }

    public Set<StavkaKliringa> getStavkaKliringas() {
        return stavkaKliringas;
    }

    public void setStavkaKliringas(Set<StavkaKliringa> stavkaKliringas) {
        this.stavkaKliringas = stavkaKliringas;
    }

    public Valuta getValuta() {
        return valuta;
    }

    public void setValuta(Valuta valuta) {
        this.valuta = valuta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Kliring kliring = (Kliring) o;
        if(kliring.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, kliring.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Kliring{" +
            "id=" + id +
            ", idPoruke='" + idPoruke + "'" +
            ", swwift_duznika='" + swwift_duznika + "'" +
            ", obracunskiRacunDuznika='" + obracunskiRacunDuznika + "'" +
            ", swift_poverioca='" + swift_poverioca + "'" +
            ", obracunskiRacunPoverioca='" + obracunskiRacunPoverioca + "'" +
            ", ukupanIznos='" + ukupanIznos + "'" +
            ", datumValute='" + datumValute + "'" +
            ", datum='" + datum + "'" +
            '}';
    }
}
