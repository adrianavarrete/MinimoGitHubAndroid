package upc.dsa.minimogithub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class resultadoActivity extends AppCompatActivity {

    User user;
    TextView repoFijo;
    TextView folloFijo;
    TextView repos;
    TextView follos;
    ImageView fotoUser;
    RecyclerView recyclerView;
    Recycler recycler;

    UserAPI API;
    List<Follower> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        repoFijo = (TextView) findViewById(R.id.textRepoFijo);
        folloFijo = (TextView) findViewById(R.id.textFollowingFijo);
        repos =(TextView) findViewById(R.id.textNumRepo);
        follos = (TextView) findViewById(R.id.textNumFollowings);
        fotoUser = (ImageView) findViewById(R.id.imagenBusqueda);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        user = (User) getIntent().getParcelableExtra("myUser");
        API = UserAPI.retrofit.create(UserAPI.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recycler = new Recycler(this);
        recyclerView.setAdapter(recycler);

        repos.setText(String.valueOf(user.getPublicRepos()));
        follos.setText(String.valueOf(user.getFollowing()));

        Picasso.with(this).load(user.getAvatarUrl()).into(fotoUser);


        Call<List<Follower>> call = API.getListFollowers(user.getLogin());

        call.enqueue(new Callback<List<Follower>>() {
            @Override
            public void onResponse(Call<List<Follower>> call, Response<List<Follower>> response) {
                data = response.body();
                recycler.addFollowers(data);

            }

            @Override
            public void onFailure(Call<List<Follower>> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Fallo con la petición de información",Toast.LENGTH_SHORT);


            }
        });
    }
}
