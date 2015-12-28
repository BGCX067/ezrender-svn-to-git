#version 110

attribute vec4 vPosition;

void main() {
	gl_FrontColor = gl_Color;
	gl_Position = ftransform();
}