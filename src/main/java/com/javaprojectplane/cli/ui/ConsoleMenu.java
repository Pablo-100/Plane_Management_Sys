package com.javaprojectplane.cli.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Stack;

public class ConsoleMenu {
    private static ConsoleMenu instance;
    private final BufferedReader reader;
    private final Stack<String> menuPath;

    // ANSI color codes
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String CLEAR_SCREEN = "\033[H\033[2J";
    
    // Command mode flag
    private boolean commandMode = false;

    // Private constructor for Singleton
    private ConsoleMenu() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        menuPath = new Stack<>();
    }

    // Singleton getInstance method
    public static ConsoleMenu getInstance() {
        if (instance == null) {
            instance = new ConsoleMenu();
        }
        return instance;
    }
    
    // Set command mode
    public void setCommandMode(boolean mode) {
        this.commandMode = mode;
    }
    
    // Get command mode
    public boolean isCommandMode() {
        return commandMode;
    }

    public void showWelcomeScreen() {
        clearScreen();
        System.out.println(ANSI_RED + """
            +------------------------------------------+
            |         PLANE MANAGEMENT SYSTEM          |
            +------------------------------------------+
            """ + ANSI_RESET);
        System.out.println(ANSI_BLUE + "Welcome to the Plane Management System" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "\nType 'help' for available commands or 'menu' for interactive menu" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "Press Enter to continue..." + ANSI_RESET);
        try {
            reader.readLine();
        } catch (IOException e) {
            // Ignore
        }
    }
    
    // New method to read commands
    public String readCommand() {
        System.out.print(ANSI_GREEN + "plane-mgmt> " + ANSI_RESET);
        try {
            return reader.readLine().trim();
        } catch (IOException e) {
            return "";
        }
    }
    
    // New method to show help
    public void showHelp() {
        clearScreen();
        System.out.println(ANSI_CYAN + "Available Commands:" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "  help" + ANSI_RESET + " - Show this help message");
        System.out.println(ANSI_GREEN + "  menu" + ANSI_RESET + " - Switch to interactive menu mode");
        System.out.println(ANSI_GREEN + "  exit" + ANSI_RESET + " - Exit the application");
        System.out.println(ANSI_GREEN + "  clear" + ANSI_RESET + " - Clear the screen");
        System.out.println();
        System.out.println(ANSI_GREEN + "  plane list" + ANSI_RESET + " - List all planes");
        System.out.println(ANSI_GREEN + "  plane add" + ANSI_RESET + " - Add a new plane");
        System.out.println(ANSI_GREEN + "  plane search <id>" + ANSI_RESET + " - Search for a plane by ID");
        System.out.println(ANSI_GREEN + "  plane delete <id>" + ANSI_RESET + " - Delete a plane by ID");
        System.out.println();
        System.out.println(ANSI_GREEN + "  pilot list" + ANSI_RESET + " - List all pilots");
        System.out.println(ANSI_GREEN + "  pilot add" + ANSI_RESET + " - Add a new pilot");
        System.out.println(ANSI_GREEN + "  pilot search <id>" + ANSI_RESET + " - Search for a pilot by ID");
        System.out.println(ANSI_GREEN + "  pilot delete <id>" + ANSI_RESET + " - Delete a pilot by ID");
        System.out.println();
        System.out.println(ANSI_GREEN + "  assign <plane_id> <pilot_id>" + ANSI_RESET + " - Assign pilot to plane");
        System.out.println(ANSI_GREEN + "  passenger add <plane_id>" + ANSI_RESET + " - Add passenger to plane");
        System.out.println(ANSI_GREEN + "  passenger list <plane_id>" + ANSI_RESET + " - List passengers on plane");
        System.out.println();
        System.out.println(ANSI_YELLOW + "Press Enter to continue..." + ANSI_RESET);
        try {
            reader.readLine();
        } catch (IOException e) {
            // Ignore
        }
    }

    public int showMenu(String title, List<String> options) {
        int selectedIndex = 0;
        boolean choosing = true;

        // Update menu path
        if (!menuPath.isEmpty() && menuPath.peek().equals(title)) {
            // Don't add if it's the same menu
        } else {
            menuPath.push(title);
        }

        while (choosing) {
            // Clear screen and show menu
            clearScreen();
            
            // Show navigation path
            System.out.println(ANSI_CYAN + "Location: " + String.join(" > ", menuPath) + ANSI_RESET);
            System.out.println();
            
            // Print title with box
            System.out.println(ANSI_RED + "╔═" + "═".repeat(title.length() + 2) + "═╗");
            System.out.println("║ " + title + " ║");
            System.out.println("╚═" + "═".repeat(title.length() + 2) + "═╝" + ANSI_RESET);
            System.out.println();

            // Display options
            for (int i = 0; i < options.size(); i++) {
                if (i == selectedIndex) {
                    System.out.println(ANSI_GREEN + "→ " + options.get(i) + ANSI_RESET);
                } else {
                    System.out.println("  " + options.get(i));
                }
            }

            // Show navigation help
            System.out.println();
            System.out.println(ANSI_YELLOW + "Use W/S or ↑/↓ to navigate, Enter to select, Q to go back" + ANSI_RESET);

            try {
                // Read key input
                int input = System.in.read();
                
                switch (input) {
                    case 'w':
                    case 'W':
                    case 65: // Up arrow (first byte)
                        selectedIndex = (selectedIndex > 0) ? selectedIndex - 1 : options.size() - 1;
                        break;
                    case 's':
                    case 'S':
                    case 66: // Down arrow (first byte)
                        selectedIndex = (selectedIndex < options.size() - 1) ? selectedIndex + 1 : 0;
                        break;
                    case '\r':
                    case '\n':
                        choosing = false;
                        break;
                    case 'q':
                    case 'Q':
                        if (!menuPath.isEmpty()) {
                            menuPath.pop();
                        }
                        return -1;
                }
                
                // Clear input buffer
                while (System.in.available() > 0) {
                    System.in.read();
                }
            } catch (IOException e) {
                System.err.println("Error reading input: " + e.getMessage());
                return -1;
            }
        }

        clearScreen();
        return selectedIndex;
    }

    public String readInput(String prompt) {
        // Show current location before input prompt
        System.out.println(ANSI_CYAN + "Location: " + String.join(" > ", menuPath) + ANSI_RESET);
        System.out.println();
        
        System.out.print(ANSI_BLUE + prompt + ANSI_RESET);
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.err.println("Error reading input: " + e.getMessage());
            return "";
        }
    }

    public void showMessage(String message) {
        System.out.println(ANSI_YELLOW + message + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "\nAppuyez sur Entrée pour continuer..." + ANSI_RESET);
        try {
            reader.readLine();
        } catch (IOException e) {
            // Ignorer
        }
    }

    public void clearScreen() {
        System.out.print(CLEAR_SCREEN);
        System.out.flush();
    }

    public void goBack() {
        if (!menuPath.isEmpty()) {
            menuPath.pop();
        }
    }
}