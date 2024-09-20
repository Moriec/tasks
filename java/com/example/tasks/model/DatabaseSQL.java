package com.example.tasks.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.tasks.modelview.Quest;
import java.util.ArrayList;

public class DatabaseSQL extends SQLiteOpenHelper {
    private static final String DB_NAME = "my_database";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase db;

    public DatabaseSQL(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE activeQuests (_id INTEGER PRIMARY KEY, name TEXT, description TEXT)");
        db.execSQL("CREATE TABLE completeQuests (_id INTEGER PRIMARY KEY, name TEXT, description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    // Чтение информации

    // Получить список всех активных квестов
    public ArrayList<Quest> getAllActiveQuests(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM activeQuests" ,null);
        ArrayList<Quest> questMassiv = new ArrayList<>();
        while(cursor.moveToNext()){
            Quest quest = new Quest();
            quest.setId(cursor.getInt(0));
            quest.setName(cursor.getString(1));
            quest.setDescription(cursor.getString(2));
            quest.isActiveQuest = 1;
            questMassiv.add(quest);
        }
        db.close();
        cursor.close();
        return questMassiv;
    }

    // Получить список всех завершенных квестов
    public ArrayList<Quest> getAllCompleteQuests() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM completeQuests", null);
        ArrayList<Quest> questMassiv = new ArrayList<>();
        while (cursor.moveToNext()) {
            Quest quest = new Quest();
            quest.setId(cursor.getInt(0));
            quest.setName(cursor.getString(1));
            quest.setDescription(cursor.getString(2));
            quest.isActiveQuest = 0;
            questMassiv.add(quest);
        }
        db.close();
        cursor.close();
        return questMassiv;
    }
    // Добавление информации

    // Добавить активный квест
    public boolean addActiveQuest(Quest quest){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", quest.getName());
        contentValues.put("description", quest.getDescription());
        long id = db.insert("activeQuests", null, contentValues);
        db.close();
        return id == -1;
    }

    // Добавить завершенный квест
    public boolean addCompleteQuest(Quest quest){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", quest.getName());
        contentValues.put("description", quest.getDescription());
        long id = db.insert("completeQuests", null, contentValues);
        db.close();
        return id == -1;
    }

    // Удаление информации

    // Удалить активный квест
    public boolean deleteActiveQuest(Quest quest){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("activeQuests", "_id = ?", new String[]{String.valueOf(quest.getId())});
        db.close();
        return result == 1;
    }

    // Удалить завершенный квест
    public boolean deleteCompleteQuest(Quest quest){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("completeQuests", "_id = ?", new String[]{String.valueOf(quest.getId())});
        db.close();
        return result == 1;
    }

    //Изменить Информацию
    public boolean changeActiveQuest(Quest quest) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", quest.getId());
        contentValues.put("name", quest.getName());
        contentValues.put("description", quest.getDescription());
        int result = db.update("activeQuests", contentValues, "_id = ?", new String[]{String.valueOf(quest.getId())});
        db.close();
        return result == 1;
    }
}