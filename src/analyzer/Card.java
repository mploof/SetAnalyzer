package analyzer;

public class Card {
	
	public class Color{		
		final static int PUR = 0;
		final static int GRN = 1;
		final static int RED = 2;
	}
	public class Fill{
		final static int SOLID = 0;
		final static int HATCH = 1;
		final static int EMPTY = 2;
	}
	public class Shape{
		final static int OVAL = 0;
		final static int DIAM = 1;
		final static int SQUIG = 2;
	}
	
	private int shape;
	private int color;
	private int fill;
	private int num;
	private String shapeName;
	private String colorName;
	private String fillName;
	
	public Card(int num, int shape, int color, int fill){
		this.num = num;
		this.shape = shape;
		this.color = color;
		this.fill = fill;
		
		switch(shape){
		case Shape.OVAL:
			this.shapeName = "O";
			break;
		case Shape.DIAM:
			this.shapeName = "<>";
			break;
		case Shape.SQUIG:
			this.shapeName = "~";
			break;		
		}
		switch(color){
		case Color.PUR:
			this.colorName = "Pur";
			break;		
		case Color.GRN:
			this.colorName = "Grn";
			break;
		case Color.RED:
			this.colorName = "Red";
			break;
		}
		switch(fill){
		case Fill.SOLID:
			this.fillName = "+++";
			break;
		case Fill.HATCH:
			this.fillName = "///";
			break;
		case Fill.EMPTY:
			this.fillName = "[ ]";
			break;		
		}
		
		
	}

	public int getShape() {
		return shape;
	}

	public void setShape(int shape) {
		this.shape = shape;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getFill() {
		return fill;
	}

	public void setFill(int fill) {
		this.fill = fill;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	public String getShapeName() {
		return shapeName;
	}

	public String getColorName() {
		return colorName;
	}

	public String getFillName() {
		return fillName;
	}
	
	public boolean equals(Card comparison){
		if(comparison.num == this.num && comparison.shape == this.shape && 
				comparison.color == this.color && comparison.fill == this.fill)
			return true;
		else
			return false;
	}
	
	public void print(){
		System.out.println("Number\tShape\tColor\tFill");
		System.out.println(this.getNum() + "\t" + this.getShapeName() + "\t" + this.getColorName() + "\t" + this.getFillName());
		System.out.println("");
	}
	
	public static Card getSetComplement(Card first, Card second){
		
		final int TOTAL = 3;
		
		int newNum = first.getNum() == second.getNum() ? first.getNum() : TOTAL - (first.getNum() + second.getNum());
		int newShape = first.getShape() == second.getShape() ? first.getShape() : TOTAL - (first.getShape() + second.getShape());
		int newColor = first.getColor() == second.getColor() ? first.getColor() : TOTAL - (first.getColor() + second.getColor());
		int newFill = first.getFill() == second.getFill() ? first.getFill() : TOTAL - (first.getFill() + second.getFill());
		
		Card ret = new Card(newNum, newShape, newColor, newFill);		
		return ret;
	}
}
