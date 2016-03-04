package fr.uja.im2ag.patia.syska.controller.motion;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;

public class Arm extends EV3LargeRegulatedMotor {
	
	static int ANGLE_MIN = 1;
	static int ANGLE_MAX = 3;

	private Port port;
	private int angle;
	
	/////Constructor/////////////////////////////////////////
	
	public Arm(Port p){
		super(p);
		this.port = p;
		this.angle = 0;
		super.setSpeed(speed);
	}
	
	public Arm(Port p, int a){
		super(p);
		this.port = p;
		this.angle = a;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public Port getPort() {
		return port;
	}
	
	public void closeArm(){
		super.forward();
	}
	
	public void openArm(){
		super.backward();
	}
	
	public void stop(){
		super.stop();
	}
}
