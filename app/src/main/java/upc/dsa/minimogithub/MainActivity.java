package upc.dsa.minimogithub;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Serializable {

    Button buscar;
    TextView nombreUser;
    private final static Logger log = Logger.getLogger(MainActivity.class.toString());

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buscar = (Button)findViewById(R.id.btnQuery);
        nombreUser = (TextView) findViewById(R.id.editText);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserAPI API = UserAPI.retrofit.create(UserAPI.class);
                Call<User> call = API.getUser(nombreUser.getText().toString());

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()) {
                            user = new User();
                            user = response.body();


                            Intent mIntent = new Intent(MainActivity.this, resultadoActivity.class);
                            mIntent.putExtra("myUser", user);
                            startActivity(mIntent);
                        }
                            else{

                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                            alertDialogBuilder
                                    .setTitle("Error")
                                    .setMessage(response.message())
                                    .setCancelable(false)
                                    .setPositiveButton("OK", (dialog, which) -> closeContextMenu());

                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.show();                            }


                        }



                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                        alertDialogBuilder
                                .setTitle("Error")
                                .setMessage("No response by the server")
                                .setCancelable(false)
                                .setPositiveButton("OK", (dialog, which) -> closeContextMenu());

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }
                });

            }
        });

    }
}
