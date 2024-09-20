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

public class ReadCompleteQuestsActivity extends AppCompatActivity {

    EditText editTextName, editTextDescription;
    ImageButton buttonBack;
    FloatingActionButton buttonDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_read_complete_quests);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextName = findViewById(R.id.edit_text_nameC);
        editTextDescription = findViewById(R.id.edit_text_descriptionC);
        buttonBack = findViewById(R.id.button_back_to_mainC);
        buttonDelete = findViewById(R.id.button_deleteC);

        Intent intent = getIntent();
        Quest quest = new Quest();
        quest.setName(intent.getStringExtra("name"));
        quest.setDescription(intent.getStringExtra("description"));
        quest.isActiveQuest = intent.getIntExtra("isActive", -1);
        quest.setId(intent.getIntExtra("id", -1));

        editTextName.setText(quest.getName());
        editTextDescription.setText(quest.getDescription());

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseSQL db = new DatabaseSQL(v.getContext());
                if (quest.isActiveQuest == 1){
                    db.deleteActiveQuest(quest);
                }
                else{
                    db.deleteCompleteQuest(quest);
                }
                db.close();
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}