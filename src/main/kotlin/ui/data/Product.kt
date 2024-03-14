package ui.data

import java.math.BigDecimal

enum class Product(val title: String, val description: String, val price: BigDecimal) {
    BACKPACK(
        "Sauce Labs Backpack",
        "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
        BigDecimal("29.99")
    ),
    BIKE_LIGHT(
        "Sauce Labs Bike Light",
        "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included.",
        BigDecimal("9.99")
    ),
    BOLT_T_SHIRT(
        "Sauce Labs Bolt T-Shirt",
        "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.",
        BigDecimal("15.99")
    ),
    FLEECE_JACKET(
        "Sauce Labs Fleece Jacket",
        "It's not every day that you come across a midweight quarter-zip fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.",
        BigDecimal("49.99")
    ),
    ONESIE(
        "Sauce Labs Onesie",
        "Rib snap infant onesie for the junior automation engineer in development. Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel.",
        BigDecimal("7.99")
    ),
    T_SHIRT_RED(
        "Test.allTheThings() T-Shirt (Red)",
        "This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.",
        BigDecimal("15.99")
    ),
}
