package fr.uga.im2ag.patia.syska.controller.motion;

import lejos.hardware.port.Port;
import lejos.utility.Delay;

public class Motion{

	private Wheel left;
	private Wheel right;
	
	/////Constructor/////////////////////////////////////////
	
	public Motion(Port l, Port r){
		this.left = new Wheel(l);
		this.right = new Wheel(r);
	}
	
	public Motion(Port l, Port r, int s){
		this.left = new Wheel(l, s);
		this.right = new Wheel(r, s);
	}
	
	/////Getter & Setter/////////////////////////////////////////

	public Wheel getLeft() {
		return left;
	}

	public void setLeft(Wheel left) {
		this.left = left;
	}

	public Wheel getRight() {
		return right;
	}

	public void setRight(Wheel right) {
		this.right = right;
	}
	
	/////Methods////////////////////////////////////////////////////////

	public void rotateLeft(){
		this.left.backward();
		this.right.forward();
	}
	
	public void rotateRight(){
		this.left.forward();
		this.right.backward();
	}

	public void forward(){
		this.left.forward();
		this.right.forward();
	}
	
	public void stop(){
		this.left.stop();
		this.right.stop();
	}
	
	// TODO: A TESTER
	// ATTENTION: Le robot est bloqué durant "milliseconds" 
	public void forwardDuring(int milliseconds){
		this.forward();
		Delay.msDelay(milliseconds);
		this.stop();	
	}

	
}