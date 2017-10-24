/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.bank.web;

import ejb.bank.business.BillPay;
import ejb.bank.business.Checking;
import java.io.Serializable;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.faces.FacesException;
import javax.inject.Named;

/**
 *
 * @author jackle
 */
@SessionScoped
@Named("account")
public class AccountView implements Serializable {

    @EJB
    private Checking checking;
    
    @EJB
    private BillPay billPay;
    
    private boolean paymentOK = false;
    private String paymentConfirmation = null;
    
    public String getCheckingAccountNumber() {
        return this.checking.getAccountNumber();
    }
    
    public double getCheckingBalance() {
        return this.checking.getBalance();
    }
    
    public void reset() {
        this.paymentOK = false;
        this.paymentConfirmation = null;
    }
    
    public String getPaymentConfirmation() {
        return this.paymentConfirmation;
    }
    
    public boolean getPaymentOK() {
        return this.paymentOK;
    }
    
    public String makePayment (){
//        try {
//            this.checking.doDeduct(this.amount);
//            String confirmation = this.billPay.doPay(
//            this.checking.getAccountNumber(),
//                    currentPayee,
//                    this.amount);
//            this.paymentOK = true;
//            this.paymentConfirmation = confirmation;
//            this.amount = 15;
//            this.currentPayee = null;
//            
//        } catch (PaymentException pe) {
//            throw new FacesException(pe.getMessage());
//        }

        return ("pay");
    }
}
