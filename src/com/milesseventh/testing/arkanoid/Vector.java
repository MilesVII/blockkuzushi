package com.milesseventh.testing.arkanoid;

public class Vector {
	//Temporary implementation of 2D vector
	//Should not be used for real projects
	public float x = 0, y = 0;

	public Vector(){}

	public Vector(float xx, float yy){
		x = xx; y = yy;
	}
	
	public void mimic(Vector v){
		x = v.x; y = v.y;
	}

	public float length(){
		return (float)Math.sqrt(x * x + y * y);
	}

	public Vector normalized(){
		return scale(1 / length());
	}

	public void normalize(){
		mimic(scale(1 / length()));
	}

	public void add(Vector v){
		x += v.x; y += v.y;
	}

	//Processed vectors are loaded from pool and should not be used as global values
	//Should be used carefully
	public Vector scale(float s){
		Vector v = getVector(this);
		v.x *= s; v.y *= s;
		return v;
	}

	public Vector multiply(Vector nv){
		Vector v = getVector(this);
		v.x *= nv.x; v.y *= nv.y;
		return v;
	}

	static float dot(Vector a, Vector b){
		return a.x * b.x + a.y * b.y;
	}

	static float project(Vector v0, Vector v1){
		return dot(v0, v1) / dot(v1, v1);
	}

	//Vector pool
	private static final int VECTORS_IN_POOL = 16;
	private static Vector[] vpool = new Vector[VECTORS_IN_POOL];
	private static int vectorsCounter = 0, holder;
	public static Vector getVector(){//new Vector2() alternative
		return getVector(0, 0);
	}
	public static Vector getVector(Vector in){//cpy() alternative
		return getVector(in.x, in.y);
	}
	public static Vector getVector(float x, float y){//new Vector2(x, y) alternative
		if (vpool[vectorsCounter] == null)
			vpool[vectorsCounter] = new Vector();
		vpool[vectorsCounter].x= x;
		vpool[vectorsCounter].y= y;
		holder = vectorsCounter;
		++vectorsCounter;
		vectorsCounter %= VECTORS_IN_POOL;
		return vpool[holder];
	}
}