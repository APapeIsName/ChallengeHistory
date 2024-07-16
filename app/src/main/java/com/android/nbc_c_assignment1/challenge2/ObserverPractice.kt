package com.android.nbc_c_assignment1.challenge2

interface ObserverPractice {
    fun onReceive(str: String)
}

class SubscriberPractice: ObserverPractice {
    override fun onReceive(str: String) = println("$str \n")
}

class NewsLetterPractice {
    // 구독자 관리
    private val subscribers: MutableMap<Int, ObserverPractice> = mutableMapOf()

    // 뉴스 내용
    private var str = "구독해주셔서 감사합니다."
    private var idIndex = 0

    // 뉴스 내용 변경
    fun changeNews(s: String) {
        str = s
        publish()
    }

    // 구독자 추가
    fun subscribe(subscriber: ObserverPractice) = subscribers.put(idIndex++, subscriber)

    fun unsubscribe(id: Int) = subscribers.remove(id-1)
    // id 추가

    // 뉴스 발행
    fun publish() {
        for(sub in subscribers) {
            print("${sub.key + 1}번 구독자 수신: ")
            sub.value.onReceive(str)
        }
        println("----=----=----=----\n")
    }
}

