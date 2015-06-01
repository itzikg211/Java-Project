package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;

public class Boat{
	int x,y;
	int w,h;
	Image image;
	
	public Boat(int a,int b)
	{
		this.x=a;
		this.y=b;
		
	}
	public void setSize(int x,int y)
	{
		this.w=x;
		this.h=y;
	}
	public void addX(int a)
	{
		this.x+=a;
	}
	public void addY(int b)
	{
		this.y+=b;
	}

	public void paint(Image im,PaintEvent e,int i,int j,int dir)
	{
		/*x=38;
		y=38;
		e.gc.setForeground(new Color(null,0,255,0));
		e.gc.setBackground(new Color(null,0,255,0));
		int x = j*w + this.x;
		int y = i*h + this.y;
		Image image = new Image(e.display, "resources/boat-right.jpg");
		Image scaled = new Image(null, image.getImageData().scaledTo(100, 100));
		e.gc.drawImage(scaled, 20, 20);*/
		//e.gc.setForeground(new Color(null,255,0,0));
		//e.gc.setBackground(new Color(null,255,0,0));
	}
}