package com.example.dailygratitude;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    DBHelper dbHelper;

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

        // Ініціалізація об'єкту DBHelper
        dbHelper = new DBHelper(this);

        // Тепер ви можете використовувати dbHelper для виконання запитів до бази даних
        // Наприклад:
        // SQLiteDatabase db = dbHelper.getWritableDatabase();
        // Виконайте запити тут
        // db.close(); // Не забудьте закрити базу даних, коли ви закінчите використовувати її
    }
}