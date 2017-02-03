package pl.poblock.plan2fly.common;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

/**
 * Created by krzysztof.poblocki on 2017-01-23.
 */

public class ActivityUtils {
    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static void replaceFragment (@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.commit();
    }

    public static void addNonUIFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                                   @NonNull Fragment nonUIFragment, String fragmentTag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(nonUIFragment, fragmentTag);
        transaction.commit();
    }

    public static void showProgress(final boolean show, Context context, final View first, final View second) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = context.getResources().getInteger(android.R.integer.config_shortAnimTime);

            first.setVisibility(show ? View.GONE : View.VISIBLE);
            first.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    first.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            second.setVisibility(show ? View.VISIBLE : View.GONE);
            second.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    second.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            second.setVisibility(show ? View.VISIBLE : View.GONE);
            first.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
