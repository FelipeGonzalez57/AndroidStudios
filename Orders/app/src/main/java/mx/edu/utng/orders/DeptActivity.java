package mx.edu.utng.orders;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import mx.edu.utng.orders.adapters.DeptAdapter;
import mx.edu.utng.orders.adapters.ProductAdapter;
import mx.edu.utng.orders.model.DeptManager;
import mx.edu.utng.orders.model.Product;
import mx.edu.utng.orders.sqlite.DBOperations;

/**
 * Created by PandithaGD on 23/02/2017.
 */

public class DeptActivity extends AppCompatActivity {

    private EditText etNameDept;
    private EditText etFromDate;
    private EditText etToDate;
    private Button btSaveDept;
    private DBOperations operations;
    private DeptManager deptManager = new DeptManager() ;
    private List<DeptManager> deptManagers = new ArrayList<DeptManager>();

    private DeptAdapter adapter;

    private RecyclerView rvDeptManagers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept);
        operations = DBOperations.getDBOperations(getApplicationContext());
        deptManager.setIdDept("");
        initComponents();
    }

    private void initComponents(){
        etNameDept = (EditText)findViewById(R.id.et_name_dept);
        etFromDate = (EditText)findViewById(R.id.et_from_date);
        etToDate = (EditText)findViewById(R.id.et_to_date);
        rvDeptManagers = (RecyclerView) findViewById(R.id.rv_dept_list);
        rvDeptManagers.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvDeptManagers.setLayoutManager(layoutManager);
        getDeptMangers();
        adapter = new DeptAdapter(deptManagers);

        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.bt_delete_dept:
                        operations.deleteDept(
                                deptManagers.get(rvDeptManagers.getChildPosition((View)v.getParent().getParent())).getIdDept());
                        getDeptMangers();
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.bt_edit_dept:

                        Cursor c = operations.getDeptManagerById(deptManagers.get(rvDeptManagers.getChildPosition(
                                (View)v.getParent().getParent())).getIdDept());

                        if(c!=null){
                            if(c.moveToFirst()){
                                deptManager = new DeptManager(c.getString(1),
                                        c.getString(2),c.getString(3), c.getString(4));
                            }
                            etNameDept.setText(deptManager.getNameDept());
                            etFromDate.setText(String.valueOf(deptManager.getFromDate()));
                            etToDate.setText(String.valueOf(deptManager.getToDate()));
                        }else{
                            //Toast.makeText(getApplicationContext(),
                            //      "Registro no encontrado", Toast)
                        }

                        break;
                    default:
                        break;

                }
            }
        });
        rvDeptManagers.setAdapter(adapter);
        btSaveDept = (Button) findViewById(R.id.bt_save_dept);

        btSaveDept = (Button)findViewById(R.id.bt_save_dept);

        btSaveDept.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                if (!deptManager.getIdDept().equals("")) {
                    deptManager.setNameDept(etNameDept.getText().toString());
                    deptManager.setFromDate(etFromDate.getText().toString());
                    deptManager.setToDate(etToDate.getText().toString());

                    operations.updateDeptManager(deptManager);
                } else {
                    deptManager = new DeptManager("", etNameDept.getText().toString(),
                             etFromDate.getText().toString(),
                             etToDate.getText().toString());
                    operations.insertDeptManager(deptManager);
                }
                // Log.d("Products","Products");
                // DatabaseUtils.dumpCursor(operations.getProducts());
                clearData();
                getDeptMangers();
                adapter.notifyDataSetChanged();
            }
        });

    }
    private void getDeptMangers() {
        Cursor  c= operations.getDeptManager();
        deptManagers.clear();
        if(c!=null){
            while (c.moveToNext()){
                deptManagers.add(new DeptManager(c.getString(1),
                        c.getString(2),c.getString(3), c.getString(4)));

            }
        }
    }

    private void clearData(){
        etNameDept.setText("");
        etFromDate.setText("");
        etToDate.setText("");
        deptManager= null;
        deptManager = new DeptManager();
        deptManager.setIdDept("");
    }
}

