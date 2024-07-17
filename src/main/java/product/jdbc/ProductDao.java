package product.jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductDao {

	static String URL = "jdbc:postgresql://localhost:5432/eca_jdbc?user=postgres&password=root";

	public void saveProduct(Product product) {
		int id = product.productId;
		String pName = product.productName;
		String pType = product.productType;
		double cost = product.productCost;

		try {
			Class.forName("org.postgresql.Driver");

			Connection con = DriverManager.getConnection(URL);

			String sql = "INSERT INTO product VALUES(? , ? , ? , ?)";

			PreparedStatement stm = con.prepareStatement(sql);

			stm.setInt(1, id);
			stm.setString(2, pName);
			stm.setString(3, pType);
			stm.setDouble(4, cost);

			stm.execute();

			System.out.println("Data inserted sucessfully");

			con.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	public void updateProduct(Product product)
	{
		int id = product.productId ;
		String pName = product.productName ;
		String pType = product.productType ;
		double cost = product.productCost ;
		
		try {
			Class.forName("org.postgresql.Driver") ;
			
			Connection con = DriverManager.getConnection(URL) ;
			
			String sql = "UPDATE product SET productCost = ? WHERE productId = ? " ;
			
			PreparedStatement stm = con.prepareStatement(sql) ;		
			
			stm.setDouble(1, cost);
			stm.setInt(2, id);
			
			int rowsAffected = stm.executeUpdate();

			if (rowsAffected > 0) {
			    System.out.println("Product updated successfully");
			} else {
			    System.out.println("No product found with the given ID");
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	public ArrayList<Product> displayAll()
	{
		ArrayList<Product> al = new ArrayList<>() ;
		
		try {
			Class.forName("org.postgresql.Driver") ;
			
			Connection con = DriverManager.getConnection(URL) ;
			
			String sql = "SELECT * FROM product" ;
			
			Statement stm = con.createStatement() ;
			
			ResultSet rs = stm.executeQuery(sql) ;
			
			while(rs.next())
			{
				
				al.add(new Product(rs.getInt(1) , rs.getString(2) , rs.getString(3) , rs.getDouble(4) ));
				
								
			}
			
		   	
			return al ;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;
		
		
	}
	
	
	public ArrayList<Product> findById(int id)
	{
		ArrayList<Product> al = new ArrayList<>() ;
		
		try {
			Class.forName("org.postgresql.Driver") ;
			
			Connection con = DriverManager.getConnection(URL);
			
			String sql = "SELECT * FROM product where productid = "+id+" " ;
			
			Statement stm = con.createStatement() ;
			
			
			ResultSet rs = stm.executeQuery(sql);
			
			int count = 0 ;
			
				
			while(rs.next())
			{
				count++ ;
				al.add(new Product(rs.getInt(1) , rs.getString(2) , rs.getString(3) , rs.getDouble(4)));
			}
			
			
			if(count==0)
			{
				System.out.println("Id " +id+ " not found") ;
			}
			
			
			return al ;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;
	}
	
	
	public void deleteProduct(int pid)
	{
		try {
			Class.forName("org.postgresql.Driver") ;
			
			Connection con = DriverManager.getConnection(URL) ;
			
			String sql = "DELETE FROM product where productid = "+pid+" " ;
			
			Statement stm = con.createStatement() ;
			
			int res = stm.executeUpdate(sql) ;
			
			if(res>0)
			{
				System.out.println("Deleted Sucessfully") ;
			}
			else
			{
				System.out.println("Id not found") ;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	

	public static void main(String[] args) {
		

		Scanner s = new Scanner(System.in);

		ProductDao pd = new ProductDao();

		System.out.println("------------------Welcome---------------------------");

		System.out.println("Press 1 to save product");
		System.out.println("Press 2 to update product");
		System.out.println("Press 3 to display all product");
		System.out.println("Press 4 to findProduct product");
		System.out.println("Press 5 to delete product");

		int ip = s.nextInt();

		switch (ip) {
		case 1: {

			System.out.print("Enter the Product Id : ");
			int id = s.nextInt();

			System.out.print("Enter the product name : ");
			String pName = s.next();

			System.out.print("Enter the product type : ");
			String pType = s.next();

			System.out.print("Enter the product cost : ");
			double cost = s.nextDouble();
			
			pd.saveProduct(new Product(id , pName , pType , cost));

		}
		
		break ;
		
		case 2 :{
			
			System.out.println("Enter product id to upadte") ;
			
			int pid = s.nextInt() ;
			
			System.out.println("Enter product name , type , cost") ;
			
			double cost = s.nextDouble() ;
			
			pd.updateProduct(new Product(pid , cost));
			
		}
		
		break ;
		
		case 3 :{
			
			pd.displayAll().forEach(System.out::println);
			
		}
		
		break ;
		
		case 4:{
			
			System.out.println("Enter the id :") ;
			
			int id = s.nextInt();
			
			pd.findById(id).forEach(System.out::println);
			
		}
		
		break ;
		
		case 5 :{
			
			System.out.println("Enter the Id you want to delete") ;
			
			int id = s.nextInt() ;
			
			pd.deleteProduct(id) ;
		}
		
		break ;
		
		default : System.out.println("Please select correct option") ;
		
		}

	}

}
