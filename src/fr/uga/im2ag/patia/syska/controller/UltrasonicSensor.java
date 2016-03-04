         package fr.uga.im2ag.patia.syska.controller;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.MeanFilter;

/* test */

public class UltrasonicSensor 
{
	private static SampleProvider average;
    private static EV3UltrasonicSensor usSensor;
    private float[] distance;
    private final static double ERROR = 0.01;

    public UltrasonicSensor(Port port)
    {
    	usSensor = new EV3UltrasonicSensor(port);
    	usSensor.enable();
    	average = new MeanFilter(usSensor.getDistanceMode(), 1);
    	distance = new float[average.sampleSize()];
    }

    public float currentDistance(){
    	
    	float[] sample = new float[average.sampleSize()];
		average.fetchSample(sample, 0);
		
		System.out.print("Current distance : ");
		for(int i=0;i<sample.length;i++){
			System.out.print(sample[i] + " ");
		}
		System.out.println();
		//Button.ENTER.waitForPressAndRelease();
		
		return sample[0];
    }
    
}

