package ri.game;

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

	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 3;
	public static final String NAME = "Game";
	public static final Dimension DIMENSIONS = new Dimension(WIDTH * SCALE,
			HEIGHT * SCALE);
	public JFrame frame;

	public Thread thread;

	public boolean running = false;
	public int tickCount = 0;

	public Game() {
	}// constructor

	public void init() {// all initial monsters are created here

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

}// end of class

