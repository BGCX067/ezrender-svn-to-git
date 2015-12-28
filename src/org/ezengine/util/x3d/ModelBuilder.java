package org.ezengine.util.x3d;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class ModelBuilder {

	private static ArrayList<Vector3f> verts;
	private static ArrayList<Vector4f> colors;
	private static boolean isColored;

	public static void initNewModel() {
		verts = new ArrayList<Vector3f>();
		colors = new ArrayList<Vector4f>();
		isColored = false;
	}

	private static float[] getQuadArray(float x, float y, float z, float s, boolean cw) {
		if (cw) {
			return new float[] { x - s, y, z + s, x + s, y, z + s, x - s, y, z - s, x + s, y, z + s, x + s, y, z - s, x - s, y, z - s };
		} else {
			return new float[] { x - s, y, z + s, x - s, y, z - s, x + s, y, z + s, x + s, y, z - s, x + s, y, z + s, x - s, y, z - s };
		}
	}

	public static Model buildQuad(float x, float y, float z, float s, boolean cw) {
		return new Model(getQuadArray(x, y, z, s, cw));
	}

	public static Model buildQuad(float x, float y, float z, float s, boolean cw, float[] rgb) {
		Model m = buildQuad(x, y, z, s, cw);
		float[] color = new float[m.getVertexNum() * 3];
		for (int i = 0; i < color.length;) {
			color[i++] = rgb[0];
			color[i++] = rgb[1];
			color[i++] = rgb[2];
		}
		m.setColor(color);
		m.setRGBA(false);
		return m;
	}

	public static void addQuad(float x, float y, float z, float s, boolean cw) {
		float[] array = getQuadArray(x, y, z, s, cw);
		for (int i = 0; i < array.length; i += 3) {
			verts.add(new Vector3f(array[i], array[i + 1], array[i + 2]));
		}
	}

	private static float[] getCubeArray(float x, float y, float z, float s) {
		return new float[] { x - s, y + s, z + s, x - s, y - s, z + s, x + s, y + s, z + s, x + s, y + s, z + s, x - s, y - s, z + s, x + s, y - s, z + s, x + s, y + s, z - s, x + s, y - s, z - s, x - s, y + s, z - s, x - s, y + s, z - s, x + s, y - s, z - s, x - s, y - s, z - s, x + s, y + s, z - s, x + s, y + s, z + s, x + s, y - s, z + s, x + s, y - s, z + s, x + s, y - s, z - s, x + s, y + s, z - s, x - s, y - s, z + s, x - s, y + s, z + s, x - s, y + s, z - s, x - s, y + s, z - s, x - s, y - s, z - s, x - s, y - s, z + s, x + s, y + s, z - s, x - s, y + s, z - s, x - s, y + s, z + s, x - s, y + s, z + s, x + s, y + s, z + s, x + s, y + s, z - s, x - s, y - s, z - s, x + s, y - s, z - s, x - s, y - s, z + s, x + s, y - s, z + s, x - s, y - s, z + s, x + s, y - s, z - s };
	}

	public static Model buildCube(float x, float y, float z, float s) {
		return new Model(getCubeArray(x, y, z, s));
	}

	public static Model buildCube(float x, float y, float z, float s, float[] rgb) {
		Model m = buildCube(x, y, z, s);
		float[] color = new float[m.getVertexNum() * 3];
		for (int i = 0; i < color.length;) {
			color[i++] = rgb[0];
			color[i++] = rgb[1];
			color[i++] = rgb[2];
		}
		m.setColor(color);
		m.setRGBA(false);
		return m;
	}

	public static void addCube(float x, float y, float z, float s) {
		float[] array = getCubeArray(x, y, z, s);
		for (int i = 0; i < array.length; i += 3) {
			verts.add(new Vector3f(array[i], array[i + 1], array[i + 2]));
		}
	}

	public static void addCube(float x, float y, float z, float s, float[] rgb) {
		float[] array = getCubeArray(x, y, z, s);
		for (int i = 0; i < array.length; i += 3) {
			verts.add(new Vector3f(array[i], array[i + 1], array[i + 2]));
			colors.add(new Vector4f(rgb[0], rgb[1], rgb[2], 1f));
		}
		isColored = true;
	}

	public static void addVertex(Vector3f v) {
		verts.add(v);
	}

	public static void addVertex(Vector3f v, Vector4f c) {
		verts.add(v);
		isColored = true;
		colors.add(c);
	}

	public static Model buildModel() {
		float[][] vertColor = new float[2][];
		vertColor[0] = new float[verts.size() * 3];
		int i, num = 0;
		for (i = 0; i < verts.size(); i++) {
			Vector3f v = verts.get(i);
			vertColor[0][num++] = v.x;
			vertColor[0][num++] = v.y;
			vertColor[0][num++] = v.z;
		}

		if (isColored) {
			vertColor[1] = new float[colors.size() * 4];
			num = 0;
			for (i = 0; i < verts.size(); i++) {
				Vector4f v = colors.get(i);
				vertColor[1][num++] = v.x;
				vertColor[1][num++] = v.y;
				vertColor[1][num++] = v.z;
				vertColor[1][num++] = v.w;
			}
		}
		Model m;
		if (isColored) {
			m = new Model(vertColor[0], vertColor[1], true);
		} else {
			m = new Model(vertColor[0]);
		}
		vertColor = null;
		return m;
	}

	public static void buildCube(float x, float y, float z, float size, int textures[]) {
		if (textures == null) {
			buildCube(x, y, z, size);
		}
		if (textures.length == 2) {
			buildCube(x, y, z, size, textures[0], textures[1]);
		}
		if (textures.length == 3) {
			buildCube(x, y, z, size, textures[0], textures[1], textures[2]);
		}
		if (textures.length == 6) {
			buildCube(x, y, z, size, textures[0], textures[1], textures[2], textures[3], textures[4], textures[5]);
		}
	}

	public static void buildCube(float x, float y, float z, float size, int top, int bottom) {}

	public static void buildCube(float x, float y, float z, float size, int top, int bottom, int sides) {}

	public static void buildCube(float x, float y, float z, float size, int top, int bottom, int north, int west, int south, int east) {}
}
