package com.FPMap;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

public class FPMap extends ImageView {
	
	//paint for drawing fingerprint points
	private Paint greenPaint = null;
	private Paint redPaint = null;
	//FP point click callback
	private FPPointClickListener fp_point_click_listener = null;
	
	final private int radius = 15;
	
	static private ArrayList<FPPoint> fplist = null;

	public FPMap(Context context) {
		super(context);
		initPaint();
	}

	public FPMap(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initPaint();
	}

	public FPMap(Context context, AttributeSet attrs) {
		super(context, attrs);
		initPaint();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(fplist != null)
		{
			for(int i=0; i<fplist.size(); i++)
			{
				FPPoint p = fplist.get(i);
				float x = FPUtility.translateDPToPixcel_X(getContext(), p.X);
				float y = FPUtility.translateDPToPixcel_Y(getContext(), p.Y);
				if(p.state == FPPoint.State.NOT_SAMPLED)
				{
					canvas.drawCircle(x, y, radius, redPaint);
				}
				else if(p.state == FPPoint.State.SAMPLED)
				{
					canvas.drawCircle(x, y, radius, greenPaint);
				}
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction())
		{
		case MotionEvent.ACTION_UP:
			float x = FPUtility.translatePixelToDP_X(getContext(), event.getX());
			float y = FPUtility.translatePixelToDP_Y(getContext(), event.getY());
			FPPoint fp = FPUtility.locateImmediateFP(x, y, fplist, radius);
			if(fp != null)
			{
				if(fp_point_click_listener!=null)
				{
					//invoke call back
					fp_point_click_listener.OnClick(fp);
				}
			}
			break;
		default:
			break;
		}
		return true;
	}

	private void initPaint()
	{
		greenPaint = new Paint();
		greenPaint.setColor(Color.GREEN);
		redPaint = new Paint();
		redPaint.setColor(Color.RED);
	}

	/**
	 * set FingerPrint list, trigger map refresh
	 * @param _fplist FingerPrint array list
	 */
	public void setFPlist(ArrayList<FPPoint> _fplist)
	{
		fplist = _fplist;
		this.invalidate();
	}
	
	public void setFPPointClickListener(FPPointClickListener listener)
	{
		this.fp_point_click_listener = listener;
	}
	
}
