package com.android.nbc_c_assignment1

interface FlightInterfaceD {
    val invisibleFlightInfo: InvisibleFlightInfo
    var time: Time
    val flight: Flight
    val checkInCounter: List<String> // 체크인 카운터
    val gate: Int // 탑승구, 출구
    var state: Int // 현황

    fun setTime(changedTime: String)
    fun changeState(flightState: FlightState)

}

interface PrintFlightInterfaceD {
    fun printItem()
}

// 상속 합성 위임

class FlightD(
    override val invisibleFlightInfo: InvisibleFlightInfo,
    override var time: Time,
    override val flight: Flight,
    override val checkInCounter: List<String>,
    override val gate: Int,
    override var state: Int
) : FlightInterfaceD {

    override fun setTime(changedTime: String) {
        time.estimatedTime = changedTime
    }

    override fun changeState(flightState: FlightState) {
        state = flightState.ordinal
    }

}

class DepartureFlightD(flight: FlightD, private val destination: String): FlightInterfaceD by flight, PrintFlightInterfaceD {
    override fun printItem() {
        println("${time.scheduledTime}\t${time.estimatedTime}\t$destination\t${flight.flightNum}\t${flight.codeShare}\t${checkInCounter}\t$gate\t$state")
    }
}

class ArrivalFlightD(flight: FlightD, private val from: String): FlightInterfaceD by flight, PrintFlightInterfaceD  {
    override fun printItem() {
        println("${time.scheduledTime}\t${time.estimatedTime}\t$from\t${flight.flightNum}\t${flight.codeShare}\t${checkInCounter}\t$gate\t$state")
    }
}