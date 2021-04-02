package minesweeper.fxui;

import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.SECONDS;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import minesweeper.core.Board;
import minesweeper.core.BombTile;
import minesweeper.core.Tile;

public class MainController {
	
	// Core-komponenter
	private Board board;
	private LocalTime start;

	// FXML-komponenter
	@FXML
	VBox Vbox;

	@FXML
	Label totalBombsLabel;
	
	@FXML
	Label text;

	/**
	 * konstruktør for kontrolleren. 
	 * Lager brettet i core.
	 * */
	public MainController() {
		this.board = new Board(10);
	}
	
	/**
	 * initialiserer gui-brettet med knapper
	 * */
	@FXML
    public void initialize() {
		this.initializeBoard();
		this.start = LocalTime.now();
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
		int totalBombs = this.board.getSize()*this.board.getSize() - this.board.getSafeTilesAmount();
		this.text.setText("Antall bomber:");
		this.totalBombsLabel.setVisible(true);
		totalBombsLabel.setText(Integer.toString(totalBombs));
	}

	/**
	 * Hjelpemetode for å oppdatere gui-brettet
	 * */
	private void updateBoard() {
	    // Går gjennom hver tile og sjekker om den har blitt åpnet.
		int openedTiles = 0;
		for (Tile tile : board) {
			if (tile.isOpened()) {
				openedTiles += 1;
				int x_cor = tile.getX();
				int y_cor = tile.getY();
				Button tileButton = (Button) this.Vbox.lookup("#" + x_cor + "," + y_cor);
				tileButton.setText(tile.toString());
				tileButton.setDisable(true);
				// Dersom man åpner en BombTile taper man
				if (tile instanceof BombTile) {
					this.gameOver();
					return;
				}
			}
		}

		// Dersom man har åpnet riktig antall tiles har man vunnet
		if (this.board.getSafeTilesAmount() == openedTiles) {
			this.winGame();
			return;
		}
	}

	/**
	 * Hjelpemetode for å åpne tiles
	 * @param button knappen som har blitt trykt på
	 * */
	private void handleTileClick(Button button) {
		String[] id = button.getId().split(",");
		int x_cor = Integer.parseInt(id[0]);
		int y_cor = Integer.parseInt(id[1]);
		Tile tile = board.getTile(x_cor, y_cor);
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
	 * Hjelpemetode som lager et nytt game
	 * */
	@FXML
	private void handleNewGame() {
		this.Vbox.getChildren().clear();
		this.board = new Board(10);
		this.initializeBoard();
		this.start = LocalTime.now();
	}
	
	/**
	 * Hjelpemetode for å håndtere at man trykker på en bombe
	 * */
	private void gameOver() {
		this.disableAll();
		long difference = this.start.until(LocalTime.now(), SECONDS);
		this.text.setText("Du tapte etter:\n" + difference + " sekunder");
		this.totalBombsLabel.setVisible(false);
	}
	
	/**
	 * Hjelpemetode for å håndtere at man vinner spillet
	 * */
	private void winGame() {
		this.disableAll();
		long difference = this.start.until(LocalTime.now(), SECONDS);
		this.text.setText("Du vant etter:\n" + difference + " sekunder");
		this.totalBombsLabel.setVisible(false);
	}
}
