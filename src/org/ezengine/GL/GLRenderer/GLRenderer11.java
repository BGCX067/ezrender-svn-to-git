package org.ezengine.GL.GLRenderer;

import static org.lwjgl.opengl.GL11.*;

import org.ezengine.util.x3d.Model;

public class GLRenderer11 implements GLRenderer {

	public void render(Model m, int mode) {
		glEnableClientState(GL_VERTEX_ARRAY);
		if (m.isColored()) {
			glEnableClientState(GL_COLOR_ARRAY);
		}
		if (m.isTextured()) {
			glEnableClientState(GL_TEXTURE_COORD_ARRAY);
		}
		glVertexPointer(3, 3 << 2, m.getModel());
		if (m.isColored()) {
			if (m.isRGBA()) {
				glColorPointer(4, 3 << 2, m.getColor());
			} else {
				glColorPointer(3, 3 << 2, m.getColor());
			}
		}
		if (m.isTextured()) {
			glBindTexture(GL_TEXTURE_2D, m.getTexture());
			glTexCoordPointer(2, 3 << 2, m.getTextureCoords());
		}
		if (m.isIndexed()) {
			glDrawElements(mode, m.getIndices());
		} else {
			glDrawArrays(mode, 0, m.getVertexNum() / 3);
		}
		glDisableClientState(GL_VERTEX_ARRAY);
		if (m.isColored()) {
			glDisableClientState(GL_COLOR_ARRAY);
		}
		if (m.isTextured()) {
			glDisableClientState(GL_TEXTURE_COORD_ARRAY);
		}
	}

	public void renderNoColor(Model m, int mode) {
		glEnableClientState(GL_VERTEX_ARRAY);
		glVertexPointer(3, 3 << 2, m.getModel());
		if (m.isIndexed()) {
			glDrawElements(mode, m.getIndices());
		} else {
			glDrawArrays(mode, 0, m.getVertexNum() / 3);
		}
		glDisableClientState(GL_VERTEX_ARRAY);
	}
}