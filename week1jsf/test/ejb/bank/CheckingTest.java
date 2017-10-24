/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.bank;

import ejb.bank.business.Checking;
import java.sql.ResultSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import week1.ft.web.Customer;

/**
 *
 * @author jackle
 */
public class CheckingTest {
    
    public CheckingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAccountNumber method, of class Checking.
     */
    @Test
    public void testGetAccountNumber() {
        System.out.println("getAccountNumber");
        Integer expResult = 8729;
        Customer customer = new Customer();
        customer.setCustomerId(8729);
        Checking instance = new Checking(customer);
        
        String result = instance.getAccountNumber();
        assertEquals(expResult.toString(), result);
    }

    /**
     * Test of getBalance method, of class Checking.
     */
    @Test
    public void testGetBalance() {
        System.out.println("getBalance");
        Double expResult = 1000d;
        Customer customer = new Customer();
        customer.setBalance(expResult);
        Checking instance = new Checking(customer);
        
        Double result = instance.getBalance();
        assertEquals(expResult, result);
    }

    /**
     * Test of doDeduct method, of class Checking.
     */
    @Test
    public void testDoDeduct() throws Exception {
        System.out.println("doDeduct");
        double amount = 0.0;
        Checking instance = new Checking(null);
        instance.doDeduct(amount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of checkValidTransferAmount method, of class Checking.
     */
    @Test
    public void testCheckValidTransferAmount() {
        System.out.println("checkValidTransferAmount");
        Double expResult = 1000d;
        Customer customer = new Customer();
        customer.setBalance(expResult);
        Checking instance = new Checking(customer);
        
        assertTrue(instance.checkValidTransferAmount(100d));
        assertFalse(instance.checkValidTransferAmount(10000d));
        assertFalse(instance.checkValidTransferAmount(1000d));
    }
    
}
