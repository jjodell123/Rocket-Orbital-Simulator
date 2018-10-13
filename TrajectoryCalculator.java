public class TrajectoryCalculator {
	
	private static double deltaV1 = 0L, deltaV2 = 0L, systemEcc = 0, systemA = 0L, tof = 0;
	private static int type = 0;
	private static Entity start = null, end = null;
	private static double variation = 0, offset = 0;
	private static double vAnn = 0;
	private static boolean ready = false, launch = false, newVel = false;

	public static void calculateHohmann(Entity e1, Entity e2){
		Entity center = Constants.center();
		double vMag1 = Math.sqrt(Math.pow(e1.getDx(), 2) + Math.pow(e1.getDy(), 2) + Math.pow(e1.getDz(), 2));
		double vMag2 = Math.sqrt(Math.pow(e2.getDx(), 2) + Math.pow(e2.getDy(), 2) + Math.pow(e2.getDz(), 2));
		double uc = center.getMass() * Constants.G();
		double rCMag1 = Math.sqrt(Math.pow(e1.x(), 2) + Math.pow(e1.y(), 2) + Math.pow(e1.z(), 2));
		double rCMag2 = Math.sqrt(Math.pow(e2.x(), 2) + Math.pow(e2.y(), 2) + Math.pow(e2.z(), 2));
		double a = (rCMag1 + rCMag2)/2;
		//escape velocity is sqrt(2u/r)
		double vTrans1 = 0, vTrans2 = 0;
		
		//if 1, it goes outwards, if -1 goes inwards
		systemEcc = Math.abs(1-(rCMag1/a));
		if(rCMag2 < rCMag1){				
			systemEcc = Math.abs((rCMag2/a) - 1);
		}
		
		vTrans1 = Math.sqrt(2*uc/rCMag1 - uc/a);
		vTrans2 = Math.sqrt(2*uc/rCMag2 - uc/a);
		//for when user sets deltaV
		if(newVel)
			newVel = false;
		else
			deltaV1 = vTrans1 - vMag1;
		deltaV2 = vTrans2 - vMag2;
		systemA = a;
	}
	
	public static void calculateHohmannAnomaly(Entity e1, Entity e2){
		Entity center = Constants.center();
		
		double u = center.getMass() * Constants.G();
		double rMag1 = Math.sqrt(Math.pow(e1.x(), 2) + Math.pow(e1.y(), 2) + Math.pow(e1.z(), 2));
		double rMag2 = Math.sqrt(Math.pow(e2.x(), 2) + Math.pow(e2.y(), 2) + Math.pow(e2.z(), 2));
		double vMag1 = Math.sqrt(Math.pow(e1.getDx(), 2) + Math.pow(e1.getDy(), 2) + Math.pow(e1.getDz(), 2));
		double a = (rMag1 + rMag2)/2;
		//how long the trajectory takes
		double transPeriod = Math.PI * Math.sqrt(Math.pow(a, 3) / u);
		tof = transPeriod;
		//how long it takes dest planet to orbit sun
		double destPeriod = 2 * Math.PI *  Math.sqrt(Math.pow(rMag1, 3) / u);
		//proportion of how far dest planet will have travled in transPeriod time, then subtracted to get where
		//it needs to begin
		offset = 180 - (transPeriod / destPeriod * 360);
		while(offset<-360)
			offset+=360;
		while(offset>360)
			offset-=360;
		variation = vMag1 * Constants.step() / (Math.PI * rMag2) * 180;
	}
	
	public static void calculateOneTan(Entity e1, Entity e2){
		Entity center = Constants.center();
		double u = center.getMass() * Constants.G();
		double rMag1 = Math.sqrt(Math.pow(e1.x(), 2) + Math.pow(e1.y(), 2) + Math.pow(e1.z(), 2));
		double rMag2 = Math.sqrt(Math.pow(e2.x(), 2) + Math.pow(e2.y(), 2) + Math.pow(e2.z(), 2));
		double vMag1 = Math.sqrt(Math.pow(e1.getDx(), 2) + Math.pow(e1.getDy(), 2) + Math.pow(e1.getDz(), 2));
		double vMag2 = Math.sqrt(Math.pow(e2.getDx(), 2) + Math.pow(e2.getDy(), 2) + Math.pow(e2.getDz(), 2));
		
		double R = rMag1/rMag2; 
		double ecc = (R-1)/(Math.cos(vAnn)-R);
		double a = rMag1/(1-ecc);
		double vTrans1 = Math.sqrt(2*u/rMag1 - u/a);
		double vTrans2 = Math.sqrt(2*u/rMag2 - u/a);
		if(newVel)
			newVel = false;
		else
			deltaV1 = vTrans1 - vMag1;
		double intAng = Math.atan2(ecc*Math.sin(vAnn), 1+ecc*Math.cos(vAnn));
		deltaV2 = Math.sqrt(Math.pow(vTrans2, 2) + Math.pow(vMag2, 2) - 2*vTrans2*vMag2*Math.cos(intAng));
		systemEcc = ecc;
		systemA = a;
	}
	
	public static void calculateOneTanAnomaly(Entity e1, Entity e2){
		Entity center = Constants.center();
		
		double u = center.getMass() * Constants.G();
		double rMag1 = Math.sqrt(Math.pow(e1.x(), 2) + Math.pow(e1.y(), 2) + Math.pow(e1.z(), 2));
		double rMag2 = Math.sqrt(Math.pow(e2.x(), 2) + Math.pow(e2.y(), 2) + Math.pow(e2.z(), 2));
		double vMag1 = Math.sqrt(Math.pow(e1.getDx(), 2) + Math.pow(e1.getDy(), 2) + Math.pow(e1.getDz(), 2));
		double R = rMag1/rMag2; 
		double ecc = Math.abs((R-1)/(Math.cos(vAnn)-R));

		//doesnt work for cos(vAnn)<=R, or rMag2*cos(vAnn) <= rMag1

		double eAnn = 0, transPeriod = 0;

		//if it is going outwards, otherwise inwards
		if(rMag1<rMag2){
			double a = rMag1/(1-ecc);

			//if it is a hyperbolic orbit or not
			if(ecc>=1){
				double hypHolder = (Math.cos(vAnn)+ecc)/(1+ecc*Math.cos(vAnn));
				eAnn = Math.log(Math.abs(hypHolder) + Math.sqrt(Math.pow(hypHolder, 2) - 1));
				transPeriod = Math.sqrt(Math.abs(Math.pow(a, 3)/u))*(ecc*Math.sinh(eAnn) - eAnn);

			}
			else{
				double holder = (Math.cos(vAnn)+ecc) / (1+ecc*Math.cos(vAnn));

				if(holder > 1)
					holder -= (int)holder;
				else if(holder < -1)
					holder++;
				eAnn = Math.acos(holder);
				transPeriod = Math.sqrt(Math.abs(Math.pow(a, 3)/u))*(eAnn-ecc*Math.sin(eAnn));//10995, 1.7748e7
			}
		}
		else{
			double a = rMag1/(1+ecc);
			//if it is a hyperbolic orbit or not
			if(ecc>=1){
				double hypHolder = (Math.cos(vAnn)-ecc)/(1-ecc*Math.cos(vAnn));
				eAnn = Math.log(Math.abs(hypHolder) + Math.sqrt(Math.pow(hypHolder, 2) - 1));
				transPeriod = Math.abs(Math.sqrt(Math.abs(Math.pow(a, 3)/u))*(ecc*Math.sinh(eAnn) - eAnn));
			}
			else{
				double holder = (ecc-Math.cos(vAnn))/(1-ecc*Math.cos(vAnn));

				if(holder > 1)
					holder -= (int)holder;
				else if(holder < -1)
					holder++;
				eAnn = Math.PI-Math.acos(holder);
				transPeriod = Math.sqrt(Math.abs(Math.pow(a, 3)/u))*(eAnn+ecc*Math.sin(eAnn));//10995, 1.7748e7
			}
		}
		
		tof = transPeriod;
		double destPeriod = 2 * Math.PI *  Math.sqrt(Math.pow(rMag2, 3) / u);
		offset = Math.toDegrees(vAnn) - (transPeriod / destPeriod * 360.0);
		while(offset<-360)
			offset+=360;
		while(offset>360)
			offset-=360;
		variation = vMag1 * Constants.step() / (Math.PI * rMag2) * 180;
	}
	
	public static double deltaV1(){
		return deltaV1;
	}
	
	public static double deltaV2(){
		return deltaV2;
	}
	
	public static void setType(int x){
		type=x;
	}
	
	public static int getType(){
		return type;
	}
	
	public static void setLocations(Entity s, Entity e){
		start = s;
		end = e;
	}
	
	public static void setLocations(String s, String e){
		for(int i = 0; i < Runner.drawPlanets.size(); i++){
			
			if(Runner.drawPlanets.get(i).getName().equals(s))
			{
				start = Runner.drawPlanets.get(i);
			}
			else if(Runner.drawPlanets.get(i).getName().equals(e))
			{
				end = Runner.drawPlanets.get(i);
			}
		}
	}
	
	public static Entity getStart(){
		return start;
	}
	
	public static Entity getEnd(){
		return end;
	}
	
	public static double getHohmannOffset(boolean greater){
		if(greater)
			return offset + variation;
		return offset - variation;
	}
	
	public static double getOneTanOffset(boolean greater){
		if(greater)
			return offset + variation;
		return offset - variation;
	}
	
	public static void setStatus(boolean status){
		ready = status;
	}
	
	public static boolean getStatus(){
		return ready;
	}
	
	public static void setLaunch(boolean status){
		launch = status;
	}
	
	public static boolean getLaunch(){
		return launch;
	}
	
	public static void setVelocity(String vel){
		deltaV1 = Double.parseDouble(vel);
		newVel = true;
	}
	
	public static void setTrueAnamoly(String anom){
		vAnn = Math.toRadians(Double.parseDouble(anom));
	}
	
	public static double getEcc(){
		return systemEcc;
	}
	
	public static double getA(){
		return systemA;
	}
	
	public static double getTof(){
		return tof;
	}
	
}