package com.sapuseven.untis.adapters

class RoomFinderAdapterItem(val name: String, var loading: Boolean) : Comparable<RoomFinderAdapterItem> {
	var states = emptyList<Boolean>()
	//private var startDate: LocalDate = LocalDate.now()
	var hourIndex: Int = 0

	val isOutdated: Boolean
		get() = false // Days.daysBetween(startDate, LocalDate.now().withDayOfWeek(DateTimeConstants.MONDAY)).days != 0 // TODO: Rework this function

	fun getState(): Int {
		if (loading)
			return STATE_LOADING
		var i = 0
		var hours = 0
		while (hourIndex + i < states.size && !states[hourIndex + i]) {
			hours++
			i++
		}
		return hours
	}

	override fun compareTo(other: RoomFinderAdapterItem): Int {
		val state1 = getState()
		val state2 = other.getState()

		return when {
			state2 > state1 -> 1
			state1 > state2 -> -1
			else -> name.compareTo(other.name)
		}
	}

	override fun hashCode(): Int {
		return name.hashCode()
	}

	override fun equals(other: Any?): Boolean {
		return other is RoomFinderAdapterItem && other.name == name
	}

	override fun toString(): String {
		return name
	}

	companion object {
		const val STATE_OCCUPIED = 0
		const val STATE_FREE = 1
		const val STATE_LOADING = -1
	}
}
