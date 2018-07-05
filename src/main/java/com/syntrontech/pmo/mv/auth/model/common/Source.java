package com.syntrontech.pmo.mv.auth.model.common;

// CREATE (由租戶管理者建立) / LDAP (介接 ad/ldap) / REGISTER (註冊建立) / IMPORT (檔案匯入) / INIT(系統初始建立)
public enum Source {
	CREATE, LDAP, REGISTER, IMPORT, INIT;
}
