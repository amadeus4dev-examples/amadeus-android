package com.amadeus.android.example.fragments.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amadeus.android.domain.resources.HotelOffer
import com.amadeus.android.example.R
import com.amadeus.android.example.databinding.ItemHotelOffersBinding
import com.amadeus.android.example.fragments.search.HotelsOffersAdapter.HotelOffersViewHolder

class HotelsOffersAdapter(viewModel: HotelsOffersViewModel) :
    ListAdapter<HotelOffer, HotelOffersViewHolder>(HotelOffersDiffCallback()) {

    override fun getItemId(position: Int): Long {
        return getItem(position).hotel?.hotelId.hashCode().toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelOffersViewHolder {
        return HotelOffersViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_hotel_offers, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HotelOffersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HotelOffersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemHotelOffersBinding.bind(itemView)

        fun bind(hotelOffers: HotelOffer) {
            binding.hotelTitle.text = hotelOffers.hotel?.name
            hotelOffers.hotel?.address?.let { address ->
                val builder = StringBuilder()
                address.lines?.forEach { builder.append("$it ") }
                address.postalCode?.let { builder.append(", $it") }
                address.cityName?.let { builder.append(" $it") }
                binding.hotelAddress.text = builder.toString()
            }
            binding.offersCount.text = hotelOffers.offers?.size.toString()
        }
    }

    class HotelOffersDiffCallback : DiffUtil.ItemCallback<HotelOffer>() {
        override fun areItemsTheSame(oldItem: HotelOffer, newItem: HotelOffer): Boolean {
            return oldItem.hotel?.hotelId == newItem.hotel?.hotelId
        }

        override fun areContentsTheSame(oldItem: HotelOffer, newItem: HotelOffer): Boolean {
            return oldItem == newItem
        }
    }
}