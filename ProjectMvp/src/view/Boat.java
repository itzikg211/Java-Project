package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class Boat
{
	int x,y;
	int w,h;
	Image boatImg;

	
	public Boat() 
	{
	
	}

	public Image getBoatImg() {
		return boatImg;
	}

	public void setBoatImg(Image boatImg) {
		this.boatImg = boatImg;
	}
}