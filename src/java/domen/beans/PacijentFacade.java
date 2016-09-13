/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen.beans;

import domen.Pacijent;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Djox
 */
@Stateless
public class PacijentFacade extends AbstractFacade<Pacijent> {

    @PersistenceContext(unitName = "diplPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PacijentFacade() {
        super(Pacijent.class);
    }
    
}
