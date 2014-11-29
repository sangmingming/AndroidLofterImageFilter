package me.isming.tools.cvfilter.library;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.nio.IntBuffer;

/**
 * Created by sam on 14/11/28.
 */
public final class ImageData {
    public int a;
    public Bitmap origin;
    public Bitmap result;
    protected int[] dataByte;
    private String format;
    private int width;
    private int height;

    public ImageData(Bitmap imgBitmap) {
        origin = imgBitmap;
        format = "jpg";
        width = imgBitmap.getWidth();
        height = imgBitmap.getHeight();
    }

    private ImageData(Bitmap imgBitmap, int intValue) {
        this(imgBitmap);
        a = intValue;
    }

    public ImageData(Bitmap imgBitmap, boolean isValue) {
        this(imgBitmap);
        a = isValue ?  1 : 0;
    }

    private static ImageData createFromResource(Activity activity, int resId) {
        return new ImageData(BitmapFactory.decodeResource(activity.getResources(), resId));
    }

    private void xxx(int val1, int val2, int val3) {
        dataByte[val1 + val2*origin.getWidth()] = val3;
    }

    private void setOriginBitmap(Bitmap bitmap) {
        origin = bitmap;
    }

    private void setFormat(String format) {
        this.format = format;
    }

    private void resetByte(int value) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j ++) {
                dataByte[j + i * origin.getWidth()] = value;
            }
        }
    }

    private void setWidth(int srcWidth) {
        width = srcWidth;
    }

    private int getByte(int val1, int val2) {
        return dataByte[val1 + val2 * width];
    }

    private void setHeight(int srcHeight) {
        height = srcHeight;
    }

    private static int getValidValue(int value) {
        if (value < 0) {
            return 0;
        } else if(value <= 255) {
            return value;
        } else {
            return 255;
        }
    }

    private ImageData copy() {
        return new ImageData(origin, a);
    }

    public Bitmap getResult() {
        return result;
    }

    private void recycle() {
        if (result != null && !result.isRecycled()) {
            result.recycle();;
            result = null;
            System.gc();
        }
    }

    private String getFormat() {
        return format;
    }

    public final int getDateByteValue(int val1, int val2) {
        return (0xFF0000 & dataByte[val1 + val2 * width]) >> 16;
    }

    public final void readByte() {
        dataByte = new int[width * height];
        origin.getPixels(dataByte, 0, width, 0, 0, width, height);
        for (int i = 0; i < height; i ++) {
            for (int j = 0; j < width; j ++) {
                int k = j + i * width;
                int data16 = 0xFF & dataByte[k] >> 16;
                int data8 = 0xFF & dataByte[k] >> 8;
                int data10 = 0xFF & dataByte[k];
                dataByte[k] = (data16 | (0xFF000000 | data10 << 16 | data8 << 8));
            }
        }
    }

    public final void rotate(int degrees) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        if (result == null) {
            result = origin;
        }

        result = Bitmap.createBitmap(result, 0,0, width, height, matrix, true);
        width = result.getWidth();
        height = result.getHeight();
    }

    public final void setByteData(int x, int y, int data16, int data8, int data10) {
        int i = data10 + (-16777216 + (data16 << 16) + (data8 << 8));
        dataByte[x + y*origin.getWidth()] = i;
    }

    public final void setDataBytes(int[] values) {
        dataByte = values;
    }

    public final int getDataByte(int x, int y) {
        return (0xFF00 & dataByte[(x + y * width)]) >> 8;
    }

    public final void createResult() {
        if (dataByte == null) {
            return;
        }

        IntBuffer buffer = IntBuffer.wrap(dataByte);
        dataByte = null;
        result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        result.copyPixelsFromBuffer(buffer);
        buffer.clear();
    }

    public final int getWidth() {
        return width;
    }

    public final int getHeight() {
        return height;
    }

    public final int[] getDataByte() {
        return dataByte;
    }

    public final int c(int x, int y)
    {
        return 0xFF & this.dataByte[(x + y * width)];
    }
















}
