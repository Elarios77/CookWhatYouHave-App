package com.example.cookwhatyouhave.domain.model

enum class MealCategory(val mealName: String, val apiParam:String?) {

    DESSERT("Dessert","Dessert"),
    VEGETARIAN("Vegetarian","Vegetarian"),
    SEAFOOD("Seafood","Seafood"),
    OTHER("Other",null)
}

val otherCategories = listOf(
    "Beef","Chicken","Pork","Lamb","Pasta",
    "Side","Starter","Vegan","Breakfast","Goat","Miscellaneous"
)