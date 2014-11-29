package me.isming.tools.cvfilter.library;

/**
 * Created by sam on 14/11/28.
 */
public interface ICVFilter {
    public static final double e = 3.141592653589793D;
    public abstract  ImageData convert(ImageData source);
    public abstract String getName();
    public abstract int getResId();
}
