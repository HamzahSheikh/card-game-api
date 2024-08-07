# ♠️♥️♦️♣️ Card Game Take Home Project ♠️♥️♦️♣️

Built using Spring Boot, this REST API service facilitates a straightforward card game, handling deck dealing, player creation, and more.

### Assumptions
Only one game can run at a time, thus it was implemented as a singleton


# Project Setup
This is a Maven project, as such an installation of Maven would be required to package into a runnable JAR.

### 1. Clone this Repository

  ```git clone https://github.com/HamzahSheikh/card-game-api.git```

### 2. Redirect into the project root 

  ```cd .../card-game-api```
  
### 3. Run Maven package

  ```mvn package```

### 4. Run the .jar file found in the target folder

  ```java -jar target/cardgame-0.0.1-SNAPSHOT.jar```

### Alternative

A pre-compiled JAR file of the application can be found under Releases: https://github.com/HamzahSheikh/card-game-api/releases/tag/JAR


# REST API Documentation for Card Game

## Create Game

Creates a new game if one does not already exist

### URL
`POST /api/v1/create/game`

### Response
| Parameter   | Type           | Description                      |
| :---        |     :---       |         :---                     |
| message     | String         | Information about transaction    |
| status      | Integer        | HTTP Code                        |

#### Example
```
{
    "message": "Success! Game created!",
    "status": 201
}
```

## Delete Game

Deletes the game if one exists

### URL
`POST /api/v1/delete/game`

### Response
| Parameter    | Type           | Description                      |
| :---         |     :---       |         :---                     |
| message      | String         | Information about transaction    |
| status       | Integer        | HTTP Code                        |

#### Example
```
{
    "message": "Success! Game deleted!",
    "status": 200
}
```


## Create Deck

Creates a new deck and add it to the stack of reserve decks

### URL
`POST /api/v1/create/deck`

### Response
| Parameter    | Type           | Description                       |
| :---         |     :---       |         :---                      |
| data         | Integer        | Number of Decks in Reserve        |
| message      | String         | Information about transaction     |
| status       | Integer        | HTTP Code                         |

#### Example
```
{
    "data": 5,
    "message": "Success! Deck Created! Total Reserve Decks: 5;",
    "status": 200
}
```


## Add Deck to Game

Adds a deck from the reserve stack to the game deck

### URL
`POST /api/v1/add/deck`

### Response
| Parameter    | Type                         | Description                                              |
| :---         |     :---                     |         :---                                             |
| data         | List of Cards/Suit Count     | List of cards in the deck and count for each suit type   |
| message      | String                       | Information about transaction                            |
| status       | Integer                      | HTTP Code                                                |

#### Example
```
{
    "data": {
        "cards": [
            {
                "suit": "HEARTS",
                "value": "ACE"
            },
            {
                "suit": "HEARTS",
                "value": "TWO"
            },
            {
                "suit": "HEARTS",
                "value": "THREE"
            },
            {
                "suit": "HEARTS",
                "value": "FOUR"
            },
            {
                "suit": "HEARTS",
                "value": "FIVE"
            },
                
           ...

        ],
        "suitCount": {
            "HEARTS": 26,
            "CLUBS": 26,
            "SPADES": 26,
            "DIAMONDS": 26
        }
    },
    "message": "Success! Deck added to game!",
    "status": 200
}
```


## Add Player to Game

Create and Add a player to the game

### URL
`POST /api/v1/game/add/players`

### Response
| Parameter     | Type           | Description                      |
| :---          |     :---       |         :---                     |
| data          | Integer        | ID of the player added           |
| message       | String         | Information about transaction    |
| status        | Integer        | HTTP Code                        |

#### Example
```
{
    "data": 6,
    "message": "Player 6 added!",
    "status": 201
}
```
## Remove Player from Game
Remove a player from the game

### URL
`POST /api/v1/game/remove/players/{playerNumber}`

### Path Variable
| Parameter     | Type           | Description                   |
| :---          |     :---       |         :---                  |
| playerNumber  | Integer        | ID of player being removed    |

### Response
| Parameter    | Type           | Description                      |
| :---         |     :---       |         :---                     |
| data         | Integer        | Count of players remaining       |
| message      | String         | Information about transaction    |
| status       | Integer        | HTTP Code                        |

