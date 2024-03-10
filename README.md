## What it does
Our app allows independent/startup farmers to effectively manage their fields, keep track of their inventory, keep tabs on the weather, and manage their finances. These are all time-consuming and error-prone tasks that may be detrimental to a new farmer starting, so streamlining, aggregating, and pushing these features into their phone will increases efficiency and success.

## How we built it
We built the main app using Android Studio, utilising a mix of Kotlin and Java code. Initially, we wanted to use a skeleton, but it was shortly discovered that it was much better for us to build the app from the ground up, and learn all of the features along the way.

## Challenges we ran into
One main challenge we ran into was around 2 am, when git didn't recognise the last 7 commits when merging into main. After **~15mins** and some panicked terminal work, we managed to revert the repo to what it was before, and merge properly.

Another challenge was getting the database up and running, and a lot of time was spent trying to run local databases instead of an online one. Eventually, we made the switch, and even then there was a lot of refactoring of custom classes to make them work and parse into the database. 

One last challenge was trying to integrate the weather API into the app. Having not worked with APIs before, it was a learning curve but in the end, we managed to format everything properly and display it. 

## Accomplishments that we're proud of
As a team we are super proud of everything we've done, getting so much done in such little time. Almost _everything_ coded today was new to us at the time of coding, so we have learnt a lot to do with android programming, firebase and git.

## What's next for CropMate
The next steps go in different directions
- Adding **user functionality**, so you can log in on other devices and have all your information saved
- Creating **interactive widgets** on the home screen so you can see at a glance the status of everything
- Introducing *progress bars* on crop growth and inventory to gamify the experience
