public class FacadeUser {
	
	GestionnaireUser GU = GestionnaireUser.getGU();
	ProductsHandler PH = ProductsHandler.getPH();
	CategoryHandler CH = CategoryHandler.getCH();

	GestionnaireGoal GG = GestionnaireGoal.getGG();

	
	//Construct Singleton
	private FacadeUser()
	{}
	
	//Init Singleton
	private static FacadeUser singleton;
	
	//Access Singleton
	public static FacadeUser getFU()
	{
		if (singleton==null)
		{
			singleton = new FacadeUser();
		}
		return singleton;
	}
	
	
	//Méthodes User
	public Integer login(String nickname, String mdp) throws ObjectNotInTheDatabaseException, WrongPasswordException
	{
		int bool = GU.login(nickname, mdp);
		System.out.println(bool);
		return bool;
	}
	
	public User getCurrentUser()
	{
		return GU.getCurrentUser();
	}
	
	public void disconnectUser()
	{
		GU.deleteCurrentUser();
	}
	
	
	//Méthodes Products
	public String[][] getStringProductList()
	{
		return PH.getStringProductList(GU.currentUser.nicknameUser);
	}
	
	public void deleteProduct(String pdt_product) throws ObjectNotInTheDatabaseException, ObjectDeletedException{
		PH.deleteProduct(pdt_product, GU.getCurrentUser().nicknameUser);
	}
	
	public void modifyCurrentProduct(String nom_produit, String nickname)
	{
		PH.getProduct(nom_produit, nickname);
	}
	
	public Product getCurrentProduct()
	{
		return PH.product_selected;
	}
	
	// Méthodes Admin
	public void addUser(String nick, String pass, String email) throws ObjectAlreadyExistsException, ObjectCreatedException {
		GU.addUser(nick, pass, email);
	}
	
	public void modifyUserData(String nick, String pass, String email, String firstname, String lastname,
			String city,String street,String postalcode,String streetnumber) 
	throws ObjectNotInTheDatabaseException, ObjectModifiedException{
		
		GU.modifyUser(nick, pass, email, firstname, lastname, city, street, postalcode, streetnumber);
	}
	
	public void modifyUser(String nick, String pass, String email, String firstname, String lastname,
			String city,String street,String postalcode,String streetnumber) 
	throws ObjectNotInTheDatabaseException, ObjectModifiedException{
		
		GU.modifyUser(nick, pass, email, firstname, lastname, city, street, postalcode, streetnumber);
	}
	
	public void deleteUser(String nick) throws ObjectNotInTheDatabaseException, ObjectDeletedException{
		GU.deleteUser(nick);
	}
	

	//methods activity category
		public void suggestActivityCategory(String title, String description){
			CH.suggestActivityCategory(title,description);
			
		}

	public void addGoal(String goal_title, String goal_description, String nick)throws ObjectCreatedException, ObjectAlreadyExistsException {
		// TODO Auto-generated method stub
		GG.addGoal(goal_title, goal_description, nick);
	}


	public User getUserData(String nicknameUser) {
		return GU.getUserData(nicknameUser);
	}
	
	public boolean isAdmin(String nick) {
		return GU.isAdmin(nick);
	}

	public boolean isSimpleUser(String nick) {
		return GU.isSimpleUser(nick);
	}
	
	public boolean isSeller(String nick) {
		return GU.isSeller(nick);
	}
	
	public void chooseUserRoleSeller(String nick) {
		GU.chooseUserRoleSeller(nick);
	}
	
	public void chooseUserRoleSimpleUser(String nick) {
		GU.chooseUserRoleSimpleUser(nick);
	}
	
	public void chooseUserRoleAdmin(String nick) {
		GU.chooseUserRoleAdmin(nick);
	}


	public void addProduct(String nickname, String pdt_name, Integer pdt_quantity, Integer pdt_price,
			String pdt_briefDesc, String pdt_longDesc) 
	{
		PH.addProduct(nickname, pdt_name, pdt_quantity, pdt_price, pdt_briefDesc, pdt_longDesc);
	}


	public String[][] getStringGoalList() {
		// TODO Auto-generated method stub
		
		return GG.getStringGoalList(GU.currentUser.nicknameUser);
	}
	
	public String[][] getStringActivityCategoryList(){
		return CH.getStringCategoryList() ;
	}


	public String[][] getStringActivityCategorySuggestionList() {
		return CH.getStringCategorySuggestionList();
	}


	public void deleteGoal(String goal_name) throws ObjectNotInTheDatabaseException, ObjectDeletedException {

		GG.deleteGoal(goal_name, GU.getCurrentUser().nicknameUser);
		
	}
	


	


}
