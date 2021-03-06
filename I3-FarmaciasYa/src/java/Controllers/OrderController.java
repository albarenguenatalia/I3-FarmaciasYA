package Controllers;

import Model.Order1;
import Model.OrderDetail;
import Model.OrderRate;
import Model.ProductDrugstore;
import Model.User;
import Session.OrderFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@ManagedBean(name = "orderController")
@SessionScoped
public class OrderController implements Serializable {
    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;
    @Inject
    private Session.OrderFacade ejbFacade;
    private List<Order1> userHistory;
    private OrderRate rateOrder;
   
    public OrderController() {
    }

    public OrderFacade getFacade() {
        return ejbFacade;
    }
    
    
    public String create() {
       return "";
    }    
    
    public boolean updateUserHistory(){
        System.out.println("in updateUserHistory");
        if(sessionController.getCurrent().getIdUser() != null){
            userHistory = ejbFacade.findUserOrders(sessionController.getCurrent());
            return true;
        }
        return false;
    }
    
    public String addProductToCart(ProductDrugstore pd){ 
        OrderDetail od = new OrderDetail();
        od.setIdProdutDrugStore(pd);
        od.setIdOrder(sessionController.getCurrentOrder());
        od.setQuantity(1);     
        sessionController.getCurrentOrder().addOrderDetail(od);
        return "";
    }
    
    public String setOrderDetailQuantity(OrderDetail od){
        od.setQuantity(od.getQuantity());
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
    
    public String checkOut(){
        sessionController.getCurrentOrder().setDate(new Date());
        sessionController.getCurrentOrder().setStatus(1);
        this.ejbFacade.create(sessionController.getCurrentOrder());
        MailsController.SendEMail(sessionController.getCurrentOrder(), sessionController.getCurrent());
        sessionController.resetOrder();
        return "";
    }

    /**
     * @return the userHistory
     */
    public List<Order1> getUserHistory() {
        updateUserHistory();
        return userHistory;
    }

    /**
     * @param userHistory the userHistory to set
     */
    public void setUserHistory(List<Order1> userHistory) {
        this.userHistory = userHistory;
    }

    /**
     * @return the rateOrder
     */
    public OrderRate getRateOrder() {
         if (this.rateOrder == null) {
            rateOrder = new OrderRate();
            rateOrder.setRate(1);
        }
        return rateOrder;
    }

    /**
     * @param rateOrder the rateOrder to set
     */
    public void setRateOrder(OrderRate rateOrder) {
        this.rateOrder = rateOrder;
    }
    
    public void createRateOrder(Order1 order){
        this.rateOrder.setIdOrder(order);
        this.rateOrder.setIdDrugStore(order.getOrderDrugstore());
        order.getOrderRateCollection().add(rateOrder);
        order.getOrderDrugstore().getOrderRateCollection().add(rateOrder);
        this.ejbFacade.edit(order);
        this.rateOrder = new OrderRate();
        this.rateOrder.setRate(1);
        updateUserHistory();
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
