package server;

public interface Protocol {
    public static final String EXIT_STRING = "exit";
    public static final String LOGIN = "login";
    public static final String REGISTRATION = "register";
    public static final String CONSULT = "consult";
    public static final String SYNCHRONIZATION = "sync";
    public static final String ACCOUNT_NOT_FOUND = "account-not-found";
    public static final String ACCOUNT_ALREADY_EXIST = "account-already-exist";
    public static final String LOGIN_SUCCESSFUL = "login-successful";
    public static final String FORBIDDEN_REQUEST = "forbbiden-request";
    public static final String REGISTRATION_SUCESSFUL = "registration-successful";
}
