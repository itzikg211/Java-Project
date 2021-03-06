package view;


import java.awt.event.MouseEvent;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.gdip.PointF;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;

public class Board extends Composite
{
	int mazeR;
	int mazeC;
	Tile[][] tiles;
	Maze m;
	Image image;
	int boatI,boatJ;
	int hintI = -1,hintJ = -1;
	Image beforeImage;
	PaintEvent pe;
	int dir;
	Boat b;
	Solution s;
	
	public Board(Composite parent, int style) 
	{
		super(parent, style);
		
		b = new Boat();
		this.boatI=0;
		this.boatJ=0;
		addPaintListener(new PaintListener() 
		{
			@Override
			public void paintControl(PaintEvent arg0) 
			{
				pe = arg0;
				if(tiles!=null)
				{
					for(int i=0;i<tiles.length;i++)
						for(int j=0;j<tiles[0].length;j++)
							tiles[i][j].redraw();
				}
				if(tiles==null){
					int width=(int)(parent.getSize().x*0.80);
					int height=(int)(parent.getSize().y*0.85);
					ImageData data = new ImageData("resources/mainPic.png");
					arg0.gc.drawImage(new Image(getDisplay(),"resources/mainPic.png"),0,0,data.width,data.height,0,0,width, height);
				}
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

		System.out.println("Display");
		
		GridLayout layout=new GridLayout(mazeC, true);
		layout.horizontalSpacing=0;
		layout.verticalSpacing=0;
		setLayout(layout);
		tiles=new Tile[mazeR][mazeC];
		System.out.println("tiles");
		for(int i=0;i<mazeR;i++)
			for(int j=0;j<mazeC;j++)
			{
				tiles[i][j]=new Tile(this,SWT.NONE);
				tiles[i][j].setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				Image temp = setImg(m,i,j);
				tiles[i][j].setImage(temp);
				tiles[i][j].setBeforeImage(temp);
			}
    	tiles[0][0].setFirstTile(true);
		layout();
	}
	public void setHint(int x,int y)
	{
		if((hintI == -1) & (hintJ == -1))
		{
			hintI = x;
			hintJ =y;	
		}
		else
		{
			tiles[hintI][hintJ].removeHint();
			hintI = x;
			hintJ =y;	
		}
		if(tiles[x][y].isCircle())
		{
			tiles[x][y].removeCircle();
		}
		tiles[x][y].setHint();
	}
	public void setBoatPosition(int i, int j) 
	{
		tiles[boatI][boatJ].setBoatImage(null);
		tiles[boatI][boatJ].redraw();
		
		if(tiles[i][j].isCircle())
		{
			System.out.println(i + "," + j + " has circle");
			tiles[i][j].removeCircle();
		}
		if(tiles[i][j].isHint())
		{
			System.out.println(i + "," + j + " has hint");
			tiles[i][j].removeHint();
		}
		tiles[i][j].setBoatImage(b.chooseOption(dir,i,j));
		tiles[i][j].redraw();
		boatI = i;
		boatJ = j;
	}
	
	public boolean canMove(int i,int j,int dir)
	{
		//tiles[0][0].redraw();
		boatI = i;
		boatJ = j;
		System.out.println();
		//0 means top
		if(dir==0)
		{
			if(i==0)
				return false;
			else
			{
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
	public void displaySolution(Solution s)
	{
		ArrayList<Integer> arr = s.SolutionToArray();
		int x=0;
		int y=0;
		int a=getX();
		int b=getY();
		System.out.println("SOLUTION : ");
		s.displaySolution();
		for(int i=3;i<arr.size();i+=2)
		{
			Image arrow;
			y=arr.get(arr.size()-i);
			x=arr.get(arr.size()-i-1);
			System.out.println("A : " + a + " B : "+b);
			System.out.println("X : " + x + " Y : "+y);
			if(x == a+1) //direction is right
			{
				arrow = new Image(null, "resources/arrow-down.png"); 
				tiles[a][b].putArrow(arrow);
				a=x;
			}
			else if(x == a-1) //direction is left
			{
				arrow = new Image(null, "resources/arrow-up.png"); 
				tiles[a][b].putArrow(arrow);
				a=x;
				
			}
			else if(y == b+1) //direction is down
			{
				
				arrow = new Image(null, "resources/arrow-right.png"); 
				tiles[a][b].putArrow(arrow);
				b=y;
			}
			else if(y == b-1) //direction is up
			{
				arrow = new Image(null, "resources/arrow-left.png"); 
				tiles[a][b].putArrow(arrow);
				b=y;
			}
			redraw();
		}
	}

	public void drawPicture(Image i,int dir)
	{
		
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

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}
	public Boat getBoat()
	{
		return b;
	}
}