#### Example
```
{
    "data": 3,
    "message": "Player 2 removed!",
    "status": 200
}
```


## Deal Cards to a Player

### URL
`POST /api/v1/game/deck/deal/{playerNumber}/{numberOfCards}`

### Path Variable
| Parameter       | Type           | Description                    |
| :---            |     :---       |         :---                   |
| playerNumber    | Integer        | ID of player being dealt cards |
| numberOfCards   | Integer        | Number of cards being dealt    |

### Response
| Parameter     | Type           | Description                                              |
| :---          |     :---       |         :---                                             |
| data          | List           | Player name, current hand of player and value of hand    |
| message       | String         | Information about transaction                            |
| status        | Integer        | HTTP Code                                                |

#### Example
```
{
    "data": {
        "name": "Player 5",
        "hand": [
            {
                "suit": "DIAMONDS",
                "value": "TEN"
            },
            {
                "suit": "HEARTS",
                "value": "TEN"
            },
            {
                "suit": "DIAMONDS",
                "value": "ACE"
            },
             ...
        ],
        "valueOfHand": 262
    },
    "message": "Card dealt!",
    "status": 200
}
```


## Get Player Hand

Get a player's hand of cards.

### URL
`GET /api/v1/game/hand/players/{playerNumber}`

### Path Variable
| Parameter      | Type           | Description                |
| :---           |     :---       |         :---               |
| playerNumber   | Integer     | ID of player being removed    |

### Response
| Parameter    | Type           | Description                       |
| :---         |     :---       |         :---                      |
| data         | Cards          | List of cards in player's hand    |
| message      | String         | Information about transaction     |
| status       | Integer        | HTTP Code                         |

#### Example
```
{
    {
    "data": [
        {
            "suit": "SPADES",
            "value": "THREE"
        },
        {
            "suit": "DIAMONDS",
            "value": "TWO"
        },
        {
            "suit": "SPADES",
            "value": "JACK"
        },
        {
            "suit": "SPADES",
            "value": "FIVE"
        }
    ],
    "message": "Player 1's hand, Total: 4 Cards",
    "status": 200
}
}
```


## Get All Players in the Game and Their Hand Value 

Get all players in the game and their hand value

### URL
`GET /api/v1/game/players/all/value`

### Response
| Parameter    | Type           | Description                                               |
| :---         |     :---       |         :---                                              |
| data         | List           | List of player names, cards in their hand and their value |
| message      | String         | Information about transaction                             |
| status       | Integer        | HTTP Code                                                 |

#### Example
```
{
    "data": [
        {
            "name": "Player 2",
            "hand": [
                {
                    "suit": "CLUBS",
                    "value": "SEVEN"
                },
                {
                    "suit": "DIAMONDS",
                    "value": "SEVEN"
                },
                {
                    "suit": "SPADES",
                    "value": "SIX"
                },
                {
                    "suit": "CLUBS",
                    "value": "TEN"
                },
                {
                    "suit": "CLUBS",
                    "value": "FIVE"
                }
            ],
            "valueOfHand": 35
        },
        {
            "name": "Player 3",
            "hand": [
                {
                    "suit": "SPADES",
                    "value": "TEN"
                },
                {
                    "suit": "DIAMONDS",
                    "value": "NINE"
                },
                {
                    "suit": "DIAMONDS",
                    "value": "TWO"
                }
            ],
            "valueOfHand": 21
        },
         ...
    ],
    "message": "Players Value!",
    "status": 200
}
```


## Get Deck Suit Count

Get the count of how many cards per suit are left undealt in the game deck 

### URL
`GET /api/v1/game/deck/count/suit`

### Response
| Parameter | Type           | Description                        |
| :---      |     :---       |         :---                       |
| data      | List           | Suit and their respective count    |
| message   | String         | Information about transaction      |
| status    | Integer        | HTTP Code                          |

#### Example
```
{
    "data": {
        "HEARTS": 18,
        "CLUBS": 16,
        "SPADES": 16,
        "DIAMONDS": 14
    },
    "message": "Suit Count!",
    "status": 200
}
```

## Get Game Events

