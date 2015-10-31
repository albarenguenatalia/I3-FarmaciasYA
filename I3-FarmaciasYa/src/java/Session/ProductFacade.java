package Session;

import Model.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author martingonzalez
 */
@Stateless
public class ProductFacade extends AbstractFacade{
    @PersistenceContext(unitName = "I3-FarmaciasYa-PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     */
    public ProductFacade() {
        super(Product.class);
    }
    

    public List<Product> findByName(String name) {
         List<Product> prods = getEntityManager().createNamedQuery(
            "Product.findLikeName")
                 .setParameter("name", name)
                 .getResultList();
         return prods;
    }
    
    
}
