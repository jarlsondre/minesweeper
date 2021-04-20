package minesweeper.core;

public interface AdjacentListener {
	
	/**
	 * Metode som blir kalt av den observerte når den
	 * skal varsle sine nabotiles om at den har blitt åpnet.
	 * Observatøren åpnes og varsler sine lyttere dersom den 
	 * ikke har bomber rundt seg.
	 * */
	void adjacentOpened();
}
