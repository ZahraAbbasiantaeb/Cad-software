package drawing_algorithms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.JPanel;

public class DirectDrawDemo extends JPanel {

    private BufferedImage canvas;
    private Dimension screenSize;
    public DirectDrawDemo(int width, int height) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        fillCanvas(Color.gray);
    }

    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }


    public void fillCanvas(Color c) {
        int color = c.getRGB();
        for (int x = 0; x < canvas.getWidth(); x++) {
            for (int y = 0; y < canvas.getHeight(); y++) {
                canvas.setRGB(x, y, color);
            }
        }
        repaint();
    }


    public void fillSpans(Vector<Integer>x1,Vector<Integer>x2,Vector<Integer>y){
    	for(int i=0; i<x1.size();i++){
    		drawLine(x1.elementAt(i),y.elementAt(i),x2.elementAt(i),y.elementAt(i),Color.magenta);
    	}
    }
    public void drawLine( int x1, int y1, int x2, int y2, Color c) {
    
    	int color = c.getRGB();
    	MidPointLine mpl = new MidPointLine(x1, y1, x2, y2, color);
    	int size = mpl.getPixel_X().size();
    	
    	for(int i=0; i< size;i++)
    		canvas.setRGB(mpl.getPixel_X().elementAt(i), mpl.getPixel_Y().elementAt(i), color);
        
    	repaint();
    }

    public void drawCircle(Color c, int x0, int y0, int R) {
        
    	int color = c.getRGB();
    	MidpointCircle mpc = new MidpointCircle(R, color);
    	int size = mpc.getPixel_X().size();
    	
    	for(int i=0; i< size;i++)
    	{
    		canvas.setRGB(mpc.getPixel_X().elementAt(i)+x0, mpc.getPixel_Y().elementAt(i)+y0, color);
    		canvas.setRGB(-mpc.getPixel_X().elementAt(i)+x0, mpc.getPixel_Y().elementAt(i)+y0, color);
    		canvas.setRGB(-mpc.getPixel_X().elementAt(i)+x0, -mpc.getPixel_Y().elementAt(i)+y0, color);
    		canvas.setRGB(mpc.getPixel_X().elementAt(i)+x0, -mpc.getPixel_Y().elementAt(i)+y0, color);
    		canvas.setRGB(mpc.getPixel_Y().elementAt(i)+x0, mpc.getPixel_X().elementAt(i)+y0, color);
    		canvas.setRGB(-mpc.getPixel_Y().elementAt(i)+x0, mpc.getPixel_X().elementAt(i)+y0, color);
    		canvas.setRGB(-mpc.getPixel_Y().elementAt(i)+x0, -mpc.getPixel_X().elementAt(i)+y0, color);
    		canvas.setRGB(mpc.getPixel_Y().elementAt(i)+x0, -mpc.getPixel_X().elementAt(i)+y0, color);
    	}
        repaint();
    }

    public void drawEllipse(Color c, int x1, int y1, int a, int b) {
       
    	int color = c.getRGB();
    	MidPointEllipse mpc = new MidPointEllipse(a,b,color);
    	int size = mpc.getPixel_X().size();
    	
    	for(int i=0; i< size;i++)
    	{
    		canvas.setRGB(mpc.getPixel_X().elementAt(i)+x1, mpc.getPixel_Y().elementAt(i)+y1, color);
    		canvas.setRGB(mpc.getPixel_X().elementAt(i)+x1, -mpc.getPixel_Y().elementAt(i)+y1, color);
    		canvas.setRGB(-mpc.getPixel_X().elementAt(i)+x1, mpc.getPixel_Y().elementAt(i)+y1, color);
    		canvas.setRGB(-mpc.getPixel_X().elementAt(i)+x1, -mpc.getPixel_Y().elementAt(i)+y1, color);
    	}
        repaint();
    }



    

}