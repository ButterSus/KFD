# Terminal - Test task of KFD

## Description

Check the original repository to find the task description.

Here I'll share some implementation details:
1. My programming language of choice is [Kotlin](https://kotlinlang.org/).
2. I use [Gradle](https://gradle.org/). 
3. Application provides simple interactive CLI to run terminal.
4. Initial exchange rates are calculated based on initial terminal balance.
5. Code can be extended to have multiple users. Check `User.kt` for details.
6. There are some quality of life enhancements such as detailed error messages.

Here is some real output from terminal:

```text
PS C:\Users\admin\IdeaProjects\Terminal> java -jar .\build\libs\Terminal-1.0-SNAPSHOT-all.jar           
Welcome to terminal!
Enter an operation (or h for help): h
Available operations:
    h - help
    b - balance
    c - print currency list
    d - print exchange rates
    e - exit
    ex[amount]<from>-[amount]<to> - exchange
Example: ex1000USD-RUB; exRUB-250USD
Enter an operation (or h for help): b
Your balance:
    RUB - 1 000 000,0000000000
    USD - 0,0000000000
    EUR - 0,0000000000
    USDT - 0,0000000000
    BTC - 0,0000000000
Terminal balance:
    RUB - 10 000,0000000000
    USD - 1 000,0000000000
    EUR - 1 000,0000000000
    USDT - 1 000,0000000000
    BTC - 1,5000000000
Enter an operation (or h for help):
```

## Build

In order to build runnable jar file we need to bundle all kotlin
libraries and dependencies in one jar file.

[Shadow](https://github.com/GradleUp/shadow?ysclid=m0vb0xowdq283487607) plugin is used to do that.

```shell
./gradlew shadowJar
```

## Usage

Now run jar file with `java > 17.0`.

```shell
java -jar ./build/libs/Terminal-1.0-SNAPSHOT-all.jar
```


