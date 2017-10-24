/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.bank.web;

import ejb.bank.business.BillPay;
import ejb.bank.business.Checking;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.print.attribute.standard.Severity;

/**
 *
 * @author jackle
 */
@SessionScoped
@Named("payment")
public class PaymentView implements Serializable {

    @EJB
    private Checking checking;
    
    @EJB
    private BillPay billPay;
    
    private String currentPayee;
    private double amount = 0;
    private Map<String, String> payeeMap;

    public Map<String, String> getPayees() {
        List<String> payees = this.billPay.getPayees();
        payeeMap = new LinkedHashMap<String, String>();
        for(String payee : payees){
            payeeMap.put(payee, payee);
        }
        return payeeMap;
    }
    
    public String getCurrentPayee() {
        if(this.currentPayee == null) {
            this.currentPayee = this.billPay.getPayees().get(0);
        }
        
        return this.currentPayee;
    }
    
    public void setCurrentPayee(String payee) {
        this.currentPayee = payee;
    }
    
    public double getAmount() {
        return this.amount;
    }
    
    public String makePayment() {
        if(checking.checkValidTransferAmount(amount)){
            this.billPay.doPay(this.checking.getAccountNumber(), currentPayee, amount);
            return ("confirm");
        }
        
        FacesMessage msg = new FacesMessage("Amount is bigger than your balance");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage("paymentForm:amountField", msg);
        
        return "";
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
