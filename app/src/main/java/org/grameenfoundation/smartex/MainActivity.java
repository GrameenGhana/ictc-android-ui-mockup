package org.grameenfoundation.smartex;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import org.grameenfoundation.smartex.common.Utils;
import org.grameenfoundation.smartex.fragments.NavigationDefaultFragment;
import org.grameenfoundation.smartex.fragments.NavigationDrawerFragment;
import org.grameenfoundation.smartex.fragments.NavigationHomeFragment;
import org.grameenfoundation.smartex.fragments.NavigationPricesFragment;
import org.grameenfoundation.smartex.fragments.NavigationTractorCalFragment;
import org.grameenfoundation.smartex.fragments.NavigationWeatherFragment;


public class MainActivity extends AppCompatActivity implements NavigationDrawerCallbacks, View.OnClickListener, FloatingActionMenu.MenuStateChangeListener {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;
    private FloatingActionButton actionButton = null;
    private FloatingActionMenu rightLowerMenu = null;
    private ImageView fab = null;

    private static final String CKW = "CKWAPP";
    private static final String TAROWORKS = "TAROWORKSAPP";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set FAB
        setFAB(0);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

        // populate the navigation drawer
        mNavigationDrawerFragment.setUserData("Kojo Amoah", "kojo@amoah.com", BitmapFactory.decodeResource(getResources(), R.drawable.avatar));
    }


    public void hideFAB() {
        if (actionButton != null) {
            actionButton.removeAllViews();
            actionButton.setVisibility(View.INVISIBLE);
        }
    }

    public void setFAB(int position)
    {
        if (position==0) {
            fab = new ImageView(this);
            ImageView addMeetingIcon = new ImageView(this);
            ImageView addFarmerIcon = new ImageView(this);

            fab.setImageResource(R.drawable.ic_star_border_white_24dp);
            addMeetingIcon.setImageResource(R.drawable.ic_comment_black_24dp);
            addFarmerIcon.setImageResource(R.drawable.ic_person_add_black_24dp);

            actionButton = new FloatingActionButton.Builder(this)
                    .setBackgroundDrawable(R.drawable.floating_button)
                    .setContentView(fab)
                    .build();

            SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(this)
                    .setBackgroundDrawable(Utils.getDrawable(this, R.drawable.floating_sub_button));

            SubActionButton addMeetingButton = rLSubBuilder.setContentView(addMeetingIcon).build();
            SubActionButton addFarmerButton = rLSubBuilder.setContentView(addFarmerIcon).build();
            addMeetingButton.setTag("AddMeeting");
            addFarmerButton.setTag("AddFarmer");
            addMeetingButton.setOnClickListener(this);
            addFarmerButton.setOnClickListener(this);

            rightLowerMenu = new FloatingActionMenu.Builder(this)
                    .addSubActionView(addMeetingButton)
                    .addSubActionView(addFarmerButton)
                    .attachTo(actionButton)
                    .build();

            rightLowerMenu.setStateChangeListener(this);
        }
    }
    @Override
    public void onMenuOpened(FloatingActionMenu menu) {
        // Rotate the icon of rightLowerButton 45 degrees clockwise
        fab.setRotation(0);
        PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
        ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fab, pvhR);
        animation.start();
    }

    @Override
    public void onMenuClosed(FloatingActionMenu menu) {
        // Rotate the icon of rightLowerButton 45 degrees counter-clockwise
        fab.setRotation(45);
        PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
        ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fab, pvhR);
        animation.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getTag().toString()) {
            case CKW:
                Toast.makeText(getApplicationContext(), "Starting CKW application", Toast.LENGTH_SHORT).show();
                break;
            case TAROWORKS:
                Toast.makeText(getApplicationContext(), "Starting Taroworks", Toast.LENGTH_SHORT).show();
                break;
            case "AddMeeting":
                Toast.makeText(getApplicationContext(), "Starting Taroworks to add meeting", Toast.LENGTH_SHORT).show();
                break;
            case "AddFarmer":
                Toast.makeText(getApplicationContext(), "Starting Taroworks to add farmer", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Fragment fragment;

        hideFAB();

        switch (position) {
            case 0: // home
                fragment = getFragmentManager().findFragmentByTag(NavigationHomeFragment.TAG);
                if (fragment == null) {
                    fragment = new NavigationHomeFragment();
                }
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, NavigationHomeFragment.TAG).commit();
                setFAB(0);
                break;
            case 1: // tractor calendar
                fragment = getFragmentManager().findFragmentByTag(NavigationTractorCalFragment.TAG);
                if (fragment == null) {
                    fragment = new NavigationTractorCalFragment();
                }
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, NavigationTractorCalFragment.TAG).commit();
                break;
            case 2: // prices
                fragment = getFragmentManager().findFragmentByTag(NavigationPricesFragment.TAG);
                if (fragment == null) {
                    fragment = new NavigationPricesFragment();
                }
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, NavigationPricesFragment.TAG).commit();
                break;
            case 3: // weather
                fragment = getFragmentManager().findFragmentByTag(NavigationWeatherFragment.TAG);
                if (fragment == null) {
                    fragment = new NavigationWeatherFragment();
                }
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, NavigationWeatherFragment.TAG).commit();
                break;
            default:
                fragment = getFragmentManager().findFragmentByTag(NavigationDefaultFragment.TAG);
                if (fragment == null) {
                    fragment = new NavigationDefaultFragment();
                }
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, NavigationDefaultFragment.TAG).commit();
                break;
        }

    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_logout) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
