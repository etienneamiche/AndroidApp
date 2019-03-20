# Projet Application mobile 

Ce fichier contient les information relative au projet mobile de 3eme année. Cette application est une app Météo qui donne le temps en fonction de la position géographique de l'individu ainsi que les prévisions sur les 5 jours à venir

# Spécificités techniques 

 - Architecture MVC
 - RX Java
 - Localisation
 - RecycleView & Adapter
 - Design Patern
	- Adapter (RecycleView)
	- Observable & Facade (Interface Retrofit)
 - Fragments & Fragments Manager
 - Retrofit
 - Picasso
 - Gson
 - Toast
 - GitFlow
 - Librairie Externe Progress Circle
 - Dexter 
 
# Présentation 

## Vue general


![enter image description here](https://lh3.googleusercontent.com/mLraTBTgP5Wg-qoyf1Vl0zDFpeKm-LidHpPf6XC_SDl4C5zwESUkZh8JfnAad0c1gDnFk-vVf2ZI)

Une partie pour la météo actuelle et une liste des prévision météo sur 5 jours

> **note**: Les donnée peuvent être rafraîchie avec un swipe vers le bas

## Météo actuelle

![enter image description here](https://lh3.googleusercontent.com/BqXGJmzNHg62PH_QozSlEu46aMGskfysHGyhRgPm8pQB-gI_cZjN7E7WTOe6mE0kVDWxCFw2J6pb)

Dans cette partie on peut avoir les informations sur :
 
 - La ville en fonction de la localisation
 - Les coordonnées géographique
 - Une icone pour un feedback visuel sur le temps
 - Une description sur le type de temps
 - La température
 - Des information sur le vent (vitesse et degrés)
 - L'humidité de l'air

## Prévision météo

![enter image description here](https://lh3.googleusercontent.com/xN3ZgEw7Si4_0fhirHxLPnDo_4yjtbHVggiMPxyC2yK8Ao-QoL-EnwSSRwB0DdJ1Uy-g9eGqcyZq)

Dans cette partie on peut avoir accès au prévisions météo sur les 5 jour a venir en fonction de la position toute les 3 heures. C'est une recycleview horizontale

- La date et l'heure
- Une icone pour un feedback visuel sur le temps
- La température
- Une description sur le type de temps


# Structure du code Java
```
+ -- activity
|	+-- ForecastWeatherController.java
|	+-- ForecastWeatherFragment.java
|	+-- MainActivity.java
|	+-- TodayWeatherController.java
|	+-- TodayWeatherFragment.java
+ -- adapter
|	+-- WeatherForecastAdapter.java
+ -- common
|	+-- Common.java
+ -- model
|	+-- City.java
|	+-- Clouds.java
|	+-- Coord.java
|	+-- Main.java
|	+-- MyList.java
|	+-- Rain.java
|	+-- Results.java
|	+-- Sys.java
|	+-- Weather.java
|	+-- WeatherForecastResults.java
|	+-- Wind.java
+ -- retroFit
|	+-- IOpenWeather.java
|	+-- RetroFitClient.java
   ```






