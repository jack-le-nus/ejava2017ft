/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaee.order.web;
import javaee.order.entity.CustomerOrder;
import java.io.Serializable;
import java.util.List;
import javaee.order.ejb.RequestBean;
import javaee.order.entity.Vendor;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author jackle
 */
@ManagedBean
@SessionScoped
@Named
public class OrderManager implements Serializable {
    private static final long serialVersionID = 1L;
    private List<CustomerOrder> orders;
    private CustomerOrder newCustomerOrder;
    private String searchVendorName;
    private List<Vendor> vendorSearchResults;
    private boolean isVendorTableDisabled = true;

    public boolean isIsVendorTableDisabled() {
        return isVendorTableDisabled;
    }

    public void setIsVendorTableDisabled(boolean isVendorTableDisabled) {
        this.isVendorTableDisabled = isVendorTableDisabled;
    }

    public List<Vendor> getVendorSearchResults() {
        return vendorSearchResults;
    }

    public void setVendorSearchResults(List<Vendor> vendorSearchResults) {
        this.vendorSearchResults = vendorSearchResults;
    }
    
    public String getSearchVendorName() {
        return searchVendorName;
    }

    public void setSearchVendorName(String searchVendorName) {
        this.searchVendorName = searchVendorName;
    }
    
    public void searchVendor() {
        try {
            vendorSearchResults = this.requestBean.searchVendor(this.searchVendorName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public CustomerOrder getNewCustomerOrder() {
        return newCustomerOrder;
    }

    public void setNewCustomerOrder(CustomerOrder newCustomerOrder) {
        this.newCustomerOrder = newCustomerOrder;
    }

    public RequestBean getRequestBean() {
        return requestBean;
    }

    public void setRequestBean(RequestBean requestBean) {
        this.requestBean = requestBean;
    }
    private String currentOrder;
    @EJB
    private RequestBean requestBean;
    
    public OrderManager() {
        newCustomerOrder = new CustomerOrder();
        
    }

    public List<CustomerOrder> getOrders() {
        try {
            
            orders = requestBean.getOrders();
        } catch(Exception e) {
            System.out.println("Couldn't get orders");
        }
        
        return orders;
    }

    public void setOrders(List<CustomerOrder> orders) {
        this.orders = orders;
    }
    
    public void removeOrder(ActionEvent event) {
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("deleteOrderId");
            this.requestBean.removeOrder(param.getValue().toString());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void submitOrder() {
        try {
            this.requestBean.submitOrder(this.newCustomerOrder);
            this.newCustomerOrder = new CustomerOrder();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(String currentOrder) {
        this.currentOrder = currentOrder;
    }
}
