package com.cheappchef.app;

import org.apache.camel.Exchange;
import org.apache.camel.spi.HeaderFilterStrategy;

public class AllowCacheHeaderFilterStrategy implements HeaderFilterStrategy {

	public AllowCacheHeaderFilterStrategy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean applyFilterToCamelHeaders(String headerName, Object headerValue, Exchange exchange) {
		if (headerName.toLowerCase().startsWith ("camel")) {
		
			return true;
		}
		else {
			
			return false;
		}
		
	}

	@Override
	public boolean applyFilterToExternalHeaders(String headerName, Object headerValue, Exchange exchange) {
		if (headerName.toLowerCase().equals("cookie")) {
			
			return false;
		}
		
		return false;
	}

}
