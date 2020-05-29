package com.gwl.chartlibrary.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.gwl.chartlibrary.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 折线图
 */
public class LineChartView extends View {

    private Paint xyPaint;
    private int paddingLeft;
    private int paddingRight;
    private int paddingBottom;
    private int paddingTop;
    private int mWidth;
    private int mHeight;
    private List<PointF> pointFs = new ArrayList<PointF>();
    ;
    private float maxX;
    private float maxRightValus;
    private TextPaint textPaint;
    private float maxY;
    private float maxTopValus;
    private final float DEFALUT_PADDING = DensityUtil.dip2px(getContext(), 25);
    private final float DEFALUT_PADDING_BOTTOM = DensityUtil.dip2px(getContext(), 15);
    private float xWidth;
    private float yHeight;
    private Paint markPaint;
    private Path path;
    private Paint linePaint;
    private Path pathCircle;
    private Paint pointPaint;
    private float animationValue;

    public LineChartView(Context context) {
        this(context, null);
    }

    public LineChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureSize(getViewWidth(), widthMeasureSpec, true), measureSize(getViewHeight(), heightMeasureSpec, false));
    }

    private int getViewWidth() {
        float textWidth = textPaint.measureText(String.valueOf(maxRightValus));
        return (int) (textWidth * (pointFs.size() - 1) * 1.5);
    }

    private int getViewHeight() {
        float textWidth = textPaint.measureText(String.valueOf(maxTopValus));
        return (int) (textWidth * (pointFs.size() - 1) * 1.5);
    }

    private int measureSize(int size, int measureSpec, boolean isWidth) {
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            if (mode == MeasureSpec.AT_MOST) {
                size = size + (isWidth ? getPaddingLeft() + getPaddingRight() : getPaddingBottom() + getPaddingTop());
                result = Math.min(size, specSize);
            }
        }
        return result;
    }

    private void init() {
        xyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        xyPaint.setColor(Color.BLACK);
        xyPaint.setStyle(Paint.Style.STROKE);
        xyPaint.setStrokeWidth(5);

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(DensityUtil.sp2px(getContext(), 12));
        textPaint.setTextAlign(Paint.Align.CENTER);

        markPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        markPaint.setStrokeWidth(2);
        markPaint.setColor(Color.RED);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStrokeWidth(3);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(Color.BLUE);

        pointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pointPaint.setStrokeWidth(3);
        pointPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        pointPaint.setColor(Color.WHITE);

        path = new Path();
        pathCircle = new Path();

    }

    private void getXyMaxValue() {

        maxX = 0;
        maxY = 0;
        for (PointF pointF : pointFs) {
            if (pointF.x > maxX) {
                maxX = pointF.x;
            }
            if (pointF.y > maxY) {
                maxY = pointF.y;
            }
        }
        if (maxX % (pointFs.size() - 1) == 0) {
            maxRightValus = maxX;
        } else {
            maxRightValus = maxX - maxX % (pointFs.size() - 1) + (pointFs.size() - 1);
        }
        if (maxY % (pointFs.size() - 1) == 0) {
            maxTopValus = maxY;
        } else {
            maxTopValus = maxY - maxY % (pointFs.size() - 1) + (pointFs.size() - 1);
        }
    }

    public void setData(List<PointF> mPointFs) {
        this.pointFs = mPointFs;
        getXyMaxValue();
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        paddingLeft = getPaddingLeft();
        paddingRight = getPaddingRight();
        paddingBottom = getPaddingBottom();
        paddingTop = getPaddingTop();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1.0f);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animationValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (pointFs.size()<=0)return;
        drawXY(canvas);//画XY轴线
        drawMarkXY(canvas);//画XY刻度线
        drawPoint(canvas);//画点和连线
    }

    private void drawXY(Canvas canvas) {
        //绘制X轴
        canvas.drawLine(paddingLeft + textPaint.measureText(String.valueOf(maxTopValus)) + DEFALUT_PADDING, mHeight - paddingBottom - (textPaint.descent() - textPaint.ascent()) - DEFALUT_PADDING, mWidth - paddingRight - DEFALUT_PADDING, mHeight - paddingBottom - (textPaint.descent() - textPaint.ascent()) - DEFALUT_PADDING, xyPaint);
        //绘制Y轴
        canvas.drawLine(paddingLeft + textPaint.measureText(String.valueOf(maxTopValus)) + DEFALUT_PADDING, mHeight - paddingBottom - (textPaint.descent() - textPaint.ascent()) - DEFALUT_PADDING, paddingLeft + textPaint.measureText(String.valueOf(maxTopValus)) + DEFALUT_PADDING, paddingTop + DEFALUT_PADDING, xyPaint);
        xWidth = (mWidth - paddingRight - DEFALUT_PADDING) - (paddingLeft + textPaint.measureText(String.valueOf(maxTopValus)) + DEFALUT_PADDING);
        yHeight = (mHeight - paddingBottom - (textPaint.descent() - textPaint.ascent()) - DEFALUT_PADDING) - (paddingTop + DEFALUT_PADDING);
        for (int i = 1; i <= pointFs.size() - 1; i++) {
            canvas.drawText(String.valueOf((int) maxRightValus / (pointFs.size() - 1) * i), (paddingLeft + textPaint.measureText(String.valueOf(maxTopValus)) + DEFALUT_PADDING) + (xWidth / (pointFs.size() - 1)) * i, (mHeight - paddingBottom - (textPaint.descent() - textPaint.ascent()) - DEFALUT_PADDING_BOTTOM + (Math.abs(textPaint.ascent()) - (textPaint.descent() - textPaint.ascent()) / 2.0f)), textPaint);
        }
        for (int i = 0; i <= pointFs.size() - 1; i++) {
            canvas.drawText(String.valueOf((int) maxTopValus / (pointFs.size() - 1) * i), paddingLeft + DEFALUT_PADDING, (mHeight - paddingBottom - (textPaint.descent() - textPaint.ascent()) - DEFALUT_PADDING) - (yHeight / (pointFs.size() - 1)) * i + (Math.abs(textPaint.ascent()) - (textPaint.descent() - textPaint.ascent()) / 2.0f), textPaint);
        }
    }

    private void drawMarkXY(Canvas canvas) {
        for (int i = 1; i <= pointFs.size() - 1; i++) {
            canvas.drawLine(paddingLeft + textPaint.measureText(String.valueOf(maxTopValus)) + DEFALUT_PADDING, mHeight - paddingBottom - (textPaint.descent() - textPaint.ascent()) - DEFALUT_PADDING - (yHeight / (pointFs.size() - 1)) * i, mWidth - paddingRight - DEFALUT_PADDING, mHeight - paddingBottom - (textPaint.descent() - textPaint.ascent()) - DEFALUT_PADDING - (yHeight / (pointFs.size() - 1)) * i, markPaint);
        }
    }

    private void drawPoint(Canvas canvas) {
        for (int i = 0; i < pointFs.size(); i++) {
            float x = (paddingLeft + textPaint.measureText(String.valueOf(maxTopValus)) + DEFALUT_PADDING) + xWidth / maxRightValus * pointFs.get(i).x;
            float y = mHeight - paddingBottom - (textPaint.descent() - textPaint.ascent()) - DEFALUT_PADDING - yHeight / maxTopValus * pointFs.get(i).y * animationValue;
            //linePaint.setPathEffect(new DiscretePathEffect(10.0F, 2.0F));
            // linePaint.setPathEffect(new CornerPathEffect(350));
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }

        canvas.drawPath(path, linePaint);
        for (int i = 0; i < pointFs.size(); i++) {
            float x = (paddingLeft + textPaint.measureText(String.valueOf(maxTopValus)) + DEFALUT_PADDING) + xWidth / maxRightValus * pointFs.get(i).x;
            float y = mHeight - paddingBottom - (textPaint.descent() - textPaint.ascent()) - DEFALUT_PADDING - yHeight / maxTopValus * pointFs.get(i).y * animationValue;
            canvas.drawCircle(x, y, 10, pointPaint);
            canvas.drawCircle(x, y, 10, linePaint);
        }
        path.reset();
    }
}
