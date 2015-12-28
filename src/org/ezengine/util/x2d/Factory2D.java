package org.ezengine.util.x2d;

import static org.lwjgl.opengl.GL11.*;

public class Factory2D {

	public static int createTile(int texture, double width, double height) {
		int listID = glGenLists(1);
		glNewList(listID, GL_COMPILE);
		glBindTexture(GL_TEXTURE_2D, texture);
		glBegin(GL_TRIANGLES);
		glTexCoord2d(0, 0);
		glVertex2d(0, 0);
		glTexCoord2d(0, 1);
		glVertex2d(0, height);
		glTexCoord2d(1, 1);
		glVertex2d(width, height);
		glTexCoord2d(1, 1);
		glVertex2d(width, height);
		glTexCoord2d(1, 0);
		glVertex2d(width, 0);
		glTexCoord2d(0, 0);
		glVertex2d(0, 0);
		glEnd();
		glBindTexture(GL_TEXTURE_2D, 0);
		glEndList();
		return listID;
	}
	
	public static int createTile(double width, double height) {
		int listID = glGenLists(1);
		glNewList(listID, GL_COMPILE);
		glBegin(GL_TRIANGLES);
		glVertex2d(0, 0);
		glVertex2d(0, height);
		glVertex2d(width, height);
		glVertex2d(width, height);
		glVertex2d(width, 0);
		glVertex2d(0, 0);
		glEnd();
		glEndList();
		return listID;
	}
	
	public static int createCenteredTile(int texture, double width, double height) {
		width /= 2;
		height /= 2;
		int listID = glGenLists(1);
		glNewList(listID, GL_COMPILE);
		glBindTexture(GL_TEXTURE_2D, texture);
		glBegin(GL_TRIANGLES);
		glTexCoord2d(0, 0);
		glVertex2d(-width, -height);
		glTexCoord2d(0, 1);
		glVertex2d(-width, height);
		glTexCoord2d(1, 1);
		glVertex2d(width, height);
		glTexCoord2d(1, 1);
		glVertex2d(width, height);
		glTexCoord2d(1, 0);
		glVertex2d(width, -height);
		glTexCoord2d(0, 0);
		glVertex2d(-width, -height);
		glEnd();
		glBindTexture(GL_TEXTURE_2D, 0);
		glEndList();
		return listID;
	}
	
	public static int createCenteredTile(double width, double height) {
		width /= 2;
		height /= 2;
		int listID = glGenLists(1);
		glNewList(listID, GL_COMPILE);
		glBegin(GL_TRIANGLES);
		glVertex2d(-width, -height);
		glVertex2d(-width, height);
		glVertex2d(width, height);
		glVertex2d(width, height);
		glVertex2d(width, -height);
		glVertex2d(-width, -height);
		glEnd();
		glEndList();
		return listID;
	}

}
