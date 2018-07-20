package com.syntrontech.pmo;

public enum ServiceName implements GetUrl{
    Role {
        @Override
        public String geturl() {
            return "http://localhost:8080/aaa/forSystem/sync/role";
        }
    },
    Tenant {
        @Override
        public String geturl() {
            return "http://localhost:8080/aaa/forSystem/sync/tenant";
        }
    },
    Unit {
        @Override
        public String geturl() {
            return "http://localhost:8080/aaa/forSystem/sync/unit";
        }
    },
    User {
        @Override
        public String geturl() {
            return "http://localhost:8080/aaa/forSystem/sync/user";
        }
    }
}

@FunctionalInterface
interface GetUrl{
    String geturl();
}

