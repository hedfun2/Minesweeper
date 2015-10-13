import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Field extends JPanel implements ActionListener {

	Tile[][] tiles;
	final double MINE_PERCENTAGE = 0.2;
	int height;
	int width;
	GameFrame frame;
	boolean firstPress = true;
	
	public Field(int height, int width, GameFrame frame) {
		this.height = height;
		this.width = width;
		this.frame = frame;
		tiles = new Tile[height][width];
		setLayout(new GridLayout(width, height));

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				tiles[row][col] = new Tile(row, col);
				tiles[row][col].addActionListener(this);
				tiles[row][col].addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mousePressed(MouseEvent arg0) {
						if(arg0.isMetaDown()){
							Tile tileClicked = (Tile) arg0.getSource();
							if(tileClicked.getBackground() == Color.red)
								tileClicked.setBackground(null);
							else
								tileClicked.setBackground(Color.red);
							
						}
						
					}
					
					@Override
					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseClicked(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});;
				tiles[row][col].setFont(new Font("Sans Serif", Font.BOLD, 10));
				add(tiles[row][col]);
			}
		}

		int numberOfMines = (int) ((height * width) * MINE_PERCENTAGE) + 1;

		while (numberOfMines > 0) {
			int rdmCol = (int) (Math.random() * width);
			int rdmRow = (int) (Math.random() * height);

			if (tiles[rdmRow][rdmCol].isBomb()) {
				continue;
			} else {
				tiles[rdmRow][rdmCol].setBomb(true);
				//tiles[rdmRow][rdmCol].setBackground(Color.red);
				numberOfMines--;
			}
		}

	}

	public int surroundingMines(Tile tile) {
		ArrayList<Tile> surroundingTiles = getSurroundingTiles(tile);
		int numberOfBombs = 0;

		for (int i = 0; i < surroundingTiles.size(); i++) {
			if (surroundingTiles.get(i).isBomb()) {
				numberOfBombs++;
			}
		}

		return numberOfBombs;
	}

	public ArrayList<Tile> getSurroundingTiles(Tile tile) {
		int tileRow = tile.getRow();
		int tileCol = tile.getCol();

		ArrayList<Tile> surroundingTiles = new ArrayList<Tile>();

		int height = this.height - 1;
		int width = this.width - 1;

		if (tileRow == 0 && tileCol == 0) {
			surroundingTiles.add(tiles[tileRow + 1][tileCol]);
			surroundingTiles.add(tiles[tileRow + 1][tileCol + 1]);
			surroundingTiles.add(tiles[tileRow][tileCol + 1]);
		} else if (tileRow == 0 && tileCol > 0 && tileCol < width) {
			surroundingTiles.add(tiles[tileRow + 1][tileCol]);
			surroundingTiles.add(tiles[tileRow + 1][tileCol + 1]);
			surroundingTiles.add(tiles[tileRow + 1][tileCol - 1]);
			surroundingTiles.add(tiles[tileRow][tileCol - 1]);
			surroundingTiles.add(tiles[tileRow][tileCol + 1]);
		} else if (tileRow == 0 && tileCol == width) {
			surroundingTiles.add(tiles[tileRow + 1][tileCol]);
			surroundingTiles.add(tiles[tileRow + 1][tileCol - 1]);
			surroundingTiles.add(tiles[tileRow][tileCol - 1]);
		} else if (tileRow > 0 && tileRow < height && tileCol == 0) {
			surroundingTiles.add(tiles[tileRow + 1][tileCol]);
			surroundingTiles.add(tiles[tileRow + 1][tileCol + 1]);
			surroundingTiles.add(tiles[tileRow][tileCol + 1]);
			surroundingTiles.add(tiles[tileRow - 1][tileCol]);
			surroundingTiles.add(tiles[tileRow - 1][tileCol + 1]);
		} else if (tileRow > 0 && tileRow < height && tileCol > 0 && tileCol < width) {
			surroundingTiles.add(tiles[tileRow + 1][tileCol]);
			surroundingTiles.add(tiles[tileRow + 1][tileCol + 1]);
			surroundingTiles.add(tiles[tileRow + 1][tileCol - 1]);
			surroundingTiles.add(tiles[tileRow][tileCol - 1]);
			surroundingTiles.add(tiles[tileRow][tileCol + 1]);
			surroundingTiles.add(tiles[tileRow - 1][tileCol]);
			surroundingTiles.add(tiles[tileRow - 1][tileCol + 1]);
			surroundingTiles.add(tiles[tileRow - 1][tileCol - 1]);
		} else if (tileRow > 0 && tileRow < height && tileCol == width) {
			surroundingTiles.add(tiles[tileRow + 1][tileCol]);
			surroundingTiles.add(tiles[tileRow + 1][tileCol - 1]);
			surroundingTiles.add(tiles[tileRow][tileCol - 1]);
			surroundingTiles.add(tiles[tileRow - 1][tileCol]);
			surroundingTiles.add(tiles[tileRow - 1][tileCol - 1]);
		} else if (tileRow == height && tileCol == 0) {
			surroundingTiles.add(tiles[tileRow][tileCol + 1]);
			surroundingTiles.add(tiles[tileRow - 1][tileCol]);
			surroundingTiles.add(tiles[tileRow - 1][tileCol + 1]);
		} else if (tileRow == height && tileCol > 0 && tileCol < width) {
			surroundingTiles.add(tiles[tileRow][tileCol - 1]);
			surroundingTiles.add(tiles[tileRow][tileCol + 1]);
			surroundingTiles.add(tiles[tileRow - 1][tileCol]);
			surroundingTiles.add(tiles[tileRow - 1][tileCol + 1]);
			surroundingTiles.add(tiles[tileRow - 1][tileCol - 1]);
		} else if (tileRow == height && tileCol == width) {
			surroundingTiles.add(tiles[tileRow][tileCol - 1]);
			surroundingTiles.add(tiles[tileRow - 1][tileCol]);
			surroundingTiles.add(tiles[tileRow - 1][tileCol - 1]);
		}

		return surroundingTiles;
	}

	public void clearSurroundingTiles(Tile tile) {
		ArrayList<Tile> surroundingTiles = getSurroundingTiles(tile);
		
		int size = surroundingTiles.size();
		
		for (int i = 0; i < size; i++) {
			if(!surroundingTiles.get(i).isEnabled()){
				surroundingTiles.remove(i);
				i--;
				size--;
			}
		}

		for (int i = 0; i < surroundingTiles.size(); i++) {
			if (surroundingMines(surroundingTiles.get(i)) != 0) {
				surroundingTiles.get(i).setText(surroundingMines(surroundingTiles.get(i)) + "");
				surroundingTiles.get(i).setEnabled(false);
			} else {
				surroundingTiles.get(i).setEnabled(false);
				clearSurroundingTiles(surroundingTiles.get(i));
			}
		}
	}

	public void gameOver(boolean won) {
		if (won) {
			JOptionPane.showMessageDialog(this, "You Won!");
		} else {
			JOptionPane.showMessageDialog(this, "You Lose");
		}

		int again = JOptionPane.showConfirmDialog(this, "Play Again?", "Game Over", JOptionPane.YES_NO_OPTION);
		if (again == 0) {
			new IntroFrame();
			frame.dispose();
		} else {
			System.exit(0);
		}
	}

	public boolean isWinner() {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (tiles[row][col].isEnabled() && !tiles[row][col].isBomb()) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Tile tilePressed = (Tile) e.getSource();
		
		if(firstPress && tilePressed.isBomb()){
			tilePressed.setBomb(false);
		}
		firstPress = false;
		if (tilePressed.isBomb()) {
			gameOver(false);
			return;
		}

		tilePressed.setEnabled(false);
		int surroundingMines = surroundingMines(tilePressed);

		if (surroundingMines == 0) {
			clearSurroundingTiles(tilePressed);
		} else {
			tilePressed.setText(surroundingMines + "");
		}

		if (isWinner())
			gameOver(true);

	}
}
