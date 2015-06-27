package com.deodarpark.updater.prodclient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.deodarpark.updater.prodclient.domain.ProdEntry;
import com.deodarpark.updater.prodclient.dummy.DummyContent;

/**
 * A fragment representing a single ProdEntry detail screen.
 * This fragment is either contained in a {@link ProdEntryListActivity}
 * in two-pane mode (on tablets) or a {@link ProdEntryDetailActivity}
 * on handsets.
 */
public class ProdEntryDetailFragment extends Fragment {
    private ProdEntry prodEntry;

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProdEntryDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (getArguments().containsKey(ARG_ITEM_ID)) {
//            // Load the dummy content specified by the fragment
//            // arguments. In a real-world scenario, use a Loader
//            // to load content from a content provider.
//            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
//        }
        if (getArguments().containsKey("prodEntry")) {
            prodEntry = getArguments().getParcelable("prodEntry");
            Log.v("myApp", "Detail Fragment: Retrieved course from argument");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_prodentry_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (prodEntry != null) {
            ((TextView) rootView.findViewById(R.id.prodentry_detail)).setText(prodEntry.getEmployeeName());
        } else {
            ((TextView) rootView.findViewById(R.id.prodentry_detail)).setText("Please fix me!");
        }

        return rootView;
    }
}
