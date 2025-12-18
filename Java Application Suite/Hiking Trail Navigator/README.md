[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/ZsZPG7iu)
# Programming I (420-G10-HR)

# Assignment 2 – Kat's Hiking Trail Navigator 🥾

Date assigned: October 10, 2025  
Due date: October 28, 2025 EOD (11:59PM)

---

## Learning Objectives

Upon successful completion of this assignment, the student will be able to:
1. Compile and execute a simple Java program.
2. Implement various types of Java loops.
3. Demonstrate proper use of version control and commit etiquette in Git/GitHub.
4. Read input from the keyboard and output to the screen.
5. Use String manipulation

---

## Marking Scheme

| Component                        | Mark |
|----------------------------------|------|
| Command validation & parsing     | 15   |
| Location rules & priorities      | 20   |
| Loop usage                       | 14   |
| String manipulation              | 14   |
| Control flow usage               | 12   |
| Output formatting & completeness | 10   |
| Git commit etiquette             | 5    |
| Readability & coding conventions | 10   |
| **Total**                        | 100  |



## Setup & Submission
- [ ] READ THE ENTIRE ASSIGNMENT FIRST before starting (including the marking scheme above), so you understand ALL requirements and the breakdown of the assignment.
- [ ] Clone and use the provided starter project in this repository. All code must be committed and pushed here.
- [ ] Before starting, RUN the starter project first! See what I have given you and how you can use it to start off.
- [ ] Commit and PUSH up your changes often! There are marks for proper Git commit etiquette. If you are working in multiple places, `git pull` your changes before continuing.
- [ ] Kat will review your code and provide feedback & final marks via the Feedback Pull Request after the assignment deadline.

---

## Kat's Hiking Trail Navigator 🥾

### Overview
Create a Java program called "Kat's Hiking Trail Navigator", which allows users to start at a trailhead and then hike along a trail. The trail has multiple paths, and the user must hike each segment and then decide which direction to go. The user is able to look around at their surroundings. The program keeps track of the directions the user has gone, and how many segments they have hiked.
You will use different types of loops, control flow, String manipulation, and formatted output to make the experience enjoyable for the user.

---

## Trail Navigator commands
The program will allow the user to enter the following commands: "help", "quit", "go X" (where X is either North, South, East, or West), or "look".

### Help
When the user types in this command, you should print out all the possible commands for the program. Ensure you format the output so it is easy to read. You also must give Tips so the user knows what to expect along the trail.

### Quit
Typing 'quit' should print out a goodbye message such as "Thanks for hiking!! Safe travels" and then immediately end the program.

### Go X
There are 4 possible directions to go: North, East, South, West.  
When the user types in `go x`, multiple things should happen:

1. Parse the command:
    - Read the whole line (ex. "go  NORTH "), trim spaces, and convert to lowercase
    - Split the verb ("go") from the argument ("north")
    - Validate the direction: only accept north, south, east, or west. If invalid, print an error message.

2. Record the segment:
    - Append a single letter to a String called `journey`:
        - 'N' for north, 'S' for south, 'E' for east, 'W' for west
    - The `journey` String is the full history of directions, ex: commands 'go north', 'go east', 'go north', 'go north' result in `journey` being "NENN"

3. Update the current `location`:
    - When no 'go X' actions have been entered, set `location = "trailhead"`.
    - After the first valid 'go X' action, if no special rule below matches, move to the `"forest"` (you have left the trailhead).
    - On every subsequent 'go X' command, evaluate the rules below in order and apply the highest priority match:
        - Priority 1: If `journey` length is 5 or more and the LAST segment is `'N'`, set `location = "summit"`.
        - Priority 2: If the entire `journey` String reads the same forwards and backwards (a palindrome) and its length is at least 3, set `location = "trailhead"`. Examples:
          ⁃ NEN → palindrome → trailhead
          ⁃ NESEN → palindrome → trailhead
          ⁃ NS → not a palindrome → NOT trailhead
          ⁃ NESS → not a palindrome → NOT trailhead
        - Priority 3: If there is an `'E'` somewhere before an `'N'`, set `location = "waterfall"`. Examples:
          ⁃ ESN → `E` at position 0, `N` at position 2 → waterfall
          ⁃ NE → `E` occurs after `N` → NOT waterfall
          ⁃ EEN → `E` occurs before `N` → waterfall
        - Priority 4: If `journey` contains `"NN"` (two CONSECUTIVE north segments), set `location = "ridge"`.
        - Priority 5: If none of the above rules apply, set `location = "forest"`

