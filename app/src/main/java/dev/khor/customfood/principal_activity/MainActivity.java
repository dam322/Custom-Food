package dev.khor.customfood.principal_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import dev.khor.customfood.DataContainer;
import dev.khor.customfood.R;

public class MainActivity extends AppCompatActivity {

    public UserFragment userFragment = new UserFragment();
    public OrderFragment orderFragment = new OrderFragment();
    public MenuFragment menuFragment = new MenuFragment();
    public OrdersFragment ordersFragment = new OrdersFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principalview);
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        loadFragment(menuFragment);
        DataContainer.fm = getSupportFragmentManager();

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.firstFragment:
                            loadFragment(userFragment);
                            return true;
                        case R.id.secondFragment:
                            loadFragment(orderFragment);
                            return true;
                        case R.id.fourFragment:
                            loadFragment(ordersFragment);
                            return true;
                        case R.id.thirdFragment:
                            loadFragment(menuFragment);
                            return true;
                    }
                    return false;
                }
            };

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }
}
