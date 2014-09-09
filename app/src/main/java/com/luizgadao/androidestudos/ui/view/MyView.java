package com.luizgadao.androidestudos.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.luizgadao.androidestudos.utils.MeasuresUtils;

/**
 * Created by luizcarlos on 09/09/14.
 */
public class MyView extends View
{

    private Paint black;
    private Paint red;
    private Paint blue;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //setBackgroundColor( Color.LTGRAY );

        black = new Paint();
        black.setARGB(255, 0, 0, 0);

        red = new Paint();
        red.setARGB( 255, 255, 0, 0 );

        blue = new Paint();
        blue.setARGB( 255, 0, 0, 255 );

        setFocusable(true);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //px
        int y = 10;
        //dp to pixel
        int value50 = MeasuresUtils.dipToPixel( 50 );
        int value100 = MeasuresUtils.dipToPixel( 100 );

        //canvas.drawRect( y, y, y + 50, y + 50, blue );
        //canvas.drawLine( y + 50, y + 50, 100, 100, black );
        //canvas.drawCircle( 100, 100, 20, red );

        canvas.drawRect( y, y, y + value50, y + value50, blue );
        canvas.drawLine( y + value50, y + value50, value100, value100, black );
        canvas.drawCircle( value100, value100, MeasuresUtils.dipToPixel( 20 ), red );

        //draw triangle
        black.setAntiAlias( true );
        y = MeasuresUtils.dipToPixel( 150 );
        int sideTriangle = MeasuresUtils.dipToPixel( 30 );
        canvas.drawLine( 10, y, 10 + sideTriangle, y - sideTriangle, black );
        canvas.drawLine( 10 + sideTriangle, y - sideTriangle, 10 + 2*sideTriangle,  y, black );
        canvas.drawLine( 10, y, 10 + 2*sideTriangle, y, black);

        y = MeasuresUtils.dipToPixel( 280 );
        sideTriangle = MeasuresUtils.dipToPixel( 80 );

        red.setStrokeWidth(1);
        red.setStyle( Paint.Style.FILL_AND_STROKE );
        red.setColor( Color.YELLOW );
        red.setAntiAlias( true );

        Point point1_returned = new Point( 10, y );
        Point point2_returned = new Point( 10 + sideTriangle, y - sideTriangle );
        Point point3_returned = new Point( 10 + 2*sideTriangle, y );

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);

        path.moveTo(point1_returned.x, point1_returned.y);
        path.lineTo(point2_returned.x, point2_returned.y);
        path.moveTo(point2_returned.x, point2_returned.y);
        path.lineTo(point3_returned.x, point3_returned.y);
        path.moveTo(point3_returned.x, point3_returned.y);
        path.lineTo(point1_returned.x, point1_returned.y);
        path.close();

        canvas.drawPath( path, red );

        canvas.drawCircle( point1_returned.x, point1_returned.y, 4, blue );
        canvas.drawCircle( point2_returned.x, point2_returned.y, 4, blue );
        canvas.drawCircle( point3_returned.x, point3_returned.y, 4, blue );

    }
}
