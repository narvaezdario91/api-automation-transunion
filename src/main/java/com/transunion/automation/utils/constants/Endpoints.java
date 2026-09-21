package com.transunion.automation.utils.constants;

/**
 * Centralized API endpoints constants.
 */
public final class Endpoints {

    private Endpoints() {
        // Utility constant class
    }

    public static final String PRODUCTS_LIST = "/api/productsList";
    public static final String BRANDS_LIST = "/api/brandsList";
    public static final String SEARCH_PRODUCT = "/api/searchProduct";
    public static final String VERIFY_LOGIN = "/api/verifyLogin";
    public static final String CREATE_ACCOUNT = "/api/createAccount";
    public static final String DELETE_ACCOUNT = "/api/deleteAccount";
    public static final String UPDATE_ACCOUNT = "/api/updateAccount";
    public static final String GET_USER_DETAIL_BY_EMAIL = "/api/getUserDetailByEmail";
}
