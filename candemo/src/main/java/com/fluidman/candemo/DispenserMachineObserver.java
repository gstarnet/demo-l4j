package com.fluidman.candemo;

public interface DispenserMachineObserver {
    public void notifyFault(String deviceId, String faultCode);
    
    public void notifyFaultClear(String deviceId, String faultCode);
   
    public void notifyOnline(String deviceId);
   
    public void notifyOffline(String deviceId);
}
