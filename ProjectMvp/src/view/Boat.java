package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

public class Boat 
{
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

	public void paint(PaintEvent e,int i,int j,int dir)
	{
		x=38;
		y=38;
		e.gc.setForeground(new Color(null,0,255,0));
		e.gc.setBackground(new Color(null,0,255,0));
		int x = i*w + this.x;
		int y = j*h + this.y;
		//0 means top
		e.gc.fillOval(i, j, x, y);
		//e.gc.drawImage(im,x,y);
	}
}