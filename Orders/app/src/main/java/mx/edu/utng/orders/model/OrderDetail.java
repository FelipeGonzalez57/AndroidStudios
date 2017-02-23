package mx.edu.utng.orders.model;

/**
 * Created by PandithaGD  on 2/7/2017.
 */

public class OrderDetail {

    private String idOrderHeader;
    private int sequence;
    private String idProduct;
    private int quality;
    private float price;

    public OrderDetail(String idOrderHeader, int sequence, String idProduct, int quality, float price) {
        this.idOrderHeader = idOrderHeader;
        this.sequence = sequence;
        this.idProduct = idProduct;
        this.quality = quality;
        this.price = price;
    }

    public OrderDetail() {
        this("",0,"",0,0.0f);
    }

    public String getIdOrderHeader() {
        return idOrderHeader;
    }

    public void setIdOrderHeader(String idOrderHeader) {
        this.idOrderHeader = idOrderHeader;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "idOrderHeader='" + idOrderHeader + '\'' +
                ", sequence=" + sequence +
                ", idProduct='" + idProduct + '\'' +
                ", quality=" + quality +
                ", price=" + price +
                '}';
    }
}
