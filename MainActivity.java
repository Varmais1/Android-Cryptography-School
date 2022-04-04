// Author: Ayowade owojori
// Date:4/4/22
// Purpose: Layout of Cryptography assignment
// Helper: Ishaan Varma
package com.example.cryptography;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.cryptography.R;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int cipher = 3;
    Button encrypt;
    Button decrypt;
    EditText decrypt_code;
    EditText input_code;
    EditText key_code;
    RadioButton Caeser;
    RadioButton Vigenere;
    RadioButton Scytale;


    public void onRadioButtonClicked(View v) {
        // Is the button now checked?
        boolean checked = ((RadioButton) v).isChecked();

        // Check which radio button was clicked
        switch(v.getId()) {
            case R.id.radio_Caesar:
                if (checked){
                    cipher = 0;
                }
                    break;
            case R.id.radio_Vigenere:
                if (checked){
                    cipher = 1;
                }
                    break;
            case R.id.radio_Scytale:
                if (checked){
                    cipher = 2;
                }
                    break;
        }
    }
    //do we have to do a case where you can't let user type #
    public void onClick(View v){
        if(v.equals(encrypt)){
            if(Caeser.isChecked()){
                cipher = 0;
            }
            if(Vigenere.isChecked()){
                cipher = 2;
            }
            if(Scytale.isChecked()){
                cipher = 1;
            }
            switch (cipher){
                case 0:
                    boolean right = false;
                    int mover;
                    //Gets the message, and gets the key, and creates teh output string
                    String message = decrypt_code.getText().toString();
                    String key3 = key_code.getText().toString();
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
                    input_code .setText(encrypted_messsage);

                    break;
                case 1:
                    //gets the unencrypted message, the key, and the output string
                    String changed = decrypt_code.getText().toString();
                    String mover2 = key_code.getText().toString();
                    String output = "";
                    String changeable = "";
                    if(!mover2.equals("")) {
                        //the length and height of the array which the message is put in.
                        int arrayLength = Integer.parseInt(mover2);
                        for(int i=0; i < changed.length(); i++) {
                            if(65<=(int)(changed.charAt(i)) && 90 >= (int)(changed.charAt(i)) || 97<= (int)changed.charAt(i)
                                    && 122 >= (int)changed.charAt(i)) {
                                changeable += changed.charAt(i);
                            }
                        }
                        System.out.println("arrayLength: " + arrayLength);
                        int arrayHeight = changeable.length() / arrayLength;
                        System.out.println("arrayHeight: " + arrayHeight);
                        //if there are not enough rows, then add one
                        if (changeable.length() % arrayLength != 0) {
                            arrayHeight++;
                        }

                        //the array which the message is put in.
                        char[][] text = new char[arrayLength][arrayHeight];
                        //sets every single character in array to @
                        for (int i = 0; i < text.length; i++) {
                            for (int j = 0; j < text[0].length; j++) {
                                text[i][j] = '@';
                            }
                        }
                        //puts the characters into the array
                        for (int i = 0; i < changeable.length(); i++) {
                            int height = i / arrayLength;
                            int length = i % arrayLength;
                            char letter4 = changeable.charAt(i);
                            if (97 <= letter4 && letter4 <= 122) {
                                letter4 -= 32;
                            }
                            text[length][height] = letter4;
                            System.out.println("i: " + i);
                            System.out.println("height: " + height);
                            System.out.println("length: " + length);
                            System.out.println("character: " + text[length][height]);
                        }
                        //gets the characters from the array in a column first array traversal.
                        for (int i = 0; i < text.length; i++) {
                            for (int j = 0; j < text[0].length; j++) {
                                output += Character.toString(text[i][j]);
                            }
                        }
                        //shows the encrypted message.
                        input_code.setText(output);
                    }

                    break;
                case 2:
                    //gets the unencrypted message, the key, and creates the output string
                    String printText = decrypt_code.getText().toString();
                    String mover3;
                    mover3 = key_code.getText().toString();
                    String message_encrypted = "";
                    String printTextReal = "";
                    if(!mover3.equals("")) {
                        for (int i = 0; i < printText.length(); i++) {
                            if (65 <= (int) (printText.charAt(i)) && 90 >= (int) (printText.charAt(i)) || 97 <= (int) printText.charAt(i)
                                    && 122 >= (int) printText.charAt(i)) {
                                printTextReal += printText.charAt(i);
                            }
                        }
                        printText = printTextReal;
                        for (int i = 0; i < printText.length(); i++) {
                            char letter = printText.charAt(i);
                            int order = i % mover3.length();
                            char shifter = mover3.charAt(order);
                            if (65 <= letter && letter <= 90 || 97 <= letter && letter <= 122) {
                                if (65 <= shifter && shifter <= 90 || 97 <= shifter && shifter <= 122) {
                                    if (97 <= letter && letter <= 122) {
                                        letter -= 32;
                                    }
                                    if (97 <= shifter && shifter <= 122) {
                                        shifter -= 32;
                                    }
                                    letter = (char) ((int) (letter) + (int) (shifter) - 65);
                                    if (letter > 90) {
                                        letter -= 26;
                                    }
                                    message_encrypted += Character.toString(letter);
                                }
                            }
                        }
                        input_code.setText(message_encrypted);
                    }
                break;
                case 3:

                break;

            }
        }
        else if(v.equals(decrypt)){
            switch (cipher){
                case 0:
                    boolean right = false;
                        int mover;
                        //Gets the message, and gets the key
                        String message = input_code.getText().toString();
                        String key4 = key_code.getText().toString();
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
        //shows the decrypted message
                    decrypt_code.setText(decrypted_messsage);

                    break;
                case 1:
                    //gets the encrypted message, the key, and creates output string
                    String changeable = input_code.getText().toString();
                    String mover4 = key_code.getText().toString();
                    String output4 = "";
                    //the length and height of the array which the message is put in.
                    int arrayHeight = Integer.parseInt(mover4);
                    int arrayLength =changeable.length()/arrayHeight;
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
                        int height = i%arrayHeight;
                        int length = i/arrayHeight;
                        text[length][height] = changeable.charAt(i);
                    }
                    //gets the characters from the array in a column first array traversal.
                    for(int i = 0; i<text[0].length; i++) {
                        for(int j = 0; j<text.length; j++) {
                            output4 += Character.toString(text[j][i]);
                        }
                    }
                    //shows the decrypted message.
                    decrypt_code.setText(output4);

                    break;
                case 2:
                    //gets the unencrypted message, the key, and creates the output string
                    String printText = input_code.getText().toString();
                    String mover3;
                    mover3 = key_code.getText().toString();
                    String message_encrypted = "";
                    String printTextReal = "";
                    if(!mover3.equals("")) {
                        for (int i = 0; i < printText.length(); i++) {
                            if (65 <= (int) (printText.charAt(i)) && 90 >= (int) (printText.charAt(i)) || 97 <= (int) printText.charAt(i)
                                    && 122 >= (int) printText.charAt(i)) {
                                printTextReal += printText.charAt(i);
                            }
                        }
                        printText = printTextReal;
                        for (int i = 0; i < printText.length(); i++) {
                            char letter = printText.charAt(i);
                            int order = i % mover3.length();
                            char shifter = mover3.charAt(order);
                            if (65 <= letter && letter <= 90 || 97 <= letter && letter <= 122) {
                                if (65 <= shifter && shifter <= 90 || 97 <= shifter && shifter <= 122) {
                                    if (97 <= letter && letter <= 122) {
                                        letter -= 32;
                                    }
                                    if (97 <= shifter && shifter <= 122) {
                                        shifter -= 32;
                                    }
                                    letter = (char) ((int) (letter) - ((int) (shifter) - 65));
                                    if (letter < 65) {
                                        letter += 26;
                                    }
                                    message_encrypted += Character.toString(letter);
                                }
                            }
                        }
                        decrypt_code.setText(message_encrypted);
                    }

                    break;
                case 3:

                break;
            }
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        encrypt = (Button)this.findViewById(R.id.Encryptbutton);
        encrypt.setOnClickListener(this);
        input_code = (EditText)this.findViewById(R.id.editTextEncryptCode);
        decrypt_code = (EditText)this.findViewById(R.id.editTextDecryptCode);
        key_code = (EditText)this.findViewById(R.id.editTextKeyforcode);
        decrypt = (Button)this.findViewById(R.id.Decryptbutton);
        Caeser = (RadioButton)this.findViewById(R.id.radio_Caesar);
        Vigenere = (RadioButton)this.findViewById(R.id.radio_Vigenere);
        Scytale = (RadioButton)this.findViewById(R.id.radio_Scytale);
        decrypt.setOnClickListener(this);
    }


}