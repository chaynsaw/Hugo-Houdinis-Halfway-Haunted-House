package com.locallampoon.fiveh.core;

import com.locallampoon.fiveh.client.UserInput;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Game implements Serializable {
    private Player player;
    private Map<String, Room> houseMap;
    private static final List<String> ACTIONS = new ArrayList<>(Arrays.asList("go", "move", "get", "drop", "talk", "inspect", "h", "help", "i", "inventory", "q", "quit"));
    private static final List<String> ACTION_ITEMS = new ArrayList<>(Arrays.asList("key", "book", "amulet", "oregano", "sword", "duffel",
            "north", "south", "east", "west"));
    private static final String HELP_FILE = "src/com/locallampoon/fiveh/data/helpmenu.txt";

    // CONSTRUCTOR
    public Game() {
        setHouseMap(XMLParser.parseRooms());
        System.out.println(houseMap.entrySet());
        this.player = new Player(houseMap.get("hall"));
    }

    // GETTER/SETTER METHODS

    Player getPlayer() {
        return player;
    }

    void setPlayer(Player player) {
        this.player = player;
    }

    Map<String, Room> getHouseMap() {
        return houseMap;
    }

    private void setHouseMap(Map<String, Room> houseMap) {
        this.houseMap = houseMap;
    }

    static List<String> getActions() {
        return ACTIONS;
    }

    static List<String> getActionItems() {
        return ACTION_ITEMS;
    }


    // METHODS
    public List<String> inputListener(String commandInput) {
        return UserInput.inputList(commandInput);
    }

    private List<String> parseCommand(List<String> wordsList) throws IOException {

        String verb;
        String noun;

        if (wordsList.size() == 2) {
            verb = wordsList.get(0);
            noun = wordsList.get(1);

            if (!ACTIONS.contains(verb)) {
                System.out.println("Not an acceptable action");
            }
            if (!ACTION_ITEMS.contains(noun)) {
                System.out.println("Item not in room");
            }

        } else if (wordsList.size() == 1) {
            verb = wordsList.get(0);

            if (!ACTIONS.contains(verb)) {
                System.out.println("Not an acceptable action");

            }
        } else {
            System.out.println("Two word command expected I.E. 'get sword' or 'go north'");
        }
        return wordsList;
    }

    private void getHelp() {
        GameArt.renderHelper();
        try (BufferedReader br = new BufferedReader(new FileReader(HELP_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("UH OH! If it weren't for you pesky kids, I would have printed the Help Menu!");
        }
    }

    private int playerMovement(String goDirection) {
        int dirIdx;
        switch (goDirection) {
            case "north":
                dirIdx = Direction.NORTH.getDirection();
                break;
            case "south":
                dirIdx = Direction.SOUTH.getDirection();
                break;
            case "east":
                dirIdx = Direction.EAST.getDirection();
                break;
            case "west":
                dirIdx = Direction.WEST.getDirection();
                break;
            case "up":
                dirIdx = Direction.UP.getDirection();
                break;
            case "down":
                dirIdx = Direction.DOWN.getDirection();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + goDirection);
        }
        return dirIdx;
    }

    public void start() throws IOException {

        BufferedReader bufferedReader;
        String input;
        List<String> output;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        game.showIntro();
        do {
            System.out.println(player.getCurrentRoom().getDesc());
            System.out.print("> ");
            input = bufferedReader.readLine();
            output = inputListener(input);
            List<String> roomExits = player.getCurrentRoom().getExits();
            System.out.println(roomExits);

            // TODO: Finish switch to contain all Verbs and Noun interaction
            switch (output.get(0)) {
                case "go":
                case "move":
                    int dirMovement = playerMovement(output.get(1));
                    Room roomKeyID = houseMap.get(roomExits.get(dirMovement));
                    player.move(roomKeyID);
                    break;
                case "help":
                case "h":
                    getHelp();
                    break;
                default:
                    System.out.println("suck it");
                    System.out.println(houseMap);
            }
        } while (!"q".equals(input));
    }

}