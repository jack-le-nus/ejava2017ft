package week2.web.giphy;

import java.io.IOException;
import java.io.PrintWriter;
import javax.cache.Cache;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class GiphyTask implements Runnable {

	private final String q;
	private final AsyncContext ctx;
	private final Cache cache;

	public GiphyTask(String q, AsyncContext ctx, Cache cache) {
		this.q = q;
		this.ctx = ctx;
		this.cache = cache;
	}

	@Override
	public void run() {
		System.out.println("... starting the runnable ...");
		Client client = ClientBuilder.newClient();

		WebTarget target = client.target("https://api.giphy.com/v1/gifs/search");
		target = target.queryParam("api_key", "__GIPHY_API__KEY__")
					.queryParam("q", q);

		Invocation.Builder invocation = target.request(MediaType.APPLICATION_JSON);

		JsonObject result = invocation.get(JsonObject.class);
		JsonArray imgArray = result.getJsonArray("data");

		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		for (JsonObject img: imgArray.getValuesAs(JsonObject.class)) {
			arrBuilder.add(img.getJsonObject("images")
					.getJsonObject("fixed_height")
					.getString("url"));
		}

		JsonArray images = arrBuilder.build();

		System.out.println(">>> caching result: " + q);
		cache.put(q, images.toString());

		client.close();

		HttpServletResponse resp = (HttpServletResponse)ctx.getResponse();

		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType(MediaType.APPLICATION_JSON);
		try (PrintWriter pw = resp.getWriter()) {
			pw.print(images.toString());
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		System.out.println("... completing the runnable ...");
		ctx.complete();
	}

	
	
}
