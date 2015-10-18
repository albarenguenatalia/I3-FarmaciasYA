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

/**
 * @author martingonzalez
 */
@ManagedBean(name = "findDrugstoreController")
@SessionScoped
public class FindDrugstoreController {
    private List<Drugstore> drugstoreList;
    private String myAddress;
    
    public FindDrugstoreController() {
        this.myAddress = "Av. Italia 2020, Montevideo";
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
        try{
            String idProductStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idProduct");
            return Integer.parseInt(idProductStr);
        }
        catch(Exception ex){
            return 0;
        }
    }
    
    public void findDrugstores(){
        int idProduct = getIdProduct();
        this.drugstoreList = (List<Drugstore>) new ArrayList<Drugstore>();
    }
}
