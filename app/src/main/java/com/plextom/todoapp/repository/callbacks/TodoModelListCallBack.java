package com.plextom.todoapp.repository.callbacks;

import com.plextom.todoapp.model.TodoModel;

import java.util.List;

public interface TodoModelListCallBack {

    void onTodoModelListFailed(String msg);

    void onTodoModelListSuccess(List<TodoModel> modelList);

}
