package mvptutorial.ir.adromsh.mvp;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.ss.bottomnavigation.BottomNavigation;
import com.ss.bottomnavigation.events.OnSelectedItemChangeListener;

import mvptutorial.ir.adromsh.mvp.Home.HomeFragment;
import mvptutorial.ir.adromsh.mvp.best.BestFragment;
import mvptutorial.ir.adromsh.mvp.cat.CatFragment;
import mvptutorial.ir.adromsh.mvp.search.SearchFragment;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction fragmentTransaction;
    BottomNavigation bottomNavigation;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        setupViews();



    }

    private void setupViews() {
        bottomNavigation = (BottomNavigation) findViewById(R.id.bottom_navigation);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.rel_main_fragmentContainer, new HomeFragment());
        fragmentTransaction.commit();


        bottomNavigation.setOnSelectedItemChangeListener(new OnSelectedItemChangeListener() {
            @Override
            public void onSelectedItemChanged(int i) {
                switch (i) {
                    case R.id.tab_home:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.rel_main_fragmentContainer, new HomeFragment());
                        fragmentTransaction.commit();
                        break;

                    case R.id.tab_cats:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.rel_main_fragmentContainer,new CatFragment());
                        fragmentTransaction.commit();
                        break;

                    case R.id.tab_bests:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.rel_main_fragmentContainer,new BestFragment());
                        fragmentTransaction.commit();
                        break;

                    case R.id.tab_search:
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.rel_main_fragmentContainer,new SearchFragment());
                        fragmentTransaction.commit();
                        break;


                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(bottomNavigation.getSelectedItem()==0){
            super.onBackPressed();
        }else{
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.rel_main_fragmentContainer,new HomeFragment());
            fragmentTransaction.commit();
            bottomNavigation.setSelectedItem(0);
        }
    }
}
