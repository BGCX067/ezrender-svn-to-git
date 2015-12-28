package org.ezengine.util;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FRONT;
import static org.lwjgl.opengl.GL11.GL_RGB;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glReadBuffer;
import static org.lwjgl.opengl.GL11.glReadPixels;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.ezengine.GL.Renderer;
import org.ezengine.util.x2d.physics.Physics2D;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window {

	public static boolean isClosing = false;
	protected static boolean isDebugEnabled = false, isPhysicsEnabled = false;
	public static final int DEBUG = 1;
	public static final int MOUSE = 2;
	public static final int MOUSE_GRAB = 4;
	public static final int KEYBOARD = 8;
	public static final int VSYNC = 16;
	public static final int FULLSCREEN = 32;
	public static final int PHYSICS_2D = 64;
	private static int fpsCap = Display.getDesktopDisplayMode().getFrequency(), scNum = 0;
	private static Point2D.Double gl2DSize;
	private static int fps, displayFPS;
	private static long lastFPS;

	public static void create(String title, int width, int height, int fps, int options) throws Exception {
		if (fps > 0) {
			fpsCap = fps;
		}
		if (Bitwise.test(DEBUG, options)) isDebugEnabled = true;

		if (Bitwise.test(FULLSCREEN, options)) {
			Display.setDisplayModeAndFullscreen(Display.getDisplayMode());
		} else {
			Display.setDisplayMode(new DisplayMode(width, height));
		}
		gl2DSize = new Point2D.Double(width, height);
		Display.setTitle(title);
		Display.setSwapInterval(0);
		Display.setVSyncEnabled(Bitwise.test(VSYNC, options));
		Display.create();
		if (Bitwise.test(KEYBOARD, options)) {
			Keyboard.create();
		}
		if (Bitwise.test(MOUSE, options)) {
			Mouse.create();
			if (MOUSE_GRAB == (options & MOUSE_GRAB)) {
				Mouse.setGrabbed(true);
			}
		}
		if (Bitwise.test(PHYSICS_2D, options)) {
			Physics2D.initialize(fpsCap);
			isPhysicsEnabled = true;
		}
		Renderer.initRenderer(width, height);
		lastFPS = Util.getTime();
	}

	public static int setIcon(String path) throws Exception {
		return Display.setIcon(TextureLoader.loadIcons(path));
	}

	public static int setIcon(ByteBuffer[] icons) {
		return Display.setIcon(icons);
	}

	public static void destroy() {
		Display.destroy();
	}

	public static boolean update() {
		return update(fpsCap);
	}

	public static boolean update(int fpsCap) {
		Display.update();
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		if (fpsCap > 0) {
			Display.sync(fpsCap);
		}
		if (isDebugEnabled) {
			if (Util.getTime() - lastFPS > 1000) {
				displayFPS = fps;
				fps = 0;
				lastFPS += 1000;
			}
			fps++;
		}
		if (isPhysicsEnabled) Physics2D.update();
		return !(isClosing = Display.isCloseRequested());
	}

	public static boolean updateNoClear() {
		return updateNoClear(fpsCap);
	}

	public static boolean updateNoClear(int fpsCap) {
		Display.update();
		glLoadIdentity();
		if (fpsCap > 0) {
			Display.sync(fpsCap);
		}
		if (isDebugEnabled) {
			if (Util.getTime() - lastFPS > 1000) {
				displayFPS = fps;
				fps = 0;
				lastFPS += 1000;
			}
			fps++;
		}
		if (isPhysicsEnabled) Physics2D.update();
		return !(isClosing = Display.isCloseRequested());
	}

	public static void capture() {
		int width = Display.getWidth();
		int height = Display.getHeight();

		ByteBuffer pixBuf = BufferUtils.createByteBuffer(height * width * 3);
		BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		int R, G, B;
		pixBuf.position(0);
		glReadBuffer(GL_FRONT);
		glReadPixels(0, 0, width, height, GL_RGB, GL_UNSIGNED_BYTE, pixBuf);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				R = pixBuf.get((x + width * y) * 3) & 0xFF;
				G = pixBuf.get((x + width * y) * 3 + 1) & 0xFF;
				B = pixBuf.get((x + width * y) * 3 + 2) & 0xFF;
				out.setRGB(x, height - (y + 1), 0xFF << 24 | R << 16 | G << 8 | B);
			}
		}
		try {
			ImageIO.write(out, "png", new File(System.getProperty("user.home") + "\\" + scNum++ + ".png"));
		} catch (Exception e) {}
	}

	public static Point2D.Double getGL2DSize() {
		return gl2DSize;
	}

	public static void setGL2DSize(double w, double h) {
		gl2DSize = new Point2D.Double(w, h);
	}

	public static int getFPS() {
		return displayFPS;
	}
}
