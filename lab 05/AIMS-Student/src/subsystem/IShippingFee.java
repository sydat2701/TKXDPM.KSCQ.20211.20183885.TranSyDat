package subsystem;

public interface IShippingFee {

//   default float calculateShippingFee(float amount){
//       return 0;
//   };
    public abstract float calculateShippingFee(float amount);
}
