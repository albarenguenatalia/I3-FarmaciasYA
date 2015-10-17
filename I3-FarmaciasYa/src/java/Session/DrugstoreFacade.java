/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Model.Drugstore;
import Model.User;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author albarenguenatalia
 */
@Stateless
public class DrugstoreFacade extends AbstractFacade<Drugstore> {
    @PersistenceContext(unitName = "I3-FarmaciasYa-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DrugstoreFacade() {
        super(Drugstore.class);
    }
        
    /*
    *Poner en wiki que el username es el email
    */
    public List<Drugstore> findByName(String name) {
         List<Drugstore> drugs = getEntityManager().createNamedQuery(
            "Drugstore.findLikeName")
                 .setParameter("name", "%" +name + "%")
                 .getResultList();
         return drugs;
    }
    
    
    
    
}
