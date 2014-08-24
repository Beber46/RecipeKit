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
 * Cette classe permet de réaliser la fondu pour l'actrion bar.</br>
 *
 * Voici comment a été déterminer l'équation (à l'origine):
 *
 * <ul>
 *     <li>0 = a*140 + b</li>
 *     <li>255 = a*229 + b</li>
 * </ul>
 *
 * @author Bertrand
 * @version 1.0
 */
public class ScrollChangedActionBar implements ViewTreeObserver.OnScrollChangedListener {

    private final BigDecimal MIN_SCROLL_Y = new BigDecimal(0);
    private final BigDecimal MAX_SCROLL_Y = new BigDecimal(229);

    private final BigDecimal MIN_ALPHA_COLOR = new BigDecimal(0);
    private final BigDecimal MAX_ALPHA_COLOR = new BigDecimal(255);

    /**
     * Elle se détermine en faisant la soustraction de {@link #MAX_SCROLL_Y} par {@link #MIN_SCROLL_Y}.
     */
    private final BigDecimal DIVIDENDE = MAX_SCROLL_Y.subtract(MIN_SCROLL_Y);
    /**
     * Elle se détermine en faisant la multiplication de {@link #MAX_ALPHA_COLOR} par {@link #MIN_SCROLL_Y}.
     */
    private final BigDecimal DIVISEUR = MAX_ALPHA_COLOR.multiply(MIN_SCROLL_Y);

    private ActionBar actionBar;
    private ScrollView scrollView;

    public ScrollChangedActionBar(final ActionBar actionBar, final ScrollView scrollView) {
        super();
        this.actionBar = actionBar;
        this.scrollView = scrollView;
    }

    @Override
    public void onScrollChanged() {
        if (scrollView.getScrollY() >= MIN_SCROLL_Y.intValue() && scrollView.getScrollY() <= MAX_SCROLL_Y.intValue()) {
            final BigDecimal result = this.calculEquation(new BigDecimal(scrollView.getScrollY()));

            Log.d(this.getClass().getName(), "scrolly : " + scrollView.getScrollY() + " result : " + result);
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(result.intValue(), 0, 0, 0)));
        } else if (scrollView.getScrollY() < MIN_SCROLL_Y.intValue()) {
            Log.d(this.getClass().getName(), "scrolly min : " + scrollView.getScrollY());
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(MIN_ALPHA_COLOR.intValue(), 0, 0, 0)));
        } else {
            Log.d(this.getClass().getName(), "scrolly max : " + scrollView.getScrollY());
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(MAX_ALPHA_COLOR.intValue(), 0, 0, 0)));
        }
    }

    /**
     * Permet de résoudre l'équation suivant: <ul> <li>y = a*x + b</li> </ul>
     * <p/>
     * <p>a: le coefficient directeur, il se détermine en faisant la division de {@link #MAX_ALPHA_COLOR} par {@link
     * #DIVIDENDE}.</p> <p>b: il se détermine en faisant la division de {@link #DIVISEUR} par {@link #DIVIDENDE}.</p>
     *
     * @param x Il s'agit de la variante de l'équation, dans ce cas le scroll en Y.
     * @return Le résultat de l'opération.
     */
    private BigDecimal calculEquation(final BigDecimal x) {
        final BigDecimal a = MAX_ALPHA_COLOR.divide(DIVIDENDE, 2, RoundingMode.HALF_UP);
        final BigDecimal b = DIVISEUR.divide(DIVIDENDE, 2, RoundingMode.HALF_UP);

        return (a.multiply(x)).subtract(b);
    }

}
