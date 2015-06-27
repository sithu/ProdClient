package com.deodarpark.updater.prodclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.deodarpark.updater.prodclient.domain.ProdEntry;

/**
 * An activity representing a list of ProdEntries. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ProdEntryDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ProdEntryListFragment} and the item details
 * (if present) is a {@link ProdEntryDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link ProdEntryListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class ProdEntryListActivity extends ActionBarActivity
        implements ProdEntryListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Show the Up button in the action bar.
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentManager fm = getSupportFragmentManager();
        Log.v("myApp", "List activity: " + R.layout.activity_prodentry_list);
        setContentView(R.layout.activity_prodentry_list);

        if (findViewById(R.id.prodentry_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
//            ((ProdEntryListFragment) getSupportFragmentManager()
//                    .findFragmentById(R.id.prodentry_list))
//                    .setActivateOnItemClick(true);

            // my code
            ProdEntryDetailFragment prodEntryDetailFragment = (ProdEntryDetailFragment) fm.findFragmentByTag("Detail");
            if (prodEntryDetailFragment == null) {
                Log.v("myApp", "List Activity: Initialize new detail view");
                // init new detail fragment
                prodEntryDetailFragment = new ProdEntryDetailFragment();
                Bundle args = new Bundle();
                args.putParcelable("prodEntry", new ProdEntry("Phu Phu", 1000));
                prodEntryDetailFragment.setArguments(args);
                fm.beginTransaction().
                        replace(R.id.prodentry_detail_container, prodEntryDetailFragment, "Detail").commit();
            } else {
                Log.v("myApp", "List Activity, Use existing Detail Fragment " + prodEntryDetailFragment);
            }
        }

        // TODO: If exposing deep links into your app, handle intents here.
        ProdEntryListFragment prodEntryListFragment = (ProdEntryListFragment)
                fm.findFragmentByTag("List");
        if (prodEntryListFragment == null) {
            prodEntryListFragment = new ProdEntryListFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("prodEntryList", ProdEntry.getProdEntryList());
            prodEntryListFragment.setArguments(bundle);
            Log.v("prodClient", "List Activity: create a new list fragment " + ProdEntry.getProdEntryList().size());
            fm.beginTransaction()
                    .replace(R.id.prodentry_list, prodEntryListFragment, "List").commit();
        } else {
            Log.v("myApp", "List Activity: Use existing List Fragment " + prodEntryListFragment);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu items for use in the action bar
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == android.R.id.home) {
//            // This ID represents the Home or Up button. In the case of this
//            // activity, the Up button is shown. Use NavUtils to allow users
//            // to navigate up one level in the application structure. For
//            // more details, see the Navigation pattern on Android Design:
//            //
//            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
//            //
//            NavUtils.navigateUpFromSameTask(this);
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    /**
     * Callback method from {@link ProdEntryListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(ProdEntry prodEntry) {
        if (mTwoPane) {
            Log.v("myApp", "TwoPane mode" + prodEntry.getEmployeeName());
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            //arguments.putString(ProdEntryDetailFragment.ARG_ITEM_ID, id);
            arguments.putParcelable("prodEntry", prodEntry);
            ProdEntryDetailFragment fragment = new ProdEntryDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.prodentry_detail_container, fragment, "Detail")
                    .commit();

        } else {
            Log.v("myApp", "Single-pane mode" + prodEntry.getEmployeeName());
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, ProdEntryDetailActivity.class);
            //detailIntent.putExtra(ProdEntryDetailFragment.ARG_ITEM_ID, id);
            detailIntent.putExtra("prodEntry", prodEntry);
            startActivity(detailIntent);
        }
    }
}
