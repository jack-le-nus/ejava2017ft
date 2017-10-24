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
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.servlet.http.HttpSession;

@SessionScoped
@Named
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	@Resource(lookup = "jms/factory")
	private ConnectionFactory factory;

	@Resource(lookup = "jms/warehouse")
	private Queue warehouseQueue;

	private List<String> contents = new LinkedList<>();
	private String item;

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
		try (JMSContext jmsCtx = factory.createContext()) {
			JMSProducer producer = jmsCtx.createProducer();
			TextMessage txtMsg = jmsCtx.createTextMessage();
			txtMsg.setText(new Date() + ">> " + contents.toString());
			producer.send(warehouseQueue, txtMsg);

		} catch (JMSException ex) {
			ex.printStackTrace();
		}
		System.out.println("checkout: " + contents);
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext extCtx = fc.getExternalContext();

		HttpSession sess = (HttpSession)extCtx.getSession(false);

		//HttpServletRequest req = (HttpServletRequest)extCtx.getRequest();
		//HttpSession sess = req.getSession();

		sess.invalidate();

		contents = new LinkedList<>();

		return ("index");
	}

	
}
