/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Drugstore;
import java.util.ArrayList;
import java.util.List;
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
    private List<Drugstore> drugstoreList;
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
}
