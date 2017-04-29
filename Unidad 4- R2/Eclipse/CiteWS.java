package mx.edu.utng.ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CiteWS {
	
private Connection connection;
private Statement statement;
private ResultSet resultSet;
private PreparedStatement ps;

public CiteWS() {
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
public int addCite(Cite cite){
	int result=0;
	if(connection!=null){
		try {
			ps=connection.prepareStatement("INSERT INTO cite"
					+ " (usuario,"
					+ " room,"
					+ "recurrence_parent,"
					+ "subject,"
					+ "inicio,"
					+ " finall,"
					+ "recurrence_rule,"
					+ "annotations,"
					+ " descripcion, "
					+ "reminder) "
					+ " VALUES (?,?,?,?,?,?,?,?,?,?);");
			
			ps.setString(1, cite.getUsuario());
			ps.setString(2, cite.getRoom());
			ps.setString(3, cite.getRecurrenceParent());
			ps.setString(4, cite.getSubject());
			ps.setString(5, cite.getInicio());
			ps.setString(6, cite.getFinall());
			ps.setString(7, cite.getRecurrenceRule());
			ps.setString(8, cite.getAnnotations());
			ps.setString(9, cite.getDescripcion());
			ps.setString(10, cite.getReminder());

			result=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return result;
}
//para hacer un update
public int updateCite(Cite cite){
	int result=0;
	if(connection!=null){
		try {
			ps=connection.prepareStatement("UPDATE cite  SET "
					+ "usuario= ?,"
					+ "room= ?,"
					+ "recurrence_parent= ?,"
					+ "subject= ?,"
					+ "inicio= ?,"
					+ "finall= ?,"
					+ "recurrence_rule= ?,"
					+ "annotations= ?,"
					+ "descripcion= ?,"
					+ "reminder= ? "
					
					+ "WHERE id= ?;");
			
				
			ps.setString(1, cite.getUsuario());
			ps.setString(2, cite.getRoom());
			ps.setString(3, cite.getRecurrenceParent());
			ps.setString(4, cite.getSubject());
			ps.setString(5, cite.getInicio());
			ps.setString(6, cite.getFinall());
			ps.setString(7, cite.getRecurrenceParent());
			ps.setString(8, cite.getAnnotations());
			ps.setString(9, cite.getDescripcion());
			ps.setString(10, cite.getReminder());
			ps.setInt(11, cite.getId());
			
			
			result=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return result;
}
//para borrar
public int removeCite(int id){
	int result=0;
	if(connection!=null){
		try {
			ps=connection.prepareStatement("DELETE FROM cite WHERE id= ?;");
			ps.setInt(1, id);
			result=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return result;
}
//lista de cite
public Cite[] getCites(){
	Cite[] result = null;
	List <Cite> cites=new ArrayList<Cite>();
	if(connection!=null){
		try {
			statement=connection.createStatement();
			resultSet=statement.executeQuery("SELECT * FROM cite;");
			while(resultSet.next()){
				Cite cite=new Cite(
						resultSet.getInt("id"),
						resultSet.getString("usuario"),
						resultSet.getString("room"),
						resultSet.getString("recurrence_parent"),
						resultSet.getString("subject"),
						resultSet.getString("inicio"),
						resultSet.getString("finall"),
						resultSet.getString("recurrence_rule"),
						resultSet.getString("annotations"),
						resultSet.getString("descripcion"),
						resultSet.getString("reminder"));
					cites.add(cite);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	result = cites.toArray(new Cite[cites.size()]);
	return result;
}
//
public Cite getCitebyId(int id){
	Cite cite=null;
	if(connection!=null){
		try {
			ps=connection.prepareStatement("SELECT * FROM cite WHERE id = ?;");
			ps.setInt(1, id);
			resultSet=ps.executeQuery();
			while(resultSet.next()){
				cite=new Cite(
						resultSet.getInt("id"),
						resultSet.getString("usuario"),
						resultSet.getString("room"),
						resultSet.getString("recurrence_parent"),
						resultSet.getString("subject"),
						resultSet.getString("inicio"),
						resultSet.getString("finall"),
						resultSet.getString("recurrence_rule"),
						resultSet.getString("annotations"),
						resultSet.getString("descripcion"),
						resultSet.getString("reminder"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return cite;
}


}
