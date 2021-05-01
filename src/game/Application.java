package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.Dinosaurs.*;
import game.grounds.*;

/**
 * The main class for the Jurassic World game.
 * @author Jinyeop
 * @author Maurya Gamage
 * @version 1.0.2
 */
public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(), new Egg(), new VendingMachine(), new Bush()); // Jinyeop
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		".....#######....................................................................",
		".....#_____#....................................................................",
		".....#_+___#....................................................................",
		".....###.###....................................................................",
		"........+.......................................................................",
		"......................................+++.......................................",
		".......................................++++.....................................",
		"...................................+++++........................................",
		".....................................++++++.....................................",
		"......................................+++.......................................",
		".....................................+++........................................",
		"................................................................................",
		"............+++.................................................................",
		".............+++++..............................................................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		GameMap gameMap = new DinosaurGameMap(groundFactory, map ); // Jinyeop
		((DinosaurGameMap)gameMap).growBushRandomly();	// Jinyeop
		world.addGameMap(gameMap);
		
		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(9, 4));
		
		// Place a pair of stegosaurs in the middle of the map
		gameMap.at(30, 12).addActor(new Stegosaur("Stegosaur-1","MALE"));
		gameMap.at(32, 12).addActor(new Stegosaur("Stegosaur-2","FEMALE"));
		gameMap.at(10, 12).addActor(new Brachiosaur("Brachiosaur-1","FEMALE"));
		gameMap.at(12, 12).addActor(new Brachiosaur("Brachiosaur-2","FEMALE"));
		gameMap.at(10, 14).addActor(new Brachiosaur("Brachiosaur-3","MALE"));
		gameMap.at(12, 14).addActor(new Brachiosaur("Brachiosaur-4","MALE"));

			
		world.run();
	}
}
