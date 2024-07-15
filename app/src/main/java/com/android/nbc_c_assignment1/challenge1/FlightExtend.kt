package com.android.nbc_c_assignment1.challenge1

/*

- 1단계
    - 전광판 보고 Flight 객체 만들기
    - 대용량 / 장기간 운영할때 flight객체에는 무엇이 필요한지 고민 해보기
    - 보이지 않는 변수 까지 만들기
- 2단계
    - Arrival 도착 전광판과 Departure 출발 전광판을 만든다면 객체지향 설계 해보기
    - 객체 안의 Data들을 어떻게 묶을수 있는지 고민 해보기

    ETD: 출발예정시간
    ETA: 도착예정시간

    STD: 스케줄상 출발예정시간
    STA: 스케줄상 도착예정시간

    Departure: 출발지
    Arrival: 도착지

    FlightNum: 편명
    Codeshare: 공동운항

    CheckInCounter: 체크인
    Gate: 탑승구
    State: 현황

 */

enum class FlightState(state: String) {
    BOARDING("탑승 중"),
    DELAY("지연"),
    CANCEL("결항"),
    ARRIVE("도착")
}

data class InvisibleFlightInfo(
    val id: String, // 해당 아이템의 번호
    val airplaneUniqueCode: String, // 비행기 코드
    val airlineCode: String,// 항공사 코드
    val createdTime: String, // 아이템 최초 생성 시간
    val updatedTime: MutableList<String> // 아이템 업데이트 시간
)

data class Time (
    val scheduledTime: String, // 예정
    var estimatedTime: String = "", // 변경(최종)
)

data class Flight (
    val flightNum: String, // 편명
    val codeShare: List<String> // 공동 운항
)

interface FlightInterface {
    val invisibleFlightInfo: InvisibleFlightInfo
    var time: Time
    val flight: Flight
    val checkInCounter: List<String> // 체크인 카운터
    val gate: Int // 탑승구, 출구
    var state: FlightState // 현황

    fun updated()
    fun setTime(changedTime: String)
    fun changeState(flightState: FlightState)
    fun printItem()

}

class DepartureFlight(
    override val invisibleFlightInfo: InvisibleFlightInfo,
    override var time: Time,
    override val flight: Flight,
    override val checkInCounter: List<String>,
    override val gate: Int,
    override var state: FlightState,
    private val destination: String,
): FlightInterface {
    override fun updated() {
        invisibleFlightInfo.updatedTime += "11:11"
    }

    override fun setTime(changedTime: String) {
        time.estimatedTime = changedTime
        updated()
    }

    override fun changeState(flightState: FlightState) {
        state = flightState
        updated()
    }

    override fun printItem() {
        println("${time.scheduledTime}\t${time.estimatedTime}\t$destination\t${flight.flightNum}\t${flight.codeShare}\t${checkInCounter}\t$gate\t${state.name}")
    }

}

class ArrivalFlight(
    override val invisibleFlightInfo: InvisibleFlightInfo,
    override var time: Time,
    override val flight: Flight,
    override val checkInCounter: List<String>,
    override val gate: Int,
    override var state: FlightState,
    private val from: String,
) : FlightInterface {
    override fun updated() {
        invisibleFlightInfo.updatedTime += "11:11"
    }

    override fun setTime(changedTime: String) {
        time.estimatedTime = changedTime
    }

    override fun changeState(flightState: FlightState) {
        state = flightState
    }

    override fun printItem() {
        println("${time.scheduledTime}\t${time.estimatedTime}\t$from\t${flight.flightNum}\t${flight.codeShare}\t${checkInCounter}\t$gate\t${state.name}")
    }

}