Get a chronological log of the game events

### URL
`GET /api/v1/event/game/log`

### Response
| Parameter | Type           | Description                               |
| :---      |     :---       |         :---                              |
| data      | List           | All events related to the game actions    |
| message   | String         | Information about transaction             |
| status    | Integer        | HTTP Code                                 |

#### Example
```
{
    "data": [
        {
            "timestamp": 1718239986546,
            "eventType": "GAME_EVENT",
            "eventAction": "REMOVE_PLAYER",
            "eventTime": "2024-06-12T20:53:06.5468076",
            "description": {
                "headers": {},
                "body": {
                    "message": "No game created!",
                    "status": 400
                },
                "statusCode": "BAD_REQUEST",
                "statusCodeValue": 400
            },
            "source": "GAME_EVENT"
        },
        {
            "timestamp": 1718239987073,
            "eventType": "GAME_EVENT",
            "eventAction": "REMOVE_PLAYER",
            "eventTime": "2024-06-12T20:53:07.0733851",
            "description": {
                "headers": {},
                "body": {
                    "message": "No game created!",
                    "status": 400
                },
                "statusCode": "BAD_REQUEST",
                "statusCodeValue": 400
            },
            "source": "GAME_EVENT"
        },
          ...
    ],
    "message": "Game Event Log!",
    "status": 200
}
```

## Get Deck Events

Get a chronological log of the deck events

### URL
`GET /api/v1/event/deck/log`

### Response
| Parameter | Type           | Description                               |
| :---      |     :---       |         :---                              |
| data      | List           | All events related to the deck actions    |
| message   | String         | Information about transaction             |
| status    | Integer        | HTTP Code                                 |

#### Example
```
{
    "data": [
        {
            "timestamp": 1718249002021,
            "eventType": "DECK_EVENT",
            "eventAction": "ADD_DECK",
            "eventTime": "2024-06-12T23:23:22.021717",
            "description": {
                "headers": {},
                "body": {
                    "message": "No game created!",
                    "status": 400
                },
                "statusCode": "BAD_REQUEST",
                "statusCodeValue": 400
            },
            "source": "DECK_EVENT"
        },
        {
            "timestamp": 1718249002670,
            "eventType": "DECK_EVENT",
            "eventAction": "ADD_DECK",
            "eventTime": "2024-06-12T23:23:22.6709213",
            "description": {
                "headers": {},
                "body": {
                    "message": "No game created!",
                    "status": 400
                },
                "statusCode": "BAD_REQUEST",
                "statusCodeValue": 400
            },
            "source": "DECK_EVENT"
        },
          ...
    ],
    "message": "Game Event Log!",
    "status": 200
}
```

## Get Player Events

Get a chronological log of the player events

### URL
`GET /api/v1/event/player/log`

### Response
| Parameter | Type           | Description                               |
| :---      |     :---       |         :---                              |
| data      | List           | All events related to the player actions  |
| message   | String         | Information about transaction             |
| status    | Integer        | HTTP Code                                 |

#### Example
```
{
    "data": [
        {
            "timestamp": 1718239986546,
            "eventType": "PLAYER_EVENT",
            "eventAction": "REMOVE_PLAYER",
            "eventTime": "2024-06-12T20:53:06.5468076",
            "description": {
                "headers": {},
                "body": {
                    "message": "No game created!",
                    "status": 400
                },
                "statusCode": "BAD_REQUEST",
                "statusCodeValue": 400
            },
            "source": "PLAYER_EVENT"
        },
        {
            "timestamp": 1718239987073,
            "eventType": "PLAYER_EVENT",
            "eventAction": "REMOVE_PLAYER",
            "eventTime": "2024-06-12T20:53:07.0733851",
            "description": {
                "headers": {},
                "body": {
                    "message": "No game created!",
                    "status": 400
                },
                "statusCode": "BAD_REQUEST",
                "statusCodeValue": 400
            },
            "source": "PLAYER_EVENT"
        },
          ...
    ],
    "message": "Player Event Log!",
    "status": 200
}
```

# Test Coverage
![image](https://github.com/HamzahSheikh/goto-cardsgame/assets/57862914/9514c023-3e64-40c4-8da0-9337c5f65aa6)





