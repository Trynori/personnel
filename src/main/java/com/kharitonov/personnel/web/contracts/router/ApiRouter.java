package com.kharitonov.personnel.web.contracts.router;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiRouter {

    public static class CandidateRouter {
        public static final String BASE_URL = "api/v1/candidate";
        public static final String FETCH_BY_ID = "/{id}";
        public static final String DELETE_BY_ID = "/{id}";
    }

    public static class ResumeController {
        public static final String BASE_URL = "api/v1/resume";
        public static final String FETCH_BY_ID = "/{id}";
        public static final String DOWNLOAD_BY_ID = "/download/{id}";
    }

    public static class AuthenticationController {
        public static final String BASE_URL = "/api/v1/auth";
        public static final String REGISTER = "/register";
        public static final String AUTHENTICATE = "/authenticate";
        public static final String LOGOUT = "/logout";
        public static final String REFRESH_TOKEN = "/refresh-token";
    }

    public static class QueryController {
        public static final String BASE_URL = "api/v1/query";
        public static final String FETCH_ALL_FOR_USER = "/{id}";
        public static final String DELETE_BY_ID = "/{id}";
    }
}
