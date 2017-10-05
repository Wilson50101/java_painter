
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/*
 * 資管三A 104403201 郭哲宇
 */

public class PaintShape {
	
	public Ellipse2D.Float drawBrush (int x1, int y1, int w, int h) {
		return new Ellipse2D.Float(x1, y1, w, h);
	}
	
	public Line2D.Float drawLine(int x1, int y1, int x2, int y2){
		/*
		int x = Math.min(x1, x2);
		int y = Math.min(y1, y2);
		
		int w = Math.abs(x2 - x1);
		int h = Math.abs(y2 - y1);
		*/
		
		return new Line2D.Float(x1, y1, x2, y2);
	}
	public Ellipse2D.Float drawElli(int x1, int y1, int x2, int y2){
		int x = Math.min(x1, x2);
		int y = Math.min(y1, y2);
		
		int w = Math.abs(x2 - x1);
		int h = Math.abs(y2 - y1);
		
		return new Ellipse2D.Float(x, y, w, h);
	}
	
	public Rectangle2D.Float drawRect(int x1, int y1, int x2, int y2){
		int x = Math.min(x1, x2);
		int y = Math.min(y1, y2);
		
		int w = Math.abs(x2 - x1);
		int h = Math.abs(y2 - y1);
		
		return new Rectangle2D.Float(x, y, w, h);
	}
	
	public RoundRectangle2D.Float drawRoundRect(int x1, int y1, int x2, int y2){
		int x = Math.min(x1, x2);
		int y = Math.min(y1, y2);
		
		int w = Math.abs(x2 - x1);
		int h = Math.abs(y2 - y1);
		
		return new RoundRectangle2D.Float(x, y, w, h, 50, 50);
	}
	
	
	

}
