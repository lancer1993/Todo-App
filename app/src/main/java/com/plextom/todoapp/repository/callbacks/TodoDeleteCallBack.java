package com.plextom.todoapp.repository.callbacks;

public interface TodoDeleteCallBack {

    void onTodoModelDeleteFailed(String msg);

    void onTodoModelDeleteSuccess(String msg);
}
