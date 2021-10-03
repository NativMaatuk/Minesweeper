package mines;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mines m = new Mines(3, 4, 0);
		m.addMine(0, 1);
		m.addMine(2, 3);
		m.open(2, 0);
		System.out.println(m);
		m.toggleFlag(0, 1);
		System.out.println(m);
	}
}
