package mx.edu.utng.orders.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mx.edu.utng.orders.R;
import mx.edu.utng.orders.model.DeptManager;
import mx.edu.utng.orders.model.Product;

/**
 * Created by PandithaGD on 23/02/2017.
 */

public class DeptAdapter extends RecyclerView.Adapter<DeptAdapter.DeptViewHolder>
        implements View.OnClickListener {

    List<DeptManager> deptManagers;
    View.OnClickListener listener;

    public DeptAdapter(List<DeptManager> deptManagers){
        this.deptManagers = deptManagers;
    }

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }



    @Override
    public void onBindViewHolder(DeptAdapter.DeptViewHolder holder, int position) {
        holder.tvDeptManagerName.setText(deptManagers.get(position).getNameDept());
        holder.tvDeptManagerFromData.setText(deptManagers.get(position).getFromDate());
        holder.tvDeptManagerTpData.setText(deptManagers.get(position).getToDate());
        holder.ivManager.setImageResource(R.mipmap.ic_launcher);
        holder.setListener(this);

    }

    @Override
    public int getItemCount() {
        return deptManagers.size();
    }


    @Override
    public void onClick(View v) {
        if(listener!= null){
            listener.onClick(v);
        }
    }


    @Override
    public DeptAdapter.DeptViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dept_layout, parent, false);
        DeptAdapter.DeptViewHolder holder = new DeptAdapter.DeptViewHolder(view);
        view.setOnClickListener(this);
        return holder;

    }
        public static class DeptViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            CardView cvDeptManager;
            TextView tvDeptManagerName;
            TextView tvDeptManagerFromData;
            TextView tvDeptManagerTpData;
            ImageView ivManager;
            ImageButton btEditDept;
            ImageButton btDeleteDept;
            View.OnClickListener listener;



            DeptViewHolder(View itemView) {
                super(itemView);
                cvDeptManager = (CardView) itemView.findViewById(R.id.cv_dept_manager);
                ivManager = (ImageView) itemView.findViewById(R.id.iv_dept);
                tvDeptManagerName = (TextView) itemView.findViewById(R.id.tv_dept_name);
                tvDeptManagerFromData = (TextView) itemView.findViewById(R.id.tv_dept_from_data);
                tvDeptManagerTpData = (TextView) itemView.findViewById(R.id.tv_dept_to_data);
                btEditDept = (ImageButton) itemView.findViewById(R.id.bt_edit_dept);
                btDeleteDept = (ImageButton) itemView.findViewById(R.id.bt_delete_dept);
                btEditDept.setOnClickListener(this);
                btDeleteDept.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                }
            }

            public void setListener(View.OnClickListener listener) {
                this.listener = listener;
            }
        }


}



