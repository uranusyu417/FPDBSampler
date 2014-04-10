package com.FPMap;

import java.util.ArrayList;

import android.content.Context;
import android.util.DisplayMetrics;

public class FPUtility
{
	public static float translatePixelToDP_X(Context context, float px)
	{
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return px * 160 / dm.xdpi;
	}
	
	public static float translatePixelToDP_Y(Context context, float px)
	{
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return px * 160 / dm.ydpi;
	}
	
	public static float translateDPToPixcel_X(Context context, float dp)
	{
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dp * (dm.xdpi / 160);
	}
	
	public static float translateDPToPixcel_Y(Context context, float dp)
	{
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dp * (dm.ydpi / 160);
	}
	
	/**
	 * 
	 * @param x x of point
	 * @param y y of point
	 * @param cx circle center x
	 * @param cy circle center y
	 * @param radius circle radius
	 * @return
	 */
	public static boolean isPointInCircle(float x, float y, float cx, float cy, float radius)
	{
		if((cx-x)*(cx-x) + (cy-y)*(cy-y) <= radius*radius)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * find which FP circle the point locates in.
	 * @param x
	 * @param y
	 * @param fplist
	 * @param radius
	 * @return null for no FP match.
	 */
	public static FPPoint locateImmediateFP(float x, float y, ArrayList<FPPoint> fplist, float radius)
	{
		for(int i=0; i<fplist.size(); i++)
		{
			FPPoint temp = fplist.get(i);
			if(isPointInCircle(x, y, temp.X, temp.Y, radius))
			{
				return temp;
			}
		}
		return null;
	}
}
