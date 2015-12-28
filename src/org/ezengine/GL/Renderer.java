package org.ezengine.GL;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import org.ezengine.GL.GLRenderer.*;
import org.ezengine.util.Window;
import org.ezengine.util.x2d.Graphics;
import org.ezengine.util.x3d.Model;

public class Renderer {

	private static GLRenderer glRender = null;
	private static boolean is2D = false;

	public static void render(Model m, int mode) {
		glPushMatrix();
		m.place();
		glRender.render(m, mode);
		glPopMatrix();
	}

	public static void renderNoColor(Model m, int mode) {
		glPushMatrix();
		m.place();
		glRender.renderNoColor(m, mode);
		glPopMatrix();
	}

	public static void setClearColor(float r, float g, float b) {
		glClearColor(r, g, b, 1f);
	}

	public static void setClearColor(float r, float g, float b, float a) {
		glClearColor(r, g, b, a);
	}

	public static void initRenderer(int width, int height) {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(60.0f, (float) width / (float) height, 0.0f, 500.0f);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LEQUAL);
		glShadeModel(GL_FLAT);
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glEnable(GL_COLOR_MATERIAL);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glClearColor(0, 0, 0, 1);
		Graphics.initialize();
		glRender = new GLRenderer11();
	}

	public static void make2D() {
		if (is2D) return;
		is2D = true;
		glDisable(GL_DEPTH_TEST);
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		glLoadIdentity();
		glOrtho(0, Window.getGL2DSize().x, Window.getGL2DSize().y, 0, -1.0, 1.0);
		glMatrixMode(GL_MODELVIEW);
		glPushMatrix();
		glLoadIdentity();
	}

	public static void make3D() {
		if (!is2D) return;
		is2D = false;
		glMatrixMode(GL_PROJECTION);
		glPopMatrix();
		glMatrixMode(GL_MODELVIEW);
		glPopMatrix();
		glEnable(GL_DEPTH_TEST);
	}

	public static boolean is2D() {
		return is2D;
	}
}
