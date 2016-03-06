package fr.uga.im2ag.patia.syska.controller.motion;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;


public class ArmClamp extends EV3LargeRegulatedMotor {
	
	private Port port;
	private boolean isRunning;
	
	/////Constructor/////////////////////////////////////////
	
	public ArmClamp(Port p){
		super(p);
		this.port = p;
		this.isRunning = false;
	}
	
	public Port getPort() {
		return port;
	}
	
	public void closeArmMvt(){
		super.backward();
		this.isRunning = true;
	}
	
	public void openArmMvt(){
		super.forward();
		this.isRunning = true;
	}
	
	public void stop(){
		super.stop();
		this.isRunning = false;
	}
	
	public boolean isRunning() {
		return this.isRunning;
	}
}
