package fr.beber.adapter;

import android.content.Context;
import android.graphics.Point;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.BaseAdapter;
import fr.beber.util.SpeedScrollListener;

import java.util.List;

/**
 * Cette classe permet de créer une liste comme pour GooglePlus.
 *
 * @author Beber046
 * @version 1.0
 */
public abstract class GPlusListAdapter<T> extends BaseAdapter {

    protected static final long ANIM_DEFAULT_SPEED = 1000L;

    protected Interpolator interpolator;

    protected SparseBooleanArray positionsMapper;
    protected List<T> items;
    protected int size, /*height, width,*/ previousPostition;
    protected Point point;
    protected SpeedScrollListener scrollListener;
    protected double speed;
    protected long animDuration;
    protected View v;
    protected Context context;

    public GPlusListAdapter(Context context, SpeedScrollListener scrollListener, List<T> items) {
        this.context = context;
        this.scrollListener = scrollListener;
        this.items = items;
        if (items != null)
            size = items.size();

        previousPostition = -1;
        positionsMapper = new SparseBooleanArray(size);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        /*width = windowManager.getDefaultDisplay().getWidth();
        height = windowManager.getDefaultDisplay().getHeight();*/

        windowManager.getDefaultDisplay().getSize(point);

        defineInterpolator();
    }

    @Override
    public int getCount() {
        return size;
    }

    @Override
    public Object getItem(int position) {
        return items != null && position < size ? items.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getAnimatedView(position, convertView, parent);
    }

    protected void defineInterpolator() {
        interpolator = new DecelerateInterpolator();
    }

    public View getAnimatedView(int position, View convertView, ViewGroup parent) {
        v = getRowView(position, convertView, parent);

        if (v != null && !positionsMapper.get(position) && position > previousPostition) {
            speed = scrollListener.getSpeed();

            animDuration = (((int) speed) == 0) ? ANIM_DEFAULT_SPEED : (long) (1 / speed * 15000);

            if (animDuration > ANIM_DEFAULT_SPEED)
                animDuration = ANIM_DEFAULT_SPEED;

            previousPostition = position;

            v.setTranslationX(0.0F);
            v.setTranslationY(point.y);//TODO: vérifier que ca marche
            v.setRotationX(45.0F);
            v.setScaleX(0.7F);
            v.setScaleY(0.55F);

            ViewPropertyAnimator localViewPropertyAnimator =
                    v.animate().rotationX(0.0F).rotationY(0.0F).translationX(0).translationY(0).setDuration(animDuration).scaleX(
                            1.0F).scaleY(1.0F).setInterpolator(interpolator);

            localViewPropertyAnimator.setStartDelay(0).start();
            positionsMapper.put(position, true);
        }
        return v;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either create a View
     * manually or inflate it from an XML layout file. When the View is inflated, the parent View (GridView,
     * ListView...) will apply default layout parameters unless you use {@link android.view.LayoutInflater#inflate(int,
     * android.view.ViewGroup, boolean)} to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view is non-null and of
     *                    an appropriate type before using. If it is not possible to convert this view to display the
     *                    correct data, this method can create a new view.
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    protected abstract View getRowView(int position, View convertView, ViewGroup parent);

}
