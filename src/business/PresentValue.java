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
    private double[] monthlyValue;              // monthlyValue
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
            double monthlyRate = super.getRate() / 12.0;
            System.out.println("monthly rate = " + monthlyRate);
            
            this.monthlyValue = new double[super.getTerm()]; // 0 - 23
            this.monthlyDiscount = new double[super.getTerm()];
            this.endingBalance = new double[super.getTerm()];
                        
            System.out.println("term = " + super.getTerm());
            //this.monthlyValue[0] = 0;
            for(int month = super.getTerm() - 1; month >= 0; month--) {
      //          int month = 23;
            double denom = Math.pow(1 + monthlyRate, super.getTerm());
      /*      if (month == super.getTerm()) {
                    this.endingBalance[month] = super.getAmount();
                } */
                this.monthlyValue[month] = this.getAmount() / denom;
                
                this.monthlyDiscount[month] = super.getAmount() - this.monthlyValue[month];
                this.endingBalance[month] = this.monthlyValue[month];

            System.out.println("value, discount, balance " + this.monthlyValue + ", " + this.monthlyDiscount + ", " + this.endingBalance);
                //this.monthlyValue[month] - this.monthlyDiscount[month] + super.getAmount();
             //   this.endingBalance[month] = monthlyDiscount[month]; // - (this.monthlyDiscount[month] *;
            }
            this.built = true;
        } catch (Exception e) {
            super.setErrorMessage("Present value couldn't be calculated: " + e.getMessage());
            this.built = false;
        } 
    }
    
    @Override 
    public double getPrincipleFactor() {
        if(!built) {
            calculatePresentValue();
        }
        return super.getAmount();
    }

    @Override
    public double getBeginningBalance(int month) {
        if(!built) {
            calculatePresentValue();
        }
        if(month < 1 || month > super.getTerm()) {
            return 0;
        }
        return this.monthlyValue[month - 1];
    }

    @Override
    public double getInterestFactor(int month) {
        return this.monthlyDiscount[month];
    }

    @Override
    public double getEndingBalance(int month) {
        if(!built) {
            calculatePresentValue();
        }
        if(month < 1 || month > super.getTerm()) {
            return 0;
        }
        return this.endingBalance[month - 1];
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
        return PresentValue.PRINCIPALBALANCEDESCRIPTION;
    }

    @Override
    public String getInterestFactorDescription() {
        return null;
    }

    @Override
    public String getEndingBalanceDescription() {
        return PresentValue.ENDINGBALANCEDESCRIPTION;
    }

    @Override
    public String getTableTitle() {
        return PresentValue.TITLE;
    }
            
        
    
}
