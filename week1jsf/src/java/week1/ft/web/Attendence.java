package week1.ft.web;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class Attendence implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject private RSVP rsvp;

	private List<RSVP> attendence = new LinkedList<>();

	public List<RSVP> getAttendence() {
		return attendence;
	}
	public void setAttendence(List<RSVP> attendence) {
		this.attendence = attendence;
	}

	public void add() {
		add(rsvp.getContent());
	}

	public void add(RSVP rsvp) {
		this.attendence.add(rsvp);
	}

	public void delete(String email) {
		List<RSVP> del = new LinkedList<>();
		for (RSVP r: attendence)
			if (!r.getEmail().equals(email))
				del.add(r);
		attendence = del;
	}
}
