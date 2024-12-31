package com.example.bloomexplorer;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;

import com.bumptech.glide.Glide;

public class FlowerDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower_detail);

        ImageView imgFlower = findViewById(R.id.imgFlower);
        TextView tvFlowerName = findViewById(R.id.tvFlowerName);

        // Get data from intent
        String flowerName = getIntent().getStringExtra("flowerName");
        String imageUrl = getIntent().getStringExtra("imageUrl");

        // Set data to views
        // You can load image from URL or set image resource
        if (imageUrl != null) {
            Glide.with(this)
                    .load(imageUrl)
                    .error(R.drawable.rose) // Tambahkan gambar error jika gagal
                    .into(imgFlower);
        } else {
            imgFlower.setImageResource(R.drawable.rose); // Gambar default
        }

        tvFlowerName.setText(flowerName);
    }
}
