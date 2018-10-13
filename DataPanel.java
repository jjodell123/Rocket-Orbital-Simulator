import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class DataPanel extends JPanel{
	
	public DataPanel(){
		this.setBounds(0,0,916, 682);
		this.setVisible(true);
	}
	
	public void update(){
		this.removeAll();
		this.setBounds(0,0,916, 682);
		this.setLayout(null);
		int size = Runner.drawPlanets.size()-1;
		JPanel panels [] = new JPanel[size];
		for(int i = 0; i < size; i++){
			if(!Runner.drawPlanets.get(i+1).getName().equals("Rocket")){
				panels[i] = new JPanel(null);
				panels[i].setBounds((int)(i%2)*458,((int)(i)/2)*227,458,227);
				panels[i].setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.GRAY));
				JLabel name = new JLabel("Name:");
				name.setBounds(10,12,100,20);
				name.setForeground(Color.DARK_GRAY);
				name.setFont(new Font("Times New Roman", 1, 24));
				panels[i].add(name);
				JLabel pName = new JLabel(Runner.drawPlanets.get(i+1).getName());
				pName.setBounds(90,12,500,20);
				pName.setForeground(Color.GRAY);
				pName.setFont(new Font("Times New Roman", 1, 24));
				panels[i].add(pName);
				JLabel x = new JLabel("X-Pos (m):");
				x.setBounds(10,57,120,20);
				x.setForeground(Color.DARK_GRAY);
				x.setFont(new Font("Times New Roman", 1, 24));
				panels[i].add(x);
				JLabel pX = new JLabel(""+(new DecimalFormat("###,###,###,###,###").format(
						Math.round(Runner.drawPlanets.get(i+1).x()))));
				pX.setBounds(125,57,500,25);
				pX.setForeground(Color.GRAY);
				pX.setFont(new Font("Times New Roman", 1, 24));
				panels[i].add(pX);
				JLabel y = new JLabel("Y-Pos (m):");
				y.setBounds(10,102,120,20);
				y.setForeground(Color.DARK_GRAY);
				y.setFont(new Font("Times New Roman", 1, 24));
				panels[i].add(y);
				JLabel pY = new JLabel(""+(new DecimalFormat("###,###,###,###,###").format(
						Math.round(Runner.drawPlanets.get(i+1).y()))));
				pY.setBounds(125,102,500,25);
				pY.setForeground(Color.GRAY);
				pY.setFont(new Font("Times New Roman", 1, 24));
				panels[i].add(pY);
				JLabel xV = new JLabel("X-Vel (m/s):");
				xV.setBounds(10,147,130,20);
				xV.setForeground(Color.DARK_GRAY);
				xV.setFont(new Font("Times New Roman", 1, 24));
				panels[i].add(xV);
				JLabel pXV = new JLabel(""+(new DecimalFormat("###,###,###.##").format(
						Math.round(Runner.drawPlanets.get(i+1).getDx()*100.0)/100.0)));
				pXV.setBounds(140,147,500,25);
				pXV.setForeground(Color.GRAY);
				pXV.setFont(new Font("Times New Roman", 1, 24));
				panels[i].add(pXV);
				JLabel yV = new JLabel("Y-Vel (m/s):");
				yV.setBounds(10,192,130,20);
				yV.setForeground(Color.DARK_GRAY);
				yV.setFont(new Font("Times New Roman", 1, 24));
				panels[i].add(yV);
				JLabel pYV = new JLabel(""+(new DecimalFormat("###,###,###.##").format(
						Math.round(Runner.drawPlanets.get(i+1).getDy()*100.0)/100.0)));		
				pYV.setBounds(140,192,500,25);
				pYV.setForeground(Color.GRAY);
				pYV.setFont(new Font("Times New Roman", 1, 24));
				panels[i].add(pYV);
				
				this.add(panels[i]);
			}
		}
		for(int i = 0;i<4-size;i++){
			panels[i] = new JPanel(null);
			panels[i].setBounds((int)((i+size)%2)*458,((int)(i+size)/2)*227,458,227);
			panels[i].setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.GRAY));
			
			this.add(panels[i]);
		}
		int index = Runner.drawPlanets.size()-1;
		if(Runner.drawPlanets.get(index).getName().equals("Rocket")){
			JPanel rocket = new JPanel(null);
			rocket.setBounds(0,454,916,228);
			rocket.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.DARK_GRAY, Color.GRAY));
			JLabel name = new JLabel("Name:");
			name.setBounds(10,12,100,20);
			name.setForeground(Color.DARK_GRAY);
			name.setFont(new Font("Times New Roman", 1, 24));
			rocket.add(name);
			JLabel pName = new JLabel(Runner.drawPlanets.get(index).getName());
			pName.setBounds(90,12,500,20);
			pName.setForeground(Color.GRAY);
			pName.setFont(new Font("Times New Roman", 1, 24));
			rocket.add(pName);
			JLabel x = new JLabel("X-Pos (m):");
			x.setBounds(10,57,120,20);
			x.setForeground(Color.DARK_GRAY);
			x.setFont(new Font("Times New Roman", 1, 24));
			rocket.add(x);
			JLabel pX = new JLabel(""+(new DecimalFormat("###,###,###,###,###").format(
					Math.round(Runner.drawPlanets.get(index).x()))));
			pX.setBounds(125,57,500,25);
			pX.setForeground(Color.GRAY);
			pX.setFont(new Font("Times New Roman", 1, 24));
			rocket.add(pX);
			JLabel y = new JLabel("Y-Pos (m):");
			y.setBounds(10,102,120,20);
			y.setForeground(Color.DARK_GRAY);
			y.setFont(new Font("Times New Roman", 1, 24));
			rocket.add(y);
			JLabel pY = new JLabel(""+(new DecimalFormat("###,###,###,###,###").format(
					Math.round(Runner.drawPlanets.get(index).y()))));
			pY.setBounds(125,102,500,25);
			pY.setForeground(Color.GRAY);
			pY.setFont(new Font("Times New Roman", 1, 24));
			rocket.add(pY);
			JLabel xV = new JLabel("X-Vel (m/s):");
			xV.setBounds(10,147,130,20);
			xV.setForeground(Color.DARK_GRAY);
			xV.setFont(new Font("Times New Roman", 1, 24));
			rocket.add(xV);
			JLabel pXV = new JLabel(""+(new DecimalFormat("###,###,###.##").format(
					Math.round(Runner.drawPlanets.get(index).getDx()*100.0)/100.0)));
			pXV.setBounds(140,147,500,25);
			pXV.setForeground(Color.GRAY);
			pXV.setFont(new Font("Times New Roman", 1, 24));
			rocket.add(pXV);
			JLabel yV = new JLabel("Y-Vel (m/s):");
			yV.setBounds(10,192,130,20);
			yV.setForeground(Color.DARK_GRAY);
			yV.setFont(new Font("Times New Roman", 1, 24));
			rocket.add(yV);
			JLabel pYV = new JLabel(""+(new DecimalFormat("###,###,###.##").format(
					Math.round(Runner.drawPlanets.get(index).getDy()*100.0)/100.0)));		
			pYV.setBounds(140,192,500,25);
			pYV.setForeground(Color.GRAY);
			pYV.setFont(new Font("Times New Roman", 1, 24));
			rocket.add(pYV);
			JLabel dV1 = new JLabel("Initial Delta-V (m/s):");
			dV1.setBounds(468,12,400,25);
			dV1.setForeground(Color.black);
			dV1.setFont(new Font("Times New Roman", 1, 24));
			rocket.add(dV1);
			JLabel pDV1 = new JLabel(""+(new DecimalFormat("###,###,###.##").format(
					Math.round(TrajectoryCalculator.deltaV1()*100.0)/100.0)));		
			pDV1.setBounds(690,12,400,25);
			pDV1.setForeground(Color.GRAY);
			pDV1.setFont(new Font("Times New Roman", 1, 24));
			rocket.add(pDV1);
			JLabel dV2 = new JLabel("Final Delta-V (m/s):");
			dV2.setBounds(468,57,400,25);
			dV2.setForeground(Color.black);
			dV2.setFont(new Font("Times New Roman", 1, 24));
			rocket.add(dV2);
			JLabel pDV2 = new JLabel(""+(new DecimalFormat("###,###,###.##").format(
					Math.round(TrajectoryCalculator.deltaV2()*100.0)/100.0)));		
			pDV2.setBounds(678,57,400,25);
			pDV2.setForeground(Color.GRAY);
			pDV2.setFont(new Font("Times New Roman", 1, 24));
			rocket.add(pDV2);
			JLabel ecc = new JLabel("Eccentricity:");
			ecc.setBounds(468,102,400,25);
			ecc.setForeground(Color.black);
			ecc.setFont(new Font("Times New Roman", 1, 24));
			rocket.add(ecc);
			JLabel pEcc = new JLabel(""+(new DecimalFormat("###,###,###.#####").format(
					Math.round(TrajectoryCalculator.getEcc()*10000.0)/10000.0)));		
			pEcc.setBounds(612,102,400,25);
			pEcc.setForeground(Color.GRAY);
			pEcc.setFont(new Font("Times New Roman", 1, 24));
			rocket.add(pEcc);
			JLabel a = new JLabel("Semi-Major Axis (m):");
			a.setBounds(468,147,400,25);
			a.setForeground(Color.black);
			a.setFont(new Font("Times New Roman", 1, 24));
			rocket.add(a);
			JLabel pA = new JLabel(""+(new DecimalFormat("###,###,###.##").format(
					Math.round(TrajectoryCalculator.getA()*100.0)/100.0)));		
			pA.setBounds(698,147,400,25);
			pA.setForeground(Color.GRAY);
			pA.setFont(new Font("Times New Roman", 1, 24));
			rocket.add(pA);
			JLabel tof = new JLabel("Time of Flight (s):");
			tof.setBounds(468,192,400,25);
			tof.setForeground(Color.black);
			tof.setFont(new Font("Times New Roman", 1, 24));
			rocket.add(tof);
			JLabel pTof = new JLabel(""+(new DecimalFormat("###,###,###.##").format(
					Math.round(TrajectoryCalculator.getTof()*100.0)/100.0)));		
			pTof.setBounds(659,192,400,25);
			pTof.setForeground(Color.GRAY);
			pTof.setFont(new Font("Times New Roman", 1, 24));
			rocket.add(pTof);
			this.add(rocket);
		}
		JPanel bottomLeft = new JPanel();
		this.add(bottomLeft);
		JPanel bottomRight = new JPanel();
		this.add(bottomRight);
	}
}
