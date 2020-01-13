import java.awt.*;
import java.awt.geom.Ellipse2D;
public class PaintBrush implements PaintBehavior{
	//µeµ§¨ê
	public Shape paintShape(int x1,int y1,int x2,int y2) 
	{
		//x2==w,y2==h
		return new Ellipse2D.Float(x1,y1,x2,y2);
	}
}
