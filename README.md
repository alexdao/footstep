Footstep
==========

Android app to automatically track carbon emissions based on phone sensor data. 1st Place winner of the Energy and Environment Track at HackDuke, a hackathon hosted by Duke University. 

### About
Created by Alex Dao, Callie Mao, and Danny Oh.

If every commuter were to decrease their carbon dioxide emissions by just 10%, there would be a decrease in carbon dioxide emissions by hundreds of pounds per person. Traditionally, tracking carbon emissions required users to actively record their activities. 

In order to raises awareness of day-to-day subconscious decisions that detrimentally affect the environment. Footstep tracks accelerometer, gyroscope, and GPS location data points from your phone. This data forms a probabilistic model on which mode of transportation the phone is most likely to be experiencing (staying still, walking, biking, car, etc). When a "trip" is detected, Footstep will automatically run in the background and track GPS location until a final destination is reached. It will then intelligently stop recording and organize trips based on start/end time and location. 

The results are displayed on a day-to-day basis for a user to view, and each route is accompanied by alternative walking/biking routes that could be taken. Footstep in the future can be extended to intelligently predict when a user will be taking a common commute route and immediately suggest an alternative walking/biking route that matches with a user’s schedule/linked calendar.

### API
Built with the help of Google's [DetectedActivity API](https://developers.google.com/android/reference/com/google/android/gms/location/DetectedActivity)


License
--------

    Copyright 2015 Alex Dao, Callie Mao, and Danny Oh.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.