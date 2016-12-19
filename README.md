# Project Setup
1. Import the project.

    > _If using [IntelliJ IDEA](https://www.jetbrains.com/idea/download/):_  
    > `File` > `New` > `Project from Existing Sources...` to import the project  

2. Configure the project.

    > _If using [IntelliJ IDEA](https://www.jetbrains.com/idea/download/):_  
    > `File` > `Project Structure...`
    > * On `Project` tab:
    >   * `Project SDK`: [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) (if there is none, click `New...` and select your local JDK directory)
    >   * `Project language Level`: `SDK default`
    > `Tools` > `Kotlin` > `Configure Kotlin in Project`
    > * Select `All modules` and `Use library from plugin`, then `OK`

3. Add `.jar` files inside `/libraries` as dependencies.

    > _If using [IntelliJ IDEA](https://www.jetbrains.com/idea/download/):_  
    > Right click the library file(s) and select `Add as Library...`

3. Read [`Lab3_tasks.pdf`](https://github.com/blead/KShootTheBullet/blob/master/Lab3_tasks.pdf) and implement each classes accordingly.

4. After compiling the project, copy the contents of `/resources` into the compiler output directory.

    > _If using [IntelliJ IDEA](https://www.jetbrains.com/idea/download/):_  
    > the default directory name is `/out`.)

___

> _*JDK can be downloaded from http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html_
> _*IntelliJ IDEA can be downloaded from https://www.jetbrains.com/idea/download/_
