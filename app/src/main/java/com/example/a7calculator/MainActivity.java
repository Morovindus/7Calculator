package com.example.a7calculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a7calculator.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    Button Reset, Calculate;
    EditText Price, InitialPayment, Term, InterestRate;
    TextView MonthlyPayment1, MonthlyPayment2;
    Intent intent;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Калькулятор ипотеки");

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        Reset = binding.buttonReset;
        Calculate = binding.buttonCalculate;
        Price = binding.Price;
        InitialPayment = binding.InitialPayment;
        Term = binding.Term;
        InterestRate = binding.InterestRate;
        MonthlyPayment1 = binding.Monthlypayment;
        MonthlyPayment2 = binding.Mounthlypayment2;

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
            Intent intent = new Intent(MainActivity.this, ReferenceInformation.class);
            startActivity(intent);
        } else if (id == R.id.item2){
            Intent intent2 = new Intent(MainActivity.this, AuthorInformation.class);
            startActivity(intent2);
        } else if (id == R.id.item3){
            showResult(binding.getRoot());
        }

        return super.onOptionsItemSelected(item);
    }

    public void showResult(View v){
        CustomDialogFragment dialog = new CustomDialogFragment();
        dialog.show(getSupportFragmentManager(), "custom");
    }

    public void OnClickReset(View view) {
        Price.setText(""); Price.setHint("");
        InitialPayment.setText(""); InitialPayment.setHint("");
        Term.setText(""); Term.setHint("");
        InterestRate.setText(""); InterestRate.setHint("");
    }

    public void OnClickСalculate(View view) {
        double price, initialpayment, term, interestrate, monthlypayment;

        int count = 0;
        try {
            price = Double.parseDouble(Price.getText().toString());
        } catch (Exception e) {
            Price.setHint("Обязательно заполните это поле");
            count += 1;
        }
        try {
            initialpayment = Double.parseDouble(InitialPayment.getText().toString());
        } catch (Exception e) {
            InitialPayment.setHint("Обязательно заполните это поле");
            count += 1;
        }
        try {
            term = Double.parseDouble(Term.getText().toString());
        } catch (Exception e) {
            Term.setHint("Срок");
            count += 1;
        }
        try {
            interestrate = Double.parseDouble(InterestRate.getText().toString());
        } catch (Exception e) {
            InterestRate.setHint("Ставка");
            count += 1;
        }
        if (count != 0)
            return;
        price = Double.parseDouble(Price.getText().toString());
        initialpayment = Double.parseDouble(InitialPayment.getText().toString());
        term = Double.parseDouble(Term.getText().toString());
        interestrate = Double.parseDouble(InterestRate.getText().toString());
        if (term == 0)
            monthlypayment = (price - initialpayment);
        else if (interestrate == 0)
            monthlypayment = (price - initialpayment) / term;
        else {
            interestrate = interestrate / 100 / 12;
            term = term * 12;
            double variable = interestrate * Math.pow(1 + interestrate, term);
            monthlypayment = (price - initialpayment) * (variable / (Math.pow((1 + interestrate), term) - 1));
        }

        MonthlyPayment1.setText("Ежемесячный платеж");
        MonthlyPayment2.setText((String.format("%.2f", monthlypayment)).toString() + " Р");
    }
}