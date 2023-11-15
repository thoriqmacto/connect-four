// complete this function, add default values
fun carPrice(old: Int = 5, kilometers: Int = 100_000, maximumSpeed: Int = 120, automatic: Boolean = false){
    val defaultPrice = 20_000
    val priceDecreaseByYear = 2_000
    val priceParamsBySpeed = 100
    val priceDecreaseByKilometers = 200
    val kilometersThresholdForPriceDecrease = 10_000
    val priceIncreaseByAutoTransmission = 1500

    // old factor
//    var oldFactor = 0
//    if (old > 5) {
//        oldFactor = old - 5
//    }

    // speed factor
    var speedFactor = 0
    if (maximumSpeed != 120) {
        speedFactor = maximumSpeed - 120
    }

    // kilometers factor
    val kmFactor = kilometers / kilometersThresholdForPriceDecrease

    // transmission factor
    var transFactors = 0
    if (automatic) {
        transFactors = 1
    }

    // price calculation
    println (defaultPrice -
            (old * priceDecreaseByYear) +
            (speedFactor * priceParamsBySpeed) -
            (kmFactor * priceDecreaseByKilometers) +
            (transFactors * priceIncreaseByAutoTransmission))
}

//fun main(){
//    carPrice()
//    carPrice(7)
//    carPrice(maximumSpeed = 119)
//}