package util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public final class ToastyMatt{
    public static Toast makeMToast(View v, String message, boolean longTimer){
        Toast t;
        Context c = v.getContext();
        if(longTimer){
            t = Toast.makeText(c, message, Toast.LENGTH_LONG);
        }else{
            t = Toast.makeText(c, message, Toast.LENGTH_SHORT);
        }
        t.setGravity(Gravity.CENTER, 0,0);
        return t;
    }
}
