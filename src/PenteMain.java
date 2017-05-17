import javax.swing.JFrame;


public class PenteMain {
	
	public static final int EMPTY = 0;
	public static final int BLACKSTONE = 1;
	public static final int WHITESTONE = -1;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int boardWidth = 720;
		int boardWidthInSquares = 19;
		
		JFrame F = new JFrame ("Play Pente -- All the Time...");
		
		//for nice exiting:
		F.setDefaultCloseOperation(F.EXIT_ON_CLOSE);
		F.setSize(boardWidth, boardWidth);
		
		
		PenteGameBoard p =new PenteGameBoard(boardWidth, boardWidthInSquares);
		
		F.add(p);
		F.setVisible(true);
		
		
	}
	

}
