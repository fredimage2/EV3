package fr.uja.im2ag.patia.syska.controller.motion;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;

public class Wheel extends EV3LargeRegulatedMotor {

	private Port port;
	private int speed;
	
	/////Constructor/////////////////////////////////////////
	
	public Wheel(Port p){
		super(p);
		this.port = p;
		this.speed = 50;
		super.setSpeed(speed);
	}
	
	public Wheel(Port p, int s){
		super(p);
		this.port = p;
		this.speed = s;
		super.setSpeed(s);
	}

	/////Getter & Setter/////////////////////////////////////////

	public Port getPort() {
		return port;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
		super.setSpeed(speed);
	}

	/////Methods////////////////////////////////////////////////////////

	public void forward(){
		super.forward();
	}
	
	public void backward(){
		super.backward();
	}
	
	public void stop(){
		super.stop();
	}
}