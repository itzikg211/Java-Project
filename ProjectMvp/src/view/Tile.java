package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class Tile extends Canvas
{
	Image beforeImage;
	Image tileImg;
	int a,b,temp1,temp2;
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
		temp1=0;
		temp2=0;
		a=0;
		b=0;
		/*if(this.tileImg!=null)
			this.tileImg.dispose();*/
		this.tileImg=image;
		redraw();
	}
	public void setBeforeImage(Image image)
	{
		this.beforeImage=image;
	}
	public Image getImage()
	{
		return this.tileImg;
	}
}
