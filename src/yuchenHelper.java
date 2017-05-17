import java.util.ArrayList;

import javax.swing.JOptionPane;


public class yuchenHelper {


		PenteGameBoard myBoard;
		int myStoneColor, opponentStoneColor;
		int boardWidthSquares;
		Square [][] theGameBoard;
		
		boolean timeToMakeAMove = false;
		boolean moveToMake = false;
		int moveToDealWithRow;
		int moveToDealWithCol;
		
		//big addition for opponent groups...
		ArrayList<OpponentGroup> group1 = new ArrayList<OpponentGroup>();
		ArrayList<OpponentGroup> group2 = new ArrayList<OpponentGroup>();
		ArrayList<OpponentGroup> group3 = new ArrayList<OpponentGroup>();
		ArrayList<OpponentGroup> group4 = new ArrayList<OpponentGroup>();
		 
		public yuchenHelper(PenteGameBoard b, int stoneColor){
			myBoard = b;
			myStoneColor = stoneColor;
			this.setOpponentStoneColor();
			boardWidthSquares = b.getBoardWidthInSquares();
			theGameBoard = b.getActualGameBoard();
			JOptionPane.showMessageDialog(null, "Hi, yuchen Here. Be ready to play!");
			
		}
		public void setOpponentStoneColor(){
			if(myStoneColor == PenteMain.BLACKSTONE){
				opponentStoneColor = PenteMain.WHITESTONE;
			}else{
				opponentStoneColor = PenteMain.BLACKSTONE;
			}
		}
		public Square doComputerMove(int lastMoveRow, int lastMoveCol){
			//To use for opponent group:
			System.out.println("in doMove about to call assess board");
			this.assessBoard(lastMoveRow, lastMoveCol); 
			//logic:
			//stop any win(four in a row)
			//if there is three in a row
			//identify a capture possibility and go for it
			//set up for a capture
			//when all else fails to move randomly
			
			Square nextMove = null;
			nextMove = this.blockEveryWang(group4,4);
			if(nextMove == null) {
				nextMove = this.blockEveryWang(group3,3);
				if (nextMove == null){
					nextMove = captureATwo();
					if (nextMove == null){
						nextMove = this.blockEveryWang(group2, 2);
						if(nextMove == null){
					nextMove = makeRandomMove();
						}
				}
			}
	      }
			return nextMove;
		}
		public Square captureATwo(){
			Square nextMove= null;
			
			if(group2.size() > 0){
				boolean done = false;
				int groupIndex = 0;
				
				while (!done && groupIndex < group2.size()){
					OpponentGroup currentgroup = group2.get(groupIndex);
					Square e1 = group2.get(groupIndex).getEnd1Square();
					Square e2 = group2.get(groupIndex).getEnd2Square();
					
					System.out.println("At top of loop in capture a two and group Index is" + groupIndex);
					groupIndex++;
					
					//my code:
					if((e1 != null && e1.getState() == PenteMain.EMPTY) 
							&& (e2 !=null && e2.getState() == PenteMain.WHITESTONE )){
							System.out.println("e1 is empty but e2 is not ");
								nextMove = e1;
							done = true;
						
					}else{
						if((e1 != null && e1.getState() == PenteMain.WHITESTONE) 
								&& (e2 !=null && e2.getState() == PenteMain.EMPTY )){
							System.out.println("e2 is empty but e1 is not ");
							nextMove = e2;
							done = true;
						}
					}
				}
			}
			return nextMove;
		}
		
