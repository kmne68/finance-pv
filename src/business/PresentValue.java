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
    

    private void calculatePresentValue() {
        double monthlyRate = super.getRate() / 12.0;
        try {            
            monthlyValue = new double[super.getTerm() + 1]; 
            monthlyDiscount = new double[super.getTerm() + 1];
            endingBalance = new double[super.getTerm() + 1];
                        
            for (int month = 0; month <= (super.getTerm() + 1); month++) {
    //        for(int month = 0; month < 24; month++) {
                double denom = Math.pow(1 + monthlyRate, this.getTerm() - month);
                System.out.println("denom = " + denom);
                this.monthlyValue[month] = super.getAmount() / denom;                
                this.monthlyDiscount[month] = super.getAmount() - this.monthlyValue[month];
                this.endingBalance[month] = this.monthlyValue[month];
            }
            this.built = true;
        } catch (Exception e) {
            System.out.println("from catch block");
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
        if(month < 0 || month > super.getTerm() + 1) {
            return 0;
        }
        return this.endingBalance[month];
    }

    @Override
    public double getResult() {
        if(!built) {
            calculatePresentValue();
        }
        return this.endingBalance[0];
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
