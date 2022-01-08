package si.evinjete.uporabniki;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class UporabnikService {
    @PersistenceContext(unitName = "evinjete-uporabniki")
    private EntityManager em;

    public Uporabnik getUporabnik(String uporabnikId) {
        return em.find(Uporabnik.class, Integer.parseInt(uporabnikId));
    }

    public List<Uporabnik> getUporabniki() {
        List<Uporabnik> uporabniki = em
                .createNamedQuery("Uporabnik.findUporabniki", Uporabnik.class)
                .getResultList();

        return uporabniki;
    }

    public List<Uporabnik> getUporabnikFromNameSurnameEmail(String name, String surname, String email){
        List<Uporabnik> v = em
                .createNamedQuery("Uporabnik.findUporabnikFromNameSurnameEmail", Uporabnik.class)
                .setParameter("name", name)
                .setParameter("surname", surname)
                .setParameter("email", email)
                .getResultList();

        return v;
    }

    public List<Uporabnik> getUporabnikFromEmail(String email){
        List<Uporabnik> v = em
                .createNamedQuery("Uporabnik.findUporabnikFromEmail", Uporabnik.class)
                .setParameter("email", email)
                .getResultList();

        return v;
    }

    @Transactional
    public void saveUporabnik(Uporabnik uporabnik) {
        if (uporabnik != null) {
            em.persist(uporabnik);
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteUporabnik(String uporabnikId) {
        Uporabnik uporabnik = em.find(Uporabnik.class, Integer.parseInt(uporabnikId));
        if (uporabnik != null) {
            em.remove(uporabnik);
        }
    }

    @Transactional
    public Uporabnik addNewUporabnik(Uporabnik uporabnik) {
        if (uporabnik != null) {
            em.persist(uporabnik);
        }
        return uporabnik;
    }
}
