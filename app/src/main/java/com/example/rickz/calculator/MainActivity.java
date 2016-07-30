package com.example.rickz.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {
    String number = "0";
    String savedNumber = "";
    char operation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public boolean isWholeNumber(BigDecimal number) {
        if (number.scale() <= 0)
        {
            return true;
        }
        else if(number.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0)
        {
            return true;
        }
        return false;
    }
    public void equals(View view)
    {
        if (savedNumber.isEmpty() || number.isEmpty())
        {
            return;
        }
        BigDecimal num1 = new BigDecimal(savedNumber);
        BigDecimal num2 = new BigDecimal(number);
        BigDecimal answer = new BigDecimal("0");
        if (operation == '+')
        {
            answer = num1.add(num2);
        }
        else if (operation == '%')
        {
            if (num2.toString().equals("0"))
            {
                display("ERROR");
                return;
            }
            else
            {
                answer = num1.remainder(num2);
            }
        }
        else if (operation == '×')
        {
            answer = num1.multiply(num2);
        }
        else if (operation == '/')
        {
            if (num2.toString().equals("0"))
            {
                display("ERROR");
                return;
            }
            else
            {
                answer = num1.divide(num2);
            }
        }
        else if (operation == '-')
        {
            answer = num1.subtract(num2);
        }
        number = answer.toPlainString();
        if (number.contains("."))
        {
            if (isWholeNumber(answer))
            {
                number = number.substring(0,number.indexOf('.'));
            }
        }
        display(number);
        savedNumber = "";
        removeOtherOperation(view);
    }
    public void update(View view)
    {
        switch(view.getId())
        {
            case R.id.decimal:
                if (!number.substring(number.length()-1,number.length()).equals("."))
                {
                    number = number + ".";
                }
                display(number);
                break;
            case R.id.one:
                if (number.equals("0"))
                {
                    number = "1";
                }
                else
                {
                    number = number + "1";
                }
                display(number);
                break;
            case R.id.two:
                if (number.equals("0"))
                {
                    number = "2";
                }
                else
                {
                    number = number + "2";
                }
                display(number);
                break;
            case R.id.three:
                if (number.equals("0"))
                {
                    number = "3";
                }
                else
                {
                    number = number + "3";
                }
                display(number);
                break;
            case R.id.four:
                if (number.equals("0"))
                {
                    number = "4";
                }
                else
                {
                    number = number + "4";
                }
                display(number);
                break;
            case R.id.five:
                if (number.equals("0"))
                {
                    number = "5";
                }
                else
                {
                    number = number + "5";
                }
                display(number);
                break;
            case R.id.six:
                if (number.equals("0"))
                {
                    number = "6";
                }
                else
                {
                    number = number + "6";
                }
                display(number);
                break;
            case R.id.seven:
                if (number.equals("0"))
                {
                    number = "7";
                }
                else
                {
                    number = number + "7";
                }
                display(number);
                break;
            case R.id.eight:
                if (number.equals("0"))
                {
                    number = "8";
                }
                else
                {
                    number = number + "8";
                }
                display(number);
                break;
            case R.id.nine:
                if (number.equals("0"))
                {
                    number = "9";
                }
                else
                {
                    number = number + "9";
                }
                display(number);
                break;
            case R.id.zero:
                if (!number.equals("0"))
                {
                    number = number + "0";
                    display(number);
                }
                break;
        }
    }
    public void saveOperation(View view)
    {
        equals(view);
        switch (view.getId())
        {
            case R.id.modulus:
                removeOtherOperation(view);
                operation = '%';
                if (!number.equals("0"))
                {
                    savedNumber = number;
                }
                number = "0";
                view.setBackgroundResource(R.drawable.button_border);
                break;
            case R.id.divide:
                removeOtherOperation(view);
                operation = '/';
                if (!number.equals("0"))
                {
                    savedNumber = number;
                }
                view.setBackgroundResource(R.drawable.button_border);
                number = "0";
                break;
            case R.id.multiply:
                removeOtherOperation(view);
                operation = '×';
                if (!number.equals("0"))
                {
                    savedNumber = number;
                }
                view.setBackgroundResource(R.drawable.button_border);
                number = "0";
                break;
            case R.id.add:
                removeOtherOperation(view);
                operation = '+';
                if (!number.equals("0"))
                {
                    savedNumber = number;
                }
                view.setBackgroundResource(R.drawable.button_border);
                number = "0";
                break;
            case R.id.subtract:
                removeOtherOperation(view);
                operation = '-';
                if (!number.equals("0"))
                {
                    savedNumber = number;
                }
                view.setBackgroundResource(R.drawable.button_border);
                number = "0";
                break;
        }

    }
    public void removeOtherOperation(View view)
    {
        if (operation == '+')
        {
            Button b = ((Button)findViewById(R.id.add));
            b.setBackgroundResource(R.drawable.button_noborder);
        }
        else if (operation == '%')
        {
            Button b = ((Button)findViewById(R.id.modulus));
            b.setBackgroundResource(R.drawable.button_noborder);
        }
        else if (operation == '×')
        {
            Button b = ((Button)findViewById(R.id.multiply));
            b.setBackgroundResource(R.drawable.button_noborder);
        }
        else if (operation == '/')
        {
            Button b = ((Button)findViewById(R.id.divide));
            b.setBackgroundResource(R.drawable.button_noborder);
        }
        else if (operation == '-')
        {
            Button b = ((Button)findViewById(R.id.subtract));
            b.setBackgroundResource(R.drawable.button_noborder);
        }
    }
    public void invert(View view)
    {
        if (!number.equals("0"))
        {
            if (number.charAt(0) == '-')
            {
                number = number.substring(1);
            }
            else
            {
                number = "-" + number;
            }
        }
        display(number);
    }
    public void clear(View view)
    {
        TextView display = ((TextView)findViewById(R.id.answer));
        number = "0";
        savedNumber = "";
        display("0");
        removeOtherOperation(view);
    }
    public void display(String s)
    {
        TextView display = ((TextView)findViewById(R.id.answer));
        display.setText(s);
    }
}
