import java.util.ArrayList;

public class ProductBD extends Product{
	
	static JDBConnection jdbc = JDBConnection.getJDBC();
	
	ProductBD(String pdtName)
	{
		this.pdt_name = null;
		this.price = null;
		this.id_category =null;
		this.quantity = null;
		this.user_nickname = null;
	}
	
	public static void addProduct(String nickname, String pdt_name, Integer pdt_quantity, Float pdt_price, String pdt_briefDesc, String pdt_longDesc)
	{
		jdbc.createProduct(nickname,pdt_name,pdt_quantity,pdt_price,pdt_briefDesc,pdt_longDesc);
	}

	private void createProduct(String nickname ,String pdtName)
	{
		try {
			jdbc.getProduct(nickname, pdtName);
		} catch (UserNotInTheDatabaseException e) {
			
			e.printStackTrace();
		}
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
