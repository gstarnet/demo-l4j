package com.fluidman.candemo;

import java.util.List;

public interface DispenserMachineIface {
    public static final int STATUS_OK = 0;
    public static final int STATUS_COMM_ERROR = -1;
   
    /* initialization logic to be carried out in constructor */
   
    public void registerObserver(DispenserMachineObserver observer);
   
    public void unregisterObserver(DispenserMachineObserver observer);
   
    public void unregisterAllObservers();
   
    public List<String> getAllDeviceIds();
   
    public int getStatus();
   
    public void resetAll();
}
