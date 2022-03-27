package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_PER_CUPCAKE = 2.00
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

class OrderViewModel : ViewModel() {

    // Quantity
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    // Flavor
    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor

    // Date
    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    // Price
    private val _price = MutableLiveData<Double>()

    // Use Transformations.map() to initialize the new variable, pass in the _price and a lambda function.
    val price: LiveData<String> = Transformations.map(_price) {
        //  Convert the price to local currency format
        NumberFormat.getCurrencyInstance().format(it)
    }

    // Prepare the list of 4 dates
    val dateOptions = getPickupOptions()

    /**
     *  We are using the init block to initialize the properties when an instance of OrderViewModel is created.
     */
    init {

        // Quantity = 0
        // Flavor = ""
        // Date = dateOptions[0] (Current Date)
        // Price = 0.0

        resetOrder()

    }

    /**
     * Modifiers (Setters)
     */
    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes

        // update the price variable when the quantity is set
        updatePrice()
    }

    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    fun setDate(pickupDate: String) {
        _date.value = pickupDate

        // update the price variable when the quantity is set
        updatePrice()
    }

    /**
     *  To check if the flavor for the order has been set or not.
     *  You will use this method in the StartFragment class in a later step.
     */
    fun hasNoFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }

    /**
     * Create and return the list of pickup dates
     */
    private fun getPickupOptions(): List<String> {

        // Options, Formatter, Calendar
        val options = mutableListOf<String>()

        // Format: "Tue Dec 10"
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())

        // This variable will contain the current date and time
        val calendar = Calendar.getInstance()

        // Create a list of dates starting with the current date and the following 3 dates
        repeat(4) {

            // Apply the selected Date Formatter
            options.add(formatter.format(calendar.time))

            // Build up a list of dates starting with the current date and the following three dates
            calendar.add(Calendar.DATE, 1)
        }

        // Return list of 4 dates
        return options
    }


    /**
     * Calculate the price
     */
    private fun updatePrice() {
        var calculatedPrice = (quantity.value ?: 0) * PRICE_PER_CUPCAKE

        // If the user selected the first option (today) for pickup, add the surcharge
        if (dateOptions[0] == _date.value) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }

        _price.value = calculatedPrice
    }


    /**
     * Reset the values to default
     */
    fun resetOrder() {
        _quantity.value = 0
        _flavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }

}