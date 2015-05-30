package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.MessageBox;

import presenter.Properties;

public class GUIStart extends BasicWindow
{
	private Properties properties; 
	public GUIStart(String title, int width, int height)
	{
		super(title, width, height);
		
	}

	@Override
	void initWidgets() 
	{
		shell.setLayout(new GridLayout(2,false));
		Button setPro = new Button(shell, SWT.PUSH);
		setPro.setText("Set Properties");
		setPro.setLayoutData(new GridData(SWT.FILL,SWT.NONE,false,false,1,1));
		Canvas img = new Canvas(shell, SWT.BORDER);
		img.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,3));
		img.setBackgroundImage(new Image(display, new ImageData("resources/state 4.png")));
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
				display.dispose();
				StartWindow win=new StartWindow("Row Out Maze", 1000, 800);
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
