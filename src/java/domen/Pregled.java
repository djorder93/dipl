/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Djox
 */
@Entity
@Table(name = "pregled")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pregled.findAll", query = "SELECT p FROM Pregled p"),
    @NamedQuery(name = "Pregled.findById", query = "SELECT p FROM Pregled p WHERE p.pregledPK.id = :id"),
    @NamedQuery(name = "Pregled.findByPacijent", query = "SELECT p FROM Pregled p WHERE p.pregledPK.pacijent = :pacijent"),
    @NamedQuery(name = "Pregled.findByZub", query = "SELECT p FROM Pregled p WHERE p.pregledPK.zub = :zub"),
    @NamedQuery(name = "Pregled.findByDatum", query = "SELECT p FROM Pregled p WHERE p.datum = :datum")})
public class Pregled implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PregledPK pregledPK;
    @Column(name = "datum")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;
    @JoinColumn(name = "pacijent", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Pacijent pacijent1;
    @JoinColumn(name = "zub", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Zub zub1;
    @JoinColumn(name = "usluga", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Usluga usluga;

    public Pregled() {
    }

    public Pregled(PregledPK pregledPK) {
        this.pregledPK = pregledPK;
    }

    public Pregled(int id, int pacijent, int zub) {
        this.pregledPK = new PregledPK(id, pacijent, zub);
    }

    public PregledPK getPregledPK() {
        return pregledPK;
    }

    public void setPregledPK(PregledPK pregledPK) {
        this.pregledPK = pregledPK;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Pacijent getPacijent1() {
        return pacijent1;
    }

    public void setPacijent1(Pacijent pacijent1) {
        this.pacijent1 = pacijent1;
    }

    public Zub getZub1() {
        return zub1;
    }

    public void setZub1(Zub zub1) {
        this.zub1 = zub1;
    }

    public Usluga getUsluga() {
        return usluga;
    }

    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pregledPK != null ? pregledPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pregled)) {
            return false;
        }
        Pregled other = (Pregled) object;
        if ((this.pregledPK == null && other.pregledPK != null) || (this.pregledPK != null && !this.pregledPK.equals(other.pregledPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Pregled[ pregledPK=" + pregledPK + " ]";
    }
    
}
