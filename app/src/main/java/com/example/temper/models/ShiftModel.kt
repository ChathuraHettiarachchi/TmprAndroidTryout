package com.example.temper.models

import com.google.gson.annotations.SerializedName

data class ShiftModel(

	@field:SerializedName("data")
	val data: List<Shift?>? = null
)

data class Rating(

	@field:SerializedName("average")
	val average: Double? = null,

	@field:SerializedName("count")
	val count: Int? = null
)

data class ReportTo(

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("details")
	val details: String? = null
)

data class Shift(

	@field:SerializedName("starts_at")
	val startsAt: String? = null,

	@field:SerializedName("variable_pricing")
	val variablePricing: Boolean? = null,

	@field:SerializedName("distance")
	val distance: String? = null,

	@field:SerializedName("factoring_allowed")
	val factoringAllowed: Boolean? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("average_estimated_earnings_per_hour")
	val averageEstimatedEarningsPerHour: AverageEstimatedEarningsPerHour? = null,

	@field:SerializedName("has_substituted_openings")
	val hasSubstitutedOpenings: Boolean? = null,

	@field:SerializedName("earnings_per_hour")
	val earningsPerHour: EarningsPerHour? = null,

	@field:SerializedName("duration")
	val duration: Int? = null,

	@field:SerializedName("start_time_earlier_variation")
	val startTimeEarlierVariation: Int? = null,

	@field:SerializedName("enable_auto_accept_recent_freelancers")
	val enableAutoAcceptRecentFreelancers: Boolean? = null,

	@field:SerializedName("is_auto_accepted_when_applied")
	val isAutoAcceptedWhenApplied: Boolean? = null,

	@field:SerializedName("earliest_possible_start_time")
	val earliestPossibleStartTime: String? = null,

	@field:SerializedName("links")
	val links: Links? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("ends_at")
	val endsAt: String? = null,

	@field:SerializedName("tempers_needed")
	val tempersNeeded: Int? = null,

	@field:SerializedName("chance_of_success")
	val chanceOfSuccess: Boolean? = null,

	@field:SerializedName("latest_possible_end_time")
	val latestPossibleEndTime: String? = null,

	@field:SerializedName("archived_at")
	val archivedAt: String? = null,

	@field:SerializedName("start_time_later_variation")
	val startTimeLaterVariation: Int? = null,

	@field:SerializedName("time_variation_message")
	val timeVariationMessage: String? = null,

	@field:SerializedName("open_positions")
	val openPositions: Int? = null,

	@field:SerializedName("is_flexible")
	val isFlexible: Boolean? = null,

	@field:SerializedName("end_time_earlier_variation")
	val endTimeEarlierVariation: Int? = null,

	@field:SerializedName("high_chance_score")
	val highChanceScore: Int? = null,

	@field:SerializedName("recurring_shift_schedule")
	val recurringShiftSchedule: RecurringShiftSchedule? = null,

	@field:SerializedName("applications_count")
	val applicationsCount: Int? = null,

	@field:SerializedName("cancellation_policy")
	val cancellationPolicy: Int? = null,

	@field:SerializedName("job")
	val job: Job? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("end_time_later_variation")
	val endTimeLaterVariation: Int? = null
)

data class TotalCostPerHour(

	@field:SerializedName("amount")
	val amount: Double? = null,

	@field:SerializedName("currency")
	val currency: String? = null
)

data class ReportAtAddress(

	@field:SerializedName("geo")
	val geo: Geo? = null,

	@field:SerializedName("number")
	val number: String? = null,

	@field:SerializedName("country")
	val country: Country? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("street")
	val street: String? = null,

	@field:SerializedName("extra")
	val extra: String? = null,

	@field:SerializedName("links")
	val links: Links? = null,

	@field:SerializedName("region")
	val region: String? = null,

	@field:SerializedName("number_with_extra")
	val numberWithExtra: String? = null,

	@field:SerializedName("line2")
	val line2: String? = null,

	@field:SerializedName("zip_code")
	val zipCode: String? = null,

	@field:SerializedName("line1")
	val line1: String? = null
)

data class EarningsPerHour(

	@field:SerializedName("amount")
	val amount: Double? = null,

	@field:SerializedName("currency")
	val currency: String? = null
)

data class Project(

	@field:SerializedName("archived_at")
	val archivedAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("client")
	val client: Client? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class Country(

	@field:SerializedName("iso3166_1")
	val iso31661: String? = null,

	@field:SerializedName("human")
	val human: String? = null
)

data class Geo(

	@field:SerializedName("lon")
	val lon: Double? = null,

	@field:SerializedName("lat")
	val lat: Double? = null
)

data class SkillsItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class AppearancesItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)

