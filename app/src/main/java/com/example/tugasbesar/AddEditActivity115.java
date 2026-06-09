package com.example.tugasbesar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

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

        etTanggal115.setFocusable(false);
        etTanggal115.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v115) {
                showDatePickerDialog115();
            }
        });

        userId115 = getIntent().getIntExtra("USER_ID115", -1);
        if (getIntent().hasExtra("TRANS_ID115")) {
            transId115 = getIntent().getIntExtra("TRANS_ID115", -1);
            etKategori115.setText(getIntent().getStringExtra("KATEGORI115"));
            etNominal115.setText(String.valueOf(getIntent().getDoubleExtra("NOMINAL115", 0)));
            etKeterangan115.setText(getIntent().getStringExtra("KETERANGAN115"));
            etTanggal115.setText(getIntent().getStringExtra("TANGGAL115"));
            tvTitle115.setText("Edit Transaksi");
            btnDelete115.setVisibility(View.VISIBLE);
        } else {
            Calendar c115 = Calendar.getInstance();
            String today115 = c115.get(Calendar.YEAR) + "-" + (c115.get(Calendar.MONTH) + 1) + "-" + c115.get(Calendar.DAY_OF_MONTH);
            etTanggal115.setText(today115);
        }

        btnSave115.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v115) {
                if(etKategori115.getText().toString().isEmpty() || etNominal115.getText().toString().isEmpty()) {
                    Toast.makeText(AddEditActivity115.this, "Kategori dan Nominal harus diisi", Toast.LENGTH_SHORT).show();
                    return;
                }
                
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

    private void showDatePickerDialog115() {
        final Calendar c115 = Calendar.getInstance();
        int year115 = c115.get(Calendar.YEAR);
        int month115 = c115.get(Calendar.MONTH);
        int day115 = c115.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog115 = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view115, int year115, int monthOfYear115, int dayOfMonth115) {
                        etTanggal115.setText(year115 + "-" + (monthOfYear115 + 1) + "-" + dayOfMonth115);
                    }
                }, year115, month115, day115);
        datePickerDialog115.show();
    }
}
