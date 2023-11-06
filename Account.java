import java.util.ArrayList;
import java.util.List;

public class Account {
    private int Zid;
    private User user;
    private double rCBalance;
    private double zCBalance;
    private List<String> statement;
   

    
    public Account(int Zid, User user, double rCBalance, double zCBalance) {
        this.Zid = Zid;
        this.user = user;
        this.rCBalance = rCBalance;
        this.zCBalance = zCBalance;
        this.statement = new ArrayList<>();
      
    }

    public int getZid() {
        return Zid;
    }

    public void setZid(int Zid) {
        this.Zid = Zid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getRCBalance() {
        return rCBalance;
    }

    public void addRCBalance(double rCBalance) {
        this.rCBalance += rCBalance;
    }

    public double getZCBalance() {
        return zCBalance;
    }

    public void addZCBalance(double zCBalance) {
        this.zCBalance += zCBalance;
    }

  

    public void addStatement(String transaction) {
        this.statement.add(transaction);
    }
	
	public void getStatement(){
		
		for(String s : statement ) {
            System.out.println(s);
        }
	}
	
	
}
