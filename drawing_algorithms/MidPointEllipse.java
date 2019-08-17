package drawing_algorithms;

import java.util.Vector;

public class MidPointEllipse {

	private int a;
	private int b;
	private int color;
	
	private Vector <Integer> pixel_x;
	private Vector <Integer> pixel_y;
	
	public MidPointEllipse (int a,int b,int color) {
	
		this.a=a;
		this.b=b;		
		this.color=color;
		pixel_x = new Vector<Integer>();
		pixel_y = new Vector<Integer>();
		findPoints();
	}
	private void findPoints(){
		
		double d1= b*b-b*a*a-(0.25)*a*a;
		double d2= b*b-b*a*a-(0.25)*a*a;
		int x=0;
		int y=b;

		pixel_x.add(x);
		pixel_y.add(y);
		
		while (a*a*(y-0.5)>b*b*(x+1)){
			if(d1<0){
				d1+= b*b*(2*x+3);
			}
			else{
				d1+= b*b*(2*x+3) + a*a*(-2*y+2);
				
				y--;
			}
			x++;
			pixel_x.add(x);
			pixel_y.add(y);
		}
		d2= b*b*(x+0.5)*(x+0.5)+a*a*(y-1)*(y-1)-a*a*b*b;
		
		while(y>0){
			if(d2<0){
				d2+=b*b*(2*x+2)+ a*a*(-2*y+3);
				x++;
			}
			else {
				d2+= a*a*(-2*y+3);
			}
			y--;
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
