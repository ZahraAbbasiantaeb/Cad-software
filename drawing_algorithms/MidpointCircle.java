package drawing_algorithms;

import java.util.Vector;

public class MidpointCircle {

	private int R;
	private int color;
	private Vector <Integer> pixel_x;
	private Vector <Integer> pixel_y;
	
	public MidpointCircle (int R ,int color) {
		
		this.R=R;		
		this.color=color;
		pixel_x = new Vector<Integer>();
		pixel_y = new Vector<Integer>();
		findPoints();
		
	}
	private void findPoints(){
		
		
		
		int x=0;
		int y=R;
		int d= 1-R;
		int incrE= 3;
		int incrSE = 5-2*R;		
		pixel_x.add(x);
		pixel_y.add(y);
		
		while (y>x){
			if(d<0){
				d+= incrE;
				incrE+=2;
				incrSE+=2;
			}
			else{
				d+=incrSE;
				incrE+=2;
				incrSE+=4;
				y--;
			}
			x++;
			pixel_x.add(x);
			pixel_y.add(y);
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
