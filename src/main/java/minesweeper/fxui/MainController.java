package minesweeper.fxui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import minesweeper.core.Board;
import minesweeper.core.BombTile;
import minesweeper.core.SafeTile;
import minesweeper.core.Tile;

public class MainController {
	
	// Core-komponenter
	private Board board;
	private int openedTiles;
	
	// FXML-komponenter
	@FXML
	VBox Vbox;
	
	/**
	 * konstruktør for kontrolleren. 
	 * Lager brettet i core.
	 * */
	public MainController() {
		this.board = new Board(10);
	}
	
	/**
	 * initialiserer brettet med knapper
	 * */
	@FXML
    public void initialize() {
		this.makeBoard();
	}
	
	private void makeBoard() {
		// System.out.println(this.board);
		this.Vbox.getChildren().clear();
		for(int i = 1; i < this.board.getSize() + 1; i++) {
			HBox Hbox = new HBox();
			for(int j = 1; j < this.board.getSize() + 1; j++) {
				Button button = new Button();
				
				// Legger til eventhandler
				Tile tile = this.board.getTile(j, i);
				if(tile.isOpened()) {
					// TODO Endre dette?
					if(tile instanceof SafeTile && ((SafeTile) tile).getSurroundingBombAmount() > 0) {
						button.setText(((SafeTile) tile).getSurroundingBombAmount() + "");
					}
					else {
						button.setText(tile.toString());						
					}
				}
				else {
					button.setOnAction(e -> this.handleButton((Button) e.getSource()));					
				}
				button.setId(j + "," + i);
				button.setPrefHeight(50);
				button.setPrefWidth(50);
				Hbox.getChildren().add(button);
			}
			this.Vbox.getChildren().add(Hbox);
		}
	}
	
	private void gameOver() {
		//TODO
	}
	
	private void winGame() {
		//TODO
	}

	/**
	 * Håndterer knappetrykk.
	 * Åpner tilsvarende tile i core-brettet.
	 * @param button knappen som har blitt trykket på
	 * */
    @FXML
    private void handleButton(Button button) {
    	String[] id = button.getId().split(",");
    	int x = Integer.parseInt(id[0]);
    	int y = Integer.parseInt(id[1]);
    	// System.out.println("(" + id[0] + ", " + id[1] + ")");
    	Tile tile = this.board.getTile(x, y);
    	if(tile instanceof BombTile) {
			this.gameOver();
		}
    	tile.open();
    	this.openedTiles += 1;
    	if(this.board.getSafeTilesAmount() == this.openedTiles) {
    		this.winGame();
    	}
    	this.makeBoard();
    }
}
