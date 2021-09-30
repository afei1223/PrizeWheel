package afei.lb.prizewheel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Wheel extends View {
    private Paint mPaint;
    private int[] mColors;
    private String[] mPrizes;
    public Wheel(Context context) {
        super(context);
        init(context,null);
    }

    public Wheel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public Wheel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mColors = new int[]{Color.RED,Color.BLUE,Color.YELLOW,Color.WHITE,Color.GREEN};
    }

    public void setColors(int... color){
        if(color.length==1){
            return;
        }
        mColors = color;
    }

    public void setPrize(String... prizes){
        mPrizes = prizes;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int canvasWidth = getMeasuredWidth();
        int canvasHeight = getMeasuredHeight();
        int totalLength = canvasHeight>canvasWidth?canvasWidth:canvasHeight;
        //circle圆心
        int circleX = canvasWidth/2;
        int circleY = canvasHeight/2;
        int circleR = totalLength/2;
        int sweepAngle = 360/mPrizes.length;

        RectF rectF = new RectF(circleX-circleR,circleY-circleR,
                circleX+circleR,circleY+circleR);

        canvas.drawCircle(circleX,circleY,circleR,mPaint);
        for(int i=0;i<mPrizes.length;i++){
//            if(i!=){
//                mPaint.setColor(Color.BLUE);
//            }else {
//                mPaint.setColor(Color.RED);
//            }
            mPaint.setColor(mColors[i%mColors.length]);
            if( i==mPrizes.length-1 &&
                    mColors[i%mColors.length]==mColors[0] ){
                mPaint.setColor(mColors[1]);
            }
            canvas.drawArc(rectF,0,sweepAngle,true,mPaint);
            mPaint.setColor(Color.BLACK);
            canvas.drawLine(circleX,circleX+circleR,circleY,circleY,mPaint);
            if(mColors[i] == Color.BLACK){
                mPaint.setColor(Color.WHITE);
            }
            //旋转画布
            canvas.rotate(sweepAngle/2,circleX,circleY);
            mPaint.setTextSize(40);
            canvas.drawText(mPrizes[i],circleX+circleR/2,circleY,mPaint);
            //旋转画布
            canvas.rotate(sweepAngle/2,circleX,circleY);
        }
    }
}
