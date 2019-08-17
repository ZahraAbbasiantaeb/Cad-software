package editting_algorithms;

import drawing_algorithms.DirectDrawDemo;

import java.util.Vector;

public class FillPolygon {

	DirectDrawDemo demo;
	int edges; 
	int activeEdges; 

	int[] x_polygon; 
	int[] y_polygon; 
	int num_cordinates;
	
	Vector<Integer> x1_of_line;
	Vector<Integer> x2_of_line;
	Vector<Integer> y_of_line;
	
	int y_max;
	double[] x_ET;	
	int[] y_ET_up;	
	int[] y_ET_down;	
	double[] slope_ET;   

	int[] sorted_edge;

	int[] active_edge; 



	public FillPolygon(int[] x, int[] y, int n,DirectDrawDemo demo) {
		this.demo=demo;
		setPolygon(x, y, n);
		fillPolygon();
	 }

	public void setPolygon(int[] x, int[] y, int n) {
		this.x_polygon = x;
		this.y_polygon = y;
		this.num_cordinates = n;
		
		if (x_ET==null || n>x_ET.length) {
			x_ET = new double[n];
			y_ET_up = new int[n];
			y_ET_down = new int[n];
			sorted_edge = new int[n];
			active_edge = new int[n];
			slope_ET = new double[n];
		}
		x1_of_line =new Vector<Integer>();
		x2_of_line =new Vector<Integer>();
		y_of_line =new Vector<Integer>();
	}

	 
	void buildEdgeTable(int[] x, int[] y, int n) {
		
		y_max=0;
		edges = 0;
		int  next, x1, x2, y1, y2;
		for (int i=0; i<n; i++) {
			next = (i+1)%n;
			y1 = y[i];	y2 = y[next];
			x1 = x[i];	x2 = x[next];
			if (y1==y2)
				continue; 
			if (y1>y2) { 
				int tmp = y1;
				y1=y2; y2=tmp;
				tmp=x1;
				x1=x2; x2=tmp;
			}
			if(y_max<y2)
				y_max=y2;
			double slope = (double)(x2-x1)/(y2-y1);
			x_ET[edges] = x1 + slope/2.0; 
			y_ET_up[edges] = y1;
			y_ET_down[edges] = y2;
			slope_ET[edges] = slope;
			edges++;   
		}
		for (int i=0; i<edges; i++)
			sorted_edge[i] = i;
		activeEdges = 0;
	}




	public void fillPolygon() {
		
		buildEdgeTable(x_polygon, y_polygon, num_cordinates);
		int x1, x2;
		for (int y=0; y<y_max; y++) {
			removeInactiveEdges(y);
			add_activeEdges(y);
			for (int i=0; i<activeEdges; i+=2) {
				x1 = (int)(x_ET[active_edge[i]]+0.5);
				x2 = (int)(x_ET[active_edge[i+1]]+0.5); 
				x1_of_line.add(x1);
				x2_of_line.add(x2);
				y_of_line.add(y);
		
			}			
			updateXOfActiveEdge();
		}
		demo.fillSpans(x1_of_line, x2_of_line, y_of_line);
		
	}	

	void sortActiveEdges() {
		int min, tmp;
		for (int i=0; i<activeEdges; i++) {
			min = i;
			for (int j=i; j<activeEdges; j++)
				if (x_ET[active_edge[j]] <x_ET[active_edge[min]]) 
					min = j;
			tmp=active_edge[min];
			active_edge[min] = active_edge[i]; 
			active_edge[i]=tmp;
		}
	}

	void updateXOfActiveEdge() {
		int index;
		double x1=-Double.MAX_VALUE, x2;
		boolean sorted = true;
		for (int i=0; i<activeEdges; i++) {
			index = active_edge[i];
			x2 = x_ET[index] + slope_ET[index];
			x_ET[index] = x2;
			if (x2<x1) 
				sorted = false;
			x1 = x2;
		}
		if (!sorted) 
			sortActiveEdges();
	}




	void add_activeEdges(int y) {
		for (int i=0; i<edges; i++) {
			int edge =sorted_edge[i];
			if (y==y_ET_up[edge]) {
				int index = 0;
				while (index<activeEdges && x_ET[edge]>x_ET[active_edge[index]])
					index++;
				for (int j=activeEdges-1; j>=index; j--) 
					active_edge[j+1] = active_edge[j];
				active_edge[index] = edge;
				activeEdges++;
			}
		}
	}

	void removeInactiveEdges(int y) {
		int i = 0;
		while (i<activeEdges) {
			int index = active_edge[i];
			if (y<y_ET_up[index] || y>=y_ET_down[index]) {
				for (int j=i; j<activeEdges-1; j++)
					active_edge[j] = active_edge[j+1];
				activeEdges--; 
			} else
				i++;		 
		}
	}
	
}
