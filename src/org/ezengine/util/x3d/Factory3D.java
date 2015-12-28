package org.ezengine.util.x3d;

import static org.lwjgl.opengl.GL11.*;

public class Factory3D {

	public static int buildCube(double w, double h, double d) {
		double[] vertices = new double[] { -w, h, d, -w, -h, d, w, h, d, w, h, d, -w, -h, d, w, -h, d, w, h, -d, w, -h, -d, -w, h, -d, -w, h, -d, w, -h, -d, -w, -h, -d, w, h, -d, w, h, d, w, -h, d, w, -h, d, w, -h, -d, w, h, -d, -w, -h, d, -w, h, d, -w, h, -d, -w, h, -d, -w, -h, -d, -w, -h, d, w, h, -d, -w, h, -d, -w, h, d, -w, h, d, w, h, d, w, h, -d, -w, -h, -d, w, -h, -d, -w, -h, d, w, -h, d, -w, -h, d, w, -h, -d };
		int listID = 0;
		listID = glGenLists(1);
		glNewList(listID, GL_COMPILE);
		glBegin(GL_TRIANGLES);
		for (int i = 0; i < vertices.length / 3; i++) {
			glVertex3d(vertices[i * 3], vertices[(i * 3) + 1], vertices[(i * 3) + 2]);
		}
		glEnd();
		glEndList();
		return listID;
	}

}
