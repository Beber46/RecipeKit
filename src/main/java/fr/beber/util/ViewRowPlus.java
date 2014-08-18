package fr.beber.util;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Cette classe permet de Créer l'objet nécessaire pour la classe {@link fr.beber.adapter.StableArrayAdapter}. Il récupère une image, un titre et une note pour une recette.
 *
 * @author Beber046
 * @version 1.0
 */
public class ViewRowPlus {

    /**
     * Image de la recette.
     */
    private ImageView imageView;
    /**
     * Nom de la recette.
     */
    private TextView textView;
    /**
     * Note de la recette.
     */
    private RatingBar ratingBar;

    /**
     * Contructeur par défaut
     */
    public ViewRowPlus() {

    }

    /**
     * Contructeur complet.
     *
     * @param imageView Image de la recette.
     * @param textView Nom de la recette.
     * @param ratingBar Note de la recette.
     */
    public ViewRowPlus(final ImageView imageView, final TextView textView, final RatingBar ratingBar) {
        this.imageView = imageView;
        this.textView = textView;
        this.ratingBar = ratingBar;
    }

    /**
     * Permet de récupérer l'image de la recette.
     * @return l'image de la recette.
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * Change l'image de la recette.
     * @param imageView Image de la recette à changer.
     */
    public void setImageView(final ImageView imageView) {
        this.imageView = imageView;
    }

    /**
     * Permet de récupérer le nom de la recette.
     * @return le nom de la recette.
     */
    public TextView getTextView() {
        return textView;
    }

    /**
     * Change le nom de la recette.
     * @param textView Nom de la recette à changer.
     */
    public void setTextView(final TextView textView) {
        this.textView = textView;
    }

    /**
     * Permet de récupérer la note de la recette.
     * @return la note de la recette.
     */
    public RatingBar getRatingBar() {
        return ratingBar;
    }

    /**
     * Change la note de la recette.
     * @param ratingBar Note de la recette.
     */
    public void setRatingBar(final RatingBar ratingBar) {
        this.ratingBar = ratingBar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewRowPlus that = (ViewRowPlus) o;

        if (imageView != null ? !imageView.equals(that.imageView) : that.imageView != null) return false;
        if (ratingBar != null ? !ratingBar.equals(that.ratingBar) : that.ratingBar != null) return false;
        if (textView != null ? !textView.equals(that.textView) : that.textView != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = imageView != null ? imageView.hashCode() : 0;
        result = 31 * result + (textView != null ? textView.hashCode() : 0);
        result = 31 * result + (ratingBar != null ? ratingBar.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ViewRowPlus[" +
                "imageView=" + imageView +
                ", textView=" + textView +
                ", ratingBar=" + ratingBar +
                ']';
    }
}
