package fr.beber.util;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Cette classe permet de
 *
 * @author Beber046
 * @version 1.0
 */
public class ViewRowPlus {

    private ImageView imageView;
    private TextView textView;
    private RatingBar ratingBar;

    public ViewRowPlus() {

    }

    public ViewRowPlus(final ImageView imageView, final TextView textView, final RatingBar ratingBar) {
        this.imageView = imageView;
        this.textView = textView;
        this.ratingBar = ratingBar;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public RatingBar getRatingBar() {
        return ratingBar;
    }

    public void setRatingBar(RatingBar ratingBar) {
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
