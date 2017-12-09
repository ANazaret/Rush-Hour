import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RushHourGraphics extends JFrame {
	private RushHourGame game;
	
	public RushHourGraphics(RushHourGame game) {
		this.game = game;
		
		
		this.setSize(400,400);
		this.setTitle("RushHour");
		
		this.setContentPane(new Board(this.game));
		this.setVisible(true);
		
		
	}
		
}



class Board extends JPanel{
	
	private RushHourGame game;
	
	public Board(RushHourGame game) {
		this.game = game;
				
	}
	
	private static Color[] colors = new Color[] {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN, Color.GRAY, Color.PINK, Color.ORANGE};
	
	
	public void paintComponent(Graphics g) {
		int w = this.getWidth();
		int h = this.getHeight();
		
		
		
		int interW =  w/ game.size;
		int interH = h/ game.size;
		
		//Draw grid		
		//Horizontal
		for (int i=0; i<=game.size; i++) {
			g.drawLine(0,i*interH,w,interH*i);
		}
		//Vertical
		for (int j=0; j<= game.size; j++) {
			g.drawLine(interW*j,0, interW*j,h);
		}
		
		
		int color_index = 0;
		g.setFont(new Font("TimesRoman", Font.PLAIN, Integer.min(interH, interW))); 
				
		for (Vehicule v : game.vList) {
			if (color_index >= colors.length)
				color_index = 1;
			
			
			
			g.setColor(Board.colors[color_index]);
			
			if (v.orientation == 0) 
				g.fillRect( v.x*interW,  v.y*interH ,v.length*interW ,interH);				
			else
				g.fillRect( v.x*interW,  v.y*interH ,interW ,v.length*interH);
			
			g.setColor(Color.BLACK);
			g.drawString(""+v.id, v.x*interW + interW/4, v.y*interH +interH*3/4);
			
			color_index ++;
		}
		
	}
}
