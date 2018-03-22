package vn.loitp.core.utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import loitp.core.R;

/**
 * Created by www.muathu@gmail.com on 12/6/2017.
 */

public class LDialogUtil {
    //https://stackoverflow.com/questions/43513919/android-alert-dialog-with-one-two-and-three-buttons
    public interface CallbackShowOne {
        public void onClick();
    }

    private static List<AlertDialog> alertDialogList = new ArrayList<>();

    private static void clearAllDialog() {
        for (AlertDialog alertDialog : alertDialogList) {
            if (alertDialog != null) {
                alertDialog.cancel();
            }
        }
        alertDialogList.clear();
    }

    public static void showOne(Context context, String title, String message, String button, final CallbackShowOne callbackShowOne) {
        clearAllDialog();
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
        alertDialogList.add(dialog);
    }

    public static void showError(Context context, int errCode, String title, CallbackShowOne callbackShowOne) {
        String msg;
        switch (errCode) {
            case 400:
                msg = context.getString(R.string.err_400);
                break;
            case 401:
                msg = context.getString(R.string.err_401);
                break;
            case 404:
                msg = context.getString(R.string.err_404);
                break;
            case 422:
                msg = context.getString(R.string.err_422);
                break;
            case 500:
                msg = context.getString(R.string.err_500);
                break;
            case 503:
                msg = context.getString(R.string.err_503);
                break;
            default:
                msg = context.getString(R.string.err_unknow);
                break;

        }
        LDialogUtil.showOne(context, title, msg, context.getString(R.string.confirm), callbackShowOne);
    }
}
