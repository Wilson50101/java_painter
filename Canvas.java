
/*
 * 資管三A 104403201 郭哲宇
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Canvas extends JPanel {

	PaintShape paintShape = new PaintShape();

	// Status bar
	public final JLabel statusBar;
	private String message;

	// Canvas
	ArrayList<Shape> shapes;
	ArrayList<Color> shapeFill;
	ArrayList<Integer> shapeStroke;
	ArrayList<Integer> whichShape;
	ArrayList<Boolean> shapeIsFill;
	Point drawStart, drawEnd;

	// Default Color
	public static Color fgColor = Color.BLACK;
	public static Color bgColor = Color.WHITE;

	// Which Brush -> brushes = { "筆刷", "直線", "橢圓形", "矩形", "圓角矩形" };
	private int shapeIndex = 0;

	// Shape size
	private final int SMALL = 2;
	private final int MEDIUM = 4;
	private final int LARGE = 8;
	private int shapeSize = SMALL;
	
	// isFill
	private boolean fill = false;

	public Canvas() {
		shapeFill = new ArrayList<Color>();
		shapeStroke = new ArrayList<Integer>();
		shapes = new ArrayList<Shape>();
		shapeIsFill = new ArrayList<Boolean>();
		whichShape = new ArrayList<Integer>();
		
		setBackground(bgColor);

		message = "游標位置：";
		statusBar = new JLabel(message);

		MouseHandler handler = new MouseHandler();
		addMouseListener(handler);
		addMouseMotionListener(handler);
		addMouseWheelListener(handler);
	}

	public void clearCanvas() {
		shapeFill = new ArrayList<Color>();
		shapeStroke = new ArrayList<Integer>();
		shapes = new ArrayList<Shape>();
		shapeIsFill = new ArrayList<Boolean>();
		whichShape = new ArrayList<Integer>();
		repaint();
	}
	
	public void setNewBg() {
		setBackground(getBgColor());
	}

	// Override extends method
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // clears drawing area
		// Explicitly Type Conversion
		Graphics2D graphics2d = (Graphics2D) g;

		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		
		Iterator<Color> fillCounter = shapeFill.iterator();
		Iterator<Integer> strokeCounter = shapeStroke.iterator();
		Iterator<Boolean> isFill = shapeIsFill.iterator();
		Iterator<Integer> whichCounter = whichShape.iterator();
		
		for (Shape shape : shapes) {
			// dashed
			boolean fillTemp = isFill.next();
			int stroke = strokeCounter.next();
			if (whichCounter.next() == 1 && !fillTemp) {
				graphics2d.setStroke(new BasicStroke(stroke, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{stroke}, 0));
			} else {
				graphics2d.setStroke(new BasicStroke(stroke));
			}
			
			graphics2d.setPaint(fillCounter.next());
			graphics2d.draw(shape);
			if (fillTemp == true) {
				graphics2d.fill(shape);
			}

		}
	}

	// Implements MouseAdapter
	private class MouseHandler extends MouseAdapter {
		@Override
		public void mouseExited(MouseEvent e) {
			message = "游標在外面";
			statusBar.setText(message);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			message = String.format("游標位置： (%d,%d)", e.getX(), e.getY());
			statusBar.setText(message);
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			message = String.format("游標位置： (%d,%d)", e.getX(), e.getY());
			statusBar.setText(message);
			
			if (getShapeIndex() == 0) {
				Shape shapeToDraw = null;
				int x = e.getX(), y = e.getY();
				
				shapeToDraw = paintShape.drawBrush(x, y, getShapeSize(), getShapeSize());
				
				shapes.add(shapeToDraw);
				shapeFill.add(getFgColor());
				shapeStroke.add(getShapeSize());
				shapeIsFill.add(fill);
				whichShape.add(getShapeIndex());
				
			}
			
			drawEnd = new Point(e.getX(), e.getY());
			repaint(); // repaint JFrame
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (getShapeIndex() != 0) {
				drawStart = new Point(e.getX(), e.getY());
	        	drawEnd = drawStart;
	            repaint();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (getShapeIndex() != 0) {
				Shape shapeToDraw = null;
				
				if (getShapeIndex() == 1) {
					shapeToDraw = paintShape.drawLine(drawStart.x, drawStart.y, e.getX(), e.getY());
				} else if (getShapeIndex() == 2) {
					shapeToDraw = paintShape.drawElli(drawStart.x, drawStart.y, e.getX(), e.getY());
				} else if (getShapeIndex() == 3) {
					shapeToDraw = paintShape.drawRect(drawStart.x, drawStart.y, e.getX(), e.getY());
				} else {
					shapeToDraw = paintShape.drawRoundRect(drawStart.x, drawStart.y, e.getX(), e.getY());
				}
				
				shapes.add(shapeToDraw);
				shapeFill.add(getFgColor());
				shapeStroke.add(getShapeSize());
				shapeIsFill.add(fill);
				whichShape.add(getShapeIndex());
				
				drawEnd = new Point(e.getX(), e.getY());
                repaint();
			}
		}
	}

	public int getShapeIndex() {
		return shapeIndex;
	}

	public void setShapeIndex(int shapeIndex) {
		this.shapeIndex = shapeIndex;
	}

	public boolean getFill() {
		return fill;
	}

	public void setFill(boolean fill) {
		this.fill = fill;
	}
	
	public int getShapeSize() {
		return shapeSize;
	}
	
	public void setShapeSize(String shapeSize) {
		switch (shapeSize) {
		case "小":
			this.shapeSize = SMALL;
			break;
		case "中":
			this.shapeSize = MEDIUM;
			break;
		case "大":
			this.shapeSize = LARGE;
			break;
		default:
			this.shapeSize = SMALL;
			break;
		}
	}
	
	public static Color getFgColor() {
		return fgColor;
	}
	
	public static void setFgColor(Color fgColor) {
		Canvas.fgColor = fgColor;
	}
	
	public static Color getBgColor() {
		return bgColor;
	}
	
	public static void setBgColor(Color bgColor) {
		Canvas.bgColor = bgColor;
	}
}