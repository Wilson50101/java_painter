import java.awt.geom.Line2D;
import java.awt.*;
public class PaintLine implements PaintBehavior{
	//µe½u
	public Shape paintShape(int x1,int y1,int x2,int y2) 
	{
		/*
		int x = Math.min(x1, x2);
		int y = Math.min(y1, y2);
		
		int w = Math.abs(x2 - x1);
		int h = Math.abs(y2 - y1);
		*/
		return new Line2D.Float(x1, y1, x2, y2);
	}
}
