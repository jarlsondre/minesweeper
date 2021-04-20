package minesweeper.fxui;

import java.util.Collection;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import minesweeper.core.Board;
import minesweeper.core.Games;
import minesweeper.core.Player;
import minesweeper.core.Tile;

public class MainController {
	
	// Core-komponenter
	private Board board;
	private int time;
	private Games games;
	
	private final int boardSize = 10;

	// FXML-komponenter
	@FXML
	private VBox Vbox;

	@FXML
	private Label totalBombsLabel;
	
	@FXML
	private Label text;

	@FXML
	private ScrollPane highscoreScrollPane;
	
	@FXML 
	private TextField username;
	
	@FXML
	private Label usernameLabel;
	
	@FXML
	private Button submit;

	/**
	 * konstruktør for kontrolleren. 
	 * Lager brettet i core.
	 * */
	public MainController() {
		this.board = new Board(boardSize);
		this.games = new Games();
		this.time = -1;
	}
	
	/**
	 * initialiserer gui-brettet med knapper
	 * */
	@FXML
    public void initialize() {
		this.initializeBoard();
		this.addScrollPaneElements();
		this.board.startTimer();
	}
	
	/**
	 * Legger til alle spillerene med tilhørende highscore
	 * i highscoreScrollPane.
	 */
	public void addScrollPaneElements() {
		this.highscoreScrollPane.setContent(new VBox());
		VBox highscoreBox = (VBox) highscoreScrollPane.getContent();
		Collection<Player> highscoreList = this.games.getPlayersResults();
		for (Player hsElem : highscoreList) {
			highscoreBox.getChildren().add(new Label(hsElem.getName() + ": " + hsElem.getHighScoreTime()));
		}
		highscoreBox.setSpacing(10);
	}
	
	/**
	 * Hjelpemetode for å initialisere gui-brettet med knapper
	 * */
	private void initializeBoard() {
		for (int i = 1; i < this.board.getSize() + 1; i++) {
			HBox hBox = new HBox();
			for (int j = 1; j < this.board.getSize() + 1; j++) {
				Button tileButton = new Button();
				tileButton.setId(j + "," + i);
				tileButton.setPrefHeight(50);
				tileButton.setPrefWidth(50);
				tileButton.setOnMouseClicked(e -> {
					if (e.getButton() == MouseButton.SECONDARY) {
						if (tileButton.getText().equals("F")) {
							tileButton.setText("");
						} else {
							tileButton.setText("F");
						}
					}
					else {
						this.handleTileClick((Button) e.getSource());
					}
				});

				hBox.getChildren().add(tileButton);
			}
			this.Vbox.getChildren().add(hBox);
		}
		this.text.setText("Antall bomber:");
		this.totalBombsLabel.setVisible(true);
		totalBombsLabel.setText(Integer.toString(this.board.getBombAmount()));
	}

	/**
	 * Hjelpemetode for å oppdatere gui-brettet
	 * */
	private void updateBoard() {
	    // Går gjennom hver tile og sjekker om den har blitt åpnet.
		for (Tile tile : board) {
			if (tile.isOpened()) {
				int xCor = tile.getX();
				int yCor = tile.getY();
				Button tileButton = (Button) this.Vbox.lookup("#" + xCor + "," + yCor);
				tileButton.setText(tile.toString());
				tileButton.setDisable(true);
			}
		}

		// Sjekker om spillet har blitt vunnet og kaller riktig metode
		if (this.board.checkGameWon()) {
			this.winGame();
		}
		else if (this.board.checkGameLost()) {
			this.gameOver();
		}
	}

	/**
	 * Hjelpemetode for å åpne tiles
	 * @param button knappen som har blitt trykt på
	 * */
	private void handleTileClick(final Button button) {
		String[] id = button.getId().split(",");
		int xCor = Integer.parseInt(id[0]);
		int yCor = Integer.parseInt(id[1]);
		Tile tile = board.getTile(xCor, yCor);
		tile.open();
		this.updateBoard();
	}

	/**
	 * Hjelpemetode for å deaktivere alle gui-knappene
	 * */
	private void disableAll() {
		for (Node vboxChild : this.Vbox.getChildren()) {
			HBox hbox = (HBox) vboxChild;
			for (Node hboxChild : hbox.getChildren()) {
				Button button = (Button) hboxChild;
				button.setDisable(true);
			}
		}
	}

	/**
	 * Metode som håndterer at brukeren trykker på Submit. Registrerer
	 * spillet dersom brukeren har vunnet og har et gyldig brukernavn
	 */
	@FXML
	private void handleSubmitGame() {
		if(this.board.checkGameWon()) {
			throw new IllegalStateException("Må ha vunnet et spill før man kan registrere spillet");
		}
		if(this.username.getText().isBlank()) {
			this.usernameLabel.setText("Du må skrive inn et gyldig brukernavn!");
			return;
		}
		String username = this.username.getText();
		this.games.registerPlay(username, this.time);
		this.games.saveGames();
		this.addScrollPaneElements();
		this.handleNewGame();
	}

	/**
	 * Hjelpemetode som lager et nytt game
	 * */
	@FXML
	private void handleNewGame() {
		this.Vbox.getChildren().clear();
		this.board = new Board(boardSize);
		this.initializeBoard();
		this.board.startTimer();
		this.time = -1;
		this.usernameLabel.setVisible(false);
		this.username.setVisible(false);
		this.submit.setVisible(false);
	}

	/**
	 * Hjelpemetode for å håndtere at man trykker på en bombe
	 * */
	private void gameOver() {
		this.disableAll();
		this.time = this.board.stopTimer();
		this.text.setText("Du tapte etter:\n" + this.time + " sekunder");
		this.totalBombsLabel.setVisible(false);
	}

	/**
	 * Hjelpemetode for å håndtere at man vinner spillet
	 * */
	private void winGame() {
		this.disableAll();
		this.time = this.board.stopTimer();
		this.text.setText("Du vant etter:\n" + this.time + " sekunder");
		this.totalBombsLabel.setVisible(false);
		this.usernameLabel.setVisible(true);
		this.username.setVisible(true);
		this.submit.setVisible(true);
	}
}
