import javax.swing.JFrame;

public class GameFrame extends JFrame{
	
	public GameFrame(int height, int width){
		setTitle("Minesweeper");
        setSize(700, 700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        add(new Field(height, width, this));
	}
	
	public void dispose(){
		super.dispose();
	}
	
}
