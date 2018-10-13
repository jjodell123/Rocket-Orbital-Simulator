import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUIMenu extends JFrame{
	
	
	private static final long serialVersionUID = 1L;

	public boolean isRunning;
	
	public TextField xpos = new TextField();
	public TextField ypos = new TextField();
	public TextField mass = new TextField();
	public TextField xvel = new TextField();
	public TextField yvel = new TextField();
		
	public JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
	public Board board = new Board();
	
	public int restartindex;
	public JPanel panel_2 = new JPanel(true);
	public JPanel conPanBackground;
	public JPanel PlanetListTab;
	public JPanel SimulationTab;
	public JPanel PresetOrbitChooser;
	public JPanel ConTab;
	public JPanel contentPane;
	public JPanel pEditHolder = new JPanel();
	
	//my stuff
	private JButton mainScreenButton = new JButton("Return to Main Screen");
	private static JButton cancelButton = new JButton("Cancel Trajectory");
	private JButton removePathPoints = new JButton("Remove Old Path Markers");
	private static JLabel timer = new JLabel("Approximate Time Until Launch: ");
	private static JLabel instructions = new JLabel("<html><div style='text-align: center;'>" + 
	"Welcome to the tutorial! To begin, click on the \"Transfer Type\"" + "<br>" + 
	"button in the top right and select Hohmann" + "</div></html>");
	private static JLabel currentAnom = new JLabel("The angle between the locations is _ degrees");
	
	public ControlPanel controlPanel = new ControlPanel();
	public DataPanel dataPanel = new DataPanel();
	public ConstantPanel conPanel = new ConstantPanel();


	
	public GUIMenu(String title) {
		setForeground(Color.CYAN);
		setBackground(Color.DARK_GRAY);
		setBounds(5, 5, 940, 755);
		
		setTitle(title);
		
		contentPane = new JPanel();
		contentPane.setForeground(Color.red);
		contentPane.setBackground(Color.blue);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane);
		
		JPanel TabPaneHolder = new JPanel();
		scrollPane.setViewportView(TabPaneHolder);
		TabPaneHolder.setLayout(null);
		tabbedPane.setBounds(0, 0, 921, 715);
		
		
		TabPaneHolder.add(tabbedPane);
		
		PresetOrbitChooser = new JPanel();
		tabbedPane.addTab("Presets", null, PresetOrbitChooser, null);
		PresetOrbitChooser.setLayout(null);
		PresetOrbitChooser.setBackground(Color.DARK_GRAY);
		
		JLabel welcome = new JLabel("<html><div style='text-align: center;'>" + "Welcome to<br>Rocket Orbital Simulator!" + "</div></html>");
		welcome.setBounds(10,11,921,170);
		welcome.setFont(new Font("Times New Roman", 1, 80));
		welcome.setForeground(Color.cyan);
		PresetOrbitChooser.add(welcome);
		
		JLabel toBegin = new JLabel("<html><div style='text-align: center;'>" + "Select a scenario to begin:" + "</div></html>");
		toBegin.setBounds(297,200,327,40);
		toBegin.setFont(new Font("Times New Roman", 1, 30));
		toBegin.setForeground(Color.cyan);
		PresetOrbitChooser.add(toBegin);
		
		JButton sunCenteredOrbit = new JButton("");
		sunCenteredOrbit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			restartindex = 1;
			recallConfig();
			}
		});
		sunCenteredOrbit.setIcon(new ImageIcon(ImageHandler.getScen1().get(0).getScaledInstance(280, 280, Image.SCALE_DEFAULT)));
		sunCenteredOrbit.setBounds(17, 300, 280, 280);
		sunCenteredOrbit.setBackground(Color.LIGHT_GRAY);
		sunCenteredOrbit.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.cyan, Color.blue));
		PresetOrbitChooser.add(sunCenteredOrbit);
		
		JLabel scen1Lab = new JLabel("Planets orbiting the Sun");
		scen1Lab.setBounds(28,580,258,50);
		scen1Lab.setForeground(Color.cyan);
		scen1Lab.setFont(new Font("Times New Roman", 1, 25));
		PresetOrbitChooser.add(scen1Lab);
		
		JButton tutorial = new JButton("Tutorial");
		tutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			restartindex = 2;
			instructions.setVisible(true);
			recallConfig();
			}
		});
		tutorial.setBounds(617, 300, 280, 280);
		tutorial.setFont(new Font("Times New Roman", 1, 70));
		tutorial.setBackground(Color.LIGHT_GRAY);
		tutorial.setForeground(Color.BLUE);
		tutorial.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.cyan, Color.blue));
		PresetOrbitChooser.add(tutorial);
		
		JLabel tutLab = new JLabel("<html><div style='text-align: center;'>" + "A walkthrough for how to use the simulator" + "</div></html>");
		tutLab.setBounds(633,594,254,50);
		tutLab.setForeground(Color.cyan);
		tutLab.setFont(new Font("Times New Roman", 1, 25));
		PresetOrbitChooser.add(tutLab);
		
		JButton earthCenteredOrbit = new JButton("");
		earthCenteredOrbit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			restartindex = 3;
			recallConfig();
			}
		});
		earthCenteredOrbit.setBounds(317, 300, 280, 280);
		earthCenteredOrbit.setIcon(new ImageIcon(ImageHandler.getScen2().get(0).getScaledInstance(280, 280, Image.SCALE_DEFAULT)));
		earthCenteredOrbit.setBackground(Color.LIGHT_GRAY);
		earthCenteredOrbit.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.cyan, Color.blue));
		PresetOrbitChooser.add(earthCenteredOrbit);
		
		JLabel scen2Lab = new JLabel("<html><div style='text-align: center;'>" + "Satellites orbiting the<br>Earth" + "</div></html>");
		scen2Lab.setBounds(343,594,227,50);
		scen2Lab.setForeground(Color.cyan);
		scen2Lab.setFont(new Font("Times New Roman", 1, 25));
		PresetOrbitChooser.add(scen2Lab);
		
		SimulationTab = new JPanel();
		tabbedPane.addTab("Simulation", null, SimulationTab, null);
		SimulationTab.setLayout(null);
		panel_2.setBounds(5, 5, 755, 676);
		
		panel_2.setLayout(null);
		board.setSize(panel_2.getSize());
		
		board.setBackground(Color.black);
		board.setLayout(null);
		panel_2.add(board);
		
		SimulationTab.add(panel_2);

		SimulationTab.add(controlPanel);
		
		mainScreenButton.setHorizontalAlignment(SwingConstants.LEFT);
		mainScreenButton.setBounds(10, 626, 160, 35);
		mainScreenButton.setBackground(Color.cyan);
		mainScreenButton.setForeground(Color.blue);
		mainScreenButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(Constants.getScenario() == 2 && ControlPanel.tutorialStep == 11){
					ControlPanel.tutorialStep ++;
					ControlPanel.updateText();
				}
				tabbedPane.setSelectedIndex(0);
				isRunning = false;
				TrajectoryCalculator.setStatus(false);
				for(int i = Runner.pathPoints.size()-1; i>0; i--)
					Runner.pathPoints.remove(i);
				if(Constants.getScenario() == 2)
					instructions.setVisible(false);
				TrajectoryCalculator.setLaunch(false);
				SimulationTab.remove(controlPanel);
				controlPanel = new ControlPanel();
				SimulationTab.add(controlPanel);
				tabbedPane.setEnabled(false);
				currentAnom.setVisible(false);
				Board.setComplete(true);
				Board.setTimer(0);
			}
		});
		board.add(mainScreenButton);
		
		cancelButton.setHorizontalAlignment(SwingConstants.LEFT);
		cancelButton.setBounds(607,626,134,35);
		cancelButton.setBackground(Color.cyan);
		cancelButton.setForeground(Color.blue);
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				TrajectoryCalculator.setStatus(false);
				Board.setComplete(true);
				Board.setTimer(0);
				int rocketIndex = Runner.drawPlanets.size() - 1;
				if(Runner.drawPlanets.get(rocketIndex).getName().equals("Rocket")){
					Runner.drawPlanets.remove(rocketIndex);
					if(Constants.getScenario() == 2 && (ControlPanel.tutorialStep == 3 || ControlPanel.tutorialStep == 7
							|| ControlPanel.tutorialStep == 10)){
						ControlPanel.tutorialStep ++;
						ControlPanel.updateText();
					}
				}
			}
		});
		cancelButton.setVisible(false);
		board.add(cancelButton);

		{
			int tempX[] = new int[500], tempY[]=new int[500];
			for(int xx = 0;xx<tempX.length;xx++){
				tempX[xx] = (int)(Math.random()*board.getWidth());
				tempY[xx] = (int)(Math.random()*board.getHeight());
			}
			board.setStars(tempX, tempY);
		}
		removePathPoints.setHorizontalAlignment(SwingConstants.LEFT);
		removePathPoints.setBounds(10,10,182,35);
		removePathPoints.setBackground(Color.cyan);
		removePathPoints.setForeground(Color.blue);
		removePathPoints.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//number of entities in drawPlanets that are planets
				int numPlanets = 0;
				if(Constants.getScenario() == 2 && ControlPanel.tutorialStep == 11){
					ControlPanel.tutorialStep ++;
					ControlPanel.updateText();
				}
				if(Runner.drawPlanets.get(Runner.drawPlanets.size()-1).getName().equals("Rocket"))
				{
					numPlanets = Runner.drawPlanets.size()-1;
					for(int ind = Runner.pathPoints.size()-2; ind>=numPlanets;ind--){
						Runner.pathPoints.remove(ind);
						Runner.drawPlanets.get(Runner.drawPlanets.size()-1).decreaseIndex(1);
					}
				}
				else{
					numPlanets = Runner.drawPlanets.size();
					for(int ind = Runner.pathPoints.size()-1; ind>=numPlanets;ind--){
						Runner.pathPoints.remove(ind);
					}
				}
			}
		});
		board.add(removePathPoints);
		
		timer.setBounds(460,10,300,35);
		timer.setFont(new Font("Times New Roman", 1, 17));
		timer.setForeground(Color.cyan);
		timer.setVisible(false);
		
		board.add(timer);
		
		currentAnom.setBounds(223, 626, 400, 35);
		currentAnom.setFont(new Font("Times New Roman", 1, 17));
		currentAnom.setForeground(Color.cyan);
		currentAnom.setVisible(false);
		
		board.add(currentAnom);
		
		PlanetListTab = new JPanel();
		tabbedPane.addTab("Data Values", null, PlanetListTab, null);
		PlanetListTab.setLayout(null);
		tabbedPane.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent event){
				if(tabbedPane.getSelectedIndex()==2){
					dataPanel.update();
					if(Constants.getScenario() == 2 && ControlPanel.tutorialStep == 4){
						ControlPanel.tutorialStep ++;
						ControlPanel.updateText();
					}
				}
				if(tabbedPane.getSelectedIndex()==3){
					conPanel.setScenario(Constants.getScenario());
				}
			}
		});

		PlanetListTab.add(dataPanel);
		tabbedPane.setEnabled(false);

		//only pertain to the tutorial slide
		instructions.setBounds(144, 460, 1000, 100);
		instructions.setFont(new Font("Times New Roman", 1, 17));
		instructions.setForeground(Color.cyan);
		instructions.setBackground(Color.white);
		instructions.setVisible(false);
		
		board.add(instructions);
		
		ConTab = new JPanel();
		tabbedPane.addTab("Constants Values", null, ConTab, null);
		ConTab.setLayout(null);

		ConTab.add(conPanel);		
	}

	public void recallConfig() {
		tabbedPane.setEnabled(true);
		tabbedPane.setEnabledAt(0, false);
		for(int i = Runner.drawPlanets.size()-1; i>=0; i--)
			Runner.drawPlanets.remove(i);
		for(int i = Runner.pathPoints.size()-1; i>=0; i--)
			Runner.pathPoints.remove(i);
		if (restartindex == 0){
			isRunning = false;
			tabbedPane.setSelectedIndex(3);
			tabbedPane.setEnabled(true);
		} else if (restartindex == 1) {
			Runner.drawPlanets = new ArrayList<Entity>();
			Constants.setConstants(1);
			Runner.drawPlanets.add(new Entity("Sun", 0, 0, 0, 1.988544e30, 0, 0, 0, 70, true));
			Runner.drawPlanets.add(new Entity("Mercury", 0, 0.39*Constants.AUScale(), 0, 1, -47695.2142, 0, 0,10));
			Runner.drawPlanets.add(new Entity("Venus", 0, -0.723*Constants.AUScale(), 0, 1, 35029.82426, 0, 0,10));		
			Runner.drawPlanets.add(new Entity("Earth", 0, -1*Constants.AUScale(), 0, 1, 29783.0295, 0, 0, 10));
			Runner.drawPlanets.add(new Entity("Mars", 1.524*Constants.AUScale(), 0, 0, 1, 0, 24125.719, 0, 10));
			Constants.setCenter(0);
			
			isRunning = true;
			tabbedPane.setSelectedIndex(1);
		} else if (restartindex == 2) {
			Constants.setConstants(2);
			Runner.drawPlanets.add(new Entity("Earth", 0, 0, 0, 5.97219e24, 0, 0, 0, true));
			Runner.drawPlanets.add(new Entity("Sat1", -1*(5e6+Constants.getRad("Earth"))*Constants.scale(),0, 0, 1e4, 0,-5918.7969, 0, 30));
			Runner.drawPlanets.add(new Entity("Sat2", -1*(20e6+Constants.getRad("Earth"))*Constants.scale(), 0, 0, 1e4, 0, -3887.29016, 0, 30));
			Constants.setCenter(0);

			isRunning = true;
			tabbedPane.setSelectedIndex(1);
		} else if (restartindex == 3) {
			Constants.setConstants(3);
			//Only Earth, rocket goes from one circular plane to another
			Runner.drawPlanets.add(new Entity("Earth", 0, 0, 0, 5.97219e24, 0, 0, 0, true));
			Runner.drawPlanets.add(new Entity("Sat1", -1*(5e6+Constants.getRad("Earth"))*Constants.scale(),0, 0, 1e4, 0,-5918.7969, 0, 30));
			Runner.drawPlanets.add(new Entity("Sat2", -1*(20e6+Constants.getRad("Earth"))*Constants.scale(), 0, 0, 1e4, 0, -3887.29016, 0, 30));
			Constants.setCenter(0);
			
			isRunning = true;
			tabbedPane.setSelectedIndex(1);
		}
	}
	
	public static void setCancelButtonVisibility(boolean bol){
		cancelButton.setVisible(bol);
	}
	
	public static void setTimerVisibility(boolean bol){
		timer.setVisible(bol);
	}
	
	public static void setAnomVisibility(boolean bol){
		currentAnom.setVisible(bol);
	}
	
	public static void setTimer(String st){
		timer.setText(timer.getText().substring(0, timer.getText().indexOf(":") + 2)+st);
	}
	
	public static void setInstructions(String st){
		instructions.setText("<html><div style='text-align: center;'>" + st + "</div></html>");
	}
	
	public static void setInstructionsSizeY(int y){
		instructions.setBounds(144, 460, 1000, y);
	}

	public static void setCurrentAnom(double anom){
		currentAnom.setText("The angle between the locations is " + Math.round(anom*100.0)/100.0 + " degrees");
	}

}