package br.com.geekemovies.themoviedb.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.greenrobot.eventbus.EventBus;
import br.com.geekemovies.themoviedb.R;
import br.com.geekemovies.themoviedb.fragments.DetailTmDbFragment;
import br.com.geekemovies.themoviedb.interfaces.OnTmDbClick;
import br.com.geekemovies.themoviedb.model.Result;

public class MainActivity extends AppCompatActivity implements OnTmDbClick{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onTmDbClick(Result result) {
        if (getResources().getBoolean(R.bool.phone)){
            Intent it = new Intent(this, DetailTmDbAct.class);
            it.putExtra("result",result);
            startActivity(it);
        } else{
            DetailTmDbFragment detailTmDbFragment = DetailTmDbFragment.newInstance(result.getId().toString());
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_tmdb_detail,detailTmDbFragment,"result")
                    .commit();
        }
    }
}
