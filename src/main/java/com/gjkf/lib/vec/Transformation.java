package com.gjkf.lib.vec;

import com.gjkf.lib.render.GJRenderState;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Abstract supertype for any 3D vector transformation
 */
public class Transformation extends ITransformation<Vector3, Transformation> implements GJRenderState.IVertexOperation{
    public static final int operationIndex = GJRenderState.registerOperation();

    /**
     * Applies this transformation to a normal (doesn't translate)
     * @param normal The normal to transform
     */
    public void applyN(Vector3 normal) {
	}

    /**
     * Applies this transformation to a matrix as a multiplication on the right hand side.
     * @param mat The matrix to combine this transformation with
     */
    public void apply(Matrix4 mat) {
	}

    public Transformation at(Vector3 point) {
        return new TransformationList(new Translation(-point.x, -point.y, -point.z), this, point.translation());
    }

    public TransformationList with(Transformation t) {
        return new TransformationList(this, t);
    }

    @SideOnly(Side.CLIENT)
    public abstract void glApply();

    @Override
    public boolean load() {
    	GJRenderState.pipeline.addRequirement(GJRenderState.normalAttrib.operationID());
        return !isRedundant();
    }

    @Override
    public void operate() {
        apply(GJRenderState.vert.vec);
        if(GJRenderState.normalAttrib.active)
            applyN(GJRenderState.normal);
    }

    @Override
    public int operationID() {
        return operationIndex;
    }

	@Override
	public void apply(Vector3 vec) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Transformation inverse() {
		// TODO Auto-generated method stub
		return null;
	}
}
