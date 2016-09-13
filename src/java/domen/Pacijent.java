/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Djox
 */
@Entity
@Table(name = "pacijent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pacijent.findAll", query = "SELECT p FROM Pacijent p"),
    @NamedQuery(name = "Pacijent.findById", query = "SELECT p FROM Pacijent p WHERE p.id = :id"),
    @NamedQuery(name = "Pacijent.findByJmbg", query = "SELECT p FROM Pacijent p WHERE p.jmbg = :jmbg"),
    @NamedQuery(name = "Pacijent.findByIme", query = "SELECT p FROM Pacijent p WHERE p.ime = :ime"),
    @NamedQuery(name = "Pacijent.findByPrezime", query = "SELECT p FROM Pacijent p WHERE p.prezime = :prezime"),
    @NamedQuery(name = "Pacijent.findByTelefon", query = "SELECT p FROM Pacijent p WHERE p.telefon = :telefon"),
    @NamedQuery(name = "Pacijent.findByDatumrodjenja", query = "SELECT p FROM Pacijent p WHERE p.datumrodjenja = :datumrodjenja")})
public class Pacijent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 13)
    @Column(name = "jmbg")
    private String jmbg;
    @Size(max = 255)
    @Column(name = "ime")
    private String ime;
    @Size(max = 255)
    @Column(name = "prezime")
    private String prezime;
    @Size(max = 255)
    @Column(name = "telefon")
    private String telefon;
    @Column(name = "datumrodjenja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumrodjenja;
    @JoinColumn(name = "doktor", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Doktor doktor;
    @JoinColumn(name = "medSestra", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Medsestra medSestra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacijent1", fetch = FetchType.EAGER)
    private List<Pregled> pregledList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacijent1", fetch = FetchType.EAGER)
    private List<Termin> terminList;
    @Transient
    private String datFilter;

    public Pacijent() {
    }

    public Pacijent(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Date getDatumrodjenja() {
        return datumrodjenja;
    }

    public void setDatumrodjenja(Date datumrodjenja) {
        this.datumrodjenja = datumrodjenja;
    }

    public String getDatFilter() {
        return new SimpleDateFormat("dd/MM/yyyy").format(datumrodjenja);
    }

    public void setDatFilter(String datFilter) {
        this.datFilter = datFilter;
    }

    public Doktor getDoktor() {
        return doktor;
    }

    public void setDoktor(Doktor doktor) {
        this.doktor = doktor;
    }

    public Medsestra getMedSestra() {
        return medSestra;
    }

    public void setMedSestra(Medsestra medSestra) {
        this.medSestra = medSestra;
    }

    @XmlTransient
    public List<Pregled> getPregledList() {
        return pregledList;
    }

    public void setPregledList(List<Pregled> pregledList) {
        this.pregledList = pregledList;
    }

    @XmlTransient
    public List<Termin> getTerminList() {

        return terminList;
    }

    public void setTerminList(List<Termin> terminList) {
        this.terminList = terminList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pacijent)) {
            return false;
        }
        Pacijent other = (Pacijent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

}
