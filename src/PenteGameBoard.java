import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class PenteGameBoard extends JPanel implements MouseListener {

	private int bWidthPixels;
	private int bWidthSquares;
	private int bSquareWidth;
	private int currentTurn = PenteMain.WHITESTONE;
	//Color boardSquareColor = new Color(150,111,52);
	
	//for testing purposes we need 361 squares...
	//Square testSquare;
	private Square [] [] theBoard;
	
	//For counting captures
	private int whiteCaptures = 0;
	private int blackCaptures = 0;
	
	yuchenson computerMoveGenerator = null;
	boolean playAgainstYuchen = false;
	int yuchenStoneColor;
	
	public PenteGameBoard(int bWPixels, int bWSquares){
		
		bWidthPixels = bWPixels -10;
		bWidthSquares = bWSquares;
		//compute the width of the b squares...
		bSquareWidth = (int)(Math.ceil(bWidthPixels/bWidthSquares))+2;
		
		this.setSize(bWidthPixels, bWidthSquares);
		this.setBackground(Color.PINK);
		
		theBoard = new Square[bWSquares][bWSquares];
		//testSquare = new Square(0, 0, bSquareWidth);
		
		//fill the board with squares
		for (int row = 0; row < bWidthSquares; ++row){
		    for (int column = 0; column < bWidthSquares; ++column){
			    theBoard [row][column] = new Square((column*bSquareWidth), 
			    		(row*bSquareWidth), bSquareWidth, row, column);
					}
		}
		
		
		//set the first stone
		theBoard[(int)(bWidthSquares/2)][(int)(bWidthSquares/2)].setState(PenteMain.EMPTY);
		
		String computerAnswer = JOptionPane.showInputDialog("Hi, would you like to play with computer?");
		if(computerAnswer.equals("yes")||
				computerAnswer.equals("Yes")){
			computerMoveGenerator = new yuchenson(this, currentTurn);
			playAgainstYuchen = true;
			yuchenStoneColor = currentTurn;
		}
		
		this.changeTurn();
		//activate mouse listening 
		this.addMouseListener(this);
		
	}
	
	//overriding method: overides paintComponent in JPanel
	public void paintComponent(Graphics g){
		
		g.setColor(Color.black);
		g.fillRect(0, 0, bWidthPixels, bWidthPixels);
		
		   for (int row = 0; row < bWidthSquares; ++row){
		        for (int column = 0 ; column < bWidthSquares; ++column){
			        theBoard[row][column].draw(g);
		    }
		}
	}
	
	public void changeTurn(){
		//if currentTurn is black, make it a whitestone

		if (currentTurn == PenteMain.BLACKSTONE){
			currentTurn = PenteMain.WHITESTONE;
		}else if (currentTurn == PenteMain.WHITESTONE){
				currentTurn = PenteMain.BLACKSTONE;
		}
	}
	
	public void checkForCaptures(Square s){
			
		int sRow = s.getRow();
		int sCol = s.getCol();
		System.out.println("In checkForCaptures sRow and sCol is [ "+ sRow + "," + sCol + "].");
		int Opposite = this.getTheOppositeState(s);
		
		//for a right horizontal check
		
		for(int dy = -1; dy <= 1; ++dy){
			if((dy > 0 && sRow < bWidthSquares -3) || (dy < 0 && sRow >= 3) || dy == 0){
			   for(int dx = -1; dx<=1; ++dx){
				   if((dx > 0 && sCol < bWidthSquares -3) || (dx < 0 && sCol >= 3) || dx == 0){
					   if(theBoard[sRow + (1 * dy)][sCol + (1 * dx)].getState() == Opposite){
						   if(theBoard[sRow + (2 * dy)][sCol + (2 * dx)].getState() == Opposite){
								   if(theBoard[sRow + (3 * dy)][sCol + (3* dx)].getState() == currentTurn){
									   System.out.println("We have a Horizontal Capture checking right");
									   this.takeStones(sRow + (1 * dy), sCol + (1 * dx), 
											   sRow + (2 * dy), sCol + (2 * dx), currentTurn);
									   repaint();
								   }
							   }
						   }
					   }
				   }
			   
			   /*if(theBoard[sRow][sCol+1].getState() == Opposite &&
			   theBoard[sRow][sCol+2].getState() == Opposite &&
			   theBoard[sRow][sCol+3].getState() == currentTurn){
				//my Code starts here:
				this.takeStones(sRow, sCol+1, sRow, sCol+2, currentTurn);
				
			}
		}
		if(sCol <= bWidthSquares -1){
			if(theBoard[sRow][sCol-1].getState() == Opposite &&
			   theBoard[sRow][sCol-2].getState() == Opposite &&
			   theBoard[sRow][sCol-3].getState() == currentTurn){
				//my Code starts here:
				this.takeStones(sRow, sCol-1, sRow, sCol-2, currentTurn);
			     
			    }*/
			}
		}
	}


	public int getTheOppositeState(Square s){
		if(s.getState() == PenteMain.BLACKSTONE){
			return PenteMain.WHITESTONE;
		}else {
			return PenteMain.BLACKSTONE;
		}
	}
	
	public void takeStones(int r1, int c1, int r2, int c2, int taker){
		//clear the stones in the between
		theBoard[r1][c1].setState(PenteMain.EMPTY);
		theBoard[r2][c2].setState(PenteMain.EMPTY);
		
		if(taker == PenteMain.BLACKSTONE){
			++blackCaptures;
		}else{
			++whiteCaptures;
		}
		this.checkForWinOnCaptures();
	}
	
	public void checkForWinOnCaptures(){
		if(blackCaptures >=5){
			//there shoule be some phenomenal win
			//String myMessage = "<p style> "font-size:"
			JOptionPane.showMessageDialog(null, "Black wins! with " +
			blackCaptures + " captures!");
		}
		if(whiteCaptures >=5){
			//there shoule be some phenomenal win
			JOptionPane.showMessageDialog(null, "white wins! with " +
			whiteCaptures + " captures!");
		}
	}
	
	public void checkForWin(Square s){
		int sRow = s.getRow();
		int sCol = s.getCol();
		this.repaint();
		System.out.println("In checkForCaptures sRow and sCol is [ "+ sRow + "," + sCol + "].");
		int Opposite = this.getTheOppositeState(s);
		
		//for a right horizontal check
		
		for(int dy = -1; dy <= 1; ++dy){
			
			if((dy > 0 && sRow < bWidthSquares -4) || (dy < 0 && sRow >= 4) || dy == 0){
				
			   for(int dx = -1; dx<=1; ++dx){
				   if(!(dx == 0 && dy ==0)){
				   if((dx > 0 && sCol < bWidthSquares -4) || (dx < 0 && sCol >= 4) || dx == 0){
					   
					   if(theBoard[sRow + (1 * dy)][sCol + (1 * dx)].getState() == currentTurn)
						   if(theBoard[sRow + (2 * dy)][sCol + (2 * dx)].getState() == currentTurn)
								   if(theBoard[sRow + (3 * dy)][sCol + (3 * dx)].getState() == currentTurn)
									   if(theBoard[sRow + (4 * dy)][sCol + (4 * dx)].getState() == currentTurn)
										   if(theBoard[sRow + (5 * dy)][sCol + (5 * dx)].getState() == currentTurn){
									   System.out.println("We have a winner!");
									   this.takeStones(sRow + (1 * dy), sCol + (1 * dx), 
											   sRow + (2 * dy), sCol + (2 * dx), currentTurn);
									   repaint();
						  }
							}
					   }
				   }
			}
		}
	}
	public void checkForWin2(Square s){
		boolean done = false;
		int [] myDys = {-1, 0, 1};
		int whichDy = 0;
		
		while(!done && whichDy < 3){
			if (checkForWinAllInOne(s, myDys[whichDy], 1) == true){
				weHaveAWinner();
				done = true;
			}
			whichDy++;
		}
		if(!done){
			if (checkForWinAllInOne(s, 1, 0) == true){
				weHaveAWinner();
			}
		}
	}	

	public boolean checkForWinAllInOne(Square s, int dy, int dx){
		boolean isThereAWin = false;
		int sRow = s.getRow();
		int sCol = s.getCol();
		System.out.println("In checkForCaptures sRow and sCol is [" +
		sRow + ", " + sCol + "]");
		
		//for a right and left horizontal check:
		int howManyRight = 0;
		int howManyLeft = 0;
		
		//loop to check right side of where stone is
		int step = 1 ;
		while((sCol + (step * dx) < bWidthSquares) && (sRow + (step * dy)) < bWidthSquares
				&& (sCol + (step * dx )) >= 0 && (sRow + (step * dy)) >= 0
				&& theBoard[sRow + (step* dy)][sCol +(step *dx)].getState() == currentTurn){
			howManyRight++;
			step++;
		}
		step =1;
		while((sCol - (step * dx)) >= 0 && (sRow - (step * dy)) >= 0
				&& (sCol - (step * dx) < bWidthSquares) && (sRow - (step * dy)) < bWidthSquares
				&& (theBoard[sRow - (step *dy)][sCol - (step *dx)].getState() == currentTurn)){
			howManyLeft++;
			step++;
		}
		//Moving Left...
		step = 1;
		if((howManyRight + howManyLeft + 1) >= 5){
			isThereAWin = true;
		}
		return isThereAWin;
	}
	
	public void weHaveAWinner(){
		String theWinner = null;
		if(currentTurn == PenteMain.BLACKSTONE){
			 theWinner = "BlackStone wins!";
		}else{
			theWinner = "WhiteStone wins!";
		}
		JOptionPane.showMessageDialog(null, "Congratulations! " + theWinner);
	}
	
	public int getBoardWidthInSquares(){
		return bWidthSquares;
	}
	
	public Square[][] getActualGameBoard(){
		return theBoard;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("You clicked at [" + e.getX() + "," + e.getY() + "].");
		playGame(e);
	}
 
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void playGame(MouseEvent e){
		
		Square s = findSquare(e.getX(),e.getY());
		if(s!=null){
			if(s.getState() == PenteMain.EMPTY){
			this.doPlay(s);
			if(playAgainstYuchen == true && currentTurn == yuchenStoneColor){
				Square cs = computerMoveGenerator.doComputerMove(s.getRow(), s.getCol());
				this.doPlay(cs);
				this.requestFocus();
			}
		}else{
			JOptionPane.showMessageDialog(null, "You cannot click on a space with a stone!");
		}
	}else {
		JOptionPane.showMessageDialog(null, "You did not click on a square!");
	}
		
}
	public void doPlay(Square s){
		s.setState(currentTurn);
		this.repaint();
		this.checkForCaptures(s);
		this.checkForWinOnCaptures();
		this.checkForWin2(s);
		this.changeTurn();
	}
	
	public Square findSquare(int mouseX, int mouseY){
		Square ClickSquare = null;
		
		//run through all of the squares and call youClickedMe(
		for (int row = 0; row < bWidthSquares; ++row){
	        for (int column = 0 ; column < bWidthSquares; ++column){
		        if(theBoard[row][column].youClickedMe(mouseX, mouseY) == true){
		        	ClickSquare = theBoard[row][column];
		     }
	    }
	}
		
		return ClickSquare;
	}
}
