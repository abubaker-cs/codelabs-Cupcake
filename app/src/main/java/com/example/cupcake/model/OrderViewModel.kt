package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class OrderViewModel : ViewModel() {

    // Quantity
    private val _quantity = MutableLiveData<Int>(0)
    val quantity: LiveData<Int> = _quantity

    // Flavor
    private val _flavor = MutableLiveData<String>("")
    val flavor: LiveData<String> = _flavor

    // Date
    private val _date = MutableLiveData<String>("")
    val date: LiveData<String> = _date

    // Price
    private val _price = MutableLiveData<Double>(0.0)
    val price: LiveData<Double> = _price

    // Prepare the list of 4 dates
    val dateOptions = getPickupOptions()

    /**
     * Modifiers (Setters)
     */
    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
    }

    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    fun setDate(pickupDate: String) {
        _date.value = pickupDate
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

}