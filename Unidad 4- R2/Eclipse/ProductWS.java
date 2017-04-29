package mx.edu.utng.ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductWS {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private PreparedStatement ps;

	public ProductWS() {
		conectar();
	}
	//Crear la conexion
	private void conectar(){
		try {
			Class.forName("org.postgresql.Driver");
			connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/wstest","postgres","12345");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
		e.printStackTrace();
		}
	}
	
	//para crear un insert
	public int addProduct(Product product){
		int result=0;
		if(connection!=null){
			try {
				ps=connection.prepareStatement("INSERT INTO product"
						+ "(product_name, "
						+ "supplier_id, "
						+ "category_id,"
						+ " quantity_per_unit,"
						+ " unit_price,"
						+ " units_in_stock, "
						+ "units_on_order, "
						+ "reorder_level,"
						+ " discontinued) "
						+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?);");
				
			
				ps.setString(1, product.getProductName());
				ps.setString(2, product.getSupplierId());
				ps.setString(3, product.getCategoryId());
				ps.setString(4, product.getQuantityPerUnit());
				ps.setString(5, product.getUnitPrice());
				ps.setString(6, product.getUnitsInStock());
				ps.setString(7, product.getUnitsOnOrder());
				ps.setString(8, product.getReorderLevel());
				ps.setString(9, product.getDiscontinued());
				

				result=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	//para hacer un update
	public int updateProduct(Product product){
		int result=0;
		if(connection!=null){
			try {
				ps=connection.prepareStatement("UPDATE product "
						+ "SET  product_name=?, "
						+ "supplier_id=?, "
						+ "category_id=?,"
						+ " quantity_per_unit=?,"
						+ " unit_price=?, "
						+ "units_in_stock=?,"
						+ " units_on_order=?,"
						+ " reorder_level=?,"
						+ " discontinued=?"
						+ ""
						+ " WHERE id=?;");
		
				
				ps.setString(1, product.getProductName());
				ps.setString(2, product.getSupplierId());
				ps.setString(3, product.getCategoryId());
				ps.setString(4, product.getQuantityPerUnit());
				ps.setString(5, product.getUnitPrice());
				ps.setString(6, product.getUnitsInStock());
				ps.setString(7, product.getUnitsOnOrder());
				ps.setString(8, product.getReorderLevel());
				ps.setString(9, product.getDiscontinued());
				ps.setInt(10, product.getId());
				
				
				result=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//para borrar
	public int removePrdocut(int id){
		int result=0;
		if(connection!=null){
			try {
				ps=connection.prepareStatement("DELETE FROM product WHERE id= ?;");
				ps.setInt(1, id);
				
				result=ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//lista de cite
	public Product[] getProducts(){
		Product[] result = null;
		List <Product> products=new ArrayList<Product>();
		if(connection!=null){
			try {
				statement=connection.createStatement();
				resultSet=statement.executeQuery("SELECT * FROM product;");
				while(resultSet.next()){
					Product product=new Product(
							resultSet.getInt("id"),
							resultSet.getString("product_name"),
							resultSet.getString("supplier_id"),
							resultSet.getString("category_id"),
							resultSet.getString("quantity_per_unit"),
							resultSet.getString("unit_price"),
							resultSet.getString("units_in_stock"),
							resultSet.getString("units_on_order"),
							resultSet.getString("reorder_level"),
							resultSet.getString("discontinued"));
							
						products.add(product);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		result = products.toArray(new Product[products.size()]);
		return result;
	}
	//
	public Product getProductbyId(int id){
		Product product=null;
		if(connection!=null){
			try {
				ps=connection.prepareStatement("SELECT * FROM product WHERE id = ?;");
				ps.setInt(1, id);
				resultSet=ps.executeQuery();
				while(resultSet.next()){
					product=new Product(
							resultSet.getInt("id"),
							resultSet.getString("product_name"),
							resultSet.getString("supplier_id"),
							resultSet.getString("category_id"),
							resultSet.getString("quantity_per_unit"),
							resultSet.getString("unit_price"),
							resultSet.getString("units_in_stock"),
							resultSet.getString("units_on_order"),
							resultSet.getString("reorder_level"),
							resultSet.getString("discontinued"));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return product;
	}
}//End clas
