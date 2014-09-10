package com.luizgadao.androidestudos.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.luizgadao.androidestudos.utils.MeasuresUtils;

/**
 * Created by luizcarlos on 09/09/14.
 */
public class MyViewInteractionKeyboard extends View
{
    private boolean selected;
    private Paint black;
    private Paint red;
    private Paint blue;
    private Rect rect;
    private int y = 50;
    private int x = 50;
    private int value50;

    public MyViewInteractionKeyboard(Context context) {
        super(context);
    }

    public MyViewInteractionKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);

        setBackgroundColor( Color.LTGRAY );

        black = new Paint();
        black.setAntiAlias(true);
        black.setARGB(255, 0, 0, 0);

        red = new Paint();
        red.setARGB( 255, 255, 0, 0 );

        blue = new Paint();
        blue.setARGB( 255, 0, 0, 255 );

        setFocusable(true);
    }

    public MyViewInteractionKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //dp to pixel
        value50 = MeasuresUtils.dipToPixel(50);
        int value100 = MeasuresUtils.dipToPixel( 100 );

        //canvas.drawRect( y, y, y + 50, y + 50, blue );
        //canvas.drawLine( y + 50, y + 50, 100, 100, black );
        //canvas.drawCircle( 100, 100, 20, red );

        rect = new Rect( x, y, x + value50, y + value50 );
        canvas.drawRect( rect, blue );
        canvas.drawCircle( value100, value100, MeasuresUtils.dipToPixel( 20 ), red );
        canvas.drawLine( (int) (x + (value50 * 0.5)) , (int) (y + (value50 * 0.5)), value100, value100, black );
    }

    @Override
    // captura novo tamanho da tela.
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ( event.KEYCODE_DPAD_DOWN == keyCode ) {
            y += 10;
            invalidate();
            return true;
        }
        else if ( event.KEYCODE_DPAD_UP == keyCode ) {
            y -= 10;
            invalidate();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch ( event.getAction() )
        {
            case MotionEvent.ACTION_DOWN:
                if ( rect.contains( (int)x, (int)y ) )
                {
                    selected = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if ( selected )
                {
                    this.x = (int) (x - (value50 * 0.5));
                    this.y = (int) (y - (value50 * 0.5));
                }
                break;
            case MotionEvent.ACTION_UP:
                selected = false;
                break;
        }

        invalidate();
        return true;
    }
}
