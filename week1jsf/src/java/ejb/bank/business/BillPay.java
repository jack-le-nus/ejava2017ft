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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.sql.DataSource;

/**
 *
 * @author jackle
 */
@Stateless
public class BillPay {
    private List<String> payees = new ArrayList<String>();
    private static final String FIND_USERS = "select * from CUSTOMER";
    @Resource(lookup = "ejavaftProject")
    private DataSource ds;
    @Resource(mappedName = "jms/myConnectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Resource(mappedName = "jms/myQueue")
    private Queue queue;
    
    public BillPay(List<String> payees) {
        this.payees = payees;
    }
    
    public BillPay() {
        
    }
    
    @PostConstruct
    private void init() {
        try(Connection c = ds.getConnection()) {
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(FIND_USERS);
            while(rs.next()) {
                payees.add(rs.getString("NAME"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<String> getPayees() {
        return payees;
    }

    public String doPay(String accountNumber, String currentPayee, double amount){
        String confirmation = new Long(System.currentTimeMillis()).toString();
        this.updateBackofficeRecords(accountNumber, currentPayee, amount, confirmation, new Date());
        return confirmation;
    }
    
    private void updateBackofficeRecords(String accountNumber, String payee, double amount, String confirmation, Date d){
        try (javax.jms.Connection jmsCtx = this.connectionFactory.createConnection()) {
//            JMSProducer producer = jmsCtx.createProducer();
//            TextMessage txtMsg = jmsCtx.createTextMessage();
//            txtMsg.setText(String.format("%s confirmed to pay %s to %d on %t", accountNumber, payee, amount, d));
//            producer.send(queue, txtMsg);
        } catch(JMSException e) {
            System.out.println(e.getMessage());
        }
    }
}
