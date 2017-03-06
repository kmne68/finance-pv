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
    private double[] monthlyValue;
    private double[] monthlyDiscount;
    private double[] endingBalance;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
