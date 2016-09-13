/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Djox
 */
@Entity
@Table(name = "doktor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Doktor.findAll", query = "SELECT d FROM Doktor d"),
    @NamedQuery(name = "Doktor.findById", query = "SELECT d FROM Doktor d WHERE d.id = :id"),
    @NamedQuery(name = "Doktor.findByIme", query = "SELECT d FROM Doktor d WHERE d.ime = :ime"),
    @NamedQuery(name = "Doktor.findByPrezime", query = "SELECT d FROM Doktor d WHERE d.prezime = :prezime")})
public class Doktor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "ime")
    private String ime;
    @Size(max = 255)
    @Column(name = "prezime")
    private String prezime;
    @OneToMany(mappedBy = "doktor", fetch = FetchType.EAGER)
    private List<Pacijent> pacijentList;

    public Doktor() {
    }

    public Doktor(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @XmlTransient
    public List<Pacijent> getPacijentList() {
        return pacijentList;
    }

    public void setPacijentList(List<Pacijent> pacijentList) {
        this.pacijentList = pacijentList;
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
        if (!(object instanceof Doktor)) {
            return false;
        }
        Doktor other = (Doktor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
    }
    
}
