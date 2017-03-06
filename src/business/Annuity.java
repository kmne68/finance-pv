package business;

/*
 * Annuity.java
 *
 * @author Keith Emery
 * IS 287
 * Spring, 2017
 * Instructor P. Daniel
 * Assignment 4
 */
public class Annuity extends Financial {

    public static final String AMOUNTDESCRIPTION = "Monthly Deposit";
    public static final String RESULTDESCRIPTION = "Final Value";
    public static final String TITLE = "Annuity";
    public static final String BEGINNINGBALANCEDESCRIPTION = "Beginning Annuity Value:";
    public static final String PRINCIPLEFACTORDESCRIPTION = "Deposit:";
    public static final String INTERESTFACTORDESCRIPTION =  "Interest Earned:";
    public static final String ENDINGBALANCEDESCRIPTION = "Ending Annuity Value:";

    /*    private double deposit;
    private double rate;
    private int term;       */
    private boolean built;  //whether fv has been built
    //private double fv;     //final value of annuity
    private double[] beginningBalance;
    private double[] interestEarned;
    private double[] endingBalance;

    public Annuity() {
        super();
    }

    public Annuity(double deposit, double rate, int term) {
        super(deposit, rate, term);

        this.built = false;
        if (super.isValid()) {
            //this.fv = 0;
            calculateAnnuity();
        }
    }

    @Override
    public double getResult() {
        if (!built) {
            calculateAnnuity();
        }
        return this.endingBalance[super.getTerm() - 1];
    }

    @Override
    public double getBeginningBalance(int month) {
        if (!built) {
            calculateAnnuity();
        }
        if (month < 1 || month > super.getTerm()) {
            return 0;
        }
        return this.beginningBalance[month - 1];
    }

    @Override
    public double getInterestFactor(int month) {
        if (!built) {
            calculateAnnuity();
        }
        if (month < 1 || month > super.getTerm()) {
            return 0;
        }
        return this.interestEarned[month - 1];
    }

    public double getEndingBalance(int month) {
        if (!built) {
            calculateAnnuity();
        }
        if (month < 1 || month > super.getTerm()) {
            return 0;
        }
        return this.endingBalance[month - 1];
    }
    
    
    @Override
    public double getPrincipleFactor() {
        return super.getAmount();
    }

    private void calculateAnnuity() {
        //internal logic for building an annuity...
        //this.fv = 0;
        //double intearned=0;
        try {
            this.beginningBalance = new double[super.getTerm()];
            System.out.println("term " + super.getTerm());
            this.interestEarned = new double[super.getTerm()];
            System.out.println("term " + this.interestEarned.toString());
            this.endingBalance = new double[super.getTerm()];
            System.out.println("term " + this.endingBalance.toString());

            beginningBalance[0] = 0;
            for (int month = 0; month < super.getTerm(); month++) {
                if (month > 0) {
                    this.beginningBalance[month] = this.endingBalance[month - 1];
                }
                this.interestEarned[month] = (this.beginningBalance[month] + super.getAmount()) * (super.getRate() / 12.0);
                this.endingBalance[month] = this.beginningBalance[month] + this.interestEarned[month] + super.getAmount();
                //this.fv = this.fv + intearned + this.deposit;
            }
            this.built = true;
        } catch (Exception e) {
            super.setErrorMessage("Annuity build failed: " + e.getMessage());
            this.built = false;
        }
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
        
        return Annuity.TITLE;
    }

}
