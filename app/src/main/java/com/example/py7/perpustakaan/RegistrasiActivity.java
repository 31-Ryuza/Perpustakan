package com.example.py7.perpustakaan;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.text.Html.fromHtml;

public class RegistrasiActivity extends AppCompatActivity {

    EditText TxUsername, TxPassword, TxConPassword;
    Button BtnRegister;
    DBHalper1 dbHalper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        dbHalper = new DBHalper1(this);

        TxUsername = (EditText) findViewById(R.id.txUsernameReg);
        TxPassword = (EditText) findViewById(R.id.txPasswordReg);
        TxConPassword = (EditText) findViewById(R.id.txConPassword);
        BtnRegister = (Button) findViewById(R.id.btnRegister);

        TextView tvRegister = (TextView) findViewById(R.id.tvRegister);

        tvRegister.setText(fromHtml("Back to " + "</font><font color='#FF9800'>Login</font>"));

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrasiActivity.this, MainActivity.class));
            }
        });

        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = TxUsername.getText().toString().trim();
                String password = TxPassword.getText().toString().trim();
                String conPassword = TxConPassword.getText().toString().trim();

                ContentValues values = new ContentValues();

                if (!password.equals(conPassword)) {
                    Toast.makeText(RegistrasiActivity.this, "Password harus sama", Toast.LENGTH_SHORT).show();
                } else if (password.equals(" ") || username.equals("")) {
                    Toast.makeText(RegistrasiActivity.this, "Username dan password harus di isi", Toast.LENGTH_SHORT).show();
                } else {
                    values.put(DBHalper1.rov_username, username);
                    values.put(DBHalper1.rov_password, password);
                    dbHalper.insertData(values);

                    Toast.makeText(RegistrasiActivity.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    public static Spanned fromHtml(String html){
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        }else {
            result = Html.fromHtml(html);
        }
        return result;
    }

}