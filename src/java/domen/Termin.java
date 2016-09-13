/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Djox
 */
@Entity
@Table(name = "termin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Termin.findAll", query = "SELECT t FROM Termin t"),
    @NamedQuery(name = "Termin.findByMedSestra", query = "SELECT t FROM Termin t WHERE t.terminPK.medSestra = :medSestra"),
    @NamedQuery(name = "Termin.findByPacijent", query = "SELECT t FROM Termin t WHERE t.terminPK.pacijent = :pacijent"),
    @NamedQuery(name = "Termin.findByDatum", query = "SELECT t FROM Termin t WHERE t.terminPK.datum = :datum")})
public class Termin implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TerminPK terminPK;
    @JoinColumn(name = "pacijent", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Pacijent pacijent1;
    @JoinColumn(name = "medSestra", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Medsestra medsestra;

    public Termin() {
        terminPK = new TerminPK();
    }

    public Termin(TerminPK terminPK) {
        this.terminPK = terminPK;
    }

    public Termin(int medSestra, int pacijent, Date datum) {
        this.terminPK = new TerminPK(medSestra, pacijent, datum);
    }

    public TerminPK getTerminPK() {
        return terminPK;
    }

    public void setTerminPK(TerminPK terminPK) {
        this.terminPK = terminPK;
    }

    public Pacijent getPacijent1() {
        return pacijent1;
    }

    public void setPacijent1(Pacijent pacijent1) {
        this.pacijent1 = pacijent1;
    }

    public Medsestra getMedsestra() {
        return medsestra;
    }

    public void setMedsestra(Medsestra medsestra) {
        this.medsestra = medsestra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (terminPK != null ? terminPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Termin)) {
            return false;
        }
        Termin other = (Termin) object;
        if ((this.terminPK == null && other.terminPK != null) || (this.terminPK != null && !this.terminPK.equals(other.terminPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Termin[ terminPK=" + terminPK + " ]";
    }
    
}
