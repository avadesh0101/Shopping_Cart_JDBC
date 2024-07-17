package product.jdbc;

public class Product {
	
	int productId ;
	String productName ;
	String productType ;
	double productCost ;
	
	Product(int productId , String productName , String productType , double productCost)
	{
		this.productId = productId ;
		this.productName = productName ;
		this.productType = productType ;
		this.productCost = productCost ;
	}
	
	
	Product(int productId , String productName)
	{
		this.productId = productId ;
		this.productName = productName ;
	}
	
	Product(String productType , int productId)
	{
		this.productId = productId ;
		this.productType = productType ;
	}
	
	
	Product(int productId , double productCost )
	{
		this.productId = productId ;
		this.productCost = productCost ;
	}
	
	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productType=" + productType
				+ ", productCost=" + productCost + "]";
	}

}
