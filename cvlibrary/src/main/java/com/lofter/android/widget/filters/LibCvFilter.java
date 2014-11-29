package com.lofter.android.widget.filters;

/**
 * Created by sam on 14/11/28.
 */
public class LibCvFilter {

    static
    {
        System.loadLibrary("CvFilter");
    }

    public static native int[] AddByMask(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2);

    public static native int[] CvAmaro(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3);

    public static native int[] CvEarlyBird(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3);

    public static native int[] CvHDR(int[] paramArrayOfInt, int paramInt1, int paramInt2);

    public static native int[] CvHudson(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3);

    public static native int[] CvInkwell(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3);

    public static native int[] CvKelvin(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3);

    public static native int[] CvLomo(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3);

    public static native int[] CvNashille(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3);

    public static native int[] CvRise(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3);

    public static native int[] CvWalden(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3);

    public static native int[] CvXproII(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2, int paramInt3);

    public static native int[] ShowMask(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt1, int paramInt2);

    public static native int[] TestCV(int[] paramArrayOfInt, int paramInt1, int paramInt2);

}
