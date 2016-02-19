import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;

/* test */

public class IRSensor 
{
	private static SampleProvider average;
    private static EV3UltrasonicSensor usSensor;
    private float[] distance;
    private final static double ERROR = 0.01;

    public IRSensor(Port port)
    {
    	irSensor = new EV3UltraSonicSensor(port);
    	average = new MeanFilter(irSensor.getDistanceMode(), 1);
    	distance = new float[average.sampleSize()];
    }

    public boolean isPressed()
    {
        float[] sample = new float[1];
        fetchSample(sample, 0);

        return sample[0] != 0;
    }
}
