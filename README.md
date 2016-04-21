#Rapport de Laboratoire SMTP

##Introduction

Le but de ce projet et de permettre � un utilisateur d�envoyer automatiquement des mails forg�s diff�rents, � une liste de victimes. Ce projet nous permettra de mieux comprendre comment fonctionne la communication avec un serveur SMTP

##Diagramme des classes

![alt tag](https://github.com/emetsys/MailRobotGit/tree/master/figures/Diagramme_des_classes.jpg)

##Description de l�impl�mentation

/!\ Nous avons oubli� de changer les chemins des fichiers de config pour faire des chemins absolus, du coup le programme ne fonctionnera pas sans les changer.
ConfigurationManager: R�cup�rer les donn�es des fichiers de config.
Person: Contenir les informations de chaque personnes associ�es � leur adresses mail,
utilis�es pour l�envoi. Nous avons choisi de mettre uniquement l�adresse mail, mais l�on pourrait par exemple extraire le nom et le pr�nom de l�adresse ou donner plus d�informations.
Message: Sert � stocker le contenu data du mail avec le sujet inclus, faire une classe permettrait �ventuellement d�ajouter un syst�me de personnalisation par exemple.
Group: Sert � cr�er un groupe avec un sender et plusieurs receinvers.
PrankGenerator: Permet de g�n�rer des groupes � partir d�une liste de personnes. Nous avons d�cid� de faire une gestion simple en prenant la premi�re personne de chaque groupe comme receiver et les autres comme receiver mais l�on aurait pu ajouter une composante al�atoire. Pour ce qui est de la g�n�ration des messages, on a d�cid� d�envoyer la liste et de choisir directement dans le main quel message on choisit, on aurait aussi pu renvoyer un message al�atoirement.
Mail: Construire le mail en prenant en param�tre le groupe, cela permet de mettre les ent�tes from:, to: et cc:. Elle met aussi � disposition ces informations pour la g�n�ration du dialogue avec le server.
SmtpClient: Permet d�envoyer le mail. On r�cup�re le configurationManager pour pouvoir lancer le socket avec la bonne adresse du serveur ainsi que le bon port.


##Instruction de mise en oeuvre

Afin de mettre en oeuvre notre application, il faut d�abord configurer le fichier config.properties (dans le dossier Config) afin de d�terminer l�adresse et le num�ro de port du serveur SMTP. Dans ce fichier, on peut aussi configurer le nombre de groupe � cr�er, et d�terminer l�adresse de la personne � mettre en CC dans le message. Ensuite l�utilisateur peut d�finir dans le fichier victimes.utf8 les adresses des personnes � pi�ger. L�utilisateur peut aussi d�terminer les diff�rents messages � envoyer dans le fichier messages.utf8. Une fois ces fichiers �dit�s, l�utilisateur peut lancer le programme, qui se chargera d�envoyer les mails en fonction des configurations de l�utilisateur.

##Mise en oeuvre du serveur mock

Afin d��viter de surcharger un serveur smtp, et de ne pas envoyer des mails frauduleux � tout le monde, nous avons utilis� un serveur SMTP MockMock en local. Ce serveur peut se t�l�charger sur le repo github. Une fois les fichiers sources r�cup�r�s, il faut ex�cuter la commande *mvn clean install* afin de r�cup�rer toutes les d�pendances du projet MockMock. Ensuite il suffit d�ex�cuter la commande *java -jar MockMock.jar*  qui d�marrera le serveur MockMock sur le port 25 du localhost de la machine. On peut r�cup�rer le smials envoy�s par le serveur en se connectant sur le port 8282.

### URL du Repo
https://github.com/emetsys/MailRobotGit

### Etudiants 
Badoux Luciens et Henocq Raphael
