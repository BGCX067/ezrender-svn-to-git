package org.ezengine.util;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.FileInputStream;

public class ShaderLoader {

	public static int program, vertexLocation;

	public static int loadShader(int type, String src) {
		int shader = glCreateShader(type);
		glShaderSource(shader, src);
		glCompileShader(shader);
		if (glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE) {
			if (glGetShaderi(shader, GL_INFO_LOG_LENGTH) > 1) {
				String infoLog = glGetShaderInfoLog(shader, 1024);
				System.err.println(infoLog);
			}
			glDeleteShader(shader);
			System.exit(1);
		}

		return shader;
	}

	public static int loadDefaultShaders() {
		String vShaderSrc = "", fShaderSrc = "";
		try {
			FileInputStream f = new FileInputStream(ShaderLoader.class.getClass().getResource("/org/ezrender/GL/res/vertex.glsl").getFile());
			byte[] src = new byte[f.available()];
			f.read(src, 0, f.available());
			f.close();
			vShaderSrc = new String(src);
			f = new FileInputStream(ShaderLoader.class.getClass().getResource("/org/ezrender/GL/res/fragment.glsl").getFile());
			src = new byte[f.available()];
			f.read(src, 0, f.available());
			f.close();
			fShaderSrc = new String(src);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loadShaders(vShaderSrc, fShaderSrc);
	}

	public static int loadShaders(String vertexShader, String fragmentShader) {
		program = glCreateProgram();
		glAttachShader(program, loadShader(GL_VERTEX_SHADER, vertexShader));
		glAttachShader(program, loadShader(GL_FRAGMENT_SHADER, fragmentShader));
		glLinkProgram(program);
		if (glGetProgrami(program, GL_LINK_STATUS) == GL_FALSE) {
			if (glGetProgrami(program, GL_INFO_LOG_LENGTH) > 1) {
				String infoLog = glGetProgramInfoLog(program, 1024);
				System.err.println(infoLog);
				glDeleteProgram(program);
				System.exit(1);
			}
		}
		vertexLocation = glGetUniformLocation(program, "vPosition");
		vertexShader = null;
		fragmentShader = null;
		return program;
	}
}
