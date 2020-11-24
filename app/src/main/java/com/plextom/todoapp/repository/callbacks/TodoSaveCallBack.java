package com.plextom.todoapp.repository.callbacks;

import com.plextom.todoapp.model.TodoModel;

public interface TodoSaveCallBack {

    void onTodoModelSaveFailed(String message);

    void onTodoModelSaveSuccess(TodoModel todoModel);

}
