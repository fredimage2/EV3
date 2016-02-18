package fr.uja.im2ag.patia.tique.controller;

import lejos.hardware.Button;
import lejos.utility.Delay;

public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			System.out.println("Hello today!");
			Button.ENTER.waitForPress();

		} catch (Throwable t) {
			t.printStackTrace();
			Delay.msDelay(10000);
			System.exit(0);
		}
	}

}