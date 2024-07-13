package com.android.nbc_c_assignment1

import kotlin.random.Random

val extendList: List<FlightInterface> = listOf(
    DepartureFlight(
        invisibleFlightInfo = InvisibleFlightInfo(
            "KOREA123",
            "EAGLE444",
            "KE",
            "09:49",
            mutableListOf("09:52", "09:55")
        ),
        time = Time("10:40", "10:50"),
        flight = Flight("KE1089", listOf("AS3300")),
        checkInCounter = listOf("A", "B", "C"),
        gate = 248,
        state = FlightState.DELAY,
        destination = "도쿄"
    ),
    DepartureFlight(
        invisibleFlightInfo = InvisibleFlightInfo(
            "KOREA346",
            "EAGLE221",
            "KE",
            "17:40",
            mutableListOf("18:33")
        ),
        time = Time("22:40", "23:50"),
        flight = Flight("KE1077", listOf("NY2321")),
        checkInCounter = listOf("A", "B", "C", "D", "E"),
        gate = 266,
        state = FlightState.BOARDING,
        destination = "뉴욕"
    ),
    ArrivalFlight(
        invisibleFlightInfo = InvisibleFlightInfo(
            "CHINA113",
            "BEE409",
            "CE",
            "23:50",
            mutableListOf("00:44")
        ),
        time = Time("06:10", "07:00"),
        flight = Flight("CN888", listOf("CN331", "TW5959")),
        checkInCounter = listOf("C", "D"),
        gate = 251,
        state = FlightState.CANCEL,
        from = "베이징"
    )
)

val delegateList: List<PrintFlightInterfaceD> = listOf(
    DepartureFlightD(
        FlightD(
            invisibleFlightInfo = InvisibleFlightInfo(
                "KOREA123",
                "EAGLE444",
                "KE",
                "09:49",
                mutableListOf("09:52", "09:55")
            ),
            time = Time("10:40", "10:50"),
            flight = Flight("KE1089", listOf("AS3300")),
            checkInCounter = listOf("A", "B", "C"),
            gate = 248,
            state = FlightState.DELAY.ordinal,
        ),
        destination = "도쿄"
    ),
    DepartureFlightD(
        FlightD(
            invisibleFlightInfo = InvisibleFlightInfo(
                "KOREA346",
                "EAGLE221",
                "KE",
                "17:40",
                mutableListOf("18:33")
            ),
            time = Time("22:40", "23:50"),
            flight = Flight("KE1077", listOf("NY2321")),
            checkInCounter = listOf("A", "B", "C", "D", "E"),
            gate = 248,
            state = FlightState.ARRIVE.ordinal,
        ),
        destination = "뉴욕"
    ),
    ArrivalFlightD(
        FlightD(
            invisibleFlightInfo = InvisibleFlightInfo(
                "CHINA113",
                "BEE409",
                "CE",
                "23:50",
                mutableListOf("00:44")
            ),
            time = Time("06:10", "07:00"),
            flight = Flight("CN888", listOf("CN331", "TW5959")),
            checkInCounter = listOf("C", "D"),
            gate = 251,
            state = FlightState.CANCEL.ordinal,
        ),
        from = "베이징"
    )
)

fun main() {
    println("예정=-=-=변경=-=-=도착지=-=-=-=-=-편명-=-=-=-=-=체크인=-=-=-=탑승구-현황")
    extendList.forEachIndexed { index, it ->
        it.printItem()
        if (index == 1) println("\n예정=-=-=변경=-=-=출발지=-=-=-=-=-=-편명-=-=-=-=-=-=체크인=-=탑승구-현황")
    }
    println("\n예정=-=-=변경=-=-=도착지=-=-=-=-=-편명-=-=-=-=-=체크인=-=-=-=탑승구-현황")
    delegateList.forEachIndexed { index, it ->
        it.printItem()
        if (index == 1) println("\n예정=-=-=변경=-=-=출발지=-=-=-=-=-=-편명-=-=-=-=-=-=체크인=-=탑승구-현황")
    }
}