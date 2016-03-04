package fr.uga.im2ag.patia.syska.camera;

	import java.net.DatagramPacket;
	import java.net.DatagramSocket;
	import java.net.SocketException;
	import lejos.hardware.Button;

public class Palet {
    DatagramSocket dsocket ;
    byte[] buffer ;
    DatagramPacket packet ;

    public Palet() throws SocketException{
        int port = 8888;

        // Create a socket to listen on the port.
        this.dsocket = new DatagramSocket(port);

        // Create a buffer to read datagrams into. If a
        // packet is larger than this buffer, the
        // excess will simply be discarded!
        
        this.buffer = new byte[2048];
        // Create a packet to receive data into the buffer
        this.packet = new DatagramPacket(this.buffer, this.buffer.length);
    }

    
    /*
    *   a executer en boucle
    */
    public int[][] getPositionPalets() {
        try {
            // Wait to receive a datagram
            this.dsocket.receive(this.packet);

            // Convert the contents to a string, and display them
            String msg = new String(this.buffer, 0, this.packet.getLength());
            //System.out.println(packet.getAddress().getHostName() + ": "
            //    + msg);

            String[] palets = msg.split("\\n");

            int[][] pos = new int[palets.length][3] ;
            for (int i = 0; i < palets.length; i++) {
                String[] coord = palets[i].split(";");
                if(coord.length>2){
                    pos[i][0] = Integer.parseInt(coord[0]) ; // arbitrary index
                    pos[i][1] = Integer.parseInt(coord[1]) ; // x position
                    pos[i][2] = Integer.parseInt(coord[2]) ; // y position

                    //System.out.println(Integer.toString(x) + " / " + Integer.toString(x) );
                }
            }

            // Reset the length of the packet before reusing it.
            this.packet.setLength(this.buffer.length);
            this.packet.setData(buffer);
            
            return pos ;

        }catch(Exception e){
            System.err.println(e);
        }
        return null ;
    }
    
    public void end(){
        this.dsocket.close() ;
    }
}
