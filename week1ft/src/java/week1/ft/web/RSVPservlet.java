package week1.ft.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/rsvp"})
public class RSVPservlet extends HttpServlet {

	@Inject private RSVP rsvp;
	@Inject private Attendence attendence;

	public RSVPservlet(String n) { }

	public RSVPservlet() { }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		final String name = rsvp.getName();
		final String email = rsvp.getEmail();
		final String phone = rsvp.getPhone();

		System.out.println(
				String.format("name: %s, email: %s, phone: %s", name, email, phone));
		System.out.println(">> class for RSVP: " + rsvp.getClass().getName());
		System.out.println(">> content for RSVP: " + rsvp.getContent().getClass().getName());
                
		//attendence.add(rsvp.getContent());
		attendence.add();
                
		resp.setStatus(HttpServletResponse.SC_ACCEPTED);
		resp.setContentType("text/html");

		try (PrintWriter pw = resp.getWriter()) {
			pw.println("<h1>Thank you for RSVPing</h1>");
			pw.println(String.format("<h1>Number of attendence: %d</h1>", attendence.getAttendence().size()));
			pw.println("<ul>");
			for (RSVP r: attendence.getAttendence())
				pw.println(String.format("<li>name = %s</li>", r.getName()));
			pw.println("</ul>");
			pw.flush();
                        
                        pw.println(String.format("<h1>Number of attendence: %d</h1>", attendence.getAttendence().size()));
		}
	}

	
	
}
