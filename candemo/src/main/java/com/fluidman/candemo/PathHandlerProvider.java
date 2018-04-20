
package com.fluidman.candemo;

import com.networknt.config.Config;
import com.networknt.server.HandlerProvider;
import io.undertow.Handlers;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Methods;
import com.networknt.info.ServerInfoGetHandler;
import com.networknt.health.HealthGetHandler;

import com.fluidman.candemo.handler.*;

public class PathHandlerProvider implements HandlerProvider {
    @Override
    public HttpHandler getHandler() {
        return Handlers.routing()
        
            .add(Methods.GET, "/WIDDS/v1/device-faults", new DeviceFaultsGetHandler())
        
            .add(Methods.POST, "/WIDDS/v1/commands/RESET", new CommandsRESETPostHandler())
        
            .add(Methods.GET, "/WIDDS/v1/health", new HealthGetHandler())
        
            .add(Methods.GET, "/WIDDS/v1/fault-history", new FaultHistoryGetHandler())
        
            .add(Methods.GET, "/WIDDS/v1/fault-history/{limit}", new FaultHistoryLimitGetHandler())
        
            .add(Methods.GET, "/WIDDS/v1/server/info", new ServerInfoGetHandler())
        
        ;
    }
}
