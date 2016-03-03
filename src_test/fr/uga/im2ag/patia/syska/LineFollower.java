package fr.uga.im2ag.patia.syska;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;
import fr.uga.im2ag.patia.syska.controller.*;


public class LineFollower
{
    public static void main(String[] args)
    {
        Controller controller = new Controller(SensorPort.S1, MotorPort.B, MotorPort.D);
        
        controller.run();
        
    }
}