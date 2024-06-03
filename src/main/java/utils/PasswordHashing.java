package utils;

import it.cosenonjaviste.crypto.BCrypt;

public class PasswordHashing {
	public static String passwordHash(String password)
	{
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public static boolean checkPassword(String password, String hashPassword)
	{
		return BCrypt.checkpw(password, hashPassword);
	}

}
