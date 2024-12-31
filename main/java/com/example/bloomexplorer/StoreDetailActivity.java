package com.example.bloomexplorer;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class StoreDetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textViewName, textViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);

        imageView = findViewById(R.id.imageViewStore);
        textViewName = findViewById(R.id.textViewStoreName);
        textViewDescription = findViewById(R.id.textViewStoreDescription);

        // Get data from intent
        String name = getIntent().getStringExtra("storeName");
        String description = getIntent().getStringExtra("storeDescription");
        int imageRes = getIntent().getIntExtra("storeImageRes", -1);

        // Set data to views
        textViewName.setText(name);
        textViewDescription.setText(description);
        imageView.setImageResource(imageRes);
    }
}
