package com.fluidman.candemo.cansim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.fluidman.candemo.DispenserMachineIface;
import com.fluidman.candemo.DispenserMachineObserver;

public class SimulatedCanEventDriver implements DispenserMachineIface, Runnable {
	
	private List<DispenserMachineObserver> observers = new ArrayList<DispenserMachineObserver>();
	private String devices[] = new String[] {"Device 1", "Device 2", "Device 3"};
	private String faults[] = new String[] {"Fault 1", "Fault 2", "Fault 3"};
	private Thread sim_thread;
	private Random prng = new Random();
	
	public SimulatedCanEventDriver()
	{
		sim_thread = new Thread(this);
		sim_thread.start();
	}

	@Override
	public void registerObserver(DispenserMachineObserver observer) {
		observers.add(observer);
	}

	@Override
	public void unregisterObserver(DispenserMachineObserver observer) {
		observers.remove(observer);

	}

	@Override
	public void unregisterAllObservers() {
		observers.clear();

	}

	@Override
	public List<String> getAllDeviceIds() {
		return Arrays.asList(devices);
	}

	@Override
	public int getStatus() {
		return 0;
	}

	@Override
	public void resetAll() {
		for (String d : devices)
		{
			for (DispenserMachineObserver o : observers)
			{
				o.notifyOffline(d);
			}
		}
	}

	@Override
	public void run() {
		while (true)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			
			String device = devices[prng.nextInt(devices.length)];
			
			switch (prng.nextInt(6))
			{
			case 0:
				for (DispenserMachineObserver o : observers)
				{
					o.notifyOffline(device);
				}
				break;
			case 1:
				for (DispenserMachineObserver o : observers)
				{
					o.notifyOnline(device);
				}
				break;
			case 2:
			case 4:
				String fault = faults[prng.nextInt(faults.length)];
				for (DispenserMachineObserver o : observers)
				{
					o.notifyFault(device, fault);
				}
				break;
			case 3:
			case 5:
				fault = faults[prng.nextInt(faults.length)];
				for (DispenserMachineObserver o : observers)
				{
					o.notifyFaultClear(device, fault);
				}
				break;
			}
		}
		
	}

}
