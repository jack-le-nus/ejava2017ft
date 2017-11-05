package week2.web.giphy.rest;

import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/giphy")
public class GiphyResource {

	@Resource(lookup = "concurrent/myThreadPool")
	private ManagedScheduledExecutorService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public void get(@QueryParam("q")String q,
			@Suspended AsyncResponse asyncResp) {

		GiphyTask task = new GiphyTask(asyncResp, q);

		System.out.println(">>> scheduling the task to run after 5 sec");
		service.schedule(task, 5, TimeUnit.SECONDS);

		System.out.println(">> exiting");
	}
	
}
