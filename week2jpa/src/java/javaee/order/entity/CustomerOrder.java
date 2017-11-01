/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaee.order.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jackle
 */
@Entity
@Table(name="CUSTOMER_ORDER")
@NamedQuery(name="findAllOrders", 
        query="SELECT o from CustomerOrder o " + 
        "ORDER BY o.orderId")
public class CustomerOrder implements Serializable {
    @Id
    private String orderId;
    private String shipmentInfo;
    
    public CustomerOrder() {
        
    }

    public String getShipmentInfo() {
        return shipmentInfo;
    }

    public void setShipmentInfo(String shipmentInfo) {
        this.shipmentInfo = shipmentInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
    private String status;
    private String lastUpdate;
    private String discount;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    public CustomerOrder(String customerId, String shipmentInfo, String status, 
            String lastUpdate, String discount) {
        this.orderId = customerId;
        this.shipmentInfo = shipmentInfo;
        this.status = status;
        this.discount = discount;
        this.lastUpdate = lastUpdate;
    }
}
