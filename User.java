
public abstract class User {

	String nicknameUser;
	String mdpUser;
	String firstNameUser;
	String lastNameUser;
	String cityUser;
	String streetUser;
	String postalCodeUser;
	String streetNumberUser;
	String emailUser;
	
	User()
	{
		
	}
	
	
	User(String nickname)
	{
		this.nicknameUser = nickname;
	}
	
	User(String nickname, String password)
	{
		this.nicknameUser = nickname;
		this.mdpUser = password;
	}
	
	User(String nickname, String password, String firstName, String lastName, String city, String street, String postalCode, String streetNumber, String email)
	{
		this.nicknameUser = nickname;
		this.mdpUser = password;
		this.firstNameUser = firstName;
		this.lastNameUser = lastName;
		this.cityUser = city;
		this.streetUser = street;
		this.postalCodeUser = postalCode;
		this.streetNumberUser = streetNumber;
		this.emailUser = email;
	}

	public String getUserNickname()
	{
//		if (this.nicknameUser == null)
//			{
//				return null;
//			}	
		return this.nicknameUser;
	}
	
	public String getUserPassword()
	{
		return this.mdpUser;
	}
	
	public String getUserFirstName()
	{
		return this.firstNameUser;
	}
	
	public String getUserLastName()
	{
		return this.lastNameUser;
	}
	public String getUserCity()
	{
		return this.cityUser;
	}
	public String getUserStreet()
	{
		return this.streetUser;
	}
	public String getUserPostalCode()
	{
		return this.postalCodeUser;
	}
	public String getUserStreetNumber()
	{
		return this.streetNumberUser;
	}
	public String getUserEMail()
	{
		return this.emailUser;
	}
}
