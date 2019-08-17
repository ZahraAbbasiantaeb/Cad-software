package drawing_algorithms;

import editting_algorithms.LiangBarsky;
import editting_algorithms.Line;
import editting_algorithms.Square;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main {

	private DirectDrawDemo panel;
	
	public Main(){
		
		setUpPannel();
//		panel.drawLine(200,300 ,30,50,Color.ORANGE);
//        panel.drawCircle(Color.green, 200,200, 100) ;
//       panel.drawEllipse(Color.blue,300,400,100,50);
//        panel.drawLine(20,30,400,100,Color.ORANGE);
//        panel.drawCircle(Color.green, 500,500, 200) ;
//        panel.drawEllipse(Color.blue,700,100,200,50);
		
//		CohenSutherland clip= new CohenSutherland(new Line(0, 0, 600, 300),new Square(100, 100, 400, 400),panel);	
		LiangBarsky clip2 = new LiangBarsky(new Line(200, 100, 400, 200),new Square(100, 100, 400, 400),panel);
//		int[]x= new int[4];
//		int[]y= new int[4];
//		x[0]=70;
//		x[1]=130;
//		x[2]=130;
//		x[3]=70;
//		
//		y[0]=10;
//		y[1]=10;
//		y[2]=50;
//		y[3]=50;
//		
//		
//        FillPolygon fp = new FillPolygon(x, y, 4,panel);
	}
	
	
	private  void setUpPannel() {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        JFrame frame = new JFrame("Direct draw demo");
        panel = new DirectDrawDemo(width, height);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	
	public static void main(String []arg0){
		
		Main m = new Main();
	}
}
