package week1.ft.web;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named("rsvp")
public class RSVP implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject private Attendence attendence;

	private String name;
	private String email;
	private String phone;
	private Integer guest;

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

	public Integer getGuest() {
		return guest;
	}
	public void setGuest(Integer guest) {
		this.guest = guest;
	}

	public RSVP getContent() {
		RSVP rsvp = new RSVP();
		rsvp.email = email;
		rsvp.name = name;
		rsvp.phone = phone;
		rsvp.guest = guest;
		return (rsvp);
	}

	public String rsvpMe() {
		System.out.println(">> name = " + name);
		System.out.println(">> email = " + email);
		System.out.println(">> phone = " + phone);
		System.out.println(">> guest = " + guest);
		if (guest <= -1) {
			FacesMessage msg = new FacesMessage("You cannot have negative guest");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance()
					.addMessage("rsvpForm:guestField", msg);
			return (null);
		}
		attendence.add();
		name = "";
		email = "";
		phone = "";
		guest = null;

		return ("attendence");

	}
	
}
