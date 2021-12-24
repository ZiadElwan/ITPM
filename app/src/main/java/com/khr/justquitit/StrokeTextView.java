package com.khr.justquitit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StrokeTextView extends androidx.appcompat.widget.AppCompatTextView {
    public StrokeTextView(@NonNull Context context) {
        super(context);
    }

    public StrokeTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StrokeTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Paint paint = new Paint();
    private String mText;
    protected void onDraw(Canvas canvas){
        mText = this.getText().toString();

        //make shadow
        paint.setTextSize(this.getTextSize());
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(10);
        paint.setShadowLayer(10,2,2, Color.DKGRAY);
        canvas.drawText(mText,10,getLineHeight(),paint);

        paint.clearShadowLayer();
        paint.setTextSize(this.getTextSize());
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(10);
        paint.setColor(Color.rgb(230,230,230));
        canvas.drawText(mText,10,getLineHeight(),paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(this.getTextColors().getDefaultColor());
        canvas.drawText(mText,10,getLineHeight(),paint);

    }
}
