/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Model.User;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author albarenguenatalia
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
    @PersistenceContext(unitName = "I3-FarmaciasYa-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
     public User validateUser(String username, String password){
        User userFound = findByUsername(username);
        if( userFound != null && userFound.getPassword().equals(password)){
            return userFound;
        }
        return null;
    }
    
    /*
    *Poner en wiki que el username es el email
    */
    public User findByUsername(String username){
         List<User> users = getEntityManager().createNamedQuery(
            "User.findByUsername")
                 .setParameter("username", username)
                 .getResultList();
         if(users.size() == 1){
             return users.get(0);
         }
         return null;
    }
    
    @Override
    public void create(User user){
        user.setCreatedDate(new Date() );
        user.setEmail(user.getUsername());
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(user);
        getEntityManager().getTransaction().commit();
    }
    
    
}
