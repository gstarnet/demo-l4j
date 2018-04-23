package com.fluidman.candemo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;



import com.fluidman.candemo.cansim.SimulatedCanEventDriver;
import com.fluidman.candemo.model.DeviceFault;


public class CanEventProcessor implements DispenserMachineObserver {

	private DispenserMachineIface can_events = new SimulatedCanEventDriver();
	private HashMap<String, DeviceFault> deviceFaults = new HashMap<String, DeviceFault>();
	private LinkedList<String> eventLog = new LinkedList<String>();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public CanEventProcessor()
	{
		can_events.registerObserver(this);
		List<String> devices = can_events.getAllDeviceIds();
		
		for (String d : devices)
		{
			DeviceFault fault = new DeviceFault();
			fault.setDeviceId(d);
			fault.setStatus("online");
			fault.setFaults(new ArrayList<String>());
			deviceFaults.put(d, fault);
		}
	}
		
	private void updateEventLog(String log)
	{
		while (eventLog.size() >= 100)
		{
			eventLog.removeLast();
		}
		
		eventLog.addFirst(dateFormat.format(new Date()).toString() + " - " + log);
	}
	
	@Override
	public void notifyFault(String deviceId, String faultCode) {
		synchronized(deviceFaults)
		{
			DeviceFault df = deviceFaults.get(deviceId);
			
			if (df != null)
			{
				if (!df.getFaults().contains(faultCode))
				{
					df.getFaults().add(faultCode);
					updateEventLog(faultCode.toString() + " reported on " + deviceId);
				}
			}
		}
	}

	@Override
	public void notifyFaultClear(String deviceId, String faultCode) {
		synchronized(deviceFaults)
		{
			DeviceFault df = deviceFaults.get(deviceId);
			
			if (df != null)
			{
				if (df.getFaults().contains(faultCode))
				{
					df.getFaults().remove(faultCode);
					updateEventLog(faultCode.toString() + " cleared on " + deviceId);
				}
			}
		}

	}

	@Override
	public void notifyOnline(String deviceId) {
		synchronized(deviceFaults)
		{
			DeviceFault df = deviceFaults.get(deviceId);
			
			if (df != null)
			{
				if (df.getStatus().equals("offline"))
				{
					df.setStatus("online");
					updateEventLog(deviceId.toString() + " is now ONLINE");
				}
			}
		}
	}

	@Override
	public void notifyOffline(String deviceId) {
		synchronized(deviceFaults)
		{
			DeviceFault df = deviceFaults.get(deviceId);
			
			if (df != null)
			{
				if (df.getStatus().equals("online"))
				{
					df.setStatus("offline");
					updateEventLog(deviceId.toString() + " is now OFFLINE");
				}
			}
		}
	}

	public List<DeviceFault> getDeviceFaults() {
		return new ArrayList<DeviceFault>(deviceFaults.values());
	}

	public LinkedList<String> getEventLog() {
		return eventLog;
	}

	@Override
	public DispenserMachineIface getDispenserMachineIface() {
		return can_events;
	}

}
