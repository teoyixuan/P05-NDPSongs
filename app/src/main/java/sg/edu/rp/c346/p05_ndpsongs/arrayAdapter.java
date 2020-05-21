package sg.edu.rp.c346.p05_ndpsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class arrayAdapter extends ArrayAdapter<Song> {
    private ArrayList<Song> songs;
    private Context context;
    private TextView tvYear, tvTitle, tvSinger;

    public arrayAdapter(Context context, int resource, ArrayList<Song> objects) {
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        songs = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }

    // getView() is the method ListView will call to get the
    //  View object every time ListView needs a row
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // The usual way to get the LayoutInflater object to
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.row, parent, false);

        // Get the TextView object
        tvYear = (TextView) rowView.findViewById(R.id.tvYear);
        tvTitle = (TextView) rowView.findViewById(R.id.tvTitle);
        tvSinger = (TextView) rowView.findViewById(R.id.tvSinger);



        // The parameter "position" is the index of the
        //  row ListView is requesting.
        //  We get back the food at the same index.
        Song current = songs.get(position);
        // Set the TextView to show the food


        tvYear.setText(current.getYear() + "");
        tvTitle.setText(current.getTitle());
        tvSinger.setText(current.getSingers());

        // Return the nicely done up View to the ListView
        return rowView;
    }
}