package com.yhj.modules.commons.components;

/**
 * @description 返回码标识
 */
public interface CustomConstantInterface {
    //对应状态的 key
    String STATUS_KEY = "status";

    // response json header
    String RESPONSE_CONTENT_TYPE_HEADER = "application/json;charset=utf-8";

    //消息key
    String MSG_KEY = "message";

    // success status
    int SUCCESS_CODE = 2000;

    // error password
    int CREDENTIALS_CODE = 2001;

    // account is locked
    int LOCKED_CODE = 2002;

    // password expired
    int EXPIRED_PASSWORD_CODE = 2003;

    // account expired
    int EXPIRED_ACCOUNT_CODE = 2004;

    // disabled account
    int DISABLE_ACCOUNT_CODE = 2005;

    // token authentication failure
    int TOKEN_AUTH_UNSUCCESS_CODE = 2005;


    // error status
    int ERROR_CODE = 5000;


}
