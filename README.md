# 🐻 Yogi Bear Game

**Yogi Bear Game** is an interactive game where Yogi Bear collects picnic baskets while avoiding rangers in **Yellowstone National Park**. The game involves obstacles, AI-controlled rangers, and level progression mechanics.

---

## 📜 Game Description
Yogi Bear wants to collect all the picnic baskets while avoiding rangers patrolling the park. The game features:

- **Obstacles:** Mountains and trees limit movement.
- **Rangers:** Move horizontally or vertically and can harm Yogi if they get too close.
- **Lives:** Yogi has three lives; losing all ends the game.
- **High Scores:** Players can save their names and scores.
- **Level Progression:** Collecting all baskets advances to the next level.

---

## 🔧 Installation & Setup
Follow these steps to clone and run the game locally.

### 1. Clone the Repository
```bash
git clone https://github.com/Malik22G/YogiBear.git
cd YogiBear
```

### 2. Compile & Run
```bash
javac -d bin src/*.java
java -cp bin Main

---

## 🎮 Game Controls
| Key       | Action              |
|-----------|---------------------|
| **W / ↑** | Move Up             |
| **S / ↓** | Move Down           |
| **A / ←** | Move Left           |
| **D / →** | Move Right          |
| **Esc**   | Pause / Resume Game |

---

## 🛠 Game Architecture
This game uses the **Model-View-Controller (MVC)** pattern:

1. **Model:** Manages the game state (Yogi, Rangers, Obstacles, Baskets).
2. **View:** Graphical interface and rendering.
3. **Controller:** Processes user input, updates the model, and refreshes the view.

### Key Classes
1. **`Player` (Yogi Bear)**  
   - Moves around the board to collect baskets.  
   - Loses a life if colliding with a ranger.

2. **`Ranger`**  
   - Patrols horizontally or vertically, reducing Yogi’s lives on close contact.

3. **`GameController`**  
   - Handles user input, collision detection, and game state transitions (win/lose conditions).

4. **`GameFrame`**  
   - Main window hosting the game. Provides menus for **Restart** and **High-Scores**.

5. **`GamePanel`**  
   - Renders the actual game visuals.  
   - Displays game status (lives, baskets, etc.) in a status bar.

---

## ✅ Features & Mechanics
- **Real-time Ranger AI Movement**
- **Collision Detection:** Yogi vs. rangers, baskets, and obstacles
- **Level Progression:** Automatic loading of new levels when baskets are fully collected
- **High-Score System:** Stores player names and scores
- **User-Friendly UI:** Simple menus, status bars, and input controls
- **Smooth Animations:** Updates at regular intervals

---

## ⚙️ Important Methods
Below is a summary of significant methods in the code (adjust method names to your actual implementation):

1. **`GameFrame#createMenu()`**  
   - Initializes the menu bar with **Restart** and **High-Scores** items.

2. **`GamePanel#paintComponent(Graphics g)`**  
   - Draws game elements (Yogi, Rangers, Obstacles, Baskets).  
   - Calls `GameController#render(Graphics g)` for detailed object rendering.

3. **`GameController#keyPressed(KeyEvent e)`**  
   - Processes keyboard input to move Yogi.  
   - Triggers collision detection after movement.

4. **`GameController#checkCollisions()`**  
   - Checks Yogi’s distance to rangers (loses life if too close).  
   - Checks if Yogi collected any baskets.

5. **`GameController#loadNextLevel()`**  
   - Loads a new level once all baskets in the current level are collected.

6. **`GameController#endGame()`**  
   - Prompts the player to enter their name.  
   - Saves the high score if Yogi’s lives reach zero.

7. **`HighscoreManager#addScore(String playerName, int baskets, int levelsCleared)`**  
   - Stores a new high score in the database or file.

8. **`HighscoreManager#getAllScores()`**  
   - Retrieves high scores in a sorted list.

---

## 🧪 Testing

### Black-Box Test Cases
| Test Name               | Scenario                                        | Expected Outcome                                            |
|-------------------------|-------------------------------------------------|-------------------------------------------------------------|
| **TestBasketCollection** | Yogi moves onto a basket                        | Basket disappears; score increases                          |
| **TestRangerCollision**  | Ranger within one unit distance of Yogi         | Yogi loses a life and respawns                              |
| **TestGameOver**         | Yogi’s last life is lost                        | Game ends; prompt for player name                           |
| **TestHighscoreEntry**   | Game finishes and user enters a name            | Score is saved in the high-score list                       |
| **TestInvalidMove**      | Yogi tries to move beyond map boundaries        | Move is blocked; possible on-screen warning                 |

### White-Box Test Cases
| Test Name               | Scenario                                                      | Expected Outcome                                             |
|-------------------------|---------------------------------------------------------------|--------------------------------------------------------------|
| **TestUpdateRangers**   | Verifies rangers move according to their set pattern         | Ranger positions update on each game tick                   |
| **TestCollisionDetection** | Yogi and a ranger occupy adjacent cells                     | Collision triggers life loss for Yogi                        |
| **TestResetGame**       | Restart function is called after game over                   | Everything resets to initial values                          |
| **TestWinCondition**    | All baskets collected                                        | Next level loads automatically                               |
| **TestHighscoreDatabase** | New score is saved and high-score table is displayed         | New score appears in correct sorted order                    |

---

## 🤝 Contributing
Contributions are welcome!  
1. **Fork** this repository.  
2. **Create a new branch**: `git checkout -b feature-name`.  
3. **Commit your changes**: `git commit -m "Add feature"`.  
4. **Push to your branch**: `git push origin feature-name`.  
5. **Open a Pull Request** on GitHub.


---

## 📧 Contact
- **Author:** Abdul Basit  
- **GitHub:** [Malik22G](https://github.com/Malik22G)  
- **Email:** malik.basit3690@gmail.com  
