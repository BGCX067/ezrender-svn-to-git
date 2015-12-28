package org.ezengine.util.x2d;

import static org.lwjgl.opengl.GL11.*;

import org.ezengine.util.TextureLoader;

public class TextRenderer {

	private int[] font;
	private int textureID;
	public static final int FONT_A = 0, FONT_B = 1, FONT_C = 2, FONT_D = 3, FONT_E = 4, FONT_F = 5, FONT_G = 6, FONT_H = 7, FONT_I = 8, FONT_J = 9, FONT_K = 10, FONT_L = 11, FONT_M = 12, FONT_N = 13, FONT_O = 14, FONT_P = 15, FONT_Q = 16, FONT_R = 17,
			FONT_S = 18, FONT_T = 19, FONT_U = 20, FONT_V = 21, FONT_W = 22, FONT_X = 23, FONT_Y = 24, FONT_Z = 25, FONT_a = 26, FONT_b = 27, FONT_c = 28, FONT_d = 29, FONT_e = 30, FONT_f = 31, FONT_g = 32, FONT_h = 33, FONT_i = 34, FONT_j = 35,
			FONT_k = 36, FONT_l = 37, FONT_m = 38, FONT_n = 39, FONT_o = 40, FONT_p = 41, FONT_q = 42, FONT_r = 43, FONT_s = 44, FONT_t = 45, FONT_u = 46, FONT_v = 47, FONT_w = 48, FONT_x = 49, FONT_y = 50, FONT_z = 51, FONT_1 = 52, FONT_2 = 53,
			FONT_3 = 54, FONT_4 = 55, FONT_5 = 56, FONT_6 = 57, FONT_7 = 58, FONT_8 = 59, FONT_9 = 60, FONT_0 = 61, FONT_EXM = 62, FONT_QUOTE = 63, FONT_HASH = 64, FONT_OX = 65, FONT_PERCENT = 66, FONT_AND = 67, FONT_FWSLASH = 68, FONT_LBRACKET = 69,
			FONT_RBRACKET = 70, FONT_EQUAL = 71, FONT_QUESTION = 72, FONT_BSLASH = 73, FONT_PLUS = 74, FONT_COLON = 75, FONT_SEMICOLON = 76, FONT_COMMA = 77, FONT_PUNCTUATION = 78, FONT_DASH = 79, FONT_APOSTROPHE = 80, FONT_STAR = 81, FONT_LESS = 82,
			FONT_MORE = 83;

	public static final double FONT_ONE_PIXEL = (1.0 / 128.0);
	public static final double FONT_X_SIZE = 9.0;
	public static final double FONT_Y_SIZE = 18.0;
	public static final double FONT_GET_X = FONT_ONE_PIXEL * FONT_X_SIZE;
	public static final double FONT_GET_Y = FONT_ONE_PIXEL * FONT_Y_SIZE;

