import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class CRUDProductTests {

	static FacadeUser FU;

	static String nickname;
	static String pdt_nameExist;
	static String pdt_nameNew;
	static String pdt_briefDesc;
	static String pdt_longDesc;
	static String stringPdt_quantity;
	static String stringPdt_price;

	static int pdt_quantity;
	static int pdt_price;

	//Loading the Data
	
	@BeforeClass
	static public void setUpTests() {
		try 
		{
			JDBConnectionOpen.getJDBCO();
			FU = FacadeUser.getFU();

			nickname = "Polytech";
			pdt_nameExist = "planche";
			pdt_nameNew = "nouveauProduitTest";
			pdt_briefDesc = "briefDesc";
			pdt_longDesc = "ablabla";
			stringPdt_quantity = "54";
			stringPdt_price = "23";

			pdt_quantity = Integer.parseInt(stringPdt_quantity);
			pdt_price = Integer.parseInt(stringPdt_price);

		}
		catch (Exception e){};
	}	

	//ADD TESTS

	//Test one by one the fields, to see if it raises the right Exception
	//Product name
	@Test (expected = EmptyFieldsException.class)
	public void testIsEmptyFieldName() throws EmptyFieldsException, NotExpectedValueException
	{
		FU.verifyAddProductFields("", pdt_briefDesc, pdt_longDesc, stringPdt_quantity, stringPdt_price);
	}

	//Brief Description
	@Test (expected = EmptyFieldsException.class)
	public void testIsEmptyFieldBriefDesc() throws EmptyFieldsException, NotExpectedValueException
	{
		FU.verifyAddProductFields(pdt_nameExist, "", pdt_longDesc, stringPdt_quantity, stringPdt_price);
	}

	//Long Description
	@Test (expected = EmptyFieldsException.class)
	public void testIsEmptyFieldLongDesc() throws EmptyFieldsException, NotExpectedValueException
	{
		FU.verifyAddProductFields(pdt_nameExist, pdt_briefDesc, "", stringPdt_quantity, stringPdt_price);
	}

	//Quantity
	@Test (expected = EmptyFieldsException.class)
	public void testIsEmptyFieldQuantity() throws EmptyFieldsException, NotExpectedValueException
	{
		FU.verifyAddProductFields(pdt_nameExist, pdt_briefDesc, pdt_longDesc, "", stringPdt_price);
	}

	//Price
	@Test (expected = EmptyFieldsException.class)
	public void testIsEmptyFieldPrice() throws EmptyFieldsException, NotExpectedValueException
	{
		FU.verifyAddProductFields(pdt_nameExist, pdt_briefDesc, pdt_longDesc, stringPdt_quantity, "");
	}


	//Test one by one the fields that are supposed to get an Integer, to see if it raises the right Exception
	//Quantity
	@Test (expected = NotExpectedValueException.class)
	public void testNotNumberFieldQuantity() throws EmptyFieldsException, NotExpectedValueException
	{
		FU.verifyAddProductFields(pdt_nameExist, pdt_briefDesc, pdt_longDesc, "NotAnInt", stringPdt_price);
	}

	//Price
	@Test (expected = NotExpectedValueException.class)
	public void testNotNumberFieldPrice() throws EmptyFieldsException, NotExpectedValueException
	{
		FU.verifyAddProductFields(pdt_nameExist, pdt_briefDesc, pdt_longDesc, stringPdt_quantity, "NotAnInt");
	}


	// Test the if the product are already or not in the database, to see if it raises the right Exception
	//Already Exists
	@Test (expected = ObjectAlreadyExistsException.class)
	public void testProductAlreadyExists() throws ObjectAlreadyExistsException, ObjectNotInTheDatabaseException
	{
		FU.verifyAlreadyExists(nickname,pdt_nameExist);
	}

	//Does Not Exist
	@Test (expected = ObjectNotInTheDatabaseException.class)
	public void testProductDoesNotExist() throws ObjectAlreadyExistsException, ObjectNotInTheDatabaseException
	{
		FU.verifyAlreadyExists(nickname,pdt_nameNew);
	}

	
	//Test if the product is correctly added to the database, to see if it raises the right Exception
	//Add The Product
	@Test
	public void testAddProduct()
	{
		FU.addProduct(nickname, pdt_nameNew, pdt_quantity, pdt_price, pdt_briefDesc, pdt_longDesc);
	}

	
	
	//DELETE TESTS

	//Delete the Product
	@Test
	public void testDeleteProduct()
	{
		try {
			FU.PH.getPH();
			FU.PH.deleteProduct(pdt_nameNew, nickname);
		} catch (ObjectNotInTheDatabaseException e) {
			// TODO Auto-generated catch block
			System.out.println("raté");
		} catch (ObjectDeletedException e) {
			// TODO Auto-generated catch block
			System.out.println("gg");
		}
	}


}
