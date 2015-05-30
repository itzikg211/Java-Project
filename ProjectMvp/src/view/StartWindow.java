package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import presenter.Presenter;
import algorithms.mazeGenerators.DFSMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.search.Solution;

public class StartWindow extends BasicWindow implements View
{
	Presenter p;
	int numR = 0;
	int numC = 0;
	Maze myMaze;
	Solution sol;
	public StartWindow(String title, int width, int height) 
	{
		super(title, width, height);
		//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter writer = new PrintWriter(System.out);

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Command getUserCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void displayMaze(Maze m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySolution(Solution s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCommands(HashMap<String, Command> commands2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printMessage(String str) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void initWidgets() 
	{
		
		shell.setLayout(new GridLayout(2,false));
		
		Label numOfRows = new Label(shell,SWT.NONE);
		numOfRows.setText("Choose the number of rows");
		numOfRows.setLayoutData(new GridData(SWT.NONE,SWT.NONE, false,false,1,1));
		Board maze=new Board(shell, SWT.BORDER);
		maze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,7));
		maze.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseScrolled(MouseEvent e) 
			{
				if((e.stateMask & SWT.CTRL) != 0)
				{
					if(e.count > 0)
					{
						if((int)((maze.getSize().x)*1.1)<= shell.getSize().x && (int)((maze.getSize().y)*1.1)<= shell.getSize().y)
						{
							maze.setSize((int)((maze.getSize().x)*1.1),(int)((maze.getSize().y)*1.1));
							maze.redraw();
						}
					}
					else 
					{
						if((maze.getSize().x)>=0 && (maze.getSize().y)>=0)
						{
							maze.setSize((int)((maze.getSize().x)/1.1),(int)((maze.getSize().y)/1.1));
							maze.redraw();
						}
					}
						
				}
				
			}
		});
		Combo rows = new Combo(shell, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);
		rows.setLayoutData(new GridData(SWT.NONE,SWT.NONE, false,false,1,1));
		for(int i=2;i<36;i++)
		{
			rows.add(i + " rows");
		}
		Label numOfCols = new Label(shell, SWT.COLOR_BLUE);
		numOfCols.setText("Choose the number of columns");
		numOfCols.setLayoutData(new GridData(SWT.NONE,SWT.NONE, false,false,1,1));
		Combo cols = new Combo(shell, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);
		cols.setLayoutData(new GridData(SWT.NONE,SWT.NONE, false,false,1,1));
		for(int i=2;i<36;i++)
		{
			cols.add(i + " columns");
		}
		Button a=new Button(shell, SWT.PUSH);
		a.setText("Generate the maze");
		a.setLayoutData(new GridData(SWT.NONE,SWT.NONE, false,false,1,1));
		Button hint=new Button(shell, SWT.PUSH);
		hint.setText("Give me a hint ✆");
		hint.setLayoutData(new GridData(SWT.NONE,SWT.NONE, false,false,1,1));
		Button solve=new Button(shell, SWT.PUSH);
		solve.setText("Solve the maze ☺");
		solve.setLayoutData(new GridData(SWT.NONE,SWT.NONE, false,false,1,1));
		cols.addSelectionListener(new SelectionListener() 
		{
			
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				for(int i=2;i<36;i++)
				{
					StringBuilder str= new StringBuilder();
					str.append(i);
					str.append(" columns");
					if(cols.getText().equals(str.toString()))
						numC = i;
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		rows.addSelectionListener(new SelectionListener() 
		{
			
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				for(int i=2;i<36;i++)
				{
					StringBuilder str= new StringBuilder();
					str.append(i);
					str.append(" rows");
					if(rows.getText().equals(str.toString()))
						numR = i;
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		a.addSelectionListener(new SelectionListener() 
		{
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				if(numR != 0 && numC != 0)
				{
					//setChanged();
					//notifyObservers("generate maze");
					myMaze = new DFSMazeGenerator().generateMaze(numR, numC);
					maze.displayMaze(myMaze);
					maze.forceFocus();					
				}
				else
				{
					MessageBox mb = new MessageBox(shell,SWT.ICON_ERROR);
					mb.setText("Error");
					if(numR==0)
						mb.setMessage("You didn't choose the number of rows");
					else
						mb.setMessage("You didn't choose the number of columns");
					mb.open();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		hint.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		solve.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		maze.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == SWT.ESC)
				{
					display.dispose ();
					
				}
				if(e.keyCode == SWT.ARROW_UP)
				{
					System.out.println("UP");
					if(maze.canMove(maze.getX(),maze.getY(), 0))
					{
						System.out.println("CAN MOVE");
						maze.setPos(maze.getX()-1,maze.getY());
						//fucntion to set the image
					}
				}
					 
				/*else
					System.out.println("CAN NOT MOVE");*/
			if(e.keyCode == SWT.ARROW_DOWN)
			{
				System.out.println("down");
				if(maze.canMove(maze.getX(),maze.getY(), 2))
				{
					System.out.println("CAN MOVE");
					maze.setPos(maze.getX()+1,maze.getY());
					//fucntion to set the image
				}
				/*else
					System.out.println("CAN NOT MOVE");*/
			}
			if(e.keyCode == SWT.ARROW_LEFT)
			{
				System.out.println("left");
				if(maze.canMove(maze.getX(),maze.getY(), 3))
				{
					System.out.println("CAN MOVE");
					maze.setPos(maze.getX(),maze.getY()-1);
					//fucntion to set the image
				}
				/*else
					System.out.println("CAN NOT MOVE");*/
			}
			if(e.keyCode == SWT.ARROW_RIGHT)
			{
				System.out.println("right");
				if(maze.canMove(maze.getX(),maze.getY(), 1))
				{
					System.out.println("CAN MOVE");
					maze.setPos(maze.getX(),maze.getY()+1);
					//fucntion to set the image
				}
				/*else
					System.out.println("CAN NOT MOVE");*/
				
			}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		

		
	}

	
}
