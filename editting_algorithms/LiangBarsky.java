package editting_algorithms;

import drawing_algorithms.DirectDrawDemo;

import java.awt.Color;

public class LiangBarsky {
	private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;
    
    public int x0;
    public int y0;
    public int x1;
    public int y1;
    private DirectDrawDemo demo;
   
    public LiangBarsky(Line line , Square square, DirectDrawDemo demo){
    	
    	 this.x0 = line.x0;
         this.y0 = line.y0;
         this.x1 = line.x1;
         this.y1 = line.y1;
         
         this.xMin=square.xmin;
         this.xMax=square.xmax;
         this.yMin=square.ymin;
         this.yMax=square.ymax;
         
         this.demo=demo;
      	 demo.drawLine(x0, y0, x1, y1 ,Color.GREEN);
         lineClipAndDraw();
    }


	private void lineClipAndDraw() {
		
		double u1 = 0, u2 = 1;
        int dx = x1 - x0, dy = y1 - y0;
        int p[] = {-dx, dx, -dy, dy};
        int q[] = {x0 - xMin, xMax - x0, y0 - yMin, yMax - y0};
        
        for (int i = 0; i < 4; i++) {
            if (p[i] == 0) {
                if (q[i] < 0) {
                    return ;
                }
            } else {
                double u = (double) q[i] / p[i];
                if (p[i] < 0) {
                    u1 = Math.max(u, u1);
                } else {
                    u2 = Math.min(u, u2);
                }
            }
        }
        if (u1 > u2) {
            return ;
        }
        
        int nx0, ny0, nx1, ny1;
        
        nx0 = (int) (x0 + u1 * dx);
        ny0 = (int) (y0 + u1 * dy);
        nx1 = (int) (x0 + u2 * dx);
        ny1 = (int) (y0 + u2 * dy);
        
        Line line= new Line(nx0, ny0, nx1, ny1);
        System.out.println("this part of line accepted:");
        demo.drawLine(xMin, yMin, xMax, yMin ,Color.RED);
   		demo.drawLine(xMin, yMin, xMin, yMax ,Color.RED);
    	demo.drawLine(xMax, yMin ,xMax, yMax, Color.RED);
    	demo.drawLine(xMin, yMax ,xMax, yMax, Color.RED);
    	demo.drawLine(nx0, ny0, nx1, ny1 ,Color.orange);
    	Line m= new Line(nx0, ny0, nx1, ny1);
    }
	}

