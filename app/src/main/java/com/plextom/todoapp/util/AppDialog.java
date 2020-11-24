package com.plextom.todoapp.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.plextom.todoapp.R;

public class AppDialog {

    public static void showOptionDialog(Context context, String msg, final DialogEvent event) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(context.getResources().getString(R.string.app_name))
                .setMessage(msg)
                .setPositiveButton(context.getResources().getString(R.string.okButton),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                if (event != null) {
                                    event.onPositiveClicked();
                                }
                            }
                        })
                .setNegativeButton(context.getResources().getString(R.string.cancelButton),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                if (event != null) {
                                    event.onNegativeClicked();
                                }
                            }
                        })
                .show();
    }

    public static abstract class DialogEvent {
        protected void onPositiveClicked() {
        }

        protected void onNegativeClicked() {
        }
    }

}
