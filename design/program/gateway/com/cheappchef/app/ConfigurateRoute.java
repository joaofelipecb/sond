package com.cheappchef.app;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.Registry;

public class ConfigurateRoute extends RouteBuilder {

	public ConfigurateRoute() {
		// TODO Auto-generated constructor stub
	}

	public ConfigurateRoute(CamelContext context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void configure() throws Exception {
		Registry registry = getContext().getRegistry();
	    registry.bind("allowCacheFilterStrategy", new AllowCacheHeaderFilterStrategy());
	    registry.bind("cacheHash", new CacheHash());
	    registry.bind("getCSRFCookie", new GetCSRFCookie());
	}

}
