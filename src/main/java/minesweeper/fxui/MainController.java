package minesweeper.fxui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import minesweeper.core.Board;

public class MainController {
	
	// Core-komponenter
	private Board board;
	
	// FXML-komponenter
	@FXML
	VBox Vbox;
	
	public MainController() {
		this.board = new Board(10);
	}
	
	@FXML
    public void initialize() {
		for(int i = 0; i < this.board.getSize(); i++) {
			HBox Hbox = new HBox();
			for(int j = 0; j < this.board.getSize(); j++) {
				// TODO Legg til event-listener
				Button button = new Button();
				button.setPrefHeight(50);
				button.setPrefWidth(50);
				Hbox.getChildren().add(button);
			}
			this.Vbox.getChildren().add(Hbox);
		}
	} 

    @FXML
    private void handleButton() {
    	//TODO Implementer
    }
}
