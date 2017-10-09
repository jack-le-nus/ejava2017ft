package week1.ft.web;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class RSVP implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String email;
	private String phone;

	@PostConstruct
	private void init() {
		System.out.println(">> creating RSVP");
	}

	@PreDestroy
	private void destroy() {
		System.out.println(">> destroying RSVP");
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public RSVP getContent() {
		RSVP rsvp = new RSVP();
		rsvp.email = email;
		rsvp.name = name;
		rsvp.phone = phone;
		return (rsvp);
	}
	
}
