package week2.web.giphy;

import java.io.IOException;
import java.io.PrintWriter;
import javax.cache.Cache;
import javax.cache.annotation.CacheDefaults;
import javax.inject.Inject;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

@CacheDefaults(cacheName = "giphy")
@WebServlet(urlPatterns = "/giphy", asyncSupported = true)
public class GiphyServlet extends HttpServlet {

	@Inject JSR107Cache jsr107Cache;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		String q = req.getParameter("q");
		Cache cache = jsr107Cache.getCache();

		System.out.println(">>>cache = " + cache);

		if (cache.containsKey(q)) {
			System.out.println(" returning result from cache");
			String cachedResult = (String)cache.get(q);
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.setContentType(MediaType.APPLICATION_JSON);
			try (PrintWriter pw = resp.getWriter()) {
				pw.print(cachedResult);
			}
			return;
		}

		System.out.println("--- go an fetch the result from Giphy");
		AsyncContext ctx = req.startAsync(req, resp);

		ctx.start(new GiphyTask(q, ctx, cache));

		System.out.println("--- exiting GiphyServlet - the request will be suspended");
	}

	
	
}
