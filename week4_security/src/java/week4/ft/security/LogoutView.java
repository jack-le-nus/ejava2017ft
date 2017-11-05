package week4.ft.security;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@RequestScoped
@Named
public class LogoutView {

	public String logout() {
		ExternalContext eCtx = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletRequest req = (HttpServletRequest)eCtx.getRequest();

		req.getSession().invalidate();

		return ("/index");

	}
	
}
