/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaee.order.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author jackle
 */
@Entity
@Table(name="VENDOR")
@NamedQueries(
        {
            @NamedQuery(name="findAllVendors",
        query="SELECT v FROM Vendor v"),
            @NamedQuery(name="findAllVendorsByVendorName",
        query="SELECT v FROM Vendor v WHERE v.name = :name")
        })

public class Vendor implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String vendorId;

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    private String name;
    private String address;
    private String contact;
    private String phone;
    
    public Vendor() {
        
    }
    
    public Vendor(String vendorId, String name, String address, String contact, String phone) {
        this.vendorId = vendorId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.phone = phone;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s %s %s %s", this.vendorId, this.name, this.address, this.contact, this.phone);
    }
}
