package gas.money.calculator;


/**
 *
 * @author ahlin_000
 */
public class Trip {
    private int riders;
    private double mpg, distance, gasPrice, price;
    
    public Trip (int myRiders, double myDistance, double myMpg, double myPrice){
        riders = myRiders;
        distance = myDistance;
        mpg = myMpg;
        gasPrice = myPrice;
        price = 0.0;
    }
    
    public double getPrice(){
        price = distance / mpg * gasPrice / riders;
        return price;
    }
    
    public void incRider(){
        riders++;
    }
    
    public void roundTrip(){
        distance = distance * 2;
    }
    
}
