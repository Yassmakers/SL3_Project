# HireHub Kotlin Applicatie

#### Handleiding voor het HireHub systeem
Dit is de handleiding voor de HireHub Kotlin-applicatie, een project dat gericht is op het ontwikkelen van een Android smartphone-app voor HireHub Resources, gespecialiseerd in HR Recruitment. De app stelt zich tot doel sollicitantprofielen van de opdrachtgever te presenteren, met de focus op het uitbrengen van een prototype voor deze app.
##   -- Bekijk ook graag de <a href="#aanvullende-opmerkingen" target="_new">Aanvullende opmerkingen</a> voor de beoordelaars. --
## Inhoudsopgave
- [Installatie](#installatie)
- [Inloggegevens](#inloggegevens)
- [Systeemvereisten](#systeemvereisten)
- [Database migratie](#database-migratie)
- [Comply and explain](#comply-and-explain)
- [Gebruikershandleiding](#gebruikershandleiding)
    - [Inloggen](#inloggen)
    - [Navigatie](#navigatie)
    - [Gebruiker Eigen Account beheren](#gebruiker-eigen-account-beheren)
    - [Admin Crud Accounts beheren](#admin-crud-accounts-beheren)
    - [Recruiter Profiel Crud beheren](#recruiter-profiel-crud-beheren)
- [Screenshots](#screenshots)
- [Aanvullende opmerkingen](#aanvullende-opmerkingen)


# Installatie

- Kloon de Git-repository of download en pak het zip-bestand uit.
- Open het project in Android Studio.
- Zorg ervoor dat alle benodigde bibliotheken correct zijn geïnstalleerd en dat de .gradle instellingen gesynct is.
- Compileer en voer het project uit.
- Er is ook een werkend APK bestand beschikbaar in de Apk HireHub Bestand folder die te vinden is op de root van het Project (de applicatie build en runt natuurlijk uitzichzelf gewoon prima en maakt dit aan dit was gewoon voor de zekerheid )


## Inloggegevens

### Gebruik de volgende inloggegevens om toegang te krijgen tot het systeem

#### (hoofdlettergevoelig) :

#### Beheerder - Administrator:

    Gebruikersnaam = Admin
    Wachtwoord = Admin123
Recruiter:

    Gebruikersnaam = Recruiter
    Wachtwoord = Recruiter123

Sollicitant:

    Gebruikersnaam = Gebruiker
    Wachtwoord = Gebruiker123

Systeemvereisten

    Android Studio
    Minimaal Android 6.0 (API-niveau 26)


## Gebruikershandleiding

### Inloggen

- #### Open de applicatie en u komt op de landingactivity pagina dit is de introductiepagina waar gebruikers begroet worden met informatie over de HireHub Resources-app. Gebruikers hebben de mogelijkheid om in te loggen, te registreren of als gast door te gaan. Houd er echter rekening mee dat sommige functies mogelijk niet beschikbaar zijn voor gastgebruikers.

### Navigatie

- #### Bottom Navigation spreekt voor zich daarmee kan je door de app heen navigeren.
- #### Based on your role kan je verschillende dingen op de bottom navigation zien een admin bijvoorbeeld ziet de dashboard voor account crud
- #### De Recruiter ziet dashboard voor Profielcrud
- #### Een normale gebruiker (sollicitant) ziet alleen maar de profiel knop om zijn eigen profiel te wijzigen/verwijderen
- #### En een gast kan alleen de sollicitanten zien (mits zij hun eigen profiel op zichtbaar gelaten hebben) en kan op de knop registreren en inloggen drukken



### Gebruiker Eigen Account beheren
- Klik op de knop  'Home' in de navigatie om de lijst met sollicitanten te bekijken.
- Klik op de knop 'Profiel' in de bottom navigation om je profiel te weergeven.
- Klik op 'Verander' om de gegevens van je profiel bij te werken en klik op 'Opslaan'.
- Klik op 'Verwijderen' om je profiel met je Account te verwijderen.
- Klik op 'toggle visibility' om het profiel voor gastgebruikers onzichtbaar te maken.
- Klik op 'Reset Profiel' om je Account met het profiel standaard waarden te geven resetten naar default.



### Admin Crud Accounts beheren
- Klik op de knop  'Home' in de navigatie om de lijst met sollicitanten te bekijken.
- Klik op de knop 'Dashboard' in de bottom navigation om de lijst met accounts te bekijken.
- Klik op de '+' icoon om een nieuw account aan de lijst toe te voegen.
  Vul de gegevens van het nieuwe account in en klik 'Opslaan'.
- Klik op 'Zichtbaarheid Wijzigen' om het profiel voor gastgebruikers onzichtbaar te maken.
- Klik op 'Delete' om het account te verwijderen klik op 'Bevestig'.
- Klik op 'Update' om de gegevens van een Account bij te werken en klik op 'Opslaan'.
- Klik op 'Verwijderen' om een Account uit de lijst te verwijderen.
- Klik op 'Reset Profiel' om een Account met het profiel standaard waarden te geven resetten naar default.
- Klik op 'Show Details' om de details van een Account uit de lijst te weergeven.



### Recruiter Profiel Crud beheren
- Klik op de knop  'Home' in de navigatie om de lijst met sollicitanten te bekijken.
- Klik op de knop  'Dashboard' in de navigatie om de lijst met profielen te bekijken.
- Klik op het de knop 'Dashboard' om de lijst met profielen te bekijken.
- Klik op de '+' knop om een nieuwe profiel aan de lijst toe te voegen.
  Vul de gegevens van de nieuwe profiel in en klik op 'Opslaan'.
- Klik op 'Zichtbaarheid Wijzigen' om het profiel voor gastgebruikers onzichtbaar te maken.
- Klik op 'Reset Profiel' om een Account met het profiel standaard waarden te geven resetten naar default (Soft-delete).


# Comply and explain


# Screenshots
https://gyazo.com/b743d020e255b1598fd8b59848160ef1
https://gyazo.com/d0aaf6c1970cdeb8a776f4519ae6e0c0
# Aanvullende opmerkingen

### -
We willen graag vermelden dat we dit project hebben gemaakt, echter, vanwege ziekte en privésituaties hadden we niet evenveel tijd beschikbaar. Onze coach, Stephan, is op de hoogte van deze omstandigheden. We hopen op begrip voor deze situatie en vragen om coulance bij de beoordeling van onze prestaties. Mochten jullie meer informatie hierover nodig hebben dan zijn wij beschikbaar voor contact.


> 👨‍🎓 **Yassine Messaoudi**
>
> - S-number: s1188088
> - Email: [s1188088@student.windesheim.nl](mailto:s1188088@student.windesheim.nl)
> - Github: [@Yassineprojects](https://github.com/Yassmakers)

> 👨‍🎓 **Jeffrey Tang**
>
> - S-number: s1188666
> - Email: [s1188666@student.windesheim.nl](mailto:s1188666@student.windesheim.nl)
> - Github: [@TangJFP](https://github.com/TangJFP)
> - GitLab: [@TangJF](https://gitlab.com/TangJF)
