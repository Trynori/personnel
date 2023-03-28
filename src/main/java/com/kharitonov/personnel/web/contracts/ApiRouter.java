package com.kharitonov.personnel.web.contracts;

public class ApiRouter {

    public static class CandidateRouter {
        public static final String BASE_URL = "api/v1/candidates";
        public static final String FETCH_BY_ID = "/{id}";
        public static final String DELETE_BY_ID = "/{id}";
    }

    public static class ResumeController {
        public static final String BASE_URL = "api/v1/resume";
    }
}
