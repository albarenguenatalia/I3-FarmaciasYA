/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Model.Order1;
import Model.OrderDetail;
import Model.User;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author albarenguenatalia
 */
@Stateless
public class OrderFacade extends AbstractFacade<Order1> {
    @PersistenceContext(unitName = "I3-FarmaciasYa-PU")
    private EntityManager em;
    @Inject
    private OrderDetailFacade odFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderFacade() {
        super(Order1.class);
    }
    
     

     
        
}
