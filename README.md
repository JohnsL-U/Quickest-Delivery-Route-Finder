# Quickest Delivery Route Finder
## Summary
This Java project finds the quickest delivery route for a store's delivery car. The delivery car must visit each house once to deliver the ordered items and then return to the store. The program reads the coordinates of the houses and the store from an input text file and calculates the shortest route.

## Features
Reads coordinates of houses and the store from a text file
Calculates the shortest route using a greedy algorithm
Plots the shortest route using Java Swing library

## Prerequisites
Java Development Kit (JDK) 8 or later
(Optional) An Integrated Development Environment (IDE) like Eclipse, IntelliJ IDEA, or Visual Studio Code

## Usage
* Clone the repository:
git clone https://github.com/your-username/quickest-delivery-route-finder.git
* Compile the Java files:
cd quickest-delivery-route-finder/src
javac ClientCode.java
* Run the ClientCode:
java ClientCode
* Follow the prompts to enter the name of the input text file containing the coordinates of houses and the store.

The program will output the shortest route and display a plot of the route.

# Input File Format
The input text file should contain the coordinates of houses and the store, separated by commas. Each line represents a location. The store's coordinates should have the keyword "Store" at the end of the line.
