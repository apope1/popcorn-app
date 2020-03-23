## __PopcornApp - Android__

This Popcorn App excercise is an app that allows users to search for movies from an online movie database.

For the development of this app I use Kotlin as the programming language.

I used Koin for dependency injection, Retrofit and Moshi for API calls and JSON parsing.

For the "front-end" part I used Glide for image processing, the android Navigation component for 
navigation around the app (following the "single Activity" architecture), and a library that we use 
internally inside out company for easing of developing Recycler View logic.

The main problems I came upon while developing this app were that the assets provided had very big 
sizes and also the dimensions on Invision were incredilby huge so I had to use some internal downscaled 
sizes (following Material Guidelines of course).

Another problem I came upon was that some data we could see on Invision was not available on the provided 
API so I decided to not create some random values so I just did not display anything in their place.