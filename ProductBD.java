
public class ProductBD extends Product{
	
	static JDBConnection jdbc = JDBConnection.getJDBC();
	
	ProductBD(String pdtName)
	{
		
	}

	private void createProduct(String nickname ,String pdtName)
	{
		jdbc.getProduct(nickname, pdtName);
	}
}
