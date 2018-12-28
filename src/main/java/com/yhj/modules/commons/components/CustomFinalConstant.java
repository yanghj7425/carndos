package com.yhj.modules.commons.components;

/**
 * @description 返回码标识
 */
public final class CustomFinalConstant {


    //status key
    public static final String STATUS_KEY = "status";

    // response json header
    public static final String RESPONSE_CONTENT_TYPE_HEADER = "application/json;charset=utf-8";

    //message key
    public static final String MSG_KEY = "message";


    //
    public static final String DATA_KEY = "info";


    // success status
    public static final int SUCCESS_CODE = 2000;

    // error password
    public static final int CREDENTIALS_CODE = 2001;

    // account is locked
    public static final int LOCKED_CODE = 2002;

    // password expired
    public static final int EXPIRED_PASSWORD_CODE = 2003;

    // account expired
    public static final int EXPIRED_ACCOUNT_CODE = 2004;

    // disabled account
    public static final int DISABLE_ACCOUNT_CODE = 2005;

    // token authentication failure
    public static final int TOKEN_AUTH_FAIL_CODE = 2006;

    // this request cant access this resource
    public static final int NONE_PRIVILEGE = 2007;

    // error status
    public static final int ERROR_CODE = 5000;

    private CustomFinalConstant() {

    }

}
