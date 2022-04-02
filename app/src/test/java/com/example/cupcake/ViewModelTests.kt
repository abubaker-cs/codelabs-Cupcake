package com.example.cupcake

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cupcake.model.OrderViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ViewModelTests {

    /**
     * To specify that LiveData objects should not call the main thread we need to provide a
     * specific test rule any time we are testing a LiveData object.
     */
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun quantity_twelve_cupcakes() {

        val viewModel = OrderViewModel()

        /**
         *  it is important to note that when testing the values of a LiveData object,
         *  the objects need to be observed in order for changes to be emitted.
         */
        viewModel.quantity.observeForever {}

        // Quantity = 12 Cups
        viewModel.setQuantity(12)

        // Assume: 12 Cups will be selected
        assertEquals(12, viewModel.quantity.value)
    }

    @Test
    fun price_twelve_cupcakes() {

        val viewModel = OrderViewModel()

        // Quantity = 12 Cups
        viewModel.setQuantity(12)

        viewModel.price.observeForever {}

        // Assume that the final price will be 27$
        // 12 Quantity * 2$ for 1 Cup + 3 urgent delivery = 27
        assertEquals("$27.00", viewModel.price.value)
    }
}