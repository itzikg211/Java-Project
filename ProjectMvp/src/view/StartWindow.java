package view;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import model.MyModel;

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
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import presenter.Presenter;
import presenter.Properties;
import presenter.Properties.WayOfDisplay;
import algorithms.demo.MazeSearch;
import algorithms.mazeGenerators.Maze;
import algorithms.search.BFS;
import algorithms.search.Solution;

public class StartWindow extends BasicWindow implements View
{
	private Properties properties;
	int numR = 0;
	int numC = 0;
	HashMap<String, Command> commands = new HashMap<String, Command>();
	Command command;
	Maze myMaze;
	Solution sol;
	Solution sol2;
	Boat b;
	public StartWindow(String title, int width, int height) 
	{
		super(title, width, height);
		properties = new Properties();
		//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter writer = new PrintWriter(System.out);
	}

	@Override
	public void start() 
	{
		setChanged();
		notifyObservers("start");
		
	}

	@Override
	public Command getUserCommand() 
	{
		return command;
	}

	@Override
	public void displayMaze(Maze m) 
	{
		//need to complete TODO
		
	}

	@Override
	public void displaySolution(Solution s) 
	{
		//need to complete TODO
		
	}
	
	public void setChangedFunc()
	{
		setChanged();
	}

	@Override
	public void setCommands(HashMap<String, Command> commands2) 
	{
		this.commands = commands2;
		
	}

	@Override
	public void printMessage(String str) 
	{
		MessageBox mb = new MessageBox(shell,0);
		mb.setMessage(str);
		mb.setText("Message");
		mb.open();
		
	}

