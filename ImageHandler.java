import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImageHandler {
	
	private static String path = "D:/Users/jjode/OneDrive/Documents/JJ's Folder/Thesis Practice/Images/";
	private static ArrayList<Image> scen1 = new ArrayList<Image>();
	private static Image rocket = null;
	private static Image capsule = null;
	private static ArrayList<Image> scen2 = new ArrayList<Image>();
	public ImageHandler(){
		try{
			Image sun = ImageIO.read(new File(path+"Sun.png"));
			scen1.add(sun);
			Image mercury = ImageIO.read(new File(path+"Mercury.png"));
			scen1.add(mercury);
			Image venus = ImageIO.read(new File(path+"Venus.png"));
			scen1.add(venus);
			Image earth = ImageIO.read(new File(path+"Earth.png"));
			scen1.add(earth);
			Image mars = ImageIO.read(new File(path+"Mars.png"));
			scen1.add(mars);
			
			rocket = ImageIO.read(new File(path+"Rocket.png"));
			
			scen2.add(earth);
			Image sat = ImageIO.read(new File(path+"Satellite.png"));
			scen2.add(sat);
			scen2.add(sat);
			
			capsule = ImageIO.read(new File(path+"Capsule.png"));
		}
		catch(IOException e){System.out.print("failed");}
	}
	
	public static ArrayList<Image> getScen1(){
		return scen1;
	}
	
	public static Image getRocket(){
		return rocket;
	}
	
	public static ArrayList<Image> getScen2(){
		return scen2;
	}
	
	public static Image getCapsule(){
		return capsule;
	}
}
