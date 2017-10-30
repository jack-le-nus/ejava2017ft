package week2.web.giphy.core;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppStartup implements ServletContextListener {

	@Resource(lookup = "concurrent/myThreadPool")
	private ManagedScheduledExecutorService service;

	//@Inject ApplicationQueue queue;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println(">>>> starting app");
		ScheduledFuture future = service.scheduleAtFixedRate(
				() -> {
					System.out.println(">>> running: " + new Date());
				}, 
				3, 5, TimeUnit.SECONDS);
		sce.getServletContext().setAttribute("thread", future);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println(">>>> stopping app");
		ScheduledFuture future = (ScheduledFuture)sce
				.getServletContext().getAttribute("thread");
		future.cancel(true);
	}
	
}
