package com.banking.egbank.shared.common.translations;

import java.util.HashMap;
import java.util.Map;

public class EnMessages {

    protected static final Map<KeysMessages, String> CLIENT_EN_MESS;

    static {
        CLIENT_EN_MESS = new HashMap<>();
        CLIENT_EN_MESS.put(KeysMessages.SUCCESS, "success");
        CLIENT_EN_MESS.put(KeysMessages.FAILED, "failed {value}");
        CLIENT_EN_MESS.put(KeysMessages.CREATEDSUCCESS, "Client has been created successfully id: {id}");
        CLIENT_EN_MESS.put(KeysMessages.UPDATEDSUCCESS, "Client id: {id} has been updated successfully");
        CLIENT_EN_MESS.put(KeysMessages.DELETEDSUCCESS, "Client id: {id} has been deleted successfully");
        CLIENT_EN_MESS.put(KeysMessages.RESTOREDSUCCESS, "Client id: {id} has been restored successfully");
        CLIENT_EN_MESS.put(KeysMessages.CHECKNOTEXISTORRESTORED,
                "Client id: {id} not found in database or maybe already restored");
        CLIENT_EN_MESS.put(KeysMessages.CHECKNOTEXIST, "this client {id} not exist ");
        CLIENT_EN_MESS.put(KeysMessages.ISUNIQUE, "{id} is already exist in database");
        CLIENT_EN_MESS.put(KeysMessages.CHECKPHONEISUNIQUE, "Client phone {id} is already exist in database");
        CLIENT_EN_MESS.put(KeysMessages.CHECKEMAILISUNIQUE, "Client email {id} is already exist in database");
        CLIENT_EN_MESS.put(KeysMessages.PASSWORDCONFIMRATION, "password and confirmation password not match");
        CLIENT_EN_MESS.put(KeysMessages.SIGNINSUCCESS, "Success signin");
        CLIENT_EN_MESS.put(KeysMessages.LOGOUTSUCCESS, "Success logout");
        CLIENT_EN_MESS.put(KeysMessages.WHOAMISUCCESS, "Successfully fetching client data");
        CLIENT_EN_MESS.put(KeysMessages.CHANGEPASSWORDSUCCESS, "Client password changed successfully");
        CLIENT_EN_MESS.put(KeysMessages.ACTIVENEWCLIENTSUCCESS, "Client has been activated successfully");
        CLIENT_EN_MESS.put(KeysMessages.OLDPASSWORDFAILED, "The old password is incorrect");
        CLIENT_EN_MESS.put(KeysMessages.SIGNINFAILED, "Unauthorized");
        CLIENT_EN_MESS.put(KeysMessages.SIGNINFAILEDMESSAGE, "Failed, please check credential information");
        CLIENT_EN_MESS.put(KeysMessages.OTP_RESETPASSWORDSENDSUCCESS,
                "The password set code has been sent to the registered phone number successfully");
        CLIENT_EN_MESS.put(KeysMessages.OTP_RESETPASSWORDSENDFAILED, "Unable to send code, please try again later");
        CLIENT_EN_MESS.put(KeysMessages.OTP_WRONGCODE, "Wrong code");
        CLIENT_EN_MESS.put(KeysMessages.OTP_RESETPASSWORD, "welcome {name} Your account recovery code is: J-{code}");
        CLIENT_EN_MESS.put(KeysMessages.OTP_ACTIVENEWCLIENT,
                "welcome {name} Your account activation code is: J-{code}");
        CLIENT_EN_MESS.put(KeysMessages.ERROR_MSG, "Have ({count}) error(s)");
        CLIENT_EN_MESS.put(KeysMessages.NOT_EMPTY, "{value} cannot be empty");
        CLIENT_EN_MESS.put(KeysMessages.IS_OPTIONAL, "is optional");
        CLIENT_EN_MESS.put(KeysMessages.INVALID_GENDER, "Invalid Gender must be male or female");
        CLIENT_EN_MESS.put(KeysMessages.INVALID_DELIVERY_TYPE, "Invalid delivery type must be SATA OR VENDOR");
        CLIENT_EN_MESS.put(KeysMessages.INVALID_LANGUAGE, "Invalid language must be arabic or english");
        CLIENT_EN_MESS.put(KeysMessages.INVALID_NUMBER, "{property} is invalid number");
        CLIENT_EN_MESS.put(KeysMessages.INVALID_PHONE, "is invalid phone number");
        CLIENT_EN_MESS.put(KeysMessages.INVALID_STRING, "{property} must be a string");
        CLIENT_EN_MESS.put(KeysMessages.INVALID_DECIMAL, "must be a decimal");
        CLIENT_EN_MESS.put(KeysMessages.INVALID_EMAIL, "{property} email is invalid");
        CLIENT_EN_MESS.put(KeysMessages.INVALID_BOOLEAN, "{property} is not a boolean");
        CLIENT_EN_MESS.put(KeysMessages.INVALID_DATE, "{property} is invalid date");
        CLIENT_EN_MESS.put(KeysMessages.INVALID_ISNOTIME, "start_date must be a valid ISO 8601 date string");
        CLIENT_EN_MESS.put(KeysMessages.INVALID_ISNOTARRAY, "{property} must be an array");
        CLIENT_EN_MESS.put(KeysMessages.MIN, "It must contain at least {count} element");
        CLIENT_EN_MESS.put(KeysMessages.MAX, "It must contain maximum {count} element");
        CLIENT_EN_MESS.put(KeysMessages.DELETE_MANYTOMANY, " {count} element has been deleted successfully");
        CLIENT_EN_MESS.put(KeysMessages.CREATE, "The element has been created successfully");
        CLIENT_EN_MESS.put(KeysMessages.UPDATE, "The element has been updated successfully");
        CLIENT_EN_MESS.put(KeysMessages.DELETE, "The element has been deleted successfully");
        CLIENT_EN_MESS.put(KeysMessages.OTP, "The otp created successfully please check your messages");
        CLIENT_EN_MESS.put(KeysMessages.LOGIN, "you have been login successfully");
        CLIENT_EN_MESS.put(KeysMessages.LOGOUT, "you have been logged out successfully");
        CLIENT_EN_MESS.put(KeysMessages.REGISTER,
                "The Account has been registered successfully please check your messages for code");
        CLIENT_EN_MESS.put(KeysMessages.WRONG, "Something went wrong");
        CLIENT_EN_MESS.put(KeysMessages.UNAUTHORIZED, "You are unauthorized");
        CLIENT_EN_MESS.put(KeysMessages.WRONG_PASS_CONFIRM, "Wrong password confirmation");
        CLIENT_EN_MESS.put(KeysMessages.CHANGE_PASSWORD, "The password has been changed successfully");
        CLIENT_EN_MESS.put(KeysMessages.DUPLICATED, "This Element {value} is Repeated");
        CLIENT_EN_MESS.put(KeysMessages.NOTFOUND, "This Element {value} doesn't Exist");
        CLIENT_EN_MESS.put(KeysMessages.INVALID_LOGIC_VALUE, "Is Invalid Logic Value");
        CLIENT_EN_MESS.put(KeysMessages.NOFILE, "There Is No File !");
        CLIENT_EN_MESS.put(KeysMessages.FIND, "data fetched succesfully");
        CLIENT_EN_MESS.put(KeysMessages.MANY_DEVICES, "it's not allowed to use one account for many users");
        CLIENT_EN_MESS.put(KeysMessages.LICENCE_EXPIRED, "licence expired please renew the license");
        CLIENT_EN_MESS.put(KeysMessages.NOT_ACTIVE, "Not Active. the code has been sent please check your messages");
        CLIENT_EN_MESS.put(KeysMessages.MAXUSAGE, "The maximum limit has been reached");
        CLIENT_EN_MESS.put(KeysMessages.WELCOME, "Welcome to SATA app");
        CLIENT_EN_MESS.put(KeysMessages.ALREADY_ACTIVE, "Already Active");
        CLIENT_EN_MESS.put(KeysMessages.INVAILD_FILE, "the value of {property} invalid");
        CLIENT_EN_MESS.put(KeysMessages.IS_ALREADY_DONE, "is already updated and done.");
        CLIENT_EN_MESS.put(KeysMessages.ACCOUNT_EXIST, "Account already exist.");
    }

}
