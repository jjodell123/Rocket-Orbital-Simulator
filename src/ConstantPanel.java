import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConstantPanel extends JPanel{
	
	public ConstantPanel(){
		this.setBounds(0,0,916, 682);
	}
	
	public void setScenario(int scen){
		JPanel panel = new JPanel();
		this.removeAll();
		if(scen == 1){
			panel.setLayout(new GridLayout(1,5));
	
			JLabel G = new JLabel("G: 6.67428 x 10^-11");
			G.setForeground(Color.DARK_GRAY);
			G.setFont(new Font("Times New Roman", 1, 24));
			panel.add(G);
			
			JLabel sun = new JLabel("Sun's Mass: 1.989 x 10^30");
			sun.setForeground(Color.DARK_GRAY);
			sun.setFont(new Font("Times New Roman", 1, 24));
			panel.add(sun);
			
			this.add(panel);
			
		}
		else if(scen == 2 || scen == 3){
			panel.setLayout(new GridLayout(1,5));
			
			JLabel G = new JLabel("G: 6.67428 x 10^-11");
			G.setForeground(Color.DARK_GRAY);
			G.setFont(new Font("Times New Roman", 1, 24));
			panel.add(G);
			
			JLabel sun = new JLabel("Earth's Mass: 5.972 x 10^24");
			sun.setForeground(Color.DARK_GRAY);
			sun.setFont(new Font("Times New Roman", 1, 24));
			panel.add(sun);
			
			this.add(panel);
		}
	}

}
