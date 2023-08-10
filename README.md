# Fantasy Sword Duels

Think *magic* infused, *sword duel* themed, RPG version of Rock, Paper, Scissors, 
where you progress through levels, manage attribute points, and develop combat strategies. 
The combat resolution logic takes multiple variables into account, such as action type, 
player attributes, and chances of unique scenarios being activated.
Information about each play-through (e.g. combat selections, levels completed, and attribute 
distribution) is stored in a database for analysis.

**How the game works:**

At the beginning of the game, you are given points to distribute between attributes of health, speed, and strength. 
The attributes play different roles in combat encounters. For example, strength is a variable in calculating damage, 
while speed plays a role in determining whether you land certain types of attacks before your opponent. 
After distribution of attribute points, you face your first opponent. A round of combat consists of each side choosing a move and 
the game determining the resolution given the player selections and other relevant factors. Rounds of combat continue until one of the combatants has lost all of 
their health points. If you are victorious, you progress to the next opponent - facing one per level for 
a total of ten levels. At every other level, there is an upgrade to help you on your journey.

The attribute points of your opponents are determined by combining a baseline of points relative to the current level 
with a spread of randomly determined points (so while the baseline attribute points of your opponents increase with 
each level, there is a window of variability with the second layer of points that prevents a completely linear 
progression of difficulty). 

While certain combat strategies are complemented by particular attribute point distributions, there is a deliberate 
setup to the combat resolution logic such that every move has a potential weakness.

---

**Technologies**: Java, Spring framework, PostgreSQL

**Future goal(s)**: 
Create a *front end* to:
 - play the game online
 - display a scoreboard

**Future fantasies**:
 - *difficulty levels* - perhaps enemies learning from player actions
 - *compare combat and attribute point allocation strategies* across players of the game

