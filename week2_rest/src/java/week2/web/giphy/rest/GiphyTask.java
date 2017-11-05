package week2.web.giphy.rest;

import java.io.IOException;
import java.io.PrintWriter;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class GiphyTask implements Runnable {

	private final AsyncResponse asyncResponse;
	private final String q;

	public GiphyTask(AsyncResponse asyncResponse, String q) {
		this.asyncResponse = asyncResponse;
		this.q = q;
	}

	@Override
	public void run() {

		System.out.println("... starting the runnable ...");
		Client client = ClientBuilder.newClient();

		WebTarget target = client.target("https://api.giphy.com/v1/gifs/search");
		target = target.queryParam("api_key", "__API_KEY__")
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

		client.close();

		JsonArray images = arrBuilder.build();

		System.out.println(">>> resuming response");
		asyncResponse.resume(Response.ok(images).build());

	}
	
}
