package me.genu.animeservicegenu;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import me.genu.animeservicegenu.models.Anime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter arrayAdapter;
    ArrayList<String> datos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listViewAnime);
        arrayAdapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,datos);

        listView.setAdapter(arrayAdapter);
        obtenerDatos();

    }

    private void obtenerDatos(){
        String url = "https://anime-facts-rest-api.herokuapp.com/api/v1";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    pasarJson(jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

    private void pasarJson(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = null;
            Anime anime = new Anime();

            try {
                jsonObject = jsonArray.getJSONObject(i);
                anime.setAnime_id(jsonObject.getInt("anime_id"));
                anime.setAnime_name(jsonObject.getString("anime_name"));
                anime.setAnime_img(jsonObject.getString("anime_img"));

                datos.add(anime.getAnime_id() +" "+ anime.getAnime_name());


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        arrayAdapter.notifyDataSetChanged();
    }

}