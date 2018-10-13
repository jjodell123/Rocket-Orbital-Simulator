import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

public class Entity{

	private String name="";
	
	//index in drawPlanets
	private int index;
	//these are the screen coordinates, not the represented location
	private double xpos,ypos,zpos;
	
	private double ftx=0,fty=0,ftz=0;

	private double ta = 0;
	
	private int size;
	
	private double distance = 0;
	private double startX,startY,endX,endY;
	
	private boolean hold = false;
	
	//says whether it is the center of mass for local system or not
	private boolean center = false;
	
	private boolean completed = false;

	// Stores a mass value, required to determine the accelerations of the planet

	private double mass;
	
	private Image image = null;


	// Stores x and y components of the velocity vector, which are added to the
	// planet's position to create motion, and change as forces act upon a planet

	private double dx,dy,dz;

	public Entity(String name, double xpos, double ypos, double zpos, double mass, double dx, double dy, double dz, int size) {
		this.name=name;
		this.xpos = xpos;
		this.ypos = ypos;
		this.zpos=zpos;
		this.mass = mass;
		this.dx = dx;
		this.dy = dy;
		this.dz=dz;
		this.size = size;

		this.index = Runner.pathPoints.size();
		Runner.pathPoints.add(new ArrayList<Point>());
		if(Constants.getScenario()==1)
			image = ImageHandler.getScen1().get(index);
		else if(Constants.getScenario()==3 || Constants.getScenario() == 2)
			image = ImageHandler.getScen2().get(index);
	}
	
	public Entity(String name, double xpos, double ypos, double zpos, double mass, double dx, double dy, double dz, boolean center) {
		this.name=name;
		this.xpos = xpos;
		this.ypos = ypos;
		this.zpos=zpos;
		this.mass = mass;
		this.dx = dx;
		this.dy = dy;
		this.dz=dz;
		this.size = (int)(Constants.getRad(name)*Constants.scale());
		this.center = center;

		this.index = Runner.pathPoints.size();
		Runner.pathPoints.add(new ArrayList<Point>());
		if(Constants.getScenario()==1)
			image = ImageHandler.getScen1().get(index);
		else if(Constants.getScenario()==3 || Constants.getScenario() == 2)
			image = ImageHandler.getScen2().get(index);	}
	
	public Entity(String name, double xpos, double ypos, double zpos, double mass, double dx, double dy, double dz, int size, boolean center) {
		this.name=name;
		this.xpos = xpos;
		this.ypos = ypos;
		this.zpos=zpos;
		this.mass = mass;
		this.dx = dx;
		this.dy = dy;
		this.dz=dz;
		this.size = size;
		this.center = center;

		this.index = Runner.pathPoints.size();
		Runner.pathPoints.add(new ArrayList<Point>());
		if(Constants.getScenario()==1)
			image = ImageHandler.getScen1().get(index);
		else if(Constants.getScenario()==3 || Constants.getScenario() == 2)
			image = ImageHandler.getScen2().get(index);	}

	public Entity(Entity p) {
		this.xpos = p.x();
		this.ypos = p.y();
		this.mass = p.getMass();
		this.dx = p.getDx();
		this.dy = p.getDy();
		this.size = p.getSize();

		this.image = p.getImage();
		this.index = Runner.pathPoints.size();
		Runner.pathPoints.add(new ArrayList<Point>());
	}
	
	public Entity(Entity p, double mass, double dispX, double dispY){
		this.name = "Rocket";
		//fix so it launches from right location
		if(Constants.getScenario()==3 || Constants.getScenario() == 2){
			this.ypos = p.ypos;
			this.xpos = p.xpos;
		}
		else{
			this.ypos = p.ypos + (Constants.getRad(p.getName()) + 300e3)*Constants.scale() * dispY;
			this.xpos = p.xpos + (Constants.getRad(p.getName()) + 300e3)*Constants.scale() * dispX;
		}
		this.zpos = p.zpos;
		this.mass = mass;
		this.dx = p.dx;
		this.dy = p.dy;
		this.dz = p.dz;
		this.hold = true;
		this.size = 15;
		
		this.index = Runner.pathPoints.size();
		Runner.pathPoints.add(new ArrayList<Point>());
		
		if(Constants.getScenario()==1)
			image = ImageHandler.getRocket();
		else if(Constants.getScenario()==3 || Constants.getScenario() == 2)
			image = ImageHandler.getCapsule();
	}
	
	public void setStart(double sx,double sy){
		startX = sx;
		startY = sy;
	}
	
	public double getStartX(){
		return startX;
	}
	
	public double startY(){
		return startY;
	}
	
	public void setEnd(double ex, double ey){
		endX = ex;
		endY = ey;
		distance = Math.sqrt(Math.pow(startX-endX,2)+Math.pow(startY+endY, 2));
	}

