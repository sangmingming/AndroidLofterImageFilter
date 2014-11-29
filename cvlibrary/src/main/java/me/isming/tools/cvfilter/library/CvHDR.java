package me.isming.tools.cvfilter.library;

import com.lofter.android.widget.filters.LibCvFilter;

/**
 * Created by sam on 14/11/29.
 */
public class CvHDR implements ICVFilter {

    @Override
    public ImageData convert(ImageData source) {
        int width = source.getWidth();
        int height = source.getHeight();
        source.readByte();
        int[] dataByte = source.getDataByte();
        LibCvFilter.CvHDR(dataByte, width, height);
        source.setDataBytes(dataByte);
        return source;
    }

    @Override
    public String getName() {
        return "黑白";
    }

    @Override
    public int getResId() {
        return 0;
    }
}
