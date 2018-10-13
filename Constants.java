import javax.swing.JOptionPane;

public class Constants {

	private static double G = 6.67428e-11;
	private static double AU = 149.597871e9;
	private static double scale;
	private static double AUScale;
	private static double daySecs = 86400;
	private static double step;
	private static Entity center;
	private static boolean retro = false;
	private static double time = 0;
	
	private static double radE = 6371393;
	private static double radMe = 2439765.5;
	private static double radV = 6051133;
	private static double radMa = 3389278;
	private static double radS = 58232503;
	private static double radJ = 69911513;
	private static double radN = 24621354;
	private static double radU = 25361652;
	private static double radSun = 695700000;
	
	private static int scenario = 0;
	
	private static double days = 0;
	
	public static void setConstants(int num) {
		if(num == 1) {
			scenario = 1;
			scale = 200/AU;
			AUScale = scale * AU;
			step = 3600*2;
		}
		else if(num == 2){
			scenario = 2;
			scale = 1/90000.0;
			AUScale = scale * AU;
			step = 5;
		}
		else if(num == 3){
			scenario = 3;
			scale = 1/90000.0;
			AUScale = scale * AU;
			step = 5;
		}
	}
	
	public static double G() {
		return G;
	}
	
	public static double AU() {
		return AU;
	}
	
	public static double scale() {
		return scale;
	}
	
	public static double AUScale() {
		return AUScale;
	}
	
	public static double daySecs() {
		return daySecs;
	}
	
	public static double step() {
		return step;
	}
	
	public static void setRetro(boolean bol){
		retro = bol;
	}
	
	public static boolean retro(){
		return retro;
	}

	
	public static void updateTime(){
		days+=step/daySecs;
		time += step;
	}
	
	public static double getTime(){
		return time;
	}
	
	public static double getDays(){
		return days;
	}
	
	public static Entity center(){
		return center;
	}
	
	public static double getRad(String name){
		if(name.equals("Earth"))
			return radE;
		else if(name.equals("Mercury"))
			return radMe;
		else if(name.equals("Venus"))
			return radV;
		else if(name.equals("Mars"))
			return radMa;
		else if(name.equals("Jupiter"))
			return radJ;
		else if(name.equals("Saturn"))
			return radS;
		else if(name.equals("Uranus"))
			return radU;
		else if(name.equals("Neptune"))
			return radN;
		else if(name.substring(0,3).equals("Sat"))
			return 10;
		else if(name.equals("Rocket"))
			return 0;
		else if(name.equals("Sun"))
			return radSun;
		JOptionPane.showMessageDialog(null,"Not a name");
		return 0;
			
	}
	
	public static void setCenter(int i){
		center = Runner.drawPlanets.get(i);
	}
	
	public static int getScenario(){
		return scenario;
	}
	
	
}