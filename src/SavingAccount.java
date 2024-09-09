public class SavingAccount {
    private String iniName;
    private String fullName;
    private String address;
    private long nic;  // Using long to handle larger NIC numbers
    private String purpose;
    private String userName;
    private double depositAmount;

    public SavingAccount(String iniName, String fullName, String address, long nic, String purpose, String userName, double depositAmount) {
        this.iniName = iniName;
        this.fullName = fullName;
        this.address = address;
        this.nic = nic;
        this.purpose = purpose;
        this.userName = userName;
        this.depositAmount = depositAmount;
    }

    // Getters and setters for each field
    public String getIniName() {
        return iniName;
    }

    public void setIniName(String iniName) {
        this.iniName = iniName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getNic() {
        return nic;
    }

    public void setNic(long nic) {
        this.nic = nic;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }
}