	@Override
	void initWidgets() 
	{
		////All the widgets
		shell.setLayout(new GridLayout(2,false));
		
	    // Create the bar menu
	    Menu menuBar = new Menu(shell, SWT.BAR);

	    // Create the File item's dropdown menu
	    Menu fileMenu = new Menu(menuBar);
	    Menu HelpMenu = new Menu(menuBar);
	    // Create all the items in the bar menu
	    MenuItem fileItem = new MenuItem(menuBar, SWT.CASCADE);
	    fileItem.setText("File");
	    fileItem.setMenu(fileMenu);
	    MenuItem HelpItem = new MenuItem(menuBar, SWT.CASCADE);
	    HelpItem.setText("Help");
	    HelpItem.setMenu(HelpMenu);

	    // Create all the items in the File dropdown menu
	    MenuItem setPropertiesItem = new MenuItem(fileMenu, SWT.NONE);
	    setPropertiesItem.setText("Set Properties");
	    MenuItem exitItem = new MenuItem(fileMenu, SWT.NONE);
	    exitItem.setText("Exit");
	    MenuItem rules = new MenuItem(HelpMenu, SWT.NONE);
	    rules.setText("Explaining the rules");
	    MenuItem web = new MenuItem(HelpMenu, SWT.NONE);
	    web.setText("Search in the web");
	    shell.setMenuBar(menuBar);
		Label numOfRows = new Label(shell,SWT.NONE);
		numOfRows.setText("Choose the number of rows");
		numOfRows.setLayoutData(new GridData(SWT.FILL,SWT.FILL, false,false,1,1));
		Board maze=new Board(shell, SWT.BORDER);
		maze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,9));
		
		Combo rows = new Combo(shell, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);
		rows.setLayoutData(new GridData(SWT.FILL,SWT.FILL, false,false,1,1));
		for(int i=2;i<36;i++)
		{
			rows.add(i + " rows");
		}
		Label numOfCols = new Label(shell, SWT.NONE);
		numOfCols.setText("Choose the number of columns");
		numOfCols.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false,false,1,1));
		Combo cols = new Combo(shell, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);
		cols.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false,false,1,1));
		for(int i=2;i<36;i++)
		{
			cols.add(i + " columns");
		}
		Label name = new Label(shell, SWT.NONE);
		name.setText("Insert the name of the maze:");
		name.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false,false,1,1));
		Text t = new Text(shell, SWT.BORDER);
		t.setLayoutData(new GridData(SWT.FILL,SWT.TOP, false,false,1,1));
		t.setText("");
		Button a=new Button(shell, SWT.PUSH);
		a.setText("Generate the maze");
		a.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false,false,1,1));
		Button hint=new Button(shell, SWT.PUSH);
		hint.setText("Give me a hint ✆");
		hint.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false,false,1,1));
		Button solve=new Button(shell, SWT.PUSH);
		solve.setText("Solve the maze ☺");
		solve.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false,false,1,1));
		
		
		////All the listeners
		
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
				//Setting up the properties before lunching the game
				XMLEncoder xmle = null;
				try 
				{
					xmle = new XMLEncoder(new FileOutputStream("src/properties.xml"));
					xmle.writeObject(properties);
				} 
				catch (FileNotFoundException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				xmle.flush();
				xmle.close();
				if(numR != 0 && numC != 0)
				{
					
					String str = t.getText();
					if(str.equals(""))
					{
						MessageBox m2 = new MessageBox(shell);
						m2.setText("BAD INPUT");
						m2.setMessage("You didnt input maze's name");
						m2.open();
					}
					else
					{
						/////check if the maze name is already inside the database
						boolean flag = true;
						String [] names = null;
							BufferedReader reader = null;
							try {
								reader = new BufferedReader(new FileReader("names.txt"));
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							String line;
							try 
							{
								while ((line = reader.readLine()) != null)
								{
									names = line.split("#");
								}
							} 
							catch (IOException ee) 
							{
								// TODO Auto-generated catch block
								ee.printStackTrace();
							}
							
							
						if(names.length==0)
						{
							for(String s: names)
							{
								if(s.equals(t.getText()))
								{
									flag = false;
								}	
							}
							if(flag == false)
							{
								MessageBox mb = new MessageBox(shell,SWT.ICON_ERROR);
								mb.setText("Error");
								mb.setMessage("Error! the name is already exists in the database");
								mb.open();
							}
						}
						if(flag == true)
						{
							maze.setX(0);
							maze.setY(0);
							//myMaze = new DFSMazeGenerator().generateMaze(numR, numC);
							String send = "generate maze ";
							send = send + str;
							send = send + " " + numR + " " + numC ;
							setChanged();
							notifyObservers(send);
							if(myMaze!=null)
							{
								maze.displayMaze(myMaze);
								//maze.printBoat();
								maze.forceFocus();
							}	
						}
						
					}
					
					
					/*maze.displayMaze(new DFSMazeGenerator().generateMaze(numR, numC));
					maze.forceFocus();*/
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
			public void widgetSelected(SelectionEvent arg0)
			{
				String str = "hint " + maze.getX() + " " + maze.getY();
				System.out.println("hint str : " + str);
				setChanged();
				notifyObservers(str);
				//put the relevant string representing the current state here
				if(sol2!=null)
				{
					sol2.displaySolution();
					int size = sol2.getList().size();
					int j = sol2.SolutionToArray().get(size*2-3);
					int i = sol2.SolutionToArray().get(size*2-4);
					maze.setHint(i, j);					
					maze.forceFocus();
				}
				else
					System.out.println("The hint solution is null");

				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		solve.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				/////
				System.out.println("solving the maze " + t.getText());
				String send = "gui solve maze ";
				send += t.getText();
				setChanged();
				notifyObservers(send);
				if(sol==null)
				{
					System.out.println("The solution is null");
				}
				else
				{
					System.out.println("The solution is NOT null");
					maze.displaySolution(sol);
					maze.forceFocus();
				}
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		MessageBox m = new MessageBox(shell);
		m.setText("You finished");
		maze.addKeyListener(new KeyListener(){
			Boat b = new Boat();
			@Override
			public void keyPressed(KeyEvent e) {
				
				System.out.println("Place : "+maze.getX()+","+maze.getY());
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
						maze.setDir(0);
						maze.setBoatPosition(maze.getX()-1,maze.getY());						
					}
					else
						System.out.println("CAN NOT MOVE");
				}
					 
				
			if(e.keyCode == SWT.ARROW_DOWN)
			{
				System.out.println("down");
				if(maze.canMove(maze.getX(),maze.getY(), 2))
				{
					System.out.println("CAN MOVE");
					maze.setDir(2);
					maze.setBoatPosition(maze.getX()+1,maze.getY());
				}
				else
					System.out.println("CAN NOT MOVE");
			}
			if(e.keyCode == SWT.ARROW_LEFT)
			{
				System.out.println("left");
				if(maze.canMove(maze.getX(),maze.getY(), 3))
				{
					System.out.println("CAN MOVE");
					maze.setDir(3);
					maze.setBoatPosition(maze.getX(),maze.getY()-1);
				}
				else
					System.out.println("CAN NOT MOVE");
			}
			if(e.keyCode == SWT.ARROW_RIGHT)
			{
				System.out.println("right");
				if(maze.canMove(maze.getX(),maze.getY(), 1))
				{
					System.out.println("CAN MOVE");
					maze.setDir(1);
					maze.setBoatPosition(maze.getX(),maze.getY()+1);
				}
				else
					System.out.println("CAN NOT MOVE");
				
			}
			
			
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(maze.getX()==maze.mazeR-1 & maze.getY()==maze.mazeC-1)
				{
					File file = new File("resources/music/winnerMusic.wav");
					AudioInputStream stream;
					Clip clip;
					try 
					{
					    stream = AudioSystem.getAudioInputStream(file);
					    clip = AudioSystem.getClip();
					    clip.open(stream);
					    clip.start();
					}
					catch (Exception ex) 
					{
					    ex.printStackTrace();
					}
					System.out.println("FINISHED!");
					m.setMessage("Congrats! you finished the maze!");
					m.open();
				}
			}
			
		});
			
		exitItem.addSelectionListener(new SelectionListener() 
		{
			
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				int style = SWT.ICON_QUESTION |SWT.YES | SWT.NO;
				MessageBox mb = new MessageBox(shell,style);
				mb.setMessage("Exit the game ?");
				mb.setText("Confirm Exit");
				int rc = mb.open();
				switch(rc)
				{
				case SWT.YES:
					display.dispose();				
				break;
				case SWT.NO:
					break;
				}
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) 
			{
				// TODO Auto-generated method stub
				
			}
		});

		setPropertiesItem.addSelectionListener(new SelectionListener() 
		{
			
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				FileDialog fd=new FileDialog(shell,SWT.OPEN);
				fd.setText("open");
				fd.setFilterPath("");
				String[] filterExt = { "*.xml" };
				fd.setFilterExtensions(filterExt);
				String fileName = fd.open();
				XMLDecoder d = null;
				try 
				{
					d = new XMLDecoder(new FileInputStream(fileName));
					properties=(Properties)d.readObject();
				} 
				catch (FileNotFoundException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				d.close();
				
				//we check here if we need to change the way of display
				if(properties.getView() == WayOfDisplay.ECLIPSE_CONSOLE)
				{
					display.dispose();
					MyModel m = new MyModel(properties);
					MyView v = new MyView();
					Presenter p = new Presenter(m,v);
					m.addObserver(p);
					v.addObserver(p);
					v.start();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) 
			{
				// TODO Auto-generated method stub
			}
		});
		web.addSelectionListener(new SelectionListener() 
		{
			
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				org.eclipse.swt.program.Program.launch("https://www.google.co.il/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=solve%20a%20maze");
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) 
			{
				// TODO Auto-generated method stub
				
			}
		});
		rules.addSelectionListener(new SelectionListener() 
		{
			
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION);
			    messageBox.setMessage("Move the Boat using the arrows keys or by dragging the boat.\nYou may set the properties as you like by loading an xml file. Enjoy!");
			    messageBox.setText("Information");
			    messageBox.open();

			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	public Command getCommand()
	{
		return command;
	}
	
	public void setCommand(Command command) {
		this.command = command;
	}

	@Override
	public void setGuiMaze(Maze m) 
	{
		this.myMaze = m;
		
	}

	@Override
	public void setSolution(Solution s) 
	{
		System.out.println("Sets the maze");
		sol = s;
		
	}

	@Override
	public void setStartSolution(Solution sol) 
	{
		sol2=sol;		
	}

	
}
