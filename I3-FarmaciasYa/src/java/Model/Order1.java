package Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cynthia
 */
@Entity
@Table(name = "order", schema="farmaciasyadb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Order1.findAll", query = "SELECT o FROM Order1 o"),
    @NamedQuery(name = "Order1.findByIdOrder", query = "SELECT o FROM Order1 o WHERE o.idOrder = :idOrder"),
    @NamedQuery(name = "Order1.findByDate", query = "SELECT o FROM Order1 o WHERE o.date = :date"),
    @NamedQuery(name = "Order1.findByStatus", query = "SELECT o FROM Order1 o WHERE o.status = :status"),
    @NamedQuery(name = "Order1.findByTotal", query = "SELECT o FROM Order1 o WHERE o.total = :total"),
    @NamedQuery(name = "Order1.findByUserId", query = "SELECT o FROM Order1 o WHERE o.idUser = :idUser ORDER BY o.date DESC")})

public class Order1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idOrder")
    private Integer idOrder;
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "Status")
    private Integer status;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Total")
    private Float total;
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    @ManyToOne
    private User idUser;    
    @OneToMany(mappedBy = "idOrder", cascade=CascadeType.ALL)
    private Collection<OrderDetail> orderDetailCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrder")
    private Collection<OrderRate> orderRateCollection;
    @Transient
    private Drugstore orderDrugstore;
    @Transient
    private String prettyDate;
    @Transient
    private boolean hasBeenRate;


    public Order1() {
    }

    public Order1(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public void addOrderDetail(OrderDetail od){
        od.setIdOrder(this);
        this.orderDetailCollection.add(od);
        this.total += od.getPrice();
}
    
    public void removeOrderDetail(OrderDetail od){
        float aux = od.getPrice();
        this.orderDetailCollection.remove(od);
        this.total -= aux;
    }
       
    @PostLoad @PostPersist @PostUpdate void calculateTotal(){
        this.total = (float)0;
        for(OrderDetail od: this.orderDetailCollection){
            this.total += od.getPrice();
        }
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrder != null ? idOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Order1)) {
            return false;
        }
        Order1 other = (Order1) object;
        if ((this.idOrder == null && other.idOrder != null) || (this.idOrder != null && !this.idOrder.equals(other.idOrder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Order1[ idOrder=" + idOrder + " ]";
    }

    /**
     * @return the orderDetailCollection
     */
    public Collection<OrderDetail> getOrderDetailCollection() {
        return orderDetailCollection;
    }

    /**
     * @param orderDetailCollection the orderDetailCollection to set
     */
    public void setOrderDetailCollection(Collection<OrderDetail> orderDetailCollection) {
        this.orderDetailCollection = orderDetailCollection;
        calculateTotal();    
    }

    public Drugstore getOrderDrugstore() {
        if(this.orderDetailCollection != null && this.orderDetailCollection.size() > 0){
            this.orderDrugstore = ((OrderDetail)this.orderDetailCollection.toArray()[0]).getIdProdutDrugStore().getIdDrugStore();
        }else{
            this.orderDrugstore = null;
        }
        return orderDrugstore;
    }

    public Collection<OrderRate> getOrderRateCollection() {
        return orderRateCollection;
    }

    public void setOrderRateCollection(Collection<OrderRate> orderRateCollection) {
        this.orderRateCollection = orderRateCollection;
    }
    
    public void setOrderDrugstore(Drugstore drugstore){
        this.orderDrugstore = drugstore;
    }

    /**
     * @return the prettyDate
     */
    public String getPrettyDate() {
        prettyDate = new SimpleDateFormat("dd/MM/yyyy hh:mm").format(date);
        return prettyDate;
    }
    
     public String getStarsHtml(){
        if(this.orderRateCollection != null 
                && this.orderDetailCollection.size() > 0 && this.orderDetailCollection.size() == 1){
            
            OrderRate myRate = (OrderRate) orderRateCollection.toArray()[0];
            return  "<i class=\"fa fa-star "+ (myRate.getRate() >= 0.5 ? "yellow" : "grey") +"\"></i>" + 
                "<i class=\"fa fa-star "+ (myRate.getRate() >= 1.5 ? "yellow" : "grey") +"\"></i>" + 
                "<i class=\"fa fa-star "+ (myRate.getRate() >= 2.5 ? "yellow" : "grey") +"\"></i>" + 
                "<i class=\"fa fa-star "+ (myRate.getRate() >= 3.5 ? "yellow" : "grey") +"\"></i>" + 
                "<i class=\"fa fa-star "+ (myRate.getRate() >= 4.5 ? "yellow" : "grey") +"\"></i>";
        }else{
            return "No hay order rate";
        }           
    }

    /**
     * @param prettyDate the prettyDate to set
     */
    public void setPrettyDate(String prettyDate) {
        this.prettyDate = prettyDate;
    }

    /**
     * @return the hasBeenRate
     */
    public boolean getHasBeenRate() {
        if(this.orderRateCollection != null 
                && this.orderRateCollection.size() > 0 && this.orderRateCollection.size() == 1){
            this.hasBeenRate = true;
        }else{
            this.hasBeenRate = false;
        }       
        return this.hasBeenRate;
    }

    /**
     * @param hasBeenRate the hasBeenRate to set
     */
    public void setHasBeenRate(boolean hasBeenRate) {
        this.hasBeenRate = hasBeenRate;
    }
}
