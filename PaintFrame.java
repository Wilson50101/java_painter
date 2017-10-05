
/*
 * 資管三A 104403201 郭哲宇
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PaintFrame extends JFrame {
	// Main Canvas
	private final Canvas canvas;

	/*
	 * Left Panel Tools
	 */
	// Labels x 2
	private final JLabel label1;
	private final JLabel label2;

	// Brushes ComboBox x 1
	private final JComboBox<String> brushBox;
	private static final String[] brushes = { "筆刷", "直線", "橢圓形", "矩形", "圓角矩形" };

	// Radio button x 3
	private JRadioButton small;
	private JRadioButton medium;
	private JRadioButton large;
	private ButtonGroup sizes; // Radio Button Group

	// CheckBox x 1
	private final JCheckBox isFill;

	// Button X 3
	private final JButton fgColor;
	private final JButton bgColor;
	private final JButton empty;
	
	// Total Items Count
	private final int itemCount = 10;
	
	// Left Tool Panel
	private final JPanel toolPanel;

	public PaintFrame(String title) {
		// Main Canvas
		canvas = new Canvas();
		
		// Labels
		label1 = new JLabel("[繪圖工具]");
		label2 = new JLabel("[筆刷大小]");

		// ComboBox
		brushBox = new JComboBox<String>(brushes);
		brushBox.setMaximumRowCount(5);
		brushBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					JOptionPane.showMessageDialog(null, "你選擇了：" + brushes[brushBox.getSelectedIndex()], "訊息",
							JOptionPane.INFORMATION_MESSAGE);
					// Cancel Fill if select brush
					if (brushBox.getSelectedIndex() == 0) {
						canvas.setFill(false);
						isFill.setSelected(false);
						isFill.setEnabled(false);
					} else {
						isFill.setEnabled(true);
					}
					canvas.setShapeIndex(brushBox.getSelectedIndex());
				}
			}
		});

		// Radio Button
		small = new JRadioButton("小", true);
		small.addItemListener(new RadioButtonHanlder("小"));
		medium = new JRadioButton("中", false);
		medium.addItemListener(new RadioButtonHanlder("中"));
		large = new JRadioButton("大", false);
		large.addItemListener(new RadioButtonHanlder("大"));

		sizes = new ButtonGroup();
		sizes.add(small);
		sizes.add(medium);
		sizes.add(large);

		// CheckBox
		isFill = new JCheckBox("填滿");
		isFill.setEnabled(false);
		isFill.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isFill.isSelected() == true) {
					System.out.println("你選擇了：" + isFill.getText());
					canvas.setFill(true);
				} else {
					System.out.println("你取消選擇了：" + isFill.getText());
					canvas.setFill(false);
				}
			}
		});

		// Button
		fgColor = new JButton("前景色");
		fgColor.setBackground(Canvas.fgColor);
		fgColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// JOptionPane.showMessageDialog(null, "你按了：" + fgColor.getText(), "訊息", JOptionPane.INFORMATION_MESSAGE);
				Color sel = JColorChooser.showDialog(null, "Select Color", Color.BLACK);
				Canvas.setFgColor(sel);
				fgColor.setBackground(sel);
				
			}
		});
		bgColor = new JButton("背景色");
		bgColor.setBackground(Canvas.bgColor);
		bgColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// JOptionPane.showMessageDialog(null, "你按了：" + bgColor.getText(), "訊息", JOptionPane.INFORMATION_MESSAGE);
				Color sel = JColorChooser.showDialog(null, "Select Color", Color.WHITE);
				Canvas.setBgColor(sel);
				bgColor.setBackground(sel);
				canvas.setNewBg();
			}
		});
		empty = new JButton("清除畫面");
		empty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Return 0 if select yes
				int checkFill = JOptionPane.showConfirmDialog(null, "是否要" + empty.getText(), "訊息",
						JOptionPane.YES_NO_OPTION);
				if (checkFill == JOptionPane.YES_OPTION) {
					canvas.clearCanvas();
				}
			}
		});

		// Left Panel
		toolPanel = new JPanel();
		toolPanel.setLayout(new GridLayout(itemCount, 1));
		toolPanel.add(label1); // check
		toolPanel.add(brushBox); // check
		toolPanel.add(label2); // check
		toolPanel.add(small); // check
		toolPanel.add(medium); // check
		toolPanel.add(large); // check
		toolPanel.add(isFill); // check
		toolPanel.add(fgColor); // check
		toolPanel.add(bgColor); // check
		toolPanel.add(empty); // check
		
		// Set Main Frame
		setLayout(new BorderLayout());
		add(toolPanel, BorderLayout.WEST);
		add(canvas, BorderLayout.CENTER);
		add(canvas.statusBar, BorderLayout.SOUTH);
		setTitle(title);
	}

	// Handlers

	// Radio Button
	private class RadioButtonHanlder implements ItemListener {

		private String size;

		public RadioButtonHanlder(String size) {
			this.size = size;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				JOptionPane.showMessageDialog(null, "你選擇了：" + size, "訊息", JOptionPane.INFORMATION_MESSAGE);
				canvas.setShapeSize(size);
			}

		}

	}
}