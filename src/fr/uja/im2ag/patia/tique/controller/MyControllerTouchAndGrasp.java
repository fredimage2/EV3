package fr.uja.im2ag.patia.tique.controller;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.utility.Delay;

public class MyControllerTouchAndGrasp {

	private final static int DELAY = 25;

	private EV3TouchSensor touchSensor;
	private DifferentialDrive drive;

	public MyControllerTouchAndGrasp(Port sensor_port, Port left_port, Port right_port) {
		log("Initializing Controller");

		touchSensor = new EV3TouchSensor(sensor_port);
		drive = new DifferentialDrive(left_port, right_port);
	}

	public static void main(String[] args) throws InterruptedException {
		MyControllerTouchAndGrasp touchAndGraspController = new MyControllerTouchAndGrasp(SensorPort.S2, MotorPort.B,
				MotorPort.D);
		LCD.clear();
		touchAndGraspController.run();	
		Thread.sleep(5000);
		LCD.clear();
		System.out.println("EXIT");
		System.exit(0);

	}

	boolean isTouching() {
		int sampleSize = touchSensor.sampleSize();
		float[] sample = new float[sampleSize];
		touchSensor.fetchSample(sample, 0);
		return ((touchSensor == null) || (sample[0] != 0));
	}

	public void run() {
		log("On roule jusqu'à toucher.");
		move();
		log("On a touché (ou escape enfoncée).");
	}

	private void move() {
		log("En avant !");
		full_move();
		log("Stop");
	}

	private void full_move() {
		log("Avance.");
		drive.forward();
		
		while ((!Button.ESCAPE.isDown()) && (!this.isTouching())) {
			delay();
		}
		log("On touche, on s'arrête (ou esacape).");
		drive.stop();
	}

	private void delay() {
		Delay.msDelay(DELAY);
	}

	private static void log(String msg) {
		System.out.println("log>\t" + msg);
	}
}
