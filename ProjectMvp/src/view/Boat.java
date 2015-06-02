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
	
	public Image chooseOption(int dir, int i, int j)
	{
		x=0;
		y=0;
		Image image2=null;
		
		if(dir==0)
		{
			x=i+1;
			y=j;
			image2 = new Image(null, "resources/boat-up.jpg");
			//setBoatImg(image2);
			
		}
		if(dir==1)
		{
			x=i;
			y=j-1;
			image2 = new Image(null, "resources/boat-right.jpg");
			//setBoatImg(image2);
		}
		if(dir==2)
		{
			x=i-1;
			y=j;
			image2 = new Image(null, "resources/boat-down.jpg");
			//setBoatImg(image2);
			
		}
		if(dir==3)
		{
			x=i;
			y=j+1;
			image2 = new Image(null, "resources/boat-left.jpg");
			//setBoatImg(image2);
		}
		return image2;
 	}
	
	public Image getBoatImg() {
		return boatImg;
	}

	public void setBoatImg(Image boatImg) {
		this.boatImg = boatImg;
	}
}