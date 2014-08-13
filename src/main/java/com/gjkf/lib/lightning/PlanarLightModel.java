package com.gjkf.lib.lightning;

import com.gjkf.lib.colour.ColourRGBA;
import com.gjkf.lib.render.GJRenderState;

/**
 * Faster precomputed version of LightModel that only works for axis planar sides
 */
public class PlanarLightModel implements GJRenderState.IVertexOperation
{
    public static PlanarLightModel standardLightModel = LightModel.standardLightModel.reducePlanar();

    public int[] colours;

    public PlanarLightModel(int[] colours) {
        this.colours = colours;
    }

    @Override
    public boolean load() {
        if(!GJRenderState.computeLighting)
            return false;

        GJRenderState.pipeline.addDependency(GJRenderState.sideAttrib);
        GJRenderState.pipeline.addDependency(GJRenderState.colourAttrib);
        return true;
    }

    @Override
    public void operate() {
    	GJRenderState.setColour(ColourRGBA.multiply(GJRenderState.colour, colours[GJRenderState.side]));
    }

    @Override
    public int operationID() {
        return LightModel.operationIndex;
    }
}