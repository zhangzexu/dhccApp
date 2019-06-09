package com.dhcc.Entity;


import java.io.Serializable;

public class UserInfoEntity implements Serializable {
    private long _id;
    private String _name;
    private String _password;
    private String _phone;
    private String _bank_card;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_phone() {
        return _phone;
    }

    public void set_phone(String _phone) {
        this._phone = _phone;
    }

    public String get_bank_card() {
        return _bank_card;
    }

    public void set_bank_card(String _bank_card) {
        this._bank_card = _bank_card;
    }

    @Override
    public String toString() {
        return "UserInfoEntity{" +
                "_id=" + _id +
                ", _name='" + _name + '\'' +
                ", _password='" + _password + '\'' +
                ", _phone='" + _phone + '\'' +
                ", _bank_card='" + _bank_card + '\'' +
                '}';
    }
}
