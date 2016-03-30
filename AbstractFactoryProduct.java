import java.util.ArrayList;

//Abstract Class of Product Factory

public abstract class AbstractFactoryProduct 
{
	abstract public ArrayList<Product> createProductList(String nickname);
	abstract public Product getProduct(String pdt_product, String nickname) throws ObjectNotInTheDatabaseException;
	abstract public void deleteProduct(String pdt_product, String nickname);
	abstract public void addProduct(String nickname, String pdt_name, Integer pdt_quantity, Integer pdt_price,
			String pdt_briefDesc, String pdt_longDesc);
}
