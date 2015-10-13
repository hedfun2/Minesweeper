import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Tile extends JButton{
	private boolean isBomb;
	private int row;
	private int col;
	
	
	public Tile(int row, int col){
		this.row = row;
		this.col = col;
		this.isBomb = false;
	}
	
	public Tile(int row, int col, boolean isBomb) {
		this.row = row;
		this.col = col;
		this.isBomb = isBomb;
	}
	
	public void setBomb(boolean isBomb){
		this.isBomb = isBomb;
	}
	
	public boolean isBomb(){
		return isBomb;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
}
