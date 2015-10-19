/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Model.Drugstore;
import Model.ProductDrugstore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author martingonzalez
 */
public class FindDrugstoreFacade {
    @PersistenceContext(unitName = "I3-FarmaciasYa-PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public FindDrugstoreFacade() {
    }
        
    /*
    *Poner en wiki que el username es el email
    */
    public List<Drugstore> findAllByProductId(int id) {
         List<Drugstore> drugstores = getEntityManager().createNamedQuery(
            "Drugstore.findAll")
                 .getResultList();
         List<Drugstore> listaNueva = new ArrayList<Drugstore>();
         for (Drugstore d : drugstores){
             for (ProductDrugstore pd : d.getProductDrugstoreCollection())
                 if (pd.getIdProduct().getIdProduct() == id){
                     listaNueva.add(d);
                     break;
                 }
         }
         return listaNueva;
    } 
}
