package ri.game2;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 400;
	public static final int HEIGHT = WIDTH / 4 * 3;
	public static final int SCALE = 2;
	public static final String NAME = "Game";
	public static final Dimension DIMENSIONS = new Dimension(WIDTH * SCALE,HEIGHT * SCALE);
	public JFrame frame;

	public Thread thread;

	public boolean running = false;
	public int tickCount = 0;

	public Game() {
		setMinimumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		setMaximumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		
		frame = new JFrame("Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this,BorderLayout.CENTER);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void init() {

	}// end of init

	public synchronized void start() {
		running = true;
		thread = new Thread(this, NAME + "_main");
		thread.start();

	}

	public synchronized void stop() {
		running = false;
		try {
			System.out.println("stop");
			thread.join(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	

	public void tick() {

	}// end of tick

	public void render() {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {

			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();

	}// end of render
	
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;

		int ticks = 0;
		int frames = 0;

		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		init();
		while (true) {
			while (running) {
				long now = System.nanoTime();
				delta += (now - lastTime) / nsPerTick;
				lastTime = now;
				boolean shouldRender = true;

				while (delta >= 1) {
					ticks++;
					tick();
					delta -= 1;
					shouldRender = true;
				}

				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (shouldRender) {
					frames++;
					render();
				}

				if (System.currentTimeMillis() - lastTimer >= 1000) {
					lastTimer += 1000;
					frames = 0;
					ticks = 0;
				}
			}

		}

	}// end of run

	
	public static void main(String[] args){
		new Game().start();
	}

}// end of class

