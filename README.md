#Rapport de Laboratoire SMTP

##Introduction

Le but de ce projet et de permettre à un utilisateur d’envoyer automatiquement des mails forgés différents, à une liste de victimes. Ce projet nous permettra de mieux comprendre comment fonctionne la communication avec un serveur SMTP

##Diagramme des classes

![alt tag](https://github.com/emetsys/MailRobotGit/tree/master/figures/Diagramme_des_classes.jpg)

##Description de l’implémentation

/!\ Nous avons oublié de changer les chemins des fichiers de config pour faire des chemins absolus, du coup le programme ne fonctionnera pas sans les changer.
ConfigurationManager: Récupérer les données des fichiers de config.
Person: Contenir les informations de chaque personnes associées à leur adresses mail,
utilisées pour l’envoi. Nous avons choisi de mettre uniquement l’adresse mail, mais l’on pourrait par exemple extraire le nom et le prénom de l’adresse ou donner plus d’informations.
Message: Sert à stocker le contenu data du mail avec le sujet inclus, faire une classe permettrait éventuellement d’ajouter un système de personnalisation par exemple.
Group: Sert à créer un groupe avec un sender et plusieurs receinvers.
PrankGenerator: Permet de générer des groupes à partir d’une liste de personnes. Nous avons décidé de faire une gestion simple en prenant la première personne de chaque groupe comme receiver et les autres comme receiver mais l’on aurait pu ajouter une composante aléatoire. Pour ce qui est de la génération des messages, on a décidé d’envoyer la liste et de choisir directement dans le main quel message on choisit, on aurait aussi pu renvoyer un message aléatoirement.
Mail: Construire le mail en prenant en paramètre le groupe, cela permet de mettre les entêtes from:, to: et cc:. Elle met aussi à disposition ces informations pour la génération du dialogue avec le server.
SmtpClient: Permet d’envoyer le mail. On récupère le configurationManager pour pouvoir lancer le socket avec la bonne adresse du serveur ainsi que le bon port.


##Instruction de mise en oeuvre

Afin de mettre en oeuvre notre application, il faut d’abord configurer le fichier config.properties (dans le dossier Config) afin de déterminer l’adresse et le numéro de port du serveur SMTP. Dans ce fichier, on peut aussi configurer le nombre de groupe à créer, et déterminer l’adresse de la personne à mettre en CC dans le message. Ensuite l’utilisateur peut définir dans le fichier victimes.utf8 les adresses des personnes à piéger. L’utilisateur peut aussi déterminer les différents messages à envoyer dans le fichier messages.utf8. Une fois ces fichiers édités, l’utilisateur peut lancer le programme, qui se chargera d’envoyer les mails en fonction des configurations de l’utilisateur.

##Mise en oeuvre du serveur mock

Afin d’éviter de surcharger un serveur smtp, et de ne pas envoyer des mails frauduleux à tout le monde, nous avons utilisé un serveur SMTP MockMock en local. Ce serveur peut se télécharger sur le repo github. Une fois les fichiers sources récupérés, il faut exécuter la commande *mvn clean install* afin de récupérer toutes les dépendances du projet MockMock. Ensuite il suffit d’exécuter la commande *java -jar MockMock.jar*  qui démarrera le serveur MockMock sur le port 25 du localhost de la machine. On peut récupérer le smials envoyés par le serveur en se connectant sur le port 8282.

### URL du Repo
https://github.com/emetsys/MailRobotGit

### Etudiants 
Badoux Luciens et Henocq Raphael
