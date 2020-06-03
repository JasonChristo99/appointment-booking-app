package com.example.carlaundry.view.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carlaundry.R;
import com.example.carlaundry.dao.CleaningStuffDAO;
import com.example.carlaundry.util.EmailAddress;
import com.example.carlaundry.view.AdminHome.AdminHomeActivity;
import com.example.carlaundry.view.CleaningStuffHome.CleaningStuffHomeActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private LoginPresenter loginPresenter;

    private EditText edtEmail;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenter(this);
        edtEmail = findViewById(R.id.edtEmail);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.verifyUser(edtEmail.getText().toString());
            }
        });

        // testing
        edtEmail.setText("admin@mail.com");

        Log.d("LoginStuff", "Cleaners are " + CleaningStuffDAO.getCleaningStuffStringsList());
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToAdminHome() {
        Intent intent = new Intent(this, AdminHomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToStuffHome(EmailAddress emailAddress) {
        Intent intent = new Intent(this, CleaningStuffHomeActivity.class);
        intent.putExtra("stuff_email", emailAddress.toString());

        startActivity(intent);
    }
}
