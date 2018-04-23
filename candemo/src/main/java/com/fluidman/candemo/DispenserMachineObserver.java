package com.fluidman.candemo;

import java.util.LinkedList;
import java.util.List;

import com.fluidman.candemo.model.DeviceFault;

public interface DispenserMachineObserver {
    public void notifyFault(String deviceId, String faultCode);
    
    public void notifyFaultClear(String deviceId, String faultCode);
   
    public void notifyOnline(String deviceId);
   
    public void notifyOffline(String deviceId);
    
    public DispenserMachineIface getDispenserMachineIface();
    
    public List<DeviceFault> getDeviceFaults();
    
    public LinkedList<String> getEventLog();
    
}
