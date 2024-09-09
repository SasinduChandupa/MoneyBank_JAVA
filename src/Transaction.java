public class Transaction {
    private String userName;
    private double depositAmount;

    public Transaction(String userName, double depositAmount) {
        this.userName = userName;
        this.depositAmount = depositAmount;
    }

    // Getters and setters
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
