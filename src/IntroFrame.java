import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class IntroFrame extends JFrame implements ActionListener{
	JLabel title = new JLabel("Please Pick A Grid Size");
	JButton done = new JButton("Done");
	String[] widthList = {"Height", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
    String[] heightList = {"Width", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
    JComboBox<String> width = new JComboBox<String>(widthList);
    JComboBox<String> height = new JComboBox<String>(heightList);
	boolean finished = false;
	public IntroFrame(){
        setTitle("Welcome to Minesweeper");
        setSize(300, 150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        setResizable(false);
        
        title.setFont(new Font("Sans Serif", Font.BOLD, 24));
        width.setPreferredSize(new Dimension(125,30));
        height.setPreferredSize(new Dimension(125,30));
        done.setPreferredSize(new Dimension(85,25));
        done.addActionListener(this);
        add(title);
        add(height);
        add(width);
        add(done);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(width.getSelectedItem().equals("Height") || height.getSelectedItem().equals("Width")){
		}else{
			String stringWidth = (String)width.getSelectedItem();
			String stringHeight = (String)height.getSelectedItem();
			int pickWidth = Integer.parseInt(stringWidth);
			int pickHeight = Integer.parseInt(stringHeight);
			finished = true;
			new GameFrame(pickHeight, pickWidth);
			dispose();
		}
		
		
	}
}
