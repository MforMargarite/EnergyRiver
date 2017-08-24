package com.whucs.energyriver.Widget;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.whucs.energyriver.R;


public class AirControlDialog extends AlertDialog {
    public AirControlDialog(Context context) {
        super(context);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
    }

    public AirControlDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
    }

    public AirControlDialog(Context context, int themeResId) {
        super(context, themeResId);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
    }


}
