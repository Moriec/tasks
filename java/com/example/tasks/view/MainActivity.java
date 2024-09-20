package com.example.tasks.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import com.example.tasks.R;
import com.example.tasks.model.DatabaseSQL;
import com.example.tasks.modelview.Quest;

public class MainActivity extends AppCompatActivity {
    Button buttonActive, buttonComplete, buttonAddQuest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        buttonActive = findViewById(R.id.buttonActive);
        buttonComplete = findViewById(R.id.buttonComplete);
        buttonAddQuest = findViewById(R.id.button_add_quests);

        ActiveFragment activeFragment = new ActiveFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout, activeFragment);
        ft.commit();
        buttonActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActiveFragment activeFragment = new ActiveFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame_layout, activeFragment);
                ft.commit();
            }
        });

        buttonComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompleteFragment completeFragment = new CompleteFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame_layout, completeFragment);
                ft.commit();
            }
        });

        buttonAddQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddQuestActivity.class);
                startActivity(intent);
            }
        });
    }
}