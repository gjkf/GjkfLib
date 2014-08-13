package com.gjkf.lib.render.uv;

import com.gjkf.lib.render.GJRenderState;
import com.gjkf.lib.vec.ITransformation;

public abstract class UVTransformation extends ITransformation<UV, UVTransformation> implements GJRenderState.IVertexOperation
{
    public static final int operationIndex = GJRenderState.registerOperation();

    public UVTransformation at(UV point) {
        return new UVTransformationList(new UVTranslation(-point.u, -point.v), this, new UVTranslation(point.u, point.v));
    }

    public UVTransformationList with(UVTransformation t) {
        return new UVTransformationList(this, t);
    }

    @Override
    public boolean load() {
        return !isRedundant();
    }

    @Override
    public void operate() {
        apply(GJRenderState.vert.uv);
    }

    @Override
    public int operationID() {
        return operationIndex;
    }
}