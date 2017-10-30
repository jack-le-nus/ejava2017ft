package week4.ft.ws;

import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@RequestScoped
@ServerEndpoint("/chat/{room}")
public class Chat {

	private Session session;

	@OnMessage
	public void message(String message, @PathParam("room") String room) {
		System.out.println(">>>>message = " + message);
		try {
			for (Session s: session.getOpenSessions())
				s.getBasicRemote().sendText(new Date() + ": " + message);
		} catch (Throwable t) {
			t.printStackTrace();
			try { session.close(); } catch (Throwable tt) { }
		}
	}

	@OnOpen
	public void open(Session sess) {
		session = sess;
		System.out.println(">>>connection opened: " + session.getId());
	}

	@OnClose
	public void close(CloseReason reason) {
		System.out.println(">>> connection closing: " + session.getId());
		System.out.println("\treason = " + reason.toString());
	}
	
}
