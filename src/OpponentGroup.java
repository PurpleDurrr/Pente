import java.util.ArrayList;


public class OpponentGroup {

	static final int HGroup = 1;
	static final int VGroup = 2;
	static final int DRightGroup = 3;
	static final int DLeftGroup = 4;
	public static final int Middle_4_Group = -4;
	public static final int Middle_3_Group = -3;
	
	
	ArrayList<Square> groupList;
	int groupLength = 0;
	int groupRanking = 0;
	
	Square end1Square = null;
	Square end2Square = null;
	
	private int groupType;
	private String groupTypeText;
	
	private boolean inMiddleGroupStatus = false;
	private Square inMiddleSquare = null;
	
	
	//handle issue of whether the current move is a part of this group
	private boolean currentMoveIsInGroup = false;
	int currentMoveArrayListLocation = -1;
	
	public OpponentGroup(int gt){
		groupList = new ArrayList<Square>();
		//System.out.println("In Opponent Group -- just made a new group");
	groupType = gt;
	this.setGroupTypeToString();
	
	
	}
	
	public void addSquareToGroup(Square whichSquare){
		groupList.add(whichSquare);
		groupLength++;
		groupRanking++;
		
	}
	
	public void setEnd1Square(Square whatSquare){
		end1Square = whatSquare;
	}
	
	public void setEnd2Square(Square whatSquare){
		end2Square = whatSquare;
	}
	
	public ArrayList<Square> getGroupList(){
		return groupList;
	}
	
	public Square getEnd1Square(){
		return end1Square;
	}
	
	public Square getEnd2Square(){
		return end2Square;
	}
	
	public int getGroupLength(){
		return groupLength;
	}
	
	public void setGroupLength(int l) {
		groupLength = l;
	}
	public int getGroupRanking(){
		return groupRanking;
	}
	
	public void setGroupRanking(int newRanking){
		groupRanking = newRanking;
	}
	
	public int getOpponentGroupType(){
		return groupType;
	}
	
	public void setCurrentMoveIsInGroup(){
		currentMoveIsInGroup = true;
	}
	
	public boolean getCurrentMoveIsInGroup(){
		return currentMoveIsInGroup;
	}
	
	public void setCurrentMoveArrayListLocation(int arrayListIndex){
		currentMoveArrayListLocation = arrayListIndex;
	}
	
	public int getArrayListSizeFromArray(){
		return groupList.size();
	}
	
	public void setInMiddleGroup(boolean value){
		inMiddleGroupStatus = value;
	}
	
	public boolean getInMiddleGroupStatus(){
		return inMiddleGroupStatus;
	}
	
	public void setInMiddleGroupSquare(Square whatSquare){
		inMiddleSquare = whatSquare;
	}
	
	public Square getInMiddleGroupSquare(){
		return inMiddleSquare;
	}
	
	private void setGroupTypeToString(){
		switch(groupType){
		case Middle_4_Group:
            groupTypeText = "Middle-4";
		case Middle_3_Group:
            groupTypeText = "Middle-3";     
		case HGroup:
			groupTypeText = "Horizontal";
			break;
		case VGroup:
			groupTypeText = "Vertical";
			break;
		case DRightGroup:
			groupTypeText = "Diagonal Right";
			break;
		case DLeftGroup:
			groupTypeText = "Diagonal Left";
			break;
		default:
			groupTypeText = "Somethihng is messed up";
			break;
		}
	}
	
	public String getGroupTextType(){
		return groupTypeText;
	}
}

