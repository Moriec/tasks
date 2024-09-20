package com.example.tasks.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tasks.R;
import com.example.tasks.model.DatabaseSQL;
import com.example.tasks.modelview.Quest;

import java.util.ArrayList;


public class CompleteFragment extends Fragment {
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_complete, container, false);
        final ArrayList<String> namesQuests = new ArrayList<>();

        DatabaseSQL db = new DatabaseSQL(getContext());
        ArrayList<Quest> quests = db.getAllCompleteQuests();
        db.close();

        for (Quest quest : quests) {
            namesQuests.add(quest.getName());
        }

        listView = rootView.findViewById(R.id.list_view_complete);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, namesQuests);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                Quest quest = quests.get(position);
                Intent intent = new Intent(getContext(), ReadCompleteQuestsActivity.class);
                intent.putExtra("id", quest.getId());
                intent.putExtra("name", quest.getName());
                intent.putExtra("description", quest.getDescription());
                intent.putExtra("isActive", quest.isActiveQuest);
                startActivity(intent);
            }
        });
        return rootView;
    }
}