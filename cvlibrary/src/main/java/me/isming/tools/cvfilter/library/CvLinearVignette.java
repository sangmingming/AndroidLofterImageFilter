package me.isming.tools.cvfilter.library;

/**
 * Created by sam on 14/11/29.
 */
public class CvLinearVignette implements ICVFilter {

    @Override
    public ImageData convert(ImageData source) {
       return null;
    }

    @Override
    public String getName() {
        return "线性虚化";
    }

    @Override
    public int getResId() {
        return 0;
    }
}