4. Print a status update:
    - Print out the user's current location
    - Count the totals using a for loop over `journey` and print formatted counts for all the directions and the number of segments:
        - "Segments: 4 | N:2 S:0 E:1 W:1"
    - Use printf or String.format for aligned formatting.

### Look
When the user types `look`, print a description of the surroundings based on the current `location`:
- trailhead: "At the Trailhead: look at the maps, sign the logbook, and fill up your water."
- forest: "In the forest: dappled light, birdsong, and soft earth underfoot."
- ridge: "On the ridge: distant lakes sparkle, fog covers the distant mountains, eagles soar."
- waterfall: "At the waterfall: cool mist, mossy rocks, echoes of falling water."
- summit: "Summit sights: jagged peaks, cairns to follow, and 360 views!!"

---

## Technical requirements
- Enums provided: `Action` and `Direction`. Use these with a `switch` statement to route commands (GO, LOOK, HELP, QUIT) and directions (NORTH, SOUTH, EAST, WEST).
- Input validation:
    - Accept lowercase, uppercase, or mixed-case inputs.
    - If the command is not one of the accepted ones, print: `Invalid command 'X'. Try 'help'.`
    - If `go` is used without a valid direction, print: `Please specify a valid direction: north, south, east, or west.`
- Loop requirements:
    - Use a do-while loop to ensure the user is prompted at least once.
    - Use a while loop to keep the program running until the user types `quit`.
    - Use for loops to:
        - Find the first space in the input to separate verb from argument
        - Count direction totals from the `journey` String (N, S, E, W)
        - Scan `journey` to apply location rules
- String manipulation:
    - Change `location` based on String rules (you can use `indexOf`, `substring`, `charAt`, etc.)
    - Format output with `printf` or `String.format`. I will provide you with more String formatting examples in class.
- NO arrays, collections, or extra classes (we will learn these concepts soon!!)


## Example runs
Here are 2 example runs of Kat's Hiking Trail Navigator. Take note of my formatting and all the different interactions.

Example with help, look, ridge, and summit
```
Welcome to Kat's Hiking Trail Navigator!!
You are at the Trailhead.
Type 'help' to see available commands.

Enter command: help

Commands:
  go <north|south|east|west>  - hike in a direction
  look                        - observe the surroundings
  help                        - show this help
  quit                        - exit the navigator

Tips:
  Try hiking north a few times to reach the ridge.
  Explore east then north to discover the waterfall.
  A longer journey ending north might lead to the summit.
  Retrace steps (palindrome journey like NEN) to return to the trailhead.

Enter command: look
You take a moment to look around. At the Trailhead: look at the maps, sign the logbook, and fill up your water.

Enter command: go west
You are at the Forest.
Segments: 1 | N:0 S:0 E:0 W:1

Enter command: go west
You are at the Forest.
Segments: 2 | N:0 S:0 E:0 W:2

Enter command: go north
You are at the Forest.
Segments: 3 | N:1 S:0 E:0 W:2

Enter command: go north
You are at the Ridge.
Segments: 4 | N:2 S:0 E:0 W:2

Enter command: look
You take a moment to look around. On the ridge: distant lakes sparkle, fog covers the distant mountains, eagles soar.

Enter command: go north
You are at the Summit.
Segments: 5 | N:3 S:0 E:0 W:2

Enter command: look
You take a moment to look around. Summit sights: jagged peaks, cairns to follow, and 360 views!!

Enter command: quit
🥾Thanks for hiking!! Safe travels
```

Example with invalid input, waterfall, and back to trailhead
``` 
Welcome to Kat's Hiking Trail Navigator!!
You are at the Trailhead.
Type 'help' to see available commands.

Enter command: blah
Invalid command 'blah'. Try 'help'.

Enter command: go east
You are at the Forest.
Segments: 1 | N:0 S:0 E:1 W:0

Enter command: go north
You are at the Waterfall.
Segments: 2 | N:1 S:0 E:1 W:0

Enter command: go north
You are at the Waterfall.
Segments: 3 | N:2 S:0 E:1 W:0

Enter command: go east
You are at the Trailhead.
Segments: 4 | N:2 S:0 E:2 W:0

Enter command: quit
🥾Thanks for hiking!! Safe travels

BUILD SUCCESSFUL in 1m 29s
```

## Good luck!! 💫