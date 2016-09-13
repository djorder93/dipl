/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Djox
 */
@Embeddable
public class PregledPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pacijent")
    private int pacijent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "zub")
    private int zub;

    public PregledPK() {
    }

    public PregledPK(int id, int pacijent, int zub) {
        this.id = id;
        this.pacijent = pacijent;
        this.zub = zub;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPacijent() {
        return pacijent;
    }

    public void setPacijent(int pacijent) {
        this.pacijent = pacijent;
    }

    public int getZub() {
        return zub;
    }

    public void setZub(int zub) {
        this.zub = zub;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) pacijent;
        hash += (int) zub;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PregledPK)) {
            return false;
        }
        PregledPK other = (PregledPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.pacijent != other.pacijent) {
            return false;
        }
        if (this.zub != other.zub) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.PregledPK[ id=" + id + ", pacijent=" + pacijent + ", zub=" + zub + " ]";
    }
    
}
