package com.plextom.todoapp.repository.callbacks;

public interface TodoUpdateCallBack {

    void onTodoModelUpdateFailed(String msg);

    void onTodoModelUpdateSuccess(int id);
}
