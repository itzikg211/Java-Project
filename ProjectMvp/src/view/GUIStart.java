package view;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import model.MyModel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import presenter.Presenter;
import presenter.Properties;

public class GUIStart extends BasicWindow
{
	private Properties properties; 
	public GUIStart(String title, int width, int height)
	{
		super(title, width, height);
		properties = new Properties();
	}

	@Override
	void initWidgets() 
	{
		shell.setLayout(new GridLayout(2,false));
		Button setPro = new Button(shell, SWT.PUSH);
		setPro.setText("Set Properties");
		setPro.setLayoutData(new GridData(SWT.FILL,SWT.NONE,false,false,1,1));
		//Canvas img = new Canvas(shell,SWT.BORDER);
		//img.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,3));
		//img.setBackgroundImage(new Image(display, new ImageData("resources/state 4.png")));
		///img layout definition
		Image myImage = new Image( display, "resources/mainPic.png" );
		Label myLabel = new Label( shell, SWT.NONE );
		myLabel.setImage( myImage );
		myLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,3));
		myLabel.setSize(shell.getSize().x,shell.getSize().y);
		Button startGame = new Button(shell, SWT.PUSH);
		startGame.setText("Start the game");
		startGame.setLayoutData(new GridData(SWT.NONE,SWT.NONE,false,false,1,1));
		Button exit = new Button(shell, SWT.PUSH);
		exit.setText("Exit");
		exit.setLayoutData(new GridData(SWT.FILL,SWT.BOTTOM,false,false,1,1));
		
		setPro.addSelectionListener(new SelectionListener() 
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
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) 
			{
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		startGame.addSelectionListener(new SelectionListener() 
		{
			
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				//Setting up the properties before lunching the game
				XMLEncoder e = null;
				try 
				{
					e = new XMLEncoder(new FileOutputStream("src/properties.xml"));
					e.writeObject(properties);
				} 
				catch (FileNotFoundException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.flush();
				e.close();
				display.dispose();
				MyModel m=new MyModel(properties);
				StartWindow win=new StartWindow("Boat Maze", 1000, 800);
				Presenter p = new Presenter(m,win);
				m.addObserver(p);
				win.addObserver(p);
				win.start();
				win.run();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) 
			{
				// TODO Auto-generated method stub
				
			}
		});
		
		exit.addSelectionListener(new SelectionListener() 
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
		
		
	}

}
