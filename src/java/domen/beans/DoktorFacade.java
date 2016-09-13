/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen.beans;

import domen.Doktor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Djox
 */
@Stateless
public class DoktorFacade extends AbstractFacade<Doktor> {

    @PersistenceContext(unitName = "diplPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DoktorFacade() {
        super(Doktor.class);
    }
    
}
