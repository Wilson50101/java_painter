
/*
 * 資管三A 104403201 郭哲宇
 */
import javax.swing.JFrame;

public class Paint {
	public static void main(String[] args) {

		PaintFrame app = new PaintFrame("小畫家");
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(800, 600);
		app.setVisible(true);
	}
	
}