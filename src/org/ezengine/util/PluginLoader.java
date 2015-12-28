package org.ezengine.util;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginLoader {
	
	public static Object loadInternal(String path) {
		try {
			return Class.forName(path).newInstance();
		} catch (Exception e) {
			System.out.println("Internal plugin not found, searching external.");
			return loadExternal(path);
		}
	}

	public static Object loadExternal(String cls) {
		String file = "", path = "";
		URLClassLoader cl = null;
		try {
			if (cls.contains("/")) {
				String[] tmp = cls.split("/");
				file = tmp[tmp.length - 1]; 
				path = new File(cls.substring(0, cls.lastIndexOf("/") + 1)).getAbsolutePath() + "/";
				if(cls.contains(".jar")) {
					path = ("jar:file:/" + new File(path).getAbsolutePath()).replace("\\", "/") + "/";
					cl = new URLClassLoader(new URL[] {new URL(path)}, PluginLoader.class.getClassLoader());
				} else {
					cl = new URLClassLoader(new URL[] {new File(path).toURI().toURL()}, PluginLoader.class.getClassLoader());
				}
			} else {
				file = cls;
				cl = new URLClassLoader(new URL[] {new File(".").toURI().toURL()}, PluginLoader.class.getClassLoader());
			}
			Class<?> c = Class.forName(file, true, cl);
			cl.close();
			return c.newInstance();
		} catch(Exception e) {
			System.out.println("Could not find the Class " + file + " in " + path);
			e.printStackTrace();
			return null;
		}
	}
	
}
