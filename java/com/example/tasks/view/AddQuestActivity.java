package com.example.tasks.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tasks.R;
import com.example.tasks.model.DatabaseSQL;
import com.example.tasks.modelview.Quest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddQuestActivity extends AppCompatActivity {

    EditText editTextName, editTextDescripion;
    FloatingActionButton buttonAdd;
    ImageButton buttonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_quest);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextName = findViewById(R.id.edit_text_add_name);
        editTextDescripion = findViewById(R.id.edit_text_add_description);
        buttonAdd = findViewById(R.id.button_add);
        buttonBack = findViewById(R.id.button_back_add);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quest quest = new Quest();
                quest.isActiveQuest = 1;
                quest.setName(editTextName.getText().toString());
                quest.setDescription(editTextDescripion.getText().toString());

                DatabaseSQL db = new DatabaseSQL(v.getContext());
                db.addActiveQuest(quest);
                db.close();

                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}