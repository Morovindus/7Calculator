package com.example.a7calculator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.a7calculator.databinding.ActivityAuthorInformationBinding;

public class AuthorInformation extends AppCompatActivity {

    private ActivityAuthorInformationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityAuthorInformationBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setTitle("Калькулятор ипотеки");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.item1){
            Intent intent = new Intent(AuthorInformation.this, ReferenceInformation.class);
            startActivity(intent);
        } else if (id == android.R.id.home){
            startActivityAfterCleanup(MainActivity.class);
        } else if (id == R.id.item3){
            showResult(binding.getRoot());
        }

        return super.onOptionsItemSelected(item);
    }

    public void showResult(View v){
        CustomDialogFragment dialog = new CustomDialogFragment();
        dialog.show(getSupportFragmentManager(), "custom");
    }

    private void startActivityAfterCleanup(Class<?> cls) {
        Intent intent = new Intent(getApplicationContext(), cls);
        startActivity(intent);
    }
}