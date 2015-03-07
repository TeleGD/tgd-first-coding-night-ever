package fr.tgd.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class WallGenerator {
	private World world ;
	private int nextInterval,time ;
	private static int minInterval=50 ;
	private static int maxInterval=100 ;
	public Random rand = new Random();
	
	private static int holeSize = 25 ; 
	
	public WallGenerator(World world) {
		this.world= world;
	}
	
	public void update() {
		time++ ; 
		if(time > nextInterval/2) {
			ArrayList<Double> wallPos = calcWallPos();
			
			for(int i=0; i<(wallPos.size())/2+1; i++)
			{
				double xLeft = 0, xRight = 500;
				if(i!=0)
					xLeft = wallPos.get(2*i-1);
				if(i != (wallPos.size())/2)
					xRight = wallPos.get(2*i);
				Wall wall = new Wall(xLeft, 0, xRight-xLeft, 20);
				world.addWall(wall);
			}
			
			time=0;
			nextInterval= rand.nextInt(maxInterval-minInterval)+minInterval ; 
		}
	}

	private ArrayList<Double> calcWallPos() {
		System.out.println("GEN !");
		ArrayList<Double> pos = new ArrayList<Double>();
		ArrayList<WallDim> dims = new ArrayList<WallDim>();
		for(int i=0; i<3; i++)
		{
			dims.add(new WallDim((double)rand.nextInt(500), (double)holeSize));
		}

		boolean holeMerged = true;
		while(holeMerged)
		{
			dims.sort(new WallDimComparator());


			holeMerged = false;
			for(int i=0; i<dims.size() && !holeMerged; i++)
			{
				for(int j=i+1; j<dims.size() && !holeMerged; j++)
				{
					double xLi = dims.get(i).getXLeft();
					double xRi = dims.get(i).getXRight();
					double xRj = dims.get(j).getXRight();
					double xLj = dims.get(j).getXLeft();
					if(xRi > xLj)
					{
						double xC = (xLi+xRj)/2;
						double dim = (xRj-xLi)/2;
						
						dims.remove(j);
						dims.remove(i);
						dims.add(new WallDim(xC, dim));
						holeMerged = true;
					}
				}
			}
		}

		for(int i=0; i<dims.size(); i++)
		{
			pos.add(dims.get(i).getXLeft());
			pos.add(dims.get(i).getXRight());
		}
		Collections.sort(pos);
		return pos;
	}
	
	private class WallDim 
	{
		public double center, dim;
		
		public WallDim(double center, double dim)
		{
			this.center = center;
			this.dim = dim;
		}
		
		public double getXLeft()
		{
			return center - dim;
		}
		
		public double getXRight()
		{
			return center + dim;
		}
	}
	
	private class WallDimComparator implements Comparator
	{

		@Override
		public int compare(Object o1, Object o2) {
			return (int) (((WallDim)o1).center-((WallDim)o2).center);
		}
		
	}
}