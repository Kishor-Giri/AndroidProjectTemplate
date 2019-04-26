package com.dev.projecttemplate.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.projecttemplate.R;

public class CommonDialog {

    private CommonDialog() {

    }

    //Confirmation dialog
    public static Dialog confirmationDialog(Context context, String title, String description, boolean showCloseButton, ConfirmationDialogClickListener listener)
    {
        //create custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_confirmation_dialog);

        //setting dialog view
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        final TextView txtTitle = dialog.findViewById(R.id.title);
        final ImageView btnClose = dialog.findViewById(R.id.btnClose);
        final TextView txtDescription = dialog.findViewById(R.id.txtDescription);
        final TextView btnPositive = dialog.findViewById(R.id.btnPositive);
        final TextView btnNegative = dialog.findViewById(R.id.btnNegetive);
        txtTitle.setText(title);
        txtDescription.setText(description);
        btnClose.setVisibility(showCloseButton ? View.VISIBLE : View.GONE);

        btnClose.setOnClickListener(v->{
        dialog.dismiss();
        });
        btnPositive.setOnClickListener(v -> {
            if (listener != null)
                listener.onPositiveActionClick();
            dialog.dismiss();
        });
        btnNegative.setOnClickListener(v -> {
            if (listener != null)
                listener.onNegativeActionClick();
            dialog.dismiss();
        });
        return dialog;
    }

    public interface ConfirmationDialogClickListener {
        default void onPositiveActionClick() {
        }
        default void onNegativeActionClick() {
        }

    }

}
