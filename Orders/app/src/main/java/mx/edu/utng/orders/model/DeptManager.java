package mx.edu.utng.orders.model;

/**
 * Created by PandithaGD on 23/02/2017.
 */

public class DeptManager {

    private String idDept;
    private String nameDept;
    private String fromDate;
    private String toDate;

    public DeptManager(String idDept,String nameDept, String fromDate, String toDate) {
        this.idDept= idDept;
        this.nameDept = nameDept;
        this.fromDate= fromDate;
        this.toDate = toDate;
    }

    public DeptManager() {
        this("","","","");
    }

    public String getIdDept() {
        return idDept;
    }

    public void setIdDept(String idDept) {
        this.idDept = idDept;
    }

    public String getNameDept() {
        return nameDept;
    }

    public void setNameDept(String nameDept) {
        this.nameDept = nameDept;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idDept='" + idDept + '\'' +
                ", nameDept='" + nameDept + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}


