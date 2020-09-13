package com.baiyu.androidx.basicframework.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.baiyu.androidx.basicframework.R;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class ProgressDialogFragment extends DialogFragment {

    View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Anim_fade);

    }
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Objects.requireNonNull(getDialog()).setCanceledOnTouchOutside(false);
        Objects.requireNonNull(getDialog().getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().addFlags(
                        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |
                        WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM );
        if (view == null) {
            view = inflater.inflate(R.layout.dialog_progress, container, false);
        }
        view.setClickable(true);
        ImageView img_roll = view.findViewById(R.id.img_roll);

        Animation circle_anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_round_rotate);
        LinearInterpolator interpolator = new LinearInterpolator();
        if (circle_anim != null) {
            circle_anim.setInterpolator(interpolator);
            img_roll.startAnimation(circle_anim);
        }
        return view;
    }

    public void onResume() {
        super.onResume();
        getFocus();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            Window window = getDialog().getWindow();
            WindowManager.LayoutParams windowParams = Objects.requireNonNull(window).getAttributes();
            windowParams.dimAmount = 0.3f;
            window.setAttributes(windowParams);
        }
    }

    private void getFocus() {
        requireView().setFocusableInTouchMode(true);
        requireView().requestFocus();
        requireView().setOnKeyListener((v, keyCode, event) -> event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK);
    }
}
