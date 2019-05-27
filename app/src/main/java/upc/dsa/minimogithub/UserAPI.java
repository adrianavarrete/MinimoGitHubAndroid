package upc.dsa.minimogithub;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserAPI {

    String BASE_URL = "https://api.github.com/users/";

    @GET("{nombreUser}")
    Call<User> getUser(@Path("nombreUser") String nombreUser);

    @GET("{nombreUser}/followers")
    Call<List<Follower>> getListFollowers(@Path("nombreUser") String nombreUser);



    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
