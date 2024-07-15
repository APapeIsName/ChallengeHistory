package com.android.nbc_c_assignment1.challenge2

interface Observer {
    fun onReceive(news: String)
}

interface Subscriber {
    fun subscribe()
}

class NewsLetter(private var observer: Observer) {
    fun publish() {
        val news = "2024/07/15 소식입니다."
        observer.onReceive(news)
    }
}

class SubscriberA : Observer, Subscriber {
    override fun onReceive(news: String) {
        println("구독자 A가 신문을 받았습니다.")
        println("신문 내용: $news")
    }
    override fun subscribe() {
        println("구독자 A가 구독을 시작합니다.")
        val newsLetter = NewsLetter(this)
        newsLetter.publish()
    }
}

class SubscriberB : Subscriber {

    override fun subscribe() {
        println("구독자 B가 구독을 시작합니다.")
        NewsLetter(object : Observer {
            override fun onReceive(news: String) {
                println("구독자 B가 신문을 받았습니다.")
                println("신문 내용: $news")
            }
        }).publish()
    }

}