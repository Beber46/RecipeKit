package fr.beber.adapter;

import android.content.Context;
import android.graphics.Point;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.BaseAdapter;
import fr.beber.recipekit.R;
import fr.beber.util.SpeedScrollListener;

import java.util.List;

/**
 * Cette classe permet de créer une liste comme pour GooglePlus.
 *
 * @author Beber046
 * @version 1.0
 */
public class GPlusListAdapter<T> extends BaseAdapter {

    protected static final long ANIM_DEFAULT_SPEED = 1500L;

    protected Interpolator interpolator;

    protected SparseBooleanArray positionsMapper;
    protected List<T> items;
    protected int size, previousPosition;
    protected Point point;
    protected SpeedScrollListener scrollListener;
    protected double speed;
    protected long animDuration;
    protected View view;
    protected Context context;

    /**
     * Constructeur
     *
     * @param context Le contexte courant.
     * @param scrollListener Animation pour la listeView.
     * @param items liste de {@link T} à fournir p
     */
    public GPlusListAdapter(Context context, SpeedScrollListener scrollListener, List<T> items) {
        this.context = context;
        this.scrollListener = scrollListener;
        this.items = items;
        if (items != null)
            size = items.size();

        previousPosition = -1;
        positionsMapper = new SparseBooleanArray(size);
        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        point = new Point();
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
    public View getView(int position, final View convertView,final ViewGroup parent) {
        return this.getAnimatedView(position, convertView, parent);
    }

    protected void defineInterpolator() {
        interpolator = new DecelerateInterpolator();
    }

    /**
     * Permet d'effectuer l'animation lors de la mise en place de la listeView.
     *
     * @param position Position de l'objet par rapport à à la liste renseignée.
     * @param convertView Permet de récupérer la view nécessaire pour l'animation.
     * @param parent Peut être nécessaire pour récupérer {@link View} de la listView.
     * @return La vue récupérée pour l'animation.
     */
    private View getAnimatedView(int position,final View convertView,final ViewGroup parent) {
        view = getRowView(position, convertView, parent);

        if (view != null && !positionsMapper.get(position) && position > previousPosition) {
            speed = scrollListener.getSpeed();

            animDuration = (((int) speed) == 0) ? ANIM_DEFAULT_SPEED : (long) (1 / speed * 15000);

            if (animDuration > ANIM_DEFAULT_SPEED)
                animDuration = ANIM_DEFAULT_SPEED;

            previousPosition = position;

            view.setTranslationX(0.0F);
            view.setTranslationY(point.y);
            view.setRotationX(45.0F);
            view.setScaleX(0.7F);
            view.setScaleY(0.55F);

            ViewPropertyAnimator localViewPropertyAnimator =
                    view.animate().rotationX(0.0F).rotationY(0.0F).translationX(0).translationY(0).setDuration(animDuration).scaleX(
                            1.0F).scaleY(1.0F).setInterpolator(interpolator);

            localViewPropertyAnimator.setStartDelay(0).start();
            positionsMapper.put(position, true);
        }
        return view;
    }

    /**
     * Retourne la view nécessaire pour l'animation.
     *
     * @param position    Position de l'objet par rapport à à la liste renseignée.
     * @param convertView Permet de récupérer la view nécessaire pour l'animation.
     * @param parent      Peut être nécessaire pour récupérer {@link View} de la listView.
     * @return La vue récupérée.
     */
    protected View getRowView(int position, View convertView, ViewGroup parent){

        if(convertView==null)
            convertView = LayoutInflater.from(context).inflate(R.layout.gplus_row, parent, false);

        return convertView;
    }

}
