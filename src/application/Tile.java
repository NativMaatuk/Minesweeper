package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import mines.Point;

	public class Tile extends Point{
		private int btnI;
		private int btnJ;
		private Button btn;
		public Tile(int btnI,int btnJ) {
			super(btnI,btnJ);
			this.btnI = btnI;
			this.btnJ = btnJ;
		}
		public Tile(int btnI,int btnJ,int minesAround) {
			this(btnI,btnJ);
			setMinesAround(minesAround);
		}
		public int getBtnI() {
			return btnI;
		}
		public void setBtnI(int btnI) {
			this.btnI = btnI;
		}
		public int getBtnJ() {
			return btnJ;
		}
		public void setBtnJ(int btnJ) {
			this.btnJ = btnJ;
		}
		public Button getBtn() {
			//can be  = this = btn
			return btn;
		}
		public void setBtn(Button btn) {
			this.btn = btn;
		}
		
		public void updateText() {
			 if (isFlag()) {
				String flag = new StringBuilder().appendCodePoint(0X1F3C1).toString();
				btn.setText(flag);
			 }
			 else if (!isOpen()) {
				btn.setText(" ");
			}
			else if (IsMine())
				btn.setText("X");
			else
				btn.setText(minesAround==0?" ":minesAround+"");
		}
		public void open() {
			this.setOpen(true);
			this.setFlag(false);
			updateText();
		}

	}

