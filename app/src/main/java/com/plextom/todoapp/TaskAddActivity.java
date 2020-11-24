package com.plextom.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.plextom.todoapp.model.TodoModel;
import com.plextom.todoapp.util.Constant;
import com.plextom.todoapp.viewModel.TodoViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskAddActivity extends AppCompatActivity {

    @BindView(R.id.task_title_text)
    EditText editText;

    @BindView(R.id.save_button)
    Button button;

    TodoViewModel todoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_add);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        todoViewModel = ViewModelProviders.of(TaskAddActivity.this).get(TodoViewModel.class);
    }

    @OnClick(R.id.save_button)
    void onClick(View view) {
        if (!editText.getText().toString().isEmpty()) {
            TodoModel todoModel = new TodoModel();
            todoModel.setId(100);
            todoModel.setUserId(Constant.USER_ID);
            todoModel.setTitle(editText.getText().toString());
            todoModel.setCompleted(false);

            todoViewModel.saveTodo(todoModel);
            Intent intent = new Intent(TaskAddActivity.this, MainActivity.class);
            startActivity(intent);

        } else {
            Snackbar.make(view, "Please enter a task", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}