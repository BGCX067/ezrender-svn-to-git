package org.ezengine.util.x2d;

import static org.lwjgl.opengl.GL11.*;

import org.ezengine.util.TextureLoader;
import org.ezengine.util.Window;

public class Graphics {
	private static TextRenderer text;
	public static final int ALIGN_TOP_LEFT = 0, ALIGN_TOP_MIDDLE = 1, ALIGN_TOP_RIGHT = 2, ALIGN_CENTER_LEFT = 3, ALIGN_CENTER = 4, ALIGN_CENTER_RIGHT = 5, ALIGN_BOTTOM_LEFT = 6, ALIGN_BOTTOM_MIDDLE = 7, ALIGN_BOTTOM_RIGHT = 8;

	public static void drawTexture(int texture, int tile_x, int tile_y, int tile_w, int tile_h, int size_w, int size_h, double x, double y, double w, double h) {

		glBindTexture(GL_TEXTURE_2D, texture);
		glBegin(GL_TRIANGLES);
		{
			glTexCoord2d(TextureLoader.getTileX(tile_x, size_w), TextureLoader.getTileY(tile_y, size_h));
			glVertex2d(x, y);
			glTexCoord2d(TextureLoader.getTileX(tile_x, size_w), TextureLoader.getTileY(tile_y + tile_h, size_h));
			glVertex2d(x, y + h);
			glTexCoord2d(TextureLoader.getTileX(tile_x + tile_w, size_w), TextureLoader.getTileY(tile_y + tile_h, size_h));
			glVertex2d(x + w, y + h);
			glTexCoord2d(TextureLoader.getTileX(tile_x + tile_w, size_w), TextureLoader.getTileY(tile_y + tile_h, size_h));
			glVertex2d(x + w, y + h);
			glTexCoord2d(TextureLoader.getTileX(tile_x + tile_w, size_w), TextureLoader.getTileY(tile_y, size_h));
			glVertex2d(x + w, y);
			glTexCoord2d(TextureLoader.getTileX(tile_x, size_w), TextureLoader.getTileY(tile_y, size_h));
			glVertex2d(x, y);
		}
		glEnd();

	}

	public static void drawTexture(int texture, double x, double y, double w, double h) {

		glBindTexture(GL_TEXTURE_2D, texture);
		glBegin(GL_TRIANGLES);
		{
			glTexCoord2d(0, 0);
			glVertex2d(x, y);
			glTexCoord2d(0, 1);
			glVertex2d(x, y + h);
			glTexCoord2d(1, 1);
			glVertex2d(x + w, y + h);
			glTexCoord2d(1, 1);
			glVertex2d(x + w, y + h);
			glTexCoord2d(1, 0);
			glVertex2d(x + w, y);
			glTexCoord2d(0, 0);
			glVertex2d(x, y);
		}
		glEnd();
		glBindTexture(GL_TEXTURE_2D, 0);

	}

	public static void drawList(int list, double x, double y) {

		if (x != 0 && y != 0) glTranslated(x, y, 0);
		glCallList(list);
		if (x != 0 && y != 0) glTranslated(-x, -y, 0);

	}

	public static void drawList(int list, double x, double y, double rot) {

		if (x != 0 && y != 0) glTranslated(x, y, 0);
		if (rot != 0) glRotated(rot, 0, 0, 1);
		glCallList(list);
		if (x != 0 && y != 0) glTranslated(-x, -y, 0);

	}

	public static void drawString(String str, double sz, double x, double y, double z) {

		text.drawString(str, sz, x, y, z);

	}

	public static void drawString(String str, int align) {

		final double width = Window.getGL2DSize().x;
		final double height = Window.getGL2DSize().y;
		switch (align) {
			case ALIGN_TOP_LEFT:
				drawString(str, 1, 0, 0, 0);
			break;
			case ALIGN_TOP_MIDDLE:
				drawString(str, 1, (width / 2) - (TextRenderer.FONT_X_SIZE * (str.length() / 2.0)), 0, 0);
			break;
			case ALIGN_TOP_RIGHT:
				drawString(str, 1, width - (TextRenderer.FONT_X_SIZE * str.length()), 0, 0);
			break;
			case ALIGN_CENTER_LEFT:
				drawString(str, 1, 0, (height / 2) - (TextRenderer.FONT_Y_SIZE), 0);
			break;
			case ALIGN_CENTER:
				drawString(str, 1, (width / 2.0) - ((TextRenderer.FONT_X_SIZE * (str.length() / 2.0))), (height / 2) - (TextRenderer.FONT_Y_SIZE / 2.0), 0);
			break;
			case ALIGN_CENTER_RIGHT:
				drawString(str, 1, width - (TextRenderer.FONT_X_SIZE * str.length()), (height / 2) - (TextRenderer.FONT_Y_SIZE / 2.0), 0);
			break;
			case ALIGN_BOTTOM_LEFT:
				drawString(str, 1, 0, height - (TextRenderer.FONT_Y_SIZE / 2.0), 0);
			break;
			case ALIGN_BOTTOM_MIDDLE:
				drawString(str, 1, (width / 2) - (TextRenderer.FONT_X_SIZE * (str.length() / 2.0)), height - (TextRenderer.FONT_Y_SIZE / 2.0), 0);
			break;
			case ALIGN_BOTTOM_RIGHT:
				drawString(str, 1, width - (TextRenderer.FONT_X_SIZE * str.length()), height - (TextRenderer.FONT_Y_SIZE / 2.0), 0);
			break;
			default:
			break;
		}

	}

	public static void initialize() {
		text = new TextRenderer();
	}

	public static void rotate(double x, double y, double z) {
		if (x != 0) glRotated(x, 1, 0, 0);
		if (y != 0) glRotated(y, 0, 1, 0);
		if (z != 0) glRotated(z, 0, 0, 1);
	}

}
