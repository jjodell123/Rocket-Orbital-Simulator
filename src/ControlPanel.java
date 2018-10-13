import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class ControlPanel extends JPanel{
	
	private String last = "";
	private ArrayList<String> list;
	private static JComboBox type,transMethod,plan1, plan2;
	private static JTextField vel,anom;
	public static int tutorialStep = 0;
	private static boolean started = false;
	
	public ControlPanel(){
			
		this.setBounds(755,5,156,671);
		this.setBackground(Color.blue);
		this.setLayout(null);
		JPanel background = new JPanel();
		background.setBounds(5,0,151,671);
		background.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		background.setBackground(Color.gray);
		GridBagLayout conBoxCons = new GridBagLayout();
		//first number is how much of the outside space it takes up, the second is the distance things on the inside
		//have from the edges
		conBoxCons.columnWidths = new int[] {150, 0};
		//additional size of each row
		conBoxCons.rowHeights = new int[] {35, 620};
		conBoxCons.columnWeights = new double[] {1.0, Double.MIN_VALUE};
		conBoxCons.rowWeights = new double[] {0.0, 0.0, Double.MIN_VALUE};
		background.setLayout(conBoxCons);
		
		String transfers[] = {"Transfer Type", "Hohmann", "One-Tangent Burn"};
		type = new JComboBox(transfers);
		type.setBackground(Color.cyan);
		type.setForeground(Color.blue);
		GridBagConstraints typeCons = new GridBagConstraints();
		typeCons.fill = GridBagConstraints.BOTH;
		typeCons.insets = new Insets(0, 0, 5, 0);
		typeCons.gridx = 0;
		typeCons.gridy = 0;
		background.add(type, typeCons);
		
		type.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				String temp = type.getSelectedItem().toString();
				Board.setUpdateAnom(false,"","");
				if(!last.equals(temp) && !temp.equals("Transfer Type"))
				{
					started = true;
					//gets rid of options each time
					int tempCount = background.getComponentCount();
					while(tempCount>1)
					{
						background.remove(1);
						tempCount = background.getComponentCount();
					}
					
					if(temp.equals("Hohmann")){
						if(Constants.getScenario() == 2 && tutorialStep == 0){
							tutorialStep ++;
							updateText();
						}
						
						list = new ArrayList<String>();
						
						for(int times = 0; times < Runner.drawPlanets.size(); times++)
						{
							if(!Runner.drawPlanets.get(times).getCenter())
								list.add(Runner.drawPlanets.get(times).getName());
						}
						
						//first number is how much of the outside space it takes up, the second is the distance things on the inside
						//have from the edges
						conBoxCons.columnWidths = new int[] {150, 0};
						//additional size of each row
						conBoxCons.rowHeights = new int[] {35, 10, 10, 30, 10, 30, 10, 30, 10, 30, 307, 100};
						conBoxCons.columnWeights = new double[] {1.0, Double.MIN_VALUE};
						conBoxCons.rowWeights = new double[] {0.0, 0.0, Double.MIN_VALUE};
						background.setLayout(conBoxCons);
						
						JLabel lab1 = new JLabel("Who do you want");
						lab1.setBackground(Color.cyan);
						lab1.setForeground(Color.cyan);
						GridBagConstraints lab1cons = new GridBagConstraints();
						lab1cons.fill = GridBagConstraints.BOTH;
						lab1cons.insets = new Insets(0, 0, 5, 0);
						lab1cons.gridx = 0;
						lab1cons.gridy = 1;
						background.add(lab1,lab1cons);
						
						JLabel lab2 = new JLabel("to control launching?");
						lab2.setBackground(Color.cyan);
						lab2.setForeground(Color.cyan);
						GridBagConstraints lab2cons = new GridBagConstraints();
						lab2cons.fill = GridBagConstraints.BOTH;
						lab2cons.insets = new Insets(0, 0, 5, 0);
						lab2cons.gridx = 0;
						lab2cons.gridy = 2;
						background.add(lab2,lab2cons);						
						
						String methods[] = {"Computer", "User"};
						transMethod = new JComboBox(methods);
						transMethod.setBackground(Color.cyan);
						transMethod.setForeground(Color.blue);
						GridBagConstraints transCons = new GridBagConstraints();
						transCons.fill = GridBagConstraints.BOTH;
						transCons.insets = new Insets(0, 0, 5, 0);
						transCons.gridx = 0;
						transCons.gridy = 3;
						background.add(transMethod, transCons);
						
						JLabel plan1lab = new JLabel("Start Location:");
						plan1lab.setBackground(Color.cyan);
						plan1lab.setForeground(Color.cyan);
						GridBagConstraints plan1labCons = new GridBagConstraints();
						plan1labCons.fill = GridBagConstraints.BOTH;
						plan1labCons.insets = new Insets(0, 0, 5, 0);
						plan1labCons.gridx = 0;
						plan1labCons.gridy = 4;
						background.add(plan1lab, plan1labCons);
						
						plan1 = new JComboBox(list.toArray());
						plan1.setBackground(Color.cyan);
						plan1.setForeground(Color.blue);
						GridBagConstraints plan1cons = new GridBagConstraints();
						plan1cons.fill = GridBagConstraints.BOTH;
						plan1cons.insets = new Insets(0, 0, 5, 0);
						plan1cons.gridx = 0;
						plan1cons.gridy = 5;
						background.add(plan1, plan1cons);
						
						JLabel plan2lab = new JLabel("End Location:");
						plan2lab.setBackground(Color.cyan);
						plan2lab.setForeground(Color.cyan);
						GridBagConstraints plan2labCons = new GridBagConstraints();
						plan2labCons.fill = GridBagConstraints.BOTH;
						plan2labCons.insets = new Insets(0, 0, 5, 0);
						plan2labCons.gridx = 0;
						plan2labCons.gridy = 6;
						background.add(plan2lab, plan2labCons);
						
						plan2 = new JComboBox(list.toArray());
						plan2.setBackground(Color.cyan);
						plan2.setForeground(Color.blue);
						GridBagConstraints plan2cons = new GridBagConstraints();
						plan2cons.fill = GridBagConstraints.BOTH;
						plan2cons.insets = new Insets(0, 0, 5, 0);
						plan2cons.gridx = 0;
						plan2cons.gridy = 7;
						background.add(plan2, plan2cons);
						
						JLabel velLab = new JLabel("Initial Delta-V:");
						velLab.setBackground(Color.cyan);
						velLab.setForeground(Color.cyan);
						GridBagConstraints velLalCons = new GridBagConstraints();
						velLalCons.fill = GridBagConstraints.BOTH;
						velLalCons.insets = new Insets(0, 0, 5, 0);
						velLalCons.gridx = 0;
						velLalCons.gridy = 8;
						background.add(velLab, velLalCons);
						
						vel = new JTextField("0", 10);
						vel.setBackground(Color.cyan);
						vel.setForeground(Color.blue);
						GridBagConstraints velCons = new GridBagConstraints();
						velCons.fill = GridBagConstraints.BOTH;
						velCons.insets = new Insets(0, 0, 5, 0);
						velCons.gridx = 0;
						velCons.gridy = 9;
						background.add(vel, velCons);
						
						JButton go = new JButton("Enter");
						go.setBackground(Color.cyan);
						go.setForeground(Color.blue);
						GridBagConstraints goCons = new GridBagConstraints();
						goCons.fill = GridBagConstraints.BOTH;
						goCons.insets = new Insets(0, 0, 5, 0);
						goCons.gridx = 0;
						goCons.gridy = 11;

						
						plan1.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								if(Board.getComplete() == true){
									if(!plan1.getSelectedItem().toString().equals(plan2.getSelectedItem().toString())){
										if(Constants.getScenario() == 2 && tutorialStep == 1){
											tutorialStep ++;
											updateText();
										}
										if(transMethod.getSelectedItem().toString().equals("Computer")){
											Entity start = null,end = null;
											for(int i = 0; i < Runner.drawPlanets.size(); i++){
												
												if(Runner.drawPlanets.get(i).getName().equals(plan1.getSelectedItem().toString()))
												{
													start = Runner.drawPlanets.get(i);
												}
												else if(Runner.drawPlanets.get(i).getName().equals(plan2.getSelectedItem().toString()))
												{
													end = Runner.drawPlanets.get(i);
												}
											}
											TrajectoryCalculator.calculateHohmann(start, end);
											vel.setText(""+(int)TrajectoryCalculator.deltaV1());
										}
										else{
											vel.setText("0");
											Board.setUpdateAnom(true, plan1.getSelectedItem().toString(), plan2.getSelectedItem().toString());
										}
									}
									else
										Board.setUpdateAnom(false, "", "");
								}
							}
						});
						
						plan2.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								if(Board.getComplete() == true){
									if(!plan2.getSelectedItem().toString().equals(plan1.getSelectedItem().toString())){
										if(Constants.getScenario() == 2 && tutorialStep == 1){
											tutorialStep ++;
											updateText();
										}
										if(transMethod.getSelectedItem().toString().equals("Computer")){
											Entity start = null,end = null;
											for(int i = 0; i < Runner.drawPlanets.size(); i++){
												
												if(Runner.drawPlanets.get(i).getName().equals(plan1.getSelectedItem().toString()))
												{
													start = Runner.drawPlanets.get(i);
												}
												else if(Runner.drawPlanets.get(i).getName().equals(plan2.getSelectedItem().toString()))
												{
													end = Runner.drawPlanets.get(i);
												}
											}
											TrajectoryCalculator.calculateHohmann(start, end);
											vel.setText(""+(int)TrajectoryCalculator.deltaV1());
										}
										else{
											vel.setText("0");
											Board.setUpdateAnom(true, plan1.getSelectedItem().toString(), plan2.getSelectedItem().toString());
										}
									}
									else
										Board.setUpdateAnom(false, "", "");
								}
							}
						});
						
						transMethod.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								if(transMethod.getSelectedItem().toString().equals("Computer"))
									Board.setUpdateAnom(false,"","");
								else if(plan1.getSelectedItem().toString().equals(plan2.getSelectedItem().toString()))
									Board.setUpdateAnom(false, "", "");
								else
									Board.setUpdateAnom(true,plan1.getSelectedItem().toString(),plan2.getSelectedItem().toString());
								
								if(!plan2.getSelectedItem().toString().equals(plan1.getSelectedItem().toString())){
									if(transMethod.getSelectedItem().toString().equals("Computer")){
										Entity start = null,end = null;
										for(int i = 0; i < Runner.drawPlanets.size(); i++){
											
											if(Runner.drawPlanets.get(i).getName().equals(plan1.getSelectedItem().toString()))
											{
												start = Runner.drawPlanets.get(i);
											}
											else if(Runner.drawPlanets.get(i).getName().equals(plan2.getSelectedItem().toString()))
											{
												end = Runner.drawPlanets.get(i);
											}
										}
										TrajectoryCalculator.calculateHohmann(start, end);
										vel.setText(""+(int)TrajectoryCalculator.deltaV1());
									}
									else{
										vel.setText("0");
										Board.setUpdateAnom(true, plan1.getSelectedItem().toString(), plan2.getSelectedItem().toString());
									}
								}
							}
						});
						
						go.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								if(Board.getComplete()==true){
									boolean perform = true;
									try{
										double test = Double.parseDouble(vel.getText());
									}
									catch(NumberFormatException exc){
										JOptionPane.showMessageDialog(null, "Invalid velocity input. Please make it a number");
										perform = false;
									}
									if(!plan1.getSelectedItem().toString().equals(plan2.getSelectedItem().toString()) 
											&& perform){
										TrajectoryCalculator.setLocations(plan1.getSelectedItem().toString(),
											plan2.getSelectedItem().toString());
										TrajectoryCalculator.setType(1);
										TrajectoryCalculator.setStatus(true);
										TrajectoryCalculator.setVelocity(vel.getText());
										if(transMethod.getSelectedItem().toString().equals("User")){
											TrajectoryCalculator.setLaunch(true);
										}
										Board.setComplete(false);
									}
									else if(!perform)
										JOptionPane.showMessageDialog(null, "Please Select a different end position from your start position");
								}
								else
									JOptionPane.showMessageDialog(null, "Please let the trajectory finish");
							}
							
						});
						
						background.add(go, goCons);
					}
					else if(temp.equals("One-Tangent Burn")){
						started = true;

						if(Constants.getScenario() == 2 && tutorialStep == 5){
							tutorialStep ++;
							updateText();
						}
						
						list = new ArrayList<String>();
						
						for(int times = 0; times < Runner.drawPlanets.size(); times++)
						{
							if(!Runner.drawPlanets.get(times).getCenter())
								list.add(Runner.drawPlanets.get(times).getName());
						}
						
						conBoxCons.columnWidths = new int[] {150, 0};
						conBoxCons.rowHeights = new int[] {35, 10, 10, 30, 10, 30, 10, 30, 10, 30, 10, 30, 256, 100};
						conBoxCons.columnWeights = new double[] {1.0, Double.MIN_VALUE};
						conBoxCons.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
						background.setLayout(conBoxCons);
						
						JLabel lab1 = new JLabel("Who do you want");
						lab1.setBackground(Color.cyan);
						lab1.setForeground(Color.cyan);
						GridBagConstraints lab1cons = new GridBagConstraints();
						lab1cons.fill = GridBagConstraints.BOTH;
						lab1cons.insets = new Insets(0, 0, 5, 0);
						lab1cons.gridx = 0;
						lab1cons.gridy = 1;
						background.add(lab1,lab1cons);
						
						JLabel lab2 = new JLabel("to control launching?");
						lab2.setBackground(Color.cyan);
						lab2.setForeground(Color.cyan);
						GridBagConstraints lab2cons = new GridBagConstraints();
						lab2cons.fill = GridBagConstraints.BOTH;
						lab2cons.insets = new Insets(0, 0, 5, 0);
						lab2cons.gridx = 0;
						lab2cons.gridy = 2;
						background.add(lab2,lab2cons);						
						
						String methods[] = {"Computer", "User"};
						transMethod = new JComboBox(methods);
						transMethod.setBackground(Color.cyan);
						transMethod.setForeground(Color.blue);
						GridBagConstraints transCons = new GridBagConstraints();
						transCons.fill = GridBagConstraints.BOTH;
						transCons.insets = new Insets(0, 0, 5, 0);
						transCons.gridx = 0;
						transCons.gridy = 3;
						background.add(transMethod, transCons);
												
						JLabel plan1lab = new JLabel("Start Location:");
						plan1lab.setBackground(Color.cyan);
						plan1lab.setForeground(Color.cyan);
						GridBagConstraints plan1labCons = new GridBagConstraints();
						plan1labCons.fill = GridBagConstraints.BOTH;
						plan1labCons.insets = new Insets(0, 0, 5, 0);
						plan1labCons.gridx = 0;
						plan1labCons.gridy = 4;
						background.add(plan1lab, plan1labCons);
						
						plan1 = new JComboBox(list.toArray());
						plan1.setBackground(Color.cyan);
						plan1.setForeground(Color.blue);
						GridBagConstraints plan1cons = new GridBagConstraints();
						plan1cons.fill = GridBagConstraints.BOTH;
						plan1cons.insets = new Insets(0, 0, 5, 0);
						plan1cons.gridx = 0;
						plan1cons.gridy = 5;
						background.add(plan1, plan1cons);
						
						JLabel plan2lab = new JLabel("End Location:");
						plan2lab.setBackground(Color.cyan);
						plan2lab.setForeground(Color.cyan);
						GridBagConstraints plan2labCons = new GridBagConstraints();
						plan2labCons.fill = GridBagConstraints.BOTH;
						plan2labCons.insets = new Insets(0, 0, 5, 0);
						plan2labCons.gridx = 0;
						plan2labCons.gridy = 6;
						background.add(plan2lab, plan2labCons);
						
						
						plan2 = new JComboBox(list.toArray());
						plan2.setBackground(Color.cyan);
						plan2.setForeground(Color.blue);
						GridBagConstraints plan2cons = new GridBagConstraints();
						plan2cons.fill = GridBagConstraints.BOTH;
						plan2cons.insets = new Insets(0, 0, 5, 0);
						plan2cons.gridx = 0;
						plan2cons.gridy = 7;
						background.add(plan2, plan2cons);
						
						JLabel anomLab = new JLabel("True Anomaly:");
						anomLab.setBackground(Color.cyan);
						anomLab.setForeground(Color.cyan);
						GridBagConstraints anomLabCons = new GridBagConstraints();
						anomLabCons.fill = GridBagConstraints.BOTH;
						anomLabCons.insets = new Insets(0, 0, 5, 0);
						anomLabCons.gridx = 0;
						anomLabCons.gridy = 8;
						background.add(anomLab, anomLabCons);
						
						anom = new JTextField("130", 10);
						anom.setBackground(Color.cyan);
						anom.setForeground(Color.blue);
						GridBagConstraints anomCons = new GridBagConstraints();
						anomCons.fill = GridBagConstraints.BOTH;
						anomCons.insets = new Insets(0, 0, 5, 0);
						anomCons.gridx = 0;
						anomCons.gridy = 9;
						background.add(anom, anomCons);
						
						JLabel velLab = new JLabel("Initial Delta-V:");
						velLab.setBackground(Color.cyan);
						velLab.setForeground(Color.cyan);
						GridBagConstraints velLalCons = new GridBagConstraints();
						velLalCons.fill = GridBagConstraints.BOTH;
						velLalCons.insets = new Insets(0, 0, 5, 0);
						velLalCons.gridx = 0;
						velLalCons.gridy = 10;
						background.add(velLab, velLalCons);
						
						vel = new JTextField("0", 10);
						vel.setBackground(Color.cyan);
						vel.setForeground(Color.blue);
						GridBagConstraints velCons = new GridBagConstraints();
						velCons.fill = GridBagConstraints.BOTH;
						velCons.insets = new Insets(0, 0, 5, 0);
						velCons.gridx = 0;
						velCons.gridy = 11;
						background.add(vel, velCons);
						
						JButton go = new JButton("Enter");
						go.setBackground(Color.cyan);
						go.setForeground(Color.blue);
						GridBagConstraints goCons = new GridBagConstraints();
						goCons.fill = GridBagConstraints.BOTH;
						goCons.insets = new Insets(0, 0, 5, 0);
						goCons.gridx = 0;
						goCons.gridy = 13;
						
						transMethod.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								
								if(Constants.getScenario() == 2 && tutorialStep == 8){
									tutorialStep ++;
									updateText();
								}
								
								if(transMethod.getSelectedItem().toString().equals("Computer"))
									Board.setUpdateAnom(false, "", "");
								else if(plan1.getSelectedItem().toString().equals(plan2.getSelectedItem().toString()))
										Board.setUpdateAnom(false, "", "");
								else
									Board.setUpdateAnom(true, plan1.getSelectedItem().toString(), plan2.getSelectedItem().toString());
								
								if(!plan2.getSelectedItem().toString().equals(plan1.getSelectedItem().toString())){
									if(transMethod.getSelectedItem().toString().equals("Computer")){
										Entity start = null,end = null;
										for(int i = 0; i < Runner.drawPlanets.size(); i++){
											
											if(Runner.drawPlanets.get(i).getName().equals(plan1.getSelectedItem().toString()))
											{
												start = Runner.drawPlanets.get(i);
											}
											else if(Runner.drawPlanets.get(i).getName().equals(plan2.getSelectedItem().toString()))
											{
												end = Runner.drawPlanets.get(i);
											}
										}
										TrajectoryCalculator.calculateHohmann(start, end);
										vel.setText(""+(int)TrajectoryCalculator.deltaV1());
									}
									else{
										vel.setText("0");
										Board.setUpdateAnom(true, plan1.getSelectedItem().toString(), plan2.getSelectedItem().toString());
									}
								}
							}
						});
						
						plan1.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								boolean perform = true;
								try{
									double testAnom = Double.parseDouble(anom.getText());
								}
								catch(NumberFormatException exception){
									JOptionPane.showMessageDialog(null, "Invalid true anomoly input. Please make it a number");
									perform = false;
								}
								if(!plan1.getSelectedItem().toString().equals(plan2.getSelectedItem().toString())
									&& perform){
									if(Constants.getScenario() == 2 && tutorialStep == 6){
										tutorialStep ++;
										updateText();
									}
									if(transMethod.getSelectedItem().toString().equals("Computer")){
										Entity start = null,end = null;
										for(int i = 0; i < Runner.drawPlanets.size(); i++){
											
											if(Runner.drawPlanets.get(i).getName().equals(plan1.getSelectedItem().toString()))
											{
												start = Runner.drawPlanets.get(i);
											}
											else if(Runner.drawPlanets.get(i).getName().equals(plan2.getSelectedItem().toString()))
											{
												end = Runner.drawPlanets.get(i);
											}
										}
										TrajectoryCalculator.setTrueAnamoly(anom.getText());
										TrajectoryCalculator.calculateOneTan(start, end);
										vel.setText(""+(int)TrajectoryCalculator.deltaV1());
									}
									else{
										vel.setText("0");
										Board.setUpdateAnom(true, plan1.getSelectedItem().toString(), plan2.getSelectedItem().toString());
									}
								}
								else
									Board.setUpdateAnom(false, "", "");
							}
						});
						
						plan2.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								boolean perform = true;
								try{
									double testAnom = Double.parseDouble(anom.getText());
								}
								catch(NumberFormatException exception){
									JOptionPane.showMessageDialog(null, "Invalid true anomoly input. Please make it a number");
									perform = false;
								}
								if(!plan2.getSelectedItem().toString().equals(plan1.getSelectedItem().toString())
										&& perform){
									if(Constants.getScenario() == 2 && tutorialStep == 6){
										tutorialStep ++;
										updateText();
									}
									if(transMethod.getSelectedItem().toString().equals("Computer")){
										Entity start = null,end = null;
										for(int i = 0; i < Runner.drawPlanets.size(); i++){
											
											if(Runner.drawPlanets.get(i).getName().equals(plan1.getSelectedItem().toString()))
											{
												start = Runner.drawPlanets.get(i);
											}
											else if(Runner.drawPlanets.get(i).getName().equals(plan2.getSelectedItem().toString()))
											{
												end = Runner.drawPlanets.get(i);
											}
										}
										TrajectoryCalculator.setTrueAnamoly(anom.getText());
										TrajectoryCalculator.calculateOneTan(start, end);
										vel.setText(""+(int)TrajectoryCalculator.deltaV1());
									}
									else{
										vel.setText("0");
										Board.setUpdateAnom(true, plan1.getSelectedItem().toString(), plan2.getSelectedItem().toString());
									}
								}
								else
									Board.setUpdateAnom(false, "", "");
							}
						});
						
						go.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								if(Board.getComplete()==true){
									boolean perform = true;
									try{
										double testAnom = Double.parseDouble(anom.getText());
									}
									catch(NumberFormatException exception){
										perform = false;
									}
									try{
										double testVel = Double.parseDouble(vel.getText());
									}
									catch(NumberFormatException exception){
										JOptionPane.showMessageDialog(null,"Invalid true anomoly input. Please make it a number");
										perform = false;

									}
									if(!plan1.getSelectedItem().toString().equals(plan2.getSelectedItem().toString())
											&& perform){
										
										//determines when it is in bounds for vAnn
										Entity start = null, end = null;
										for(int i = 0; i < Runner.drawPlanets.size(); i++){
											
											if(Runner.drawPlanets.get(i).getName().equals(plan1.getSelectedItem().toString()))
											{
												start = Runner.drawPlanets.get(i);
											}
											else if(Runner.drawPlanets.get(i).getName().equals(plan2.getSelectedItem().toString()))
											{
												end = Runner.drawPlanets.get(i);
											}
										}
										double rMag1 = Math.sqrt(Math.pow(start.x(), 2) + Math.pow(start.y(), 2) + Math.pow(start.z(), 2));
										double rMag2 = Math.sqrt(Math.pow(end.x(), 2) + Math.pow(end.y(), 2) + Math.pow(end.z(), 2));
										double vAng = Math.toDegrees(Math.acos(rMag1/rMag2 - (int)(rMag1/rMag2)));
										if(rMag1<rMag2){
											if(vAng < ((int)Double.parseDouble(anom.getText())) && 
												180 >= Double.parseDouble(anom.getText())){
												TrajectoryCalculator.setLocations(start,end);
												TrajectoryCalculator.setTrueAnamoly(anom.getText());
												TrajectoryCalculator.setType(2);
												TrajectoryCalculator.setStatus(true);
												if(transMethod.getSelectedItem().toString().equals("User")){
													TrajectoryCalculator.setLaunch(true);
													TrajectoryCalculator.setVelocity(vel.getText());
												}
												Board.setComplete(false);
											}
											else{
												JOptionPane.showMessageDialog(null, "True Anomaly must be between " + ((int)vAng + 1) + " and 180");
											}
										}
										else if(1 <= Double.parseDouble(anom.getText()) && 180 >= Double.parseDouble(anom.getText())){
											TrajectoryCalculator.setLocations(start,end);
											TrajectoryCalculator.setTrueAnamoly(anom.getText());
											TrajectoryCalculator.setType(2);
											TrajectoryCalculator.setStatus(true);
											if(transMethod.getSelectedItem().toString().equals("User")){
												if(Constants.getScenario() == 2 && tutorialStep == 9){
													tutorialStep ++;
													updateText();
												}
												TrajectoryCalculator.setLaunch(true);
												TrajectoryCalculator.setVelocity(vel.getText());
											}
											Board.setComplete(false);
										}
										else
											JOptionPane.showMessageDialog(null, "True Anomaly must be between 1 and 180");
									}
									else if(!perform)
										JOptionPane.showMessageDialog(null, "Please select a different end position from your start position");
								}
								else
									JOptionPane.showMessageDialog(null, "Let the trajectory finish");
							}						
						});
						
						background.add(go, goCons);
					}
					last = temp;
				}
				else if(temp.equals("Transfer Type")){
					JOptionPane.showMessageDialog(null, "Please select a transfer type");
				}
				
			}
		});
		
		this.add(background);
		

	}
	
	public static void updateText(){
		String st = "";
		
		if(tutorialStep == 1)
			st = "This initiates a Hohmann transfer, a trajectory that travels 180" + "<br>" +
					"degrees around the central object. Next, select a \"Start" + "<br>" + 
					"Location\" and an \"End Location.\" Note that they must" + "<br>" +
					"be different locations.";
		else if(tutorialStep == 2)
			st = "Once you have selected a start and end location, hit the \"Enter\"" + "<br>" +
					"button to launch the rocket. In the top right, a countdown" + "<br>" + 
					"will appear for how much time remains until launch.";
		else if(tutorialStep == 3)
			st = "During the launch countdown or rocket flight, you can end<br>" +
					"the trajectory with the \"Canel Trajectory\" button in<br>" +
					"the bottom right. However, don't do that yet.";
		else if(tutorialStep == 4)
			st = "Now click on the \"Data Values\" tab on the top of the screen.<br>" + 
					"This will show you the position and velocity values for<br>" + 
					"the planets. When you launch a rocket, its data will<br>" +
					"also be stored here. After viewing the data, select<br>" + 
					"the \"Simulation\" tab to return to this page.";
		else if(tutorialStep == 5)
			st = "After looking at the orbital data, again select the transfer type<br>" +
					"button that now reads \"Hohmann\" and change it to<br>" + 
					"\"One-Tangent Burn.\"";
		else if(tutorialStep == 6)
			st = "This initiates a One-Tangent burn transfer, a trajectory that<br>" +
					"travels between 0 and 180 degrees around the central<br>" +
					"object, depending on the fuel constraints. Again,<br>" + 
					"change either the start or end location.";
		else if(tutorialStep == 7)
			st = "With this trajectory, you can also change the true anomoly, or the<br>" + 
					"distance around the central object that the rocket travels.<br>" + 
					"To do this, change the number in the \"True Anomoly\"<br>" + 
					"section. Afterwards, launch the rocket and observe the<br>" + 
					"different flight path.";
		else if(tutorialStep == 8)
			st = "So far, the trajectories have all been calculated by the computer.<br>" + 
					"To calculate them yourself, select the button under \"Who<br>" + 
					"do you want to control launching\", currently labeled<br>" +
					"\"Computer,\" and change it to \"User.\"";
		else if(tutorialStep == 9){
			st = "This allows you to determine the \"Initial Delta-V\" value, or the<br>" + 
					"change in velocity at launch. For now, give it a random delta-v,<br>" + 
					"and select \"Enter.\" When the trajectory is controlled by the<br>" + 
					"user, selecting \"Enter\" will instantly launch the rocket.<br>" +
					"Take note that the label at the bottom tells you<br>"+
					"the angle between the two locations.";
			GUIMenu.setInstructionsSizeY(120);
		}
		else if(tutorialStep == 10){
			st = "Now, launch the rocket. If it misses the destination, select<br>"+
				"\"Cancel Trajectory.\"";
			GUIMenu.setInstructionsSizeY(100);
		}
		else if(tutorialStep == 11)
			st = "To remove the path markers, the small blue dots that show an<br>" +
					"object's path, from past trajectories, select the \"Remove<br>" +
					"Old Path Markers\" button in the top left. Try that now.";
		else if(tutorialStep == 12)
			st = "You have completed the tutorial. Select the \"Return to Main<br>" + 
					"Screen\" button to use the regular scenarios.";
		GUIMenu.setInstructions(st);
	}
	
	public static void updateSelectors(boolean bol){
		type.setEnabled(bol);
		if(started){
			transMethod.setEnabled(bol);
			plan1.setEnabled(bol);
			plan2.setEnabled(bol);
			vel.setEnabled(bol);
		}
		if(type.getSelectedItem().toString().equals("One-Tangent Burn"))
			anom.setEnabled(bol);
	}
}
