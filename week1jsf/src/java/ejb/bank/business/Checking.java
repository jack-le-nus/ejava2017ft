/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.bank.business;

import ejb.bank.utilities.PaymentException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import week1.ft.web.Customer;

/**
 *
 * @author jackle
 */
@Stateless
public class Checking {

    private static final String FIND_CURRENT_ACCOUNT = "select * from CUSTOMER where CUSTOMER_ID = 8729";
    
    private Customer currentCustomer = null;
    
    @Resource(lookup = "ejavaftProject")
	private DataSource ds;
    
    public Checking(Customer customer) {
        currentCustomer = customer;
    }
    
    public Checking() {
        
    }
    
    @PostConstruct
    private void init() {
        try (Connection conn = ds.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(FIND_CURRENT_ACCOUNT);
            if (rs.next()) {
               currentCustomer = Customer.populate(rs);
            }

        } catch (SQLException ex) {
                ex.printStackTrace();
        }
    }
    
    public String getAccountNumber() {
        return currentCustomer.getCustomerId().toString();
    }

    public double getBalance() {
        return currentCustomer.getBalance();
    }
    
    public boolean checkValidTransferAmount(double amount) {
        return amount < this.getBalance();
    }

    public void doDeduct(double amount) throws PaymentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
