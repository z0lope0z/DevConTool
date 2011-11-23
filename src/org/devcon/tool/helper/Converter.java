package org.devcon.tool.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.devcon.tool.exception.EmailFormatException;

import com.google.appengine.api.datastore.Email;

public class Converter {
    public static final Email stringToEmail(String emailString)
            throws EmailFormatException {
        // Set the email pattern string
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

        // Match the given string with the pattern
        Matcher m = p.matcher(emailString);

        // check whether match is found
        boolean matchFound = m.matches();

        if (matchFound) {
            return new Email(emailString);
        } else {
            if ((emailString != "") && (emailString != null)) {
                throw new EmailFormatException();
            } else {
                return new Email(emailString);
            }
        }
    }
}
