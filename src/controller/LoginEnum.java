package controller;

public enum LoginEnum {
	DEFAULT(0), MANAGER(1), RENTER(2), REG_RENTER(3), LANDLORD(4);
	
    private final int code;
    LoginEnum(int code)  { this.code = code; }
    public int getCode() { return code; }
}
