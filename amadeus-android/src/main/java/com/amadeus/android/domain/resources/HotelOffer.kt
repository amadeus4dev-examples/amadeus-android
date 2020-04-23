package com.amadeus.android.domain.resources

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

/**
 * An HotelOffer object as returned by the HotelOffers API.
 * @see com.amadeus.shopping.HotelOffers.get
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class HotelOffer internal constructor(
    val type: String? = null,
    val hotel: Hotel? = null,
    val available: Boolean = false,
    val offers: List<Offer>? = null
) : Parcelable {

    /**
     * An HotelOffer-related object as returned by the HotelOffers API.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Hotel internal constructor(
        val type: String? = null,
        val hotelId: String? = null,
        val chainCode: String? = null,
        val brandCode: String? = null,
        val dupeId: String? = null,
        val name: String? = null,
        val rating: Int? = null,
        val description: TextWithLanguageType? = null,
        val amenities: List<String>? = null,
        val media: List<MediaURI>? = null,
        val cityCode: String? = null,
        val latitude: Float = 0.0f,
        val longitude: Float = 0.0f,
        val hotelDistance: HotelDistance? = null,
        val address: AddressType? = null,
        val contact: HotelContact? = null
    ) : Parcelable

    /**
     * An HotelOffer-related object as returned by the HotelOffers API.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Offer internal constructor(
        val type: String? = null,
        val id: String? = null,
        val roomQuantity: Int? = null,
        val rateCode: String? = null,
        val rateFamilyEstimated: RateFamily? = null,
        val category: String? = null,
        val description: TextWithLanguageType? = null,
        val commission: Commission? = null,
        val boardType: String? = null,
        val room: RoomDetails? = null,
        val guests: Guests? = null,
        val price: HotelPrice? = null,
        val policies: PolicyDetails? = null
    ) : Parcelable

    /**
     * An HotelOffer-related object as returned by the HotelOffers API.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class HotelDistance internal constructor(
        val description: String? = null,
        val distance: Float = 0.0f,
        val distanceUnit: String? = null
    ) : Parcelable

    /**
     * An HotelOffer-related object as returned by the HotelOffers API.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class RateFamily internal constructor(
        val code: String? = null,
        val type: String? = null
    ) : Parcelable

    /**
     * An HotelOffer-related object as returned by the HotelOffers API.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Commission internal constructor(
        val percentage: String? = null,
        val amount: String? = null,
        val description: TextWithLanguageType? = null
    ) : Parcelable

    /**
     * An HotelOffer-related object as returned by the HotelOffers API.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class RoomDetails internal constructor(
        val type: String? = null,
        val typeEstimated: EstimatedRoomType? = null,
        val description: TextWithLanguageType? = null
    ) : Parcelable

    /**
     * An HotelOffer-related object as returned by the HotelOffers API.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class EstimatedRoomType internal constructor(
        val category: String? = null,
        val beds: Int? = null,
        val bedType: String? = null
    ) : Parcelable

    /**
     * An HotelOffer-related object as returned by the HotelOffers API.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class HotelPrice internal constructor(
        val currency: String? = null,
        val total: String? = null,
        val base: String? = null,
        val taxes: List<HotelTax>? = null,
        val variations: PriceVariations? = null
    ) : Parcelable

    /**
     * An HotelOffer-related object as returned by the HotelOffers API.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class HotelTax internal constructor(
        val currency: String? = null,
        val amount: String? = null,
        val code: String? = null,
        val percentage: String? = null,
        val included: Boolean = false,
        val description: String? = null,
        val pricingFrequency: String? = null,
        val pricingMode: String? = null
    ) : Parcelable

    /**
     * An HotelOffer-related object as returned by the HotelOffers API.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class PriceVariations internal constructor(
        val average: BaseTotalAmount? = null,
        val changes: List<PriceVariation>? = null
    ) : Parcelable

    /**
     * An HotelOffer-related object as returned by the HotelOffers API.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class PriceVariation internal constructor(
        val startDate: String? = null,
        val endDate: String? = null,
        val base: String? = null,
        val total: String? = null
    ) : Parcelable

    /**
     * An HotelOffer-related object as returned by the HotelOffers API.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class BaseTotalAmount internal constructor(
        val base: String? = null,
        val total: String? = null
    ) : Parcelable

    /**
     * An HotelOffer-related object as returned by the HotelOffers API.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Guests internal constructor(
        val adults: Int? = null,
        val childAges: List<Int>? = null
    ) : Parcelable

    /**
     * An HotelOffer-related object as returned by the HotelOffers API.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class TextWithLanguageType internal constructor(
        val lang: String? = null,
        val text: String? = null
    ) : Parcelable

    /**
     * An HotelOffer-related object as returned by the HotelOffers API.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class MediaURI internal constructor(
        val uri: String? = null,
        val category: String? = null
    ) : Parcelable

    /**
     * An HotelOffer-related object as returned by the HotelOffers API.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class AddressType internal constructor(
        val lines: List<String>? = null,
        val postalCode: String? = null,
        val cityName: String? = null,
        val countryCode: String? = null,
        val stateCode: String? = null
    ) : Parcelable

    /**
     * An HotelOffer-related object as returned by the HotelOffers API.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class HotelContact internal constructor(
        val phone: String? = null,
        val fax: String? = null
    ) : Parcelable

    /**
     * An HotelOffer-related object as returned by the HotelOffers API.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class PolicyDetails internal constructor(
        val guarantee: GuaranteePolicy? = null,
        val deposit: GuaranteePolicy? = null,
        val prepay: GuaranteePolicy? = null,
        val holdTime: GuaranteePolicy? = null,
        val cancellation: CancellationPolicy? = null,
        val checkInOut: CheckInOutPolicy? = null
    ) : Parcelable

    /**
     * An HotelOffer-related object as returned by the HotelOffers API.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class CheckInOutPolicy internal constructor(
        val checkIn: String? = null,
        val checkInDescription: TextWithLanguageType? = null,
        val checkOut: String? = null,
        val checkOutDescription: TextWithLanguageType? = null
    ) : Parcelable

    /**
     * An HotelOffer-related object as returned by the HotelOffers API.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class GuaranteePolicy internal constructor(
        val amount: String? = null,
        val deadline: String? = null,
        val description: TextWithLanguageType? = null,
        val acceptedPayments: PaymentPolicy? = null
    ) : Parcelable

    /**
     * An HotelOffer-related object as returned by the HotelOffers API.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class CancellationPolicy internal constructor(
        val type: String? = null,
        val amount: String? = null,
        val numberOfNights: Int? = null,
        val percentage: String? = null,
        val deadline: String? = null,
        val description: TextWithLanguageType? = null
    ) : Parcelable

    /**
     * An HotelOffer-related object as returned by the HotelOffers API.
     * @see com.amadeus.shopping.HotelOffers.get
     */
    @Parcelize
    @JsonClass(generateAdapter = true)
    data class PaymentPolicy internal constructor(
        val creditCards: List<String>? = null,
        val method: String? = null
    ) : Parcelable
}