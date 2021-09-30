package afei.lb.prizewheel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


public class Pointer extends View {
    private Paint mPaint;

    private int mDegrees = 0;

    public Pointer(Context context) {
        super(context);
        init(context,null);
    }

    public Pointer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public Pointer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    /**
     * 初始化
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
       mPaint = new Paint();
       mPaint.setAntiAlias(true);
       mPaint.setColor(Color.BLACK);

    }

    public void setDegrees(int degrees) {
        mDegrees = degrees;
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
        int circleR = totalLength/6;
        //triangle三角形
        int triangleLeftX = circleX-circleR;
        int triangleCenterX = circleX;
        int triangleRightX = circleX+circleR;
        int triangleBottomY = circleY;
        int triangleTopY = circleY - totalLength/3;


//        canvas.translate(rotateX,rotateY);
        canvas.rotate(mDegrees,circleX,circleY);

        canvas.drawCircle(circleX,circleY,circleR,mPaint);
        Path path = new Path();
        path.setFillType(Path.FillType.WINDING);
        path.moveTo(triangleLeftX,triangleBottomY);
        path.lineTo(triangleCenterX,triangleTopY);
        path.lineTo(triangleRightX,triangleBottomY);
        path.lineTo(triangleLeftX,triangleBottomY);
        canvas.drawPath(path,mPaint);
    }

}
