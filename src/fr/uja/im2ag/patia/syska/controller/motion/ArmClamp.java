package fr.uja.im2ag.patia.syska.controller.motion;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;


public class ArmClamp extends EV3LargeRegulatedMotor {
	
	private Port port;
	
	/////Constructor/////////////////////////////////////////
	
	public ArmClamp(Port p){
		super(p);
		this.port = p;
	}
	
	public Port getPort() {
		return port;
	}
	
	public void closeArmMvt(){
		super.backward();
	}
	
	public void openArmMvt(){
		super.forward();
	}
	
	public void stop(){
		super.stop();
	}
}
