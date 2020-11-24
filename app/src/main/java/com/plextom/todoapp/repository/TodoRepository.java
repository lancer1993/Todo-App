package com.plextom.todoapp.repository;

import com.plextom.todoapp.model.TodoModel;
import com.plextom.todoapp.repository.callbacks.TodoDeleteCallBack;
import com.plextom.todoapp.repository.callbacks.TodoModelListCallBack;
import com.plextom.todoapp.repository.callbacks.TodoSaveCallBack;
import com.plextom.todoapp.repository.callbacks.TodoUpdateCallBack;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TodoRepository {

    public void getAllTodos(TodoModelListCallBack callBack) {
        APIService.apiServiceInstance().retrofitApiService.todos().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((list, exception) -> {
                    if (list != null) {
                        callBack.onTodoModelListSuccess(list);
                    } else {
                        callBack.onTodoModelListFailed(exception.getMessage());
                    }
                });

    }

    public void updateTodo(int id, TodoUpdateCallBack todoUpdateCallBack) {
        APIService.apiServiceInstance().retrofitApiService.updateTodo(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Integer integer) {
                        todoUpdateCallBack.onTodoModelUpdateSuccess(integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        todoUpdateCallBack.onTodoModelUpdateFailed(e.getMessage());
                    }
                });
    }

    public void saveTodo(TodoModel todoModel, TodoSaveCallBack todoSaveCallBack){
        APIService.apiServiceInstance().retrofitApiService.saveTodo(todoModel).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<TodoModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull TodoModel todoModel) {
                        todoSaveCallBack.onTodoModelSaveSuccess(todoModel);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        todoSaveCallBack.onTodoModelSaveFailed(e.getMessage());
                    }
                });
    }

    public void deleteTodo(int id, TodoDeleteCallBack todoDeleteCallBack) {
        APIService.apiServiceInstance().retrofitApiService.deleteTodo(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull String s) {
                        todoDeleteCallBack.onTodoModelDeleteSuccess(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        todoDeleteCallBack.onTodoModelDeleteFailed(e.getMessage());
                    }
                });
    }

}
