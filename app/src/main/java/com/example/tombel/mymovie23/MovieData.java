package com.example.tombel.mymovie23;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MovieData extends AsyncTask<String, String, ArrayList<Movie>> {
    public MovieData(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    private MainActivity mainActivity;

    @Override
    protected void onPostExecute(ArrayList<Movie> movies) {
        super.onPostExecute(movies);
        mainActivity.setListMovie(movies);
    }

    @Override
    protected ArrayList<Movie> doInBackground(String... strings) {
        final ArrayList<Movie> list = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(strings[0])
                .build();
        try {
            Response response = client.newCall(request).execute();

            String movies = response.body().string();

            try{
                JSONObject objData = new JSONObject(movies);
                final JSONArray arrayResults = objData.getJSONArray("results");
                if(arrayResults != null) {
                    for (int i = 0; i < arrayResults.length(); i++) {
                        JSONObject objMovie = new JSONObject(arrayResults.get(i).toString());
                        String title = objMovie.getString("title");
                        String overview = objMovie.getString("overview");
                        String releaseDate = objMovie.getString("release_date");
                        String imgPoster = "http://image.tmdb.org/t/p/w185" + objMovie.getString("poster_path");
                        Movie movie = new Movie();
                        movie.setPoster(imgPoster);
                        movie.setOverview(overview);
                        movie.setTitle(title);
                        list.add(movie);
                    }
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
