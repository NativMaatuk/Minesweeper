package application;

import java.util.Iterator;

import javafx.css.Style;
import javafx.event.ActionEvent;
import javafx.event.EventDispatcher;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import mines.Mines;

public class Controller {
	protected Label getStart() {
		return out;
	}
	private static final ActionEvent ActionEvent = null;

	@FXML
	private TextArea width;

	@FXML
	private TextArea hight;

	@FXML
	private TextArea mines;

	@FXML
	private Button start;
	
	@FXML
	private Label out;

	@FXML
	private GridPane grid;
	
	private MediaPlayer  mediaplayer;
	public void setMediaWin() {
		if(mediaplayer != null)
			mediaplayer.stop();
		//Media musicFile = new Media("file:///"+System.getProperty("user.dir").replace('\\', '/')+"/sounds/Snoop-Dogg.mp3");
		Media musicFile = new Media(getClass().getClassLoader().getResource("Snoop-Dogg.mp3").toString());
		mediaplayer = new MediaPlayer(musicFile);
		mediaplayer.setVolume(0.07);
		mediaplayer.setAutoPlay(true);
		
	}
	public void setDisqualified() {
		if(mediaplayer != null)
			mediaplayer.stop();
		//Media musicFile = new Media("file:///"+System.getProperty("user.dir").replace('\\', '/')+"/sounds/Disqualified.mp3");
		Media musicFile = new Media(getClass().getClassLoader().getResource("Disqualified.mp3").toString());
		mediaplayer = new MediaPlayer(musicFile);
		mediaplayer.setVolume(0.07);
		mediaplayer.setAutoPlay(true);
	}
	public void setStart() {
		if(mediaplayer != null)
			mediaplayer.stop();
		//Media musicFile = new Media("file:///"+System.getProperty("user.dir").replace('\\', '/')+"/sounds/backgroundMusic.mp3");
		Media musicFile = new Media(getClass().getClassLoader().getResource("backgroundMusic.mp3").toString());
		mediaplayer = new MediaPlayer(musicFile);
		mediaplayer.setVolume(0.07);
		mediaplayer.setAutoPlay(true);
	}
	//can be add 3 smiles
	// (◔_◔)
	// ε(´סּ︵סּ`)з
	// (̿▀̿‿ ̿▀̿ ̿)
	@FXML
	void buildGame(ActionEvent event) throws InterruptedException {
		//If the player is playing from the previous game
		if(mediaplayer != null)
			mediaplayer.stop();
		setStart();	
		try {
			int w = (int) Integer.valueOf(width.getText());
			int h = (int) Integer.valueOf(hight.getText());
			int m = (int) Integer.valueOf(mines.getText());
			if (w <= 0 || h <= 0 || m <= 0) 
				throw new Exception("Inavlid Input");
			out.setText("(̿▀̿‿ ̿▀̿ ̿)");
			out.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(12), new Insets(0))));
			startGame(w, h, m);
		} catch (Exception e) {
			System.err.println(e.getMessage());
// e.printStackTrace();
// Eror input Invalid
			out.setText("Inavlid Input");
			if(mediaplayer != null)
				mediaplayer.stop();
			out.setBackground(new Background(new BackgroundFill(Color.INDIANRED, new CornerRadii(12), new Insets(0))));
		}
	}
	
	private boolean Disqualified = false;
	private Stage stage;

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	private int getBtnI(String btn) {
		return Integer.valueOf(btn.substring(0, btn.indexOf('|')));
	}

	private int getBtnJ(String btn) {
		return Integer.valueOf(btn.substring(btn.indexOf('|') + 1));
	}
	
	void startGame(int w, int h, int m) {
		Disqualified = false;
// create Tile array [][] = [i , j , button (the button is not initiolized)]
		Tile index[][] = new Tile[w][h];
// clear grid
		grid.getChildren().clear();
		ColumnConstraints c = new ColumnConstraints();
		c.setHalignment(HPos.CENTER);
// create new Minesweeper
		Mines nm = new Mines(w, h, m);
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				index[i][j] = new Tile(i, j, nm.getPoint(i, j).getMinesAround());
// debug index button
//Button b = new Button(String.valueOf(i + "," + j));
				Button b = new Button();
				// set Id
				b.setId(String.valueOf(i + "|" + j));
//change font if you want
				//Font font = Font.font("Courier New", FontWeight.BOLD, 16);
				//b.setFont(font);
//can be set the style from css page..
				//b.getStyleClass().add("btn");
				b.setBackground(new Background(new BackgroundFill(Color.BISQUE, new CornerRadii(15), new Insets(0))));

//the event start if the user click on the left side in the mouse 
				b.setOnMousePressed(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						if(!Disqualified) {
						Button tmp = (Button) e.getSource();
						int i = getBtnI(tmp.getId());
						int j = getBtnJ(tmp.getId());
						
						if (index[i][j].isOpen()) 
							return;
							
						if (e.getButton() == MouseButton.SECONDARY) {
							index[i][j].setFlag(!index[i][j].isFlag());
							nm.toggleFlag(i, j);
							index[i][j].updateText();
							return;
						}

						if (index[i][j].isFlag())
							return;
//e.getClickCount();	
						if (!nm.open(i, j)) {
							String bomb = new StringBuilder().appendCodePoint(0x1F4A3).toString();
							index[i][j].getBtn().setText(bomb);
							setDisqualified();
							out.setText("you lose");
							out.setBackground(
							new Background(new BackgroundFill(Color.RED, new CornerRadii(15), new Insets(0))));
							index[i][j].getBtn().setBackground(
							new Background(new BackgroundFill(Color.RED, new CornerRadii(15), new Insets(0))));
//you lose
							Disqualified = true;
							
						} else {
							index[i][j].open();
							index[i][j].getBtn().setBackground(new Background(
							new BackgroundFill(Color.ALICEBLUE, new CornerRadii(15), new Insets(0))));
							Iterator<Integer> it_i, it_j;
							it_i = nm.getOpen_i().iterator();
							it_j = nm.getOpen_j().iterator();
							while (it_i.hasNext()) {
								int x = it_i.next();
								int y = it_j.next();
								index[x][y].open();
								index[x][y].getBtn().setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, new CornerRadii(15), new Insets(0))));
							}
							nm.emptyToOpen();
							if(nm.isDone()) {
								setMediaWin();
								out.setText("you win");
								out.setBackground(
								new Background(new BackgroundFill(Color.YELLOWGREEN, new CornerRadii(15), new Insets(0))));
								Disqualified = true;
							}
						}
						}

					}
				});
				b.setPrefSize(50, 50);
// add to Tile button
				index[i][j].setBtn(b);
// add button to grid from Tile
				grid.add(index[i][j].getBtn(), j, i);
				GridPane.setMargin(index[i][j].getBtn(), new Insets(2));
			}
		}
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(40));
		BackgroundFill bf = new BackgroundFill(Color.CADETBLUE, null, null);
		Background bg = new Background(bf);
		grid.setBackground(bg);
// initionlize resize stage
		stage.sizeToScene();
	}
}