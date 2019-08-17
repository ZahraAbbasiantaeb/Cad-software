package drawing_algorithms;

import java.util.Vector;

public class MidPointLine {

	private int x0;
	private int x1;
	private int y0;
	private int y1;
	private int color;
	private Vector <Integer> pixel_x;
	private Vector <Integer> pixel_y;
	public MidPointLine (int x0,int y0, int x1 , int y1, int color) {
	
		this.x0=x0;
		this.y0=y0;
		this.x1=x1;
		this.y1=y1;
		this.color=color;
		pixel_x = new Vector<Integer>();
		pixel_y = new Vector<Integer>();
		findPoints();
	}
	private void findPoints(){
		
		int dx= x1-x0;
		int dy= y1-y0;
		int d= 2*dy-dx;
		int incrE= 2*dy;
		int incrNE = 2*(dy-dx);
		int x=x0;
		int y=y0;
		pixel_x.add(x);
		pixel_y.add(y);
		
		if(dy==0){
			while (x<x1){
				
				pixel_x.add(x);
				pixel_y.add(y);
				x++;
			}
		}
		else if (dx==0){
				while (y<y1){			
				pixel_x.add(x);
				pixel_y.add(y);
				y++;
			}
		}
		else{
		while (x<x1){
			System.out.println("came");
			
			if(d<=0){
				d+= incrE; x++;
			}
			else{
				d+=incrNE;
				x++;
				y++;
			}
			pixel_x.add(x);
			pixel_y.add(y);
		} 
		}
	}
	public Vector<Integer> getPixel_X (){
		return this.pixel_x;
	}
	
	public Vector<Integer> getPixel_Y (){
		return this.pixel_y;
	}
	public int getColor(){
		return this.color;
	}
	
}
