# The Movie Db Client

## Overview
O objetivo desse exercicio era escrever um aplicativo que apresentassem os filmes do [The Movie Db](https://www.themoviedb.org/) em ordem de popularidade e também que permitisse realizar uma busca pelos filmes.

Foi escolhido o [MVP](https://antonioleiva.com/mvp-android/) como padrão de separação da camada de apresentação, pois facilita a leitura, manutenção e a escrita de testes unitários.

O projeto possui a seguinte separação de pacotes:

|-- data                            //models e comunicações externas
|    |-- loader                     //Loaders e callbacks das requisiçoes
|    |-- model                      //classes de modelos
|-- extension                       //kotlin extension
|-- ui                              //parte visual
|    |-- activity                   //todas as activities
|    |-- adapter                    //todos os adapters
|    |-- presenter                  //todos os presenters
|    |-- view                       //protocólos entre os presenters e as views

## Tools
### IDE
* Android Studio

### Language
* Kotlin

### Libraries:
* Google Support Library