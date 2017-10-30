package week4.ft.security;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@ViewScoped
@Named
public class LoginView implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String answer;
	private int count = 0;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String login() {

		FacesContext fCtx = FacesContext.getCurrentInstance();
		ExternalContext extCtx = fCtx.getExternalContext();
		HttpServletRequest req = (HttpServletRequest)extCtx.getRequest();

		try {
			req.login(username, password);
		} catch (ServletException ex) {
			count++;
			fCtx.addMessage(null, new FacesMessage("Incorrect login"));
			return (null);
		}

		return ("protected/topsecret");
	}

}
