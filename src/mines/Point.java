package mines;


public class  Point {
	protected int I;
	protected int J;
	protected boolean isMine;
	protected boolean isOpen;
	protected boolean isFlag;
	protected int minesAround;

	public Point(int i, int j) {
		this.I = i;
		this.J = j;
		this.isMine = false;
		this.isOpen = false;
		this.isFlag = false;
	}

	public Point(int i) {
		this.I = i;
		this.isMine = false;
		this.isOpen = false;
	}
	public int getI() {
		return I;
	}

	public void setI(int i) {
		this.I = i;
	}

	public int getJ() {
		return J;
	}

	public void setJ(int j) {
		this.J = j;
	}
	
	public boolean IsMine() {
		return isMine;
	}

	public void isMine(boolean isMine) {
		this.isMine = isMine;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	
	public boolean isFlag() {
		return isFlag;
	}
	
	public int getMinesAround() {
		return minesAround;
	}

	public void setMinesAround(int minesAround) {
		this.minesAround = minesAround;
	}

	public void setFlag(boolean isFlag) {
		this.isFlag = isFlag;
	}

	@Override
	public String toString() {
		if (!this.isOpen()) {
			if (this.isFlag())
				return "F";
			else
				return ".";
		}
		if (this.IsMine())
			return "X";
		else
			return this.getMinesAround() == 0 ? " " : String.valueOf(this.getMinesAround());
	}
	
	public String getString2() {
		if (this.IsMine())
			return "X";
		else
			return this.getMinesAround() == 0 ? " " : String.valueOf(this.getMinesAround());
	}

}