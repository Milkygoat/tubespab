package com.example.tugasbesar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditActivity115 extends AppCompatActivity {
    private EditText etKategori115, etNominal115, etKeterangan115, etTanggal115;
    private Button btnSave115, btnDelete115;
    private TextView tvTitle115;
    private DatabaseHelper115 dbHelper115;
    private int userId115, transId115 = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState115) {
        super.onCreate(savedInstanceState115);
        setContentView(R.layout.activity_add_edit115);

        dbHelper115 = new DatabaseHelper115(this);
        etKategori115 = findViewById(R.id.etKategori115);
        etNominal115 = findViewById(R.id.etNominal115);
        etKeterangan115 = findViewById(R.id.etKeterangan115);
        etTanggal115 = findViewById(R.id.etTanggal115);
        btnSave115 = findViewById(R.id.btnSave115);
        btnDelete115 = findViewById(R.id.btnDelete115);
        tvTitle115 = findViewById(R.id.tvTitle115);

        userId115 = getIntent().getIntExtra("USER_ID115", -1);
        if (getIntent().hasExtra("TRANS_ID115")) {
            transId115 = getIntent().getIntExtra("TRANS_ID115", -1);
            etKategori115.setText(getIntent().getStringExtra("KATEGORI115"));
            etNominal115.setText(String.valueOf(getIntent().getDoubleExtra("NOMINAL115", 0)));
            etKeterangan115.setText(getIntent().getStringExtra("KETERANGAN115"));
            etTanggal115.setText(getIntent().getStringExtra("TANGGAL115"));
            tvTitle115.setText("Edit Transaksi");
            btnDelete115.setVisibility(View.VISIBLE);
        }

        btnSave115.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v115) {
                String kategori115 = etKategori115.getText().toString();
                double nominal115 = Double.parseDouble(etNominal115.getText().toString());
                String keterangan115 = etKeterangan115.getText().toString();
                String tanggal115 = etTanggal115.getText().toString();

                if (transId115 == -1) {
                    dbHelper115.addTransaction115(userId115, kategori115, nominal115, keterangan115, tanggal115);
                    Toast.makeText(AddEditActivity115.this, "Berhasil Tambah", Toast.LENGTH_SHORT).show();
                } else {
                    dbHelper115.updateTransaction115(transId115, kategori115, nominal115, keterangan115, tanggal115);
                    Toast.makeText(AddEditActivity115.this, "Berhasil Update", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

        btnDelete115.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v115) {
                dbHelper115.deleteTransaction115(transId115);
                Toast.makeText(AddEditActivity115.this, "Berhasil Hapus", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
