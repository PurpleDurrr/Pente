import java.awt.Color;
import java.awt.Graphics;


public class Square {
    
	private int xLoc, yLoc; //top left corner position of square on board
	private int sWidth;  //the width of the square also the height
	private Color boardSquareColor = new Color(255,209,26);
	private Color crossHair = new Color(150,111,51);
	private int squareState = PenteMain.EMPTY;
	private int myRow, myCol;
		
	public Square(int x, int y, int w, int r, int c){
		
		xLoc = x;
		yLoc = y;
		sWidth = w;
		myRow = r;
		myCol = c;
		
	}
	
	public void draw(Graphics g){
		//Draw a square
		g.setColor(boardSquareColor);
		g.fillRect(xLoc, yLoc, sWidth, sWidth);
		
		//Draw cross hair
		g.setColor(crossHair);
		g.drawLine(xLoc + (int)(sWidth/2), yLoc, xLoc + (int)(sWidth/2), yLoc + sWidth);
		
		//Make another cross hair
		g.drawLine(xLoc, yLoc + (int)(sWidth/2), xLoc + sWidth, yLoc + (int)(sWidth/2));
		g.setColor(Color.RED);
		g.drawRect(xLoc, yLoc, sWidth, sWidth);
		
		if(squareState == PenteMain.BLACKSTONE){
			g.setColor(Color.BLACK);
			g.fillOval(xLoc +3, yLoc +3, sWidth -6, sWidth-6);
		}
		
		if(squareState == PenteMain.WHITESTONE){
			g.setColor(Color.WHITE);
			g.fillOval(xLoc +3, yLoc +3, sWidth -6, sWidth-6);
		}
	}
	
	public void setState (int newState){
		squareState = newState;
		
	}
	
	public int getState(){
		return squareState;
		
	}
	
	public int getRow(){
		return myRow;
	}
	
	public int getCol(){
		return myCol;
	}
	
	//This checks a mouseclick to see if its inside the bounds of the square:

	public boolean youClickedMe(int mouseX, int mouseY){
		boolean ClickSquare = false;
		
		if ( mouseX >= xLoc && mouseX <= xLoc + sWidth && mouseY>= yLoc && mouseY <= yLoc +sWidth){
			System.out.println("square found");
			 ClickSquare = true;
	
		}
		return ClickSquare;
	}
}
