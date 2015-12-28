package org.ezengine.util;

import static org.lwjgl.opengl.GL11.GL_CLAMP;
import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import net.sf.image4j.codec.ico.ICODecoder;

import org.lwjgl.BufferUtils;

public class TextureLoader {

	public static int GL_TEXTURE_MIN_FILTER_SETTING = GL_LINEAR;
	public static int GL_TEXTURE_MAG_FILTER_SETTING = GL_NEAREST;
	public static int GL_TEXTURE_WRAP_S_SETTING = GL_CLAMP;
	public static int GL_TEXTURE_WRAP_T_SETTING = GL_CLAMP;

	public static int loadTexture(String name) throws Exception {
		return loadTexture(name, 0);
	}

	public static ByteBuffer[] loadIcons(String path) throws Exception {
		BufferedImage[] icons = ICODecoder.read(TextureLoader.class.getResource(path).openStream()).toArray(new BufferedImage[0]);
		ByteBuffer[] iconBuffers = new ByteBuffer[icons.length];
		for (int ii = 0; ii < icons.length; ii++) {
			int[] pixels = icons[ii].getRGB(0, 0, icons[ii].getWidth(), icons[ii].getHeight(), null, 0, icons[ii].getHeight());
			iconBuffers[ii] = BufferUtils.createByteBuffer(pixels.length * 4);
			for (int i = 0; i < pixels.length; i++) {
				iconBuffers[ii].put((byte) (pixels[i] >> 16 & 0xFF));
				iconBuffers[ii].put((byte) (pixels[i] >> 8 & 0xFF));
				iconBuffers[ii].put((byte) (pixels[i] & 0xFF));
				iconBuffers[ii].put((byte) (pixels[i] >> 24 & 0xFF));
			}
			iconBuffers[ii].flip();
		}
		return iconBuffers;
	}

	public static int loadTexture(String name, int alpha) throws Exception {
		BufferedImage image = ImageIO.read(TextureLoader.class.getResource(name));
		return loadGLTexture(image, alpha);
	}

	public static int loadGLTexture(BufferedImage image, int alpha) throws Exception {
		if (image == null) { return -1; }
		int textureID = 0;
		int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getHeight());
		ByteBuffer buffer = BufferUtils.createByteBuffer(pixels.length * 4);
		for (int i = 0; i < pixels.length; i++) {
			if (pixels[i] == alpha) pixels[i] = 0x00000000; /*Make alpha seethrough*/
			buffer.put((byte) (pixels[i] >> 16 & 0xFF));
			buffer.put((byte) (pixels[i] >> 8 & 0xFF));
			buffer.put((byte) (pixels[i] & 0xFF));
			buffer.put((byte) (pixels[i] >> 24 & 0xFF));
		}
		buffer.flip();
		textureID = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, textureID);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER_SETTING);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER_SETTING);

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_TEXTURE_WRAP_S_SETTING);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_TEXTURE_WRAP_T_SETTING);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
		glBindTexture(GL_TEXTURE_2D, 0);
		return textureID;
	}

	public static double getTileX(int x, int w) {
		if (x == w) return 1.0;
		return (1.0 / w) * x;
	}

	public static double getTileY(int y, int h) {
		if (y == h) return 1.0;
		return (1.0 / h) * y;
	}
}
