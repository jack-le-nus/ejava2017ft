/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaee.order.ejb;

import java.io.Serializable;
import java.util.List;
import javaee.order.entity.CustomerOrder;
import javaee.order.entity.Vendor;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jackle
 */
@Stateless
public class RequestBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @PersistenceContext private EntityManager em;
    
    public RequestBean() {
        
    }

    public List<CustomerOrder> getOrders() throws Exception {
        try {
            return em.createNamedQuery("findAllOrders").getResultList();
        } catch (EJBException e) {
            throw new Exception (e.getMessage());
        }
    }

    public void removeOrder(String deleteOrderId) throws Exception {
        try {
            CustomerOrder order = em.find(CustomerOrder.class, deleteOrderId);
            em.remove(order);
        } catch(EJBException e) {
            throw new Exception(e.getMessage());
        }
    }

    public void submitOrder(CustomerOrder newCustomerOrder) throws Exception {
        try {
            em.persist(newCustomerOrder);
        } catch(EJBException e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Vendor> searchVendor(String searchVendorName) throws Exception {
        try {
            return em.createNamedQuery("findAllVendorsByVendorName")
                    .setParameter("name", searchVendorName).getResultList();
        } catch(EJBException e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public void createVendor(Vendor vendor) throws Exception {
        try {
            em.persist(vendor);
        } catch(EJBException e) {
            throw new Exception(e.getMessage());
        }
    }
}
