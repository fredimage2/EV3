package fr.uja.im2ag.patia.syska.controller;

import fr.uja.im2ag.patia.syska.controller.motion.Motion;
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
		return touchSensor;
	}

	public Motion getDrive() {
		return drive;
	}
	
	public void log(String msg) {
		System.out.println("log>\t" + msg);
	}
	
}
