package org.ezengine.GL.GLRenderer;

import org.ezengine.util.x3d.Model;

public interface GLRenderer {

	public void render(Model m, int mode);

	public void renderNoColor(Model m, int mode);
}
