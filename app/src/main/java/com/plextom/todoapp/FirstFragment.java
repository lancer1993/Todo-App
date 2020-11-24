package com.plextom.todoapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.plextom.todoapp.adapter.TodoListAdapter;
import com.plextom.todoapp.model.TodoModel;
import com.plextom.todoapp.util.AppDialog;
import com.plextom.todoapp.util.CommonUtils;
import com.plextom.todoapp.util.RecyclerItemClickListener;
import com.plextom.todoapp.viewModel.TodoViewModel;

import java.util.List;

public class FirstFragment extends Fragment implements TodoListAdapter.AdapterOnClickListener {

    TodoViewModel todoViewModel;
    RecyclerView recyclerView;
    TodoListAdapter adapter = null;
    private List<TodoModel> todoModels;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        todoViewModel = ViewModelProviders.of(getActivity()).get(TodoViewModel.class);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        setAdapter();

        todoViewModel.getTodoListData();

        if (CommonUtils.isNetworkConnected(getContext()) || CommonUtils.isWifiEnabled(getContext())) {
            todoViewModel.listLiveData.observe(getViewLifecycleOwner(), new Observer<List<TodoModel>>() {
                @Override
                public void onChanged(List<TodoModel> modelList) {
                    adapter.setTodoModelList(modelList);
                    todoModels = modelList;
                }
            });

            recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                }

                @Override
                public void onLongItemClick(View view, int position) {
                    AppDialog.showOptionDialog(getContext(), "Do you want to delete this task ?",
                            new AppDialog.DialogEvent() {
                                @Override
                                protected void onPositiveClicked() {
                                    super.onPositiveClicked();
                                    TodoModel todoModel = todoModels.get(position);
                                    todoViewModel.deleteTodo(todoModel.getId());
                                    todoViewModel.deleteTodoData.observe(getViewLifecycleOwner(), new Observer() {
                                        @Override
                                        public void onChanged(Object o) {
                                            todoModels.remove(position);
                                            adapter.notifyDataSetChanged();
                                        }
                                    });
                                }

                                @Override
                                protected void onNegativeClicked() {
                                    super.onNegativeClicked();
                                }
                            });
                }
            }));

        } else {
            CommonUtils.showAlert(getContext(), "No Internet Connection");
        }
    }

    private void initViews(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.todo_list_recycler_view);
    }

    private void setAdapter() {
        adapter = new TodoListAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onCLick(TodoModel todoModel) {
        todoViewModel.updateToDo(todoModel);
        todoViewModel.updateTodoData.observe(getViewLifecycleOwner(), new Observer() {
            @Override
            public void onChanged(Object o) {

            }
        });
    }
}