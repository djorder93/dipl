package domen.jsf;

import domen.Medsestra;
import domen.Pacijent;
import domen.Pregled;
import domen.PregledPK;
import domen.Termin;
import domen.jsf.util.JsfUtil;
import domen.jsf.util.JsfUtil.PersistAction;
import domen.beans.PacijentFacade;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@Named("pacijentController")
@SessionScoped
public class PacijentController implements Serializable {

    @EJB
    private domen.beans.PacijentFacade ejbFacade;
    private List<Pacijent> items = null;
    private Pacijent selected;
    private Termin t;
//    private List<Termin> termini;
    private Pregled p;
//    private List<Pregled> pregledi;

    public PacijentController() {
//        pregledi = new ArrayList<>();
//        termini = new ArrayList<>();
        p = new Pregled();
        t = new Termin();
    }

    public Termin getT() {
        return t;
    }

    public void setT(Termin t) {
        this.t = t;
    }

    public Pregled getP() {
        return p;
    }

    public void setP(Pregled p) {
        this.p = p;
    }

    public Pacijent getSelected() {
        return selected;
    }

    public void setSelected(Pacijent selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public void insertPregled() {
        System.out.println("PREGLED");
        p.setDatum(new Date());
        p.setPacijent1(selected);
        p.setPregledPK(new PregledPK(selected.getPregledList().size() + 1, p.getPacijent1().getId(), p.getZub1().getId()));
        for (Pregled pr : selected.getPregledList()) {
            if (pr.getUsluga().getNaziv().equals("Vadjenje")) {
                if (pr.getZub1().equals(p.getZub1())) {
                    JsfUtil.addErrorMessage("Zub je izvadjen ne moze se vrsiti intervencija!");
                    p = new Pregled();
                    return;
                }

            }
        }
        selected.getPregledList().add(p);
        p = new Pregled();
    }

    public void insertTermin() {
        System.out.println("termin");
        t.setMedsestra((Medsestra) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("logged"));
        t.setPacijent1(selected);
        t.getTerminPK().setMedSestra(t.getMedsestra().getId());
        t.getTerminPK().setPacijent(t.getPacijent1().getId());
        Pacijent p = selected;
        if (selected.getTerminList().contains(t)) {
            JsfUtil.addErrorMessage("Termin je vec zauzet");
            t = new Termin();
            return;
        }
        System.out.println("clkasnc");
        selected.getTerminList().add(t);
        t = new Termin();
    }

    public Date currentDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public void datFilter(SelectEvent event) {
        RequestContext.getCurrentInstance().execute("PF('pacijenti').filter()");
    }

    private PacijentFacade getFacade() {
        return ejbFacade;
    }

    public Pacijent prepareCreate() {
        selected = new Pacijent();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PacijentCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PacijentUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PacijentDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Pacijent> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Pacijent getPacijent(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Pacijent> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Pacijent> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Pacijent.class)
    public static class PacijentControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PacijentController controller = (PacijentController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pacijentController");
            return controller.getPacijent(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Pacijent) {
                Pacijent o = (Pacijent) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Pacijent.class.getName()});
                return null;
            }
        }

    }

}

//    public List<Pregled> getPregledi() {
//        return pregledi;
//    }
//
//    public void setPregledi(List<Pregled> pregledi) {
//        this.pregledi = pregledi;
//    }
//    public List<Termin> getTermini() {
//        return termini;
//    }
//
//    public void setTermini(List<Termin> termini) {
//        this.termini = termini;
//    }
