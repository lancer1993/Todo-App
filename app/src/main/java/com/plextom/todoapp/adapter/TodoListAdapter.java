package com.plextom.todoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.plextom.todoapp.R;
import com.plextom.todoapp.model.TodoModel;

import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.TodoViewHolder> {

    private final LayoutInflater mInflater;
    private final AdapterOnClickListener onCLickListener;
    private List<TodoModel> todoModelList;

    public TodoListAdapter(Context context, AdapterOnClickListener onClickListener) {
        mInflater = LayoutInflater.from(context);
        onCLickListener=onClickListener;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.custom_list_layout, parent, false);
        return new TodoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        if (todoModelList != null) {
            TodoModel todoModel = todoModelList.get(position);
            holder.idCheckBox.setChecked(todoModel.getCompleted());
            String title = todoModel.getTitle();
            holder.taskTitleTextView.setText(title);
            holder.timeTextView.setText("");
            holder.idCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        onCLickListener.onCLick(todoModel);
                    }
                }
            });
        }else{

        }
    }

    @Override
    public int getItemCount() {
        if (todoModelList != null) {
            return todoModelList.size();
        }else{
            return  0;
        }
    }

    public void setTodoModelList(List<TodoModel> todoModels) {
        todoModelList = todoModels;
        notifyDataSetChanged();
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox idCheckBox;
        private final TextView taskTitleTextView, timeTextView;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            idCheckBox = itemView.findViewById(R.id.task_check_box);
            taskTitleTextView = itemView.findViewById(R.id.task_name_text);
            timeTextView = itemView.findViewById(R.id.time_text);
        }
    }

    public interface AdapterOnClickListener{
        void onCLick(TodoModel todoModel);
    }

}
