package com.example.bloomexplorer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;

public class StoreFragment extends Fragment {

    private ListView listView;
    private SearchView searchView;
    private StoreAdapter adapter;
    private ArrayList<String> storeList; // Filtered data
    private ArrayList<String> originalStoreList; // Original data
    private String[] storeNames = {
            "Bloom Garden", "Flower Mart", "Moyses Stevens", "Floral Heaven"
    };
    private String[] storeDescriptions = {
            "Bloom Garden offers a wide variety of fresh flowers.",
            "Flower Mart is your one-stop flower shop.",
            "Moyses Stevens specializes in exotic flower arrangements.",
            "Floral Heaven provides premium flowers for events."
    };
    private int[] storeImages = {
            R.drawable.store1, R.drawable.store2,
            R.drawable.store3, R.drawable.store4
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);

        listView = view.findViewById(R.id.listView);
        searchView = view.findViewById(R.id.searchView);

        // Initialize list and adapter
        originalStoreList = new ArrayList<>(Arrays.asList(storeNames));
        storeList = new ArrayList<>(originalStoreList);
        adapter = new StoreAdapter(requireContext(), storeList, storeDescriptions, storeImages);
        listView.setAdapter(adapter);

        // Set up search functionality
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                storeList.clear();
                for (String store : originalStoreList) {
                    if (store.toLowerCase().contains(newText.toLowerCase())) {
                        storeList.add(store);
                    }
                }
                adapter.notifyDataSetChanged();
                return true;
            }
        });

        // Handle item clicks
        listView.setOnItemClickListener((AdapterView<?> parent, View itemView, int position, long id) -> {
            String selectedStore = storeList.get(position);
            int realPosition = originalStoreList.indexOf(selectedStore);

            Intent intent = new Intent(getActivity(), StoreDetailActivity.class);
            intent.putExtra("storeName", storeNames[realPosition]);
            intent.putExtra("storeDescription", storeDescriptions[realPosition]);
            intent.putExtra("storeImageRes", storeImages[realPosition]);
            startActivity(intent);
        });

        return view;
    }
}
