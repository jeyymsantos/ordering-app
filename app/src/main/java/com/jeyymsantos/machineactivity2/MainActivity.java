package com.jeyymsantos.machineactivity2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // Pizza
    private RadioGroup rdGrpPizzaSize;
    private RadioButton rdPizzaSmall, rdPizzaMedium, rdPizzaLarge, rdPizzaJumbo;
    private CheckBox cbMushrooms, cbOnions, cbOlives, cbSausage, cbPepperoni, cbMoreCheese;
    private EditText edPizzaQuantity;

    // Burger
    private RadioGroup rdGrpBurgerType;
    private EditText edBurgerQuantity;
    private RadioButton rdChickenBurger, rdBeefBurger, rdVegetarianBurger;

    // Beverages
    private RadioGroup rdGrpBeveragesType, rdGrpBeveragesSize;
    private RadioButton rdCoke, rdSprite, rdRoyal, rdCoffee, rdTea;
    private RadioButton rdBeveragesRegular, rdBeveragesLarge;
    private EditText edBeveragesQuantity;

    // Mode of Payment
    private RadioGroup rdGrpPayment;
    private RadioButton rdPaymentCash, rdPaymentDebit, rdPaymentCredit;

    // Other Essentials
    private Button btnOrderNow;
    private AlertDialog.Builder alertDialog;

    // Prices && Percentages
    private final int smallPizza = 75;
    private final int mediumPizza = 150;
    private final int largePizza = 250;
    private final int jumboPizza = 400;
    private final int chickenBurger = 50;
    private final int beefBurger = 75;
    private final int vegetarianBurger = 60;
    private final int softDrinksRegular = 35;
    private final int coffeeRegular = 30;
    private final int softDrinksCoffeeLarge = 50;
    private final int teaRegular = 25;
    private final int teaLarge = 40;
    private final int addToppings = 20;
    private final double debitPayment = 0.05;
    private final double creditPayment = 0.1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initializeViews();

        btnOrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tab = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
                String message = "<b> You ordered the following: </b><br><br>";
                int pizza = 0, toppings = 0, burger = 0, beverages = 0;
                double total = 0.00;

                message += edPizzaQuantity.getText().toString() + " ";

                // PIZZA CHECKER
                RadioButton selectedPizza = null;
                if (rdPizzaSmall.isChecked()) {
                    pizza = Integer.parseInt(edPizzaQuantity.getText().toString()) * smallPizza;
                    selectedPizza = rdPizzaSmall;
                } else if (rdPizzaMedium.isChecked()) {
                    pizza = Integer.parseInt(edPizzaQuantity.getText().toString()) * mediumPizza;
                    selectedPizza = rdPizzaMedium;
                } else if (rdPizzaLarge.isChecked()) {
                    pizza = Integer.parseInt(edPizzaQuantity.getText().toString()) * largePizza;
                    selectedPizza = rdPizzaLarge;
                } else if (rdPizzaJumbo.isChecked()) {
                    pizza = Integer.parseInt(edPizzaQuantity.getText().toString()) * jumboPizza;
                    selectedPizza = rdPizzaJumbo;
                }

                message += selectedPizza.getText().toString() + " Pizza: " + tab;
                message += String.valueOf(pizza);
                message += "<br>With the following additional toppings: <br>";

                // TOPPINGS CHECKER
                if (cbMushrooms.isChecked()) {
                    message += "\t\t\t\t " + cbMushrooms.getText().toString() + "<br>";
                    toppings += addToppings;
                }
                if (cbOnions.isChecked()) {
                    message += "\t\t\t\t " + cbOnions.getText().toString() + "<br>";
                    toppings += addToppings;
                }
                if (cbOlives.isChecked()) {
                    message += "\t\t\t\t " + cbOlives.getText().toString() + "<br>";
                    toppings += addToppings;
                }
                if (cbSausage.isChecked()) {
                    message += "\t\t\t\t " + cbSausage.getText().toString() + "<br>";
                    toppings += addToppings;
                }
                if (cbPepperoni.isChecked()) {
                    message += "\t\t\t\t " + cbPepperoni.getText().toString() + "<br>";
                    toppings += addToppings;
                }
                if (cbMoreCheese.isChecked()) {
                    message += "\t\t\t\t " + cbMoreCheese.getText().toString() + "<br>";
                    toppings += addToppings;
                }

                toppings *= Integer.parseInt(edPizzaQuantity.getText().toString());
                message += "Additional Toppings: \t\t\t\t\t\t\t\t\t\t" + String.valueOf(toppings) + "<br><br>";
                pizza += toppings;
                message += "<b>" + edPizzaQuantity.getText().toString() + " " + selectedPizza.getText().toString() + " Pizza (Total): \t\t\t\t\t\t\t\t\t" + pizza + "<br>";


                // BURGER CHECKER
                RadioButton selectedBurger = null;
                if (rdChickenBurger.isChecked()) {
                    selectedBurger = rdChickenBurger;
                    burger = chickenBurger;
                } else if (rdBeefBurger.isChecked()) {
                    selectedBurger = rdBeefBurger;
                    burger = beefBurger;
                } else if (rdVegetarianBurger.isChecked()) {
                    selectedBurger = rdVegetarianBurger;
                    burger = vegetarianBurger;
                }
                burger *= Integer.parseInt(edBurgerQuantity.getText().toString());
                message += "<b>" + edBurgerQuantity.getText().toString() + " " + selectedBurger.getText().toString() + " (Total): \t\t\t\t\t" + String.valueOf(burger) + "<br>";

                // BEVERAGES CHECKER
                String selectedBeverages = null;
                String selectedSize = null;

                if (rdCoke.isChecked()) {
                    selectedBeverages = rdCoke.getText().toString();
                } else if (rdSprite.isChecked()) {
                    selectedBeverages = rdSprite.getText().toString();
                } else if (rdRoyal.isChecked()) {
                    selectedBeverages = rdRoyal.getText().toString();
                } else if (rdCoffee.isChecked()) {
                    selectedBeverages = rdCoffee.getText().toString();
                } else if (rdTea.isChecked()) {
                    selectedBeverages = rdTea.getText().toString();
                }

                if (rdBeveragesRegular.isChecked()) {
                    selectedSize = rdBeveragesRegular.getText().toString();
                } else if (rdBeveragesLarge.isChecked()) {
                    selectedSize = rdBeveragesLarge.getText().toString();
                }

                if (selectedBeverages.equals("Coke") || selectedBeverages.equals("Sprite") || selectedBeverages.equals("Royal")) {
                    if (selectedSize.equals("Regular")) {
                        beverages = softDrinksRegular;
                    } else {
                        beverages = softDrinksCoffeeLarge;
                    }
                } else if (selectedBeverages.equals("Coffee")) {
                    if (selectedSize.equals("Regular")) {
                        beverages = coffeeRegular;
                    } else {
                        beverages = softDrinksCoffeeLarge;
                    }
                } else if (selectedBeverages.equals("Tea")) {
                    if (selectedSize.equals("Regular")) {
                        beverages = teaRegular;
                    } else {
                        beverages = teaLarge;
                    }
                }

                beverages *= Integer.parseInt(edBeveragesQuantity.getText().toString());
                message += "<b>" + edBeveragesQuantity.getText().toString() + " " + selectedBeverages + " " + selectedSize + " (Total): \t\t\t\t\t\t\t" + beverages + "<br><br>";

                total = pizza + burger + beverages;
                message += "Total Amount is \t\t\t\t\t\t\t\t\t\t\t\t\t\t" + (pizza + burger + beverages) + "<br><br>";

                // PAYMENT CHECKER
                String selectedPayment = null;
                if (rdPaymentCash.isChecked()) {
                    selectedPayment = rdPaymentCash.getText().toString();
                } else if (rdPaymentDebit.isChecked()) {
                    total += total * debitPayment;
                    selectedPayment = rdPaymentDebit.getText().toString();
                } else if (rdPaymentCredit.isChecked()) {
                    total += total * creditPayment;
                    selectedPayment = rdPaymentCredit.getText().toString();
                }

                DecimalFormat decimal = new DecimalFormat("P 0.00");
                message += "Payment thru: \t\t\t\t\t\t\t\t\t\t" + selectedPayment + "<br>";
                message += "Total Amount is \t\t\t\t\t\t\t\t" + decimal.format(total) + "<br><br>";
                message += "Pay Now?";

                // ALERT DIALOG SHOW
                alertDialog.setCancelable(false);
                alertDialog.setTitle("Jeyym's Fast Food Ordering Form");
                alertDialog.setMessage(Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY));
                alertDialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Payment Abort!", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Payment Received!", Toast.LENGTH_SHORT).show();
                        reset();
                    }
                });

                alertDialog.show();

            }
        });
    }

    private void reset() {
        edBeveragesQuantity.setText(null);
        edBurgerQuantity.setText(null);
        edPizzaQuantity.setText(null);

        cbMoreCheese.setChecked(false);
        cbOnions.setChecked(false);
        cbOlives.setChecked(false);
        cbSausage.setChecked(false);
        cbPepperoni.setChecked(false);
        cbMushrooms.setChecked(false);

        rdPizzaSmall.setChecked(false);
        rdPizzaMedium.setChecked(false);
        rdPizzaLarge.setChecked(false);
        rdPizzaJumbo.setChecked(false);
        rdChickenBurger.setChecked(false);
        rdBeefBurger.setChecked(false);
        rdVegetarianBurger.setChecked(false);
        rdCoke.setChecked(false);
        rdSprite.setChecked(false);
        rdRoyal.setChecked(false);
        rdCoffee.setChecked(false);
        rdTea.setChecked(false);
        rdBeveragesRegular.setChecked(false);
        rdBeveragesLarge.setChecked(false);

        rdPaymentCash.setChecked(false);
        rdPaymentDebit.setChecked(false);
        rdPaymentCredit.setChecked(false);

        rdGrpPizzaSize.clearCheck();
        rdGrpBurgerType.clearCheck();
        rdGrpBeveragesType.clearCheck();
        rdGrpBeveragesSize.clearCheck();
        rdGrpPayment.clearCheck();
    }

    private void initializeViews() {
        // Pizza
        rdGrpPizzaSize = findViewById(R.id.rdGrpPizzaSize);
        rdPizzaSmall = findViewById(R.id.rdPizzaSmall);
        rdPizzaMedium = findViewById(R.id.rdPizzaMedium);
        rdPizzaLarge = findViewById(R.id.rdPizzaLarge);
        rdPizzaJumbo = findViewById(R.id.rdPizzaJumbo);
        cbMushrooms = findViewById(R.id.cbMushrooms);
        cbOnions = findViewById(R.id.cbOnions);
        cbOlives = findViewById(R.id.cbOlives);
        cbSausage = findViewById(R.id.cbSausage);
        cbPepperoni = findViewById(R.id.cbPepperoni);
        cbMoreCheese = findViewById(R.id.cbMoreCheese);
        edPizzaQuantity = findViewById(R.id.edPizzaQuantity);

        // Burger
        rdGrpBurgerType = findViewById(R.id.rdGrpBurgerType);
        edBurgerQuantity = findViewById(R.id.edBurgerQuantity);
        rdChickenBurger = findViewById(R.id.rdChickenBurger);
        rdBeefBurger = findViewById(R.id.rdBeefBurger);
        rdVegetarianBurger = findViewById(R.id.rdVegetarianBurger);

        // Beverages
        rdGrpBeveragesType = findViewById(R.id.rdGrpBeveragesType);
        rdGrpBeveragesSize = findViewById(R.id.rdGrpBeveragesSize);
        rdCoke = findViewById(R.id.rdCoke);
        rdSprite = findViewById(R.id.rdSprite);
        rdRoyal = findViewById(R.id.rdRoyal);
        rdCoffee = findViewById(R.id.rdCoffee);
        rdTea = findViewById(R.id.rdTea);
        rdBeveragesRegular = findViewById(R.id.rdBeveragesRegular);
        rdBeveragesLarge = findViewById(R.id.rdBeveragesLarge);
        edBeveragesQuantity = findViewById(R.id.edBeveragesQuantity);

        // Mode of Payment
        rdGrpPayment = findViewById(R.id.rdGrpPayment);
        rdPaymentCash = findViewById(R.id.rdPaymentCash);
        rdPaymentDebit = findViewById(R.id.rdPaymentDebit);
        rdPaymentCredit = findViewById(R.id.rdPaymentCredit);

        // Trigger Button
        btnOrderNow = findViewById(R.id.btnOrderNow);
        alertDialog = new AlertDialog.Builder(this);
    }
}