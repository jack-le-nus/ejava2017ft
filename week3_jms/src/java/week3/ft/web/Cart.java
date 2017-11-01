package week3.ft.web;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.http.HttpSession;

@SessionScoped
@Named
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	@Resource(lookup = "jms/myConnectionFactory")
	private ConnectionFactory factory;

	@Resource(lookup = "jms/myQueue")
	private Queue warehouseQueue;

	private List<String> contents = new LinkedList<>();
	private String item;

        // URL of the JMS server. DEFAULT_BROKER_URL will just mean
        // that JMS server is on localhost
        private static String url = "tcp://localhost:61616";
        // default broker URL is : tcp://localhost:61616"

        private static String subject = "MYQUEUE"; //Queue Name
        // You can create any/many queue names as per your requirement.

	@PostConstruct
	private void init() {
		System.out.println(">>> creating a new session");
	}

	public void add(String item) {
		contents.add(item);
	}

	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}

	public List<String> getContents() {
		return contents;
	}
	public void setContents(List<String> contents) {
		this.contents = contents;
	}

	public void addToCart() {
		add(item);
		item = null;
	}

	public String checkout() {
//		try (JMSContext jmsCtx = factory.createContext()) {
//			JMSProducer producer = jmsCtx.createProducer();
//			TextMessage txtMsg = jmsCtx.createTextMessage();
//			txtMsg.setText(new Date() + ">> " + contents.toString());
//			producer.send(warehouseQueue, txtMsg);
//
//		} catch (JMSException ex) {
//			ex.printStackTrace();
//		}
//		System.out.println("checkout: " + contents);
//		FacesContext fc = FacesContext.getCurrentInstance();
//		ExternalContext extCtx = fc.getExternalContext();
//
//		HttpSession sess = (HttpSession)extCtx.getSession(false);
//
//		//HttpServletRequest req = (HttpServletRequest)extCtx.getRequest();
//		//HttpSession sess = req.getSession();
//
//		sess.invalidate();
//
//		contents = new LinkedList<>();


                try(javax.jms.Connection connection = factory.createConnection()) {
                // JMS messages are sent and received using a Session. We will
                // create here a non-transactional session object. If you want
                // to use transactions you should set the first parameter to 'true'
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                // Destination represents here our queue 'VALLYSOFTQ' on the
                // JMS server. You don't have to do anything special on the
                // server to create it, it will be created automatically.
                Destination destination = session.createQueue(subject);
                // MessageProducer is used for sending messages (as opposed
                // to MessageConsumer which is used for receiving them)
                MessageProducer producer = session.createProducer(destination);
                // We will send a small text message saying 'Hello' in Japanese
                TextMessage message = session.createTextMessage("Hello welcome come to vallysoft ActiveMQ!");
                // Here we are sending the message!
                producer.send(message);
                System.out.println("Sentage '" + message.getText() + "'");} catch(Exception e) {
                    e.printStackTrace();
                }
		return ("index");
	}

	
}
