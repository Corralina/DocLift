package com.company.domian;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    LOC,
    USER,
    ADMIN,
    RECORTED,
    TABLIN,
    CONFIRM,
    DROP,
    RESOLVE;

    private Role() {
    }

    public String getAuthority() {
        return this.name();
    }

    public String getNameByRole(Role role){
        String name = "";
        switch (role.name()){
            case "LOC":
                name = this.getLocName();
                break;
            case "USER":
                name = this.getUserName();
                break;
            case "ADMIN":
                name = this.getAdminName();
                break;
            case "RECORTED":
                name = this.getRecortedName();
                break;
            case "TABLIN":
                name = this.getTablinName();
                break;
            case "CONFIRM":
                name = this.getConfirmName();
                break;
            case "DROP":
                name = this.getDropName();
                break;
            case"RESOLVE":
                name = this.getResolveName();
                break;
        }

        return name;
    }

    public String getLocName(){
        return "Локальний користувач";
    }

    public String getUserName(){
        return "Користувач";
    }

    public String getAdminName(){
        return "Адміністратор";
    }

    public String getRecortedName(){
        return "Додавання документів";
    }

    public String getTablinName(){
        return "Розпис резолюцій";
    }

    public String getConfirmName(){
        return "Погодження резолюцій";
    }

    public String getDropName(){
        return "Видалений користувач";
    }

    public String getResolveName(){return "Можливість перегляду усіх резолюцій";}
}
