package com.example.dailygratitude;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    private static final String TABLE_GRATITUDES = "gratitudes";
    private static final String COLUMN_GRATITUDE_ID = "gratitude_id";
    private static final String COLUMN_TEXT = "text";
    private static final String COLUMN_IMAGE_PATH = "image_path";
    private static final String COLUMN_AUDIO_PATH = "audio_path";
    private static final String COLUMN_TIMESTAMP = "timestamp";
    private static final String COLUMN_FAVOURITE = "favourite";
    private static final String COLUMN_USER_FOREIGN_KEY = "user_id";

    private static final String TABLE_JOURNAL = "journal";
    private static final String COLUMN_JOURNAL_ID = "journal_id";
    private static final String COLUMN_JOURNAL_GRATITUDE_ID = "gratitude_id";
    private static final String COLUMN_JOURNAL_DATE = "date";

    private static final String TABLE_FAVOURITES = "favourites";
    private static final String COLUMN_FAVOURITES_ID = "favourites_id";
    private static final String COLUMN_FAVOURITES_GRATITUDE_ID = "gratitude_id";

    private static final String TABLE_PREMIUM_USERS = "premium_users";
    private static final String COLUMN_PREMIUM_USER_ID = "premium_user_id";
    private static final String COLUMN_PREMIUM_START_DATE = "start_date";
    private static final String COLUMN_PREMIUM_EXPIRATION_DATE = "expiration_date";

    private static final String TABLE_CHALLENGE = "challenges";
    private static final String COLUMN_CHALLENGE_ID = "challenge_id";
    private static final String COLUMN_CHALLENGE_TITLE = "title";
    private static final String COLUMN_CHALLENGE_DESCRIPTION = "description";
    private static final String COLUMN_CHALLENGE_START_DATE = "start_date";
    private static final String COLUMN_CHALLENGE_END_DATE = "end_date";
    private static final String COLUMN_CHALLENGE_TARGET_COUNT = "target_count";
    private static final String COLUMN_CHALLENGE_USER_ID = "user_id";
    private static final String COLUMN_CHALLENGE_PRIORITY = "priority";
    private static final String COLUMN_CHALLENGE_COMPLETED = "completed";


    // Конструктор
    public DBHelper(@Nullable Context context) {
        super(context, "dailygratitude.db", null, 1);
    }

    // Метод створення бази даних
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Створення таблиць
        String createUserTableQuery = "CREATE TABLE " + TABLE_USERS + " ( " +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PASSWORD + " TEXT )";

        String createGratitudesTableQuery = "CREATE TABLE " + TABLE_GRATITUDES + " ( " +
                COLUMN_GRATITUDE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TEXT + " TEXT, " +
                COLUMN_IMAGE_PATH + " TEXT, " +
                COLUMN_AUDIO_PATH + " TEXT, " +
                COLUMN_FAVOURITE + " BOOLEAN DEFAULT 0, " + // Значення за замовчуванням - false
                COLUMN_USER_FOREIGN_KEY + " INTEGER, " +
                COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (" + COLUMN_USER_FOREIGN_KEY + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + ") )";

        String createJournalTableQuery = "CREATE TABLE " + TABLE_JOURNAL + " ( " +
                COLUMN_JOURNAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_JOURNAL_GRATITUDE_ID + " INTEGER, " +
                COLUMN_JOURNAL_DATE + " TEXT, " +
                "FOREIGN KEY (" + COLUMN_JOURNAL_GRATITUDE_ID + ") REFERENCES " + TABLE_GRATITUDES + "(" + COLUMN_GRATITUDE_ID + ") )";

        String createFavouritesTableQuery = "CREATE TABLE " + TABLE_FAVOURITES + " ( " +
                COLUMN_FAVOURITES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FAVOURITES_GRATITUDE_ID + " INTEGER, " +
                "FOREIGN KEY (" + COLUMN_FAVOURITES_GRATITUDE_ID + ") REFERENCES " + TABLE_GRATITUDES + "(" + COLUMN_GRATITUDE_ID + ") )";

        String createPremiumUsersTableQuery = "CREATE TABLE " + TABLE_PREMIUM_USERS + " ( " +
                COLUMN_PREMIUM_USER_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_PREMIUM_START_DATE + " DATE, " +
                COLUMN_PREMIUM_EXPIRATION_DATE + " DATE, " +
                "FOREIGN KEY (" + COLUMN_PREMIUM_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + ") )";

        String createChallengeTableQuery = "CREATE TABLE " + TABLE_CHALLENGE + " ( " +
                COLUMN_CHALLENGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CHALLENGE_TITLE + " TEXT, " +
                COLUMN_CHALLENGE_DESCRIPTION + " TEXT, " +
                COLUMN_CHALLENGE_START_DATE + " DATE, " +
                COLUMN_CHALLENGE_END_DATE + " DATE, " +
                COLUMN_CHALLENGE_TARGET_COUNT + " INTEGER, " +
                COLUMN_CHALLENGE_USER_ID + " INTEGER, " +
                COLUMN_CHALLENGE_PRIORITY + " INTEGER, " +
                COLUMN_CHALLENGE_COMPLETED + " BOOLEAN DEFAULT 0, " +
                "FOREIGN KEY (" + COLUMN_CHALLENGE_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + ") )";

        // Виконання SQL-запитів для створення таблиць
        db.execSQL(createUserTableQuery);
        db.execSQL(createGratitudesTableQuery);
        db.execSQL(createJournalTableQuery);
        db.execSQL(createFavouritesTableQuery);
        db.execSQL(createPremiumUsersTableQuery);
        db.execSQL(createChallengeTableQuery);
    }

    // Метод оновлення бази даних (при необхідності)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Видалення таблиць, якщо вони існують
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GRATITUDES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOURNAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVOURITES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PREMIUM_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHALLENGE);
        onCreate(db);
    }
}
