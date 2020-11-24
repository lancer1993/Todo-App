package com.plextom.todoapp.network;

import com.plextom.todoapp.model.TodoModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @GET("todos")
    Single<List<TodoModel>> todos();

    @PUT("todos/{id}")
    Single<Integer> updateTodo(@Path("id") int id);

    @POST("todos")
    Single<TodoModel> saveTodo(@Body TodoModel todoModel);

    @DELETE("todos/{id}")
    Single<String> deleteTodo(@Path("id") int id);

}
