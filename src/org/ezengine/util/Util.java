package org.ezengine.util;

import java.net.URL;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.Sys;

public class Util {

	public static FloatBuffer makeBuffer(float[] buf) {
		return (FloatBuffer) BufferUtils.createFloatBuffer(buf.length).put(buf).flip();
	}

	public static DoubleBuffer makeBuffer(double[] buf) {
		return (DoubleBuffer) BufferUtils.createDoubleBuffer(buf.length).put(buf).flip();
	}

	public static IntBuffer makeBuffer(int[] buf) {
		return (IntBuffer) BufferUtils.createIntBuffer(buf.length).put(buf).flip();
	}

	public static ShortBuffer makeBuffer(short[] buf) {
		return (ShortBuffer) BufferUtils.createShortBuffer(buf.length).put(buf).flip();
	}

	public static ByteBuffer makeBuffer(byte[] buf) {
		return (ByteBuffer) BufferUtils.createByteBuffer(buf.length).put(buf).flip();
	}

	public static LongBuffer makeBuffer(long[] buf) {
		return (LongBuffer) BufferUtils.createLongBuffer(buf.length).put(buf).flip();
	}

	public static CharBuffer makeBuffer(char[] buf) {
		return (CharBuffer) BufferUtils.createCharBuffer(buf.length).put(buf).flip();
	}

	public static PointerBuffer makePointerBuffer(long[] buf) {
		return BufferUtils.createPointerBuffer(buf.length).put(buf).flip();
	}

	public static URL getResource(String name) {
		return Util.class.getResource(name);
	}

	public static void printBuffer(Buffer b) {
		while (b.remaining() > 0) {
			if (b instanceof FloatBuffer) {
				System.out.print(((FloatBuffer) b).get());
			} else if (b instanceof DoubleBuffer) {
				System.out.print(((DoubleBuffer) b).get());
			} else if (b instanceof IntBuffer) {
				System.out.print(((IntBuffer) b).get());
			} else if (b instanceof ShortBuffer) {
				System.out.print(((ShortBuffer) b).get());
			} else if (b instanceof ByteBuffer) {
				System.out.print(((ByteBuffer) b).get());
			} else if (b instanceof LongBuffer) {
				System.out.print(((LongBuffer) b).get());
			}
			System.out.print(" ");
		}
		System.out.println();
	}
	
	public static double getDistance(double x1, double y1, double x2, double y2) {
		double x = x1 - x2;
		double y = y1 - y2;
		return Math.sqrt((x * x) + (y * y));
	}
	
	public static double stripDecimals(double d, int decimals) {
		double dec = Math.pow(10.0, decimals);
		return Math.round(d * dec) / dec;
	}
	
	public static boolean isPointWithin(int mx, int my, int x, int y, int w, int h) {
		return (mx >= x && mx <= x + w) && (my >= y && my <= y + h);
	}
	
	public static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

}
