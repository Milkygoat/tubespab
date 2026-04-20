package com.example.tugasbesar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity115 extends AppCompatActivity {
    private ListView lvTransactions115;
    private Spinner spFilter115;
    private Button btnAdd115;
    private DatabaseHelper115 dbHelper115;
    private int userId115;

    @Override
    protected void onCreate(Bundle savedInstanceState115) {
        super.onCreate(savedInstanceState115);
        setContentView(R.layout.activity_main115);

        dbHelper115 = new DatabaseHelper115(this);
        userId115 = getIntent().getIntExtra("USER_ID115", -1);

        lvTransactions115 = findViewById(R.id.lvTransactions115);
        spFilter115 = findViewById(R.id.spFilter115);
        btnAdd115 = findViewById(R.id.btnAdd115);

        setupFilter115();
        loadTransactions115(null);

        btnAdd115.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v115) {
                Intent intent115 = new Intent(MainActivity115.this, AddEditActivity115.class);
                intent115.putExtra("USER_ID115", userId115);
                startActivity(intent115);
            }
        });

        lvTransactions115.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent115, View view115, int position115, long id115) {
                Cursor cursor115 = (Cursor) parent115.getItemAtPosition(position115);
                Intent intent115 = new Intent(MainActivity115.this, AddEditActivity115.class);
                intent115.putExtra("USER_ID115", userId115);
                intent115.putExtra("TRANS_ID115", cursor115.getInt(cursor115.getColumnIndexOrThrow("_id")));
                intent115.putExtra("KATEGORI115", cursor115.getString(cursor115.getColumnIndexOrThrow("kategori115")));
                intent115.putExtra("NOMINAL115", cursor115.getDouble(cursor115.getColumnIndexOrThrow("nominal115")));
                intent115.putExtra("KETERANGAN115", cursor115.getString(cursor115.getColumnIndexOrThrow("keterangan115")));
                intent115.putExtra("TANGGAL115", cursor115.getString(cursor115.getColumnIndexOrThrow("tanggal115")));
                startActivity(intent115);
            }
        });

        spFilter115.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent115, View view115, int position115, long id115) {
                String selected115 = parent115.getItemAtPosition(position115).toString();
                if (selected115.equals("Semua Kategori")) {
                    loadTransactions115(null);
                } else {
                    loadTransactions115(selected115);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent115) {}
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTransactions115(null);
        setupFilter115();
    }

    private void setupFilter115() {
        List<String> categories115 = new ArrayList<>();
        categories115.add("Semua Kategori");
        Cursor cursor115 = dbHelper115.getAllTransactions115(userId115);
        if (cursor115.moveToFirst()) {
            do {
                String cat115 = cursor115.getString(cursor115.getColumnIndexOrThrow("kategori115"));
                if (!categories115.contains(cat115)) {
                    categories115.add(cat115);
                }
            } while (cursor115.moveToNext());
        }
        cursor115.close();

        ArrayAdapter<String> adapter115 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories115);
        adapter115.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFilter115.setAdapter(adapter115);
    }

    private void loadTransactions115(String filter115) {
        Cursor cursor115;
        if (filter115 == null) {
            cursor115 = dbHelper115.getAllTransactions115(userId115);
        } else {
            cursor115 = dbHelper115.getTransactionsByCategory115(userId115, filter115);
        }

        String[] from115 = new String[]{"kategori115", "nominal115", "keterangan115", "tanggal115"};
        int[] to115 = new int[]{R.id.tvKategori115, R.id.tvNominal115, R.id.tvKeterangan115, R.id.tvTanggal115};

        SimpleCursorAdapter adapter115 = new SimpleCursorAdapter(this, R.layout.item_transaction115, cursor115, from115, to115, 0);
        lvTransactions115.setAdapter(adapter115);
    }
}
