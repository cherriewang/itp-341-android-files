package itp341.wang.cherrie.a8.model;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Cherrie on 10/23/16.
 */

public class Card implements Serializable {
    // number
    private int num;
    // name
    private String name;
    // security code (optional)
    private int securityCode;

    // CONSTRUCTOR
    public Card() {
        super();
    }

    // GETTERS
    public int getNum() {
        return num;
    }
    public String getName() {
        return name;
    }
    public int getSecurityCode() {
        return securityCode;
    }

    // SETTERS
    public void setNum(int num) {
        this.num = num;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }
}
