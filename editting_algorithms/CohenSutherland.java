package editting_algorithms;

import java.awt.Color;

import drawing_algorithms.DirectDrawDemo;



public class CohenSutherland {

	public static final int INSIDE = 0;
    public static final int LEFT   = 1;
    public static final int RIGHT  = 2;
    public static final int BOTTOM = 4;
    public static final int TOP    = 8;
    
    private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;
    
    public int x0;
    public int y0;
    public int x1;
    public int y1;
    
   private 	DirectDrawDemo demo;
    public CohenSutherland(Line line , Square square,DirectDrawDemo demo){
    	
    	 this.demo=demo;
    	 this.x0 = line.x0;
         this.y0 = line.y0;
         this.x1 = line.x1;
         this.y1 = line.y1;
         
         this.xMin=square.xmin;
         this.xMax=square.xmax;
         this.yMin=square.ymin;
         this.yMax=square.ymax;
         
     	demo.drawLine(x0, y0, x1, y1 ,Color.GREEN);
         lineClipAndDraw();
    }
    
	void lineClipAndDraw(){
		
		int outCode0 = computeOutCode(x0, y0);
        int outCode1 = computeOutCode(x1, y1);
        boolean accept = false;
        boolean done =false;
        
        while (!done) {
            if ((outCode0 | outCode1) == 0) { 
                accept = true;
                done=true;
            } 
            else if ((outCode0 & outCode1) != 0) {
            	done=true;
            } 
            else {
            	int x, y;

                int outCodeOut = (outCode0 != 0) ? outCode0 : outCode1;


                if ((outCodeOut & TOP) != 0) {
                    x = x0 + (x1 - x0) * (yMax - y0) / (y1 - y0);
                    y = yMax;
                } else if ((outCodeOut & BOTTOM) != 0) {
                    x = x0 + (x1 - x0) * (yMin - y0) / (y1 - y0);
                    y = yMin;
                } else if ((outCodeOut & RIGHT) != 0) {
                    y = y0 + (y1 - y0) * (xMax - x0) / (x1 - x0);
                    x = xMax;
                } else {
                    y = y0 + (y1 - y0) * (xMin - x0) / (x1 - x0);
                    x = xMin;
                }


                if (outCodeOut == outCode0) {
                    x0 = x;
                    y0 = y;
                    outCode0 = computeOutCode(x0, y0);
                } else {
                    x1 = x;
                    y1 = y;
                    outCode1 = computeOutCode(x1, y1);
                }
            }
        }
        if(accept){
        	System.out.println("this part of line accepted:");
        	demo.drawLine(xMin, yMin, xMax, yMin ,Color.RED);
       		demo.drawLine(xMin, yMin, xMin, yMax ,Color.RED);
        	demo.drawLine(xMax, yMin ,xMax, yMax, Color.RED);
        	demo.drawLine(xMin, yMax ,xMax, yMax, Color.RED);
        	demo.drawLine(x0, y0, x1, y1 ,Color.orange);

        }
        else{
        	System.out.println("The line rejected :/");
        }
	}
	
	private int computeOutCode(double x, double y) {
        int code = INSIDE;
        
        if (x < xMin) {
            code |= LEFT;
        } 
        else if (x > xMax) {
            code |= RIGHT;
        }
        if (y < yMin) {
            code |= BOTTOM;
        } 
        else if (y > yMax) {
            code |= TOP;
        }

        return code;
    }
	
}
