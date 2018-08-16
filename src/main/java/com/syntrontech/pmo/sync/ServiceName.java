package com.syntrontech.pmo.sync;

public enum ServiceName implements GetUrl{
    // Auth
    Unit {
        @Override
        public String geturl() {
            return "http://localhost:8000/aaa/forSystem/sync/unit";
        }
//        public String geturl() {
//            return "http://localhost:8080/aaa/forSystem/sync/unit";
//        }
    },
    User {
        @Override
        public String geturl() {
            return "http://localhost:8000/aaa/forSystem/sync/user";
        }
//        public String geturl() {
//            return "http://localhost:8080/aaa/forSystem/sync/user";
//        }
    },
    // cip
    Device{
        @Override
        public String geturl() {
            return "http://localhost:8000/cip/sync/device";
        }
//        public String geturl() {
//            return "http://localhost:8083/cip/sync/device";
//        }
    },
    Subject{
        @Override
        public String geturl() {
            return "http://localhost:8000/cip/sync/subject";
        }
    },
    UnitMeta{
        @Override
        public String geturl() {
            return "http://localhost:8000/cip/sync/unitMeta";
        }
    },
    EmergencyContact{
        @Override
        public String geturl() {
            return "http://localhost:8000/cip/sync/emergencyContact";
        }
    },

    Questionnaire{
        @Override
        public String geturl() {
            return "http://localhost:8000/questionnair/sync/questionnaireReply";
        }

    },
    
 // Measurement
    BodyInfo{
        @Override
        public String geturl() {
            return "http://localhost:8000/measurement/sync/BodyInfo";
        }
//        public String geturl() {
//            return "http://localhost:8084/measurement/sync/BodyInfo";
//        }
    },
    BloodGlucose{
        @Override
        public String geturl() {
            return "http://localhost:8000/measurement/sync/BloodGlucose";
        }
    },
    Biochemistry{
        @Override
        public String geturl() {
            return "http://localhost:8000/measurement/sync/Biochemistry";
        }
    },
    AbnormalBloodPressure{
        @Override
        public String geturl() {
            return "http://localhost:8000/measurement/sync/AbnormalBloodPressure";
        }
    },
    AbnormalBloodPressureLog{
        @Override
        public String geturl() {
            return "http://localhost:8000/measurement/sync/AbnormalBloodPressureLog";
        }
    },
    BloodPressureHeartBeat{
        @Override
        public String geturl() {
            return "http://localhost:8000/measurement/sync/BloodPressureHeartBeat/NotDeleteSolr";
        }
    }

}


interface GetUrl{
    String geturl();
}

