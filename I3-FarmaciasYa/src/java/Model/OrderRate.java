/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cynthia
 */
@Entity
@Table(name = "order_rate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderRate.findAll", query = "SELECT o FROM OrderRate o"),
    @NamedQuery(name = "OrderRate.findById", query = "SELECT o FROM OrderRate o WHERE o.id = :id"),
    @NamedQuery(name = "OrderRate.findByRate", query = "SELECT o FROM OrderRate o WHERE o.rate = :rate")})
public class OrderRate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rate")
    private int rate;
    @JoinColumn(name = "idDrugStore", referencedColumnName = "idDrugStore")
    @ManyToOne(optional = false)
    private Drugstore idDrugStore;
    @JoinColumn(name = "idOrder", referencedColumnName = "idOrder")
    @ManyToOne(optional = false)
    private Order1 idOrder;

    public OrderRate() {
    }

    public OrderRate(Integer id) {
        this.id = id;
    }

    public OrderRate(Integer id, int rate) {
        this.id = id;
        this.rate = rate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Drugstore getIdDrugStore() {
        return idDrugStore;
    }

    public void setIdDrugStore(Drugstore idDrugStore) {
        this.idDrugStore = idDrugStore;
    }

    public Order1 getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Order1 idOrder) {
        this.idOrder = idOrder;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderRate)) {
            return false;
        }
        OrderRate other = (OrderRate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.OrderRate[ id=" + id + " ]";
    }
    
}
