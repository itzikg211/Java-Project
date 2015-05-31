package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

public class Boat 
{
	int i,j;
	Image boatImg;

	public void paint(PaintEvent e,int i,int j,int dir)
	{
		e.gc.setForeground(new Color(null,0,255,0));
		e.gc.setBackground(new Color(null,0,255,0));
		//0 means top
		//e.gc.fillOval(i, j, x, y);
		if(boatImg!=null)
		{
			e.gc.drawImage(boatImg, 0, 0, boatImg.getImageData().width,boatImg.getImageData().height,0,0,(int)(e.height * 0.7), (int)(e.width * 0.7));
		}	
	}
	public Image getImage() 
	{
		return boatImg;
	}
	public void setImage(Image image) 
	{
		this.boatImg = image;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}

}