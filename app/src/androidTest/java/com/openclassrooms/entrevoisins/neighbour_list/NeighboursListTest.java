
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.os.SystemClock.sleep;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;
    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private List<Neighbour> mFavorites;


    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        mApiService = DI.getNeighbourApiService();
        mNeighbours = mApiService.getNeighbours();
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * First Test
     * When we click on the name of on the list, the click lunch the ProfilActivity
     */
    @Test
    public void myNeighboursList_lunchAction_shouldLunchProfilActivity() {
        // We check on the RECYCLERVIEW -> the container position 1 is the second Neighbour, click on this container
        onView(withId(R.id.list_neighbours)).
                perform(RecyclerViewActions.actionOnItemAtPosition(1,click()));
        // Verify if the the layout Activity profil is lunch with the id ShowProfil
        onView(withId(R.id.showProfil)).
                check(matches(isDisplayed()));
        // We check if the Text attribute of show_name_neigbour is the name of 2 Neighbour
        onView(withId(R.id.show_name_neighbour)).
                check((matches(withText(mNeighbours.get(1).getName()))));
    }

    /**
     * Second Test
     * Lunch ShowProfil, check the name of the neighbour
     */
    @Test
    public void myNeighboursList_lunchAction_shouldLunchProfilActivityAndCheckName() {
        // We scroll to the position container of RECYCLERVIEW 5 and click on the container to lunch the ShowActivity
        onView(withId(R.id.list_neighbours)).
                perform(RecyclerViewActions.actionOnItemAtPosition(5,click()));
        // We check if the Text attribute of show_name_neigbour is the name of 5 Neighbour
        onView(withId(R.id.show_name_neighbour)).
                check(matches(withText(mNeighbours.get(5).getName())));
        // We check if the Text attribute of show_name_avatar_neighbour is the name of 5 Neighbour
        onView(withId(R.id.show_name_avatar_neighbour)).
                check(matches(withText(mNeighbours.get(5).getName())));
    }

    /**
     * Threetheen Test
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        //  Compt the list of neigbour // Expeted 12
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void myFavoritesList_showFavorite_shouldReturnTheNeighboursisFavorite() {
        //test vérifiant que l’onglet Favoris n’affiche que les voisins marqués comme
        // Go to the FavoriteListeView with a scrollLeft
        onView(withId(R.id.container)).perform(swipeLeft());
        // Check if the FavoriteListView is Empty because the FavoriteList is empty on the lunch of the test/application
        onView(withId(R.id.list_neighbours_fav)).check(withItemCount(0));
        // Go to the NeighbourList with scrollRight
        onView(withId(R.id.container)).perform(swipeRight());
        // Go to the ShowPrfil to 0 at 4 Neighbour and click on the Button Favorite and back to the listNeigbour to add them on FavoriteListe
        // At sleep because it's doesn't work
        sleep(1000);
        for (int i = 0; i < 3; i++){
            onView(withId(R.id.list_neighbours)).
                    perform(RecyclerViewActions.actionOnItemAtPosition(i,click()));

            onView(withId(R.id.floatingActionButtonProfil)).perform(click());
            onView(withId(R.id.floatingActionButtonBack)).perform(click());
        }
        // Go to the FavoriteListeView again with a scrollLeft
        onView(withId(R.id.container)).perform(swipeLeft());
        // Check if the FavoriteListView has 5 container on the FavoriteList
        onView(withId(R.id.list_neighbours_fav)).check(withItemCount(3));
    }

}