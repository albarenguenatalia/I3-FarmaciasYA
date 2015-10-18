package Controllers;

import Model.Product;
import Session.ProductFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

/**
 * @author martingonzalez
 */
@ManagedBean(name = "productController")
@SessionScoped
public class ProductController {
    @Inject
    private Session.ProductFacade ejbFacade;
    private String productName;
    List<Product> productsList;
    
    public ProductController(){
        this.productName = "prod";
    }
    
    public ProductFacade getFacade() {
        return ejbFacade;
    }
    
    public void findByName() {
        productsList = getFacade().findByName(getProductName());
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    /**
     * @return the productsList
     */
    public List<Product> getProductsList(){
        return productsList;
    }
    
    /**
     * @param productsList the productName to set
     */
    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }  
    
    public String getDrugstore(){
        return "find_drugstore.xhtml?includeViewParams=true";
    }
}