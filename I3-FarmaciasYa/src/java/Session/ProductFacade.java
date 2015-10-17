package Session;

import Model.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author martingonzalez
 */
public class ProductFacade {
    @PersistenceContext(unitName = "I3-FarmaciasYa-PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     */
    public ProductFacade() {
    }
        
    /*
    *Poner en wiki que el username es el email
    */
    public List<Product> findByName(String name) {
         List<Product> prods = getEntityManager().createNamedQuery(
            "Product.findLikeName")
                 .setParameter("name", name)
                 .getResultList();
         return prods;
    }
    
    
}
