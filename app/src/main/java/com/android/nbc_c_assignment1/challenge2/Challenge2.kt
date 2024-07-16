package com.android.nbc_c_assignment1.challenge2

fun challenge2First() {
    val news = NewsLetterPractice()
    news.subscribe(SubscriberPractice())
    news.subscribe(SubscriberPractice())
    news.subscribe(SubscriberPractice())
    news.publish()
    news.changeNews("속보입니다!!!")
    news.subscribe(SubscriberPractice())
    news.changeNews("거짓말입니다.")
    news.unsubscribe(2)
    news.unsubscribe(1)
    news.changeNews("죄송합니다.")
}

fun challenge2Second() {
    val coffee = SimpleCoffee()
    coffee.showInfo()
    val milkCoffee = MilkDecorator(coffee)
    milkCoffee.showInfo()
    val syrupCoffee = SyrupDecorator(milkCoffee)
    syrupCoffee.showInfo()
    val finalCoffee = WhipCreamDecorator(SyrupDecorator(syrupCoffee))
    finalCoffee.showInfo()
    SyrupDecorator(Americano()).showInfo()
    val redTea = BlackTea()
    redTea.showInfo()
    MilkDecorator(redTea).showInfo()
}