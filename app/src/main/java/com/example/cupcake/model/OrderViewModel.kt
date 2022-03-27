package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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

}