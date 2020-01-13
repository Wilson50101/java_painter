import java.awt.geom.Ellipse2D;
import java.awt.*;
public class PaintElli implements PaintBehavior{
	//µe¾ò¶ê
	public Shape paintShape(int x1,int y1,int x2,int y2) 
	{
		int x = Math.min(x1, x2);
		int y = Math.min(y1, y2);
		
		int w = Math.abs(x2 - x1);
		int h = Math.abs(y2 - y1);
		
		return new Ellipse2D.Float(x, y, w, h);
	}
}
