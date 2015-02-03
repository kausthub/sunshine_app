package com.example.kausthub.myapplication;

import android.content.Intent;
import android.support.v7.widget.ShareActionProvider;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DetailedActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public class PlaceholderFragment extends Fragment {
        private static final String forecast_share_hashtag = "#sunshineapp";
        private final String LOG_TAG = PlaceholderFragment.class.getSimpleName();
        private String mforecast;
        public PlaceholderFragment() {
            setHasOptionsMenu(true);
        }



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Intent intent = getActivity().getIntent();
            View rootView = inflater.inflate(R.layout.fragment_detailed, container, false);
            if(intent !=null && intent.hasExtra(Intent.EXTRA_TEXT))
            {
                mforecast = intent.getStringExtra(Intent.EXTRA_TEXT);
                ((TextView) rootView.findViewById(R.id.detail_text)).setText(mforecast);
            }
            return rootView;
        }
        private Intent createShareIntent()
        {   Intent shareintent = new Intent(Intent.ACTION_SEND);
            shareintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            shareintent.setType("text/plain");
            shareintent.putExtra(Intent.EXTRA_TEXT,mforecast+forecast_share_hashtag);
            return shareintent;

        }
        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.detailedfragment, menu);
            MenuItem menuItem = menu.findItem(R.id.action_share);
            ShareActionProvider mactionprovider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
            if(mactionprovider != null)
            {
                mactionprovider.setShareIntent(createShareIntent());
            }
            else
            {
                Log.d("intent ", " share is null ?");
            }

        }


    }
}
