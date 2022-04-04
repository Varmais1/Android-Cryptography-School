package com.example.android_cryptography;

/* Name: Ishaan Varma
Co-coder: Ayowade Owojori
Purpose: Where the functionality of the ciphers are.
Date: 3/29/22

 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int Cipher = 3;
    Button encrypt;
    Button decrypt;
    EditText print;
    EditText encryptText;
    EditText key;
    RadioButton Ceaser;
    RadioButton Scytale;
    RadioButton Viginere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        encrypt=(Button)findViewById(R.id.EncryptButton);
        decrypt=(Button)findViewById(R.id.DecryptButton);
        print=(EditText)findViewById(R.id.print_text);
        encryptText=(EditText)findViewById(R.id.encrypt_text);
        key=(EditText)findViewById(R.id.key);
        encrypt.setOnClickListener(this);
        decrypt.setOnClickListener(this);
        Ceaser = (RadioButton)findViewById(R.id.Ceaser);
        Scytale = (RadioButton)findViewById(R.id.Scytale);
        Viginere = (RadioButton)findViewById(R.id.Viginere);
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.Ceaser:
            break;
            case R.id.Scytale:
            break;
            case R.id.Viginere:
            break;
        }
    }


    public void onClick(View v) {
        if(v.equals(encrypt)) {

            if(Ceaser.isChecked()) {
                Cipher = 0;
            }
            else if(Scytale.isChecked()) {
                Cipher = 1;
            }
            else if(Viginere.isChecked()) {
                Cipher = 2;
            }
            switch (Cipher) {
                case 0:
                    boolean right = false;
                    int mover;
                    //Gets the message, and gets the key, and creates teh output string
                    String message = print.getText().toString();
                    String key3 = key.getText().toString();
                    char mover1;
                    mover1 = key3.charAt(0);
                    try {
                        mover = Integer.parseInt(key3) % 26;
                    }
                    catch(Exception e) {
                            mover = (int) mover1;
                            if (mover <= 122 && mover >= 97) {
                                mover -= 32;
                            }

                    }
                    String encrypted_messsage = "";
                    //goes through the message, and shifts the message by the key by using ASCII
                    for (int i = 0; i < message.length(); i++) {
                        int letter = (int) message.charAt(i);
                        //only continue if valid characters are entered
                        if (65 <= letter && letter <= 90 || 97 <= letter && letter <= 122) {
                            //changes all letters to uppercase
                            if (97 <= letter && letter <= 122) {
                                letter -= 32;
                            }
                            //moves the letter, and loops around to a if needed
                            if(65 <= mover && mover <= 90) {
                                letter += mover - 65;
                            }
                            else if(0<= mover && mover <= 25) {
                                letter += mover;
                            }
                            if (letter > 90) {
                                letter -= 26;
                            }
                            //converts the letter back to a char and adds the letter to the output string
                            char letter1 = (char) letter;
                            String actualLetter = Character.toString(letter1);
                            encrypted_messsage += actualLetter;

                        }
                    }
                    //shows the encrypted message
                    encryptText.setText(encrypted_messsage);
                break;
                case 1:
                    //gets the unencrypted message, the key, and the output string
                    String changeable = print.getText().toString();
                    String mover2 = key.getText().toString();
                    String output = "";
                    //the length and height of the array which the message is put in.
                    int arrayLength = Integer.parseInt(mover2);
                    System.out.println("arrayLength: "+arrayLength);
                    int arrayHeight = changeable.length()/arrayLength;
                    System.out.println("arrayHeight: "+arrayHeight);
                    //if there are not enough rows, then add one
                    if(changeable.length()%arrayLength != 0) {
                        arrayHeight++;
                    }
                    //the array which the message is put in.
                    char[][] text = new char[arrayLength][arrayHeight];
                    //sets every single character in array to @
                    for(int i = 0; i < text.length; i++) {
                        for(int j = 0; j < text[0].length; j++) {
                            text[i][j] = '@';
                        }
                    }
                    //puts the characters into the array
                    for(int i=0; i<changeable.length(); i++) {
                        int height = i/arrayLength;
                        int length = i%arrayLength;
                        char letter4 = changeable.charAt(i);
                        if(97 <= letter4 && letter4 <= 122) {
                            letter4 -= 32;
                        }
                        text[length][height] = letter4;
                        System.out.println("i: "+ i);
                        System.out.println("height: "+height);
                        System.out.println("length: "+length);
                        System.out.println("character: "+text[length][height]);
                    }
                    //gets the characters from the array in a column first array traversal.
                    for(int i = 0; i<text.length; i++) {
                        for(int j = 0; j<text[0].length; j++) {
                            output += Character.toString(text[i][j]);
                        }
                    }
                    //shows the encrypted message.
                    encryptText.setText(output);
                break;
                case 2:
                    //gets the unencrypted message, the key, and creates the output string
                    String printText = print.getText().toString();
                    String mover3;
                    mover3 = key.getText().toString();
                    String message_encrypted = "";
                    for (int i = 0; i < printText.length(); i++) {
                        char letter = printText.charAt(i);
                        int order = i%mover3.length();
                        char shifter = mover3.charAt(order);
                        if (65 <= letter && letter <= 90 || 97 <= letter && letter <= 122) {
                            if (65 <= shifter && shifter <= 90 || 97 <= shifter && shifter <= 122) {
                                if (97 <= letter && letter <= 122) {
                                    letter -= 32;
                                }
                                if(97 <= shifter && shifter <= 122) {
                                    shifter -=32;
                                }
                                letter = (char) ((int) (letter) + (int) (shifter) - 65);
                                if (letter > 90) {
                                    letter -= 26;
                                }
                                message_encrypted += Character.toString(letter);
                            }
                        }
                    }
                    encryptText.setText(message_encrypted);
                break;
                case 3:

                break;

            }
        }
        else if(v.equals(decrypt)) {
            if(Ceaser.isChecked()) {
                Cipher = 0;
            }
            else if(Scytale.isChecked()) {
                Cipher = 1;
            }
            else if(Viginere.isChecked()) {
                Cipher = 2;
            }
            switch(Cipher) {
                case 0:
                    boolean right = false;
                    int mover;
                    //Gets the message, and gets the key
                    String unencryptedText = encryptText.getText().toString();
                    String message = "";
                    for(int i = 0; i<unencryptedText.length(); i++) {
                        if(65 <= (int)unencryptedText.charAt(i) && (int)unencryptedText.charAt(i) <= 90 ||
                                97 <= unencryptedText.charAt(i) && unencryptedText.charAt(i) <= 122) {
                            message += unencryptedText.charAt(i);
                        }
                    }
                    System.out.println("message: "+message);
                    String key4 = key.getText().toString();
                    char mover1;
                    mover1 = key4.charAt(0);
                    try {
                        mover = Integer.parseInt(key4) % 26;
                    }
                    catch(Exception e) {
                        mover = (int) mover1;
                        if (mover <= 122 && mover >= 97) {
                            mover -= 32;
                        }

                    }
                    String decrypted_messsage = "";
                    //goes through the message, and shifts the message by the key by using ASCII
                    for (int i = 0; i < message.length(); i++) {
                        int letter = (int) message.charAt(i);
                        //only continue if valid characters are entered
                        if (65 <= letter && letter <= 90 || 97 <= letter && letter <= 122) {
                            //changes all letters to uppercase
                            if (97 <= letter && letter <= 122) {
                                letter -= 32;
                            }
                            //moves the letter, and loops around to a if needed
                            if(65 <= mover && mover <= 90) {
                                letter += mover - 65;
                            }
                            else if(0<= mover && mover <= 25) {
                                letter -= mover;
                            }
                            if (letter < 65) {
                                letter += 26;
                            }
                            //converts the letter back to a char and adds the letter to the output string
                            char letter1 = (char) letter;
                            String actualLetter = Character.toString(letter1);
                            decrypted_messsage += actualLetter;

                        }
                    }
                    //shows the encrypted message
                    print.setText(decrypted_messsage);
                break;
                case 1:

                break;
                case 2:

                break;
                case 3:

                break;
            }
        }
    }
}