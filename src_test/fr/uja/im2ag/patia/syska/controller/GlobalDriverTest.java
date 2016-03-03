package fr.uja.im2ag.patia.syska.controller;

import fr.uja.im2ag.patia.syska.statemachine.StateMachine;
import fr.uja.im2ag.patia.syska.statemachine.action.DrivingForwardAction;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;

public class GlobalDriverTest {
	
	public static void main(String[] args) throws InterruptedException {
		GlobalController globalController = new GlobalController(SensorPort.S2, MotorPort.B,
				MotorPort.D);
		LCD.clear();
		StateMachine sm = new StateMachine();
		sm .setCurrentAction(new DrivingForwardAction(sm,globalController));
		sm.executeAction();	
		Thread.sleep(5000);
		LCD.clear();
		System.out.println("EXIT");
		System.exit(0);
	}
}