		public Square blockEveryWang(ArrayList<OpponentGroup> whatGroup, int whatGroupSize){
			//ArrayList<OpponentGroup> whatGroup = group4;
			Square nextMove = null;
			
			if(whatGroup.size() >0){
				boolean done = false;
				int groupIndex = 0;
				
			while(!done && groupIndex < whatGroup.size()){
				OpponentGroup curGroup = whatGroup.get(groupIndex);
				Square e1 = whatGroup.get(groupIndex).getEnd1Square();
				Square e2 = whatGroup.get(groupIndex).getEnd2Square();
				
				//System.out.println("At top of loop in block a 4 and groupIndex is" + groupIndex);
				groupIndex++;
				
				if(curGroup.getInMiddleGroupStatus() == true){
					nextMove = curGroup.getInMiddleGroupSquare();
					
				}else{
					
				}
				
				if((e1 != null && e1.getState() == PenteMain.EMPTY) 
					&& (e2 !=null && e2.getState() == PenteMain.EMPTY )){
					System.out.println("e1 and e2 are empty ");
					int r = (int)(Math.random()*100);
					if(r > 50){
						nextMove = e1;
					} else {
						nextMove = e2;
					}
					done = true;
				
				}else{
					if(whatGroupSize == 4){
						
					if(e1 != null && e1.getState() == PenteMain.EMPTY){
						//System.out.println("e1 is empty in block it");
						nextMove = e1;
						done = true;
						
					} else {
					if (e2 != null && e2.getState() == PenteMain.EMPTY){
						//System.out.println("e2 is empty in block it");
						nextMove = e2;
						done = true;
								}
							}
						}
					}
				}
			}
			return nextMove;
		}	
		
		/*public Square specialProcessForThree (ArrayList<OpponentGroup> g){
			Square squareToReturn = null;
			//Square e1 = g.getEnd1Square();
			//Square e2 = g.getEnd2Square();
			
		
			
			return squareToReturn;
		}
			for(int i = 0; i < group4.size(); i++){
				Square a = group4.get(i).getEnd1Square();
				Square b = group4.get(i).getEnd2Square();
				if(a.getState() != opponentStoneColor){
					nextMove = a;
					break;
				} 
				
				else if(b.getState() != opponentStoneColor){
					nextMove = b;
					break;
				}
			}*/
		

	/*
		public Square blockAthree(){
			Square nextMove = null;
			
			if(group3.size() >0){
				boolean done = false;
				int groupIndex = 0;
				
			while(!done && groupIndex < group3.size()){
				Square e1 = group3.get(groupIndex).getEnd1Square();
				Square e2 = group3.get(groupIndex).getEnd2Square();
				
				System.out.println("At top of loop in block a 3 and groupIndex is" + groupIndex);
				groupIndex++;
				
				if((e1 != null && e1.getState() == PenteMain.EMPTY) 
					&& (e2 !=null && e2.getState() == PenteMain.EMPTY )){
					System.out.println("We have found ");
					int r = (int)(Math.random()*100);
					if(r > 50){
						nextMove = e1;
					} else {
						nextMove = e2;
					}
					done = true;
				
				}else{
					if(e1 != null && e1.getState() == PenteMain.EMPTY){
						System.out.println("e1 is empty in block a four");
						nextMove = e1;
						done = true;
					}
					if (e2 != null && e2.getState() == PenteMain.EMPTY){
						System.out.println("e2 is empty in block a four");
						nextMove = e2;
						done = true;
						
					}
							
					}
				}
			}
			return nextMove;
		}

	*/
		public void assessBoard(int lastMoveRow, int lastMoveCol){
			
			group1.clear();
			group2.clear();
			group3.clear();
			group4.clear();
			
			//System.out.println("In assessboard about to call look for groups horizontally");
			this.lookForGroupHorizontally(lastMoveRow, lastMoveCol);
			//System.out.println("In assessboard back from look for groups horiozontally");
			
			//System.out.println("In assessboard about to call look for groups vertically");
			this.lookForGroupVertically(lastMoveRow, lastMoveCol);
			//System.out.println("In assessboard back from look for groups vertically");
			
			
			//System.out.println("In assessboard about to call look for groups right diagonally");
			this.lookForDiagonalRight(lastMoveRow, lastMoveCol);
			
			//System.out.println("In assessboard about to call look for groups left diagonally");
			this.lookForGroupsDiagLeft(lastMoveRow, lastMoveCol);
			
			System.out.println("In assessboard about to call look for groups in middle ");
			this.doInMiddleCheck(3);
			//this.doInMiddleCheck(4);
		}
		
