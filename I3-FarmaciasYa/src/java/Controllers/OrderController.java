package Controllers;

import Model.Drugstore;
import Model.Order1;
import Model.OrderDetail;
import Model.Product;
import Model.ProductDrugstore;
import Model.User;
import Session.OrderFacade;
import Session.UserFacade;
import Utils.Mail;
import Utils.OneWayHash;
import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.criteria.Predicate;

@ManagedBean(name = "orderController")
@SessionScoped
public class OrderController implements Serializable {
    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;
    @Inject
    private Session.OrderFacade ejbFacade;
    private ProductDrugstore selectedProductDrugstore;
   
   
    public OrderController() {
            }

    public OrderFacade getFacade() {
        return ejbFacade;
    }
    
    
    public String create() {
       return "";
    }    
    
    public String addProductToCart(ProductDrugstore pd){ 
        System.out.println("Agregando al carrito ordercontroller");
        OrderDetail od = new OrderDetail();
        od.setIdProdutDrugStore(pd);
        od.setIdOrder(sessionController.getCurrentOrder());
        od.setQuantity(1);     
        sessionController.getCurrentOrder().addOrderDetail(od);
        for(OrderDetail orderD: sessionController.getCurrentOrder().getOrderDetailCollection()){
            System.out.println(orderD.getIdProdutDrugStore().getIdProduct().getName());
        }
        return "";
    }
    
    public String setOrderDetailQuantity(OrderDetail od){
        System.out.println("Update quantity of product " + od.getIdProdutDrugStore().getIdProduct().getName() + " to " +
                od.getQuantity());
        return "";
    }
    
    public String decrementQnty(OrderDetail od){
        od.decrementQnty();
        return "";
    }
    
    public String incrementQnty(OrderDetail od){
        od.incrementQnty();
        return "";
    }
       

    public String update() {
        try {
            getFacade().edit(sessionController.getCurrentOrder());
           //"UserUpdated"));
            return "View";
        } catch (Exception e) {
           //"PersistenceErrorOccured"));
            return null;
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(sessionController.getCurrentOrder());
           //"UserDeleted"));
        } catch (Exception e) {
            e.printStackTrace();
           //"PersistenceErrorOccured"));
        }
    }
    
    public String removeOrderDetail(OrderDetail d){
        sessionController.getCurrentOrder().removeOrderDetail(d);
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

   
    @FacesConverter(forClass = Order1.class)
    public static class  OrderControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
             OrderController controller = ( OrderController)facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "orderController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof  Order1) {
                 Order1 o = ( Order1) object;
                return String.format("Order with id: %d,  IdOrder: %s", o.getIdOrder());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + User.class.getName());
            }
        }

    }
    
    
   }