	public String getName()
	{
		return this.name;
	}
	
	public boolean getCenter(){
		return center;
	}

	// Returns the X Y and Z (where it would be) pixels in the form of an array of length 2

	public double[] getCoords() {
		return  new double[] {(Runner.handle.board.getWidth()/2) + xpos - size/2, Runner.handle.board.getHeight()/2 - ypos - size/2};
	}

	// Sets planet's coordinates
	public void setCoords(double x, double y, double z) {
		this.xpos = x;
		this.ypos = y;
		this.zpos = z;
	}

	// These return actual x and y coordinates
	// for when I'm doing the math.
	public double x() {
		return xpos/Constants.scale();
	}

	public double y() {
		return ypos/Constants.scale();
	}
	
	public double z() {
		return zpos/Constants.scale();
	}

	public void setX(double x) {
		xpos = x;
	}
	
	public void setY(double y) {
		ypos = y;
	}
	
	public void setZ(double z) {
		zpos = z;
	}
	
	// Will be needed for determining the forces that this planet acts upon others with.
	public double getMass() {
		return this.mass;
	}
	
	public void setMass(double mass) {
		this.mass = mass;
	}
	
	public int getSize(){
		return size;
	}

	public double getDx() {
		return dx;
	}

	public double getDy() {
		return dy;
	}
	
	public double getDz() {
		return dz;
	}
	
	public double getTA(){
		return ta;
	}
	
	public boolean getHold(){
		if(hold){
			hold = false;
			return true;
		}
		return false;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}
	
	public void setDz(double dz) {
		this.dz = dz;
	}
	
	public void setTA(double ta){
		if(ta < 0)
			ta = Math.PI * 2 - Math.abs(ta);
		this.ta = ta;
	}

	public String toString()
	{
		String dx = "";
		String dy = "";
	    String dz = "";
		dx = String.valueOf(this.dx);
		dy = String.valueOf(this.dy);
		dz = String.valueOf(this.dz);
		return "\tX: " + this.xpos + "\tY: " + this.ypos + "\tZ " + this.zpos+ "\tMass: " + 
			this.mass + "\tX velocity: " + dx + "\tY velocity: " + dy + "\tZ velocity: " + dz;
	}
	
	public String getRealStats() {
		String dx = "";
		String dy = "";
	    String dz = "";
		dx = String.valueOf(this.dx);
		dy = String.valueOf(this.dy);
		dz = String.valueOf(this.dz);
		return "\tX: " + this.xpos/Constants.AUScale() + "\tY: " + this.ypos/Constants.AUScale() + "\tZ " +
			this.zpos/Constants.AUScale()+ "\tMass: " + this.mass + "\tX velocity: " + dx + 
			"\tY velocity: " + dy + "\tZ velocity: " + dz;
	}

	public void setFtx(double ftx)
	{
		this.ftx=ftx;
	}

	public void setFty(double fty)
	{
		this.fty=fty;
	}
	
	public void setFtz(double ftz)
	{
		this.ftz=ftz;
	}

	public double getFtx()
	{
		return this.ftx;
	}

	public double getFty()
	{
		return this.fty;
	}
	
	public double getFtz()
	{
		return this.ftz;
	}
	
	public void decreaseIndex(int decNum){
		index -= decNum;
	}
	
	public Image getImage(){
		return image;
	}

// Adds velocity values to position
	public void move() {
		this.xpos += this.dx*Constants.step()*Constants.scale();
		this.ypos += this.dy*Constants.step()*Constants.scale();
		this.zpos += this.dz*Constants.step()*Constants.scale();
		if(Runner.pathPoints.get(index).size()>1 && Math.sqrt(Math.pow(xpos+0-Runner.pathPoints.get(index).get(0).x,2) + 
				Math.pow(ypos-0 - Runner.pathPoints.get(index).get(0).y, 2))<5)
			completed = true;
		if(Runner.pathPoints.get(index).size()>0 && !completed){
			//checks if it has gone around once
			double dispX = xpos+0 - Runner.pathPoints.get(index).get(Runner.pathPoints.get(index).size()-1).x;
			double dispY = ypos-0 - Runner.pathPoints.get(index).get(Runner.pathPoints.get(index).size()-1).y;
			if(Math.sqrt(Math.pow(dispX, 2) + Math.pow(dispY, 2)) >=5)	{	//name.equals("Sat1")||name.equals("Rocket")||name.equals("Sat2"))
				Runner.pathPoints.get(index).add(new Point((int)xpos+0,(int)ypos-0));
			}
		}
		else if(!completed)
			Runner.pathPoints.get(index).add(new Point((int)xpos+0,(int)ypos-0));

	}
}