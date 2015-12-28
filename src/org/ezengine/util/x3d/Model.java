package org.ezengine.util.x3d;

import static org.lwjgl.opengl.GL11.*;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.ezengine.GL.Renderer;
import org.lwjgl.BufferUtils;

public class Model {

	private boolean isRGBA = true, isColored = false, isTextured = false, isIndexed = false;
	private FloatBuffer color, textureCoords, verts;
	private IntBuffer indices;
	private float x, y, z, rotX, rotY, rotZ;
	private int vertexNum = 0, textureID = 0;

	public Model() {}

	public Model(float[] vertices) {
		setModel(vertices);
		setColored(false);
		setTextured(false);
	}

	public Model(float[] vertices, int texture, float[] texCoords) {
		setModel(vertices);
		setTexture(texture, texCoords);
		setRGBA(false);
		setColored(false);
		setTextured(true);
	}

	public Model(float[] vertices, float[] colors, boolean alpha) {
		setModel(vertices);
		setColor(colors);
		setRGBA(alpha);
		setColored(true);
		setTextured(false);
	}

	public void render() {
		Renderer.render(this, GL_TRIANGLES);
	}

	public void render(int mode) {
		Renderer.render(this, mode);
	}

	public static Model loadModel(String s) {
		File f = new File(s);
		if (!f.exists()) { return null; }
		Model m = new Model();
		try {
			DataInputStream in = new DataInputStream(new FileInputStream(f));
			byte[] buffer;
			String tmp = "";
			while (in.available() > 0) {
				buffer = new byte[in.available()];
				in.readFully(buffer, 0, buffer.length);
				tmp += new String(buffer);
			}
			in.close();
			tmp.charAt(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}

	public void place() {
		if (x != 0 || y != 0 || z != 0) {
			glTranslatef(x, y, z);
		}
		if (rotX != 0) {
			glRotatef(rotX, 1, 0, 0);
		}
		if (rotY != 0) {
			glRotatef(rotY, 0, 1, 0);
		}
		if (rotZ != 0) {
			glRotatef(rotZ, 0, 0, 1);
		}
	}

	public void setModel(float[] vertices) {
		vertexNum = vertices.length;
		verts = (FloatBuffer) BufferUtils.createFloatBuffer(vertexNum).put(vertices).flip();
	}

	public FloatBuffer getModel() {
		return verts;
	}

	protected void setIndices(int[] ind) {
		setIndexed(true);
		indices = (IntBuffer) BufferUtils.createIntBuffer(ind.length).put(ind).flip();
	}

	public IntBuffer getIndices() {
		return indices;
	}

	public void setColor(float[] colors) {
		setColored(true);
		color = (FloatBuffer) BufferUtils.createFloatBuffer(colors.length).put(colors).flip();
	}

	public FloatBuffer getColor() {
		return color;
	}

	public void setTexture(int texture, float[] texCoords) {
		textureCoords = (FloatBuffer) BufferUtils.createFloatBuffer(texCoords.length).put(texCoords).flip();
		textureID = texture;
	}

	public FloatBuffer getTextureCoords() {
		return textureCoords;
	}

	public int getTexture() {
		return textureID;
	}

	public int getVertexNum() {
		return vertexNum;
	}

	public void setPos(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void setRot(float rotX, float rotY, float rotZ) {
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
	}

	public boolean isRGBA() {
		return isRGBA;
	}

	public void setRGBA(boolean isRGBA) {
		this.isRGBA = isRGBA;
	}

	public boolean isColored() {
		return isColored;
	}

	public void setColored(boolean isColored) {
		this.isColored = isColored;
	}

	public boolean isTextured() {
		return isTextured;
	}

	public void setTextured(boolean isTextured) {
		this.isTextured = isTextured;
	}

	public boolean isIndexed() {
		return isIndexed;
	}

	public void setIndexed(boolean isIndexed) {
		this.isIndexed = isIndexed;
	}
}
