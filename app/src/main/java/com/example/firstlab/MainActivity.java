package com.example.firstlab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.firstlab.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public native String stringFromJNI();
    public static native int initRng();
    public static native byte[] randomBytes(int no);
    public static native byte[] encrypt(byte[] key, byte[] array);
    public static native byte[] decrypt(byte[] key, byte[] array);
    // Used to load the 'firstlab' library on application startup.
    static {
        System.loadLibrary("firstlab");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int res = initRng();
        byte [] rndString = randomBytes(16);
        byte [] rndKey = randomBytes(30);
        byte [] Encryption = encrypt(rndKey, rndString);
        byte [] Decryprion = decrypt(rndKey, Encryption);

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        tv.setText(stringFromJNI());
    }

}