
package com.fluidman.candemo.handler;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HttpString;
import java.util.HashMap;
import java.util.Map;

import com.fluidman.candemo.CanEventProcessor;
import com.fluidman.candemo.DispenserMachineIface;
import com.fluidman.candemo.DispenserMachineObserver;
import com.networknt.service.SingletonServiceFactory;

public class CommandsRESETPostHandler implements HttpHandler {
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        
    	DispenserMachineObserver processor  = 
    			(DispenserMachineObserver)SingletonServiceFactory.getBean(DispenserMachineObserver.class);
    	processor.getDispenserMachineIface().resetAll();
        exchange.endExchange();
        
    }
}
