package minesweeper.fxui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import minesweeper.core.Board;
import minesweeper.core.BombTile;
import minesweeper.core.Tile;

public class MainController {
	
	
	
	// TODO
	// Det er feil i enten koden i kontrolleren eller i 
	// koden i board. Ser ikke ut som observatør observert er impl riktig
	// Riktig ruter åpnes ikke under kjøring.
	
	
	
	
	// Core-komponenter
	private Board board;
	private int openedTiles;
	
	// FXML-komponenter
	@FXML
	VBox Vbox;
	
	public MainController() {
		this.board = new Board(10);
	}
	
	@FXML
    public void initialize() {
		this.makeBoard();
	}
	
	private void makeBoard() {
		System.out.println(this.board);
		this.Vbox.getChildren().clear();
		for(int i = 1; i < this.board.getSize() + 1; i++) {
			HBox Hbox = new HBox();
			for(int j = 1; j < this.board.getSize() + 1; j++) {
				Button button = new Button();
				// Legger til eventhandler
				Tile tile = this.board.getTile(i, j);
				if(tile.isOpened()) {
					button.setText(tile.toString());
				}
				else {
					button.setOnAction(e -> this.handleButton((Button) e.getSource()));					
				}
				button.setId("" + i + j);
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

    @FXML
    private void handleButton(Button button) {
    	String id = button.getId();
    	int x = Integer.parseInt(String.valueOf(id.charAt(0)));
    	int y = Integer.parseInt(String.valueOf(id.charAt(1)));
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
