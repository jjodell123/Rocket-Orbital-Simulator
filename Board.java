import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel{
	public static double dt = .05;
	private static double startTime = 0, endTime = 0, totalTime = 0;
	private static double startAng = 0, endAng = 0, deltaAng = 0;
	private static int clockTimer = 0;
	private static boolean updateAnom = false;
	private static String userStart = "", userEnd = "";
	private static double timeUntilLaunch = 0;
	private int starX[] = new int[500], starY[] = new int[500];
		
	private static final long serialVersionUID = 1L;
	
	double minMa=2.0663e15,minMab=2.27e15;
	private static boolean complete = true;
	double smallD = Long.MAX_VALUE, r = 0;
	
	double maxMa=2.4923e1;
	int te=1;
		
	@Override
	public int getWidth() {
		return 750;
	}; 
	
	@Override
	public int getHeight() {
		return 671;
	}

	@Override
	protected void paintComponent(Graphics q) {
		
		super.paintComponent(q);
		
		Graphics2D g = (Graphics2D)q;
		
		g.setColor(Color.gray);
		g.drawRect(0, 0, 749, 670);
		
		//updates time
		Constants.updateTime();
				
		double dx;
		double dy;
		double dz;
		Entity p1;
		Entity p2;
		for(int i = 0; i < Runner.drawPlanets.size(); i++) {
			p2 = Runner.drawPlanets.get(i);
			dx = p2.getDx();
			dy = p2.getDy();
			dz = p2.getDz();
			p2.setFtx(0);
			p2.setFty(0);
			p2.setFtz(0);
			
			ArrayList <Integer> toRemove = new ArrayList<Integer>();

			
			for(int k = 0; k < Runner.drawPlanets.size(); k++){
				p1 = Runner.drawPlanets.get(k);

				if(i != k){
					double dpx = p1.x() - p2.x();
					double dpy = p1.y() - p2.y();
					double dpz = p1.z() - p2.z();

					r = Math.sqrt(Math.pow(dpx, 2)+Math.pow(dpy, 2)+Math.pow(dpz, 2));
					if(r!=0)
					{
						double f = Constants.G() * p1.getMass() * p2.getMass() / Math.pow(r, 2);
						double fx = dpx / r * f;
						double fy = dpy / r * f;
						double fz = dpz / r * f;
						if(p2.getName().equals("Rocket") && p1.getName().equals(TrajectoryCalculator.getEnd().getName())
							&& smallD > r && ((r < 1500000 && (Constants.getScenario() == 3 || Constants.getScenario() == 2)) 
							|| (r < 1500000000L && Constants.getScenario() == 1)))
						{
							smallD = r;
						}
							
						dx+=fx/p2.getMass()*Constants.step();
						dy+=fy/p2.getMass()*Constants.step();
						dz+=fz/p2.getMass()*Constants.step();
					}
				}
				if(!p2.getCenter()){
					if(p2.x() < 0 && p2.y() < 0)
						p2.setTA(Math.atan2(-1 * Math.sqrt(Math.pow(p2.y(), 2) + Math.pow(p2.z(), 2)),-1 * Math.sqrt(Math.pow(p2.x(), 2) +
							Math.pow(p2.z(), 2))));
					else if(p2.y() < 0)
						p2.setTA(Math.atan2(-1 * Math.sqrt(Math.pow(p2.y(), 2) + Math.pow(p2.z(), 2)), Math.sqrt(Math.pow(p2.x(), 2) +
							Math.pow(p2.z(), 2))));
					else if(p2.x() < 0)
						p2.setTA(Math.atan2(Math.sqrt(Math.pow(p2.y(), 2) + Math.pow(p2.z(), 2)),-1 * Math.sqrt(Math.pow(p2.x(), 2) +
							Math.pow(p2.z(), 2))));
					else
						p2.setTA(Math.atan2(Math.sqrt(Math.pow(p2.y(), 2) + Math.pow(p2.z(), 2)),Math.sqrt(Math.pow(p2.x(), 2) +
							Math.pow(p2.z(), 2))));

					if(TrajectoryCalculator.getStatus() && TrajectoryCalculator.getStart()!=null &&
						TrajectoryCalculator.getEnd()!=null && 
						p2.getName().equals(TrajectoryCalculator.getStart().getName()) && 
						p1.getName().equals(TrajectoryCalculator.getEnd().getName())){
						
						if(TrajectoryCalculator.getType() == 1)
						{
							double tempAng = p1.getTA() - p2.getTA();
							double p2r = Math.sqrt(Math.pow(p2.x(), 2) + Math.pow(p2.y(), 2) + Math.pow(p2.z(), 2));
							double p1r = Math.sqrt(Math.pow(p1.x(), 2) + Math.pow(p1.y(), 2) + Math.pow(p1.z(), 2));
							boolean adjust = false;
							
							TrajectoryCalculator.calculateHohmannAnomaly(p1, p2);

							//calculates which is on the inside, and makes sure tempAng
							//is always positive or negative depending on which is needed
							if(p1r > p2r){
								if( tempAng < 0){
									tempAng += Math.PI * 2;
								}
								if(clockTimer == 0){
									startTime = System.nanoTime();
									startAng = Math.toRadians(TrajectoryCalculator.getHohmannOffset(false))
											- tempAng;
									if(tempAng < Math.toRadians(TrajectoryCalculator.getHohmannOffset(false)) 
											&& tempAng > 0)
										startAng -= Math.PI*2;
								}
								else if(clockTimer >0){
									endTime = System.nanoTime();
									endAng = Math.toRadians(TrajectoryCalculator.getHohmannOffset(false))
											- tempAng;
									if(tempAng < Math.toRadians(TrajectoryCalculator.getHohmannOffset(false)) 
											&& tempAng > 0){
										endAng -= Math.PI*2;
										tempAng += Math.PI*2;
									}
									totalTime = (endTime - startTime)/10e8;
									deltaAng = (endAng - startAng);
									double rate = deltaAng / totalTime;
									double changeNeeded = Math.toRadians(TrajectoryCalculator.getHohmannOffset(false))
											- tempAng;
									timeUntilLaunch = changeNeeded/rate;
								}
								
							}
							else if(p1r < p2r){
								
								//checks if it needs to take away from tempAng to match TrajectoryCalculator's output
								if(tempAng > 0){
									tempAng -= Math.PI * 2;
								}
								if(clockTimer == 0){
									startTime = System.nanoTime();
									startAng = Math.toRadians(TrajectoryCalculator.getHohmannOffset(false))
											- tempAng;
									if(startAng < 0)
										startAng += Math.PI*2;
								}
								else if(clockTimer >0){

									endTime = System.nanoTime();
									endAng = Math.toRadians(TrajectoryCalculator.getHohmannOffset(false))
											- tempAng;
									//checks if it starts before r vectors become parallel, if so adjusts values
									if(tempAng < 0 && Math.toDegrees(tempAng) > TrajectoryCalculator.getHohmannOffset(false)){
										endAng += Math.PI*2;
										tempAng -= Math.PI*2;
										adjust = true;
									}
									totalTime = (endTime - startTime)/10e8;
									deltaAng = (endAng - startAng);
									double rate = deltaAng / totalTime;
									double changeNeeded = Math.toRadians(TrajectoryCalculator.getHohmannOffset(false))
											- tempAng;
									timeUntilLaunch = changeNeeded/rate;

									//if values were adjusted
									if(adjust)
										tempAng += Math.PI*2;
								}
							}
							GUIMenu.setTimer(""+Math.round(Math.abs(timeUntilLaunch)*10.0)/10.0);
							clockTimer++;

							if((tempAng < TrajectoryCalculator.getHohmannOffset(true)*Math.PI/180 && 
								tempAng > TrajectoryCalculator.getHohmannOffset(false)*Math.PI/180) || 
								TrajectoryCalculator.getLaunch()){
								
								if(Constants.getScenario() == 2 && ControlPanel.tutorialStep == 2){
									ControlPanel.tutorialStep ++;
									ControlPanel.updateText();
								}

								//makes sure it won't launch again
								TrajectoryCalculator.setStatus(false);
								TrajectoryCalculator.setLaunch(false);
								//makes sure it stays zero
								GUIMenu.setTimer(""+0.0);
								
								//creates vector in direction of launch
								double dispX = p1.x() - p2.x();
								double dispY = p1.y() - p2.y();
								//makes the vector a unit vector (Magnitude of 1)
								double dispMag = Math.sqrt(Math.pow(dispX, 2) + Math.pow(dispY, 2));
								dispX /= dispMag;
								dispY /= dispMag;
								
								TrajectoryCalculator.calculateHohmann(p2,p1);
								
								//creates rocket identical to planet it is launched from
								Runner.drawPlanets.add(new Entity(p2, 15e4, dispX, dispY));
								double tempDX = dx;
								double tempDY = dy;
								
								if (!Constants.retro())
								{
									tempDX += TrajectoryCalculator.deltaV1() * Math.cos(p2.getTA() + Math.PI/2);
									tempDY += TrajectoryCalculator.deltaV1() * Math.sin(p2.getTA() + Math.PI/2);
								}
								else
								{
									tempDX += TrajectoryCalculator.deltaV1() * Math.cos(p2.getTA() - Math.PI/2);
									tempDY += TrajectoryCalculator.deltaV1() * Math.sin(p2.getTA() - Math.PI/2);
								}
								
								Runner.drawPlanets.get(Runner.drawPlanets.size()-1).setDx(tempDX);
								Runner.drawPlanets.get(Runner.drawPlanets.size()-1).setDy(tempDY);

							}
						}
						else if(TrajectoryCalculator.getType() == 2)
						{
							double tempAng = p1.getTA() - p2.getTA();
							double p2r = Math.sqrt(Math.pow(p2.x(), 2) + Math.pow(p2.y(), 2) + Math.pow(p2.z(), 2));
							double p1r = Math.sqrt(Math.pow(p1.x(), 2) + Math.pow(p1.y(), 2) + Math.pow(p1.z(), 2));
							boolean adjust = false;
							
							TrajectoryCalculator.calculateOneTanAnomaly(p2, p1);

							//calculates which is on the inside
							if(p1r > p2r){
								//makes sure tempAng is positive
								if( tempAng < 0){
									tempAng += Math.PI * 2;
								}
								//if trajectory just started, get initial angle and time
								if(clockTimer == 0){
									startTime = System.nanoTime();
									startAng = Math.toRadians(TrajectoryCalculator.getOneTanOffset(false))
											- tempAng;
									//makes sure the startAng won't skip from 0 to 360 or the other way around
									if(tempAng < Math.toRadians(TrajectoryCalculator.getOneTanOffset(false)) 
											&& tempAng > 0)
										startAng -= Math.PI*2;
								}
								//every other time
								else if(clockTimer >0){
									//finds new angle and time
									endTime = System.nanoTime();
									endAng = Math.toRadians(TrajectoryCalculator.getOneTanOffset(false))
											- tempAng;
									//makes sure endAng won't skip from 0 to 360 or the other way around
									if(tempAng < Math.toRadians(TrajectoryCalculator.getOneTanOffset(false)) 
											&& tempAng > 0){
										endAng -= Math.PI*2;
										tempAng += Math.PI*2;
									}
									//change in time from start
									totalTime = (endTime - startTime)/10e8;
									//change in angle from start
									deltaAng = (endAng - startAng);
									//speed
									double rate = deltaAng / totalTime;
									//change left
									double changeNeeded = Math.toRadians(TrajectoryCalculator.getOneTanOffset(false))
											- tempAng;
									//time until launch
									timeUntilLaunch = changeNeeded/rate;
								}
								
							}
							else if(p1r < p2r){
								
								//checks if it needs to take away from tempAng to match TrajectoryCalculator's output
								if(tempAng > 0){
									tempAng -= Math.PI * 2;
								}
								if(clockTimer == 0){
									startTime = System.nanoTime();
									startAng = Math.toRadians(TrajectoryCalculator.getOneTanOffset(false))
											- tempAng;
									if(startAng < 0)
										startAng += Math.PI*2;
								}
								else if(clockTimer >0){

									endTime = System.nanoTime();
									endAng = Math.toRadians(TrajectoryCalculator.getOneTanOffset(false))
											- tempAng;
									//checks if it starts before r vectors become parallel, if so adjusts values
									if(tempAng < 0 && Math.toDegrees(tempAng) > TrajectoryCalculator.getOneTanOffset(false)){
										endAng += Math.PI*2;
										tempAng -= Math.PI*2;
										adjust = true;
									}
									totalTime = (endTime - startTime)/10e8;
									deltaAng = (endAng - startAng);
									double rate = deltaAng / totalTime;
									double changeNeeded = Math.toRadians(TrajectoryCalculator.getOneTanOffset(false))
											- tempAng;
									timeUntilLaunch = changeNeeded/rate;
									//if values were adjusted
									if(adjust)
										tempAng += Math.PI*2;
								}
							}
							
							GUIMenu.setTimer(""+Math.round(Math.abs(timeUntilLaunch)*10.0)/10.0);
							clockTimer++;
							
							//find flight angle. find variance based on speed
							TrajectoryCalculator.calculateOneTanAnomaly(p2, p1);
							if((tempAng < TrajectoryCalculator.getOneTanOffset(true)*Math.PI/180 && 
									tempAng > TrajectoryCalculator.getOneTanOffset(false)*Math.PI/180) ||
									TrajectoryCalculator.getLaunch()){
								
								TrajectoryCalculator.setStatus(false);
								GUIMenu.setTimer(""+0.0);
								double dispX = p1.x() - p2.x();
								double dispY = p1.y() - p2.y();
								
								double dispMag = Math.sqrt(Math.pow(dispX, 2) + Math.pow(dispY, 2));
								dispX /= dispMag;
								dispY /= dispMag;

								TrajectoryCalculator.calculateOneTan(p2,p1);
								
								//creates rocket identical to planet it is launched from (except weight). Other two
								//parameters give put give the displacement vector to the target planet
								Runner.drawPlanets.add(new Entity(p2, 15e4, dispX, dispY));
								double tempDX = dx;
								double tempDY = dy;
								
								if (!Constants.retro())
								{
									tempDX += TrajectoryCalculator.deltaV1() * Math.cos(p2.getTA() + Math.PI/2);
									tempDY += TrajectoryCalculator.deltaV1() * Math.sin(p2.getTA() + Math.PI/2);
								}
								else
								{
									tempDX += TrajectoryCalculator.deltaV1() * Math.cos(p2.getTA() - Math.PI/2);
									tempDY += TrajectoryCalculator.deltaV1() * Math.sin(p2.getTA() - Math.PI/2);
								}
								
								Runner.drawPlanets.get(Runner.drawPlanets.size()-1).setDx(tempDX);
								Runner.drawPlanets.get(Runner.drawPlanets.size()-1).setDy(tempDY);
								Runner.drawPlanets.get(Runner.drawPlanets.size()-1).setStart(p2.x(),p2.y());
								startTime = Constants.getTime();
							}
						}
					}
					else if(updateAnom && p2.getName().equals(userStart) && 
							p1.getName().equals(userEnd) && !p1.getName().equals(p2.getName()) && 
							!userStart.equals(userEnd)){
						double tempAng = p1.getTA() - p2.getTA();
						GUIMenu.setCurrentAnom(Math.toDegrees(tempAng));
					}
					
					if(p2.getName().equals("Rocket") && p1.getName().equals(TrajectoryCalculator.getEnd().getName()) 
						&& ((smallD < 150000 && (Constants.getScenario() == 3 || Constants.getScenario() == 2)) || 
						(smallD < 1500000000 && Constants.getScenario() == 1))  && smallD < r && !complete)
					{
						if(Constants.getScenario() == 2 && (ControlPanel.tutorialStep == 3 || ControlPanel.tutorialStep == 7)){
							ControlPanel.tutorialStep ++;
							ControlPanel.updateText();
						}
						complete = true;
						clockTimer = 0;
						ControlPanel.updateSelectors(true);
						GUIMenu.setCancelButtonVisibility(false);
						GUIMenu.setTimerVisibility(false);
						Runner.drawPlanets.get(k).setMass(p1.getMass() + p2.getMass());
						p2.setEnd(p2.x(), p2.y());
						toRemove.add(i);
						smallD = Long.MAX_VALUE;
					}
					
				}
			}
			if(!p2.getHold()){
				Runner.drawPlanets.get(i).setDx(dx);
				
				Runner.drawPlanets.get(i).setDy(dy);
				
				Runner.drawPlanets.get(i).setDz(dz);
				for(int j = 0; j<toRemove.size();j++){
					if(toRemove.get(j)>=0){
						Runner.drawPlanets.remove((int)toRemove.get(j));
					}
				}
			}

		}

		Runner.handle.repaint();
		
		//stars
		g.setColor(Color.white);
		for(int xx = 0;xx < starX.length; xx++){
				g.drawRect(starX[xx], starY[xx], 1, 1);
		}
		te++;
		// Redraws things
		g.setColor(Color.blue);
		for(int ind = 0;ind<Runner.pathPoints.size();ind++){
			for(Point p:Runner.pathPoints.get(ind))
				g.fillOval((Runner.handle.board.getWidth()/2) + p.x - 1,Runner.handle.board.getHeight()/2 - p.y - 1, 2, 2);
		}
		for (int i = 0; i < Runner.drawPlanets.size(); i++) {
			if (Runner.handle.tabbedPane.getSelectedIndex() == 1){
				p1 = Runner.drawPlanets.get(i);
				
				Runner.drawPlanets.get(i).move();
				Entity draw = Runner.drawPlanets.get(i);
				//names things
				g.setColor(Color.cyan);
				if(p1.getName().equals("Earth"))
					g.drawString(p1.getName(), (int)draw.getCoords()[0] + p1.getSize()/2 - p1.getName().length()*3 + 1, (int)draw.getCoords()[1] - 2);
				else if(p1.getName().equals("Sun"))
					g.drawString(p1.getName(), (int)draw.getCoords()[0] + p1.getSize()/2 - p1.getName().length()*4, (int)draw.getCoords()[1] - 2);
				else if(p1.getName().equals("Rocket"))
					g.drawString(p1.getName(), (int)draw.getCoords()[0] + p1.getSize()/2 - p1.getName().length()*3 -1, (int)draw.getCoords()[1] - 2);
				else
					g.drawString(p1.getName(), (int)draw.getCoords()[0] + p1.getSize()/2 - p1.getName().length()*3 - 2, (int)draw.getCoords()[1] - 2);
				g.drawImage(p1.getImage(), (int)draw.getCoords()[0], (int)draw.getCoords()[1], p1.getSize(), p1.getSize(), null);
			}
			else if (Runner.handle.tabbedPane.getSelectedIndex() == 0) {
				Runner.drawPlanets.get(i).move();
			}
		}
		g.setColor(Color.black);
		
		if(Runner.handle.isRunning){
			
			Runner.handle.xpos.setEnabled(false);
			Runner.handle.ypos.setEnabled(false);
			Runner.handle.mass.setEnabled(false);
			Runner.handle.xvel.setEnabled(false);
			Runner.handle.yvel.setEnabled(false);
		
			// This repaint command is what keeps the simulation running
		
			
		}
		else {
			Runner.handle.xpos.setEnabled(true);
			Runner.handle.ypos.setEnabled(true);
			Runner.handle.mass.setEnabled(true);
			Runner.handle.xvel.setEnabled(true);
			Runner.handle.yvel.setEnabled(true);
			Runner.handle.tabbedPane.setEnabled(true);
		}
		
	}
	
	public double[] getPointMa()
	{
		return new double[] {minMab,maxMa,minMa};
	}
	
	public static boolean getComplete(){
		return complete;
	}
	
	public static double getTimerUntilLaunch(){
		return timeUntilLaunch;
	}
	
	public static void setComplete(boolean bol){
		complete = bol;
		ControlPanel.updateSelectors(bol);
		GUIMenu.setCancelButtonVisibility(!bol);
		GUIMenu.setTimerVisibility(!bol);
	}
	
	public static void setTimer(int num){
		clockTimer = num;
	}
	
	public void setStars(int x[],int y[]){
		starX = x;
		starY = y;
	}
	
	public static void setUpdateAnom(boolean bol, String start, String end){
		updateAnom = bol;
		userStart = start;
		userEnd = end;
		GUIMenu.setAnomVisibility(bol);
	}


}