/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Djox
 */
@Embeddable
public class TerminPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "medSestra")
    private int medSestra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pacijent")
    private int pacijent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datum")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;

    public TerminPK() {
        datum = new Date();
    }

    public TerminPK(int medSestra, int pacijent, Date datum) {
        this.medSestra = medSestra;
        this.pacijent = pacijent;
        this.datum = datum;
    }

    public int getMedSestra() {
        return medSestra;
    }

    public void setMedSestra(int medSestra) {
        this.medSestra = medSestra;
    }

    public int getPacijent() {
        return pacijent;
    }

    public void setPacijent(int pacijent) {
        this.pacijent = pacijent;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) medSestra;
        hash += (int) pacijent;
        hash += (datum != null ? datum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TerminPK)) {
            return false;
        }
        TerminPK other = (TerminPK) object;
        if (this.medSestra != other.medSestra) {
            return false;
        }
        if (this.pacijent != other.pacijent) {
            return false;
        }
        if ((this.datum == null && other.datum != null) || (this.datum != null && !this.datum.equals(other.datum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.TerminPK[ medSestra=" + medSestra + ", pacijent=" + pacijent + ", datum=" + datum + " ]";
    }
    
}
