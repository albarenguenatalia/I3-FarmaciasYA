/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Drugstore;
import Model.ProductDrugstore;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * @author martingonzalez
 */
@ManagedBean(name = "findDrugstoreController")
@SessionScoped
public class FindDrugstoreController {
    @Inject
    private Session.FindDrugstoreFacade ejbFacade;
    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;
     @ManagedProperty(value = "#{orderController}")
    private OrderController orderController;
    private List<Drugstore> drugstoreList;
    private List<Drugstore> drugstoreListRated;
    private String myAddress;
    private int idProduct;
    
    public FindDrugstoreController() {
        this.myAddress = "Av. Italia 3020, Montevideo";
    }
    
    /**
     * @return the drugstoreList
     */
    public List<Drugstore> getDrugstoreList() {
        return drugstoreList;
    }

    /**
     * @param drugstoreList the drugstoreList to set
     */
    public void setDrugstoreList(List<Drugstore> drugstoreList) {
        this.drugstoreList = drugstoreList;
    }

    /**
     * @return the myAddess
     */
    public String getMyAddress() {
        return myAddress;
    }

    /**
     * @param myAddress
     */
    public void setMyAddress(String myAddress) {
        this.myAddress = myAddress;
    }

    /**
     * @return the idProduct
     */
    public int getIdProduct() {
        if (this.idProduct != 0 ) return this.idProduct;
        try{
            String idProductStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idProduct");
            System.out.println(idProductStr);
            this.idProduct =  Integer.parseInt(idProductStr);
        }
        catch (Exception ex){
            this.idProduct = 0;
        } 
        return this.idProduct;
    }
    
    public void findDrugstores(){
        this.drugstoreList = this.ejbFacade.findAllByProductId(this.getIdProduct(),this.getMyAddress());
        System.out.println(idProduct);
    }

    /**
     * @param idProduct the idProduct to set
     */
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
    
    public String selectProductDrugstore(Drugstore d){
        sessionController.setSelectedDrugstore(d);
        if(sessionController.getCurrent().getIdUser() != null){
            Collection<ProductDrugstore> collection = d.getProductDrugstoreCollection();
            ProductDrugstore pdFound = null;
            for(ProductDrugstore pd: collection){
                if(pd.getIdProduct().getIdProduct() == this.getIdProduct()){
                    pdFound = pd;
                }
            }
            orderController.addProductToCart(pdFound);
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("cart.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(DrugstoreController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "";
    }

    /**
     * @return the sessionController
     */
    public SessionController getSessionController() {
        return sessionController;
    }

    /**
     * @param sessionController the sessionController to set
     */
    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    /**
     * @return the orderController
     */
    public OrderController getOrderController() {
        return orderController;
    }

    /**
     * @param orderController the orderController to set
     */
    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }
    
    public void init(){
        this.drugstoreListRated = this.ejbFacade.getDrugstoreListWithRate();
    }

    /**
     * @return the drugstoreListRated
     */
    public List<Drugstore> getDrugstoreListRated() {
        return drugstoreListRated;
    }

    /**
     * @param drugstoreListRated the drugstoreListRated to set
     */
    public void setDrugstoreListRated(List<Drugstore> drugstoreListRated) {
        this.drugstoreListRated = drugstoreListRated;
    }
}
