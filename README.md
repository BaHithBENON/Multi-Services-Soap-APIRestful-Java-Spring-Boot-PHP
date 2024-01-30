# Multi-Services-Soap-APIRestful-Java-Spring-Boot-PHP


# Structure des Projets

## A - 0001-mpisi-client-rest

Ce projet contient la partie client qui communique avec le service API RestFul situé dans le dossier `rest-service-etudiant`. Pour lancer le service, exécutez le fichier `RESTClient`.

## B - 0001-soap-ws-client

Ce projet contient le client permettant d'utiliser le service SOAP dans le projet `0001-ws-soap-server-application`. Pour lancer le service, exécutez les fichiers du package `com.javaws.client`. Le fichier `Launcher.java` permet de démarrer une interface graphique pour gérer l'authentification via le service SOAP et le CRUD via l'API Rest. Le fichier `ServiceWebClient` gère le SOAP dans le terminal, et `AuthenticationAPP` gère l'authentification avec une GUI Swing simple.

## C - 0001-ws-soap-server-application

Ce projet contient le service SOAP offrant des fonctionnalités basiques d'authentification et de CRUD d'un étudiant. Pour lancer le service, exécutez le fichier `MonitorLauncher.java`.

## D - rest-service-etudiant

Ce projet contient une API Restful pour gérer les étudiants. Pour lancer le service, exécutez le fichier `RestServiceEtudiantApplication.java`.

## Configuration et Développement

- Les développeurs doivent consulter les fichiers de propriétés dans les ressources pour gérer les ports des services et des clients. Assurez-vous que chaque service et client ait son propre port non utilisé de préférence.

- Chaque projet suit une architecture DAO. Consultez les fichiers de configuration pour renseigner les ports et l'accès à la base de données.

- Pour les services SOAP, consultez les fichiers `web-services.cfg` pour le déploiement des services.

- Les services sont basés sur MySQL pour la base de données. Consultez les modèles pour définir vos tables.

- Les projets incluent des dépendances Maven. Inspectez les fichiers `pom.xml` pour ces dépendances.

- Les projets sont conçus sous Eclipse.
