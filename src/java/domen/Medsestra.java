/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "medsestra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medsestra.login", query = "SELECT m FROM Medsestra m WHERE m.password = :password and  m.username = :username"),
    @NamedQuery(name = "Medsestra.findAll", query = "SELECT m FROM Medsestra m"),
    @NamedQuery(name = "Medsestra.findById", query = "SELECT m FROM Medsestra m WHERE m.id = :id"),
    @NamedQuery(name = "Medsestra.findByIme", query = "SELECT m FROM Medsestra m WHERE m.ime = :ime"),
    @NamedQuery(name = "Medsestra.findByPrezime", query = "SELECT m FROM Medsestra m WHERE m.prezime = :prezime"),
    @NamedQuery(name = "Medsestra.findByUsername", query = "SELECT m FROM Medsestra m WHERE m.username = :username"),
    @NamedQuery(name = "Medsestra.findByPassword", query = "SELECT m FROM Medsestra m WHERE m.password = :password")})
public class Medsestra implements Serializable {

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
    @Size(max = 255)
    @Column(name = "username")
    private String username;
    @Size(max = 255)
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "medSestra", fetch = FetchType.EAGER)
    private List<Pacijent> pacijentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medsestra", fetch = FetchType.EAGER)
    private List<Termin> terminList;

    public Medsestra() {
    }

    public Medsestra(Integer id) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public List<Pacijent> getPacijentList() {
        return pacijentList;
    }

    public void setPacijentList(List<Pacijent> pacijentList) {
        this.pacijentList = pacijentList;
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
        if (!(object instanceof Medsestra)) {
            return false;
        }
        Medsestra other = (Medsestra) object;
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
