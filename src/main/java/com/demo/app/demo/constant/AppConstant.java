package com.demo.app.demo.constant;

public class AppConstant {
    private AppConstant() {
    }

    private static final String AUTH_BASE = "/api/authenticate/**";
    private static final String USER_BASE = "/api/user/**";
    private static final String ADMIN_BASE = "/api/admin/**";
    //add your version here
    public static final String[] SWAGGER_UI = {"/v3/api-docs/**",   "/swagger-ui.html/**", "/swagger-resources/**", "/swagger-ui/**"};
    public static final String[] AUTH_BASE_URLS = {"/v1" + AUTH_BASE, "/v2" + AUTH_BASE};
    public static final String[] USER_BASE_URLS = {"/v1" + USER_BASE, "/v2" + USER_BASE};
    public static final String[] ADMIN_BASE_URLS = {"/v1" + ADMIN_BASE, "/v2" + ADMIN_BASE};


}
