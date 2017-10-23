package week2.web.giphy;

import javax.cache.Cache;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class JSR107Cache {

	@Inject Cache cache;

	public Cache getCache() {
		return cache;
	}
	public void setCache(Cache cache) {
		this.cache = cache;
	}
	
}
