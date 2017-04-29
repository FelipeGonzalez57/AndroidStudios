package mx.edu.utng.productws;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by PandithaGD on 20/04/2017.
 */

public class Product implements KvmSerializable {
    private int id;
    private String productName;
    private String supplierId;
    private String categoryId;
    private String quantityPerUnit;
    private String unitPrice;
    private String unitsInStock;
    private String unitsOnOrder;
    private String reorderLevel;
    private String discontinued;

    public Product(int id, String productName, String supplierId, String categoryId, String quantityPerUnit, String unitPrice, String unitsInStock, String unitsOnOrder, String reorderLevel, String discontinued) {
        this.id = id;
        this.productName = productName;
        this.supplierId = supplierId;
        this.categoryId = categoryId;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.unitsOnOrder = unitsOnOrder;
        this.reorderLevel = reorderLevel;
        this.discontinued = discontinued;
    }

    public Product() {
        this(0,"","","","","","","","","");
    }


    @Override
    public Object getProperty(int i) {
        switch (i){
            case 0:
                return  id;
            case 1:
                return  productName;
            case 2:
                return  supplierId;
            case 3:
                return  categoryId;
            case 4:
                return  quantityPerUnit;
            case 5:
                return  unitPrice;
            case 6:
                return  unitsInStock;
            case 7:
                return  unitsOnOrder;
            case 8:
                return  reorderLevel;
            case 9:
                return  discontinued;

        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 10;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i){
            case 0:
                id=Integer.parseInt(o.toString());
                break;
            case 1:
                productName=o.toString();
                break;
            case 2:
                supplierId=o.toString();
                break;
            case 3:
                categoryId=o.toString();
                break;
            case 4:
                quantityPerUnit=o.toString();
                break;
            case 5:
                unitPrice=o.toString();
                break;
            case 6:
                unitsInStock=o.toString();
                break;
            case 7:
                unitsOnOrder=o.toString();
                break;
            case 8:
                reorderLevel=o.toString();
                break;
            case 9:
                discontinued=o.toString();
                break;
            default:
                break;
        }

    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i){
            case 0:
                propertyInfo.type=PropertyInfo.INTEGER_CLASS;
                propertyInfo.name="id";
                break;
            case 1:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="productName";
                break;
            case 2:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="supplierId";
                break;

            case 3:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="categoryId";
                break;
            case 4:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="quantityPerUnit";
                break;
            case 5:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="unitPrice";
                break;
            case 6:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="unitsInStock";
                break;
            case 7:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="unitsOnOrder";
                break;
            case 8:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="reorderLevel";
                break;
            case 9:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="discontinued";
                break;

            default:
                break;
        }
    }
}
