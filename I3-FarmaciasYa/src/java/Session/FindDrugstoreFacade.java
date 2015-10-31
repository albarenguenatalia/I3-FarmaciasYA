/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Model.Drugstore;
import Model.ProductDrugstore;
import Utils.Coord;
import Utils.Distance;
import Utils.Nominatim;
import java.util.ArrayList;
import java.util.Collections;
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
    public List<Drugstore> findAllByProductId(int id, String direccion) {
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
         Nominatim n = new Nominatim();
         Coord userCoords = n.getCoords(direccion);
         for (Drugstore d : listaNueva){
             n = new Nominatim();
             if (d.getLatitud().equals("") || d.getLongitud().equals("")){
                 d.setCoords(n.getCoords(d.getAddress()));
             }
             d.setDistance(Distance.distance(d.getCoords(), userCoords));
         }
         Collections.sort(listaNueva);
         return listaNueva;
    } 
}
