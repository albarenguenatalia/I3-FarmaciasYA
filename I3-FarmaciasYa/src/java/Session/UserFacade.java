/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

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
     public User validateUser(String email, byte[] password) {
        User userFound = findByEmail(email);
        if (userFound != null && Arrays.equals(userFound.getPassword(), password)) {
            return userFound;
        }
        return null;
    }
    
    /*
    *Poner en wiki que el username es el email
    */
    public User findByEmail(String email) {
         List<User> users = getEntityManager().createNamedQuery(
            "User.findByEmail")
                 .setParameter("email", email)
                 .getResultList();
         if(users.size() == 1) {
             return users.get(0);
         }
         return null;
    }
    
    @Override
    public void create(User user) {
        try{
            getEntityManager().persist(user);
        }
        catch(ConstraintViolationException ex){
            Set<ConstraintViolation<?>> errors = ex.getConstraintViolations();
            for(ConstraintViolation<?> error : errors){
                System.err.println("#####################");
                System.err.println(user.getEmail());
                System.err.println(user.getAddress());
                System.err.println(user.getCreatedate());
                System.err.println(user.getIdUser());
                System.err.println(user.getLastName());
                System.err.println(user.getPassword());
                System.err.println(error.getConstraintDescriptor());
                System.err.println(error.getInvalidValue());
                System.err.println(error.getMessage());
                System.err.println("{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}");
            }
            throw ex;
        }
        
    }
    
    
}
