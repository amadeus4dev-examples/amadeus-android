package com.amadeus.android.example.fragments.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import com.amadeus.android.example.R
import com.amadeus.android.example.databinding.FragmentHotelsOffersBinding
import com.amadeus.android.example.mainActivity
import com.amadeus.android.example.utils.gone
import com.amadeus.android.example.utils.hideKeyboard
import com.amadeus.android.example.utils.visible
import com.amadeus.android.example.utils.visibleOrGone
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId


class HotelsOffersFragment : Fragment(R.layout.fragment_hotels_offers) {

    private lateinit var binding: FragmentHotelsOffersBinding
    private val viewModel by viewModels<HotelsOffersViewModel>()
    private lateinit var adapter: HotelsOffersAdapter

    private var checkInDate: LocalDate? = null
    private var checkOutDate: LocalDate? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHotelsOffersBinding.bind(view)
        mainActivity()?.setSupportActionBar(binding.toolbar)
        initView()
        subscribe()
    }

    private fun initView() {
        if (!this::adapter.isInitialized) {
            adapter = HotelsOffersAdapter(viewModel)
        }
        binding.destinationInput.editText?.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.searchByDestination(
                    v.text.toString(),
                    checkInDate,
                    checkOutDate
                )
                hideKeyboard()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayout.VERTICAL
            ).apply {
                setDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.divider_default
                    )!!
                )
            })
        binding.datePicker.setOnClickListener {
            val constraints = CalendarConstraints.Builder()
                .setValidator(DateValidatorPointForward.now())
                .build()
            MaterialDatePicker.Builder.dateRangePicker()
                .setCalendarConstraints(constraints)
                .build()
                .apply {
                    addOnPositiveButtonClickListener { selection ->
                        checkInDate = Instant.ofEpochMilli(selection.first ?: 0)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                        checkOutDate = Instant.ofEpochMilli(selection.second ?: 0)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                        binding.datePicker.text = "$checkInDate - $checkOutDate"
                    }
                }.show(childFragmentManager, "datePicker")
        }
    }

    private fun subscribe() {
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.noDataText.gone(true)
            binding.progressBar.visibleOrGone(isLoading, true)
        }
        viewModel.error.observe(viewLifecycleOwner) { message ->
            binding.noDataText.text = message
            binding.recyclerView.gone(true)
            binding.progressBar.gone(true)
            binding.noDataText.visible(true)
        }
        viewModel.hotelOffers.observe(viewLifecycleOwner) { hotelOffers ->
            adapter.submitList(hotelOffers)
            binding.recyclerView.visible(true)
            binding.noDataText.gone(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
    }

}
