import java.util.ArrayList;

public class ProductDB extends Product{
	
	static JDBConnectionProduct jdbc = JDBConnectionProduct.getJDBCP();
	
	ProductDB(String pdtName)
	{
		this.pdt_name = null;
		this.price = null;
		this.id_category =null;
		this.quantity = null;
		this.user_nickname = null;
	}
	
	ProductDB(String nickname, String pdtName, Integer pdtQuantity, Integer pdtPrice, String briefDesc, String longDesc)
	{
		this.pdt_name = pdtName;
		this.price = pdtPrice;
		this.id_category =null;
		this.quantity = pdtQuantity;
		this.user_nickname = nickname;
		this.briefDesc = briefDesc;
		this.longDesc = longDesc;
	}
	
	public static void addProduct(String nickname, String pdt_name, Integer pdt_quantity, Integer pdt_price, String pdt_briefDesc, String pdt_longDesc)
	{
		jdbc.createProduct(nickname,pdt_name,pdt_quantity,pdt_price,pdt_briefDesc,pdt_longDesc);
	}

	public static Product getProduct(String nickname ,String pdtName)
	{
		Product product = null;
		try {
			product = jdbc.getProduct(nickname, pdtName);
		} catch (ObjectNotInTheDatabaseException e) {
			
			e.printStackTrace();
		}
		
		return product;
	}
	
	static public ArrayList<Product> createProductList(String nickname)
	{
		return jdbc.getAllProducts(nickname);
	}

	public static void deleteProduct(String pdt_product, String nickname) 
	{
		
		jdbc.deleteProduct(pdt_product, nickname);
		
	}
}
