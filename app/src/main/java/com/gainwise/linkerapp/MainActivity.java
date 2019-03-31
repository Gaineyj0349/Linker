package com.gainwise.linkerapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.gainwise.linker.LinkProfile;
import com.gainwise.linker.Linker;
import com.gainwise.linker.LinkerListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView);

        Linker linker = new Linker(textView);

        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");


        ArrayList<LinkProfile> profiles = new ArrayList<>();
        profiles.add(new LinkProfile("hello world",
                Color.GREEN, false));
        profiles.add(new LinkProfile("goodbye cruel world",
                Color.RED, false));
        profiles.add(new LinkProfile("Whoa awesome!",
                Color.CYAN, true));


        linker.addProfiles(profiles);


        String[] words = new String[]{"One", "Two", "Three"};

        linker.addProfiles(new LinkProfile("helloworld",
                Color.RED, false));
        linker.addStrings(words);
        linker.addStrings(list);
        linker.setLinkColorForCharSequence("world", Color.MAGENTA);
        linker.setListener(new LinkerListener() {
            @Override
            public void onLinkClick(String charSequenceClicked) {
                Toast.makeText(MainActivity.this, charSequenceClicked, Toast.LENGTH_SHORT).show();
            }
        });

        linker.update();

    }
}
