# planerv2-client

## Introduction

This document contains information on how to use planer-v2 application. The application uses QBit libraries (https://github.com/advantageous/qbit).

## Installation and running a demo 
planerv2-server:

1. Download 
```
git clone https://github.com/sajchania/planerv2.git
```
2. Change directory
```
cd planerv2 
```
3. Build project
```
gradle clean build
```
4. Run projet
```
gradle run
```
 
planerv2-client:

1. Download 
```
git clone https://github.com/sajchania/planerv2-client.git
```
2. Change directory
```
cd planerv2-client 

3. Build project
```
gradle clean build
```
4. Run projet
```
gradle run
```


## Functionalities
If You would like to try something different than the demo in the SimpleRestClient, You can use CLI:
```
curl -X POST http://localhost:6060/services/planer/building/add/BUILDING_NAME/NUMBER_OF_ROOMS
```
Here we create building BUILDING_NAME with NUMBER_OF_ROOMS rooms.If You want to remove the building use:
```
curl -X DELETE http://localhost:6060/services/planer/building/remove/BUILDING_NAME
```
If You want to find if the building was created:
```
curl -X GET http://localhost:6060/services/planer/building/get/BUILDING_NAME
```
You can't modify the building. Each building needs to have unique name. If You want to see all buildings, do this:
```
curl -X GET http://localhost:6060/services/planer/buildings
```
If You want to see the details of a particular room:
```
curl -X GET http://localhost:6060/services/planer/building/details/BUILDING_NAME/ROOM_NUMBER
```
To create a reservation You need to provide all details:
```
curl -X POST http://localhost:6060/services/planer/reserve/BUILDING_NAME/ROOM_NUMBER/START_TIME/END_TIME
sampleTimeDate = 2018-08-27T12:00
```
To see available reservations for a particular room:
```
curl -X GET http://localhost:6060/services/planer/available/building/room/BUILDING_NAME/ROOM_NUMBER
```
To see all available reservations in a particular building:
```
curl -X GET http://localhost:6060/services/planer/available/building/hotel
```
## Interpretation of results
After each request You will receive a message. There are SUCCESS messages and ERROR messages.
```
"SUCCESS: RoomReservation [id=1, reservation=Reservation [startTime=2018-08-27T12:00:00.000, endTime=2018-08-27T13:00:00.000], room=2, isSuccesful=true, errorMsg=null]"
"ERROR: RoomReservation [id=2, reservation=Reservation [startTime=2018-08-27T12:00:00.000, endTime=2018-08-27T13:00:00.000], room=2, isSuccesful=false, errorMsg=Room is already booked]"
```
The application returns time intervals. The application works only beetwen current date and future date which is equal to current date + 1000 years. This is a sample output:
```
"[AvailableReservation [startTime=2017-08-29T01:03:46.982, endTime=2018-09-27T12:00:00.000], AvailableReservation [startTime=2018-09-27T13:00:00.000, endTime=3017-08-29T01:03:46.982]]"
```

## Time issues
The project was time consuming. I had only 4 days for the release. Due to the fact, I have faced with several issues:
1. I did this project on Intel(R) Core(TM)2 Duo CPU     P8700  @ 2.53GHz. The thermal paste wasn't replaced since day of a purchase (2009). I had a lot of spikes and freezes during last 4 days.
2. My neighbours have decided to renovate their flat. I couldn't focus most of the time.
3. I had no experience with JavaScript before the task. I guess that I spent too much time trying to create user-friendly GUI.

## Possible questions
- Why there is no web interface?
  The web interface was written in JavaScript. It wasn't working properly, so it was replaced with a QBit client. 
- Why there is a QBit library instead of Spring?
> I tried to use Spring first but due to issues with my PC I have decided to use something else. High performance micro-service library souds better for me. 
- Why QBit?
 It was said that is is fast. You need to click only once to run a server. And Tomcat + Spring + Eclipse + Firefox takes too much resources on my PC.
- Why there is no database / cluster?
  I had only 4 days to solve the task
- Why there is no default REST DELETE method in QBit's HttpClientBuilder?
 ?! 













