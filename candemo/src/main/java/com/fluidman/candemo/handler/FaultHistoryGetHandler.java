
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
import com.fluidman.candemo.model.DeviceFault;
import com.networknt.config.Config;
import com.networknt.service.SingletonServiceFactory;

public class FaultHistoryGetHandler implements HttpHandler {
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        
    	DispenserMachineObserver processor  = 
    			(DispenserMachineObserver)SingletonServiceFactory.getBean(DispenserMachineObserver.class);
    	
    	List<String> faulthistory = processor.getEventLog();
    	
    	exchange.getResponseHeaders().put(
                Headers.CONTENT_TYPE, "application/json");
    	
    	exchange.getResponseSender().send(Config.getInstance().getMapper().writeValueAsString(faulthistory));
        
    }
}
