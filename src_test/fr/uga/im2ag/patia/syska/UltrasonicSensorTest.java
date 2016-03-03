package fr.uga.im2ag.patia.syska;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;

public class UltrasonicSensorTest {
	public static void main(String[] args) throws InterruptedException {
		LCD.clear();
		UltrasonicSensor us = new UltrasonicSensor(SensorPort.S4);
		while (!Button.ESCAPE.isDown()) {
			us.currentDistance();
			Thread.sleep(100);
		}
		LCD.clear();
		System.out.println("EXIT");
		System.exit(0);
	}
}
