# Multi-Services-Soap-APIRestful-Java-Spring-Boot-PHP

# ------- ENGLISH ----------

# Project Structure

## A - 0001-mpisi-client-rest

This project contains the client part communicating with the Restful API service located in the `rest-service-etudiant` directory. To launch the service, run the `RESTClient` file.

## B - 0001-soap-ws-client

This project contains the client enabling the use of the SOAP service in the `0001-ws-soap-server-application` project. To launch the service, run the files in the `com.javaws.client` package. The `Launcher.java` file starts a graphical interface for managing authentication via the SOAP service and CRUD via the Rest API. The `ServiceWebClient` file handles SOAP in the terminal, and `AuthenticationAPP` manages authentication with a simple Swing GUI.

## C - 0001-ws-soap-server-application

This project contains the SOAP service offering basic authentication and CRUD functionality for a student. To launch the service, run the `MonitorLauncher.java` file.

## D - rest-service-etudiant

This project contains a Restful API for managing students. To launch the service, run the `RestServiceEtudiantApplication.java` file.

## Configuration and Development

- Developers should check the property files in the resources to manage the ports of services and clients. Ensure that each service and client has its own preferably unused port.

- Each project follows a DAO architecture. Check the configuration files to provide ports and database access.

- For SOAP services, refer to the `web-services.cfg` files for service deployment.

- The services are based on MySQL for the database. Look into the models to define your tables.

- The projects include Maven dependencies. Inspect the `pom.xml` files for these dependencies.

- The projects are designed under Eclipse.


# -------- FRENCH -----------

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
