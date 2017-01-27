package pl.poblock.plan2fly.common;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

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
}
