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
    },


    BodyInfo{
        @Override
        public String geturl() {
            return "http://localhost:8084/measurement/sync/BodyInfo";
        }
    },
    BloodPressureHeartBeat{
        @Override
        public String geturl() {
            return "http://localhost:8084/measurement/sync/BloodPressureHeartBeat";
        }
    },
    BloodGlucose{
        @Override
        public String geturl() {
            return "http://localhost:8084/measurement/sync/BloodGlucose";
        }
    },
    Biochemistry{
        @Override
        public String geturl() {
            return "http://localhost:8084/measurement/sync/Biochemistry";
        }
    },
    AbnormalBloodPressure{
        @Override
        public String geturl() {
            return "http://localhost:8084/measurement/sync/AbnormalBloodPressure";
        }
    },
    AbnormalBloodPressureLog{
        @Override
        public String geturl() {
            return "http://localhost:8084/measurement/sync/AbnormalBloodPressureLog";
        }
    }

}

@FunctionalInterface
interface GetUrl{
    String geturl();
}

