package me.isming.tools.cvfilter.library;

import com.lofter.android.widget.filters.LibCvFilter;

/**
 * Created by sam on 14/11/29.
 */
public class CvInkwell implements ICVFilter {

    @Override
    public ImageData convert(ImageData source) {
        int width = source.getWidth();
        int height = source.getHeight();
        source.readByte();
        int[] dataByte = source.getDataByte();
        LibCvFilter.CvInkwell(dataByte, width, height, source.a);
        source.setDataBytes(dataByte);
        return source;
    }

    @Override
    public String getName() {
        return "黑白";
    }

    @Override
    public int getResId() {
        return R.drawable.filter_heibai;
    }
}
