package com.syntrontech.pmo.sync;

public enum ServiceName implements GetUrl{
    // Auth
//    Role {
//        @Override
//        public String geturl() {
//            return "http://localhost:8080/aaa/forSystem/sync/role";
//        }
//    },
//    Tenant {
//        @Override
//        public String geturl() {
//            return "http://localhost:8080/aaa/forSystem/sync/tenant";
//        }
//    },
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


    // Measurement
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
    },

    // cip
    Device{
        @Override
        public String geturl() {
            return "http://localhost:8083/cip/sync/device";
        }
    },
    Subject{
        @Override
        public String geturl() {
            return "http://localhost:8083/cip/sync/subject";
        }
    },
    UnitMeta{
        @Override
        public String geturl() {
            return "http://localhost:8083/cip/sync/unitMeta";
        }
    }

}

@FunctionalInterface
interface GetUrl{
    String geturl();
}

