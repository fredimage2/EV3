package fr.uga.im2ag.patia.syska;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;

public class ColorSensorTest {
	public static void main(String[] args) throws InterruptedException {
		LCD.clear();
		ColorSensor cs = new ColorSensor(SensorPort.S1);
		while (!Button.ESCAPE.isDown()) {
			if(cs.onBlack()){
				System.out.println("On Black");
			}
			else if(cs.onBlue()){
				System.out.println("On Blue");
			}
			else if(cs.onGreen()){
				System.out.println("On Green");
			}
			else if(cs.onRed()){
				System.out.println("On Red");
			}
			else if(cs.onWhite()){
				System.out.println("On White");
			}
			else if(cs.onYellow()){
				System.out.println("On Yellow");
			}
			else{
				System.out.println("Color not detected");
			}
			Thread.sleep(5000);
		}
		LCD.clear();
		System.out.println("EXIT");
		System.exit(0);
	}
}
