package fr.beber.util;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Cette classe permet de
 *
 * @author Bertrand
 * @version 1.0
 */
public class ScrollChangedActionBar implements ViewTreeObserver.OnScrollChangedListener {

    private ActionBar actionBar;
    private ScrollView scrollView;

    public ScrollChangedActionBar(final ActionBar actionBar, final ScrollView scrollView) {
        super();
        this.actionBar = actionBar;
        this.scrollView = scrollView;
    }

    @Override
    public void onScrollChanged() {
        if (scrollView.getScrollY() >= 140 && scrollView.getScrollY() <= 229) {
            BigDecimal a = new BigDecimal(255);
            a = a.divide(new BigDecimal(89), 2, RoundingMode.HALF_UP);

            BigDecimal b = new BigDecimal(35700);
            b = b.divide(new BigDecimal(89), 2, RoundingMode.HALF_UP);
            BigDecimal result = (a.multiply(new BigDecimal(scrollView.getScrollY()))).subtract(b);
            Log.d(this.getClass().getName(), "scrolly : " + scrollView.getScrollY() + " result : " + result);
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(result.intValue(), 0, 0, 0)));
        } else if (scrollView.getScrollY() < 140) {
            Log.d(this.getClass().getName(), "scrolly : " + scrollView.getScrollY());
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(0, 0, 0, 0)));
        } else {
            Log.d(this.getClass().getName(), "scrolly : " + scrollView.getScrollY());
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(250, 0, 0, 0)));
        }
    }


}
