package fr.uja.im2ag.patia.syska.controller;

import fr.uja.im2ag.patia.syska.controller.motion.Motion;
import fr.uja.im2ag.patia.syska.controller.sensor.TouchSensor;
import lejos.hardware.port.Port;

public class GlobalController {

	private TouchSensor touchSensor;
	private Motion drive;

	public GlobalController(Port sensor_port, Port left_port, Port right_port) {
		log("Initializing Controller");

		touchSensor = new TouchSensor(sensor_port);
		drive = new Motion(left_port, right_port);
	}
	
	public TouchSensor getTouchSensor() {
		return this.touchSensor;
	}

	public Motion getDrive() {
		return drive;
	}
	
	public void log(String msg) {
		System.out.println("log>\t" + msg);
	}
	
	public void log(String msg,boolean carriage_return) {
		if (carriage_return)
			log(msg);
		else
			System.out.print(msg);
	}
	
}
