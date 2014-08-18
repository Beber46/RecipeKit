package fr.beber.util;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

/**
 * Cette classe est utilisé pour {@link fr.beber.adapter.GPlusListAdapter}. Elle permet de calculer la vitesse de défillement de la scrollview.
 *
 * @author Beber046
 * @version 1.0
 */
public class SpeedScrollListener implements OnScrollListener {

    private int previousFirstVisibleItem = 0;
    private long previousEventTime = 0, currTime, timeToScrollOneElement;
    private double speed = 0;

    @Override
    public void onScroll(final AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (previousFirstVisibleItem != firstVisibleItem) {
            currTime = System.currentTimeMillis();
            timeToScrollOneElement = currTime - previousEventTime;
            speed = ((double) 1 / timeToScrollOneElement) * 1000;

            previousFirstVisibleItem = firstVisibleItem;
            previousEventTime = currTime;

        }
    }

    @Override
    public void onScrollStateChanged(final AbsListView view, int scrollState) {
    }

    /**
     * Permet d'obtenir la vitesse de défillement pour la scrollview.
     *
     * @return la vitesse calculée.
     */
    public double getSpeed() {
        return speed;
    }
}
