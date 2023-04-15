package application;

public class Meal {
int mealId;
String mealName;
String mealImg;

Meal(int id, String name, String img) {
	this.mealId = id;
	this.mealName = name;
	this.mealImg = img;
}

public int getMealID() {
	return this.mealId;
}

public void setMealID(int id) {
	this.mealId = id;
}

public String getMealName() {
	return this.mealName;
}

public void setMealID(String name) {
	this.mealName = name;
}

public String getMealImg() {
	return this.mealImg;
}

public void setMealImg(String img) {
	this.mealImg = img;
}

public String toString() {
	return "The meal name is "+this.mealName+" with id "+this.mealId;
}
}