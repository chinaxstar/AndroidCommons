package xstar.com.library.commons.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import xstar.com.library.commons.R;


/**
 * @author xstar on 2016/2/23.
 */
public class MarqeeTextView extends View {
    public MarqeeTextView(Context context) {
        this(context, null);
    }

    public MarqeeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarqeeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    TextPaint textPaint;
    LocationCompute locationCompute;
    private int DEFAULT_SPEED = 10;

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.MarqeeTextView, defStyleAttr, 0);
        text = t.getString(R.styleable.MarqeeTextView_text);
        speed = t.getInt(R.styleable.MarqeeTextView_speed, DEFAULT_SPEED);
        textColor = t.getColor(R.styleable.MarqeeTextView_textColor, Color.BLACK);
        textSize = t.getDimension(R.styleable.MarqeeTextView_textSize, 14);
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawText(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private Rect textRect = new Rect();

    private void drawText(Canvas canvas) {
        if (text != null) {
            textPaint.setColor(textColor);
            textPaint.setTextSize(textSize);
            textPaint.getTextBounds(text, 0, text.length(), textRect);
            canvas.clipRect(0, 0, getWidth(), getHeight());
            if (locationCompute == null) {
                locationCompute = new LocationCompute(canvas.getWidth(), (canvas.getHeight() + textRect.height()) >> 1, speed);
            }
            float[] location = locationCompute.getLocation();
            if (textRect.width() + location[0] > 0) {
                //移出canvas
                canvas.drawText(text, 0, text.length(), location[0], location[1], textPaint);
                canvas.restore();
                invalidate();
            } else {
                if (marqeeListener != null) marqeeListener.marqeeFinish(this);
            }
        }
    }

    public void repeat() {
        if (locationCompute == null) return;
        locationCompute.restore();
        locationCompute.speed = speed;
        invalidate();
    }

    private String text;
    private int textId;
    private int speed;
    private int colorId;
    private int textSizeId;
    private float textSize;
    private int textColor;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        locationCompute = null;
        invalidate();
    }

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
        setText(getContext().getString(textId));
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        if (locationCompute == null) return;
        locationCompute.speed = speed;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
        textColor=getResources().getColor(colorId);
    }

    public int getTextSizeId() {
        return textSizeId;
    }

    public void setTextSizeId(int textSizeId) {
        this.textSizeId = textSizeId;
        setTextSize(getResources().getDimension(textSizeId));
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public class LocationCompute {
        public LocationCompute() {
            this(0, 0, DEFAULT_SPEED);
        }

        public float x, y;
        public float start_x, start_y;
        public int speed;
        private long oldTime;
        private long curTime;

        public LocationCompute(float x, float y, int speed) {
            this.start_x = x;
            this.start_y = y;
            this.x = x;
            this.y = y;
            this.speed = speed;
        }

        public float[] getLocation() {
            curTime = System.currentTimeMillis();
            if (oldTime > 0) {
                int s = (int) (((curTime - oldTime) * speed) >> 10);
                if (s > 0) {
                    x -= s;
                    oldTime = curTime;
                }
            } else
                oldTime = curTime;
            return new float[]
                    {x, y};
        }

        public void restore() {
            oldTime = 0;
            x = start_x;
            y = start_y;
        }
    }

    public interface MarqeeListener {
        void marqeeFinish(MarqeeTextView marqeeTextView);
    }

    private MarqeeListener marqeeListener;

    public MarqeeListener getMarqeeListener() {
        return marqeeListener;
    }

    public void setMarqeeListener(MarqeeListener marqeeListener) {
        this.marqeeListener = marqeeListener;
    }
}