	public TextRenderer() {
		try {
			textureID = TextureLoader.loadTexture("/org/ezengine/GL/res/font.png", 0xFFFF00FF);
		} catch (Exception e) {}
		glBindTexture(GL_TEXTURE_2D, textureID);
		final int size = 84;
		font = new int[size];
		int lists = glGenLists(size), tmp = 0;
		for (int i = 0; i < size; i++) {

			font[i] = (lists + i);
		}
		for (int i = FONT_A; i < FONT_O; i++) {
			glNewList(font[i], GL_COMPILE);
			glBegin(GL_TRIANGLES);
			glTexCoord2d(fontGetX(0 + i), fontGetY(0));
			glVertex2d(0, 0);
			glTexCoord2d(fontGetX(0 + i), fontGetY(1));
			glVertex2d(0, FONT_Y_SIZE);
			glTexCoord2d(fontGetX(1 + i), fontGetY(1));
			glVertex2d(FONT_X_SIZE, FONT_Y_SIZE);
			glTexCoord2d(fontGetX(1 + i), fontGetY(1));
			glVertex2d(FONT_X_SIZE, FONT_Y_SIZE);
			glTexCoord2d(fontGetX(1 + i), fontGetY(0));
			glVertex2d(FONT_X_SIZE, 0);
			glTexCoord2d(fontGetX(0 + i), fontGetY(0));
			glVertex2d(0, 0);
			glEnd();
			glEndList();
		}
		for (int i = FONT_O; i < FONT_c; i++) {
			if (i == FONT_R) continue;
			if (i == FONT_V) continue;
			if (i == FONT_W) continue;
			if (i == FONT_Y) continue;
			if (i == FONT_Z) continue;
			tmp = i - FONT_O;
			glNewList(font[i], GL_COMPILE);
			glBegin(GL_TRIANGLES);
			glTexCoord2d(fontGetX(0 + tmp), fontGetY(1) + FONT_ONE_PIXEL);
			glVertex2d(0, 0);
			glTexCoord2d(fontGetX(0 + tmp), fontGetY(2) + FONT_ONE_PIXEL);
			glVertex2d(0, FONT_Y_SIZE);
			glTexCoord2d(fontGetX(1 + tmp), fontGetY(2) + FONT_ONE_PIXEL);
			glVertex2d(FONT_X_SIZE, FONT_Y_SIZE);
			glTexCoord2d(fontGetX(1 + tmp), fontGetY(2) + FONT_ONE_PIXEL);
			glVertex2d(FONT_X_SIZE, FONT_Y_SIZE);
			glTexCoord2d(fontGetX(1 + tmp), fontGetY(1) + FONT_ONE_PIXEL);
			glVertex2d(FONT_X_SIZE, 0);
			glTexCoord2d(fontGetX(0 + tmp), fontGetY(1) + FONT_ONE_PIXEL);
			glVertex2d(0, 0);
			glEnd();
			glEndList();
		}

		for (int i = FONT_c; i < FONT_q; i++) {
			tmp = i - FONT_c;
			glNewList(font[i], GL_COMPILE);
			glBegin(GL_TRIANGLES);
			glTexCoord2d(fontGetX(0 + tmp), fontGetY(2) + FONT_ONE_PIXEL * 2);
			glVertex2d(0, 0);
			glTexCoord2d(fontGetX(0 + tmp), fontGetY(3) + FONT_ONE_PIXEL * 2);
			glVertex2d(0, FONT_Y_SIZE);
			glTexCoord2d(fontGetX(1 + tmp), fontGetY(3) + FONT_ONE_PIXEL * 2);
			glVertex2d(FONT_X_SIZE, FONT_Y_SIZE);
			glTexCoord2d(fontGetX(1 + tmp), fontGetY(3) + FONT_ONE_PIXEL * 2);
			glVertex2d(FONT_X_SIZE, FONT_Y_SIZE);
			glTexCoord2d(fontGetX(1 + tmp), fontGetY(2) + FONT_ONE_PIXEL * 2);
			glVertex2d(FONT_X_SIZE, 0);
			glTexCoord2d(fontGetX(0 + tmp), fontGetY(2) + FONT_ONE_PIXEL * 2);
			glVertex2d(0, 0);
			glEnd();
			glEndList();
		}

		for (int i = FONT_q; i < FONT_5; i++) {
			tmp = i - FONT_q;
			glNewList(font[i], GL_COMPILE);
			glBegin(GL_TRIANGLES);
			glTexCoord2d(fontGetX(0 + tmp), fontGetY(3) + FONT_ONE_PIXEL * 3);
			glVertex2d(0, 0);
			glTexCoord2d(fontGetX(0 + tmp), fontGetY(4) + FONT_ONE_PIXEL * 3);
			glVertex2d(0, FONT_Y_SIZE);
			glTexCoord2d(fontGetX(1 + tmp), fontGetY(4) + FONT_ONE_PIXEL * 3);
			glVertex2d(FONT_X_SIZE, FONT_Y_SIZE);
			glTexCoord2d(fontGetX(1 + tmp), fontGetY(4) + FONT_ONE_PIXEL * 3);
			glVertex2d(FONT_X_SIZE, FONT_Y_SIZE);
			glTexCoord2d(fontGetX(1 + tmp), fontGetY(3) + FONT_ONE_PIXEL * 3);
			glVertex2d(FONT_X_SIZE, 0);
			glTexCoord2d(fontGetX(0 + tmp), fontGetY(3) + FONT_ONE_PIXEL * 3);
			glVertex2d(0, 0);
			glEnd();
			glEndList();
		}

		for (int i = FONT_5; i < FONT_RBRACKET; i++) {
			tmp = i - FONT_5;
			glNewList(font[i], GL_COMPILE);
			glBegin(GL_TRIANGLES);
			glTexCoord2d(fontGetX(0 + tmp), fontGetY(4) + FONT_ONE_PIXEL * 4);
			glVertex2d(0, 0);
			glTexCoord2d(fontGetX(0 + tmp), fontGetY(5) + FONT_ONE_PIXEL * 4);
			glVertex2d(0, FONT_Y_SIZE);
			glTexCoord2d(fontGetX(1 + tmp), fontGetY(5) + FONT_ONE_PIXEL * 4);
			glVertex2d(FONT_X_SIZE, FONT_Y_SIZE);
			glTexCoord2d(fontGetX(1 + tmp), fontGetY(5) + FONT_ONE_PIXEL * 4);
			glVertex2d(FONT_X_SIZE, FONT_Y_SIZE);
			glTexCoord2d(fontGetX(1 + tmp), fontGetY(4) + FONT_ONE_PIXEL * 4);
			glVertex2d(FONT_X_SIZE, 0);
			glTexCoord2d(fontGetX(0 + tmp), fontGetY(4) + FONT_ONE_PIXEL * 4);
			glVertex2d(0, 0);
			glEnd();
			glEndList();
		}

		for (int i = FONT_RBRACKET; i <= FONT_MORE; i++) {
			tmp = i - FONT_RBRACKET;
			glNewList(font[i], GL_COMPILE);
			glBegin(GL_TRIANGLES);
			glTexCoord2d(fontGetX(0 + tmp), fontGetY(5) + FONT_ONE_PIXEL * 5);
			glVertex2d(0, 0);
			glTexCoord2d(fontGetX(0 + tmp), fontGetY(6) + FONT_ONE_PIXEL * 5);
			glVertex2d(0, FONT_Y_SIZE);
			glTexCoord2d(fontGetX(1 + tmp), fontGetY(6) + FONT_ONE_PIXEL * 5);
			glVertex2d(FONT_X_SIZE, FONT_Y_SIZE);
			glTexCoord2d(fontGetX(1 + tmp), fontGetY(6) + FONT_ONE_PIXEL * 5);
			glVertex2d(FONT_X_SIZE, FONT_Y_SIZE);
			glTexCoord2d(fontGetX(1 + tmp), fontGetY(5) + FONT_ONE_PIXEL * 5);
			glVertex2d(FONT_X_SIZE, 0);
			glTexCoord2d(fontGetX(0 + tmp), fontGetY(5) + FONT_ONE_PIXEL * 5);
			glVertex2d(0, 0);
			glEnd();
			glEndList();
		}

		glNewList(font[FONT_R], GL_COMPILE);
		glBegin(GL_TRIANGLES);
		glTexCoord2d(fontGetX(3) + FONT_ONE_PIXEL, fontGetY(1) + FONT_ONE_PIXEL);
		glVertex2d(0, 0);
		glTexCoord2d(fontGetX(3) + FONT_ONE_PIXEL, fontGetY(2) + FONT_ONE_PIXEL);
		glVertex2d(0, FONT_Y_SIZE);
		glTexCoord2d(fontGetX(4), fontGetY(2) + FONT_ONE_PIXEL);
		glVertex2d(FONT_X_SIZE, FONT_Y_SIZE);
		glTexCoord2d(fontGetX(4), fontGetY(2) + FONT_ONE_PIXEL);
		glVertex2d(FONT_X_SIZE, FONT_Y_SIZE);
		glTexCoord2d(fontGetX(4), fontGetY(1) + FONT_ONE_PIXEL);
		glVertex2d(FONT_X_SIZE, 0);
		glTexCoord2d(fontGetX(3) + FONT_ONE_PIXEL, fontGetY(1) + FONT_ONE_PIXEL);
		glVertex2d(0, 0);
		glEnd();
		glEndList();

		glNewList(font[FONT_V], GL_COMPILE);
		glBegin(GL_TRIANGLES);
		glTexCoord2d(fontGetX(7), fontGetY(1) + FONT_ONE_PIXEL);
		glVertex2d(0, 0);
		glTexCoord2d(fontGetX(7), fontGetY(2) + FONT_ONE_PIXEL);
		glVertex2d(0, FONT_Y_SIZE);
		glTexCoord2d(fontGetX(8) + FONT_ONE_PIXEL, fontGetY(2) + FONT_ONE_PIXEL);
		glVertex2d(FONT_X_SIZE, FONT_Y_SIZE);
		glTexCoord2d(fontGetX(8) + FONT_ONE_PIXEL, fontGetY(2) + FONT_ONE_PIXEL);
		glVertex2d(FONT_X_SIZE, FONT_Y_SIZE);
		glTexCoord2d(fontGetX(8) + FONT_ONE_PIXEL, fontGetY(1) + FONT_ONE_PIXEL);
		glVertex2d(FONT_X_SIZE, 0);
		glTexCoord2d(fontGetX(7), fontGetY(1) + FONT_ONE_PIXEL);
		glVertex2d(0, 0);
		glEnd();
		glEndList();

		glNewList(font[FONT_W], GL_COMPILE);
		glBegin(GL_TRIANGLES);
		glTexCoord2d(fontGetX(8) + FONT_ONE_PIXEL, fontGetY(1) + FONT_ONE_PIXEL);
		glVertex2d(0, 0);
		glTexCoord2d(fontGetX(8) + FONT_ONE_PIXEL, fontGetY(2) + FONT_ONE_PIXEL);
		glVertex2d(0, FONT_Y_SIZE);
		glTexCoord2d(fontGetX(9), fontGetY(2) + FONT_ONE_PIXEL);
		glVertex2d(FONT_X_SIZE, FONT_Y_SIZE);
		glTexCoord2d(fontGetX(9), fontGetY(2) + FONT_ONE_PIXEL);
		glVertex2d(FONT_X_SIZE, FONT_Y_SIZE);
		glTexCoord2d(fontGetX(9), fontGetY(1) + FONT_ONE_PIXEL);
		glVertex2d(FONT_X_SIZE, 0);
		glTexCoord2d(fontGetX(8) + FONT_ONE_PIXEL, fontGetY(1) + FONT_ONE_PIXEL);
		glVertex2d(0, 0);
		glEnd();
		glEndList();

		glNewList(font[FONT_Y], GL_COMPILE);
		glBegin(GL_TRIANGLES);
		glTexCoord2d(fontGetX(10), fontGetY(1) + FONT_ONE_PIXEL);
		glVertex2d(0, 0);
		glTexCoord2d(fontGetX(10), fontGetY(2) + FONT_ONE_PIXEL);
		glVertex2d(0, FONT_Y_SIZE);
		glTexCoord2d(fontGetX(11) + FONT_ONE_PIXEL, fontGetY(2) + FONT_ONE_PIXEL);
		glVertex2d(FONT_X_SIZE, FONT_Y_SIZE);
		glTexCoord2d(fontGetX(11) + FONT_ONE_PIXEL, fontGetY(2) + FONT_ONE_PIXEL);
		glVertex2d(FONT_X_SIZE, FONT_Y_SIZE);
		glTexCoord2d(fontGetX(11) + FONT_ONE_PIXEL, fontGetY(1) + FONT_ONE_PIXEL);
		glVertex2d(FONT_X_SIZE, 0);
		glTexCoord2d(fontGetX(10), fontGetY(1) + FONT_ONE_PIXEL);
		glVertex2d(0, 0);
		glEnd();
		glEndList();

		glNewList(font[FONT_Z], GL_COMPILE);
		glBegin(GL_TRIANGLES);
		glTexCoord2d(fontGetX(11) + FONT_ONE_PIXEL, fontGetY(1) + FONT_ONE_PIXEL);
		glVertex2d(0, 0);
		glTexCoord2d(fontGetX(11) + FONT_ONE_PIXEL, fontGetY(2) + FONT_ONE_PIXEL);
		glVertex2d(0, FONT_Y_SIZE);
		glTexCoord2d(fontGetX(12), fontGetY(2) + FONT_ONE_PIXEL);
		glVertex2d(FONT_X_SIZE, FONT_Y_SIZE);
		glTexCoord2d(fontGetX(12), fontGetY(2) + FONT_ONE_PIXEL);
		glVertex2d(FONT_X_SIZE, FONT_Y_SIZE);
		glTexCoord2d(fontGetX(12), fontGetY(1) + FONT_ONE_PIXEL);
		glVertex2d(FONT_X_SIZE, 0);
		glTexCoord2d(fontGetX(11) + FONT_ONE_PIXEL, fontGetY(1) + FONT_ONE_PIXEL);
		glVertex2d(0, 0);
		glEnd();
		glEndList();

		glBindTexture(GL_TEXTURE_2D, 0);
	}

