package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class Tile extends Canvas
{
	Image tileImg;
	Image boatImg;
	Boat b;
	public Tile(Composite parent, int style) {
		super(parent, style);
		b =  new Boat(0,0);
		addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) 
			{
					int width=getSize().x;
					int height=getSize().y;
			        ImageData data = tileImg.getImageData();
			        e.gc.drawImage(tileImg,0,0,data.width,data.height,0,0,width,height);
			        if(b.image!=null)
			        {
			        	//e.gc.drawOval(0, 0, width, height);
			        	e.gc.drawImage(boatImg, 0, 0, boatImg.getImageData().width,boatImg.getImageData().height,0,0,(int)(getSize().x * 0.7), (int)(getSize().y * 0.7));
			        }
			}
		});
		
	}
	public void setImage(Image image)
	{
		if(this.tileImg!=null)
			this.tileImg.dispose();
		this.tileImg=image;
		redraw();
	}
	public void setBoatImg(Image img)
	{
		if(this.boatImg!=null)
			this.boatImg.dispose();
		this.boatImg=img;
		redraw();
	}
}
