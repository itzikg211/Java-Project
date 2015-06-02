package view;

import java.util.Arrays;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class Tile extends Canvas
{
	Image beforeImage;
	Image tileImg;
	int a,b,temp1,temp2;
	Boat boat;
	Image boatImg;
	boolean firstTile;
	boolean inCircle = false;
	boolean circle = false;
	public Tile(Composite parent, int style) {
		super(parent, style);
		addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) 
			{
					int width=getSize().x;
					int height=getSize().y;
			        ImageData data = tileImg.getImageData();
			        e.gc.drawImage(tileImg,0,0,data.width,data.height,temp1,temp2,width,height);
			        if(boatImg!=null)
			        {
			        	ImageData data1 = boatImg.getImageData();
						e.gc.drawImage(boatImg,0,0,data1.width,data1.height,(int)(width/8),(int)(height/8),(int)(width*0.7),(int)(height*0.7));//(int)(Math.min(e.width,e.height) * 0.7), (int)(Math.min(e.width,e.height) * 0.7));
			        }
			        if(firstTile)
			        {
			        	setBoatImage(new Image(null, "resources/boat-right.jpg"));
			        	ImageData data1 = new Image(null, "resources/boat-right.jpg").getImageData();
			        	e.gc.drawImage(new Image(null, "resources/boat-right.jpg"),0,0,data1.width,data1.height,(int)(width/8),(int)(height/8),(int)(width*0.7),(int)(height*0.7));
			        }
			        if(circle == true)
			        {
			        	e.gc.setForeground(new Color(null,255,200,0));
						//e.gc.setBackground(new Color(null,255,255,0));
						e.gc.fillOval(width/3, height/3, width/3, height/3);
			        }
			        
			        
			}
		});
		
	}
	public void setImage(Image image)
	{
		/*if(this.tileImg!=null)
			this.tileImg.dispose();*/
		temp1=0;
		temp2=0;
		a=0;
		b=0;
		
		this.tileImg=image;
		redraw();
	}
	public void setBoatImage(Image image)
	{
		firstTile = false;
		this.boatImg = image;
		
	}
	public void setBeforeImage(Image image)
	{
		this.beforeImage=image;
	}
	public Image getImage()
	{
		return this.tileImg;
	}
	public void putCircle()
	{
		circle = true;
		redraw();
	}
	public void removeCircle()
	{
		inCircle=true;
		circle=false;
		redraw();
	}
	public boolean isCircle()
	{
		return circle;
	}
	public boolean isFirstTile() {
		return firstTile;
	}
	public void setFirstTile(boolean firstTile) {
		this.firstTile = firstTile;
	}
}
