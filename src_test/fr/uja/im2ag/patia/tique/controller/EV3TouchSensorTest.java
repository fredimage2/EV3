package fr.uja.im2ag.patia.tique.controller;

// https://github.com/jabrena/liverobots/blob/master/src/test/java/jab/lejos/ev3/sensors/EV3TouchSensorTest.java
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;

public class EV3TouchSensorTest {


	public static void main(String[] args) throws InterruptedException {
		EV3TouchSensor touch = new EV3TouchSensor(SensorPort.S1);

		LCD.clear();
		
		while (!Button.ESCAPE.isDown()) {
			int sampleSize = touch.sampleSize();
			float[] sample = new float[sampleSize];
			touch.fetchSample(sample, 0);
	        LCD.drawString("TS: " + sample[0], 0, 0);
			System.out.println("TS: " + sample[0]);
			Thread.sleep(20);
	    }

		LCD.clear();
		System.out.println("EXIT");		
		System.exit(0);

	}

}
