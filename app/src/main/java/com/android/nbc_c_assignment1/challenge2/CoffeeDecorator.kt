package com.android.nbc_c_assignment1.challenge2

interface Drink {
    val price: Int
    fun showInfo() = println("$this => 가격 : ${price}원")
}

interface Coffee: Drink

interface CoffeeDecorator: Coffee

interface Tea: Drink

interface TeaDecorator: Tea

class SimpleCoffee: Coffee {
    override val price: Int = 2000
    override fun toString(): String = "커피"
}

class Americano: Coffee {
    override val price: Int = 3500
    override fun toString(): String = "아메리카노"
}

class MilkDecorator(private val coffee: Drink): CoffeeDecorator, TeaDecorator {
    override val price: Int
        get() = 500 + coffee.price
    override fun toString(): String = "우유 넣은, $coffee"
}

class SyrupDecorator(private val coffee: Coffee): CoffeeDecorator {
    override val price: Int
        get() = 300 + coffee.price
    override fun toString(): String = "시럽 넣은, $coffee"
}

class WhipCreamDecorator(private val coffee: Coffee): CoffeeDecorator {
    override val price: Int
        get() = 1000 + coffee.price
    override fun toString(): String = "휘핑 크림 얹은, $coffee"
}

class RedTea: Tea {
    override val price: Int = 4000
    override fun toString(): String = "홍차"
}
