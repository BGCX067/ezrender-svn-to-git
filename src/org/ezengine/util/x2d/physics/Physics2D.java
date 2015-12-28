package org.ezengine.util.x2d.physics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

public class Physics2D {
	static World world;
	public static float TIMESTEP;
	
	public static void initialize(int fps) {
		TIMESTEP = 1000.0f / fps;
		world = new World(new Vec2(0, -9.8f));
	}
	
	public static void update() {
		world.step(TIMESTEP, 8, 1);
	}
	
	public static void setGravity(float x, float y) {
		world.setGravity(new Vec2(x, y));
	}
}