data class NameTranslation(

	@field:SerializedName("en_GB")
	val enGB: String? = null,

	@field:SerializedName("nl_NL")
	val nlNL: String? = null
)

data class AverageEstimatedEarningsPerHour(

	@field:SerializedName("amount")
	val amount: Double? = null,

	@field:SerializedName("currency")
	val currency: String? = null
)

data class Links(

	@field:SerializedName("get_directions")
	val getDirections: String? = null,

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("skills")
	val skills: String? = null,

	@field:SerializedName("appearances")
	val appearances: String? = null,

	@field:SerializedName("hero_380_image")
	val hero380Image: String? = null,

	@field:SerializedName("self")
	val self: String? = null,

	@field:SerializedName("client")
	val client: String? = null,

	@field:SerializedName("job_category")
	val jobCategory: String? = null,

	@field:SerializedName("report_at_address")
	val reportAtAddress: String? = null,

	@field:SerializedName("hero_image")
	val heroImage: String? = null,

	@field:SerializedName("thumb_image")
	val thumbImage: String? = null,

	@field:SerializedName("job")
	val job: String? = null,

	@field:SerializedName("match_aggregates")
	val matchAggregates: String? = null
)

data class Client(

	@field:SerializedName("factoring_allowed")
	val factoringAllowed: Boolean? = null,

	@field:SerializedName("rating")
	val rating: Rating? = null,

	@field:SerializedName("registration_id")
	val registrationId: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("factoring_payment_term_in_days")
	val factoringPaymentTermInDays: Int? = null,

	@field:SerializedName("blocked_minutes_before_shift")
	val blockedMinutesBeforeShift: String? = null,

	@field:SerializedName("average_response_time")
	val averageResponseTime: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("registration_name")
	val registrationName: String? = null,

	@field:SerializedName("links")
	val links: Links? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("allow_temper_trial")
	val allowTemperTrial: Boolean? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class Category(

	@field:SerializedName("internalId")
	val internalId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("name_translation")
	val nameTranslation: NameTranslation? = null,

	@field:SerializedName("links")
	val links: Links? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null
)

data class Job(

	@field:SerializedName("archived_at")
	val archivedAt: String? = null,

	@field:SerializedName("dress_code")
	val dressCode: String? = null,

	@field:SerializedName("project")
	val project: Project? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("tips")
	val tips: Boolean? = null,

	@field:SerializedName("reference")
	val reference: String? = null,

	@field:SerializedName("skills")
	val skills: List<SkillsItem?>? = null,

	@field:SerializedName("appearances")
	val appearances: List<AppearancesItem?>? = null,

	@field:SerializedName("is_agency")
	val isAgency: Boolean? = null,

	@field:SerializedName("report_to")
	val reportTo: ReportTo? = null,

	@field:SerializedName("extra_briefing")
	val extraBriefing: String? = null,

	@field:SerializedName("links")
	val links: Links? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("category")
	val category: Category? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("report_at_address")
	val reportAtAddress: ReportAtAddress? = null
)

data class RecurringShiftSchedule(

	@field:SerializedName("tempers_needed")
	val tempersNeeded: Int? = null,

	@field:SerializedName("starts_at")
	val startsAt: String? = null,

	@field:SerializedName("saturday")
	val saturday: Boolean? = null,

	@field:SerializedName("is_active")
	val isActive: Boolean? = null,

	@field:SerializedName("variable_pricing")
	val variablePricing: Boolean? = null,

	@field:SerializedName("total_cost_per_hour")
	val totalCostPerHour: TotalCostPerHour? = null,

	@field:SerializedName("thursday")
	val thursday: Boolean? = null,

	@field:SerializedName("commitment_preferred")
	val commitmentPreferred: Boolean? = null,

	@field:SerializedName("shift_group_id")
	val shiftGroupId: String? = null,

	@field:SerializedName("earnings_per_hour")
	val earningsPerHour: EarningsPerHour? = null,

	@field:SerializedName("sunday")
	val sunday: Boolean? = null,

	@field:SerializedName("tuesday")
	val tuesday: Boolean? = null,

	@field:SerializedName("schedule_start_date")
	val scheduleStartDate: String? = null,

	@field:SerializedName("wednesday")
	val wednesday: Boolean? = null,

	@field:SerializedName("friday")
	val friday: Boolean? = null,

	@field:SerializedName("cancellation_policy")
	val cancellationPolicy: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("ends_at")
	val endsAt: String? = null,

	@field:SerializedName("schedule_end_date")
	val scheduleEndDate: String? = null,

	@field:SerializedName("monday")
	val monday: Boolean? = null
)
