package com.amadeus.android.example.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amadeus.android.base.Result
import com.amadeus.android.base.succeeded
import com.amadeus.android.domain.hotel.models.HotelOffers
import com.amadeus.android.example.SampleApplication
import com.amadeus.android.example.utils.SingleLiveEvent
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDate

class HotelsOffersViewModel : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    val error = SingleLiveEvent<String>()

    private val _hotelOffers = MutableLiveData<List<HotelOffers>>()
    val hotelOffers: LiveData<List<HotelOffers>>
        get() = _hotelOffers

    fun searchByDestination(
        destination: String,
        checkInDate: LocalDate?,
        checkOutDate: LocalDate?
    ) {
        viewModelScope.launch {
            _loading.value = true
            when (val result = SampleApplication.amadeus.shopping.hotelOffers.get(
                cityCode = destination,
                checkInDate = checkInDate,
                checkOutDate = checkOutDate
            )) {
                is Result.Success -> {
                    if (result.succeeded) {
                        _hotelOffers.value = result.data
                    } else {
                        //call return without data
                        error.value = "No result for your research"
                    }
                }
                is Result.Error -> error.value = "Error when retrieving data."
            }
            _loading.value = false
        }
    }
}
