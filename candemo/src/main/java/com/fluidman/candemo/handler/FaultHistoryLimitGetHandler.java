
package com.fluidman.candemo.handler;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.HttpString;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fluidman.candemo.CanEventProcessor;
import com.fluidman.candemo.DispenserMachineObserver;
import com.networknt.config.Config;
import com.networknt.service.SingletonServiceFactory;

public class FaultHistoryLimitGetHandler implements HttpHandler {
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        
    	DispenserMachineObserver processor  = 
    			(DispenserMachineObserver)SingletonServiceFactory.getBean(DispenserMachineObserver.class);
    	
    	String query = exchange.getQueryString();
    	
    	// process from reuqest map.
    	int length = 3;
   
    	List<String> faulthistory;
    	
    	if (length >= processor.getEventLog().size())
		{
    		faulthistory =  processor.getEventLog();
		}
		else
		{
			faulthistory =  processor.getEventLog().subList(0, length);
		}
    	
    	exchange.getResponseHeaders().put(
                Headers.CONTENT_TYPE, "application/json");
    	exchange.getResponseSender().send(Config.getInstance().getMapper().writeValueAsString(faulthistory));
        
    }
}
