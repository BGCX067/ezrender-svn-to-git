package org.ezengine.util.x2d.physics;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;


public class PhysicsFactory2D {
	
	private static Body createBox(float posX, float posY, float w, float h, float density, BodyType bt) {
		BodyDef bd = new BodyDef();
		bd.position.set(posX, posY);
		bd.type = bt;
		
		PolygonShape ps = new PolygonShape();
		ps.setAsBox(w, h);
		Body b = Physics2D.world.createBody(bd);
		b.createFixture(ps, density);
		
		return b;
	}
	
	public static Body createDynamicBox(float posX, float posY, float w, float h) {
		return createBox(posX, posY, w, h, (w * h) / 25, BodyType.DYNAMIC);
	}
	
	public static Body createStaticBox(float posX, float posY, float w, float h) {
		return createBox(posX, posY, w, h, (w * h) / 25, BodyType.STATIC);
	}
	
	public static Body createKinematicBox(float posX, float posY, float w, float h) {
		return createBox(posX, posY, w, h, (w * h) / 25, BodyType.KINEMATIC);
		
	}
	
	public static Body createCircle(float posX, float posY, float r, float density, BodyType bt) {
		BodyDef bd = new BodyDef();
		bd.position.set(posX, posY);
		bd.type = bt;
		
		CircleShape cs = new CircleShape();
		cs.setRadius(r);
		
		Body b = Physics2D.world.createBody(bd);
		b.createFixture(cs, density);
		return b;
	}
	
	public static Body createDynamicCircle(float posX, float posY, float r) {
		return createCircle(posX, posY, r, (float) ((Math.PI * r * r) / 25), BodyType.DYNAMIC);
	}
	
	public static Body createStaticCircle(float posX, float posY, float r) {
		return createCircle(posX, posY, r, (float) ((Math.PI * r * r) / 25), BodyType.STATIC);
	}
	
	public static Body createKinematicCircle(float posX, float posY, float r) {
		return createCircle(posX, posY, r, (float) ((Math.PI * r * r) / 25), BodyType.KINEMATIC);
	}

}
