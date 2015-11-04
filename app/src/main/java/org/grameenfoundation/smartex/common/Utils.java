package org.grameenfoundation.smartex.common;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

/**
 * Created by David on 9/15/2015.
 */
public class Utils {

    public static Drawable getDrawable(Context context, int id) {

        return ContextCompat.getDrawable(context, id);

        /*Drawable myDrawable;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            myDrawable = context.getResources().getDrawable(id, context.getTheme());
        } else {
            myDrawable = context.getResources().getDrawable() getDrawable(id);
        }

        return myDrawable;
        */
    }
}
