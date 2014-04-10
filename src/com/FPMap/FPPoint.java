package com.FPMap;

public class FPPoint
{
	/**
	 * for fingerprint point
	 * */
	public int ID;

	/**
	 * x coordinate in DP
	 */
	public int X;
	/**
	 * y coordinate in DP
	 */
	public int Y;
	/**
	 * map id of this point belongs to
	 */
	public int MAP_ID;
	
	/**
	 * mark if the fp point has been sampled
	 */
	public State state;
	
	public enum State
	{
		SAMPLED,
		SAMPLING,
		NOT_SAMPLED
	}

}
