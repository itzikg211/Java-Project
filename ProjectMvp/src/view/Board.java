package view;



import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import algorithms.mazeGenerators.Cell;
import algorithms.mazeGenerators.Maze;

public class Board extends Composite
{
	
	int mazeR;
	int mazeC;
	Tile[][] tiles;
	Maze m;
	int boatI;
	int boatJ;
	public Board(Composite parent, int style) 
	{
		super(parent, style);
		this.boatI=0;
		this.boatJ=0;
		addPaintListener(new PaintListener() 
		{
			
			@Override
			public void paintControl(PaintEvent arg0) 
			{
				if(tiles!=null)
					for(int i=0;i<tiles.length;i++)
						for(int j=0;j<tiles[0].length;j++)
							tiles[i][j].redraw();
			}
		});
	}
	
	public void displayMaze(Maze m)
	{
		this.m=m;
		if(tiles!=null) 
			delMaze();
		mazeR=m.getRows();
		mazeC=m.getCols();
		GridLayout layout=new GridLayout(mazeC, true);
		layout.horizontalSpacing=0;
		layout.verticalSpacing=0;
		setLayout(layout);
		tiles=new Tile[mazeR][mazeC];
		for(int i=0;i<mazeR;i++)
			for(int j=0;j<mazeC;j++)
			{
				tiles[i][j]=new Tile(this,SWT.NONE);
				tiles[i][j].setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				tiles[i][j].setImage(setImg(m,i,j));
			}
		layout();
	}
	public boolean canMove(int i,int j,int dir)
	{
		System.out.println();
		//0 means top
		if(dir==0)
		{
			if(i==0)
				return false;
			else
			{
				/*System.out.println("Place : " + (i-1) + "," + j);
				System.out.println("has top wall : " + (m.getCell(i-1,j).getHasTopWall()));
				System.out.println("has right wall : " + (m.getCell(i-1,j).getHasRightWall()));
				System.out.println("has bottom wall : " + (m.getCell(i-1,j).getHasBottomWall()));
				System.out.println("has left wall : " + (m.getCell(i-1,j).getHasLeftWall()));*/
				if(this.m.getCell(i-1, j).getHasBottomWall()==false)
				{
					return true;
				}
				return false;
			}
		}
		//1 means right
		if(dir==1)
		{
			if(j==this.m.getCols()-1)
			{
				return false;
			}
			else
			{
				if(this.m.getCell(i, j).getHasLeftWall()==false)
				{
					return true;
				}
				return false;
			}
		}
		//2 means bottom
		if(dir==2)
		{	if(i==this.m.getRows()-1)
			{
				return false;
			}
			else
			{
				if(this.m.getCell(i, j).getHasBottomWall()==false)
					return true;
				return false;
			}
		}
		//3 means left
		if(dir==3)
		{
			if(j==0)
			{
				return false;
			}
			else
			{
				if(this.m.getCell(i, j-1).getHasLeftWall()==false)
					return true;
				return false;
			}
		}
		return false;

	}
	private void delMaze()
	{
		for(int i=0;i<tiles.length;i++)
			for(int j=0;j<tiles[0].length;j++)
			{
				tiles[i][j].dispose();
			}
	}
	private Image setImg(Maze maze,int i,int j)
	{
		if(i==0 && j==0)
        {
	          if(maze.getCell(i, j).getHasLeftWall() && maze.getCell(i, j).getHasBottomWall())
	        	  return new Image(getDisplay(),new ImageData("resources/state 4.png"));
	          else 
	          {
	        	  if(!maze.getCell(i, j).getHasBottomWall() && !maze.getCell(i, j).getHasLeftWall())
	        		  return new Image(getDisplay(),new ImageData("resources/state 12.png"));
	        	  else
	        	  {
	        		  if(maze.getCell(i, j).getHasBottomWall())
	        			  return new Image(getDisplay(),new ImageData("resources/state 9.png"));
	        		  else
	        			  return new Image(getDisplay(),new ImageData("resources/state 5.png"));
	        	  }
      	  }
        }
        else
        {
      	  	if(i==0 && j!=0)
      	  	{
		          if(maze.getCell(i, j).getHasLeftWall() && maze.getCell(i, j).getHasBottomWall())
		        	  return new Image(getDisplay(),new ImageData("resources/state 16.png"));
		          else 
		          {
		        	  if(!maze.getCell(i, j).getHasBottomWall() && !maze.getCell(i, j).getHasLeftWall())
		        		  return new Image(getDisplay(),new ImageData("resources/state 7.png"));
		        	  else
		        	  {
		        		  if(maze.getCell(i, j).getHasBottomWall())
		        			  return new Image(getDisplay(),new ImageData("resources/state 6.png"));
		        		  else
		        			  return new Image(getDisplay(),new ImageData("resources/state 13.png"));
		        	  }
	        	  } 
	          }
        
        else
        {
      	  if(j==0 && i!=0)
	          {
		          if(maze.getCell(i, j).getHasLeftWall() && maze.getCell(i, j).getHasBottomWall())
		        	  return new Image(getDisplay(),new ImageData("resources/state 10.png"));
		          else 
		          {
		        	  if(!maze.getCell(i, j).getHasBottomWall() && !maze.getCell(i, j).getHasLeftWall())
		        		  return new Image(getDisplay(),new ImageData("resources/state 8.png"));
		        	  else
		        	  {
		        		  if(maze.getCell(i, j).getHasBottomWall())
		        			  return new Image(getDisplay(),new ImageData("resources/state 14.png"));
		        		  else
		        			  return new Image(getDisplay(),new ImageData("resources/state 15.png"));
		        	  }
	        	  }
	          }
      	  else
	          {
		          if(maze.getCell(i, j).getHasLeftWall() && maze.getCell(i, j).getHasBottomWall())
		        	  return new Image(getDisplay(),new ImageData("resources/state 4.png"));
		          else 
		          {
		        	  if(!maze.getCell(i, j).getHasBottomWall() && !maze.getCell(i, j).getHasLeftWall())
		        		  return new Image(getDisplay(),new ImageData("resources/state 3.png"));
		        	  else
		        	  {
		        		  if(maze.getCell(i, j).getHasBottomWall())
		        			  return new Image(getDisplay(),new ImageData("resources/state 2.png"));
		        		  else
		        			  return new Image(getDisplay(),new ImageData("resources/state 1.png"));		        	  }
	        	  }
	          }
        }
        }
		
	}
	public void setX(int a)
	{
		this.boatI=a;
	}
	public void setY(int a)
	{
		this.boatJ=a;
	}
	public int getX()
	{
		return this.boatI;
	}
	public int getY()
	{
		return this.boatJ;
	}
	public void setPos(int a,int b)
	{
		this.boatI=a;
		this.boatJ=b;
	}
	public Maze getMaze()
	{
		return this.m;
	}
}
