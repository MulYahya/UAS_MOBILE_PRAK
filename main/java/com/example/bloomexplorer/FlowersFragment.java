package com.example.bloomexplorer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class FlowersFragment extends Fragment {

    private ListView listView;
    private SearchView searchView;
    private FlowerAdapter adapter;
    private ArrayList<Flower> flowerList; // Full flower data
    private ArrayList<Flower> filteredList; // Filtered flower data for search

    private static final String API_URL = "https://perenual.com/api/species-list?key=sk-osrW6764d0f68b3b58036";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flowers, container, false);

        listView = view.findViewById(R.id.listView);
        searchView = view.findViewById(R.id.searchView);

        flowerList = new ArrayList<>();
        filteredList = new ArrayList<>();

        // Set adapter for ListView
        adapter = new FlowerAdapter(requireContext(), filteredList);
        listView.setAdapter(adapter);

        // Fetch data from API
        fetchFlowers();

        // Set up SearchView to filter the ListView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false; // No action needed on submit
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterFlowers(newText);
                return true;
            }
        });

        // Set click listener for list items
        listView.setOnItemClickListener((AdapterView<?> parent, View itemView, int position, long id) -> {
            Flower selectedFlower = filteredList.get(position);

            Intent intent = new Intent(getActivity(), FlowerDetailActivity.class);
            intent.putExtra("flowerName", selectedFlower.getName());
            intent.putExtra("imageUrl", selectedFlower.getImageUrl());
            startActivity(intent);
        });


        return view;
    }

    private void fetchFlowers() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(API_URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray dataArray = jsonResponse.optJSONArray("data");

                    if (dataArray != null) {
                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject flowerObject = dataArray.optJSONObject(i);
                            if (flowerObject != null) {
                                String commonName = flowerObject.optString("common_name", "Unknown");
                                JSONObject defaultImage = flowerObject.optJSONObject("default_image");
                                String imageUrl = defaultImage != null ? defaultImage.optString("regular_url", "") : "";

                                Flower flower = new Flower(commonName, imageUrl);
                                flowerList.add(flower);
                            }
                        }
                        filteredList.addAll(flowerList);
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    Log.e("API_ERROR", "JSON Parsing Error: " + e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("API_ERROR", "Request Failed: " + error.getMessage());
            }
        });
    }

    private void filterFlowers(String query) {
        filteredList.clear();
        for (Flower flower : flowerList) {
            if (flower.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(flower);
            }
        }
        adapter.notifyDataSetChanged();
    }
}
