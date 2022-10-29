# Casino Discord Bot
## How to run this bot
1. Create file called ```application.local.properties``` in ```resources``` folder. 
2. In the file you created, create a variable ```bot.token``` and assign it the value of your bot's ```token```
3. Open terminal and run ```docker-compose up -d```
4. Login to ```phpMyAdmin```
5. Import schemas from ```schemas.sql``` and tables from ```tables.sql```. All files are in the ```resources``` folder

## Available commands
### Games
- `!coin-flip heads_or_tails credits` - This is a normal coin flip, you have a 50% chance of winning. If you win then you get twice the amount you bet.
- `!russian shots_num credits` - Russian roulette with one bullet in the magazine. The number of shots determines parameter `shots_num`. If you survive, you win the `shots_num` times amount you have bet.
- `!roulette color credits` - In this version of roulette, only the colors - red, black and green - are bet on. The probability of picking each color: green 4%, red 48% and black 48%. Green wins 14 times the bet, while red and black win twice.

### Other
- `!account` - Tells you how much credits you have.

