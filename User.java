public class User {
    private static int count=1;
    private int id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private int hId;
    private boolean approved;
    private int zaccountId;
    private double depositAmount;
	
   
    public User( String name, String email, String password, String phone , double depositAmount) {
        this.id = count++;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.hId = -1;
        this.approved = false;
        this.zaccountId = -1;
		this.depositAmount = depositAmount;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getHId() {
        return hId;
    }

    public void setHId(int hId) {
        this.hId = hId;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public int getZaccountId() {
        return zaccountId;
    }

    public void setZaccountId(int zaccountId) {
        this.zaccountId = zaccountId;
    }
	
	public double getDepositAmount(){
		return depositAmount;
	}
	
	public void setDepositAmount(double amount){
		this.depositAmount =depositAmount;	
	}
	
}
