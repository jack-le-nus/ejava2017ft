/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.bank;

import ejb.bank.business.BillPay;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jackle
 */
public class BillPayTest {
    
    public BillPayTest() {
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
     * Test of getPayees method, of class BillPay.
     */
    @Test
    public void testGetPayees() {
        System.out.println("getPayees");
        List<String> payees = new ArrayList<String>();
        payees.add("Jack");
        payees.add("Daniel");
        BillPay instance = new BillPay(payees);
        List<String> result = instance.getPayees();
        assertEquals(payees, result);
    }

    /**
     * Test of doPay method, of class BillPay.
     */
    @Test
    public void testDoPay() {
        System.out.println("doPay");
        String accountNumber = "";
        String currentPayee = "";
        double amount = 0.0;
        BillPay instance = new BillPay(null);
        String expResult = "";
        String result = instance.doPay(accountNumber, currentPayee, amount);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
