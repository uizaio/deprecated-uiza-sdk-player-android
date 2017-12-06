package vn.loitp.core.utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

/**
 * Created by www.muathu@gmail.com on 12/6/2017.
 */

public class LDialogUtil {
    //https://stackoverflow.com/questions/43513919/android-alert-dialog-with-one-two-and-three-buttons
    public interface CallbackShowOne {
        public void onClick();
    }

    public static void showOne(Context context, String title, String message, String button, final CallbackShowOne callbackShowOne) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (callbackShowOne != null) {
                    callbackShowOne.onClick();
                }
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
