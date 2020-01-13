import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

public class JButtonActionListener extends PaintActionListener {

	private JButton button;
	private final Canvas canvas;
	
	public JButtonActionListener(JButton btnColor, Canvas canvas) {
		this.button = btnColor;
		this.canvas = canvas;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("btnColor.getText():"+button.getText());
		if(button.getText().equals("前景色")) {
			Color sel = JColorChooser.showDialog(null, "Select Color", Color.BLACK);
			Canvas.setFgColor(sel);
			button.setBackground(sel);
		} else if(button.getText().equals("背景色")) {
			Color sel = JColorChooser.showDialog(null, "Select Color", Color.WHITE);
			Canvas.setBgColor(sel);
			button.setBackground(sel);
			canvas.setNewBg();
		} else {
			int checkFill = JOptionPane.showConfirmDialog(null, "是否要" + button.getText(), "訊息",
					JOptionPane.YES_NO_OPTION);
			if (checkFill == JOptionPane.YES_OPTION) {
				canvas.clearCanvas();
			}
		}
	}

}

