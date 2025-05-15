# 🛩️ Plane Management System

**Plane Management System** est une application Java en ligne de commande (CLI) avec une interface graphique textuelle inspirée des outils comme "Kali Tools". Elle permet la gestion complète des avions, pilotes, et passagers à travers un système de menus interactif.


## 📌 Aperçu

    ╔═════════════════════════════╗
    ║ Plane Management System ║
    ╚═════════════════════════════╝
    
    ➡ Manage Planes
    ➡ Manage Pilots
    ➡ Plane-Pilot Assignment
    ➡ Manage Passengers
    ➡ Exit
    
    Utiliser W/S ou ↑/↓ pour naviguer, Entrée pour sélectionner, Q pour revenir



## ⚙️ Fonctionnalités

- ✈️ **Gestion des Avions** : Ajouter, consulter, chercher, et supprimer des avions (avec numéro d'immatriculation unique).
- 👨‍✈️ **Gestion des Pilotes** : Ajouter, consulter, chercher, et supprimer des pilotes (avec numéro de licence unique).
- 🔗 **Assignation Pilote–Avion** : Assigner un pilote à un avion et consulter les affectations.
- 🧍 **Gestion des Passagers** : Ajouter, afficher, ou supprimer des passagers avec validation des passeports et des sièges.
- 🎨 **Interface CLI Améliorée** : Interface intuitive avec de l’art ASCII pour la navigation.
- ✅ **Validation des Entrées** : Intégrité des données assurée via une validation stricte.


## 🗂️ Structure du Projet

    plane-management-system/
    └── src/
    └── main/
    └── java/
    └── com/
    └── javaprojectplane/
    └── cli/
        ├── ui/ 
        ├── Main.java
        ├── Plane.java
        ├── Pilot.java
        └── Passenger.java


## 🛠️ Prérequis

- Java Development Kit (JDK) 8 ou plus
- Terminal ou environnement en ligne de commande


## 🚀 Lancer le Projet

### 1. Cloner le dépôt

bash

    git clone https://github.com/your-username/plane-management-system.git
    cd plane-management-system/src/main/java.

2. Compiler le code
        
       javac com/javaprojectplane/cli/*.java

3. Exécuter l'application

       java com.javaprojectplane.cli.Main

##🎮 Utilisation

Naviguer avec W/S ou ↑/↓

Sélectionner avec Entrée

Revenir en arrière avec Q

Fournir des données valides quand demandé (ex: immatriculation unique, numéros de passeport valides)

##🧱 Détails d’Implémentation

###📦 Classes Principales

Plane

   **Champs** : id, model, registrationNumber, pilotId, passengers[], passengerCount

   **Méthodes** : addPassenger, removePassenger, getPassengers, toString, etc.

Pilot

  **Champs** : id, name, licenseNumber

   **Méthodes** : Getters/Setters, toString

Passenger

   **Champs** : id, name, passportNumber, seatNumber

   **Méthodes** : Getters/Setters, toString

###🧮 Stockage

   **Avions & pilotes** : tableaux statiques (max 100).

   **Passagers** : chaque avion possède un tableau (max 200).

   Pas de base de données (pas de persistance après fermeture).

##🧰 Design Patterns Utilisés

   **Singleton** : utilisé dans ConsoleMenu (menu unique partagé)

   **Factory** : utilisé dans TransportFactory (création centralisée)

   **MVC Partiel** : séparation Model (Plane, Pilot, Passenger), View (ConsoleMenu), Controller (Main)

##⚠️ Limitations

   Tableaux de taille fixe (100 avions/pilotes, 200 passagers max/avion)

   Pas de persistance (les données disparaissent à la fermeture)

   Dépend de classes externes non fournies (ConsoleMenu, Validator, TransportFactory)

##🌱 Améliorations Futures

   Remplacer les tableaux par des ArrayList

   Ajouter un système de stockage persistant (fichier ou base de données)

   Intégrer une vraie interface graphique (JavaFX ou Swing)

   Ajouter des tests unitaires

##👨‍💻 Auteur
   
   Projet développé à des fins pédagogiques pour maîtriser Java, les structures de données, l’architecture logicielle et la conception d'interfaces CLI.

