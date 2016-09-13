/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Doktor;
import domen.Medsestra;
import domen.Pacijent;
import domen.Termin;
import domen.Zub;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author radovanovic
 */
public class Kontroler {

    private static Kontroler instanca;

    private Kontroler() {

    }

    public static Kontroler vratiInstancu() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public Medsestra login(Medsestra ms) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("diplPU");
        EntityManager em = emf.createEntityManager();
        Medsestra meds;
        meds = em.createNamedQuery("Medsestra.login", Medsestra.class).setParameter("username", ms.getUsername()).setParameter("password", ms.getPassword()).getSingleResult();
        em.close();
        emf.close();

        return meds;
    }



}
