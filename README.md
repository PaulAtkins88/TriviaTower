# TriviaTower
Trivia game where if you get the answer correct the little dude will climb the tower. Work in progress.

# 25/05/2019 - Should be studying for exam
## Sound! Gradle! (gradle by accident trying to get LWJGL to work)
Well here it is folks, its coming together nicely, Sounds are implemented now using Slick2D libraries alongside LWJGL (libraries I used in res folder). Sound works in two seperate hashMaps 1 for music, 1 for sound. sounds are implemented by "put"ing a new key in as a string and then because the class is static you can call from any other class AudioPlayer.getSound("mySound").play(); ... if there is a better way let me know!

# 24/05/2019 - night shift work again
## creating my first branch
This is another major update, changed the data structure now to 1 single csv file (thanks @jeff) and it works well. Can update more trivia questions easily now in the data.csv by using question,correctAnswer,SimilarAnswer1,SimilarAnswer2 
G'nite!!

# 23/05/2019 - night shift at work
## another major update
Too many changes to mention. Updated sprite sheets. added characters. drew the tower. implemented a timer for questions. the dude climbs. tidied up code a bit (more to be done here). Tonight (as in night shift #2) might pop in some audio stuff.. Try and figure out WHY the dude wont render in front of the tower or might just redraw him to a profile view and have him climb the side of the tower.
