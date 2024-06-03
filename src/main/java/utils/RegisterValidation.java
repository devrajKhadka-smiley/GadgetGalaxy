package utils;

public class RegisterValidation {

	public static boolean isTextOnly(String text) {
		return text.matches("[a-zA-Z\\s]+"); // accept white space along with alphabet and make sure to accept the
												// process 1 or multiple time
	}

	public static boolean isNumbersOnly(String number) {
		String text = String.valueOf(number); // as matches only work on String, i.e. doesn't work in int
		return text.matches("\\d+"); // it only take the numerical values up to any digit
	}

	public static boolean isLongOnly(String number) {
		String text = String.valueOf(number); // as matches only work on String, i.e. doesn't work in int
		return text.matches("\\d{10}"); // it only take the numerical values || \\d [0-9]
	}

	public static boolean isAlphanumberic(String text) {
		return text.matches("[a-zA-Z0-9]+");
	}

	public static boolean isEmail(String email) {

		return email.matches("^[\\w-\\.]+@ggclient\\.com$");
	}
}