	public void drawString(String str, double sz, double x, double y, double z) {
		boolean lineBreak = false;
		int ln = 0;
		glPushMatrix();
		glBindTexture(GL_TEXTURE_2D, textureID);
		if (sz != 1) glScaled(sz, sz, 0);
		glTranslated(x / sz, y / sz, z / sz);
		for (char c : str.toCharArray()) {
			switch (c) {
				case '\n':
					glTranslated(-(ln * FONT_X_SIZE), FONT_Y_SIZE, z);
					ln = 0;
					lineBreak = true;
				break;
				case ' ':
					glTranslated(FONT_X_SIZE * FONT_ONE_PIXEL, 0, 0);
				break;
				case '\t':
					glTranslated(FONT_X_SIZE * FONT_ONE_PIXEL * 4, 0, 0);
				break;
				case 'A':
					glCallList(font[FONT_A]);
				break;
				case 'B':
					glCallList(font[FONT_B]);
				break;
				case 'C':
					glCallList(font[FONT_C]);
				break;
				case 'D':
					glCallList(font[FONT_D]);
				break;
				case 'E':
					glCallList(font[FONT_E]);
				break;
				case 'F':
					glCallList(font[FONT_F]);
				break;
				case 'G':
					glCallList(font[FONT_G]);
				break;
				case 'H':
					glCallList(font[FONT_H]);
				break;
				case 'I':
					glCallList(font[FONT_I]);
				break;
				case 'J':
					glCallList(font[FONT_J]);
				break;
				case 'K':
					glCallList(font[FONT_K]);
				break;
				case 'L':
					glCallList(font[FONT_L]);
				break;
				case 'M':
					glCallList(font[FONT_M]);
				break;
				case 'N':
					glCallList(font[FONT_N]);
				break;
				case 'O':
					glCallList(font[FONT_O]);
				break;
				case 'P':
					glCallList(font[FONT_P]);
				break;
				case 'Q':
					glCallList(font[FONT_Q]);
				break;
				case 'R':
					glCallList(font[FONT_R]);
				break;
				case 'S':
					glCallList(font[FONT_S]);
				break;
				case 'T':
					glCallList(font[FONT_T]);
				break;
				case 'U':
					glCallList(font[FONT_U]);
				break;
				case 'V':
					glCallList(font[FONT_V]);
				break;
				case 'W':
					glCallList(font[FONT_W]);
				break;
				case 'X':
					glCallList(font[FONT_X]);
				break;
				case 'Y':
					glCallList(font[FONT_Y]);
				break;
				case 'Z':
					glCallList(font[FONT_Z]);
				break;
				case 'a':
					glCallList(font[FONT_a]);
				break;
				case 'b':
					glCallList(font[FONT_b]);
				break;
				case 'c':
					glCallList(font[FONT_c]);
				break;
				case 'd':
					glCallList(font[FONT_d]);
				break;
				case 'e':
					glCallList(font[FONT_e]);
				break;
				case 'f':
					glCallList(font[FONT_f]);
				break;
				case 'g':
					glCallList(font[FONT_g]);
				break;
				case 'h':
					glCallList(font[FONT_h]);
				break;
				case 'i':
					glCallList(font[FONT_i]);
				break;
				case 'j':
					glCallList(font[FONT_j]);
				break;
				case 'k':
					glCallList(font[FONT_k]);
				break;
				case 'l':
					glCallList(font[FONT_l]);
				break;
				case 'm':
					glCallList(font[FONT_m]);
				break;
				case 'n':
					glCallList(font[FONT_n]);
				break;
				case 'o':
					glCallList(font[FONT_o]);
				break;
				case 'p':
					glCallList(font[FONT_p]);
				break;
				case 'q':
					glCallList(font[FONT_q]);
				break;
				case 'r':
					glCallList(font[FONT_r]);
				break;
				case 's':
					glCallList(font[FONT_s]);
				break;
				case 't':
					glCallList(font[FONT_t]);
				break;
				case 'u':
					glCallList(font[FONT_u]);
				break;
				case 'v':
					glCallList(font[FONT_v]);
				break;
				case 'w':
					glCallList(font[FONT_w]);
				break;
				case 'x':
					glCallList(font[FONT_x]);
				break;
				case 'y':
					glCallList(font[FONT_y]);
				break;
				case 'z':
					glCallList(font[FONT_z]);
				break;
				case '1':
					glCallList(font[FONT_1]);
				break;
				case '2':
					glCallList(font[FONT_2]);
				break;
				case '3':
					glCallList(font[FONT_3]);
				break;
				case '4':
					glCallList(font[FONT_4]);
				break;
				case '5':
					glCallList(font[FONT_5]);
				break;
				case '6':
					glCallList(font[FONT_6]);
				break;
				case '7':
					glCallList(font[FONT_7]);
				break;
				case '8':
					glCallList(font[FONT_8]);
				break;
				case '9':
					glCallList(font[FONT_9]);
				break;
				case '0':
					glCallList(font[FONT_0]);
				break;
				case '!':
					glCallList(font[FONT_EXM]);
				break;
				case '"':
					glCallList(font[FONT_QUOTE]);
				break;
				case '#':
					glCallList(font[FONT_HASH]);
				break;
				case '¤':
					glCallList(font[FONT_OX]);
				break;
				case '%':
					glCallList(font[FONT_PERCENT]);
				break;
				case '&':
					glCallList(font[FONT_AND]);
				break;
				case '/':
					glCallList(font[FONT_FWSLASH]);
				break;
				case '(':
					glCallList(font[FONT_LBRACKET]);
				break;
				case ')':
					glCallList(font[FONT_RBRACKET]);
				break;
				case '=':
					glCallList(font[FONT_EQUAL]);
				break;
				case '?':
					glCallList(font[FONT_QUESTION]);
				break;
				case '\\':
					glCallList(font[FONT_BSLASH]);
				break;
				case '+':
					glCallList(font[FONT_PLUS]);
				break;
				case ':':
					glCallList(font[FONT_COLON]);
				break;
				case ';':
					glCallList(font[FONT_SEMICOLON]);
				break;
				case ',':
					glCallList(font[FONT_COMMA]);
				break;
				case '.':
					glCallList(font[FONT_PUNCTUATION]);
				break;
				case '-':
					glCallList(font[FONT_DASH]);
				break;
				case '\'':
					glCallList(font[FONT_APOSTROPHE]);
				break;
				case '*':
					glCallList(font[FONT_STAR]);
				break;
				case '<':
					glCallList(font[FONT_LESS]);
				break;
				case '>':
					glCallList(font[FONT_MORE]);
				break;
			}
			if (!lineBreak) {
				glTranslated(FONT_X_SIZE, 0, 0);
				ln++;
			} else lineBreak = false;
		}
		glPopMatrix();
		glBindTexture(GL_TEXTURE_2D, 0);
	}

	public double fontGetX(int x) {
		return FONT_GET_X * x;
	}

	public double fontGetY(int y) {
		return FONT_GET_Y * y;
	}

	public int getFont(int f) {
		return font[f];
	}

}
