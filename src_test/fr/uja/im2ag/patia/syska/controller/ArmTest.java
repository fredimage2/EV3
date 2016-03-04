package fr.uja.im2ag.patia.syska.controller;

import fr.uja.im2ag.patia.syska.controller.motion.Arm;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;

public class ArmTest {
	
	static int MOVEMENT_TEST_DURATION = 7000;

	public static void main(String[] args) throws InterruptedException {
		Arm arm = new Arm(MotorPort.C);
		LCD.clear();
		System.out.println("BEGIN CLOSING ARM");
		arm.closeArm();
		System.out.println("END CLOSING ARM");
		
		System.out.println("BEGIN OPENING ARM");
		arm.openArm();
		Thread.sleep(MOVEMENT_TEST_DURATION);
		System.out.println("END OPENING ARM");
		
		Thread.sleep(MOVEMENT_TEST_DURATION);
		
		System.out.println("STOP ARM");
		arm.stop();
		LCD.clear();
		System.out.println("EXIT");
		System.exit(0);
	}
}
