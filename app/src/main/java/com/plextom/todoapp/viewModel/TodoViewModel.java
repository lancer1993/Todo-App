package com.plextom.todoapp.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.plextom.todoapp.model.TodoModel;
import com.plextom.todoapp.repository.TodoRepository;
import com.plextom.todoapp.repository.callbacks.TodoDeleteCallBack;
import com.plextom.todoapp.repository.callbacks.TodoModelListCallBack;
import com.plextom.todoapp.repository.callbacks.TodoSaveCallBack;
import com.plextom.todoapp.repository.callbacks.TodoUpdateCallBack;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {

    public MutableLiveData listLiveData = new MutableLiveData();
    public MutableLiveData deleteTodoData = new MutableLiveData();
    public MutableLiveData saveTodoData = new MutableLiveData();
    public MutableLiveData updateTodoData = new MutableLiveData();

    TodoRepository todoRepository;

    public TodoViewModel(Application application) {
        super(application);
        todoRepository = new TodoRepository();
    }

    public void getTodoListData() {
        todoRepository.getAllTodos(new TodoModelListCallBack() {
            @Override
            public void onTodoModelListFailed(String msg) {

            }

            @Override
            public void onTodoModelListSuccess(List<TodoModel> modelList) {
                listLiveData.postValue(modelList);
            }
        });
    }

    public void updateToDo(TodoModel todoModel) {
        todoRepository.updateTodo(todoModel.getId(), new TodoUpdateCallBack() {
            @Override
            public void onTodoModelUpdateFailed(String msg) {

            }

            @Override
            public void onTodoModelUpdateSuccess(int id) {
                updateTodoData.postValue(id);
            }
        });
    }

    public void saveTodo(TodoModel todoModel){
        todoRepository.saveTodo(todoModel, new TodoSaveCallBack() {
            @Override
            public void onTodoModelSaveFailed(String message) {

            }

            @Override
            public void onTodoModelSaveSuccess(TodoModel todoModel) {
                updateTodoData.postValue(todoModel);
            }
        });
    }

    public void deleteTodo(int id){
        todoRepository.deleteTodo(id, new TodoDeleteCallBack() {
            @Override
            public void onTodoModelDeleteFailed(String msg) {

            }

            @Override
            public void onTodoModelDeleteSuccess(String msg) {
                deleteTodoData.postValue(msg);
            }
        });
    }
}
