package view;



import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
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
	int boatI;
	int boatJ;
	Image beforeImage;
	PaintEvent pe;
	int dir;
	Boat b;
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
		//Image image = new Image(getDisplay(), "resources/boat-right.jpg");
		
		//Image scaled = new Image(null, image.getImageData());
		//this.beforeImage = tiles[0][0].getImage();
		//tiles[0][0].setImage(scaled);	
		layout();
	}

	public void setBoatPosition(int i, int j) 
	{
		tiles[boatI][boatJ].setBoatImage(null);
		tiles[boatI][boatJ].redraw();
		tiles[i][j].setBoatImage(b.chooseOption(dir,i,j));
		tiles[i][j].redraw();
		boatI = i;
		boatJ = j;
	}
		
	public boolean canMove(int i,int j,int dir)
	{
		tiles[0][0].redraw();
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
		for(int i=0;i<arr.size();i+=2)
		{
			x=arr.get(i);
			y=arr.get(i+1);
			tiles[x][y].putCircle();
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
