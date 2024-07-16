package com.android.nbc_c_assignment1.challenge2

interface ObserverPractice {
    fun onReceive(str: String)
}

class SubscriberPractice: ObserverPractice {
    override fun onReceive(str: String) = println("$str \n")
}

class NewsLetterPractice {
    // 구독자 관리
    private val subscribers: MutableList<ObserverPractice> = mutableListOf()

    // 뉴스 내용
    private var str = "구독해주셔서 감사합니다."

    // 뉴스 내용 변경
    fun changeNews(s: String) {
        str = s
        publish()
    }

    // 구독자 추가
    fun subscribe(subscriber: ObserverPractice) = subscribers.add(subscriber)

    fun unsubscribe(n: Int) = subscribers.removeAt(n)

    // 뉴스 발행
    fun publish() {
        for(i in 0..<subscribers.size) {
            println("${i+1} 번 구독자가 받은 내용 : ")
            subscribers[i].onReceive(str)
        }
    }
}

