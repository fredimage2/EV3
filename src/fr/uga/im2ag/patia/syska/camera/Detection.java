package fr.uga.im2ag.patia.syska.camera;

import java.net.SocketException;

import fr.uga.im2ag.patia.syska.camera.Palet ;

public class Detection {
	public static void main(String[] args) throws SocketException{
		Palet p = new Palet() ;
		int[][] pos = p.getPositionPalets() ;
		while(true){
			for(int i = 0 ; i<pos.length ; i++){
				System.out.println("Palet "+pos[i][0]+" : ("+pos[i][1]+","+pos[i][2]+")") ;
			}
			pos = p.getPositionPalets() ;
		}
	}
}