		public void lookForGroupHorizontally(int lastMoveRow, int lastMoveCol){
			
			int curCol;
			
			for( int row = 0; row < boardWidthSquares; ++row){
				curCol = 0;
				
				while(curCol < boardWidthSquares){
					Square newStart = findOpponentStartHorizontal(row, curCol);
					if(newStart != null){
						//make an object group
						OpponentGroup newGroup = new OpponentGroup(OpponentGroup.HGroup);
						//add stone to array
						newGroup.addSquareToGroup(newStart);
						System.out.println("Adding a square to group");
						
						//check the edges
						int startRow = newStart.getRow();
						int startCol = newStart.getCol();
						if (startCol <= 0){
							newGroup.setEnd1Square(null);
						}else{
							newGroup.setEnd1Square(theGameBoard[startRow][startCol-1]);
						}
						
						//check to see if the current player move is this stone
						if(startRow == lastMoveRow && startCol == lastMoveCol){
							newGroup.setCurrentMoveIsInGroup();
							newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength()-1);
						}
						
						// start getting neighbors
						startCol++;
						
						while(startCol < boardWidthSquares && 
								theGameBoard[startRow][startCol].getState() == this.opponentStoneColor){
							newGroup.addSquareToGroup(theGameBoard[startRow][startCol]);
							if(startRow == lastMoveRow && startCol == lastMoveCol){
								newGroup.setCurrentMoveIsInGroup();
								newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength()-1);
							}
							startCol++;
						}
						
						//set the second edge... this is abridged from first edge:
						if (startCol >= boardWidthSquares){
							newGroup.setEnd1Square(null);
						}else{
							newGroup.setEnd1Square(theGameBoard[startRow][startCol]);
						}
						curCol = startCol;
						
						//finally add this to the grouplist
						this.addNewGroupToGroupLists(newGroup);
						
					} else {
						// if startsquare is null
						//this forces it to end the loop
						curCol = boardWidthSquares;
						
					}
				}
			}
			
		}
		
		public Square findOpponentStartHorizontal(int whatRow, int whatCol){
			Square opponentStart = null;
			boolean done = false;
			int curCol = whatCol;
			int curRow = whatRow;
			
			while( !done && curCol < boardWidthSquares){
				if(theGameBoard[whatRow][curCol].getState() == this.opponentStoneColor){
					opponentStart = theGameBoard[whatRow][curCol];
					done = true;
				}
				curCol++;
			}
			 
			return opponentStart;
		}
		
		public void addNewGroupToGroupLists(OpponentGroup ng){
			
			/*if(ng.getGroupLength() == 1){
				group1.addSquareToGroup(opponentStart);
			}
			if(ng.getGroupLength() == 2){
				group2.addSquareToGroup(opponentStart);
			}
			if(ng.getGroupLength() == 3){
				group3.addSquareToGroup(opponentStart);
			}
			if(ng.getGroupLength() == 4){
				group4.addSquareToGroup(opponentStart);
			}
			*/
			
			switch(ng.getGroupLength()){
		
			case 1:
				group1.add(ng);
				break;
			case 2:
				System.out.println("I have an "+ ng.getGroupTextType() + 
						" Group with two opponent stones");
				group2.add(ng);
				break;
			case 3:
				System.out.println("I have an "+ ng.getGroupTextType() + 
						" Group with three opponent stones");
				group3.add(ng);
				break;
			case 4:
				System.out.println("I have an "+ ng.getGroupTextType() + 
						" Group with four opponent stones");
				group4.add(ng);
				break;
			default:
				System.out.println("I have an "+ ng.getGroupTextType() + 
						" Group with " + ng.getGroupLength() + " opponent stones");
				System.out.println("Something is really messed up");
				break;
			}
		}
		
		public Square makeRandomMove(){
			int newMoveRow, newMoveCol;
			do{
			newMoveRow = (int)(Math.random() * boardWidthSquares);
			newMoveCol = (int)(Math.random() * boardWidthSquares);
		}while (theGameBoard[newMoveRow][newMoveCol].getState() != PenteMain.EMPTY);
		
			System.out.println("Hi! yuchen wants to move to [" + 
			newMoveRow + ", " + newMoveCol + " ].");
			return theGameBoard[newMoveRow][newMoveCol];
		}

		public void lookForGroupVertically(int lastMoveRow, int lastMoveCol){
			
			int curRow;
			for( int Col = 0; Col < boardWidthSquares; ++Col){
				curRow = 0;
				
				while(curRow < boardWidthSquares){
					Square newStart = findOpponentStartVertically(curRow, Col);
					if(newStart != null){
						//make an object group
						OpponentGroup newGroup = new OpponentGroup(OpponentGroup.VGroup);
						//add stone to array
						newGroup.addSquareToGroup(newStart);
						
						//check the edges
						int startRow = newStart.getRow();
						int startCol = newStart.getCol();
						if (startRow <= 0){
							newGroup.setEnd1Square(null);
						}else{
							newGroup.setEnd1Square(theGameBoard[startRow-1][startCol]);
						}
						
						//check to see if the current player move is this stone
						if(startRow == lastMoveRow && startCol == lastMoveCol){
							newGroup.setCurrentMoveIsInGroup();
							newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength()-1);
						}
						
						// start getting neighbors
						startRow++;
						
						while(startRow < boardWidthSquares && 
								theGameBoard[startRow][startCol].getState() == this.opponentStoneColor){
							newGroup.addSquareToGroup(theGameBoard[startRow][startCol]);
							if(startRow == lastMoveRow && startCol == lastMoveCol){
								newGroup.setCurrentMoveIsInGroup();
								newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength()-1);
							}
							startRow++;
						}
						
						//set the second edge... this is abridged from first edge:
						if (startRow >= boardWidthSquares){
							newGroup.setEnd1Square(null);
						}else{
							newGroup.setEnd1Square(theGameBoard[startRow][startCol]);
						}
						curRow = startRow;
						
						//finally add this to the grouplist
						this.addNewGroupToGroupLists(newGroup);
						
					} else {
						// if startsquare is null
						//this forces it to end the loop
						curRow = boardWidthSquares;
						
					}
				}
			}
			
		}
		
		public Square findOpponentStartVertically(int whatRow, int whatCol){
			Square opponentStart = null;
			boolean done = false;
			int curCol = whatCol;
			int curRow = whatRow;
			
			while( !done && curRow < boardWidthSquares){
				if(theGameBoard[curRow][whatCol].getState() == this.opponentStoneColor){
					opponentStart = theGameBoard[curRow][whatCol];
					done = true;
				}
				curRow++;
			}
			 
			return opponentStart;
		}
		
		public void lookForDiagonalRight( int lastMoveRow, int lastMoveCol){
			for(int row = 0; row < boardWidthSquares; ++row){
				int curCol = 0;
				int curRow = row;
				
				while(curCol < boardWidthSquares - row && curRow < boardWidthSquares){
					Square groupStart = findOpponentDiagonalRight(curRow, curCol, 0);
					
					if(groupStart != null){
						OpponentGroup newGroup = new OpponentGroup(OpponentGroup.DRightGroup);
						newGroup.addSquareToGroup(groupStart);
						int startRow = groupStart.getRow();
						int startCol = groupStart.getCol();
						
						if(startRow - 1 >= 0 && startCol -1 >= 0){
							newGroup.setEnd1Square(theGameBoard[startRow-1][startCol-1]);
						}else{
							newGroup.setEnd1Square(null);
						}
						
						if(startRow == lastMoveRow && startCol == lastMoveCol){
							newGroup.setCurrentMoveIsInGroup();
							newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
						}
						
						startCol++;
						startRow++;
						boolean done = false;
						
						while(startRow < boardWidthSquares && startCol < boardWidthSquares - row && !done){
							if(theGameBoard[startRow][startCol].getState() == this.opponentStoneColor){
								newGroup.addSquareToGroup(theGameBoard[startRow][startCol]);
								if(startRow == lastMoveRow && startCol == lastMoveCol){
									newGroup.setCurrentMoveIsInGroup();
									newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
								}
								
								startRow++;
								startCol++;
							}else{
								done = true;
							}
						}
						
						if(startRow < boardWidthSquares && startCol< boardWidthSquares){
							newGroup.setEnd2Square(theGameBoard[startRow][startCol]);
						}else{
							newGroup.setEnd2Square(null);
						}
						//important to stop infinite loop 
						curCol = startCol;
						curRow = startRow;
						this.addNewGroupToGroupLists(newGroup);
						
					} else { 
						curRow = boardWidthSquares;
					}
				}
			}
			
			for(int col = 0; col < boardWidthSquares; ++col){
				int curCol = col;
				int curRow = 0;
				
				while(curRow < boardWidthSquares - col && curCol < boardWidthSquares){
					Square groupStart = findOpponentDiagonalRight(curRow, curCol, 0);
					
					if(groupStart != null){
						System.out.println("Hi, I found a group start SECOND PART OF DIAG LOOP **** "
								+ "at " + groupStart.getRow() + " , " + groupStart.getCol());
						OpponentGroup newGroup = new OpponentGroup(OpponentGroup.DRightGroup);
						newGroup.addSquareToGroup(groupStart);
						
						int startRow = groupStart.getRow();
						int startCol = groupStart.getCol();
						
						if(startRow - 1 >= 0 && startCol -1 >= 0){
							newGroup.setEnd1Square(theGameBoard[startRow-1][startCol-1]);
						}else{
							newGroup.setEnd1Square(null);
						}
						
						if(startRow == lastMoveRow && startCol == lastMoveCol){
							newGroup.setCurrentMoveIsInGroup();
							newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
						}
						
						startCol++;
						startRow++;
						boolean done = false;
						
						//this loop collects the length of the opponent group
						while(startRow < boardWidthSquares - col && startCol < boardWidthSquares && !done){
							if(theGameBoard[startRow][startCol].getState() == this.opponentStoneColor){
								newGroup.addSquareToGroup(theGameBoard[startRow][startCol]);
								if(startRow == lastMoveRow && startCol == lastMoveCol){
									newGroup.setCurrentMoveIsInGroup();
									newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
								}
								startRow++;
								startCol++;
							}else{
								done = true;
							}
						}
						
						if(startRow < boardWidthSquares && startCol < boardWidthSquares){
							newGroup.setEnd2Square(theGameBoard[startRow][startCol]);
						}else{
							newGroup.setEnd2Square(null);
						}
						
						curCol = startCol;
						curRow = startRow;
						this.addNewGroupToGroupLists(newGroup);
					}else{
						
						//get out of loop
						curCol = boardWidthSquares;
					}
					
				}
				
			}
		}
		
		public Square findOpponentDiagonalRight(int whatCol, int whatRow, int r){
			Square opponentStart = null;
			boolean done = false;
			int curCol = whatCol;
			int curRow = whatRow;
			
			while(!done && curCol < boardWidthSquares -r && curRow < boardWidthSquares){
				if(theGameBoard[curRow][curCol].getState() == opponentStoneColor){
					opponentStart = theGameBoard[curRow][curCol];
					done = true;
				}
				curRow++;
				curCol++;
			}
			return opponentStart;
		}

		public void lookForGroupsDiagLeft( int lastMoveRow, int lastMoveCol ){
			
			for(int row = 0 ; row < boardWidthSquares; ++row ){
				int curCol = boardWidthSquares -1; 
				int curRow = row; 
			
			while(curCol >= row && curRow < boardWidthSquares) {
				Square groupStart = findOpponentDiagLeft(curRow,curCol);
			
			if( groupStart != null ) {
				//You have a start so set up a new group! 
				System.out.println ("Hi I found a group start at " + groupStart.getRow() + " , " + groupStart.getCol() ); 
				OpponentGroup newGroup = new OpponentGroup(OpponentGroup.DLeftGroup);
				newGroup.addSquareToGroup(groupStart); 
			
			int startRow = groupStart.getRow(); 
			int startCol = groupStart.getCol();
			
			// Check first edge 
			if(startRow -1 >= 0 && startCol + 1 <= boardWidthSquares) {
				newGroup.setEnd1Square(theGameBoard[startRow-1][startCol+1]);
			
			} else {
				newGroup.setEnd1Square(null);
			
			} 
			//see if current move is part of this group 
			if( startRow == lastMoveRow && startCol == lastMoveCol ){
				newGroup.setCurrentMoveIsInGroup();
				newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
			
			}
			startCol--;
			startRow++;
			boolean done = false;
			
			//look for additional group members startCol++; startRow++; boolean done = false;
			while( startCol >= row && startRow < boardWidthSquares && !done){
				if(theGameBoard[startRow][startCol].getState() == this.opponentStoneColor ) {
					newGroup.addSquareToGroup(theGameBoard[startRow][startCol]);
			
				if( startRow == lastMoveRow && startCol == lastMoveCol ){
					newGroup.setCurrentMoveIsInGroup(); 
					newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
				}
			
			startRow++; 
			startCol--;
			
			} else {
			done = true;
			}

			} 
			//check other edge 
			if(startRow < boardWidthSquares && startCol >= 0) {
				newGroup.setEnd2Square(theGameBoard[startRow][startCol]);
			} else {
				newGroup.setEnd2Square(null);
			}
			//Important to stop infinite loop 
			curCol = startCol; 
			curRow = startRow; 
			//add group to list 
			this.addNewGroupToGroupLists(newGroup);
			} else {
			//get out of loop!! 
				curRow = boardWidthSquares;
				curCol = row-1;
				}
			}
		}
		    for(int col = boardWidthSquares-2 ; col >= 0; --col ){
		    	int curCol = col; 
		    	int curRow = 0;
				
		    	while(curRow < boardWidthSquares - col && curCol >= 0) {
		    		Square groupStart = findOpponentDiagLeft(curRow,  curCol);
		    	
		    	if(groupStart != null){
			        System.out.println ("Hi I found a group start at " + groupStart.getRow() + ", " + groupStart.getCol() ); 
			        OpponentGroup newGroup = new OpponentGroup (OpponentGroup.DLeftGroup);
			        newGroup.addSquareToGroup(groupStart); 
			
			int startRow = groupStart.getRow(); 
			int startCol = groupStart.getCol();
			
			// Check first edge  same problem so same code from above should work...
			if(startRow - 1 >= 0 && startCol +1 <= boardWidthSquares) {
				newGroup.setEnd1Square(theGameBoard[startRow-1][startCol+1]);
			} else {
				newGroup.setEnd1Square(null);
			} 
			
			//see if current move is part of this group 
			if( startRow == lastMoveRow && startCol == lastMoveCol ){
			newGroup.setCurrentMoveIsInGroup(); 
			newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
			}
			//look for additional group members 
			startCol--; 
			startRow++; 
			boolean done = false;
			
			while( startCol >= 0  && startRow < boardWidthSquares && !done){
			if(theGameBoard[startRow][startCol].getState() == this.opponentStoneColor ) {
			newGroup.addSquareToGroup(theGameBoard[startRow][startCol]);
			
			if( startRow == lastMoveRow && startCol == lastMoveCol ){
			newGroup.setCurrentMoveIsInGroup(); 
			newGroup.setCurrentMoveArrayListLocation(newGroup.getGroupLength());
			}
			startRow++; 
			startCol--;
			
			} else {
			done = true;
			}
			}
			//check other edge 
			if(startRow < boardWidthSquares && startCol >= 0) {
				newGroup.setEnd2Square(theGameBoard[startRow][startCol]);
			} else {
				newGroup.setEnd2Square(null);
			}
			//Important to stop infinite loop 
			curCol = startCol; 
			curRow = startRow; 
			//add group to list 
			this.addNewGroupToGroupLists(newGroup);
			} else {
			//get out of loop 
				curCol = -1;
			}
			}

				}
			}

		
		public Square findOpponentDiagLeft(int whatRow, int whatCol){
				Square opponentStart = null; 
				boolean done = false; 
				int currentCol = whatCol; 
				int currentRow = whatRow;
				
				while(!done && currentCol >= 0 && currentRow < boardWidthSquares){
					if(theGameBoard[currentRow][currentCol].getState() == opponentStoneColor ){
						opponentStart = theGameBoard[currentRow][currentCol]; 
						System.out.println("identified opponent");
						done = true;
					} 
				currentRow++; 
				currentCol--;
				} 
				return opponentStart;
				}
				
		public void doInMiddleCheck( int groupSize ){
			for(int row = 0; row < boardWidthSquares; ++row){
				for(int col = 0; col < boardWidthSquares; ++col){ 
					if(theGameBoard[row][col].getState() == PenteMain.EMPTY){
						checkForBlockInMiddle(row, col, groupSize);
					}
				}
			}
		}
		
		public void checkForBlockInMiddle(int row, int col, int groupSize){
			boolean done = false; 
			int[] myDys = {-1, 0, 1};
			int whichDy = 0;  
			
			while(!done && whichDy < 3){
				checkForBlockInMiddleAllAround(row, col, groupSize,myDys[whichDy], 1 );
			    whichDy++;
			} 
				checkForBlockInMiddleAllAround(row, col, groupSize, 1, 0 );
		}
		
		public void checkForBlockInMiddleAllAround(int row, int col, int groupSize, int dy, int dx){
				int sRow = row; 
				int sCol = col; 
				//System.out.println("In checkForBlockInMiddleAllAround sRow and sCol is [" +
				//sRow + ", " + sCol + "]");
				//for a right-check and left... 
				
				int howManyRight = 0; 
				int howManyLeft = 0;
				
				//loop to check right side of where stone s is 
				int step = 1; 
				
				//System.out.println("In checkForWinAllInOne sRow and sCol are [" + sRow + ", " + sCol + "]");
				//System.out.println("In checkForWinAllInOne dy and dx are [" + dy + ", " + dx + "]");
				while((sCol + (step * dx) < boardWidthSquares) && (sRow + (step * dy) < boardWidthSquares) &&
							(sCol + (step * dx) >= 0) && (sRow + (step * dy) >= 0) && 
							(theGameBoard[sRow + (step * dy)][sCol + (step * dx)].getState() == this.opponentStoneColor)){
					howManyRight++; 
					step++;
				} 
				//Moving Left.... 
					step = 1; 
					
				while((sCol - (step * dx) >= 0) &&  (sRow - (step * dy) >= 0) && 
						(sCol - (step * dx) < boardWidthSquares) && (sRow - (step * dy) < boardWidthSquares) &&
						(theGameBoard[sRow - (step * dy)][sCol - (step * dx)].getState() == this.opponentStoneColor)){ 
							howManyLeft++; 
							step++;
				}
				
				if((howManyRight + howManyLeft) >= groupSize){
				//If you have this then you want to set Up an Opponent group for this 
					System.out.println("For square at " + row + ", " + col + " we have group of size of " 
							+ (howManyRight + howManyLeft)); 
					OpponentGroup newGroup; 
					
				if( groupSize == 4 ) {
					String middleGroupText = getMiddleGroupText(dx, dy, 4); 
					newGroup = new
							OpponentGroup(OpponentGroup.Middle_4_Group);
							newGroup.setGroupRanking(4); 
							newGroup.setGroupLength(4);
							newGroup.getGroupTextType();
							
				} else {
					
					String middleGroupText = getMiddleGroupText(dx, dy, 3); 
					newGroup = new OpponentGroup(OpponentGroup.Middle_3_Group);
					newGroup.setGroupRanking(3); 
					newGroup.setGroupLength(3); 
					newGroup.getGroupTextType();
				}

				newGroup.setInMiddleGroup(true); 
				newGroup.setInMiddleGroupSquare(theGameBoard[row][col]); 
				this.addNewGroupToGroupLists(newGroup);
				}
			}
		
		public String getMiddleGroupText(int dx, int dy, int groupSize){
				String gs = ""; 
			
			if(groupSize == 4){ 
				gs = "4"; 
				
			} else { 
				gs = "3"; 
			} 
			String theType = ""; if(dx == 1){
			
			if(dy == 1) theType = "Diag Right"; 
			if(dy == 0) theType = "Horizontal"; 
			if(dy == -1) theType = "Diag Left";
			
			} else {
			theType = "Vertical";
			} 
			return "Middle " + gs + ": " + theType;
			
		}
		
		public ArrayList<OpponentGroup> getPurpleGroup4(){
			return group4;
		}
		
		public ArrayList<OpponentGroup> getPurpleGroup3(){
			return group3;
		}
		
		public ArrayList<OpponentGroup> getPurpleGroup2(){
			return group2;
		}
	}
		

