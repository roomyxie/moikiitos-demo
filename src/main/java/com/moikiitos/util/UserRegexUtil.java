package com.moikiitos.util;

import java.util.regex.Pattern;

/**
 *function description
 * @author xiekuan
 * @Description  regex
 * @date 2/25/24
*/
public class UserRegexUtil {

    /**
     * regex：check username
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,20}$";

    /**
     * regex：check password
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";

    /**
     * regex：check phone number
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * regex：check email
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * regex：check chinese words
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * regex：check CARD ID
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

    /**
     * regex：check URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * regex：check IP
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    /**
     * regex：check user name
     *
     * @param username
     * @return pass: true，not pass: false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    /**
     * check password
     *
     * @param password
     * @return pass: true，not pass: false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     * check phone
     *
     * @param mobile
     * @return pass: true，not pass: false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * check email
     *
     * @param email
     * @return pass: true，not pass: false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * check chinese
     *
     * @param chinese
     * @return pass: true，not pass: false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * check CRAD ID
     *
     * @param idCard
     * @return pass: true，not pass: false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * check URL
     *
     * @param url
     * @return pass: true，not pass: false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }

    /**
     * check IP address
     *
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }


}
