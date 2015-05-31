package view;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.IOException;

import model.MyModel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

import presenter.Presenter;
import presenter.Properties;

public class GUIStart extends BasicWindow
{ 
	
	public GUIStart(String title, int width, int height)
	{
		super(title, width, height);
	}

	@Override
	void initWidgets() 
	{
		
		////All the widgets
		Button startGame = new Button(shell, SWT.PUSH);
		startGame.setText("Start the game");
		startGame.setLayoutData(new GridData(SWT.NONE,SWT.NONE,false,false,1,1));
		shell.setLayout(new GridLayout(2,false));
		Image myImage = new Image( display, "resources/mainPic.png" );
		Label myLabel = new Label( shell, SWT.NONE );
		myLabel.setImage( myImage );
		myLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL | SWT.TOP, true,true,1,1));
		myLabel.setSize((int)(shell.getSize().x-50),(int)(shell.getSize().y+30));
		shell.setSize(myLabel.getSize().x, myLabel.getSize().y);

		
		////All the listeners
		startGame.addSelectionListener(new SelectionListener() 
		{
			
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				display.dispose();
				MyModel m=new MyModel(new Properties());
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
		
		/*view.addSelectionListener(new SelectionListener() 
		{
			
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				if(view.getText().equals("GUI view"))
					isGUI = true;
				if(view.getText().equals("Eclipse console view"))
					isGUI = false;
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) 
			{
				// TODO Auto-generated method stub
				
			}
		});*/
		
		
	}

}
