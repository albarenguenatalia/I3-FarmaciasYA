/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Model.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author albarenguenatalia
 */
@Stateless

public class UserSessionBean implements UserSessionBeanLocal {
    @PersistenceContext
    private EntityManager em;
    
    
    @Override
    public List<User> getAllUsers() {
        List<User> users = em.createNamedQuery(
            "User.findAll").getResultList();
        return users;
    }
    
   
}
