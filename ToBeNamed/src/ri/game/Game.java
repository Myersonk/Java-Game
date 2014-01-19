package ri.game;

public class Game {

	
	
	public Game(){
		System.out.println("hi");	
	}
	
	public void start(){
		System.out.println("hello");	
		System.out.println("game created");
	}
	
	
	public static void Main(String[] args){
		Game game = new Game();
		game.start();
	}
}
