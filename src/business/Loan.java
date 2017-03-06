
package business;

/*
 * Loan.java
 *
 * @author Keith Emery
 * IS 287
 * Spring, 2017
 * Instructor P. Daniel
 * Assignment 4
 */
public class Loan extends Financial {
    
    public static final String AMOUNTDESCRIPTION = "Loan Amount";
    public static final String RESULTDESCRIPTION = "Monthly Loan Payment";
    public static final String TITLE = "Loan";
    public static final String BEGINNINGBALANCEDESCRIPTION = "Beginning Loan Amount:";
    public static final String PRINCIPLEFACTORDESCRIPTION = "Monthly Payment:";
    public static final String INTERESTFACTORDESCRIPTION =  "Interest Accrued:";
    public static final String ENDINGBALANCEDESCRIPTION = "Ending Loan Value:";
    public static final String PRINCIPLEPAIDDESCRIPTION = "Principle Paid:";
    
    private double monthlyPayment;
    private boolean built;
    private double[] beginningBalance;
    private double[] interestCharge;
    private double[] endingBalance;
    
    
    public Loan() {
        super();
    }
    
    
    public Loan(double principle, double rate, int term) {
        super(principle, rate, term);

        monthlyPayment = 0;
        built = false;
        
        if(super.isValid()) {
            buildLoan();
        }
    }


    @Override
    public double getResult() {
        if (!built) { buildLoan(); }
        return monthlyPayment;
    }


    @Override
    public double getBeginningBalance(int month) {
        if (!built) { buildLoan(); }
        if (month < 1 || month > super.getTerm()) { return 0; }
        return this.beginningBalance[month-1];
    }
    
    public double getInterestCharge(int month) {
        if (!built) { buildLoan(); }
        if (month < 1 || month > super.getTerm()) { return 0; }
        return this.interestCharge[month-1];
    }
    
    @Override
    public double getEndingBalance(int month) {
        if (!built) { buildLoan(); }
        if (month < 1 || month > super.getTerm()) { return 0; }
        return this.endingBalance[month-1];
    }
    /*
    public double getPrincipleFactor() {
        if(!built) { buildLoan(); }
        return this.monthlyPayment;
    } */
    
    private void buildLoan() {
        //calculate Monthly Payment....        
        try {
        double monthlyRate = super.getRate() / 12.0;
        double denom = Math.pow((1 + monthlyRate), super.getTerm()) - 1;
        this.monthlyPayment = (monthlyRate + monthlyRate/denom) * super.getAmount();
        
        this.beginningBalance = new double[super.getTerm()];
        this.interestCharge = new double[super.getTerm()];
        this.endingBalance = new double[super.getTerm()];
        
        this.beginningBalance[0] = super.getAmount();
        for(int year = 0; year < super.getTerm(); year++) {
            if (year > 0) {
                this.beginningBalance[year] = this.endingBalance[year-1];
            }
            this.interestCharge[year] = this.beginningBalance[year] * monthlyRate;
            this.endingBalance[year] = this.beginningBalance[year] + this.interestCharge[year] - this.monthlyPayment;
        }
        built = true;
        } catch(Exception e) {
            super.setErrorMessage("Loan build error: " + e.getMessage());
            this.built = false;
        }
    } // end buildLoan()
    

    @Override
    public double getInterestFactor(int month) {
        return this.getInterestCharge(month);
    }

    @Override
    public String getBeginningBalanceDescription() {
        return Loan.BEGINNINGBALANCEDESCRIPTION;
    }

    @Override
    public String getPrincipleFactorDescription() {
        return Loan.PRINCIPLEFACTORDESCRIPTION;
    }

    @Override
    public String getInterestFactorDescription() {
        return Loan.INTERESTFACTORDESCRIPTION;
    }

    @Override
    public String getEndingBalanceDescription() {
        return Loan.ENDINGBALANCEDESCRIPTION;
    }


    @Override
    public String getTableTitle() {
        
        return Loan.TITLE;
    }

    @Override
    public double getPrincipleFactor() {
        if(!built) { 
            buildLoan(); 
        }
        return this.monthlyPayment;
    }
    
    
    public double getPrinciplePaid(int month) {
        double principlePaid = 0.0;
        
        principlePaid = (this.beginningBalance[month - 1] - this.endingBalance[month - 1]) * -1;
            
        return principlePaid;
    
    }
}