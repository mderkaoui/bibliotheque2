package validators;

import beans.User;

public final class ValidateLoginField {
	public static String isNull(User user) {
		String message = "";
		if(user.getLogin().isEmpty()) {
			message += "Le champs login est vide";
		}
		if(user.getPassword().isEmpty()) {
			if(!message.isEmpty()) {
				message = "Les champs login et password sont vides";
			}
			else {
				message += "Le champs password est vide";
			}
		}
		
		return message;
	}
}
