package minesweeper.savehandling;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class GameSaveHandlerTest {

    @Test
    public void testSaveAndLoad() {
       SaveHandler gameSaveHandler = new GameSaveHandler();
       ((GameSaveHandler) gameSaveHandler).setFileName("test_file.txt");
       String state = "player1: 40,\nplayer2: 45,";
       try {
           // Tester at det man lagrer og henter ut igjen er likt
           gameSaveHandler.saveState(state);
           String loadedState = gameSaveHandler.loadState();
           Assertions.assertEquals(state, loadedState);

           // Tester at dersom man lagrer noe på nytt så fungerer det fortsatt
           state = "player1: 30,\nplayer2: 70,\nplayer3: 80,";
           gameSaveHandler.saveState(state);
           loadedState = gameSaveHandler.loadState();
           Assertions.assertEquals(state, loadedState);
       }
       catch(IOException e) {
          Assertions.fail("Skriving og lesing av fil feilet");
       }
    }

    @Test
    public void testLoad() {
        // Tester at det kastes et unntak dersom filen ikke eksisterer
        SaveHandler gameSaveHandler = new GameSaveHandler();
        ((GameSaveHandler) gameSaveHandler).setFileName("tullefil");
        Assertions.assertThrows(IOException.class, () -> {
            gameSaveHandler.loadState();
        });
    }
    
    @Test
    public void testSetFileName() {
    	// Tester at ugyldige filnavn ikke funker
    	GameSaveHandler handler = new GameSaveHandler();
    	Assertions.assertThrows(IllegalArgumentException.class,
    			() -> handler.setFileName(""));
    	Assertions.assertThrows(IllegalArgumentException.class,
    			() -> handler.setFileName(null));
    	// Tester at et gyldig filnavn funker
    	handler.setFileName("Gyldig");
    }

    @AfterEach
    public void deleteFile() {
        // Sletter filen som testen bruker
        Path path = Paths.get(System.getProperty("user.home"), "test_file.txt");
        File file = path.toFile();
        file.delete();
    }

}
