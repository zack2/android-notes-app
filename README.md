# Build an Android Notes app (Medium)
##### Build an Android Notes app

1) Create main screen with list of notes
   Screen has to display notes list and add button :
    - List item (contains note title) → After click it has to transfer you to NoteDetailsScreen
    - Add button → After click it has to transfer you to AddNotesScreen


2) Create NoteDetailsScreen 
  The screen contains
    - Note description
    - Note description
    - Save button
  The note has to be editable!

   
3) Create AddNotesScreen
  The screen contains:
    - Add title input field
    - Description Input field
    - Save button
  Notes has to be sorted by last update timestamp. For example if you edit the note, it has to be the first in the list

  Data have to be shown in this order: loader → data from database

Tech stack:

- Jetpack Compose
- Kotlin
- MVVM Architecture
- Kotlin coroutines
- Retrofit
- Dagger 2 or Dagger Hilt
- Single Activity Design Pattern
- Clean Architecture
## Project Structure


Dillinger uses a number of open source projects to work properly:

- ```components``` Contain all custom reusable composable
- ```data``` Contain all model, data source, repository and Room
    - ```model``` Contain entities
    -  ```repository``` Contain repositories
- ```di``` Contain all module for Dependency Injection
- ```navigation```  Contain NavGraph and Screen 
- ```ui.theme``` theme of app
-  ```utils``` Contain extension
- ```views``` the views
- ```vm``` the viewmodel

## Release
You can find the apk  [here](https://github.com/zack2/android-notes-app/tree/main/app/release)