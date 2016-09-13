/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen.beans;

import domen.Zub;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Djox
 */
@Stateless
public class ZubFacade extends AbstractFacade<Zub> {

    @PersistenceContext(unitName = "diplPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ZubFacade() {
        super(Zub.class);
    }
    
}
