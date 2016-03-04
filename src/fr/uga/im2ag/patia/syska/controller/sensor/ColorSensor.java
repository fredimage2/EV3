package fr.uga.im2ag.patia.syska.controller.sensor;
import lejos.hardware.Button;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.MeanFilter;


public class ColorSensor
{
    private static float[] path_color;
    private static float[] green;
    private static float[] blue;
    private static float[] red;
    private static float[] yellow;
    private static float[] black;
    private static float[] white;
    private static SampleProvider average;
    private static EV3ColorSensor colorSensor;
    private final static double ERROR = 0.01;
    
    public ColorSensor(Port port)
    {
    	colorSensor = new EV3ColorSensor(port);
    	average = new MeanFilter(colorSensor.getRGBMode(), 1);
		colorSensor.setFloodlight(Color.WHITE);
		
		System.out.println("Press enter to calibrate path color...");
		Button.ENTER.waitForPressAndRelease();
		path_color = new float[average.sampleSize()];
		average.fetchSample(path_color, 0);
		
		System.out.println("Press enter to calibrate green");
		Button.ENTER.waitForPressAndRelease();
		green = new float[average.sampleSize()];
		average.fetchSample(green, 0);
		
		System.out.println("Press enter to calibrate blue");
		Button.ENTER.waitForPressAndRelease();
		blue = new float[average.sampleSize()];
		average.fetchSample(blue, 0);
		
		System.out.println("Press enter to calibrate red");
		Button.ENTER.waitForPressAndRelease();
		red = new float[average.sampleSize()];
		average.fetchSample(red, 0);
		
		System.out.println("Press enter to calibrate yellow");
		Button.ENTER.waitForPressAndRelease();
		yellow = new float[average.sampleSize()];
		average.fetchSample(yellow, 0);
		
		System.out.println("Press enter to calibrate black");
		Button.ENTER.waitForPressAndRelease();
		black = new float[average.sampleSize()];
		average.fetchSample(black, 0);
		
		System.out.println("Press enter to calibrate white");
		Button.ENTER.waitForPressAndRelease();
		white = new float[average.sampleSize()];
		average.fetchSample(white, 0);
    }

    public boolean onPath()
    {
    	float[] sample = new float[average.sampleSize()];
		average.fetchSample(sample, 0);
		
		double scalaire = scalaire(sample, path_color);
		System.out.println(scalaire < ERROR);
		//Button.ENTER.waitForPressAndRelease();
		
		return scalaire(sample, path_color) < ERROR;
    	
    }
    
    public static double scalaire(float[] v1, float[] v2) {
		return Math.sqrt (Math.pow(v1[0] - v2[0], 2.0) +
				Math.pow(v1[1] - v2[1], 2.0) +
				Math.pow(v1[2] - v2[2], 2.0));
	}
    
    
    public boolean onGreen(){
    	float[] sample = new float[average.sampleSize()];
		average.fetchSample(sample, 0);
		double scalaire = scalaire(sample, path_color);
		System.out.println(scalaire);
		return scalaire(sample,green)< ERROR;
    }
    
    public boolean onBlue(){
    	float[] sample = new float[average.sampleSize()];
		average.fetchSample(sample, 0);
		double scalaire = scalaire(sample, path_color);
		//System.out.println(scalaire);
		return scalaire(sample,blue)< ERROR;
    }
    
    public boolean onRed(){
    	float[] sample = new float[average.sampleSize()];
		average.fetchSample(sample, 0);
		double scalaire = scalaire(sample, path_color);
		//System.out.println(scalaire);
		return scalaire(sample,red)< ERROR;
    }
    
    public boolean onYellow(){
    	float[] sample = new float[average.sampleSize()];
		average.fetchSample(sample, 0);
		double scalaire = scalaire(sample, path_color);
		//System.out.println(scalaire);
		return scalaire(sample,yellow)< ERROR;
    }
    
    public boolean onBlack(){
    	path_color[0] = 0.0f;
    	path_color[1] = 0.0f;
    	path_color[2] = 0.0f;
    	float[] sample = new float[average.sampleSize()];
		average.fetchSample(sample, 0);
		double scalaire = scalaire(sample, path_color);
		//System.out.println(scalaire);
		return scalaire(sample,black)< ERROR;
    }
     
    public boolean onWhite(){
    	float[] sample = new float[average.sampleSize()];
		average.fetchSample(sample, 0);
		double scalaire = scalaire(sample, path_color);
		//System.out.println(scalaire);
		return scalaire(sample,white)< ERROR;
    }
}
