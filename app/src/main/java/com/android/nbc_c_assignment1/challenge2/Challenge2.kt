package com.android.nbc_c_assignment1.challenge2

fun challenge2First() {
    val a = SubscriberA()
    val b = SubscriberB()
    a.subscribe()
    b.subscribe()
    println("=-=-=-=-=-=-=-=-=-=-=-=-=")
    val news = NewsLetterPractice()
    news.register(SubscriberPractice())
    news.register(SubscriberPractice())
    news.register(SubscriberPractice())
    news.publish()
    news.changeNews("속보입니다!!!")
    news.register(SubscriberPractice())
    news.changeNews("거짓말입니다.")
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
    val redTea = RedTea()
    redTea.showInfo()
    MilkDecorator(redTea).showInfo()
}