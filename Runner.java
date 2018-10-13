

import java.awt.EventQueue;
import java.awt.Point;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Runner{
	
	public static ArrayList<Entity> drawPlanets = new ArrayList<Entity>();
	
	public static ArrayList<ArrayList<Point>> pathPoints=new ArrayList<ArrayList<Point>>();
	
	public static ImageHandler images = new ImageHandler();
	
	public static GUIMenu handle = new GUIMenu("Rocket Orbital Simulator");

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable()
			{
				public void run() {
					
					try {
						
						handle.setVisible(true);
						
						handle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						
						handle.setResizable(false);
								
					} catch (Exception e) {
						
						e.printStackTrace();
						
					}
				}
			}
		);
	}
}
