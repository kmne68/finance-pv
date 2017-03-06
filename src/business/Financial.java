
package business;

/*
 * Financial.java
 *
 * @author Keith Emery
 * IS 287
 * Spring, 2017
 * Instructor P. Daniel
 * Assignment 4
 */

abstract public class Financial {
    
    private double deposit;
    private double rate;    
    private int term; 
    private String errorMessage;
    
    //private double fv;     //final value of annuity
    public Financial() {
        
        this.deposit = 0.0;
        this.rate = 0.0;
        this.term = 0;
    }
    

    public Financial(double deposit, double rate, int term) {
        
        this.deposit = deposit;
        this.rate = rate;
        this.term = term;
    }
    
    
    protected boolean isValid() {
        
        this.errorMessage = "";
        
        if(this.deposit <= 0) {
            this.errorMessage += "Deposit must be a positive value. ";
        }
        if(this.rate <= 0) {
            this.errorMessage += "Rate must be a positive value. "; 
        }
        if(this.term <= 0) {
            this.errorMessage += "Term must be a positive value. ";
        }
        return this.getErrorMessage().isEmpty();
    }

    public double getAmount() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }  
    
    
    protected void setErrorMessage(String emsg) {
        this.errorMessage = emsg;
    }
    
    
    public String getErrorMessage() {
        
        return errorMessage;
    }
    
    
    abstract public double getPrincipleFactor();
    abstract public double getBeginningBalance(int month);
    abstract public double getInterestFactor(int month);
    abstract public double getEndingBalance(int month);
    
    abstract public double getResult();
    
    abstract public String getBeginningBalanceDescription();
    abstract public String getPrincipleFactorDescription();
    abstract public String getInterestFactorDescription();
    abstract public String getEndingBalanceDescription();
    
    abstract public String getTableTitle();
    
}
