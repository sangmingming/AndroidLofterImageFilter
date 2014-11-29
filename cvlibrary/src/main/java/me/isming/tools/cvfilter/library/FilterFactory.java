package me.isming.tools.cvfilter.library;

import android.app.Activity;

/**
 * Created by sam on 14/11/29.
 */
public class FilterFactory {
    public static ICVFilter[] createFilters(Activity activity) {
        ICVFilter[] filters = new ICVFilter[11];
        filters[0] = new CvNormal();
        filters[1] = new CvAmaro(activity);
        filters[2] = new CvNashille();
        filters[3] = new CvHudson(activity);
        filters[4] = new CvRise(activity);
        filters[5] = new CvLomo(activity);
        filters[6] = new CvKelvin();
        filters[7] = new CvEarlyBird(activity);
        filters[8] = new CvWalden(activity);
        filters[9] = new CvXproII(activity);
        filters[10] = new CvInkwell();
        return filters;
    }
}
