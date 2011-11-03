/**
* DungeonGenerator.java
* Text mode Dungeon generator
* @autor pello altadill
*/

import java.util.Random;

public class DungeonGenerator {

	private int _height = 25;
	private int _width = 80;
	private char _wall = '#';
	private char _default = '.';
	private int _dungeon[][];
	private int _densityCoef = 250;
	private int _max_rooms = 8;
	/**
	* DungeonGenerator
	* Constructor
	*/
	DungeonGenerator() {
		_max_rooms = (_height * _width) / _densityCoef; 
	}
	
	private void initialRooms () {
	}

	/**
	* main method
	*
	*/	
	public static void main (String args[]) {
		System.out.println("DungeonGenerator v0.1");
	}
}