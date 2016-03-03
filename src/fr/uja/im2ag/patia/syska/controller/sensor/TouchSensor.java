package fr.uja.im2ag.patia.syska.controller.sensor;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;

public class TouchSensor {
	
	private EV3TouchSensor touchSensor;
	
	public TouchSensor(Port sensor_port) {
		touchSensor = new EV3TouchSensor(sensor_port);
	}
	
	boolean isTouching() {
		int sampleSize = touchSensor.sampleSize();
		float[] sample = new float[sampleSize];
		touchSensor.fetchSample(sample, 0);
		return ((touchSensor == null) || (sample[0] != 0));
	}
}
