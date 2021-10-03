package mines;

import java.util.*;
import java.util.Random;

public class Mines {
	private int height;
	private int width;
	private int numMines;
	private Point arr[][];
	List<Integer> open_i = new ArrayList<Integer>();
	List<Integer> open_j = new ArrayList<Integer>();
	int cntMineNebore = 0;
	public boolean showAll;

	public Mines(int height, int width, int numMines) {
		this.height = height;
		this.width = width;
		this.numMines = numMines;
		arr = new Point[height][width];
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				arr[i][j] = new Point(i, j);
			}
		}
		Random rand = new Random();
		for (int k = 0; k < this.numMines; k++) {
			int i = rand.nextInt(this.height);
			int j = rand.nextInt(this.width);
			if (!arr[i][j].IsMine()) {
				addMine(i,j);
			} else
				k--;
		}
	}

	public boolean addMine(int i, int j) {
		arr[i][j].isMine(true);
		Cord tmp;
		Iterator<Cord> it = getNeighbor(i, j).iterator();
		while (it.hasNext()) {
			tmp = it.next();
			arr[tmp.i][tmp.j].setMinesAround(arr[tmp.i][tmp.j].getMinesAround() + 1);
		}
		return true;
	}

	public List<Cord> getNeighbor(int i, int j) {
		boolean up = i >= 1;
		boolean down = i <= arr.length - 2;
		boolean left = j >= 1;
		boolean rgiht = j <= arr[0].length - 2;
		List<Cord> list = new ArrayList<Cord>();

//		(0,0) (0,1) (0,2)
//		(1,0) (1,1) (1,2)
//		(2,0) (2,1) (2,2)
		
		if (up) {
			// System.out.println("up");
			list.add(new Cord(i - 1, j));

		}
		if (down) {
			// System.out.println("down");
			list.add(new Cord(i + 1, j));
		}
		if (rgiht) {
			// System.out.println("rgiht");
			list.add(new Cord(i, j + 1));

		}
		if (left) {
			// System.out.println("left");
			list.add(new Cord(i, j - 1));
		}

		if (up && left) {
			// System.out.println("up & left");
			list.add(new Cord(i - 1, j - 1));
		}
		if (up && rgiht) {
			// System.out.println("up && rgiht");
			list.add(new Cord(i - 1, j + 1));
		}
		if (down && rgiht) {
			// System.out.println("down && rgiht");
			list.add(new Cord(i + 1, j + 1));
		}
		if (down && left) {
			// System.out.println("down && left");
			list.add(new Cord(i + 1, j - 1));
		}
		return list;
	}

	public boolean open(int i, int j) {
		if (arr[i][j].isOpen())
			return true;
		if (arr[i][j].IsMine())
			return false;
		arr[i][j].setOpen(true);
		//list to i
		open_i.add(i);
		//list to j
		open_j.add(j);
		//if i want to get the index i need loop of list i | = open_i & of list j | open_j
		if (arr[i][j].getMinesAround() != 0)
			return true;
		List<Cord> c = getNeighbor(i, j);
		Iterator<Cord> it = c.iterator();
		Cord tmp;
		while (it.hasNext()) {
			tmp = it.next();
			open(tmp.i, tmp.j);
		}
		return true;
	}
	
	public void emptyToOpen() {
		open_i.clear();
		open_j.clear();
	}

	public List<Integer> getOpen_i() {
		return open_i;
	}

	public List<Integer> getOpen_j() {
		return open_j;
	}

	public void toggleFlag(int x, int y) {
		if (arr[x][y].isFlag())
			arr[x][y].setFlag(false);
		else
			arr[x][y].setFlag(true);
	}

	public boolean isDone() {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (!arr[i][j].IsMine() && !arr[i][j].isOpen())
					return false;
			}
		}
		return true;
	}

	public String get(int i, int j) {
		if (this.showAll)
			return arr[i][j].getString2();
		return arr[i][j].toString();
	}

	public void setShowAll(boolean showAll) {
		this.showAll = showAll;
	}
	
	public Point getPoint(int i,int j) {
		return arr[i][j];
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				str.append(get(i, j));
			}
			str.append("\n");
		}
		return str.toString();
	}
}
