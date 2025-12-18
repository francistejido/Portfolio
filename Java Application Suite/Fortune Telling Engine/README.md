[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/ij6DHhbb)
# Programming I (420-G10-HR)

# Assignment 1 – Fortune Teller Program 🔮

Date assigned: Sept 10, 2025  
Due date: Sept 30, 2025 EOD (11:59PM)

---

## Learning Objectives

Upon successful completion of this assignment, the student will be able to:
1. Compile and execute a simple Java program.
2. Use variables, data types, constants, literals, arithmetic, and boolean logic.
3. Implement if statements and switch statements.
4. Use Math.random and enums.
5. Demonstrate proper use of version control and commit etiquette in Git/GitHub.
6. Read input from the keyboard and output to the screen.

---

## Marking Scheme

| Component                        | Mark |
|----------------------------------|------|
| Enums (design & usage)           | 10   |
| Validating inputs                | 15   |
| Career logic                     | 10   |
| Relationship logic               | 12   |
| Pet logic                        | 8    |
| Travel logic                     | 8    |
| Control flow usage               | 12   |
| Output formatting & completeness | 10   |
| Git commit etiquette             | 5    |
| Readability & coding conventions | 10   |
| **Total**                        | 100  |

---

## Setup & Submission
- [ ] READ THE ENTIRE ASSIGNMENT FIRST before starting (including the marking scheme above), so you understand ALL requirements and the breakdown of the assignment.
- [ ] Clone and use the provided starter project in this repository. All code must be committed and pushed here.
- [ ] Before starting, RUN the starter project first! See what I have given you and how you can use it to start off.
- [ ] Commit and PUSH up your changes often! There are marks for proper Git commit etiquette.
- [ ] Kat will review your code and provide feedback & final marks via the Feedback Pull Request after the assignment deadline.

---

### Git commit Etiquette
We have now done multiple labs using Git for version control. Remember the following for your commit messages:
- Use imperative tone ("Add fortune logic" ✅ not "Added" 🙅 or "Adding"🙅)
- Clearly and concisely describe what your changes are
- If your commit is complex, add a blank line after the summary and write a short body explaining any extra details:
  ```
  Add CareerOption enum and logic to decide career fortune
  
  - Create a CareerOption enum with five possible careers
  - Add logic to select a career based on the sum of favorite number and birth month
  - This makes the career fortune personalized to the user's input
  ```

---

## Fortune Teller Program 🔮

### Overview
Create a Java program that acts as a personalized 🔮Fortune Teller🔮.
The program will start by asking the user a series of questions, then it will produce an exciting fortune covering several areas of life.
You will use control flow, randomness, and formatted output to make the experience enjoyable for the user.

I have provided you with starter code that includes an example of collecting user input. You will need to use the user's input to come up with your fortune teller logic. During our next lectures/lab I will go over user input so you will get more practice to help you with this assignment.

---

## Part 1: Getting user input
Your program must ask the user for the following information when the program starts:
1. **Name** (data type: String) Only used for printing it in the fortune, do not use in calculations
2. **Favourite Number** (data type: int) The user must only input a favourite number from 1-10, use in calculations
3. **Birth Month** (data type: int) The user must only input a number from 1–12, use in calculations
4. **Do you believe in fate?** (data type: Boolean) The user must enter yes or no, use in calculations. Hint: you will have to figure out how to change yes to true and no to false. You may use 2 separate variables to handle this if you prefer.

Every time the user answers one of the questions, you will VALIDATE the input (using if or switch), and then you will store the valid answer in a corresponding variable that you can then do calculations and decision making with once all questions are answered.

### Validating inputs
- For each user input, use `if`/`else` statements or a switch to check if the value is valid.
- If the user enters something unexpected (like a number out of range, or anything other than "yes" or "no" for the last question), print an error message and immediately end the program
- For the yes/no question, only accept “yes” or “no” (case insensitive). For all other questions, see the above descriptions of data type and expected user input to determine validity.

---

## Part 2: Generating and printing the Fortune
*Note: An example run of the program is included at the end of the assignment!*

Once you have collected all the user information in variables, your program will generate and display a fortune covering four areas:

- **Career**
- **Relationship**
- **Pet**
- **Travel Destination**

Each area must use a combination of user input, randomness, and decision logic (if statements and switch statements).

### Fortune Option Enums

You must create enums for each fortune area:

- **Career** (`enum CareerOption`)
- **Relationship** (`enum RelationshipOption`)
- **Pet** (`enum PetOption`)
- **Travel Destination** (`enum TravelOption`)

Each enum MUST have at least 5 options for your program to choose from (be creative and have fun with this but please keep it appropriate!!). Take a look at my example run at the bottom of this assignment for some outputs that I used in mine if you are feeling uninspired. Each option will have a corresponding message that gets printed out (you will use an if statement and/or switch for this).

### Logic for generating fortune
Hint: How to use [modulo](https://www.geeksforgeeks.org/java/modulo-or-remainder-operator-in-java/)
- **Career:** Add their favorite number and birth month together. Use modulo (`%`) with the number of `CareerOption` enum values to pick a career  
  _Example:_ If there are 5 careers and the sum is 13, use `13 % 5 = 3` to pick the career at index 3.
- **Relationship:**
    - If the user believes in fate, use `Math.random` to pick a random `RelationshipOption`
    - If the user does not believe in fate, use their (favorite number % the number of `RelationshipOption` values) to pick one
- **Pet:** Use `Math.random` to pick a `PetOption`.
- **Travel Destination:** Use (birth month % the number of `TravelOption` enum values) to pick a destination

Use `if` statements and `switch` statements to implement all of the above logic and select the correct enum value for each fortune component. Remember to test with multiple variations!

### Output formatting
- Print a multi-line fortune that includes the user’s name and the four fortune areas
- Format the output for readability (use blank lines, bullet points, and clear labels!!)
- End with a closing message and 'Goodbye' on a new line

---

### Tips
- Use `System.out.println()` to print out the values of your variables while you are coding and testing. You can always remove extra prints once everything is working as expected
- Use comments in your code to keep track of each step and if you have //TODOs
- When you need to pick a random option, use `Math.random` to generate a random number to choose one of your enum values
- When using calculations, make sure your index is within the bounds of the enum options
- Use `if`/`else` and `switch` statements to control the flow of your program and select the correct fortune options
- Run your program multiple times and try with different options to ensure your program behaves as expected!!

---

### Example run

#### Invalid input entered:
```
🔮Welcome to the Fortune Teller!!🔮

What is your name? Kat
Enter your favorite number (1–10): 2
Enter your birth month (1–12): 99
Invalid input: Birth month must be between 1 and 12
Goodbye.
========================
```

#### Valid input entered:
```
🔮Welcome to the Fortune Teller!!🔮

What is your name? Kat
Enter your favorite number (1–10): 2
Enter your birth month (1–12): 4
Do you believe in fate? (yes/no): yes

💫Aligning the stars for your fortune...✨

===== YOUR FORTUNE =====
Kat, your future holds:
1. Career: You will become a legendary scientist
2. Relationship: Your current relationships will flourish
3. Pet: A loyal dog will join your adventures
4. Travel Destination: You will explore the Dolomites in Italy

Wishing you cosmic luck on your journey!
Goodbye.
========================
```

---
## Good luck!! 💫