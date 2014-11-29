package me.isming.tools.cvfilter.library;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;

import com.lofter.android.widget.filters.LibCvFilter;

/**
 * Created by sam on 14/11/29.
 */
public class CvCircularVignette implements ICVFilter {

    float a,b,c;

    public CvCircularVignette(float fa, float fb, float fc) {
        a = fa;
        b = fb;
        c = fc;
    }

    private static ImageData a(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3)
    {
        RadialGradient localRadialGradient = new RadialGradient(paramFloat1, paramFloat2, paramFloat3, new int[] { -1, -1, -16777216 }, new float[] { 0.0F, 0.5F, 1.0F }, Shader.TileMode.CLAMP);
        Paint localPaint = new Paint();
        localPaint.setDither(true);
        localPaint.setShader(localRadialGradient);
        Bitmap localBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
        new Canvas(localBitmap).drawRect(0.0F, 0.0F, paramInt1, paramInt2, localPaint);
        ImageData localq = new ImageData(localBitmap);
        localq.readByte();
        return localq;
    }

    private ImageData b(ImageData paramq)
    {
        int i = paramq.getWidth();
        int j = paramq.getHeight();
        if ((this.a < 0.0F) || (this.b < 0.0F) || (this.a > i) || (this.b > j))
            return paramq;
        ImageData localq = a(i, j, this.a / i, this.b / j, this.c);
        paramq.readByte();
        int[] arrayOfInt = paramq.getDataByte();
        LibCvFilter.ShowMask(arrayOfInt, localq.getDataByte(), i, j);
        paramq.setDataBytes(arrayOfInt);
        System.gc();
        return paramq;
    }

    @Override
    public ImageData convert(ImageData source) {
        int i = source.getWidth();
        int j = source.getHeight();
        this.a *= i;
        this.b *= j;
        if ((this.a < 0.0F) || (this.b < 0.0F) || (this.a > i) || (this.b > j))
            return source;
        ImageData localq = a(i, j, this.a, this.b, this.c);
        source.readByte();
        int[] arrayOfInt = source.getDataByte();
        LibCvFilter.AddByMask(arrayOfInt, localq.getDataByte(), i, j);
        source.setDataBytes(arrayOfInt);
        System.gc();
        return source;
    }

    @Override
    public String getName() {
        return "圆形虚化";
    }

    @Override
    public int getResId() {
        return 0;
    }
}
