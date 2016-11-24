package com.spatil32.a20367073_bookreviews;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by Shreyas on 11/9/2016.
 */
public class CustomListAdapter extends ArrayAdapter<String> {
    //private class members
    private final Activity Context;
    private final String[] booknames;
    private final String[] authornames;
    private final Float[] ratings;

    //parameterized constructor
    public CustomListAdapter(Activity context, String[] booknames, String[] authornames, Float[] ratings) {
        super(context, R.layout.books_info, booknames);
        this.Context = context;
        this.booknames = booknames;
        this.authornames = authornames;
        this.ratings = ratings;
    }

    //get custom listview
    public View getView(int position, View view, ViewGroup parent)
    {
        //get layout inflator
        LayoutInflater inflater = Context.getLayoutInflater();
        //inflate the view
        View ListViewSingle = inflater.inflate(R.layout.books_info, null, true);
        //find UI elements
        TextView ListViewBookName = (TextView) ListViewSingle.findViewById(R.id.bookname);
        TextView ListViewAuthorName = (TextView)ListViewSingle.findViewById(R.id.authorname);
        RatingBar ListViewBookRating = (RatingBar)ListViewSingle.findViewById(R.id.bookrating);
        //set UI element values
        ListViewBookName.setText(booknames[position]);
        ListViewAuthorName.setText(authornames[position]);
        ListViewBookRating.setRating(ratings[position]);

        return ListViewSingle;
    }
}
