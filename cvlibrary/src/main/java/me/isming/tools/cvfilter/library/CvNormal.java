package me.isming.tools.cvfilter.library;

/**
 * Created by sam on 14/11/29.
 */
public class CvNormal implements ICVFilter {

    @Override
    public ImageData convert(ImageData source) {
        if (source.a == 1) {
            return new CvHDR().convert(source);
        } else {
            source.result = source.origin;
            return source;
        }
    }

    @Override
    public String getName() {
        return "原图";
    }

    @Override
    public int getResId() {
        return R.drawable.filter_normal;
    }
}
