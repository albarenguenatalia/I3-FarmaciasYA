/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Model.Order1;
import Model.OrderDetail;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author albarenguenatalia
 */
@Stateless
public class OrderFacade extends AbstractFacade<Order1> {
    @PersistenceContext(unitName = "I3-FarmaciasYa-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderFacade() {
        super(Order1.class);
    }
    
     

     
        
}
