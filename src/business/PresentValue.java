/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Keith
 * 
 * The discount column should be considered the ‘Principal Factor’ value, and
 * Present Value should be considered the ‘Ending Balance.’
 * 
 */
public class PresentValue extends Financial {
    
    public static final String AMOUNTDESCRIPTION = "Future Amount";
    public static final String RESULTDESCRIPTION = "Present Value";
    public static final String TITLE = "Present Value";
    public static final String PERIOD = "Month";
    public static final String PRINCIPALBALANCEDESCRIPTION = "Discount";
    public static final String ENDINGBALANCEDESCRIPTION = "Ending Balance";
    
    private boolean built;
    private double[] monthlyValue;              // monthlyPayment
    private double[] monthlyDiscount;           // interestCharge
    private double[] endingBalance;             // endingBalance

    public PresentValue() {
        super();
    }
    
    
    public PresentValue(double amount, double rate, int term) {
        super(amount, rate, term);
        
        this.built = false;
        if(super.isValid()) {
            
            calculatePresentValue();
        }
    }
    
    
    // monthlyValue = beginning value, present value
    // monthlyDiscount = monthly change, interest charge
    // endingBalance = ending value, final value
    // PresentValue = AmountInFuture  / ((1 + Rate)^Term)
    private void calculatePresentValue() {
        try {
            this.monthlyValue = new double[super.getTerm()];
            this.monthlyDiscount = new double[super.getTerm()];
            this.endingBalance = new double[super.getTerm()];
            
            this.monthlyValue[0] = 0;
            for(int month = 0; month < super.getTerm(); month++) {
                if(month > 0) {
                    this.monthlyValue[month] = this.endingBalance[month - 1];
                }
                this.monthlyDiscount[month] = (this.monthlyValue[month] / (Math.pow((1 + (super.getRate() / 12.0)), (double)super.getTerm())));
                this.endingBalance[month] = this.monthlyValue[month] - this.monthlyDiscount[month] + super.getAmount();
            }
            this.built = true;
        } catch (Exception e) {
            super.setErrorMessage("Present value couldn't be calculated: " + e.getMessage());
            this.built = false;
        }
    }
    
    @Override 
    public double getPrincipleFactor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getBeginningBalance(int month) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getInterestFactor(int month) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getEndingBalance(int month) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getResult() {
        if(!built) {
            calculatePresentValue();
        }
        return this.endingBalance[super.getTerm() - 1];
    }

    @Override
    public String getBeginningBalanceDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPrincipleFactorDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getInterestFactorDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEndingBalanceDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTableTitle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
            
        
    
}
