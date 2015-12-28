package org.ezengine.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Compression {
	public static byte[] compress(byte[] data) {
		try {
			ByteArrayOutputStream outData = new ByteArrayOutputStream();
			GZIPOutputStream gzOut = new GZIPOutputStream(outData);
			gzOut.write(data);
			gzOut.close();
			return outData.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] decompress(byte[] data) {
		try {
			ByteArrayOutputStream outData = new ByteArrayOutputStream();
			GZIPInputStream gzIn = new GZIPInputStream(new ByteArrayInputStream(data));
			int i = 0;
			while((i = gzIn.read()) != -1) outData.write(i);
			gzIn.close();
			return outData.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] decompressFile(String filename) {
		File f = new File(filename);
		try {
			InputStream in = new FileInputStream(f);
			String bytes = "";
			int i = 0;
			while ((i = in.read()) != -1) bytes += (byte) i;
			in.close();
			return decompress(bytes.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void compressFile(String filename, byte[] data) {
		byte[] compData = compress(data);
		File f = new File(filename);
		try {
			OutputStream out = new FileOutputStream(f);
			out.write(compData);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
