package com.gwl.chartlibrary.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.gwl.chartlibrary.R;
import com.gwl.chartlibrary.utils.DensityUtil;

import java.util.ArrayList;

/**
 * 圆饼图
 */
public class PieChartView extends View {

    private int mWidth;
    private int mHeight;
    private Paint paint;
    private int paddingBottom;
    private int paddingTop;
    private int paddingRight;
    private int paddingLeft;
    private ArrayList<Float>  listData = new ArrayList<>();
    private ArrayList<Integer> listColor = new ArrayList<>();
    private float totalData;
    private float startAngle = 0;
    private float sweepAngle = 0;
    private Paint midlePaint;
    private Path path;
    private TextPaint textPaint;
    private Region region;
    private Region globleRegion;
    private Path path2;
    private Region region2;
    ArrayList<Region> regionList = new ArrayList<>();
    private ValueAnimator valueAnimator;
    private RectF rectF;
    private Canvas mCanvas;
    private float animatedValue = 0f;
    private int viewWidth;
    Matrix matrix = new Matrix();
    private Path path3;
    private int index;
    private boolean touchTag = false;

    public PieChartView(Context context) {
        this(context, null);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(3);

        midlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        midlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        midlePaint.setColor(Color.BLUE);
        midlePaint.setStrokeWidth(3);

        path = new Path();
        path3 = new Path();


        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(DensityUtil.sp2px(getContext(), 14));

        region = new Region();
    }

    public void setData(ArrayList<Float>  mListData,ArrayList<Integer> mListColor) {
        this.listData=mListData;
        this.listColor=mListColor;
        getTotalData();
        invalidate();
    }

    private void getTotalData() {
        totalData = 0;
        for (Float listDatum : listData) {
            totalData += listDatum;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureSize(true, getCurrentWidth(), widthMeasureSpec), measureSize(false, getCurrentHeight(), heightMeasureSpec));
    }

    private int getCurrentHeight() {
        float textWidth = textPaint.measureText(String.valueOf(totalData));
        return (int) (textWidth*6* 1.1f);
    }

    private int getCurrentWidth() {
        float textWidth = textPaint.measureText(String.valueOf(totalData));
        return (int) (textWidth*6* 1.1f);
    }

    private int measureSize(boolean isWidth, int size, int measureSpec) {
        int result = 0;
        int measureSize = MeasureSpec.getSize(measureSpec);
        int mode = MeasureSpec.getMode(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = measureSize;
        } else {
            int padding = isWidth ? getPaddingLeft() + getPaddingRight() : getPaddingTop() + getPaddingBottom();
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(size + padding, measureSize);
            }
        }
        return result;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        paddingLeft = getPaddingLeft();
        paddingRight = getPaddingRight();
        paddingTop = getPaddingTop();
        paddingBottom = getPaddingBottom();
        viewWidth = (int) (Math.min(mWidth - paddingRight - paddingLeft, mHeight - paddingBottom - paddingTop) / 2*0.9f);
        globleRegion = new Region(-viewWidth, -viewWidth, viewWidth, viewWidth);
        rectF = new RectF(-viewWidth, -viewWidth, viewWidth, viewWidth);
        valueAnimator = ValueAnimator.ofFloat(0f, 360f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatedValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(3000);
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (listData.size()<=0)return;
        mCanvas = canvas;
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.getMatrix().invert(matrix);

       /* for (int i = 0; i < listData.size(); i++) {
            sweepAngle = listData.get(i) / totalData * 360;
            paint.setColor(listColor.get(i));
            canvas.drawArc(rectF, startAngle, sweepAngle, true, paint);
            startAngle += sweepAngle;
            //region.setPath()
        }*/
        path.addCircle(rectF.left + rectF.width() / 2.0f, rectF.top + rectF.height() / 2.0f, rectF.width() / 4.0f, Path.Direction.CW);
        //canvas.clipPath(path);
        // region.setPath(path,globleRegion);
        //canvas.drawPath(path, midlePaint);
        regionList.clear();
        startAngle = 0f;
        for (int i = 0; i < listData.size(); i++) {

            sweepAngle = listData.get(i) / totalData * 360;
            paint.setColor(listColor.get(i));
            path2 = new Path();
            region2 = new Region();
            path2.moveTo(rectF.left + rectF.width() / 2.0f, rectF.top + rectF.height() / 2.0f);
            float drawAngle = Math.min(sweepAngle - 2, animatedValue - startAngle);

            if (drawAngle > 0) {
                if (index == i && touchTag) {
                    rectF = new RectF(-viewWidth * 1.1f, -viewWidth * 1.1f, viewWidth * 1.1f, viewWidth * 1.1f);
                    path2.arcTo(rectF, startAngle, drawAngle);
                    //path2.close();
                    region2.setPath(path2, globleRegion);
                    regionList.add(region2);
                    path2.op(path, Path.Op.DIFFERENCE);
                    mCanvas.drawPath(path2, paint);
                    drawText(canvas, listData.get(i), startAngle, sweepAngle / 2);
                    startAngle += sweepAngle;
                } else {
                    rectF = new RectF(-viewWidth, -viewWidth, viewWidth, viewWidth);
                    path2.arcTo(rectF, startAngle, drawAngle);
                    //path2.close();
                    region2.setPath(path2, globleRegion);
                    regionList.add(region2);
                    path2.op(path, Path.Op.DIFFERENCE);
                    mCanvas.drawPath(path2, paint);
                    drawText(canvas, listData.get(i), startAngle, sweepAngle / 2);
                    startAngle += sweepAngle;
                }
            }

        }
        path.reset();
        path2.reset();
        canvas.drawText(String.valueOf(totalData), rectF.left + rectF.width() / 2.0f, rectF.top + rectF.height() / 2.0f + (Math.abs(textPaint.ascent()) - (textPaint.descent() - textPaint.ascent()) / 2.0f), textPaint);


    }

    private void drawText(Canvas canvas, float value, float startAngle, float sweepAngle) {
        if (animatedValue > startAngle + sweepAngle) {
            float x = (float) (Math.cos(Math.toRadians(startAngle + sweepAngle)) * rectF.width() / 2.0f * 0.75);
            float y = (float) (Math.sin(Math.toRadians(startAngle + sweepAngle)) * rectF.width() / 2.0f * 0.75);
            canvas.drawText(String.valueOf(value), x, y, textPaint);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float[] pt = new float[2];
                pt[0] = (int) event.getX();
                pt[1] = (int) event.getY();
                matrix.mapPoints(pt);
                if (region.contains((int) pt[0], (int) pt[1])) {
                    Toast.makeText(getContext(), "点击了中间", Toast.LENGTH_SHORT).show();
                }
                for (int i = 0; i < regionList.size(); i++) {
                    if (regionList.get(i).contains((int) pt[0], (int) pt[1])) {

                        index = i;
                        touchTag = true;
                        Toast.makeText(getContext(), "点击了其他" + listData.get(i) + "==" + index, Toast.LENGTH_SHORT).show();
                    }
                    invalidate();
                }

                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                touchTag = false;
                rectF = new RectF(-viewWidth, -viewWidth, viewWidth, viewWidth);
                invalidate();
                break;
        }
        return true;
    }
}

